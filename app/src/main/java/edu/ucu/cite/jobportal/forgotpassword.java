package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
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

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class forgotpassword extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    EditText EditTextidno,EditTextemail;
    Button Buttoncontinues;
    ProgressDialog progressDialog;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        EditTextidno = findViewById(R.id.idno);
        EditTextemail = findViewById(R.id.email);
        Buttoncontinues = findViewById(R.id.continues);
        Buttoncontinues.setOnClickListener(this);
    }
    private void forgotpassword(){

        String idno, email;
        idno = String.valueOf(EditTextidno.getText()).trim();
        email = String.valueOf(EditTextemail.getText()).trim();
        String checkemail = "///";



        if(idno.isEmpty()){
            EditTextidno.setError("Please fill up");
            EditTextidno.requestFocus();
        }
        if(email.isEmpty()){
            EditTextemail.setError("Please fill up");
            EditTextemail.requestFocus();
        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern)){
            EditTextemail.setError("Please Enter Valid Email");
            EditTextemail.requestFocus();
        }else{
            EditTextemail.setError(null);
        }

        if (!idno.isEmpty() && !email.isEmpty() && email.matches(emailPattern)){


        progressDialog = new ProgressDialog(forgotpassword.this);
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
                                                Intent intent = new Intent(forgotpassword.this,resetcode.class);
//                                                Bundle extras = intent.getExtras();
                                                intent.putExtra("email",email);
                                                intent.putExtra("idno",idno);
                                                startActivity(intent);
                                                finish();
                                            }
                                        EditTextidno.setText("");
                                        EditTextemail.setText("");
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
                            params.put("idno",idno);
                            params.put("email",email);
                            params.put("checkemail",checkemail);

                            return params;

                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
        }


    }





    @Override
    public void onClick(View view) {
        if (view == Buttoncontinues)
            forgotpassword();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
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