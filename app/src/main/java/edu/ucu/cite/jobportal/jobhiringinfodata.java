package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class jobhiringinfodata extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView textviewjobtitledata, textviewcompanydata, textviewemaildata, textviewcontactdata, textviewlocationdata, textviewdurationdata, textviewqualificationdata, textviewjobtypedata, textviewlinkdata, textviewdescriptiondata,textviewsalary;
    TextView TextViewTitleNav;
    NavigationView navigationView;
    DrawerLayout mydrawer;
    Toolbar toolbar;
    ActionBarDrawerToggle mytoggle=null;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    Button ButtonSave,ButtonSaved;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    Button ButtonApplyNow,ButtonTitleJob;
    String StringTitleLength,StringTitleOverflow;
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobhiringinfodata);
        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();



        Glide.with(jobhiringinfodata.this).load(graduatedimage).into(ImageViewNavProfile);
        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewNavIdno.setText(idno);

        textviewjobtitledata = findViewById(R.id.jobtitledata);
        textviewcompanydata = findViewById(R.id.companynamedata);
        textviewemaildata = findViewById(R.id.emaildata);
        textviewcontactdata = findViewById(R.id.contactdata);
        textviewlocationdata = findViewById(R.id.locationdata);
        textviewdurationdata = findViewById(R.id.durationdata);
        textviewqualificationdata = findViewById(R.id.qualificationdata);
        textviewjobtypedata = findViewById(R.id.jobtypedata);
        textviewlinkdata = findViewById(R.id.linkdata);
        textviewdescriptiondata = findViewById(R.id.descriptiondata);
        textviewsalary = findViewById(R.id.salary);





        textviewjobtitledata.setText(getIntent().getStringExtra("jobtitle"));
        textviewcompanydata.setText(getIntent().getStringExtra("companyname"));
        textviewemaildata.setText(getIntent().getStringExtra("email"));
        textviewcontactdata.setText(getIntent().getStringExtra("contact"));
        textviewlocationdata.setText(getIntent().getStringExtra("location"));




        String startdate = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(new Date(getIntent().getStringExtra("startdate")));
        String enddate = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(new Date(getIntent().getStringExtra("enddate")));



        textviewdurationdata.setText(startdate + " - " + enddate);


        String StringQualification = getIntent().getStringExtra("qualification");
        String splitted[] = StringQualification.split(",,,");
        String StringAllQualification = "";
        for(int i =0; i<splitted.length; i++){
            StringAllQualification +=  " • " + splitted[i] + "\n";
        }

        textviewqualificationdata.setText(StringAllQualification);

        textviewjobtypedata.setText(getIntent().getStringExtra("jobtype"));
        textviewlinkdata.setText(getIntent().getStringExtra("link"));
        textviewdescriptiondata.setText(getIntent().getStringExtra("description"));

        String Salary1 = getIntent().getStringExtra("minimumsalary");
        String Salary2 = getIntent().getStringExtra("maximumsalary");
        textviewsalary.setText(Salary1 + " - " +Salary2 + " Monthly");



        mydrawer = findViewById(R.id.mydrawer);
        navigationView = findViewById(R.id.navigationdrawer);

        toolbar=findViewById(R.id.sidetoolbar);
        mytoggle = new ActionBarDrawerToggle(this,mydrawer, toolbar, R.string.open, R.string.close);

        mydrawer.addDrawerListener(mytoggle);
        navigationView.bringToFront();
        mytoggle.syncState();

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

        //nav bar
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Job Information");

        ButtonSave = findViewById(R.id.save);
        ButtonSaved = findViewById(R.id.saved);

        validationsave(this);


        ButtonApplyNow = findViewById(R.id.ApplyNow);
        ButtonApplyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                relativeLayoutProgressBar.setVisibility(View.VISIBLE);

                String id = getIntent().getStringExtra("id");

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_APPLYNOW,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {

                                    JSONObject jsonObject = new JSONObject(response);
                                    SendMail();

                                    progressBar.setVisibility(View.GONE);
                                    relativeLayoutProgressBar.setVisibility(View.GONE);

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
                        params.put("id",id);
                        return params;

                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(jobhiringinfodata.this);
                requestQueue.add(stringRequest);


            }
        });

        ButtonTitleJob = findViewById(R.id.TitleJob);
        StringTitleLength =  getIntent().getStringExtra("jobtitle");

        if (14 >= StringTitleLength.length() ){
            ButtonTitleJob.setText(getIntent().getStringExtra("jobtitle"));
        }else {
            StringTitleOverflow = getSafeSubstring(getIntent().getStringExtra("jobtitle"), 11);
            ButtonTitleJob.setText(StringTitleOverflow + "...");
        }
    }

    public String getSafeSubstring(String s, int maxLength){
        if(!TextUtils.isEmpty(s)){
            if(s.length() >= maxLength){
                return s.substring(0, maxLength);
            }
        }
        return s;
    }
    private void SendMail() {
        String recipientList = getIntent().getStringExtra("email");
        String[] recipients = recipientList.split(",");

        Intent intent = new Intent (Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);

        intent.setType("message/rfc8222");
        startActivity(Intent.createChooser(intent, "Choose an Email Client"));
    }

    private void validationsave(jobhiringinfodata jobhiringinfodata) {
        ButtonSave.setVisibility(LinearLayout.VISIBLE);
        ButtonSaved.setVisibility(LinearLayout.GONE);


        String Stringid = getIntent().getStringExtra("id");
        String bookmark = SharedPrefManager.getInstance(this).getBookmark();

        String splitbookmark[] = bookmark.split(", ");

        for(int i =0; i<splitbookmark.length; i++){

            if (splitbookmark[i].equals(Stringid)){
                ButtonSave.setVisibility(LinearLayout.GONE);
                ButtonSaved.setVisibility(LinearLayout.VISIBLE);
            }

        }

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
                Intent intent1 = new Intent(jobhiringinfodata.this,alumni.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(jobhiringinfodata.this,trendinginfo.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(jobhiringinfodata.this,bookmarkinfo.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(jobhiringinfodata.this,login.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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

    public void backjob(View view) {
        finish();
    }

    public void Save(View view) {
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);

        String idno = SharedPrefManager.getInstance(this).getIDno();
        String id = getIntent().getStringExtra("id");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_JOBSAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(), eventinfo.class));
//                            finish();
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

                            ButtonSave.setVisibility(LinearLayout.GONE);
                            ButtonSaved.setVisibility(LinearLayout.VISIBLE);


                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText",response);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idno",idno);
                params.put("id",id);


                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void Saved(View view) {
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);

        String idno = SharedPrefManager.getInstance(this).getIDno();
        String id = getIntent().getStringExtra("id");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_JOBSAVED,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(), eventinfo.class));
//                            finish();
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

                            ButtonSave.setVisibility(LinearLayout.VISIBLE);
                            ButtonSaved.setVisibility(LinearLayout.GONE);
                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText",response);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idno",idno);
                params.put("id",id);


                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}