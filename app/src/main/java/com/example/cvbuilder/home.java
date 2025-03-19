package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity {
    private static final int REQUEST_CODE = 1; // Request code for returning data
    private TextView tvResult;
    Button btnProfilePicture;
    Button btnPersonalDetails;
    Button btnSummary;
    Button btnEducation;
    Button btnExperience;
    Button btnCertifications;
    Button btnReferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


init();


        btnProfilePicture.setOnClickListener(view -> openActivity(ProfilePictureActivity.class));
        btnPersonalDetails.setOnClickListener(view -> openActivity(PersonalDetailsActivity.class));
        btnSummary.setOnClickListener(view -> openActivity(SummaryActivity.class));
        btnEducation.setOnClickListener(view -> openActivity(EducationActivity.class));
        btnExperience.setOnClickListener(view -> openActivity(ExperienceActivity.class));
        btnCertifications.setOnClickListener(view -> openActivity(CertificationsActivity.class));
        btnReferences.setOnClickListener(view -> openActivity(ReferencesActivity.class));
    }
    private final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String returnedData = result.getData().getStringExtra("dataKey");
                    tvResult.setText("Received: " + returnedData);
                }
            });
    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(home.this, activityClass);
       activityResultLauncher.launch(intent);
    }
    private void init()
    {

        tvResult = findViewById(R.id.tvResult); // Optional TextView to display results

        // Find buttons by ID
          btnProfilePicture = findViewById(R.id.btnProfilePicture);
          btnPersonalDetails = findViewById(R.id.btnPersonalDetails);
          btnSummary = findViewById(R.id.btnSummary);
          btnEducation = findViewById(R.id.btnEducation);
          btnExperience = findViewById(R.id.btnExperience);
          btnCertifications = findViewById(R.id.btnCertifications);
          btnReferences = findViewById(R.id.btnReferences);



    }
}