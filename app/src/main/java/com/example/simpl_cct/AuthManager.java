package com.example.simpl_cct;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;

import java.util.List;

public class AuthManager {

    public AuthManager(){ }

    private static final String SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
    private static final String CHROME_PACKAGE = "com.android.chrome";

    /*
    'true'  =>  Chrome supports Custom-tab.
    'false' =>  User has either disabled Chrome browser in the settings or no custom tab support.
     */
    private static boolean isChromeCustomTabsSupported(@NonNull final Context context) {
        Intent serviceIntent = new Intent(SERVICE_ACTION);
        serviceIntent.setPackage(CHROME_PACKAGE);
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(serviceIntent, 0);
        return !(resolveInfos == null || resolveInfos.isEmpty());
    }

    public void handleRedirect(Context context, String url) {
        if (isChromeCustomTabsSupported(context)) {
            CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder().build();
            customTabsIntent.intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            customTabsIntent.intent.setPackage(CHROME_PACKAGE);
            customTabsIntent.launchUrl(context, Uri.parse(url));
        } else {
            // @TODO: handle no custom tab support here. You may have to load the url in the normal Webview.
            System.out.println("Chrome is either disabled or not supported Custom-tab");
        }
    }
}
