package com.example.cvbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PreviewScreen extends AppCompatActivity {
    private ImageView ivPreviewProfile;
    private TextView tvName, tvEmail, tvLinkedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preview_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
init();
        Intent intent = getIntent();
        String uriString = intent.getStringExtra("dataKey");
        if (uriString != null && !uriString.isEmpty()) {
            try {
                Uri fileUri = Uri.parse(uriString);
                // Open an InputStream for the file URI and decode it into a Bitmap.
                InputStream inputStream = getContentResolver().openInputStream(fileUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                if (inputStream != null) {
                    inputStream.close();
                }
                // Set the Bitmap to the ImageView.
                ivPreviewProfile.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
     
            }
        }


        tvName.setText("John Doe");
        tvEmail.setText("john.doe@example.com");
        tvLinkedIn.setText("LinkedIn: linkedin.com/in/johndoe");

    }
    private void init(){




        ivPreviewProfile = findViewById(R.id.ivPreviewProfile1);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvLinkedIn = findViewById(R.id.tvLinkedIn);


//        tvName.setText("John Doe");
//        tvEmail.setText("john.doe@example.com");
//        tvLinkedIn.setText("LinkedIn: linkedin.com/in/johndoe");
    }
}