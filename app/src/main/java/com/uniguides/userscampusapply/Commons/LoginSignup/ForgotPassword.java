package com.uniguides.userscampusapply.Commons.LoginSignup;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniguides.userscampusapply.R;

public class ForgotPassword extends AppCompatActivity {

    private EditText forgotName, email, newPassword;
    private Button submit;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        //Hooks

        forgotName = findViewById(R.id.forgotName);
        email = findViewById(R.id.email);
        newPassword = findViewById(R.id.newPassword);
        submit = findViewById(R.id.submit);

        //The database instance declaration

        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        //a listener for the  submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = forgotName.getText().toString().trim();
                final String userEmail = email.getText().toString().trim();
                final String userPassword = newPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)) {
                    Toast.makeText(ForgotPassword.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(name)) {
                            DataSnapshot userSnapshot = dataSnapshot.child(name);
                            String userEmailDB = userSnapshot.child("email").getValue(String.class);

                            if(userEmail.equals(userEmailDB)) {
                                // Update the user's password
                                mDatabase.child(name).child("password").setValue(userPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            //pop-up message
                                            Toast.makeText(ForgotPassword.this, "Password updated successfully.", Toast.LENGTH_SHORT).show();
                                            finish(); // Close the activity
                                        }
                                        else {
                                            //pop-up message
                                            Toast.makeText(ForgotPassword.this, "Failed to update password.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else {
                                //pop-up message
                                Toast.makeText(ForgotPassword.this, "Incorrect email entered.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        //pop-up message
                        else {
                            Toast.makeText(ForgotPassword.this, "User not found.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("Firebase", "onCancelled: " + databaseError.getMessage());
                    }
                });
            }
        });
    }
}
