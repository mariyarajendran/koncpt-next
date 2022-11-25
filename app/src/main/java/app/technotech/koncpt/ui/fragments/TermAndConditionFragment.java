package app.technotech.koncpt.ui.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.TNCModel;
import app.technotech.koncpt.databinding.FragmentTermAndConditionBinding;
import app.technotech.koncpt.ui.viewmodels.TermsNConditionViewModel;
import app.technotech.koncpt.utils.GeneralUtils;
import es.dmoral.toasty.Toasty;


public class TermAndConditionFragment extends Fragment {


    private FragmentTermAndConditionBinding binding;
    private TermsNConditionViewModel model;
    private GeneralUtils generalUtils;
    private AlertDialog progressDialog;
    private Context mContext;
    private String args = "0";


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            args = getArguments().getString("TNS");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_term_and_condition, container, false);
        binding.setLifecycleOwner(this);
        model = new ViewModelProvider(this).get(TermsNConditionViewModel.class);
        binding.setTncViewModel(model);


        return binding.getRoot();
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

        generalUtils = new GeneralUtils(mContext);
        progressDialog = generalUtils.showProgressDialog();
        if (args.equals("1")) {
            binding.imageBtnBackButton.setVisibility(View.VISIBLE);

            binding.imageBtnBackButton.setOnClickListener(view1 -> getActivity().onBackPressed());

        }


        WebSettings webSettings = binding.webiew.getSettings();
        webSettings.setJavaScriptEnabled(true);

        binding.webiew.setWebViewClient(new WebViewClientMaster());
        binding.webiew.loadUrl("https://koncptkritatechnosolutions.com/page/terms-of-use-4");
    }

    private void callApi() {

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }


        model.getTermsNConditionsData().observe(getActivity(), new Observer<TNCModel>() {
            @Override
            public void onChanged(TNCModel tncModel) {

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            if (tncModel != null) {
                                if (tncModel.getStatus() == 1) {

                                    Toasty.success(mContext, tncModel.getMessage()).show();
                                    String data = tncModel.getRecords();
                                    binding.textHome.setText(Html.fromHtml(data));
                                } else {
                                    Toasty.success(mContext, tncModel.getMessage()).show();
                                }
                            } else {
                                Toasty.success(mContext, "Oops! Something went wrong").show();
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, 500);

            }
        });


    }


    private class WebViewClientMaster extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("mailto:")) {
                //Handle mail Urls
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(url)));
            } else if (url.startsWith("tel:")) {
                //Handle telephony Urls
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            final Uri uri = request.getUrl();
            if (uri.toString().startsWith("mailto:")) {
                //Handle mail Urls
                startActivity(new Intent(Intent.ACTION_SENDTO, uri));
            } else if (uri.toString().startsWith("tel:")) {
                //Handle telephony Urls
                startActivity(new Intent(Intent.ACTION_DIAL, uri));
            } else {
                //Handle Web Urls
                view.loadUrl(uri.toString());
            }

            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            binding.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            binding.progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            binding.progressBar.setVisibility(View.GONE);
        }
    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_BACK:
//                    if (binding.webiew.canGoBack() == true) {
//                        binding.webiew.goBack();
//                    } else {
//                        getActivity().finish();
//                    }return true;}          }
//        return super.onKeyDown(keyCode, event);
//    }
}