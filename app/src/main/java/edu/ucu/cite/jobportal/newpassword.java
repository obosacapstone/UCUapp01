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
import android.widget.TextView;
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

public class newpassword extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener  {
    EditText EditTextnewpassword,EditTextconfirmpassword;
    Button Buttonchange;
    ProgressDialog progressDialog;
    TextView TextViewdisplayidno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);
        EditTextnewpassword = findViewById(R.id.newpassword);
        EditTextconfirmpassword = findViewById(R.id.confirmpassword);
        Buttonchange = findViewById(R.id.change);
        Buttonchange.setOnClickListener(this);
        TextViewdisplayidno = findViewById(R.id.displayidno);


    }

    private void newpassword(){

        String password,cpassword;
        password = String.valueOf(EditTextnewpassword.getText()).trim();
        cpassword = String.valueOf(EditTextconfirmpassword.getText()).trim();
        String changepassword = "///";
        String email = getIntent().getStringExtra("email");
        String idno = getIntent().getStringExtra("idno");
//            String email = "crisuser1234@gmail.com";
//            String idno = "123";


        if(password.isEmpty()){
            EditTextnewpassword.setError("Please fill up");
            EditTextnewpassword.requestFocus();
        }
        if (cpassword.isEmpty()){
            EditTextconfirmpassword.setError("Please fill up");
            EditTextconfirmpassword.requestFocus();
        }



        if (password.equals(cpassword)){


            progressDialog = new ProgressDialog(newpassword.this);
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
                                    startActivity(new Intent(getApplicationContext(), login.class));
                                    finish();
                                }
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
                    params.put("password",password);
                    params.put("cpassword",cpassword);
                    params.put("changepassword",changepassword);
                    params.put("email",email);
                    params.put("idno",idno);


                    return params;

                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(),"Password Doesn't Match",Toast.LENGTH_SHORT).show();
        }


    }





    @Override
    public void onClick(View view) {
        if (view == Buttonchange)
            newpassword();
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}