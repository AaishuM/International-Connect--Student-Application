
package com.uniguides.userscampusapply.Commons.LoginSignup;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.myadapter;
import com.uniguides.userscampusapply.HelperClasses.SlideAdapter;
import com.uniguides.userscampusapply.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterForm2 extends AppCompatActivity {

    //variables

    FirebaseDatabase database;

    DatabaseReference reference;

    EditText Reg2Email, Reg2Phone, Reg2Address, Reg2UserName;

    Button chooseButton, uploadButton, RegSubmit;

    StorageReference mStorageRef;
    private static final int PICK_FILES_REQUEST_CODE = 1;
    private List<Uri> mSelectedFiles = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form2);


        // Assigning values to the class variables
        RegSubmit = findViewById(R.id.RegSubmission);
        mStorageRef = FirebaseStorage.getInstance().getReference();

        // Setting onClickListener for submit button
        RegSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(RegisterForm2.this, RealUpload.class));

            }
        });






        // Assigning values to class variables for Firebase Database and Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();
        reference = FirebaseDatabase.getInstance().getReference("pdfUploads");


        // Setting onClickListener for upload button
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFiles();
            }
        });
    }



    // Method to select files
    private void selectFiles() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        String[] mimeTypes = {"application/pdf"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        startActivityForResult(Intent.createChooser(intent, "Select PDF files"), 1);
    }





    // Method to handle result of selected files
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            List<Uri> uriList = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                // User selected multiple files
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    uriList.add(clipData.getItemAt(i).getUri());
                }
            } else {
                // User selected a single file
                uriList.add(data.getData());
            }
            UploadFiles(uriList);
        }
    }

    // Method to upload selected files
    private void UploadFiles(List<Uri> dataList) {
        // Create a progress dialog to show while files are being uploaded
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading Files....");
        progressDialog.show();

        // Loop through the list of Uri objects and upload each file separately
        for (int i = 0; i < dataList.size(); i++) {
            Uri data = dataList.get(i);
            String fileName = System.currentTimeMillis() + ".pdf";
            StorageReference references = mStorageRef.child("pdfUploads/" + fileName);
            references.putFile(data)
                    .addOnSuccessListener(taskSnapshot -> {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri url = uriTask.getResult();

                        myadapter myadapter = new myadapter(url.toString());
                        String username = Reg2UserName.getText().toString();

                        // Create a new child node with the file name and username
                        SlideAdapter slideAdapter = new SlideAdapter(username);
                        DatabaseReference newRef = reference.child(slideAdapter.getUsername());
                        newRef.setValue(myadapter);


                        Toast.makeText(RegisterForm2.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    })
                    .addOnProgressListener(snapshot -> {
                        double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded: " + (int) progress + "%");
                    });
        }
    }

}




