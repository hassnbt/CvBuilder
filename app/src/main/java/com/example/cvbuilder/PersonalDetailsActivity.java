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

public class PersonalDetailsActivity extends AppCompatActivity {
    private EditText etName, etEmail, etLinkedIn;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personal_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


init();

        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String linkedin = etLinkedIn.getText().toString().trim();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", name);
            resultIntent.putExtra("email", email);
            resultIntent.putExtra("linkedin", linkedin);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    private void init()
    {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etLinkedIn = findViewById(R.id.etLinkedIn);
        btnSubmit = findViewById(R.id.btnSubmit);



    }
}