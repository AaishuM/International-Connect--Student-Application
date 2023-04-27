package com.uniguides.userscampusapply.User;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.uniguides.userscampusapply.R;

public class TrackProgress extends AppCompatActivity {
    private ImageView profilePicture;
    private TextView studentName;
    private TextView studentID;
    private TextView studentMajor;
    private TextView studentEmail;
    private TextView studentGPA;

    Button Refresh;

    private DatabaseReference databaseRef;

    String username;

    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_progress);

        // Initialize views
        profilePicture = findViewById(R.id.profile_picture);
        studentName = findViewById(R.id.student_name);
        studentID = findViewById(R.id.student_progress);
        studentMajor = findViewById(R.id.student_major);
        studentEmail = findViewById(R.id.student_email);
        studentGPA = findViewById(R.id.student_gpa);
        Refresh = findViewById(R.id.refresh);

         username = getIntent().getStringExtra("username");
        //studentName.setText(username);

        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load student profile data from database
                String msg = "processed.";
                Log.d(TAG, msg);
                Toast.makeText(TrackProgress.this, msg, Toast.LENGTH_SHORT).show();
                loadProfileData();


            }
        });


        //loadProfileData();

        // Set up click listener for profile picture
        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Allow user to select image from gallery
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });
    }

    private void loadProfileData() {

        //String username = getIntent().getStringExtra("username");
        // Initialize Firebase database reference
        databaseRef = FirebaseDatabase.getInstance().getReference("RegisteredUser");
        //Query checkUserDatabase = databaseRef.orderByChild("name").equalTo(username);
        // Retrieve data from Firebase database
        Toast.makeText(this, "Processing details for : " + username, Toast.LENGTH_SHORT).show();


        databaseRef.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot ) {
                // Get student profile data
                String name = dataSnapshot.child("fullName").getValue(String.class);
               // String home = dataSnapshot.child("id").getValue(String.class);
                String major = dataSnapshot.child("schoolCourse").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String gpa = dataSnapshot.child("schoolGPA").getValue(String.class);

                // Update text views with student profile data
                studentName.setText(name);
              //  studentID.setText("ID: " + id);
                studentMajor.setText(major);
                studentEmail.setText(email);
                studentGPA.setText(gpa);

                // Set profile picture based on uploaded image, or first letter of name if no image uploaded
                StorageReference storageRef = FirebaseStorage.getInstance().getReference();
                String imageRef = dataSnapshot.child("image").getValue(String.class);
                if (imageRef != null) {
                    storageRef.child(imageRef).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // Set profile picture using uploaded image
                            Glide.with(getApplicationContext()).load(uri).into(profilePicture);
                        }
                    });
                } else if (name != null && name.length() > 0) {
                    // Set profile picture using first letter of name
                    TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(name.charAt(0)).toUpperCase(), Color.BLUE);
                    profilePicture.setImageDrawable((android.graphics.drawable.Drawable) drawable);
                }
                 else {
                    String msg = "processing .....";
                    Log.d(TAG, msg);
                    Toast.makeText(TrackProgress.this, msg, Toast.LENGTH_SHORT).show();
                    // Handle case where name is null or empty
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Get selected image uri
            Uri selectedImageUri = data.getData();
            // Get file extension of selected image
            String fileExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(selectedImageUri));

            // Generate unique file name for uploaded image
            String imageFileName = "profile_image_" + System.currentTimeMillis() + "." + fileExtension;

            // Load student profile data from database
            loadProfileData();

            // Upload image to Firebase Storage
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(imageFileName);
            UploadTask uploadTask = storageRef.putFile(selectedImageUri);

            // Update database with image reference
            databaseRef.child("image").setValue(imageFileName);

            // Set profile picture using uploaded image
            Glide.with(getApplicationContext()).load(selectedImageUri).into(profilePicture);
        }
    }
}




