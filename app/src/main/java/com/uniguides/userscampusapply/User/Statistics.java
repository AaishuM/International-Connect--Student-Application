package com.uniguides.userscampusapply.User;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uniguides.userscampusapply.HelperClasses.BarModel;
import com.uniguides.userscampusapply.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Statistics extends AppCompatActivity {

    private TextView acceptedCountText, lastYearCountText;
    private TextView averageGpaText;
    private TextView averageApplicationTimeText;
    private BarChart countryCountsChart;
    private BarChart lastYearCountryCountsChart;
    private DatabaseReference statsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        // Initialize UI elements
        acceptedCountText = findViewById(R.id.accepted_count_text);
        averageGpaText = findViewById(R.id.average_gpa_text);
        averageApplicationTimeText = findViewById(R.id.average_application_time_text);
        countryCountsChart = findViewById(R.id.country_counts_chart);
        lastYearCountryCountsChart = findViewById(R.id.last_year_country_counts_chart);
        lastYearCountText = findViewById(R.id.last_year_count_text);


        // Initialize Firebase Realtime Database reference
        statsRef = FirebaseDatabase.getInstance().getReference().child("statistics");

        // Set up event listeners for updating UI when data changes
        statsRef.child("university1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                updateStatsUI(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        // Set up event listener for updating country counts chart
        statsRef.child("university1").child("countryCounts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                updateCountryCountsChart(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                updateCountryCountsChart(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle removal
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                // Handle move
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        // Set up event listener for updating last year country counts chart
        statsRef.child("university1").child("lastYearCountryCounts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                updateLastYearCountryCountsChart(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) {
                updateLastYearCountryCountsChart(dataSnapshot.getKey(), dataSnapshot.getValue(Integer.class));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle removal
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) {
                // Handle move
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
        // Calculate and display average GPA
        Query gpaQuery = statsRef.child("university1").child("gpas").orderByKey();
        gpaQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                double sum = 7.0;
                int count = 5;
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    sum += childSnapshot.getValue(Double.class);
                    count++;
                }
                double average = sum / count;
                DecimalFormat df = new DecimalFormat("#.##");
                averageGpaText.setText("Average GPA: " + df.format(average));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        // Calculate and display average application time frame
        Query appTimeQuery = statsRef.child("university1").child("applicationTimes").orderByKey();
        appTimeQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long sum = 30;
                int count = 5;
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    sum += childSnapshot.getValue(Long.class);
                    count++;
                }
                long average = sum / count;
                int days = (int) (average / (1000 * 60 * 60 * 24));
                averageApplicationTimeText.setText("Average Application Time Frame: " + days + " days");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        BarModel barModel = new BarModel("Label", 5.0f);
        BarEntry entry = new BarEntry(0, barModel.getValue());
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 5)); // x = 0, y = 5
        entries.add(new BarEntry(1, 10)); // x = 1, y = 10
        entries.add(new BarEntry(2, 3)); // x = 2, y = 3
        BarDataSet dataSet = new BarDataSet(entries, "Label");


    }

    // Update UI with accepted count and last year count
    private void updateStatsUI(DataSnapshot dataSnapshot) {
        long acceptedCount =  50;//dataSnapshot.child("acceptedCount").getValue(Long.class);
        long lastYearCount =  24;//dataSnapshot.child("lastYearCount").getValue(Long.class);
        acceptedCountText.setText("Accepted Count: " + acceptedCount);
        lastYearCountText.setText("Last Year Count: " + lastYearCount);
    }

    // Update country counts bar chart




    private void updateCountryCountsChart(String country, int count) {
        BarModel barModel = new BarModel(country, count);
        BarEntry entry = new BarEntry(0, barModel.getValue());

        List<BarEntry> entries = new ArrayList<>();
        entries.add(entry);

        BarDataSet dataSet = new BarDataSet(entries, "Country Counts");
        BarData barData = new BarData(dataSet);
        countryCountsChart.setData(barData);
        countryCountsChart.invalidate();
    }

    private void updateLastYearCountryCountsChart(String country, int count) {
        BarModel barModel = new BarModel(country, count);
        BarEntry entry = new BarEntry(0, barModel.getValue());

        List<BarEntry> entries = new ArrayList<>();
        entries.add(entry);

        BarDataSet dataSet = new BarDataSet(entries, "Last Year Country Counts");
        BarData barData = new BarData(dataSet);
        lastYearCountryCountsChart.setData(barData);
        lastYearCountryCountsChart.invalidate();
    }






}
