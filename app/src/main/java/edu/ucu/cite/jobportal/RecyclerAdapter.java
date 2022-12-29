package edu.ucu.cite.jobportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<jobhiringlist> productList;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    String StringQualification,StringSkills;
    ProgressDialog progressDialog;
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
        String Stringid = product.getId();

        holder.btnjobhiring.setVisibility(LinearLayout.GONE);
        String splitted[] = StringQualification.split(",");
        String splittedskill[] = StringSkills.split(", ");
        String skilluser = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getSpecialization();
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



        holder.ImageViewSave.setVisibility(LinearLayout.VISIBLE);
        holder.ImageViewSaved.setVisibility(LinearLayout.GONE);

        String bookmark = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getBookmark();;

        String splitbookmark[] = bookmark.split(", ");

        for(int i =0; i<splitbookmark.length; i++){

            if (splitbookmark[i].equals(Stringid)){
                holder.ImageViewSave.setVisibility(LinearLayout.GONE);
                holder.ImageViewSaved.setVisibility(LinearLayout.VISIBLE);
            }

        }




        holder.ImageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(mCtx);
                progressDialog.setMessage("Please wait....");
                String idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();;
                String id = product.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_JOBSAVE,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(), eventinfo.class));
//                            finish();
                                    SharedPrefManager.getInstance(mCtx.getApplicationContext())
                                            .userLogin(
                                                    jsonObject.getString("idno"),
                                                    jsonObject.getString("password"),
                                                    jsonObject.getString("firstname"),
                                                    jsonObject.getString("middlename"),
                                                    jsonObject.getString("lastname"),
                                                    jsonObject.getString("course"),
                                                    jsonObject.getString("college"),
                                                    jsonObject.getString("yeargrad"),
                                                    jsonObject.getString("gender"),
                                                    jsonObject.getString("birthdate"),
                                                    jsonObject.getString("civilstatus"),
                                                    jsonObject.getString("contact"),
                                                    jsonObject.getString("email"),
                                                    jsonObject.getString("specialization"),
                                                    jsonObject.getString("region"),
                                                    jsonObject.getString("province"),
                                                    jsonObject.getString("city"),
                                                    jsonObject.getString("barangay"),
                                                    jsonObject.getString("street"),
                                                    jsonObject.getString("facebook"),
                                                    jsonObject.getString("instagram"),
                                                    jsonObject.getString("bookmark"),
                                                    jsonObject.getString("graduatedimage"),
                                                    jsonObject.getString("notification"),
                                                    jsonObject.getString("newsnotification"),
                                                    jsonObject.getString("eventnotification"),
                                                    jsonObject.getString("postgraduate"),
                                                    jsonObject.getString("postgraduatey1"),
                                                    jsonObject.getString("postgraduatey2"),
                                                    jsonObject.getString("employed"),
                                                    jsonObject.getString("employedy1"),
                                                    jsonObject.getString("employedy2"),
                                                    jsonObject.getString("employedy3"),
                                                    jsonObject.getString("employedy4"),
                                                    jsonObject.getString("employedy5"),
                                                    jsonObject.getString("employedn1"),
                                                    jsonObject.getString("firstjob"),
                                                    jsonObject.getString("firstjoby1"),
                                                    jsonObject.getString("firstjoby2"),
                                                    jsonObject.getString("firstjoby3"),
                                                    jsonObject.getString("firstjoby4"),
                                                    jsonObject.getString("firstjoby4y1"),
                                                    jsonObject.getString("firstjoby5"),
                                                    jsonObject.getString("firstjoby6")



                                            );

                                    holder.ImageViewSave.setVisibility(LinearLayout.GONE);
                                    holder.ImageViewSaved.setVisibility(LinearLayout.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("anyText",response);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("idno",idno);
                        params.put("id",id);


                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);
            }
        });

        holder.ImageViewSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(mCtx);
                progressDialog.setMessage("Please wait....");
                String idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();;
                String id = product.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_JOBSAVED,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(), eventinfo.class));
//                            finish();
                                    SharedPrefManager.getInstance(mCtx.getApplicationContext())
                                            .userLogin(
                                                    jsonObject.getString("idno"),
                                                    jsonObject.getString("password"),
                                                    jsonObject.getString("firstname"),
                                                    jsonObject.getString("middlename"),
                                                    jsonObject.getString("lastname"),
                                                    jsonObject.getString("course"),
                                                    jsonObject.getString("college"),
                                                    jsonObject.getString("yeargrad"),
                                                    jsonObject.getString("gender"),
                                                    jsonObject.getString("birthdate"),
                                                    jsonObject.getString("civilstatus"),
                                                    jsonObject.getString("contact"),
                                                    jsonObject.getString("email"),
                                                    jsonObject.getString("specialization"),
                                                    jsonObject.getString("region"),
                                                    jsonObject.getString("province"),
                                                    jsonObject.getString("city"),
                                                    jsonObject.getString("barangay"),
                                                    jsonObject.getString("street"),
                                                    jsonObject.getString("facebook"),
                                                    jsonObject.getString("instagram"),
                                                    jsonObject.getString("bookmark"),
                                                    jsonObject.getString("graduatedimage"),
                                                    jsonObject.getString("notification"),
                                                    jsonObject.getString("newsnotification"),
                                                    jsonObject.getString("eventnotification"),
                                                    jsonObject.getString("postgraduate"),
                                                    jsonObject.getString("postgraduatey1"),
                                                    jsonObject.getString("postgraduatey2"),
                                                    jsonObject.getString("employed"),
                                                    jsonObject.getString("employedy1"),
                                                    jsonObject.getString("employedy2"),
                                                    jsonObject.getString("employedy3"),
                                                    jsonObject.getString("employedy4"),
                                                    jsonObject.getString("employedy5"),
                                                    jsonObject.getString("employedn1"),
                                                    jsonObject.getString("firstjob"),
                                                    jsonObject.getString("firstjoby1"),
                                                    jsonObject.getString("firstjoby2"),
                                                    jsonObject.getString("firstjoby3"),
                                                    jsonObject.getString("firstjoby4"),
                                                    jsonObject.getString("firstjoby4y1"),
                                                    jsonObject.getString("firstjoby5"),
                                                    jsonObject.getString("firstjoby6")



                                            );

                                    holder.ImageViewSave.setVisibility(LinearLayout.VISIBLE);
                                    holder.ImageViewSaved.setVisibility(LinearLayout.GONE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("anyText",response);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("idno",idno);
                        params.put("id",id);


                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);
            }
        });





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
        ImageView ImageViewSave,ImageViewSaved;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewJobTitle= itemView.findViewById(R.id.jobtitle);
            textViewCompanyName = itemView.findViewById(R.id.companyname);
            textViewJobType = itemView.findViewById(R.id.jobtype);
            textViewLoocation = itemView.findViewById(R.id.location);


            textViewQualification = itemView.findViewById(R.id.qualification);
            btnjobhiring = itemView.findViewById(R.id.bgbutton);
            ImageViewSave = itemView.findViewById(R.id.save);
            ImageViewSaved = itemView.findViewById(R.id.saved);
        }
    }
}