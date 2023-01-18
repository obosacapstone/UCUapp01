package edu.ucu.cite.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText textInputEditTextidnumber, textInputEditTextpassword;
    Button buttonLogin,buttonpopupregister;
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    Dialog dialog;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, jobhiringinfo.class));
            return;
        }
        dialog = new Dialog(login.this);
        dialog.setContentView(R.layout.custom_register);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);


//        Button ButtonYes = dialog.findViewById(R.id.btn_yes);
//        Button ButtonNo = dialog.findViewById(R.id.btn_no);

//        buttonpopupregister = findViewById(R.id.popupregister);
//        buttonpopupregister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.show();
//            }
//        });
        textInputEditTextidnumber = findViewById(R.id.username);
        textInputEditTextpassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.Login);

        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        buttonLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == buttonLogin) {
            userLogin();
        }
    }

    private void userLogin() {
        final String idnumber = textInputEditTextidnumber.getText().toString().trim();
        final String password = textInputEditTextpassword.getText().toString().trim();

        if (idnumber.isEmpty()) {
            textInputEditTextidnumber.setError("Please enter your ID number");
        }
        if (password.isEmpty()) {
            textInputEditTextpassword.setError("Please enter your Password");
        }
        if (!idnumber.isEmpty() && !password.isEmpty() ){

            progressBar.setVisibility(View.VISIBLE);
            relativeLayoutProgressBar.setVisibility(View.VISIBLE);

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                textInputEditTextidnumber.setText("");
                                textInputEditTextpassword.setText("");

                                if (!jsonObject.getBoolean("error")) {
                                    SharedPrefManager.getInstance(getApplicationContext())
                                            .userLogin(
                                                    jsonObject.getString("idno"),
                                                    jsonObject.getString("password"),
                                                    jsonObject.getString("firstname"),
                                                    jsonObject.getString("middlename"),
                                                    jsonObject.getString("lastname"),
                                                    jsonObject.getString("yeargrad"),
                                                    jsonObject.getString("yeargrad1"),
                                                    jsonObject.getString("college"),
                                                    jsonObject.getString("course"),
                                                    jsonObject.getString("course1"),
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
                                                    jsonObject.getString("linkedin"),
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

                                    startActivity(new Intent(getApplicationContext(), jobhiringinfo.class));
                                    finish();
                                } else {

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("anyText", response);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);

//                            Toast.makeText(getApplicationContext(), "Please Reload ", Toast.LENGTH_SHORT).show();

                        }


                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("idno", idnumber);
                    params.put("password", password);
                    return params;

                }

            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }else
        {
            Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
        }
    }

    public void RegisterYes(View view) {
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_DATABASE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        relativeLayoutProgressBar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);



                            if (!jsonObject.getBoolean("error")) {
                                SharedPrefManagerDatabase.getInstance(getApplicationContext())
                                        .userDatabase(
                                                jsonObject.getString("courses"),
                                                jsonObject.getString("region"),
                                                jsonObject.getString("regioncode1"),
                                                jsonObject.getString("province"),
                                                jsonObject.getString("provincecode1"),
                                                jsonObject.getString("provincecode2"),
                                                jsonObject.getString("city"),
                                                jsonObject.getString("citycode1"),
                                                jsonObject.getString("citycode2"),
                                                jsonObject.getString("citycode3"),
                                                jsonObject.getString("barangay"),
                                                jsonObject.getString("barangaycode1"),
                                                jsonObject.getString("barangaycode2"),
                                                jsonObject.getString("barangaycode3")



                                        );

                                startActivity(new Intent(getApplicationContext(), register.class));
                                finish();
                            } else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        relativeLayoutProgressBar.setVisibility(View.GONE);

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    //register no
    public void RegisterNo(View view) {
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_DATABASE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        relativeLayoutProgressBar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);



                            if (!jsonObject.getBoolean("error")) {
                                SharedPrefManagerDatabase.getInstance(getApplicationContext())
                                        .userDatabase(
                                                jsonObject.getString("courses"),
                                                jsonObject.getString("region"),
                                                jsonObject.getString("regioncode1"),
                                                jsonObject.getString("province"),
                                                jsonObject.getString("provincecode1"),
                                                jsonObject.getString("provincecode2"),
                                                jsonObject.getString("city"),
                                                jsonObject.getString("citycode1"),
                                                jsonObject.getString("citycode2"),
                                                jsonObject.getString("citycode3"),
                                                jsonObject.getString("barangay"),
                                                jsonObject.getString("barangaycode1"),
                                                jsonObject.getString("barangaycode2"),
                                                jsonObject.getString("barangaycode3")



                                        );

                                startActivity(new Intent(getApplicationContext(), registerno.class));
                                finish();
                            } else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText", response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        relativeLayoutProgressBar.setVisibility(View.GONE);

                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;

            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void ForgetPassword(View view) {
        Intent intent = new Intent(login.this,forgotpassword.class);
        startActivity(intent);
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
}
