/*package com.uniguides.userscampusapply.User;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.NotiHelper;
import com.uniguides.userscampusapply.R;

import java.util.ArrayList;
import java.util.List;


public class Notifiations extends AppCompatActivity {

    ListView MyListView;
    DatabaseReference databaseReference;
    List<NotiHelper> ApprovedUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiations);

        MyListView = findViewById(R.id.myListView);

        ApprovedUsers = new ArrayList<>();

        retrieveApprovedUsers();
    }

    private void retrieveApprovedUsers() {
        databaseReference = FirebaseDatabase.getInstance().getReference("notifications");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // registeredUsers.clear(); // Clear the list before adding new users
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NotiHelper notiHelper = ds.getValue(NotiHelper.class);
                    ApprovedUsers.add(notiHelper);
                }
                displayApprovedUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void displayApprovedUsers() {
        String[] uploadName = new String[ApprovedUsers.size()];
        for (int i = 0; i < ApprovedUsers.size(); i++) {
            uploadName[i] = ApprovedUsers.get(i).getEmail() + "\n"
                    + "Admission Status: " + ApprovedUsers.get(i).getMessage() + "\n"
                    + "The Students Name: " + ApprovedUsers.get(i).getUsername();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, uploadName) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        };
        MyListView.setAdapter(adapter);
    }
}*/

/*package com.uniguides.userscampusapply.User;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.NotiHelper;
import com.uniguides.userscampusapply.R;

import java.util.ArrayList;
import java.util.List;

public class Notifiations extends AppCompatActivity {

    ListView MyListView;
    DatabaseReference databaseReference;
    List<NotiHelper> ApprovedUsers;
    String currentUserEmail; // add a field to store the logged-in user's email

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiations);

        mTextView = findViewById(R.id.notification_text);

        // Get the notification message from the intent
        String message = getIntent().getStringExtra("message");

        // Show the notification message to the user
        mTextView.setText(message);

        MyListView = findViewById(R.id.myListView);

        ApprovedUsers = new ArrayList<>();

        // set the current user's email here
        currentUserEmail = "example@domain.com";

        retrieveApprovedUsers();
    }

    private void retrieveApprovedUsers() {
        databaseReference = FirebaseDatabase.getInstance().getReference("notifications");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ApprovedUsers.clear(); // Clear the list before adding new users
                for (DataSnapshot ds : snapshot.getChildren()) {
                    NotiHelper notiHelper = ds.getValue(NotiHelper.class);
                    if (notiHelper.getEmail().equals(currentUserEmail)) { // only add notifications for the current user
                        ApprovedUsers.add(notiHelper);
                    }
                }
                displayApprovedUsers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void displayApprovedUsers() {
        String[] uploadName = new String[ApprovedUsers.size()];
        for (int i = 0; i < ApprovedUsers.size(); i++) {
            uploadName[i] = ApprovedUsers.get(i).getEmail() + "\n"
                    + "Admission Status: " + ApprovedUsers.get(i).getMessage() + "\n"
                    + "The Students Name: " + ApprovedUsers.get(i).getUsername();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, uploadName) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.BLACK);
                return view;
            }
        };
        MyListView.setAdapter(adapter);
    }
}*/
package com.uniguides.userscampusapply.User;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniguides.userscampusapply.R;

public class Notifiations extends AppCompatActivity {
    TextView messageTextView;
    DatabaseReference databaseRef; // Declare the database reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifiations);
        messageTextView = findViewById(R.id.notification_text);

        // get the latest message from the database
        String username1 = getIntent().getStringExtra("username");
        databaseRef = FirebaseDatabase.getInstance().getReference("users");
        //Query checkUserDatabase = databaseRef.orderByChild("name").equalTo(username1);



        databaseRef.child(username1).child("message").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        String message = messageSnapshot.child("content").getValue(String.class);
                        messageTextView.setText(message);
                    }
                } else {
                    messageTextView.setText("No new messages");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Notifiations.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
