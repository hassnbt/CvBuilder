package com.example.cvbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class home extends AppCompatActivity {


    public Uri profilePictureUri;

    private static final int REQUEST_CODE = 1; // Request code for returning data
    private TextView tvResult;
    public String personName, personEmail, personLinkedIn;
    public String summaryText;
    Button btnProfilePicture,btnpreviewscreen;
    Button btnPersonalDetails;
    Button btnSummary;
    Button btnEducation;
    Button btnExperience;
    Button btnCertifications;
    Button btnReferences;
    private final ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    // Check if the result is from ProfilePictureActivity (returns "dataKey").
                    if (data.hasExtra("dataKey") && data.getStringExtra("dataKey") != null &&
                            !data.getStringExtra("dataKey").isEmpty()) {
                        String returnedData = data.getStringExtra("dataKey");
                        // Save the image from the returned URI into internal storage.
                        Uri selectedUri = Uri.parse(returnedData);
                        try {
                            InputStream inputStream = getContentResolver().openInputStream(selectedUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            String fileName = "profile_picture.png";
                            File file = new File(getFilesDir(), fileName);
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                            fos.flush();
                            fos.close();
                            profilePictureUri = Uri.fromFile(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    // Check if the result is from PersonalDetailsActivity.
                    else if (data.hasExtra("name")) {
                        personName = data.getStringExtra("name");
                        personEmail = data.getStringExtra("email");
                        personLinkedIn = data.getStringExtra("linkedin");
                    }
                    // Check if the result is from SummaryActivity.
                    else if (data.hasExtra("summary")) {
                        summaryText = data.getStringExtra("summary");
                    }
                }
            });

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

        btnpreviewscreen.setOnClickListener(view -> {
            Intent intent = new Intent(home.this, PreviewScreen.class);
            // Pass the saved profile picture file URI.
            if (profilePictureUri != null) {
                intent.putExtra("dataKey", profilePictureUri.toString());
            } else {
                intent.putExtra("dataKey", "");
            }
            // Pass personal details if available.
            if (personName != null) {
                intent.putExtra("name", personName);
                intent.putExtra("email", personEmail);
                intent.putExtra("linkedin", personLinkedIn);
            }
            if (summaryText != null) {
                intent.putExtra("summary", summaryText);
            }
            startActivity(intent);
        });


    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(home.this, activityClass);
       activityResultLauncher.launch(intent);
    }
    private void init()
    {

        //tvResult = findViewById(R.id.tvResult); // Optional TextView to display results

        // Find buttons by ID
          btnProfilePicture = findViewById(R.id.btnProfilePicture);
          btnPersonalDetails = findViewById(R.id.btnPersonalDetails);
          btnSummary = findViewById(R.id.btnSummary);
          btnEducation = findViewById(R.id.btnEducation);
          btnExperience = findViewById(R.id.btnExperience);
          btnCertifications = findViewById(R.id.btnCertifications);
          btnReferences = findViewById(R.id.btnReferences);
btnpreviewscreen=findViewById(R.id.btnPreview);


    }
}