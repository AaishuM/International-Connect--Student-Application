package com.uniguides.userscampusapply.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.uniguides.userscampusapply.R;

public class StudPayment extends AppCompatActivity {

    Button sendPayment;
    String message,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stud_payment);

       sendPayment = findViewById(R.id.payment_button);
       message = "Admission Payment Sent to Administration";
       title = "Applicant Payment";
        FirebaseMessaging.getInstance().subscribeToTopic("all");



        sendPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get token from database
                DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("admin");
                final String name = "Rajesh Kumar B";
                usersRef.child(name).child("myToken").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String userToken = dataSnapshot.getValue(String.class);
                        if (userToken == null) {
                            Toast.makeText(StudPayment.this, "Files Uploaded", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(StudPayment.this, UserDashboard.class);
                            startActivity(intent);
                            return;
                        } else {
                            Toast.makeText(StudPayment.this, "Uploading you files and Payment ........", Toast.LENGTH_LONG).show();
                            Toast.makeText(StudPayment.this, "Files Uploaded", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(StudPayment.this, UserDashboard.class);
                            startActivity(intent);
                        }
                        // use userToken for sending push notification
                       // if ( /*!title.getText().toString().isEmpty() && !message.getText().toString().isEmpty()&&!userToken.isEmpty()*/) {
                            FcmNotificationsSender notificationsSender = new FcmNotificationsSender(userToken, title, message, getApplicationContext(), StudPayment.this);
                            notificationsSender.SendNotifications();
                            Toast.makeText(StudPayment.this, "All Sent Successfully \uD83E\uDD13", Toast.LENGTH_LONG).show();

                       /* } else {
                            Toast.makeText(StudPayment.this, "Error!!!", Toast.LENGTH_LONG).show();
                        }*/
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle error
                        Toast.makeText(StudPayment.this, "Database error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



    }




}