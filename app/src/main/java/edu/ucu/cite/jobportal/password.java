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

public class password extends AppCompatActivity  implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {
    EditText EditTextpassword, EditTextnewpassword, EditTextconfirmpassword;
    Button Buttonsubmit;
    ProgressDialog progressDialog;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mtoggle=null,mytoggle=null;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout mydrawer;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    TextView TextViewTitleNav;
    NavigationView navigationView;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Change Password");
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);


        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);

        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();



        Glide.with(password.this).load(graduatedimage).into(ImageViewNavProfile);
        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewNavIdno.setText(idno);


        mydrawer = findViewById(R.id.mydrawer);
        navigationView = findViewById(R.id.navigationdrawer);

        toolbar=findViewById(R.id.sidetoolbar);
        mytoggle = new ActionBarDrawerToggle(this,mydrawer, toolbar, R.string.open, R.string.close);

        mydrawer.addDrawerListener(mytoggle);
        navigationView.bringToFront();
        mytoggle.syncState();

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);




        EditTextpassword = findViewById(R.id.password);
        EditTextnewpassword = findViewById(R.id.newpassword);
        EditTextconfirmpassword = findViewById(R.id.confirmpassword);
        Buttonsubmit = findViewById(R.id.submit);
        Buttonsubmit.setOnClickListener(this);


    }
        private void updatepassword(){

            String Stringpassword,Stringnewpassword, Stringconfirmpassword;
            String idno = SharedPrefManager.getInstance(this).getIDno();
            String oldpassword = SharedPrefManager.getInstance(this).getPassword();
            Stringpassword = String.valueOf(EditTextpassword.getText()).trim();
            Stringnewpassword = String.valueOf(EditTextnewpassword.getText()).trim();
            Stringconfirmpassword = String.valueOf(EditTextconfirmpassword.getText()).trim();




            if(Stringpassword.isEmpty()){
                EditTextpassword.setError("Please fill up");
                EditTextpassword.requestFocus();
            }
            if(Stringnewpassword.length() < 7){
                EditTextnewpassword.setError("Password must be atleast 8 characters");
                EditTextnewpassword.requestFocus();
            }
            if(Stringnewpassword.isEmpty()){
                EditTextnewpassword.setError("Please fill up");
                EditTextnewpassword.requestFocus();
            }
            if(Stringconfirmpassword.isEmpty()){
                EditTextconfirmpassword.setError("Please fill up");
                EditTextconfirmpassword.requestFocus();
            }


            if(!(Stringpassword.isEmpty()) && !(Stringnewpassword.isEmpty()) && !(Stringconfirmpassword.isEmpty())) {
                if (Stringnewpassword.length() < 7) {
                }else{

                if (Stringpassword.equals(oldpassword)) {

                    if (Stringnewpassword.equals(Stringconfirmpassword)) {
                        progressBar.setVisibility(View.VISIBLE);
                        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
                        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                Constants.URL_PASSOWRD,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        progressDialog.dismiss();
                                        try {

                                            JSONObject jsonObject = new JSONObject(response);
                                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                            EditTextpassword.setText("");
                                            EditTextnewpassword.setText("");
                                            EditTextconfirmpassword.setText("");
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
                                                progressBar.setVisibility(View.GONE);
                                                relativeLayoutProgressBar.setVisibility(View.GONE);
                                                startActivity(new Intent(getApplicationContext(), profile.class));
                                                finish();

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

                                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("idno", idno);
                                params.put("Stringnewpassword", Stringnewpassword);


                                return params;

                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(this);
                        requestQueue.add(stringRequest);
                    } else {
                        EditTextpassword.setText("");
                        EditTextnewpassword.setText("");
                        EditTextconfirmpassword.setText("");
                        Toast.makeText(getApplicationContext(), "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    EditTextpassword.setText("");
                    EditTextnewpassword.setText("");
                    EditTextconfirmpassword.setText("");
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }

            }




            }

        }





    @Override
    public void onClick(View view) {
        if (view == Buttonsubmit)
            updatepassword();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.mydrawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (mytoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.Alumni:
                Intent intent1 = new Intent(password.this,courseinfo.class);
                startActivity(intent1);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(password.this,trendinginfo.class);
                startActivity(intent2);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(password.this,bookmarkinfo.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(password.this,login.class);
                startActivity(intent5);
                break;




        }

        return true;
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