package edu.ucu.cite.jobportal;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapterCourse extends RecyclerView.Adapter<RecyclerAdapterCourse.ProductViewHolder> {

    private Context mCtx;
    private List<courselist> courselists;
    public RecyclerAdapterCourse(Context mCtx, List<courselist> courselists) {
        this.mCtx = mCtx;
        this.courselists = courselists;
    }

    @Override
    public RecyclerAdapterCourse.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclecourse, null);

        return new RecyclerAdapterCourse.ProductViewHolder(view);
    }
    private Context getContext() {
        return mCtx;
    }
    @Override
    public void onBindViewHolder(RecyclerAdapterCourse.ProductViewHolder holder, int position) {
        courselist productcourse = courselists.get(position);
        holder.textViewCourseTitle.setText(productcourse.getCourse());

        holder.btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx,alumni.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("course", productcourse.getCourse());
                mCtx.startActivity(intent);
                ((Activity) getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courselists.size();
    }

    public void filterList(List<courselist> filteredList){

        courselists = filteredList;
        notifyDataSetChanged();

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourseTitle;
        CardView btnevent;
        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewCourseTitle= itemView.findViewById(R.id.coursetitle);
            btnevent = itemView.findViewById(R.id.bgbutton);





        }
    }
}