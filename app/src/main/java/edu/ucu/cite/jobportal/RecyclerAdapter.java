package edu.ucu.cite.jobportal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<jobhiringlist> productList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    String StringQualification,StringSkills;

    public RecyclerAdapter(Context mCtx, List<jobhiringlist> productList) {
        this.mCtx = mCtx;
        this.productList = productList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclejobhiring, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        jobhiringlist product = productList.get(position);
        StringQualification = product.getQualification();
        StringSkills = product.getSpecialization();






        holder.btnjobhiring.setVisibility(LinearLayout.GONE);
        String splitted[] = StringQualification.split(",");
        String splittedskill[] = StringSkills.split(", ");
        String skilluser = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getSpecialization();;
        String splitteduser[] = skilluser.split(", ");
        for(int i =0; i<splitteduser.length; i++){

            for(int x=0; x<splittedskill.length; x++){
                if (splittedskill[x].equals(splitteduser[i])){
                    holder.btnjobhiring.setVisibility(LinearLayout.VISIBLE);
                }


            }

        }



        holder.textViewJobTitle.setText(product.getJobTitle());
        holder.textViewCompanyName.setText(product.getCompanyName());
        holder.textViewJobType.setText(product.getJobType());
        holder.textViewLoocation.setText(product.getLocation());


        holder.textViewQualification.setText("PHP " + product.getMinimumSalary() + " - " + product.getMaximumSalary() + " Monthly");





        holder.btnjobhiring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx,jobhiringinfodata.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("jobtitle", product.getJobTitle());
                intent.putExtra("companyname", product.getCompanyName());
                intent.putExtra("email", product.getEmail());
                intent.putExtra("contact", product.getContact());
                intent.putExtra("startdate", product.getStartDate());
                intent.putExtra("enddate", product.getEndDate());
                intent.putExtra("jobtype", product.getJobType());
                intent.putExtra("location", product.getLocation());
                intent.putExtra("specialization", product.getSpecialization());
                intent.putExtra("link", product.getLink());
                intent.putExtra("qualification", product.getQualification());
                intent.putExtra("description", product.getDescription());
                intent.putExtra("jobstatus", product.getJobStatus());
                intent.putExtra("courseuploaded", product.getCourseUploaded());
                intent.putExtra("jobpostdate", product.getJobPostDate());
                intent.putExtra("minimumsalary", product.getMinimumSalary());
                intent.putExtra("maximumsalary", product.getMaximumSalary());
                intent.putExtra("views", product.getViews());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filterList(List<jobhiringlist> filteredList){

        productList = filteredList;

        notifyDataSetChanged();

    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewJobTitle,textViewCompanyName,textViewJobType,textViewLoocation,textViewQualification;
        CardView btnjobhiring;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewJobTitle= itemView.findViewById(R.id.jobtitle);
            textViewCompanyName = itemView.findViewById(R.id.companyname);
            textViewJobType = itemView.findViewById(R.id.jobtype);
            textViewLoocation = itemView.findViewById(R.id.location);


            textViewQualification = itemView.findViewById(R.id.qualification);
            btnjobhiring = itemView.findViewById(R.id.bgbutton);
        }
    }
}