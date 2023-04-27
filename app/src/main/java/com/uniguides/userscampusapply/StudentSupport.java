package com.uniguides.userscampusapply;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniguides.userscampusapply.User.Complain;
import com.uniguides.userscampusapply.User.UserDashboard;

public class StudentSupport extends AppCompatActivity {

    private EditText mQueryEditText;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_support);

        mQueryEditText = findViewById(R.id.query_edittext);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Complains");

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = mQueryEditText.getText().toString().trim();

                if (!TextUtils.isEmpty(query)) {
                    String id = mDatabase.push().getKey();

                    Complain complain = new Complain(id, query);
                    mDatabase.child(id).setValue(complain);
                    Intent intent = (new Intent(StudentSupport.this, UserDashboard.class));
                    startActivity(intent);

                    Toast.makeText(StudentSupport.this, "Complain submitted successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudentSupport.this, "Please enter your query or challenge", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}