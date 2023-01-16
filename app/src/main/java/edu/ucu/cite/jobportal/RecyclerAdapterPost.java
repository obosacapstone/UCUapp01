package edu.ucu.cite.jobportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapterPost extends RecyclerView.Adapter<RecyclerAdapterPost.ProductViewHolder> {

    private Context mCtx;
    private List<postlist> postlists;
    String ImageLopping, ImageSplit, ImageOutput;

    public RecyclerAdapterPost(Context mCtx, List<postlist> postlists) {
        this.mCtx = mCtx;
        this.postlists = postlists;
    }

    @Override
    public RecyclerAdapterPost.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclepost, null);

        return new RecyclerAdapterPost.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterPost.ProductViewHolder holder, int position) {
        postlist productpost = postlists.get(position);
        holder.textViewPostTitle.setText(productpost.getTitle());

    }

    @Override
    public int getItemCount() {
        return postlists.size();
    }

    public void filterList(List<postlist> filteredList) {

        postlists = filteredList;
        notifyDataSetChanged();

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewPostTitle;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewPostTitle = itemView.findViewById(R.id.posttitle);


        }
    }
}