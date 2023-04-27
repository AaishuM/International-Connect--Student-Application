package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniguides.userscampusapply.R;

import java.util.ArrayList;
public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    ArrayList<CourseHelperClass> mostViewedUniversities;

    public CoursesAdapter(ArrayList<CourseHelperClass> mostViewedUniversities) {
        this.mostViewedUniversities = mostViewedUniversities;
    }

    @NonNull
    @Override
    public CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses_card_design, parent, false);
        CoursesViewHolder coursesViewHolder = new CoursesViewHolder(view);
        return coursesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesViewHolder holder, int position) {
        CourseHelperClass helperClass = mostViewedUniversities.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitle());
        holder.desc.setText(helperClass.getDescription());
        holder.rating.setRating(helperClass.getRating());
        if (holder.relativeLayout != null) {
            holder.relativeLayout.setBackground((helperClass.getGradient()));
        }
    }

    @Override
    public int getItemCount() {
        return mostViewedUniversities.size();
    }

    public static class CoursesViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView,desc;
        RatingBar rating;


        public CoursesViewHolder(@NonNull View itemView) {
            super(itemView);
           // relativeLayout = itemView.findViewById(R.id.background_image);
            imageView = itemView.findViewById(R.id.mv_image);
            textView = itemView.findViewById(R.id.mv_title);
            desc = itemView.findViewById(R.id.mv_desc);
            rating = itemView.findViewById(R.id.mv_rating);
        }
    }

}
