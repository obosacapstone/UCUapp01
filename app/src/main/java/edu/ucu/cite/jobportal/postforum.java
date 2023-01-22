package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
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
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class postforum extends AppCompatActivity implements View.OnClickListener {
    EditText EditTextPost;
    Button Buttonsubmit;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postforum);


        EditTextPost = findViewById(R.id.post);

        Buttonsubmit = findViewById(R.id.btnUpload);
        Buttonsubmit.setOnClickListener(this);


    }
    private void postforum(){

        String idno = SharedPrefManager.getInstance(this).getIDno();

        String post = String.valueOf(EditTextPost.getText()).trim();



                    if (!post.isEmpty()) {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                Constants.URL_UPLOADPOST,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                                                startActivity(new Intent(getApplicationContext(), trendinginfo.class));
                                                finish();




                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Log.e("anyText", response);
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

//                                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("idno", idno);
                                params.put("post", post);



                                return params;

                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(this);
                        requestQueue.add(stringRequest);
                    }else{
                        Toast.makeText(getApplicationContext(),"Insert message is required",Toast.LENGTH_SHORT).show();
                    }

    }



    @Override
    public void onClick(View view) {
        if (view == Buttonsubmit)
            postforum();
    }


    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }



    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}