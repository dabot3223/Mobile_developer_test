package com.mobiledevelopertest.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.mobiledevelopertest.R;
import com.mobiledevelopertest.utils.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {

    TextView bac;
    int ct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        bac = findViewById(R.id.bac);

        SharedPrefManager.getInstance(SplashActivity.this);
        ct = SharedPrefManager.read(SharedPrefManager.KEY_COUNTER,0);
        if (ct > 0){
            bac.setVisibility(View.VISIBLE);
        }
        ct+=1;

        SharedPrefManager.getInstance(SplashActivity.this).write(SharedPrefManager.KEY_COUNTER,ct);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, ListpageActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("ct",ct);
                startActivity(i);
                finish();
            }
        },2000);
    }
}