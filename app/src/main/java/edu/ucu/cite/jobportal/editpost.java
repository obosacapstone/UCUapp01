package edu.ucu.cite.jobportal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class editpost extends AppCompatActivity implements View.OnClickListener{
    TextView TextViewIDPost;
    String Stringidpost,StringPost;
    EditText EditTextEditPost;
    Button Buttonsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpost);
        TextViewIDPost = findViewById(R.id.editpostid);
        EditTextEditPost = findViewById(R.id.editpost);
        Stringidpost =  getIntent().getStringExtra("idpost");
        TextViewIDPost.setText(Stringidpost);

        StringPost =  getIntent().getStringExtra("editpost");
        EditTextEditPost.setText(StringPost);
        Buttonsubmit = findViewById(R.id.btnUpload);
        Buttonsubmit.setOnClickListener(this);
    }
    private void editpost(){

        String idno = SharedPrefManager.getInstance(this).getIDno();

        String post = String.valueOf(EditTextEditPost.getText()).trim();



        if (!post.isEmpty()) {

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_EDITPOST,
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
                    params.put("idpost", Stringidpost);
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
            editpost();
    }


    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}