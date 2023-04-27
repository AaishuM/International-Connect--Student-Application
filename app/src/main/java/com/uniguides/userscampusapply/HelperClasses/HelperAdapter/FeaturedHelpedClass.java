package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

import android.view.View;

import java.io.Serializable;

public class FeaturedHelpedClass implements Serializable {
    int image;
    String title, description;

    private View.OnClickListener onClickListener;

    public FeaturedHelpedClass(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public View.OnClickListener getOnClickListener() {
            return onClickListener;
        }
    }



