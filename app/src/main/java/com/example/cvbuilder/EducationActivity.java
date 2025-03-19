package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EducationActivity extends AppCompatActivity {

    private EditText etEducation;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_education);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEducation = findViewById(R.id.etEducation);
        btnSubmit = findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(v -> {
            // Get the user's education text
            String educationText = etEducation.getText().toString().trim();

            // Return the education text to the Home activity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("education", educationText);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}