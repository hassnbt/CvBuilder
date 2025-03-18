package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView logo = findViewById(R.id.splash_logo);

        // Load and start the fade-in animation
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeInAnimation);

        // Use a handler to delay the transition to MainActivity
        new Handler().postDelayed(() -> {
            // Start MainActivity after the delay
            Intent intent = new Intent(MainActivity.this, home.class);
            startActivity(intent);
            finish(); // Close the splash screen activity
        }, SPLASH_DURATION);
    }



}