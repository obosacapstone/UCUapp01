package edu.ucu.cite.jobportal;

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

        Glide.with(mCtx).load(productnews.getNewsImage()).into(holder.imageViewNewsImage);

//        String StringNewsDate = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(new Date(productnews.getNewsDate());
//
        String date = productnews.getNewsDate();
        SimpleDateFormat input = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
        try {
            Date oneWayTripDate = input.parse(date);                 // parse input
            holder.textViewNewsDate.setText(output.format(oneWayTripDate));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }




//
//        holder.textViewNewsDate.setText(productnews.getNewsDate());

        String StringNewsTopic = "<b> News Topic: </b>" + productnews.getNewsTopic();
        holder.textViewNewsTopic.setText(HtmlCompat.fromHtml(StringNewsTopic, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        String StringTopicCategory = "<b> Category: </b>" + productnews.getCategory();
        holder.textViewCategory.setText(HtmlCompat.fromHtml(StringTopicCategory, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        String StringAddress = "<b> Address: </b>" + productnews.getAddress();
        holder.textViewAddress.setText(HtmlCompat.fromHtml(StringAddress, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        String StringVenue = "<b> Venue: </b>" + productnews.getVenue();
        holder.textViewVenue.setText(HtmlCompat.fromHtml(StringVenue, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));








//        holder.btnnews.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mCtx,newsinfodata.class);
//                intent.putExtra("newsdetail", productnews.getNewsDetail());
//                intent.putExtra("newstopic", productnews.getNewsTopic());
//                intent.putExtra("newsimage", productnews.getNewsImage());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mCtx.startActivity(intent);
//            }
//        });

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

        TextView textViewNewsDetail, textViewVenue, textViewAddress, textViewDescription, textViewCategory, textViewNewsTopic, textViewNewsDate;
        LinearLayout btnnews;
        ImageView imageViewNewsImage;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewNewsDetail= itemView.findViewById(R.id.newsdetail);
            textViewVenue = itemView.findViewById(R.id.venue);
            textViewAddress = itemView.findViewById(R.id.address);
            textViewDescription = itemView.findViewById(R.id.description);
            imageViewNewsImage = itemView.findViewById(R.id.newsimage);
            textViewNewsDate = itemView.findViewById(R.id.newsdate);
            textViewCategory = itemView.findViewById(R.id.category);
            textViewNewsTopic = itemView.findViewById(R.id.newstopic);



//            btnnews = itemView.findViewById(R.id.bgbuttonnews);
        }
    }
}