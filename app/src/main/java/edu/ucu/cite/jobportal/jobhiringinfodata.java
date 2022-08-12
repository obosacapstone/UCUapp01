package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class jobhiringinfodata extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView textviewjobtitledata, textviewcompanydata, textviewemaildata, textviewcontactdata, textviewlocationdata, textviewdurationdata, textviewqualificationdata, textviewjobtypedata, textviewlinkdata, textviewdescriptiondata;
    TextView TextViewTitleNav;
    NavigationView navigationView;
    DrawerLayout mydrawer;
    Toolbar toolbar;
    ActionBarDrawerToggle mytoggle=null;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobhiringinfodata);
        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);


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

            case R.id.profile:
                Intent intent1 = new Intent(jobhiringinfodata.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.jobhiring:
                Intent intent2 = new Intent(jobhiringinfodata.this,jobhiringinfo.class);
                startActivity(intent2);
                break;
            case R.id.news:
                Intent intent3 = new Intent(jobhiringinfodata.this,newsinfo.class);
                startActivity(intent3);
                break;
            case R.id.event:
                Intent intent4 = new Intent(jobhiringinfodata.this,eventinfo.class);
                startActivity(intent4);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(jobhiringinfodata.this,login.class);
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
}