package com.massky.greenlandvland.module.splash;

import android.content.Intent;
import android.os.Bundle;


import com.massky.greenlandvland.module.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Created by chenxz on 2017/11/23.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
