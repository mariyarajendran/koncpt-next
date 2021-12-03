package app.technotech.koncpt.ui.fragments.liveclass;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.SlidesModel;
import app.technotech.koncpt.databinding.FragmentSlidesClassBinding;
import app.technotech.koncpt.ui.adapter.SliderAdapterExample;
import app.technotech.koncpt.ui.viewmodels.SlidesModelViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class SlidesFragment extends Fragment {

    String video_id;
    String class_video, title, description, videoId;
    private SlidesModelViewModel model;
    private GeneralUtils generalUtils;
    FragmentSlidesClassBinding binding;
    private AlertDialog progressDialog;
    ProgressDialog progressDialogs;
    private MainFragment mainFragment;

    public static SlidesFragment getInstance(String params) {

        Bundle bundle = new Bundle();
        SlidesFragment slidesFragment = new SlidesFragment();
        bundle.putString("video_id", params);
        slidesFragment.setArguments(bundle);
        return slidesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            video_id = getArguments().getString("video_id");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_slides_class, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(SlidesModelViewModel.class);
        binding.setClassViewModel(model);
        progressDialogs = new ProgressDialog(getActivity());
        return binding.getRoot();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainFragment = MainFragment.getInstance();
        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        sendPost();
    }


    private void sendPost() {

        Map<String, String> params = new HashMap<>();
        params.put("video_id", video_id);
        params.put("type", "1");

// now tell me what is happening
        //there ? hey
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<SlidesModel>() {
            @Override
            public void onChanged(SlidesModel notesModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //check response...  postman

                        if (notesModel != null) {

                            if (notesModel.getStatus() == 1) {
//                                if (notesModel.getData().size() > 0) {
//                                    int listSize = notesModel.getData().size();
//                                    String size = String.valueOf(listSize);
//                                    if (listSize > 0) {
//                                        mainFragment.UpdateMyCount(size, 1);//last try hope will work
//                                    }
//                                }

                                if ((notesModel.getData().get(0).getClassSlides() != null) && !notesModel.getData().get(0).getClassSlides().isEmpty()) {
                                    binding.cardView.setVisibility(View.VISIBLE);

                                    SliderAdapterExample adapter = new SliderAdapterExample(getContext(), notesModel.getData().get(0).getClassSlides());
                                    binding.imageSlider.setSliderAdapter(adapter);

                                    binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                                    binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                                    binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                                    binding.imageSlider.setIndicatorSelectedColor(Color.GREEN);
                                    binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
                                    binding.imageSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
                                    binding.imageSlider.startAutoCycle();

                                    binding.lnrPlans.setVisibility(View.GONE);
                                } else {
                                    binding.cardView.setVisibility(View.GONE);
                                }
                            } else {
                                Toasty.error(getActivity(), notesModel.getMessage() + "").show();
                            }
                        }

                    }
                }, 500);
            }
        });


    }

}
