package com.example.cvbuilder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilePictureActivity extends AppCompatActivity {
    private ImageView ivSelectedImage;
    private Button btnSelectImage, btnConfirm;
    private Uri selectedImageUri;
    private final ActivityResultLauncher<String> getImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivSelectedImage.setImageURI(uri);
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_picture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

init();
        btnSelectImage.setOnClickListener(v -> {
            // Open gallery to pick an image
            getImageLauncher.launch("image/*");
        });

        btnConfirm.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                // Return the selected image URI as a string extra
                Intent resultIntent = new Intent();
                resultIntent.putExtra("dataKey", selectedImageUri.toString());
                setResult(RESULT_OK, resultIntent);
                finish();  // Close activity and return to home
            }
        });
    }

    private void init(){


        ivSelectedImage = findViewById(R.id.ivSelectedImage);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnConfirm = findViewById(R.id.btnConfirm);

    }
}