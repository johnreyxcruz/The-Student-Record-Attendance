package com.johnreytamondong.recordattendance;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    TextView appname;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appname = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);

        appname.animate().translationY(-1800).setDuration(2700).setStartDelay(0);
        lottie.animate().translationY(2000).setDuration(2000).setStartDelay(2900);

        // Delay for 3 seconds and then start the LoginActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}
