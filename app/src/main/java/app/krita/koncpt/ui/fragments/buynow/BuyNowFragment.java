package app.technotech.koncpt.ui.fragments.buynow;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.krita.koncpt.utils.EnumApiAction;
import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.BuyNowModel;
import app.technotech.koncpt.databinding.FragmentBuyNowBinding;
import app.technotech.koncpt.ui.callbacks.PlanCallbacks;
import app.technotech.koncpt.ui.viewmodels.BuyNowViewModel;
import app.technotech.koncpt.utils.AppSharedPreference;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

import static com.testfairy.g.f.U;

public class BuyNowFragment extends Fragment {
    private Context mContext;
    private FragmentBuyNowBinding binding;
    private BuyNowViewModel model;
    private BuyNowModel buyNowData;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private List<BuyNowModel.Datum> modelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_buy_now, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(BuyNowViewModel.class);
        binding.setBuyViewModel(model);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.action_index).setVisible(false);
        menu.findItem(R.id.action_notification).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        callBuyNowApi();
        clickListener();
    }

    private void callBuyNowApi() {
        Map<String, String> params = new HashMap<>();
        params.put(EnumApiAction.action.getValue(), EnumApiAction.AllPlans.getValue());
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
        model.getHuntData(params).observe(getActivity(), new Observer<BuyNowModel>() {
            @Override
            public void onChanged(BuyNowModel buyNowModel) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        if (buyNowModel != null) {
                            if (buyNowModel.getStatus() == 1) {
                                buyNowData = buyNowModel;
                                String response = new Gson().toJson(buyNowModel);
                                DebugLog.e("Response : " + response);
                                for (int i = 0; i < buyNowModel.getData().size(); i++) {
                                    if (buyNowModel.getData().get(i).getStatus() == 1) {
                                        if (buyNowModel.getData().get(i).getId() == 1) {
                                            binding.cardPlanAContainer.setVisibility(View.VISIBLE);
                                        }
                                        if (buyNowModel.getData().get(i).getId() == 2) {
                                            binding.cardPlanBContainer.setVisibility(View.VISIBLE);
                                        }
                                        if (buyNowModel.getData().get(i).getId() == 3) {
                                            binding.cardPlanCContainer.setVisibility(View.VISIBLE);
                                        }
                                        if (buyNowModel.getData().get(i).getId() == 4) {
                                            binding.cardPlanDContainer.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                                binding.btnA.setText(buyNowModel.getData().get(0).getName());
                                binding.btnB.setText(buyNowModel.getData().get(1).getName());
                                binding.btnC.setText(buyNowModel.getData().get(2).getName());
                                binding.btnD.setText(buyNowModel.getData().get(3).getName());
                                binding.txtHeadingOne.setText(buyNowModel.getData().get(0).getPlanHeading());
                                binding.txtHeadingTwo.setText(buyNowModel.getData().get(1).getPlanHeading());
                                binding.txtHeadingThree.setText(buyNowModel.getData().get(2).getPlanHeading());
                                binding.txtHeadingFour.setText(buyNowModel.getData().get(3).getPlanHeading());
                                String plantType = new AppSharedPreference(getActivity()).getUserResponse().getPlan();
                                int unicode = U + 2705;
                                switch (plantType) {
                                    case "a":
                                    case "A":
                                        binding.btnA.setText(buyNowModel.getData().get(0).getName() + "   ✔");
                                        binding.txtHeadingOne.setText("Current Plan");
                                        break;
                                    case "b":
                                    case "B":
                                        binding.btnB.setText(buyNowModel.getData().get(1).getName() + "   ✔");
                                        binding.txtHeadingTwo.setText("Current Plan");
                                        break;
                                    case "c":
                                    case "C":
                                        binding.btnC.setText(buyNowModel.getData().get(2).getName() + "   ✔");
                                        binding.txtHeadingThree.setText("Current Plan");
                                        break;
                                    case "d":
                                    case "D":
                                        binding.btnD.setText(buyNowModel.getData().get(3).getName() + "   ✔");
                                        binding.txtHeadingFour.setText("Current Plan");
                                        break;
                                }

                            }
                        }

                    }
                }, 500);

            }
        });
    }

    private void clickListener() {
        binding.setPlanCallback(new PlanCallbacks() {
            @Override
            public void onPlanA() {
                Bundle bundle = new Bundle();
                bundle.putInt("id", buyNowData.getData().get(0).getId());
                bundle.putString("name", buyNowData.getData().get(0).getName());
                bundle.putString("heading", buyNowData.getData().get(0).getPlanHeading());
                bundle.putString("package", buyNowData.getData().get(0).getAmount());
                bundle.putString("validity", buyNowData.getData().get(0).getValidity());
                bundle.putString("type", "A");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyDetailsFragment, bundle);
            }

            @Override
            public void onPlanB() {
                Bundle bundle = new Bundle();
                bundle.putInt("id", buyNowData.getData().get(1).getId());
                bundle.putString("name", buyNowData.getData().get(1).getName());
                bundle.putString("heading", buyNowData.getData().get(1).getPlanHeading());
                bundle.putString("package", buyNowData.getData().get(1).getAmount());
                bundle.putString("validity", buyNowData.getData().get(1).getValidity());
                bundle.putString("type", "B");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyDetailsFragment, bundle);
            }

            @Override
            public void onPlanC() {
                Bundle bundle = new Bundle();
                bundle.putInt("id", buyNowData.getData().get(2).getId());
                bundle.putString("name", buyNowData.getData().get(2).getName());
                bundle.putString("heading", buyNowData.getData().get(2).getPlanHeading());
                bundle.putString("package", buyNowData.getData().get(2).getAmount());
                bundle.putString("validity", buyNowData.getData().get(2).getValidity());
                bundle.putString("type", "C");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyDetailsFragment, bundle);
            }

            @Override
            public void onPlanD() {
                Bundle bundle = new Bundle();
                bundle.putInt("id", buyNowData.getData().get(3).getId());
                bundle.putString("name", buyNowData.getData().get(3).getName());
                bundle.putString("heading", buyNowData.getData().get(3).getPlanHeading());
                bundle.putString("package", buyNowData.getData().get(3).getAmount());
                bundle.putString("validity", buyNowData.getData().get(3).getValidity());
                bundle.putString("type", "D");
                Navigation.findNavController(binding.getRoot()).navigate(R.id.buyDetailsFragment, bundle);
            }
        });
    }
}