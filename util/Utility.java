package com.haris.ecommerce.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import com.haris.ecommerce.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.ColorInt;

public class Utility {

    /**
     * <p>Show the Toast in Activity</p>
     *
     * @param context context of activity or either Fragment
     * @param message your message you want to show in Toast
     * @param length  length of Toast
     */
    public static void Toaster(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }

    /**
     * <p>Show the Message in Logcat</p>
     *
     * @param tag     tag you want to use in your logger
     * @param message message you want to show in logcat
     */
    public static void Logger(String tag, String message) {
        ///do nothing , used in case of debugging app
        Log.e(tag, message);

    }

    public static void extraData(String tag, String message) {
        int maxLogSize = 2000;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;

            ///Log.e(tag, message.substring(start, end));

        }
    }

    /**
     * <p>Show the Message in Logcat</p>
     *
     * @param tag    tag you want to use in your logger
     * @param object message you want to show in logcat
     */
    public static void dumpObject(String tag, Object object) {
        ///do nothing , used in case of debugging app
        ///Log.e(tag, new Gson().toJson(object));

    }

    /**
     * <p>Share your app  with friend & Colleagues</p>
     *
     * @param context reference from the acitivty or fragment
     */
    public static void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out " + getStringFromRes(context, R.string.app_name) + " app at: https://play.google.com/store/apps/details?id=" + appPackageName);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    /**
     * <p>Share your app  with friend & Colleagues</p>
     *
     * @param context reference from the acitivty or fragment
     */
    public static void shareViaCopy(Context context, String link) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra(Intent.EXTRA_TEXT, link);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }

    /**
     * <p>Check the Connection status either it is available or not</p>
     *
     * @param context reference from activity or either fragment
     * @return true if internet connection available
     */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();

        if (activeNetworkInfo != null) { // connected to the internet
            // connected to the mobile provider's data plan
            return activeNetworkInfo.isConnected();
        }
        return false;
    }

    /**
     * <p>Check any specific text either it's null or not</p>
     *
     * @param text text about which we want to know about
     * @return true if text is Empty
     */
    public static boolean isEmptyString(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }

    /**
     * <p>It is used to open playstore app link for rating</p>
     *
     * @param context
     */
    public static void rateApp(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    /**
     * <p>It is used to Capitalize the Word first letter</p>
     *
     * @param capString
     * @return
     */
    public static String capitalize(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }

        return capMatcher.appendTail(capBuffer).toString();
    }

    /**
     * <p>It is used to get String values from Resource file</p>
     *
     * @param context
     * @param label
     * @return
     */
    public static String getStringFromRes(Context context, int label) {
        if (context == null || label == 0)
            return null;
        return context.getResources().getString(label);
    }


    /**
     * <p>It is used to get Color from Attribute</p>
     *
     * @param context
     * @param attr
     * @return
     */
    public static int getAttrColor(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(attr, typedValue, true);
        @ColorInt int color = typedValue.data;
        return color;
    }

}
