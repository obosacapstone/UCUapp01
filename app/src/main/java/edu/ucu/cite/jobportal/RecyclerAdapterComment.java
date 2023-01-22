package edu.ucu.cite.jobportal;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerAdapterComment extends RecyclerView.Adapter<RecyclerAdapterComment.ProductViewHolder> {

    private Context mCtx;
    private List<commentlist> commentlists;
    String ImageLopping, ImageSplit, ImageOutput;
    String idno,idnocomment;
    String comment,commentid;
    String ConditionComment = "firstcondition";
    TextView TextViewDelete,TextViewCancel;
    Dialog mdialog;
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
        holder.textViewComment.setText(productcomment.getComment()
        );
        String Firstname = productcomment.getFirstname();
        String Middlename = productcomment.getMiddlename();
        String Lastname = productcomment.getLastname();
        holder.TextViewName.setText(Firstname + " " + Middlename + " " + Lastname);
        String ImageGraduated = productcomment.getImg();
        Glide.with(mCtx).load(ImageGraduated).into(holder.ImageViewProfile);



        //edit button
        holder.Auto_Complete_Setting.setVisibility(LinearLayout.GONE);
        holder.LinearLayoutMotherEditComment.setVisibility(LinearLayout.GONE);

        idnocomment=  productcomment.getIdno();
        idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();
        if (idnocomment.equals(idno)){
            holder.Auto_Complete_Setting.setVisibility(LinearLayout.VISIBLE);
        }

        //settings button
        holder.adapterItems = new ArrayAdapter<String>(mCtx,R.layout.list_item,holder.items);
        holder.Auto_Complete_Setting.setAdapter(holder.adapterItems);
        holder.Auto_Complete_Setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

                if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Edit")){
                    holder.Auto_Complete_Setting.setText("...");
                    holder.adapterItems = new ArrayAdapter<String>(mCtx,R.layout.list_item,holder.items);
                    holder.Auto_Complete_Setting.setAdapter(holder.adapterItems);
                    holder.textViewComment.setVisibility(LinearLayout.GONE);
                    holder.EditTextEditComment.setText(productcomment.getComment());
                    holder.LinearLayoutMotherEditComment.setVisibility(LinearLayout.VISIBLE);
                }
                if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Delete")){

                    mdialog = new Dialog(mCtx);
                    mdialog.setContentView(R.layout.popup_comment);
                    mdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mdialog.show();
                    holder.Auto_Complete_Setting.setText("...");
                    holder.adapterItems = new ArrayAdapter<String>(mCtx,R.layout.list_item,holder.items);
                    holder.Auto_Complete_Setting.setAdapter(holder.adapterItems);

                    TextViewCancel = mdialog.findViewById(R.id.cancel);
                    TextViewCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mdialog.hide();
                        }
                    });


                    TextViewDelete = mdialog.findViewById(R.id.delete);
                    TextViewDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String idcomment = productcomment.getIdcomment();
                            String idpost = productcomment.getIdpost();


                            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                    Constants.URL_DELETECOMMENT,

                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {


                                            try {
                                                JSONObject jsonObject = new JSONObject(response);

                                                Toast.makeText(mCtx, "Comment Deleted", Toast.LENGTH_SHORT).show();
                                                holder.CardViewbgbutton.setVisibility(LinearLayout.GONE);
                                                mdialog.hide();

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                                Log.e("anyText", response);
                                            }

                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }) {
                                @Nullable
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String, String> params = new HashMap<>();
                                    params.put("idcomment", idcomment);
                                    params.put("idpost", idpost);

                                    return params;

                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                            requestQueue.add(stringRequest);
                        }
                    });


                }






            }
        });

        //Cancel Button
        holder.TextViewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textViewComment.setVisibility(LinearLayout.VISIBLE);
                holder.EditTextEditComment.setText(productcomment.getComment());
                holder.LinearLayoutMotherEditComment.setVisibility(LinearLayout.GONE);

            }
        });

        //update comment
        holder.TextViewUpdateComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                comment  = String.valueOf(holder.EditTextEditComment.getText()).trim();
                commentid = productcomment.getIdcomment();

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_EDITCOMMENT,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    holder.textViewComment.setText(comment);
                                    holder.textViewComment.setVisibility(LinearLayout.VISIBLE);
                                    holder.EditTextEditComment.setText(productcomment.getComment());
                                    holder.LinearLayoutMotherEditComment.setVisibility(LinearLayout.GONE);
                                    Toast.makeText(mCtx, "Edit Comment Sucess", Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("anyText", response);
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("comment", comment);
                        params.put("commentid", commentid);


                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);
            }
        });


        //closing curly brace
    }

    @Override
    public int getItemCount() {
        return commentlists.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewComment,TextViewName,TextViewCancel,TextViewUpdateComment;
        ImageView ImageViewProfile;
        AutoCompleteTextView Auto_Complete_Setting;
        String[] items = {"Edit","Delete"};
        ArrayAdapter<String> adapterItems;
        EditText EditTextEditComment;
        LinearLayout LinearLayoutMotherEditComment;
        CardView CardViewbgbutton;
        public ProductViewHolder(View itemView) {
            super(itemView);

            CardViewbgbutton = itemView.findViewById(R.id.bgbutton);
            textViewComment = itemView.findViewById(R.id.comment);
            TextViewName = itemView.findViewById(R.id.name);
            ImageViewProfile = itemView.findViewById(R.id.graduatedimage);
            Auto_Complete_Setting = itemView.findViewById(R.id.auto_complete_setting);
            EditTextEditComment = itemView.findViewById(R.id.editcomment);
            LinearLayoutMotherEditComment = itemView.findViewById(R.id.mothereditcomment);
            TextViewCancel = itemView.findViewById(R.id.cancel);
            TextViewUpdateComment = itemView.findViewById(R.id.updatecomment);
        }
    }
}