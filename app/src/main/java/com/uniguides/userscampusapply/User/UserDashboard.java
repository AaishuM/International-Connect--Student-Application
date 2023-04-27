package com.uniguides.userscampusapply.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.uniguides.userscampusapply.Commons.LoginSignup.Login;
import com.uniguides.userscampusapply.Commons.LoginSignup.RegisterForm;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.CourseHelperClass;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.CoursesAdapter;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.FeaturedAdapter;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.FeaturedHelpedClass;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.MostViewedAdapter;
import com.uniguides.userscampusapply.HelperClasses.HelperAdapter.MostViewedHelperClass;
import com.uniguides.userscampusapply.R;
import com.uniguides.userscampusapply.StudentSupport;
import com.uniguides.userscampusapply.subjects.ArtMajor;
import com.uniguides.userscampusapply.subjects.EngineeringMajor;
import com.uniguides.userscampusapply.subjects.TechMajor;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private SessionManager sessionManager;

   String userName1;


    //Variables
    static final float END_SCALE = 0.7f;

    RecyclerView featuredRecycler, mostViewedRecycler, coursesRecycler;
    RecyclerView.Adapter adapter;
    private GradientDrawable gradient1, gradient2, gradient3, gradient4;
    ImageView menuIcon;

    RelativeLayout registerUni,notifyUser, uploadInter, payAdmi;

    LinearLayout contentView;

    //Drawer Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

         String userName = getIntent().getStringExtra("username");
         userName1 = getIntent().getStringExtra("username");

        //userName1 = getIntent().getStringExtra("username");


        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);
        coursesRecycler = findViewById(R.id.courses_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);
        registerUni = findViewById(R.id.registerCourse);
        notifyUser = findViewById(R.id.Notifications);
        payAdmi = findViewById(R.id.payadmission);
        uploadInter = findViewById(R.id.uploadInter);

        featuredRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this,RegisterForm.class);
                startActivity(intent);
            }
        });


        registerUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this,RegisterForm.class);
                startActivity(intent);

            }
        });

        notifyUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this,Notifiations.class);
                intent.putExtra("username", userName); // pass the username as a String extra
                startActivity(intent);
            }
        });

        uploadInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this, UploadFilesInter.class);
                startActivity(intent);
            }
        });

        payAdmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDashboard.this,StudPayment.class);
                startActivity(intent);
            }
        });




        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);


        navigationDrawer();

        //Recyler Views Function calls
        mostViewedRecycler();
        coursesRecycler();
        featuredRecycler();
        //return false;
        // Recycler Views Function calls
        // Set OnItemClickListener for FeaturedRecycler
        ((FeaturedAdapter) adapter).setOnItemClickListener(new FeaturedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ((FeaturedAdapter) adapter).setOnItemClickListener(new FeaturedAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        // Get the selected item
                       // FeaturedHelpedClass selectedUniversity = ((FeaturedAdapter) adapter).getItem(position);

                        // Create an intent to start the new activity
                        Intent intent = new Intent(UserDashboard.this, RegisterForm.class);

                        // Pass the selected item as an extra
                       // intent.putExtra("selectedUniversity", selectedUniversity);

                        // Start the new activity
                        startActivity(intent);
                    }
                });

                // Handle click event here
                Toast.makeText(UserDashboard.this, "Featured item clicked at position " + position, Toast.LENGTH_SHORT).show();
            }
        });
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //String userName1 = getIntent().getStringExtra("username");
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_home:
                // Perform action for nav_home item click here
                Toast.makeText(this, "Opening... ", Toast.LENGTH_SHORT).show();
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(UserDashboard.this, AboutUs.class);
                startActivity(intent);
                return true;
                // Handle Home menu item
            case R.id.nav_search:
                intent = new Intent(UserDashboard.this, StudentSupport.class);
                startActivity(intent);
                // Handle Search menu item
                return true;
            case R.id.nav_all_categories:
                Toast.makeText(this, "Checking University Statistics ...  ", Toast.LENGTH_SHORT).show();
                //drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(UserDashboard.this, UniStats.class);
                startActivity(intent);
                return true;
            case R.id.nav_login:
                Toast.makeText(this, "Checking for any Updates", Toast.LENGTH_SHORT).show();
                intent = new Intent(UserDashboard.this, FInanceDoc.class);
                startActivity(intent);
                // Handle Login menu item
                return true;
            case R.id.nav_profile:
                Toast.makeText(this, "Lets See You Application Progress ", Toast.LENGTH_SHORT).show();
                intent = new Intent(UserDashboard.this, TrackProgress.class);
                intent.putExtra("username", userName1); // pass the username as a String extra
                startActivity(intent);
                // Handle Login menu it
                // Handle Profile menu item
                return true;
            case R.id.nav_logout:
                intent = new Intent(UserDashboard.this, Login.class);
                startActivity(intent);
                // Handle Logout menu item
                return true;
            case R.id.nav_sciences:
                intent = new Intent(UserDashboard.this, TechMajor.class);
                startActivity(intent);
                // Handle Sciences menu item
                return true;
            case R.id.nav_technicals:
                intent = new Intent(UserDashboard.this, EngineeringMajor.class);
                startActivity(intent);
                Toast.makeText(this, "World Of Engineers ", Toast.LENGTH_SHORT).show();
                // Handle Technicals menu item
                return true;
            case R.id.nav_Business:
                intent = new Intent(UserDashboard.this, ArtMajor.class);
                startActivity(intent);
                Toast.makeText(this, "Arts and Music ", Toast.LENGTH_SHORT).show();
                // Handle Business menu item
                return true;
            default:
                return false;
        }
    }

    //Navigation Drawer Function
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerVisible(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        animateNavigationDrawer();
    }


    public void OpenRegForm(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterForm.class));
    }

    private void animateNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //Sale the view based on current slide effect
                final float diffScaledOffset = slideOffset*(1-END_SCALE);
                final float offsetScale = 1-diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                //Translate the view,accounting for the scaled width
                final float xOffset = drawerView.getWidth()* slideOffset;
                final float xoffsetDiff = contentView.getWidth()* diffScaledOffset/2;
                final float xTranslation = xOffset - xoffsetDiff;
                contentView.setTranslationX(xTranslation);
                //super.onDrawerSlide(drawerView, slideOffset);
            }
        });
    }


    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerVisible(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else
        super.onBackPressed();
    }
      //Recyler view functions
    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelpedClass> featuredUniversities = new ArrayList<>();


        featuredUniversities.add(new FeaturedHelpedClass(R.drawable.university1,"One Great University for International students","Offering Full Tuition Coverage for Students"));
        featuredUniversities.add(new FeaturedHelpedClass(R.drawable.university2,"Another Destination University for International students","Offering Partial and Grand Award Scholarships Tuition Coverage"));
        featuredUniversities.add(new FeaturedHelpedClass(R.drawable.university3,"A Welcoming Institution of learning for International students","Offering Grand Award Scholarships on Merit"));

        adapter = new FeaturedAdapter(featuredUniversities);
        featuredRecycler.setAdapter(adapter);
        ((FeaturedAdapter) adapter).setOnItemClickListener(new FeaturedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Handle click event here
                switch (position) {
                    case 1:
                    case 2:
                    case 3:
                        Intent intent1 = new Intent(UserDashboard.this, RegisterForm.class);
                        startActivity(intent1);
                        break;
                    default:
                        break;
                }
            }
        });



     //radientDrawable dientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,new int[]{0xffeff400,0xffaff600});
    }
    private void coursesRecycler() {
        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});
        ArrayList<CourseHelperClass> CourseHelperClass = new ArrayList<>();
        CourseHelperClass.add(new CourseHelperClass( R.drawable.sponsor,"Unisoft", 4.5F,"All in",gradient1));
        CourseHelperClass.add(new CourseHelperClass( R.drawable.sponsor,"Unisoft",3.5F,"All in",gradient1));
        CourseHelperClass.add(new CourseHelperClass( R.drawable.sponsor,"Unisoft",4.0F,"All in",gradient1));
        CourseHelperClass.add(new CourseHelperClass( R.drawable.sponsor,"Unisoft",4.5F,"All in",gradient1));
        coursesRecycler.setHasFixedSize(true);
        adapter = new CoursesAdapter(CourseHelperClass);
        coursesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        coursesRecycler.setAdapter(adapter);
    }
    private void mostViewedRecycler() {
        mostViewedRecycler.setHasFixedSize(true);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<MostViewedHelperClass> mostViewedUniversities = new ArrayList<>();
        mostViewedUniversities.add(new MostViewedHelperClass(R.drawable.university1, "McDonald's","Its well"));
        mostViewedUniversities.add(new MostViewedHelperClass(R.drawable.university2, "Edenrobe","Its well"));
        mostViewedUniversities.add(new MostViewedHelperClass(R.drawable.university3, "J.","Fine Bro"));
        mostViewedUniversities.add(new MostViewedHelperClass(R.drawable.university1, "Walmart","Fine Ladies"));
        adapter = new MostViewedAdapter(mostViewedUniversities);
        mostViewedRecycler.setAdapter(adapter);
    }

}
