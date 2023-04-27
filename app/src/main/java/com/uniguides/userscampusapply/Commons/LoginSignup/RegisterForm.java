/*package com.uniguides.userscampusapply.Commons.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.RegHelper;
import com.uniguides.userscampusapply.R;

public class RegisterForm extends AppCompatActivity {

    FirebaseDatabase database;

    DatabaseReference reference;
    EditText regFullNames, RegDOB, RegCob, RegPob, RegCor, RegProv, RegEmail, RegCountry, RegContact, RegSchool, RegSLocation, RegSCourse, RegSGPA,
            RegCSchool, RegCLocation, RegCCourse, RegCGPA, RegUni, RegULocation, RegUCourse, RegUGPA;
    ImageView regForm2Call;
    ImageView loginRedirectArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        regForm2Call = findViewById(R.id.LoginTo_RegForm2_button);

        regForm2Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regFullNames = findViewById(R.id.regFullName);
                RegDOB = findViewById(R.id.RegDateOfBirth);
                RegCob = findViewById(R.id.RegCob);
                RegPob = findViewById(R.id.RegPob);
                RegCor = findViewById(R.id.RegCor);
                RegProv = findViewById(R.id.RegProvince);
                RegEmail = findViewById(R.id.RegEmail);
                RegCountry = findViewById(R.id.RegCntry);
                RegContact = findViewById(R.id.RegContact);
                RegSchool = findViewById(R.id.RegSchool2);
                RegSLocation = findViewById(R.id.RegLocation2);
                RegSCourse = findViewById(R.id.RegCourse2);
                RegSGPA = findViewById(R.id.RegGpa2);
                RegCSchool = findViewById(R.id.RegSchool3);
                RegCCourse = findViewById(R.id.RegCourse3);
                RegCLocation = findViewById(R.id.RegLocation3);
                RegCGPA = findViewById(R.id.RegGpa3);
                RegUni = findViewById(R.id.RegSchool4);
                RegUCourse = findViewById(R.id.RegCourse4);
                RegULocation = findViewById(R.id.RegLocation4);
                RegUGPA = findViewById(R.id.RegGpa4);

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("RegisteredUser");

                String fullName = regFullNames.getText().toString();
                String DateOfBirth = RegDOB.getText().toString();
                String CountryOfBirth = RegCob.getText().toString();
                String PlaceOfBirth = RegPob.getText().toString();
                String CountryOfResidence = RegCor.getText().toString();
                String Province = RegProv.getText().toString();
                String email = RegEmail.getText().toString();
                String country = RegCountry.getText().toString();
                String contact = RegContact.getText().toString();

                // Optional Fields
                String school = RegSchool.getText().toString().equals("") ? "N/A" : RegSchool.getText().toString();
                String schoolLocation = RegSLocation.getText().toString().equals("") ? "N/A" : RegSLocation.getText().toString();
                String schoolCourse = RegSCourse.getText().toString().equals("") ? "N/A" : RegSCourse.getText().toString();
                String schoolGPA = RegSGPA.getText().toString().equals("") ? "N/A" : RegSGPA.getText().toString();
                String college = RegCSchool.getText().toString().equals("") ? "N/A" : RegCSchool.getText().toString();
                String collegeLocation = RegCLocation.getText().toString().equals("") ? "N/A" : RegCLocation.getText().toString();
                String collegeCourse = RegCCourse.getText().toString().equals("") ? "N/A" : RegCCourse.getText().toString();
                String collegeGPA = RegCGPA.getText().toString().equals("") ? "N/A" : RegCGPA.getText().toString();
                String uni = RegUni.getText().toString().equals("") ? "N/A" : RegUni.getText().toString();
                String uniLocation = RegULocation.getText().toString().equals("") ? "N/A" : RegULocation.getText().toString();
                String uniCourse = RegUCourse.getText().toString().equals("") ? "N/A" : RegUCourse.getText().toString();
                String uniGPA = RegUGPA.getText().toString().equals("") ? "N/A" : RegUGPA.getText().toString();

                // Validation
                RegHelper regHelper = new RegHelper(fullName, DateOfBirth, CountryOfBirth, PlaceOfBirth, CountryOfResidence, Province, email, country, contact, school, schoolLocation, schoolCourse, schoolGPA, college, collegeLocation, collegeCourse, collegeGPA, uni, uniLocation, uniCourse, uniGPA);

                reference.child(email).setValue(regHelper);
                Toast.makeText(RegisterForm.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterForm.this, RegisterForm2.class);
                intent.putExtra("fullname", fullName);
                startActivity(intent);
                finish();

                //startActivity(new Intent(getApplicationContext(), RegisterForm2.class));
            }
        });

       /* loginRedirectArrow = findViewById(R.id.LoginRedirectArrow);

        loginRedirectArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });*/

package com.uniguides.userscampusapply.Commons.LoginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.RegHelper;
import com.uniguides.userscampusapply.R;

import java.util.regex.Pattern;

public class RegisterForm extends AppCompatActivity {

    // Declare necessary variables and objects
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText regFullNames, RegDOB, RegCob, RegPob, RegCor, RegProv, RegEmail, RegCountry, RegContact, RegSchool, RegSLocation, RegSCourse, RegSGPA,
            RegCSchool, RegCLocation, RegCCourse, RegCGPA, RegUSchool, RegULocation, RegUCourse, RegUGPA;
    ImageView regForm2Call;
    ImageView loginRedirectArrow;


    // Define the onCreate() method to create the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        // Initialize the regForm2Call ImageView and set a click listener
        regForm2Call = findViewById(R.id.LoginTo_RegForm2_button);

        regForm2Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize all EditText objects
                regFullNames = findViewById(R.id.regFullName);
                RegDOB = findViewById(R.id.RegDateOfBirth);
                RegDOB.addTextChangedListener(new TextWatcher() {
                    private final Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (!pattern.matcher(s.toString()).matches()) {
                            RegDOB.setError("Please enter a valid date in the format YYYY-MM-DD");
                        }
                    }
                });
                RegCob = findViewById(R.id.RegCob);
                RegPob = findViewById(R.id.RegPob);
                RegCor = findViewById(R.id.RegCor);
                RegProv = findViewById(R.id.RegProvince);
                RegEmail = findViewById(R.id.RegEmail);
                RegCountry = findViewById(R.id.RegCntry);
                RegContact = findViewById(R.id.RegContact);
                RegSchool = findViewById(R.id.RegSchool2);
                RegSLocation = findViewById(R.id.RegLocation2);
                RegSCourse = findViewById(R.id.RegCourse2);
                RegSGPA = findViewById(R.id.RegGpa2);
                RegCSchool = findViewById(R.id.RegSchool3);
                RegCCourse = findViewById(R.id.RegCourse3);
                RegCLocation = findViewById(R.id.RegLocation3);
                RegCGPA = findViewById(R.id.RegGpa3);
                RegUSchool = findViewById(R.id.RegSchool4);
                RegUCourse = findViewById(R.id.RegCourse4);
                RegULocation = findViewById(R.id.RegLocation4);
                RegUGPA = findViewById(R.id.RegGpa4);

                // Initialize Firebase objects
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("RegisteredUser");

                // Get text from all EditText objects
                String fullName = regFullNames.getText().toString();
                String DateOfBirth = RegDOB.getText().toString();
                String CountryOfBirth = RegCob.getText().toString();
                String PlaceOfBirth = RegPob.getText().toString();
                String CountryOfResidence = RegCor.getText().toString();
                String Province = RegProv.getText().toString();
                String email = RegEmail.getText().toString();
                String country = RegCountry.getText().toString();
                String contact = RegContact.getText().toString();
                String school = RegSchool.getText().toString();
                String schoolLocation = RegSLocation.getText().toString();
                String schoolCourse = RegSCourse.getText().toString();
                String schoolGPA = RegSGPA.getText().toString();
                String college = RegCSchool.getText().toString();
                String collegeLocation = RegCLocation.getText().toString();
                String collegeCourse = RegCCourse.getText().toString();
                String collegeGPA = RegCGPA.getText().toString();
                String Uni = RegUSchool.getText().toString();
                String UniLocation = RegULocation.getText().toString();
                String UniCourse = RegUCourse.getText().toString();
                String UniGPA = RegUGPA.getText().toString();

                if (fullName.equals("") || DateOfBirth.equals("") || CountryOfBirth.equals("") || PlaceOfBirth.equals("") || CountryOfResidence.equals("") || Province.equals("") || email.equals("") || country.equals("") || contact.equals("") ){//|| school.equals("") || schoolLocation.equals("") || schoolCourse.equals("") || schoolGPA.equals("") || college.equals("") || collegeLocation.equals("") || collegeCourse.equals("") || collegeGPA.equals("") || Uni.equals("") || UniLocation.equals("") || UniCourse.equals("") || UniGPA.equals("")) {

                    Toast.makeText(RegisterForm.this, "Please fill up all the fields", Toast.LENGTH_SHORT).show();

                } else {

                    RegHelper regHelper = new RegHelper(fullName, DateOfBirth, CountryOfBirth, PlaceOfBirth, CountryOfResidence, Province, email, country, contact, school, schoolLocation, schoolCourse, schoolGPA, college, collegeLocation, collegeCourse, collegeGPA, Uni, UniLocation, UniCourse, UniGPA);

                    reference.child(fullName).setValue(regHelper);

                    Intent intent = new Intent(RegisterForm.this, RealUpload.class);
                   // intent.putExtra("fullname", fullName);
                    startActivity(intent);
                    finish();
                }
            }
        });


    }

}

