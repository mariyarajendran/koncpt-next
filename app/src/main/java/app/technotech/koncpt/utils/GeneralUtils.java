package app.technotech.koncpt.utils;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import app.technotech.koncpt.R;
import app.technotech.koncpt.ui.fragments.user.RegisterFragment;
import dmax.dialog.SpotsDialog;

public class GeneralUtils {


    private Context context;
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";

    public GeneralUtils(Context context) {
        this.context = context;
    }


    public AppSharedPreference getAppSharedPreference() {
        AppSharedPreference sharedPreference = new AppSharedPreference(context);
        return sharedPreference;
    }

    //----------------------------------------------------------------------------------------------

    @SuppressLint("InflateParams")
    public static void openImageDialog(Context context, String url) { //String photoPath todo add this perams to method

        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_image_preview, null);

        ImageView imgClose = view.findViewById(R.id.img_close_image_dialog);
        imgClose.setOnClickListener(v -> dialog.dismiss());

        PhotoView dialog_img = view.findViewById(R.id.img_dialog);
        Glide.with(context)
                .load(url)
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                //.skipMemoryCache(false)
                .error(R.drawable.app_logo)
                .into(dialog_img);

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT)
        );
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();

    }


    @SuppressLint("InflateParams")
    public static void openImageDialog2(Context context, String url) { //String photoPath todo add this perams to method

        final Dialog dialog = new Dialog(context, R.style.DialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_image_preview, null);

        ImageView imgClose = view.findViewById(R.id.img_close_image_dialog);
        imgClose.setOnClickListener(v -> dialog.dismiss());

        PhotoView dialog_img = view.findViewById(R.id.img_dialog);
        Glide.with(context)
                .load(url)
                .into(dialog_img);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT)
        );
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();

    }


    public static void slideView(View view,
                                 int currentHeight,
                                 int newHeight) {

        ValueAnimator slideAnimator = ValueAnimator
                .ofInt(currentHeight, newHeight)
                .setDuration(500);

        /* We use an update listener which listens to each tick
         * and manually updates the height of the view  */

        slideAnimator.addUpdateListener(animation1 -> {
            Integer value = (Integer) animation1.getAnimatedValue();
            view.getLayoutParams().height = value.intValue();
            view.requestLayout();
        });

        /*  We use an animationSet to play the animation  */

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animationSet.play(slideAnimator);
        animationSet.start();
    }


    public AlertDialog showProgressDialog() {
        AlertDialog dialog = new SpotsDialog.Builder().setContext(context).build();
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        return dialog;
    }


    public static int getScreenWidth() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.heightPixels;
    }

    public static void showKeyboard(View view) {
        view.requestFocus();
        if (!isHardKeyboardAvailable(view)) {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    private static boolean isHardKeyboardAvailable(View view) {
        return view.getContext().getResources().getConfiguration().keyboard != Configuration.KEYBOARD_NOKEYS;
    }

    public static void hideKeyboard(Context context) {
        if (context instanceof Activity) {
            hideKeyboard((Activity) context);
        }
    }


    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus().getWindowToken() != null)
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomPassword() {
        Random random = new SecureRandom();
        String letters = "abcdefghjklmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ1234567890";
        String numbers = "1234567890";
        String specialChars = "!@#$%^&*_=+-/";
        String pw = "";
        for (int i = 0; i < 8; i++) {
            int index = (int) (random.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        int indexA = (int) (random.nextDouble() * numbers.length());
        pw += numbers.substring(indexA, indexA + 1);
        int indexB = (int) (random.nextDouble() * specialChars.length());
        pw += specialChars.substring(indexB, indexB + 1);
        return pw;
    }

    public static String generateRandomId() {
        Random random = new SecureRandom();
        String letters = "abcdefghjklmnopqrstuvwxyzABCDEFGHJKMNOPQRSTUVWXYZ1234567890";
        String numbers = "1234567890";
        String pw = "";
        for (int i = 0; i < 8; i++) {
            int index = (int) (random.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        int indexA = (int) (random.nextDouble() * numbers.length());
        pw += numbers.substring(indexA, indexA + 1);
        return pw;
    }

    public static void setAnimation(View viewToAnimate, int position, int animationId, Context context) {
        int lastPosition = -1;
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, animationId);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }


    public static void setSpinnerError(Spinner spinner, String errorString) {
        View selectedView = spinner.getSelectedView();
        if (selectedView != null && selectedView instanceof TextView) {
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setTextColor(Color.RED);
            selectedTextView.setText(errorString);
            spinner.performClick();
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }


    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return uniqueID;
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }


    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;

            return md5;
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5", e.getLocalizedMessage());
            return null;
        }
    }


//    public static void slideView(View view,
//                                 int currentHeight,
//                                 int newHeight) {
//
//        ValueAnimator slideAnimator = ValueAnimator
//                .ofInt(currentHeight, newHeight)
//                .setDuration(500);
//
//        /* We use an update listener which listens to each tick
//         * and manually updates the height of the view  */
//
//        slideAnimator.addUpdateListener(animation1 -> {
//            Integer value = (Integer) animation1.getAnimatedValue();
//            view.getLayoutParams().height = value.intValue();
//            view.requestLayout();
//        });
//
//        /*  We use an animationSet to play the animation  */
//
//        AnimatorSet animationSet = new AnimatorSet();
//        animationSet.setInterpolator(new AccelerateDecelerateInterpolator());
//        animationSet.play(slideAnimator);
//        animationSet.start();
//    }


    /**
     * Shows a 'no connection' dialog if the user is not online, or a general error if the user is online.
     *
     * @param context context
     * @param message additional message to show
     */
    public static void noConnection(final Activity context, FragmentManager manager, String message) {

        AlertDialog.Builder ab = new AlertDialog.Builder(context);

        String messageText = "";
        messageText = "\n\n" + message;

        ab.setMessage(context.getResources().getString(R.string.dialog_connection_description) + messageText);
        ab.setPositiveButton(context.getResources().getString(R.string.title_activity_woo_commerce_login), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = manager;
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        ab.setNegativeButton(context.getResources().getString(R.string.ok), null);
        ab.setTitle(context.getResources().getString(R.string.dialog_connection_title));

        if (!context.isFinishing()) {
            ab.show();
        }
    }


    public static String getMonthFromDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("MMM");
            return newDateFormat.format(convertedDate);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static String getDayFromDate(String date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd");
            return newDateFormat.format(convertedDate);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * Check if there are sufficient permissions to download a file and otherwise prompt
     *
     * @param context Context
     * @return True if permission is given
     */
    public static boolean hasPermissionToDownload(final Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED)
            return true;

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setMessage(R.string.download_permission_explaination);
        builder.setPositiveButton(R.string.download_permission_grant, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Fire off an async request to actually get the permission
                // This will show the standard permission request dialog UI
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    context.requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        });
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialog.show();

        return false;
    }

    public static int getRemainingDate(String strDate) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            Calendar calender_1 = Calendar.getInstance();
            Calendar calender_2 = Calendar.getInstance();

            Date date = simpleDateFormat.parse(strDate);
            String stringDate = simpleDateFormat.format(new Date());
            Date currentDate = simpleDateFormat.parse(stringDate);
            calender_1.setTime(date);
            calender_2.setTime(currentDate);
            long msDiff = calender_1.getTimeInMillis() - calender_2.getTimeInMillis();
            long daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff);
            String dayDifference = Long.toString(daysDiff);

            return Integer.parseInt(dayDifference);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return -1;
    }
}
