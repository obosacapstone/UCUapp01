package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;
public class generalinfo extends AppCompatActivity  implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
    ProgressDialog progressDialog;
    Button BtnSubmit;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mtoggle=null,mytoggle=null;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout mydrawer;
    TextView TextViewNavFullname, TextViewNavIdno;

    ImageView ImageViewNavProfile;
    NavigationView navigationView;
    TextView TextViewTitleNav;


    Switch Switchnotificationjob;
    String Stringnotificationjob;

    Switch Switchnotificationnews;
    String Stringnotificationnews;

    Switch Switchnotificationevent;
    String Stringnotificationevent;
    //civilstatus
    String[] Stringcivilstatus = {"Single","Married","Separated","Single Parent","Widow/er"};
    AutoCompleteTextView autoCompleteStringcivilstatus;
    ArrayAdapter<String> adapterStringcivilstatus;
    //contact number
    EditText EditTextcontact ;
    //email
    EditText EditTextemail ;
    //facebook instagra
    EditText EditFacebook ;
    EditText EditInstagram ;
    //skills
    TextView TextViewskill;
    boolean[] selectedskill;
    ArrayList<Integer> skilllist = new ArrayList<>();
    String[] StringskillArray = {"Accounting/Finance & Benifits","Admin/Human Resources","Sales/Marketing","Arts/Media/Communications","Services","Hotel/Restaurant","Education/Training",
            "Computer/Information Technology","Engineering","Manufacturing","Building/Construction","Sciences","Healthcare","Journalst/Editors","General Work","Publishing"};
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generalinfo);
//        TextViewTitleNav = findViewById(R.id.titlenav);
//        TextViewTitleNav.setText("Update Information");

        //nav
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


        Glide.with(generalinfo.this).load(graduatedimage).into(ImageViewNavProfile);
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

        //notificationjob
        Switchnotificationjob = findViewById(R.id.notificationjob);
        Switchnotificationjob.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    Stringnotificationjob = "Yes";
//                    Switchnotificationjob.setText(Stringnotificationjob);
                }
                else{
                    Stringnotificationjob = "No";
                }
            }
        });
        //notification news
        Switchnotificationnews = findViewById(R.id.notificationnews);
        Switchnotificationnews.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    Stringnotificationnews = "Yes";

                }
                else{
                    Stringnotificationnews = "No";
                }
            }
        });
        //notification event
        Switchnotificationevent = findViewById(R.id.notificationevent);
        Switchnotificationevent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    Stringnotificationevent = "Yes";
//                    Switchnotificationjob.setText(Stringnotificationjob);
                }
                else{
                    Stringnotificationevent = "No";
                }
            }
        });
        //email
        EditTextemail = findViewById(R.id.email);
        //contact
        EditTextcontact = findViewById(R.id.contact);
        //civil status
        autoCompleteStringcivilstatus = findViewById(R.id.selectqcivilstatus);
        adapterStringcivilstatus = new ArrayAdapter<String>(this,R.layout.list_item,Stringcivilstatus);
        autoCompleteStringcivilstatus.setAdapter(adapterStringcivilstatus);
        //skills
        TextViewskill = findViewById(R.id.selectskill);
        selectedskill = new boolean[StringskillArray.length];
        TextViewskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        generalinfo.this
                );
                builder.setTitle("Select Item");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(StringskillArray, selectedskill, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b){
                            skilllist.add(i);
                            Collections.sort(skilllist);
                        }else{
                            for (int j=0;j<skilllist.size();j++) {
                                if (skilllist.get(j) == i) {
                                    skilllist.remove(j);
                                }



                            }
                        }
                    }
                });
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j<skilllist.size(); j++){
                            stringBuilder.append(StringskillArray[skilllist.get(j)]);
                            if (j != skilllist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        TextViewskill.setText(stringBuilder.toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();
            }
        });
        //facebook instagram
        EditFacebook = findViewById(R.id.facebook);
        EditInstagram = findViewById(R.id.instagram);

        BtnSubmit = findViewById(R.id.submit);
        progressDialog=new ProgressDialog(this);

        BtnSubmit.setOnClickListener(this);


        String civilstatus = SharedPrefManager.getInstance(this).getCivilstatus();
        String contact = SharedPrefManager.getInstance(this).getContact();
        String email = SharedPrefManager.getInstance(this).getEmail();
        String specialization = SharedPrefManager.getInstance(this).getSpecialization();
        String facebook = SharedPrefManager.getInstance(this).getFacebook();
        String instagram = SharedPrefManager.getInstance(this).getInstagram();
        String notification = SharedPrefManager.getInstance(this).getNotification();
        String newsnotification = SharedPrefManager.getInstance(this).getNewsnotification();
        String eventnotification = SharedPrefManager.getInstance(this).getEventnotification();


        if (notification.equals("Yes")){
            Switchnotificationjob.setChecked(true);
        }
        if (newsnotification.equals("Yes")){
            Switchnotificationnews.setChecked(true);
        }
        if (eventnotification.equals("Yes")){
            Switchnotificationevent.setChecked(true);
        }

        autoCompleteStringcivilstatus.setText(civilstatus,false);
        EditTextcontact.setText(contact);
        EditTextemail.setText(email);
        TextViewskill.setText(specialization);
        EditFacebook.setText(facebook);
        EditInstagram.setText(instagram);

    }


    private void updateUser() {
        String civilstatus,contact,email,skills,notificationjob,notificationnews,notificationevent,facebook,instagram;
        String idno;
        skills = String.valueOf(TextViewskill.getText()).trim();
        contact = String.valueOf(EditTextcontact.getText()).trim();
        civilstatus = String.valueOf(autoCompleteStringcivilstatus.getText()).trim();
        facebook = String.valueOf(EditFacebook.getText()).trim();
        instagram = String.valueOf(EditInstagram.getText()).trim();
        email = String.valueOf(EditTextemail.getText()).trim();
        idno = SharedPrefManager.getInstance(this).getIDno();
        String idnumber = SharedPrefManager.getInstance(this).getIDno();

        //information
        Switchnotificationjob.setText(Stringnotificationjob);
        notificationjob = String.valueOf(Switchnotificationjob.getText()).trim();
        Switchnotificationnews.setText(Stringnotificationnews);
        notificationnews = String.valueOf(Switchnotificationnews.getText()).trim();
        Switchnotificationevent.setText(Stringnotificationevent);
        notificationevent = String.valueOf(Switchnotificationevent.getText()).trim();



        if(civilstatus.isEmpty()){
            autoCompleteStringcivilstatus.setError("Please fill up");
            autoCompleteStringcivilstatus.requestFocus();
        }else{
            autoCompleteStringcivilstatus.setError(null);
        }
        if (contact.length() != 11){
            EditTextcontact.setError("Please Enter 11 Digit");
            EditTextcontact.requestFocus();
        }else{
            EditTextcontact.setError(null);
        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern)){
            EditTextemail.setError("Please Enter Valid Email");
            EditTextemail.requestFocus();
        }else{
            EditTextemail.setError(null);
        }
        if (skills.isEmpty()){
            TextViewskill.setError("Please select");
            TextViewskill.requestFocus();
        }else{
            TextViewskill.setError(null);
        }

        if(!contact.isEmpty() && !skills.isEmpty() && email.matches(emailPattern)){


            progressBar.setVisibility(View.VISIBLE);
            relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_GENERALINFO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                            Log.e("anyText",response);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                })
              {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idno",idno);
                params.put("idno",idnumber);
                params.put("notificationjob",notificationjob);
                params.put("notificationnews",notificationnews);
                params.put("notificationevent",notificationevent);
                params.put("civilstatus",civilstatus);
                params.put("contact",contact);
                params.put("email",email);
                params.put("skills",skills);
                params.put("facebook",facebook);
                params.put("instagram",instagram);

                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        if (view == BtnSubmit)
            updateUser();
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
                Intent intent1 = new Intent(generalinfo.this,courseinfo.class);
                startActivity(intent1);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(generalinfo.this,trendinginfo.class);
                startActivity(intent2);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(generalinfo.this,bookmarkinfo.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(generalinfo.this,login.class);
                startActivity(intent5);
                break;



        }

        return true;
    }

    public void back(View view) {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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