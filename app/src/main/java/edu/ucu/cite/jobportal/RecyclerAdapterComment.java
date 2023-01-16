package edu.ucu.cite.jobportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterComment extends RecyclerView.Adapter<RecyclerAdapterComment.ProductViewHolder> {

    private Context mCtx;
    private List<commentlist> commentlists;
    String ImageLopping, ImageSplit, ImageOutput;

    public RecyclerAdapterComment(Context mCtx, List<commentlist> commentlists) {
        this.mCtx = mCtx;
        this.commentlists = commentlists;
    }

    @Override
    public RecyclerAdapterComment.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclecomment, null);

        return new RecyclerAdapterComment.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterComment.ProductViewHolder holder, int position) {
        commentlist productcomment = commentlists.get(position);
        holder.textViewComment.setText(productcomment.getComment());

    }

    @Override
    public int getItemCount() {
        return commentlists.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewComment;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewComment = itemView.findViewById(R.id.comment);


        }
    }
}