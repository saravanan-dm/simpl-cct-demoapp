package com.example.simpl_cct;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private static final AuthManager AUTH_MANAGER = new AuthManager();
    // Replace this url with Simpl redirection url
    private static final String REDIRECTION_URL = "https://abyssinian-branch-iberis.glitch.me/";

    private boolean isCustomTabOpened = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void redirect(View v) {
        isCustomTabOpened = true;
        AUTH_MANAGER.handleRedirect(this, REDIRECTION_URL);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isCustomTabOpened) {
            // @TODO: handle custom-tab manual close here. Will be called when user close the custom tab using 'X' button at the top left.
            // Here you may have to retry or load the url in normal Webview. Please keep in mind in this case, you won't get any redirection call back from Simpl
            isCustomTabOpened = false;
        }
    }

    /*
    If the redirection scheme is pointing to the current activity, Use 'onNewIntent' to get the response from Simpl redirection.
    Make sure 'android:launchMode="singleTop"' is set in AndroidManifest.xml file for this activity
    */

    /*
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // Make sure this is set of false, otherwise we won't be able to track the manual close onResume()
        isCustomTabOpened = false;

        Uri intentData = intent.getData();

        if (intentData.getQueryParameterNames().contains("token")) {
            // @TODO: handle success here
            System.out.println("Token = " + intentData.getQueryParameter("token"));
        } else {
            // @TODO: handle error here
            System.out.println("error = " + intentData.getQueryParameter("error"));
        }
    }
    */
}