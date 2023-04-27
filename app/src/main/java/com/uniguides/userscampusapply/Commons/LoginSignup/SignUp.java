package com.uniguides.userscampusapply.Commons.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uniguides.userscampusapply.HelperClasses.HelperMain;
import com.uniguides.userscampusapply.R;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    FirebaseDatabase database;

    DatabaseReference reference;
    EditText signupEmail, signupPassword, signUpUsername, signUpName;
    Button signupButton;
    ImageView loginRedirectArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set activity layout
        // Setting the layout file to be displayed when this activity is called
        setContentView(R.layout.activity_apply_sign_up);


        //Hooks
        signupEmail = findViewById(R.id.signUp_email);
        signupPassword = findViewById(R.id.signUp_Password);
        signUpName = findViewById(R.id.signUp_Name);
        signUpUsername = findViewById(R.id.signUp_Username);
        signupButton = findViewById(R.id.signUp_button);
        loginRedirectArrow = findViewById(R.id.login_back_button);

        // Set sign-up button listener
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString();
                String username = signUpUsername.getText().toString().trim();
                String name = signUpName.getText().toString();




                // Check if email is valid
                //if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                   // Toast.makeText(SignUp.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    //return;
                //}

// Define the email regular expression pattern
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// Check if the entered email matches the pattern
                if (email.matches(emailPattern)) {
                    // Email is valid
                    Toast.makeText(SignUp.this, " email address registered", Toast.LENGTH_SHORT).show();
                } else {
                    // Email is invalid
                    Toast.makeText(SignUp.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }

                // Check if email already exists
                reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(SignUp.this, "Email already exists", Toast.LENGTH_SHORT).show();
                        } else {
                            // Check if username already exists
                            reference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Toast.makeText(SignUp.this, "Username already exists", Toast.LENGTH_SHORT).show();
                                    } else {
                                        HelperMain helperMain = new HelperMain(name, email, password, username);
                                        reference.child(name).setValue(helperMain);
                                        Toast.makeText(SignUp.this, "You have signed Up successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUp.this, Login.class));
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });

        TextInputLayout passwordLayout = findViewById(R.id.password_layout);
        TextInputEditText passwordEditText = findViewById(R.id.signUp_Password);

        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        passwordEditText.setTransformationMethod(new PasswordTransformationMethod());

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();
                if (!PASSWORD_PATTERN.matcher(password).matches()) {
                    passwordLayout.setError("Password must contain at least 8 characters including 1 uppercase letter, one lowercase letter, one digit and special character");
                } else {
                    passwordLayout.setError(null);
                }
            }
        });
    }

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    );

    //Normal Functions
    public void callLoginScreen(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

}
