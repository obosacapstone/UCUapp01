package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class resetcode extends AppCompatActivity  implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    EditText EditTextentercode;
    Button Buttonsubmit;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetcode);
        EditTextentercode = findViewById(R.id.entercode);
        Buttonsubmit = findViewById(R.id.submit);
        Buttonsubmit.setOnClickListener(this);
    }
    private void entercode(){

        String otp;
        otp = String.valueOf(EditTextentercode.getText()).trim();
        String checkresetotp = "///";

        String email = getIntent().getStringExtra("email");
        String idno = getIntent().getStringExtra("idno");


        if(otp.isEmpty()){
            EditTextentercode.setError("Please fill up");
            EditTextentercode.requestFocus();
        }



        if (!otp.isEmpty()){


            progressDialog = new ProgressDialog(resetcode.this);
            progressDialog.setMessage("Please wait....");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_FORGOTPASSWORD,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                if (!jsonObject.getBoolean("error")) {
                                    progressDialog.dismiss();
                                    Intent intent = new Intent(resetcode.this,newpassword.class);
                                    intent.putExtra("email",email);
                                    intent.putExtra("idno",idno);
                                    startActivity(intent);
                                    finish();
                                }
                                EditTextentercode.setText("");
                                progressDialog.dismiss();






                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("anyText",response);
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("otp",otp);
                    params.put("checkresetotp",checkresetotp);


                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }


    }





    @Override
    public void onClick(View view) {
        if (view == Buttonsubmit)
            entercode();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}