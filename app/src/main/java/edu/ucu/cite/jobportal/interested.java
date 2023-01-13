package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class interested extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    TextView TextViewInterestedData;
    TextView TextViewEventDetailData, TextViewEventDateData, TextViewdatedurationdata,TextViewAddressData,TextViewVenueData,
            TextViewDescriptionData,TextViewSponsorData,TextViewOrganizerData;
    ImageView ImageVieweventimagedata;
    String eventid,StringInterestedPlain,StringNotInterestedPlain,StringInterestedSelected,StringNotInterestedSelected;
    Date DateUploaded,DateStartDate , DateEndDate,qEndDate,qStartDate,qCurrentDate;
    String StringStartDate,StringEndDate,StartTime,EndTime,StringqStartDate,StringqEndDate,StringqCurrentDate;
    LinearLayout linearLayoutdatevalidation;
    Button ButtonInterestedPlain,ButtonNotInterestedPlain,ButtonInterestedSelected,ButtonNotInterestedSelected;
    String idno,Submitinterested,Submitnotinterested;
    DrawerLayout mydrawer;
    NavigationView navigationView;
    ActionBarDrawerToggle mtoggle=null,mytoggle=null;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView TextViewTitleNav,TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    Button btnChoose, btnUpload;
    ImageView imageUpload ,ImageProfile;
    Button ButtonTitleEvent;
    String StringTitleLength,StringTitleOverflow;
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested);
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Event Information");
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);
        ImageProfile = findViewById(R.id.imageUpload);
        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();

        ButtonTitleEvent = findViewById(R.id.TitleEvent);
        StringTitleLength =  getIntent().getStringExtra("eventdetail");

        if (14 >= StringTitleLength.length() ){
            ButtonTitleEvent.setText(getIntent().getStringExtra("eventdetail"));
        }else {
            StringTitleOverflow = getSafeSubstring(getIntent().getStringExtra("eventdetail"), 11);
            ButtonTitleEvent.setText(StringTitleOverflow + "...");
        }

//        Glide.with(interested.this).load(graduatedimage).into(ImageProfile);
        Glide.with(interested.this).load(graduatedimage).into(ImageViewNavProfile);
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

        TextViewEventDetailData = findViewById(R.id.eventdetaildata);
        TextViewdatedurationdata = findViewById(R.id.datedurationdata);
        TextViewAddressData = findViewById(R.id.addressdata);
        TextViewVenueData = findViewById(R.id.venuedata);
        TextViewDescriptionData = findViewById(R.id.descriptiondata);
        TextViewSponsorData = findViewById(R.id.sponsordata);
        TextViewOrganizerData = findViewById(R.id.organizerdata);
        ButtonInterestedPlain = findViewById(R.id.interestedplain);
        ButtonNotInterestedPlain = findViewById(R.id.notinterestedplain);
        ButtonInterestedSelected = findViewById(R.id.interestedselected);
        ButtonNotInterestedSelected = findViewById(R.id.notinterestedselected);



        TextViewEventDetailData.setText(getIntent().getStringExtra("eventdetail"));


        String StringAddress = "<b> Address: </b>" + getIntent().getStringExtra("eventaddress");
        TextViewAddressData.setText(HtmlCompat.fromHtml(StringAddress, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        String StringVenue = "<b> Venue: </b>" +  getIntent().getStringExtra("eventvenue");
        TextViewVenueData.setText(HtmlCompat.fromHtml(StringVenue, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));


        TextViewDescriptionData.setText(getIntent().getStringExtra("eventdescription"));
        ImageVieweventimagedata = findViewById(R.id.eventimagedata);
        String image = getIntent().getStringExtra("eventimage");
        Glide.with(interested.this).load(image).into(ImageVieweventimagedata);

        //event date uploaded
        String date = getIntent().getStringExtra("eventdate");
        SimpleDateFormat input = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
        try {
            DateUploaded = input.parse(date);                 // parse input
//            TextViewEventDateData.setText(output.format(DateUploaded));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //time
        String dateString3 = getIntent().getStringExtra("eventstarttime");
        String dateString4 = getIntent().getStringExtra("eventendtime");
        //old format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try{
            Date date3 = sdf.parse(dateString3);
            Date date4 = sdf.parse(dateString4);
            //new format
            SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat sdf4 = new SimpleDateFormat("hh:mm aa");
            //formatting the given time to new format with AM/PM
            StartTime = sdf3.format(date3);
            EndTime = sdf4.format(date4);
        }catch(ParseException e){
            e.printStackTrace();
        }

        //uploading date time


//        String StringDateeDuration;
//        if (StringEndDateValidate.isEmpty()){
//            StringDateeDuration = "<b>Date: </b>" + StringStartDate  + "<br>"
//                    + "<b>Time: </b>" + StartTime + " to " + EndTime;
//            TextViewdatedurationdata.setText(HtmlCompat.fromHtml(StringDateeDuration, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
//
//            //start date only
//            String date4 = getIntent().getStringExtra("eventstartdate");
//            SimpleDateFormat input4 = new SimpleDateFormat("yy-MM-dd");
//            SimpleDateFormat output4 = new SimpleDateFormat("yMMdd");
//            try {
//                qStartDate = input.parse(date4);
//                StringqStartDate= output4.format(qStartDate);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//        }
//        String date3 = getIntent().getStringExtra("eventenddate");
//
//        if (!StringEndDateValidate.isEmpty()){
//            StringDateeDuration = "<b>Date: </b>" + StringStartDate + " to " + StringEndDate + "<br>"
//                    + "<b>Time: </b>" + StartTime + " to " + EndTime;
//            TextViewdatedurationdata.setText(HtmlCompat.fromHtml(StringDateeDuration, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
//
//
//            SimpleDateFormat input3 = new SimpleDateFormat("MMM/d/y");
//            SimpleDateFormat output3 = new SimpleDateFormat("yMMdd");
//            try {
//                qEndDate = input.parse(date3);
//                StringqEndDate = output3.format(qEndDate);
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }


        //currentdate
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yMMdd");
        StringqCurrentDate = sdf1.format(c.getTime());
        int IntegerCurrentDate = Integer.parseInt(StringqCurrentDate);
        int IntegerEnddate = 0;
        String StringEndDateValidate = getIntent().getStringExtra("eventenddate");
        String StringDateeDuration;
        
        if (StringEndDateValidate.isEmpty()){
            //startdate
            String date1 = getIntent().getStringExtra("eventstartdate");
            SimpleDateFormat input1 = new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat output1 = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat input1validation = new SimpleDateFormat("yMMdd");
            try {
                DateStartDate = input1.parse(date1);
                StringStartDate = output1.format(DateStartDate);
                StringqStartDate = input1validation.format(DateStartDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            IntegerEnddate = Integer.parseInt(StringqStartDate);
            StringDateeDuration = "<b>Date: </b>" + StringStartDate ;
            TextViewdatedurationdata.setText(HtmlCompat.fromHtml(StringDateeDuration, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        }
        if (!StringEndDateValidate.isEmpty()){
            //startdate
            String date1 = getIntent().getStringExtra("eventstartdate");
            SimpleDateFormat input1 = new SimpleDateFormat("M/dd/yyyyy");
            SimpleDateFormat output1 = new SimpleDateFormat("dd MMM yyyy");

            try {
                DateStartDate = input1.parse(date1);
                StringStartDate = output1.format(DateStartDate);



            } catch (ParseException e) {
                e.printStackTrace();
            }

            //enddate
            String date2 = getIntent().getStringExtra("eventenddate");
            SimpleDateFormat input2 = new SimpleDateFormat("M/dd/yyyyy");
            SimpleDateFormat output2 = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat input2validation = new SimpleDateFormat("yMMdd");
            try {
                DateEndDate = input2.parse(date2);
                StringEndDate = output2.format(DateEndDate);
                StringqEndDate = input2validation.format(DateEndDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            IntegerEnddate = Integer.parseInt(StringqEndDate);
            StringDateeDuration = "<b>Date: </b>" + StringStartDate + " to " + StringEndDate;
            TextViewdatedurationdata.setText(HtmlCompat.fromHtml(StringDateeDuration, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));


        }





        //are you interested
        if (IntegerEnddate > IntegerCurrentDate){
            linearLayoutdatevalidation = findViewById(R.id.datevalidationdata);
            String StringCollege =  getIntent().getStringExtra("eventcollege");
            String StringCollegeAlumni = SharedPrefManager.getInstance(this).getCollege();
            //related event
            String splitcollege[] = StringCollege.split(",,,");

            for(int i =0; i<splitcollege.length; i++){

                if (splitcollege[i].equals(StringCollegeAlumni)){
                    linearLayoutdatevalidation.setVisibility(LinearLayout.VISIBLE);
                }

            }


        }


        //Sponsor Organizer
        String StringSponsor = getIntent().getStringExtra("eventsponsor");
        String splitted[] = StringSponsor.split(",,,");
        String StringAllSponsor = "<b>Sponsor</b><br><br>";
        for(int i =0; i<splitted.length; i++){
            StringAllSponsor +=   splitted[i] + "<br>";
        }

        String StringOrganizer = getIntent().getStringExtra("eventorganizer");
        String splitted1[] = StringOrganizer.split(",,,");
        String StringAllOrganizer = "<b>Organizer</b><br><br>";
        for(int i =0; i<splitted1.length; i++){
            StringAllOrganizer +=   splitted1[i] + "<br>";
        }

        TextViewSponsorData.setText(HtmlCompat.fromHtml(StringAllSponsor, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
        TextViewOrganizerData.setText(HtmlCompat.fromHtml(StringAllOrganizer, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        //are you interested
        idno = SharedPrefManager.getInstance(this).getIDno();
        //interested selected
        StringInterestedSelected = "";
        String StringInterestedChecking = getIntent().getStringExtra("eventinterested");
        if (!StringInterestedChecking.isEmpty()) {
            String StringLooping1 = getIntent().getStringExtra("eventinterested");
            String StringLooping1splitted1[] = StringLooping1.split(",");

            for (int i = 0; i < StringLooping1splitted1.length; i++) {
                if (StringLooping1splitted1[i].equals(idno)) {
                    StringInterestedSelected = "Yes";
                    ButtonInterestedSelected.setVisibility(LinearLayout.VISIBLE);
                    ButtonNotInterestedPlain.setVisibility(LinearLayout.VISIBLE);
                }
            }
        }

        //Notinterested selected
        StringNotInterestedSelected = "";
        String StringNotInterestedChecking = getIntent().getStringExtra("eventnotinterested");
        if (!StringNotInterestedChecking.isEmpty()) {
            String StringLooping2 = getIntent().getStringExtra("eventnotinterested");
            String StringLooping1splitted2[] = StringLooping2.split(",");

            for (int i = 0; i < StringLooping1splitted2.length; i++) {
                if (StringLooping1splitted2[i].equals(idno)) {
                    StringNotInterestedSelected = "No";
                    ButtonNotInterestedSelected.setVisibility(LinearLayout.VISIBLE);
                    ButtonInterestedPlain.setVisibility(LinearLayout.VISIBLE);
                }
            }
        }
        if (StringInterestedSelected.isEmpty() && StringNotInterestedSelected.isEmpty()){
            ButtonInterestedPlain.setVisibility(LinearLayout.VISIBLE);
            ButtonNotInterestedPlain.setVisibility(LinearLayout.VISIBLE);

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


    public void SubmitInterested(View view) {


        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        String eventid = getIntent().getStringExtra("eventid");
        String interested = getIntent().getStringExtra("eventinterested");
        String notinterested = getIntent().getStringExtra("eventnotinterested");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_INTERESTED,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), eventinfo.class));
                            finish();


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
                params.put("eventid",eventid);
                params.put("interested",interested);
                params.put("notinterested",notinterested);



                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void SubmitNotInterested(View view) {


        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        String eventid = getIntent().getStringExtra("eventid");
        String interested = getIntent().getStringExtra("eventinterested");
        String notinterested = getIntent().getStringExtra("eventnotinterested");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_NOTINTERESTED,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), eventinfo.class));
                            finish();


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
                params.put("eventid",eventid);
                params.put("interested",interested);
                params.put("notinterested",notinterested);



                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    @Override
    public void onClick(View view) {

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
                Intent intent1 = new Intent(interested.this,alumni.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(interested.this,trendinginfo.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(interested.this,bookmarkinfo.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(interested.this,login.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;


        }

        return true;
    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}