package edu.ucu.cite.jobportal;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
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
import java.util.concurrent.locks.Condition;

public class RecyclerAdapterTrending extends RecyclerView.Adapter<RecyclerAdapterTrending.ProductViewHolder> {

    private Context mCtx;
    private List<trendinglist> trendinglists;
    String ImageLopping, ImageSplit, ImageOutput;
    int i = 0;
    int TotalCount;
    String splitreactcount[];
    String condition;
    String idno,idnopost;
    Dialog mdialog;
    TextView TextViewDelete,TextViewCancel;
    public RecyclerAdapterTrending(Context mCtx, List<trendinglist> trendinglists) {
        this.mCtx = mCtx;
        this.trendinglists = trendinglists;
    }

    @Override
    public RecyclerAdapterTrending.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycletrending, null);

        return new RecyclerAdapterTrending.ProductViewHolder(view);
    }
    private Context getContext() {
        return mCtx;
    }
    @Override
    public void onBindViewHolder(RecyclerAdapterTrending.ProductViewHolder holder, int position) {
        trendinglist producttrending = trendinglists.get(position);



        holder.textViewTrendingPost.setText(producttrending.getPost());
        String Firstname = producttrending.getFirstname();
        String Middlename = producttrending.getMiddlename();
        String Lastname = producttrending.getLastname();
        holder.TextViewName.setText(Firstname + " " + Middlename + " " + Lastname);
        String ImageGraduated = producttrending.getImg();
        Glide.with(mCtx).load(ImageGraduated).into(holder.ImageViewProfile);
        String idpost = producttrending.getId();
        holder.TextViewCommentCount.setText(producttrending.getCommentcount());
//        holder.LinearLayoutDelete.setVisibility(LinearLayout.GONE);
        //like or liked
        holder.LinearLayoutLike.setVisibility(LinearLayout.VISIBLE);
        holder.LinearLayoutLiked.setVisibility(LinearLayout.GONE);

        String reactcount = producttrending.getReactcount();
        condition = "like";
        splitreactcount = reactcount.split(", ");
        for( i = 0; i<splitreactcount.length; i++){
            String idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();
            if (splitreactcount[i].equals(idno)){
                holder.LinearLayoutLike.setVisibility(LinearLayout.GONE);
                holder.LinearLayoutLiked.setVisibility(LinearLayout.VISIBLE);
                condition = "liked";

            }


        }
        i = i - 1;

        holder.TextViewLikeCount.setText(String.valueOf(i));






        holder.btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idpost = producttrending.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_POSTID,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    SharedPrefManagerPost.getInstance(mCtx.getApplicationContext())
                                            .userPostId(
                                                    jsonObject.getString("idpost"),
                                                    jsonObject.getString("postidno"),
                                                    jsonObject.getString("postpost"),
                                                    jsonObject.getString("postgraduatedimage"),
                                                    jsonObject.getString("postdate"),
                                                    jsonObject.getString("posttime"),
                                                    jsonObject.getString("postfirstname"),
                                                    jsonObject.getString("postmiddlename"),
                                                    jsonObject.getString("postlastname")


                                            );
                                    Intent intent = new Intent(mCtx, commentinfo.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mCtx.startActivity(intent);
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
                        params.put("idpost", idpost);


                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);
            }
        });
    //popup setting






        //like button
        holder.LinearLayoutLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                progressDialog = new ProgressDialog(mCtx);
//                progressDialog.setMessage("Please wait....");

                String idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();
                String idpost = producttrending.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_POSTLIKE,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    holder.LinearLayoutLike.setVisibility(LinearLayout.GONE);
                                    holder.LinearLayoutLiked.setVisibility(LinearLayout.VISIBLE);

                                    if (condition == "like"){
                                        holder.TextViewLikeCount.setText(String.valueOf(i + 1));
                                    }
                                    if (condition == "liked"){
                                        holder.TextViewLikeCount.setText(String.valueOf(i +1));
                                    }


//                                    holder.TextViewLikeCount.setText(countreact + 1);
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
                        params.put("idno", idno);
                        params.put("idpost", idpost);


                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);
            }
        });
        //unlike button
        holder.LinearLayoutLiked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                progressDialog = new ProgressDialog(mCtx);
//                progressDialog.setMessage("Please wait....");

                String idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();
                String idpost = producttrending.getId();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_POSTLIKED,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    holder.LinearLayoutLike.setVisibility(LinearLayout.VISIBLE);
                                    holder.LinearLayoutLiked.setVisibility(LinearLayout.GONE);

                                    if (condition == "like"){
                                        holder.TextViewLikeCount.setText(String.valueOf(i));
                                    }
                                    if (condition == "liked"){
                                        holder.TextViewLikeCount.setText(String.valueOf(i));
                                    }

//                                    holder.TextViewLikeCount.setText(String.valueOf(i + TotalCount));

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
                        params.put("idno", idno);
                        params.put("idpost", idpost);


                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
                requestQueue.add(stringRequest);
            }
        });


        holder.Auto_Complete_Setting.setVisibility(LinearLayout.GONE);
        idnopost = producttrending.getIdno();
        idno = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getIDno();
        if (idnopost.equals(idno)){
            holder.Auto_Complete_Setting.setVisibility(LinearLayout.VISIBLE);
        }


        holder.adapterItems = new ArrayAdapter<String>(mCtx,R.layout.list_item,holder.items);
        holder.Auto_Complete_Setting.setAdapter(holder.adapterItems);
        holder.Auto_Complete_Setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                //edit button
                if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Edit")){
                    Intent intent = new Intent(mCtx,editpost.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("idpost", producttrending.getId());
                    intent.putExtra("editpost", producttrending.getPost());
                    holder.Auto_Complete_Setting.setText("...");
                    holder.adapterItems = new ArrayAdapter<String>(mCtx,R.layout.list_item,holder.items);
                    holder.Auto_Complete_Setting.setAdapter(holder.adapterItems);

                    mCtx.startActivity(intent);
                    ((Activity) getContext()).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }
                //delete
                if(adapterView.getItemAtPosition(i).toString().equalsIgnoreCase("Delete")){

                    mdialog = new Dialog(mCtx);
                    mdialog.setContentView(R.layout.popup_setting);
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
                            String idpost = producttrending.getId();



                            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                    Constants.URL_DELETEPOST,

                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {


                                            try {
                                                JSONObject jsonObject = new JSONObject(response);

                                                Toast.makeText(mCtx, "Post Deleted", Toast.LENGTH_SHORT).show();
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
    }
    @Override
    public int getItemCount() {
        return trendinglists.size();
    }

    public void filterList(List<trendinglist> filteredList) {

        trendinglists = filteredList;
        notifyDataSetChanged();

    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTrendingPost,TextViewName,TextViewLikeCount,TextViewCommentCount;
        ImageView ImageViewProfile;
        CardView btnevent;
        LinearLayout LinearLayoutLike,LinearLayoutLiked,LinearLayoutDelete;
        CardView CardViewbgbutton;
        AutoCompleteTextView Auto_Complete_Setting;
        String[] items = {"Edit","Delete"};
        ArrayAdapter<String> adapterItems;
        public ProductViewHolder(View itemView) {
            super(itemView);

            CardViewbgbutton = itemView.findViewById(R.id.bgbutton);
            textViewTrendingPost = itemView.findViewById(R.id.trendingpost);
            TextViewName = itemView.findViewById(R.id.name);
            ImageViewProfile = itemView.findViewById(R.id.graduatedimage);
            btnevent = itemView.findViewById(R.id.bgbutton);
            LinearLayoutLike = itemView.findViewById(R.id.like);
            LinearLayoutLiked = itemView.findViewById(R.id.liked);
            TextViewLikeCount = itemView.findViewById(R.id.likecount);
            TextViewCommentCount = itemView.findViewById(R.id.commentcount);
            TextViewCommentCount = itemView.findViewById(R.id.commentcount);
            Auto_Complete_Setting = itemView.findViewById(R.id.auto_complete_setting);
//            LinearLayoutDelete = itemView.findViewById(R.id.lineardelete);
//            Dropdownsetting = itemView.findViewById(R.id.dropdown);
//            ButtonPopupSetting = itemView.findViewById(R.id.popupsetting);



        }
    }
}