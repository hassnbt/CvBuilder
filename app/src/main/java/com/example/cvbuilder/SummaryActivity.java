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

public class SummaryActivity extends AppCompatActivity {
    private EditText etSummary;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        btnSubmit.setOnClickListener(v -> {
            String summaryText = etSummary.getText().toString().trim();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("summary", summaryText);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
    private void init(){

        etSummary = findViewById(R.id.etSummary);
        btnSubmit = findViewById(R.id.btnSubmitsumaary);
    }
}