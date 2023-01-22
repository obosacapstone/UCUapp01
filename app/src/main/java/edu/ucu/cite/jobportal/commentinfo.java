package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class commentinfo extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mtoggle=null,mytoggle=null;
    androidx.appcompat.widget.Toolbar toolbar;
    RecyclerView recyclerViewComment;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager managercomment;
    private List<commentlist> productListcomment;
    SwipeRefreshLayout SwiperefreshLayout;
    TextView TextViewTitleNav;
    SearchView searchView;
    RecyclerAdapterComment adapterComment;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    EditText EditTextCommentPost;
    ImageView ImageViewSubmit,ImageViewPost;
    TextView TextViewPost,TextViewName;
    String commentidno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentinfo);
        commentidno = SharedPrefManager.getInstance(this).getIDno();

        String idpost = SharedPrefManagerPost.getInstance(this).getPostId();
        String post = SharedPrefManagerPost.getInstance(this).getPost();
        String postimage = SharedPrefManagerPost.getInstance(this).getGraduatedImage();
        String date = SharedPrefManagerPost.getInstance(this).getDate();
        String time = SharedPrefManagerPost.getInstance(this).getTime();
        String postfirstname = SharedPrefManagerPost.getInstance(this).getFirstname();
        String postmiddlename = SharedPrefManagerPost.getInstance(this).getMiddlename();
        String postlastname = SharedPrefManagerPost.getInstance(this).getLastname();

        ImageViewPost = findViewById(R.id.postimage);
        TextViewPost = findViewById(R.id.post);
        TextViewName = findViewById(R.id.postname);

        Glide.with(commentinfo.this).load(postimage).into(ImageViewPost);
        TextViewPost.setText(post);
        TextViewName.setText(postfirstname + " " + postmiddlename + " " + postlastname);







//        progressBar = findViewById(R.id.progress);
//        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);


        recyclerViewComment =findViewById(R.id.recycleviewcomment);
        managercomment = new GridLayoutManager(commentinfo.this, 1);
        recyclerViewComment.setLayoutManager(managercomment);
        productListcomment = new ArrayList<>();

        loadComment();
//        if(idpost == null){
//            startActivity(new Intent(getApplicationContext(), commentinfo.class));
//            finish();
//        }
        EditTextCommentPost = findViewById(R.id.commentpost);
        ImageViewSubmit = findViewById(R.id.submit);
        ImageViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String commentpost = String.valueOf(EditTextCommentPost.getText()).trim();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_COMMENTPOST,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                productListcomment.clear();
                                try {
                                    //converting the string to json array object
                                    JSONArray array = new JSONArray(response);

                                    //traversing through all the object
                                    for (int i = 0; i < array.length(); i++) {

                                        JSONObject comment = array.getJSONObject(i);


                                        productListcomment.add(new commentlist(
                                                comment.getString("id"),
                                                comment.getString("idno"),
                                                comment.getString("idpost"),
                                                comment.getString("comment"),
                                                comment.getString("date"),
                                                comment.getString("time"),
                                                comment.getString("img"),
                                                comment.getString("firstname"),
                                                comment.getString("middlename"),
                                                comment.getString("lastname")
                                        ));

//                                        startActivity(new Intent(getApplicationContext(), commentinfo.class));
//                                        finish();


                                    }
                                    EditTextCommentPost.setText("");
                                    //creating adapter object and setting it to recyclerview
                                    adapterComment = new RecyclerAdapterComment(commentinfo.this, productListcomment);
                                    recyclerViewComment.setAdapter(adapterComment);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("anyText", response);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("commentidno",commentidno);
                        params.put("commentpost",commentpost);
                        params.put("idpost",idpost);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(commentinfo.this);
                requestQueue.add(stringRequest);
            }
        });
    }




    private void loadComment() {
        String idpost = SharedPrefManagerPost.getInstance(this).getPostId();

//        String idpost = getIntent().getStringExtra("postid");
//        String idpost = "2";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_COMMENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        productListcomment.clear();
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject comment = array.getJSONObject(i);


                                productListcomment.add(new commentlist(
                                        comment.getString("idcomment"),
                                        comment.getString("idno"),
                                        comment.getString("idpost"),
                                        comment.getString("comment"),
                                        comment.getString("date"),
                                        comment.getString("time"),
                                        comment.getString("img"),
                                        comment.getString("firstname"),
                                        comment.getString("middlename"),
                                        comment.getString("lastname")
                                ));

                                if (idpost == null){
                                    Intent intent = new Intent(commentinfo.this,courseinfo.class);
                                    startActivity(intent);
                                }


                            }

                            //creating adapter object and setting it to recyclerview
                            adapterComment = new RecyclerAdapterComment(commentinfo.this, productListcomment);
                            recyclerViewComment.setAdapter(adapterComment);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText", response);
                        }
                    }
                }, new Response.ErrorListener() {
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Volley.newRequestQueue(this).add(stringRequest);
    }


    public void back(View view) {
        finish();
    }
}