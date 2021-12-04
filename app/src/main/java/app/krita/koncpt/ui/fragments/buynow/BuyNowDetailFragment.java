package app.technotech.koncpt.ui.fragments.buynow;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.razorpay.Checkout;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.krita.koncpt.utils.DateUtil;
import app.krita.koncpt.utils.EnumApiAction;
import app.krita.koncpt.utils.TextUtil;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BuyDetailsModel;
import app.technotech.koncpt.data.network.model.PaymentFailureResponse;
import app.technotech.koncpt.data.network.model.PaymentSuccessModel;
import app.technotech.koncpt.data.network.model.UserModelLogin;
import app.technotech.koncpt.databinding.FragmentBuyNowDetailBinding;
import app.technotech.koncpt.ui.activity.MainActivity;
import app.technotech.koncpt.ui.adapter.BuyDetailsAdapter;
import app.technotech.koncpt.ui.viewmodels.BuyDetailsViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class BuyNowDetailFragment extends Fragment implements MainActivity.passOnData {
    private FragmentBuyNowDetailBinding binding;
    private BuyDetailsViewModel model;
    private GeneralUtils generalUtils;
    private AppSharedPreference sharedPreference;
    private BuyDetailsAdapter buyDetailsAdapter;
    int id, validaty;
    String idValue, type;
    String name, amount, heading;
    TextView txtAmount, txtPlanName, txtValidaty, txtHeading;
    private AlertDialog progressDialog;
    private MainActivity mainActivity;
    MainActivity.passOnData listener;
    DateUtil dateUtils;

    public BuyNowDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString("name");
            heading = getArguments().getString("heading");
            id = getArguments().getInt("id");
            idValue = String.valueOf(id);
            amount = getArguments().getString("package");
            validaty = getArguments().getInt("validity");
            type = getArguments().getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buy_now_detail, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(BuyDetailsViewModel.class);
        binding.setClassViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        String plantType = new AppSharedPreference(getActivity()).getUserResponse().getPlan();
        if (plantType.equalsIgnoreCase(type)) {
            binding.btnBuyNow.setVisibility(View.GONE);
        }
        mainActivity = MainActivity.getInstance();
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).setSendData(this);
        }
        dateUtils = new DateUtil();
        generalUtils = new GeneralUtils(getActivity());
        sharedPreference = new AppSharedPreference(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        binding.txtPlanName.setText(name);
        binding.txtHeading.setText(heading);
        binding.txtAmount.setText("\u20B9" + amount);
        binding.txtValidaty.setText("(" + String.valueOf(validaty) + "year" + ")");
        String currentPlan = sharedPreference.getUserResponse().getPlan().toUpperCase();
        if (currentPlan.equals(type) && GeneralUtils.getRemainingDate(sharedPreference.getUserResponse().getSubscriptionEndsAt()) > 0) {
            binding.btnBuyNow.setEnabled(false);
            binding.btnBuyNow.setBackgroundResource(R.drawable.drawable_disable_yellow_button);
        }
        binding.btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        binding.imageCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("9428190633"));
                    startActivity(intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        binding.imageEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"support@koncptnext.in"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        binding.imageFaqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = ((MainActivity) requireActivity()).getmNavController();
                navController.navigate(R.id.faqFragment);
            }
        });
        onApiCall();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_notification).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void onApiCallUserSubscription(BuyDetailsModel.Data data) {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.UserSubscription.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("plan_id", TextUtil.cutNull(data.getPlan_id()));
        params.put("level_id", TextUtil.cutNull(data.getLevel_id()));
        params.put("validity", TextUtil.cutNull(data.getValidity()));
        params.put("validity_type", TextUtil.cutNull(data.getValidity_type()));
        params.put("subscription_start_date", TextUtil.cutNull(dateUtils.getCurrentDate()));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getUserSubscriptionData(params).observe(getActivity(), userSubscription -> new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if (userSubscription != null) {
                    if (userSubscription.getStatus() == 1) {
                        navigateToHomeFragment();
                        Toasty.success(getActivity(), userSubscription.getMessage()).show();
                    } else {
                        Toasty.error(getActivity(), userSubscription.getMessage()).show();
                    }
                }
            }
        }, 500));
    }


    private void onApiCall() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.PlanWiseLevel.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("plan_id", idValue);
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getBuyDetailsData(params).observe(getActivity(), new Observer<BuyDetailsModel>() {
            @Override
            public void onChanged(BuyDetailsModel notesModel) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        if (notesModel != null) {
                            String jsonData = new Gson().toJson(notesModel);
                            DebugLog.e("Data  : " + jsonData);
                            if (notesModel.getData() != null && notesModel.getData().size() > 0) {
                                loadData(notesModel);
                            } else {
                                binding.btnBuyNow.setEnabled(false);
                                binding.btnBuyNow.setBackgroundResource(R.drawable.drawable_disable_yellow_button);
                            }
                        }
                    }
                }, 500);
            }
        });
    }

    private void loadData(BuyDetailsModel notesModel) {
        binding.recyclerViewDetails.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewDetails.setItemAnimator(new DefaultItemAnimator());
        buyDetailsAdapter = new BuyDetailsAdapter(getActivity(), notesModel.getData(), (int position) -> {
            onApiCallUserSubscription(notesModel.getData().get(position));
        });
        binding.recyclerViewDetails.setAdapter(buyDetailsAdapter);
    }

    private void startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Checkout co = new Checkout();
//        co.setKeyID("rzp_test_Jzct00fkQb0pEF");
        co.setKeyID("rzp_live_OY1pQH7hDbYOWL"); // live
        co.setImage(R.mipmap.ic_launcher);
        try {
            JSONObject options = new JSONObject();
            options.put("name", new AppSharedPreference(getActivity()).getUserResponse().getName());
            options.put("description", name + "\n" + "Payment Plan Subscription");
            options.put("currency", "INR");
            options.put("theme.color", "#9BCB40");
            double total = Double.parseDouble(amount) * 100;
            //total = total * 100;
            options.put("amount", total);
            JSONObject preFill = new JSONObject();
            preFill.put("email", new AppSharedPreference(getActivity()).getUserResponse().getEmail());
            preFill.put("contact", new AppSharedPreference(getActivity()).getUserResponse().getPhone());
            options.put("prefill", preFill);
            co.open(mainActivity, options);
        } catch (Exception e) {
            Toast.makeText(requireActivity(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void razorPaySuccess(String razorpayPaymentID) {
        if (razorpayPaymentID != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Calendar calendar = Calendar.getInstance();
            Date today = calendar.getTime();
            calendar.add(Calendar.DAY_OF_YEAR, 365);
            Date tomorrow = calendar.getTime();
            String todayAsString = dateFormat.format(today);
            Log.d("todayAsString", todayAsString + "");
            String tomorrowAsString = dateFormat.format(tomorrow);
            Log.d("tomorrowAsString", tomorrowAsString + "");
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendPost(todayAsString, tomorrowAsString, razorpayPaymentID);
                }
            }, 100);
        }
    }

    private void sendPost(String todayAsString, String tomorrowAsString, String razorpayPaymentID) {
        Map<String, String> params = new HashMap<>();
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        params.put("start_date", todayAsString);
        params.put("end_date", tomorrowAsString);
        params.put("plan_type", type);
        params.put("transaction_id", razorpayPaymentID);
        params.put("total_amount", amount);
        params.put("payment_status", "success");
        DebugLog.e("paramsPAy==> " + params.toString());
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<PaymentSuccessModel>() {
            @Override
            public void onChanged(PaymentSuccessModel completeModel) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (completeModel != null) {
                            if (completeModel.getStatus() == 1) {
                                // Toasty.success(getActivity(), completeModel.getMessage()).show();
                                if (completeModel.getMessage().equals("Payment updated successfully")) {
                                    updateUserData();
                                } else {
                                    DebugLog.e("Mo Data found");
                                }
                            } else {
                                Toasty.error(getActivity(), completeModel.getMessage()).show();
                            }
                        }
                    }
                }, 500);
            }
        });
    }

    private void updateUserData() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.ProfileDetail.getValue());
        params.put("user_id", Integer.toString(new AppSharedPreference(getActivity()).getUserResponse().getId()));
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getUserData(params).observe(getActivity(), new Observer<UserModelLogin>() {
            @Override
            public void onChanged(UserModelLogin userModelLogin) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        if (userModelLogin != null) {
                            if (userModelLogin.getStatus() == 1) {
                                String jsonData = new Gson().toJson(userModelLogin.getData());
                                new AppSharedPreference(getActivity()).addUserData(jsonData);
                                new AppSharedPreference(getActivity()).saveHomeScreenData(null);
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                getActivity().finish();
                            }
                        }
                    }
                }, 500);
            }
        });
    }

    public void razorPayFailure(int i, String s) {
        try {
            PaymentFailureResponse response = new Gson().fromJson(s, new TypeToken<PaymentFailureResponse>() {
            }.getType());
            Toasty.info(getActivity(), response.getError().getReason()).show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void navigateToHomeFragment() {
        NavController navController = ((MainActivity) requireActivity()).getmNavController();
        navController.navigate(R.id.homeFragment);
    }

    @Override
    public void onSuccess(String s) {
        razorPaySuccess(s);
    }

    @Override
    public void onFailure(int i, String s) {
        razorPayFailure(i, s);
    }
}