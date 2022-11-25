package app.technotech.koncpt.ui.fragments.liveclass;

import android.app.AlertDialog;
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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.DBHelper;
import app.technotech.koncpt.data.network.model.DownloadModel;
import app.technotech.koncpt.databinding.FragmentDownloadsBinding;
import app.technotech.koncpt.ui.adapter.DownloadAdapter;
import app.technotech.koncpt.utils.DebugLog;
import app.technotech.koncpt.utils.GeneralUtils;

public class DownloadFragment extends Fragment implements DownloadAdapter.OnVideoItemSelectedListener {

    FragmentDownloadsBinding binding;
    private GeneralUtils utils;
    private AlertDialog progressDialog;
    DBHelper dbHelper;
    ArrayList<DownloadModel> arrayList;
    DownloadAdapter downloadAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_downloads, container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        utils = new GeneralUtils(getActivity());
        progressDialog = utils.showProgressDialog();

        dbHelper = new DBHelper(getActivity());


        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                arrayList = dbHelper.getAllVideos();
                Log.d("videoList", String.valueOf(arrayList.size()) + "");
                if (arrayList.size() == 0) {
                    binding.recyclerView.setVisibility(View.GONE);
                    binding.lnrClasses.setVisibility(View.VISIBLE);

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    binding.lnrClasses.setVisibility(View.GONE);
                    binding.recyclerView.setVisibility(View.VISIBLE);

                    updateView();

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }

            }
        }, 2500);


    }

    private void updateView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        downloadAdapter = new DownloadAdapter(getActivity(), arrayList, this);
        binding.recyclerView.setAdapter(downloadAdapter);
        downloadAdapter.notifyDataSetChanged();
        binding.txtNoData.setVisibility(View.GONE);
    }

    @Override
    public void onVideoItemClick(DownloadModel data, int position) {

        String jsonData = new Gson().toJson(data);
        Bundle bundle = new Bundle();
        bundle.putString("data", jsonData);

        Navigation.findNavController(binding.getRoot()).navigate(R.id.videoFragment, bundle);

    }

    @Override
    public void onVideoDelete(String id, String video_url, int position) {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                dbHelper.deleteVideo(id);
                downloadAdapter.removeItem(position);

                File deleteFile = new File(video_url);
                if (deleteFile.exists()) {
                    if (deleteFile.delete()) {
                        DebugLog.e("File Deleted");
                    } else {
                        DebugLog.e("File Not Deleted");
                    }
                }

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        }, 500);


    }
}
