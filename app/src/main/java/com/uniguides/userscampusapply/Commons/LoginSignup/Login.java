package com.uniguides.userscampusapply.Commons.LoginSignup;

import static com.google.firebase.messaging.Constants.MessageNotificationKeys.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.uniguides.userscampusapply.R;
import com.uniguides.userscampusapply.User.SessionManager;
import com.uniguides.userscampusapply.User.UserDashboard;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private SessionManager sessionManager;

    private RatingBar ratingBar;
    EditText loginUsername, loginPassword;
    Button loginButton;

    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_apply_login);

        //Hooks

        loginUsername = findViewById(R.id.login_Username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        createAccount = findViewById(R.id.signUp_redirect);
        ratingBar = findViewById(R.id.ratingBar);

        sessionManager = new SessionManager(this);
         // pass the username as a String extra
        //startActivity(intent);



        // handle user login and store auth token in session manager
        // Set the onRatingBarChangeListener for the ratingBar
       /* ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    // User has rated the app, enable the loginButton
                    loginButton.setEnabled(true);
                    // Hide the myRateText TextView
                    //ratingBar.setVisibility(View.GONE);
                    String msg = "Thank you for rating our app "; //getString(R.string.msg_token_fmt, userName);
                    Log.d(TAG, msg);
                    Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });*/


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }
                String msg = "Please rate the app before logging out";
                Log.d(TAG, msg);
                Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                checkUser();
            }
        });



        /*loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*float rating = ratingBar.getRating();
                if (rating <= 0) {
                    // The user has not rated the app, display a message
                    String msg = "Please rate the app before logging in";
                    Log.d(TAG, msg);
                    Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();*//*
                 if (//validateEmail() | //validatePassword()){

                } else {
                     String msg = "Please rate the app before logging out";
                     Log.d(TAG, msg);
                     Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                    checkUser();
                }
            }
        });*/

        // Check the initial rating and enable/disable the loginButton accordingly
       /* float rating = ratingBar.getRating();
        if (rating < 0) {
            String msg = "Please rate our app"; //getString(R.string.msg_token_fmt, userName);
            Log.d(TAG, msg);
            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
            loginButton.setEnabled(true);
        } else {
            String msg = "Please rate our app when logging out";
            Log.d(TAG, msg);
            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
            loginButton.setEnabled(true);
        }*/

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }


    private Boolean validateEmail() {
        String val = loginUsername.getText().toString().trim();
        if (val.isEmpty()) {
            loginUsername.setError("User email is required");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String pass = loginPassword.getText().toString().trim();
        if (pass.isEmpty()) {
            loginPassword.setError("User password is required");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    private void checkUser() {
        String userName = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("name").equalTo(userName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = childSnapshot.child("password").getValue(String.class);
                        if (Objects.equals(passwordFromDB, userPassword)) {
                            // Fetch the device token

                            Task<String> deviceToken = FirebaseMessaging.getInstance().getToken()
                                    .addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            if (!task.isSuccessful()) {
                                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                                return;
                                            }

                                            // Get new FCM registration token
                                            String token = task.getResult();

                                            String authToken = token;
                                            sessionManager.setAuthToken(authToken);

                                            // Log and toast
                                            String msg = getString(R.string.msg_token_fmt, userName);
                                            Log.d(TAG, msg);
                                            Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            // Save the device token to the user's record in the database
                            String userName = childSnapshot.getKey();
                            DatabaseReference userRef = reference.child(userName);
                            userRef.child("token").setValue(deviceToken);


                            loginUsername.setError(null);
                            loginPassword.setError(null);
                            Intent intent = new Intent(Login.this, UserDashboard.class);
                            intent.putExtra("username", userName); // pass the username as a String extra
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                    // If the loop completes without finding a matching password, show an error message
                    loginPassword.setError("Invalid Password");
                    loginPassword.requestFocus();
                } else {
                    loginUsername.setError("User does not exist");
                    loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });

    }

    public void ForgotPass(View view) {
        startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
    }
}
