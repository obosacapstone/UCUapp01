package edu.ucu.cite.jobportal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterAll extends RecyclerView.Adapter<RecyclerAdapterAll.ProductViewHolder> {
    private Context mCtx;
    private List<jobhiringlist> productList;
    String StringQualification;
    public RecyclerAdapterAll(Context mCtx, List<jobhiringlist> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public RecyclerAdapterAll.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclejobhiringall, null);

        return new RecyclerAdapterAll.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterAll.ProductViewHolder holder, int position) {

        jobhiringlist product = productList.get(position);
        StringQualification = product.getQualification();
        String splitted[] = StringQualification.split(",");
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
        LinearLayout btnjobhiring;

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
