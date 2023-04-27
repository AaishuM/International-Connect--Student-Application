package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class CourseHelperClass {
    private int image;
    private String title;
    private String description;

    private float rating;
    private GradientDrawable gradient;


    public CourseHelperClass(int image, String title,float rating, String description,GradientDrawable gradient) {
        this.image = image;
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.gradient = gradient;
    }

    public int getImage() {
        return image;
    }

    public Drawable getGradient() {
        return gradient;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

}
