package com.uniguides.userscampusapply.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uniguides.userscampusapply.R;

public class UniSelect extends AppCompatActivity {


    private ImageView imageView;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView capacityTextView;
    private TextView feeStructureTextView;
    private TextView coursesTextView;
    private Button OpenStats;

    public static final String UNIVERSITY_NAME = "university_name";
    public static final String UNIVERSITY_DESCRIPTION = "university_description";
    public static final String UNIVERSITY_IMAGE = "university_image";

    public static void start(Context context, String university, String description, int image) {
        Intent intent = new Intent(context, UniSelect.class);
        intent.putExtra("university", university);
        intent.putExtra("description", description);
        intent.putExtra("image", image);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_select);
        ImageView imageView = findViewById(R.id.imageView);
        TextView universityTextView = findViewById(R.id.universityTextView);
        TextView descriptionTextView = findViewById(R.id.descriptionTextView);
        Button OpenStats = findViewById(R.id.chooseButton);

        OpenStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UniSelect.this, StatisticsGraph.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        String university = intent.getStringExtra("university");
        String description = intent.getStringExtra("description");
        int image = intent.getIntExtra("image", 0);

        imageView.setImageResource(image);
        universityTextView.setText(university);
        descriptionTextView.setText(description);
    }
}