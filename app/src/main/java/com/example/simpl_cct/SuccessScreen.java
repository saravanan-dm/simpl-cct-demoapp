package com.example.simpl_cct;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

public class SuccessScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri intentData = getIntent().getData();

        if (intentData.getQueryParameterNames().contains("token")) {
            // @TODO: handle success here
            System.out.println("Token = " + intentData.getQueryParameter("token"));
            showSuccessScreen();
        } else {
            // @TODO: handle error here
            System.out.println("error = " + intentData.getQueryParameter("error"));
        }
    }

    private void showSuccessScreen() {
        // on below line we are configuring our window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.success);
        ((ImageView) findViewById(R.id.idIVLogo)).setImageResource(R.drawable.success);
    }
}