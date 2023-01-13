package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class alumni extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView TextViewAlumni;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    ActionBarDrawerToggle mtoggle=null ,  mytoggle=null;
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    DrawerLayout mydrawer ,drawerLayout;
    private MenuItem item;
    NavigationView navigationView;
    TextView TextViewTitleNav;

    String idno,yeargrad1;
    String alumni = "";
    int courseoutput;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        TextViewAlumni = findViewById(R.id.alumni);
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

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

        BottomNavigationView bottomNavigationView = findViewById(R.id.naview);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){

                case R.id.Jobs:
                    startActivity(new Intent(getApplicationContext(),jobhiringinfo.class));
                    finish();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    return true;
                case R.id.News:
                    startActivity(new Intent(getApplicationContext(),newsinfo.class));
                    finish();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    return true;
                case R.id.Events:
                    startActivity(new Intent(getApplicationContext(),eventinfo.class));
                    finish();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    return true;
                case R.id.Profile:
                    startActivity(new Intent(getApplicationContext(),profile.class));
                    finish();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    return true;


            }
            return false;
        });

        Glide.with(alumni.this).load(graduatedimage).into(ImageViewNavProfile);
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
        //nav bar
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Alumni");




        String yeargrad = SharedPrefManager.getInstance(this).getYeargrad();
        idno = SharedPrefManager.getInstance(this).getIDno();
        alumni = SharedPrefManagerAlumni.getInstance(this).getAlumni();
        String StringOutput = "", StringAlumniName = "", finaloutput = "";

//        TextViewAlumni.setText(alumni);
        alumnudatebase();

        if(alumni != null){
            finaloutput = "<br><b> Batch "+yeargrad+"</b><br>";


            String splityeargrad[] = alumni.split(",/,/,/");


            String splitcourses[] = splityeargrad[1].split("/&/&/");

            for(int ii =0; ii<splitcourses.length; ii++) {


                String splitalumnilist[] = splitcourses[ii].split("///");
                for (int iii = 1; iii < splitalumnilist.length; iii++) {

                    String splitalumnilist1[] = splitalumnilist[1].split("&&&&");
                    for (int iiii = 1; iiii < splitalumnilist1.length; iiii++) {

                        StringAlumniName = StringAlumniName +"<br>" + "  -  " +splitalumnilist1[iiii] ;

                    }

                }
                StringOutput = "<br><br><b>" +splitalumnilist[0] + "</b><br>";

                finaloutput = finaloutput  + StringOutput +  StringAlumniName;
                StringOutput = "";
                StringAlumniName = "";
            }




            yeargrad1 = SharedPrefManager.getInstance(this).getYeargrad1();
            if (yeargrad1.length() == 0){

            }else {
                finaloutput = finaloutput + "<br><b>Batch "+yeargrad1+"</b><br>";
            }

                String splitcourses1[] = splityeargrad[2].split("/&/&/");

                for(int ii =0; ii<splitcourses1.length; ii++) {


                    String splitalumnilist1[] = splitcourses1[ii].split("///");
                    for (int iii = 1; iii < splitalumnilist1.length; iii++) {

                        String splitalumnilist11[] = splitalumnilist1[1].split("&&&&");
                        for (int iiii = 1; iiii < splitalumnilist11.length; iiii++) {

                            StringAlumniName = StringAlumniName +"<br>"+ "  -  " + splitalumnilist11[iiii] ;

                        }

                    }
                    StringOutput = "<br><br><b>" +splitalumnilist1[0] + "</b><br>";

                    finaloutput = finaloutput  + StringOutput +  StringAlumniName;
                    StringOutput = "";
                    StringAlumniName = "";

            }
            TextViewAlumni.setText(HtmlCompat.fromHtml(finaloutput, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        }else {
            startActivity(new Intent(getApplicationContext(), alumni.class));
            finish();
        }


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mytoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    private void alumnudatebase() {
        idno = SharedPrefManager.getInstance(this).getIDno();
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_ALUMNI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);



                            if (!jsonObject.getBoolean("error")) {
                                SharedPrefManagerAlumni.getInstance(getApplicationContext())
                                        .userDatabase(
                                                jsonObject.getString("alumni")

                                        );


                            }

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
//                        progressDialog.hide();
//
//                        Toast.makeText(getApplicationContext(), "Please Reload ", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        relativeLayoutProgressBar.setVisibility(View.GONE);
                    }


                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idno", idno);
                return params;

            }

        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.Alumni:
                Intent intent1 = new Intent(alumni.this,alumni.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(alumni.this,trendinginfo.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(alumni.this,bookmarkinfo.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(alumni.this,login.class);
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

}