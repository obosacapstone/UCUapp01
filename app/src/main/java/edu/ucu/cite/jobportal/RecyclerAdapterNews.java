package edu.ucu.cite.jobportal;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapterNews extends RecyclerView.Adapter<RecyclerAdapterNews.ProductViewHolder> {

    private Context mCtx;
    private List<newslist> newslists;
    String ImageLopping,ImageSplit,ImageOutput;
    public RecyclerAdapterNews(Context mCtx, List<newslist> newslists) {
        this.mCtx = mCtx;
        this.newslists = newslists;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclenews, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        newslist productnews = newslists.get(position);
        holder.textViewNewsDetail.setText(productnews.getNewsDetail());

        holder.textViewDescription.setText(productnews.getDescription());
        ImageLopping = productnews.getNewsImage();
        String splitted[] = ImageLopping.split(",,,");
        for(int i =1; i<splitted.length; i++){
            if (i == 1){
                Glide.with(mCtx).load(splitted[1]).into(holder.imageViewNewsImage);
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage1);
                holder.imageViewNewsImage1.setVisibility(View.VISIBLE);
                holder.imageViewNewsImage.setVisibility(View.VISIBLE);
            }
            if (i == 2){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage2);
                holder.imageViewNewsImage2.setVisibility(View.VISIBLE);
            }
            if (i == 3){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage3);
                holder.imageViewNewsImage3.setVisibility(View.VISIBLE);
            }
            if (i == 4){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage4);
                holder.imageViewNewsImage4.setVisibility(View.VISIBLE);
            }
            if (i == 5){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage5);
                holder.imageViewNewsImage5.setVisibility(View.VISIBLE);
            }
            if (i == 6){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage6);
                holder.imageViewNewsImage6.setVisibility(View.VISIBLE);
            }
            if (i == 7){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage7);
                holder.imageViewNewsImage7.setVisibility(View.VISIBLE);
            }
            if (i == 8){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage8);
                holder.imageViewNewsImage8.setVisibility(View.VISIBLE);
            }
            if (i == 9){
                Glide.with(mCtx).load(splitted[i]).into(holder.imageViewNewsImage9);
                holder.imageViewNewsImage9.setVisibility(View.VISIBLE);
            }

        }
        holder.imageViewNewsImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[1]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[2]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[3]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[4]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[5]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[6]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[7]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[8]).into(holder.imageViewNewsImage);
            }
        });
        holder.imageViewNewsImage9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Glide.with(mCtx).load(splitted[9]).into(holder.imageViewNewsImage);
            }
        });




    }

    @Override
    public int getItemCount() {
        return newslists.size();
    }

    public void filterList(List<newslist> filteredList){

        newslists = filteredList;
        notifyDataSetChanged();

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNewsDetail, textViewDescription;
        ImageView imageViewNewsImage,imageViewNewsImage0,imageViewNewsImage1,imageViewNewsImage2,imageViewNewsImage3,imageViewNewsImage4,imageViewNewsImage5,imageViewNewsImage6,imageViewNewsImage7,imageViewNewsImage8,imageViewNewsImage9,imageViewNewsImage10;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewNewsDetail= itemView.findViewById(R.id.newsdetail);
            textViewDescription = itemView.findViewById(R.id.description);
            imageViewNewsImage = itemView.findViewById(R.id.newsimage);
            imageViewNewsImage1= itemView.findViewById(R.id.newsimage1);
            imageViewNewsImage2 = itemView.findViewById(R.id.newsimage2);
            imageViewNewsImage3 = itemView.findViewById(R.id.newsimage3);
            imageViewNewsImage4 = itemView.findViewById(R.id.newsimage4);
            imageViewNewsImage5 = itemView.findViewById(R.id.newsimage5);
            imageViewNewsImage6 = itemView.findViewById(R.id.newsimage6);
            imageViewNewsImage7 = itemView.findViewById(R.id.newsimage7);
            imageViewNewsImage8 = itemView.findViewById(R.id.newsimage8);
            imageViewNewsImage9 = itemView.findViewById(R.id.newsimage9);





        }
    }
}