
package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uniguides.userscampusapply.Commons.LoginSignup.RegisterForm;
import com.uniguides.userscampusapply.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    ArrayList<FeaturedHelpedClass> featuredUniversities;
    private OnItemClickListener listener;
    private Context context;

    public FeaturedAdapter(ArrayList<FeaturedHelpedClass> featuredUniversities) {
        this.featuredUniversities = featuredUniversities;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelpedClass featuredHelpedClass = featuredUniversities.get(position);
        holder.imageView.setImageResource(featuredHelpedClass.getImage());
        holder.textView.setText(featuredHelpedClass.getTitle());
        holder.textView2.setText(featuredHelpedClass.getDescription());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context != null) {
                    Intent intent = new Intent(context, RegisterForm.class);
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredUniversities.size();
    }

    public FeaturedHelpedClass getItem(int position) {
        return featuredUniversities.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, textView2;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            //Hooks
            imageView = itemView.findViewById(R.id.featured_image);
            textView = itemView.findViewById(R.id.featured_title);
            textView2 = itemView.findViewById(R.id.featured_desc);
        }
    }
}
