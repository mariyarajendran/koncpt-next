package app.technotech.koncpt.ui.fragments.liveclass;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.DBHelper;
import app.technotech.koncpt.data.network.model.ModelNotes;
import app.technotech.koncpt.databinding.FragmentNotesViewBinding;
import app.technotech.koncpt.ui.viewmodels.ModelNotesViewModel;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;

public class NotesFragment extends Fragment {

    String video_id;
    String class_video,title,description,videoId;

    private ModelNotesViewModel model;
    private GeneralUtils generalUtils;
    FragmentNotesViewBinding binding;
    private AlertDialog progressDialog;
    DBHelper db;
    ProgressDialog progressDialogs;
    public static final int progress_bar_type = 0;
    private MainFragment mainFragment;

    public static NotesFragment getInstance(String params) {

        Bundle bundle = new Bundle();
        NotesFragment notesFragment = new NotesFragment();
        bundle.putString("video_id", params);
        notesFragment.setArguments(bundle);
        return notesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            video_id = getArguments().getString("video_id");
            //Toasty.info(getActivity(), video_id+"fgghv").show();
            Log.d("video_id",video_id+"");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes_view, viewGroup, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(ModelNotesViewModel.class);
        binding.setClassViewModel(model);
        progressDialogs=new ProgressDialog(getActivity());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainFragment = MainFragment.getInstance();

        generalUtils = new GeneralUtils(getActivity());
        progressDialog = generalUtils.showProgressDialog();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        } else {
            progressDialog.show();
            sendPost();
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                progressDialog.show();
                sendPost();
                break;
        }
    }



    private void sendPost() {
        Map<String, String> params = new HashMap<>();
        params.put("video_id",video_id);
        params.put("type","0");
        DebugLog.e("params All==> " + params.toString()); //this values are not displayed..check


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        model.getCompleteData(params).observe(getActivity(), new Observer<ModelNotes>() {
            @Override
            public void onChanged(ModelNotes notesModel) {


                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (notesModel != null) {

                            if (notesModel.getStatus() == 1){
                                // Toasty.success(getActivity(), notesModel.getMessage()+"").show();
//                                String jsonData = new Gson().toJson(notesModel);
//                                DebugLog.e("Response =>>" + jsonData);
//                                int listSize=notesModel.getData().size();
//                                String size=String.valueOf(listSize);
//                                mainFragment.UpdateMyCount(size, 2);//last try hope will work

                                String pdf_url=notesModel.getData().get(0).getClassPdf();
                                if(pdf_url!=null)
                                {
                                    if (!(pdf_url.equals("")))
                                    {

                                        new RetrivePDFStream().execute(pdf_url);
                                        binding.pdfView.setVisibility(View.VISIBLE);
                                        binding.lnrPlans.setVisibility(View.GONE);
                                    }
                                }
                                else if(pdf_url.equals(""))
                                {
                                    Toasty.info(getContext(),"No Data Found !");


//                                       binding.pdfView.setVisibility(View.GONE);
//
//                                       binding.lnrPlans.setVisibility(View.VISIBLE);
//                                       Log.d("emptyPdf","emptyPdf");
                                }






                                //   binding.txtTitle.setText(notesModel.getData().get(0).getSubjectTitle());

                            } else {
                                Toasty.error(getActivity(), notesModel.getMessage()+"").show();
                            }

                        }
                        else
                        {
//                            binding.lnrPlans.setVisibility(View.VISIBLE);
//                            binding.activityMain.setVisibility(View.GONE);

                        }

                    }
                }, 500);
            }
        });
    }

    class RetrivePDFStream extends AsyncTask<String, Void, InputStream> {
        public InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL uri = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        public void onPostExecute(InputStream inputStream) {
            // binding.pdfView.fromStream(inputStream).password("Your Password").load();

            binding.pdfView.fromStream(inputStream)
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                    .password(null)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    // spacing between pages in dp. To define spacing color, set view background
                    .spacing(0)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .load();
        }
    }
}
