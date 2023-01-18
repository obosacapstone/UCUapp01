package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class otherinfo extends AppCompatActivity  implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
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



    //employed
    RadioGroup RadioGroupqemployedyesno;
    RadioButton RadioButtonqemployedyesno,RadioButtonqemployedyes,RadioButtonqemployedno;
    LinearLayout LinearLayoutqemployed1yes,LinearLayoutqemployed1no;
    //employedy1
    String[] Stringemployedy1 = {"Regular","Temporary","Casual","Contractual","Self-employed"};
    AutoCompleteTextView autoCompleteStringemployedy1;
    ArrayAdapter<String> adapterStringemployedy1;
    //employedy2
    String[] Stringemployedy2 = {"Local","Abroad"};
    AutoCompleteTextView autoCompleteStringemployedy2;
    ArrayAdapter<String> adapterStringemployedy2;
    //employedy3
    String[] Stringemployedy3 = {"Accountant","Cashier","Teacher","Engineer/Architect","Doctor/Nurse/Midwife","Caregiver","Technician","Media","Driver","Lawyer",
            "Entrepreneur","Pharmacist","Police/Military","Waiter/Bartender/Baker","Software Developer","Social Worker","Flight Attendant","Seaman","Fireman","Hair & Make-up Artist"};
    AutoCompleteTextView autoCompleteStringemployedy3;
    ArrayAdapter<String> adapterStringemployedy3;
    //employedy4
    String[] Stringemployedy4 = {"Agriculture, Hunting & Forestry","Construction","Education","Electricty, Gas, & Water Supply","Extra-Territorial Organizations & Bodies","Financial INtermidiation","Fishing","Health & Social Works","Hotels & Restaurants",
            "Manufacturing","Mining & Quarrying","Other Community, Social & Personal Service Activities","Private Household with Employed Persons","Public Administration & Defense; Compulsory Social Security","Real State, Renting and Business Activities","Transport Storage & Communication",
            "Wholesale & Retail Trade, Repair of motor vehicles, motorcycles&\n" +
                    "\t\t  personal household goods"};
    AutoCompleteTextView autoCompleteStringemployedy4;
    ArrayAdapter<String> adapterStringemployedy4;
    //employedy5
    String[] Stringemployedy5 = {"Yes","No"};
    AutoCompleteTextView autoCompleteStringemployedy5;
    ArrayAdapter<String> adapterStringemployedy5;
    //employedyn1
    String[] Stringemployedn1 = {"Advance or further study","Family concern & decided not to find a job","Health-related reason(s)","Lack of work experience","No job opportunity","Did not look for a job"};
    AutoCompleteTextView autoCompleteStringemployedn1;
    ArrayAdapter<String> adapterStringemployedn1;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otherinfo);
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Update Information");

        //nav
        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);

        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();



        Glide.with(otherinfo.this).load(graduatedimage).into(ImageViewNavProfile);
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






        String region = SharedPrefManager.getInstance(this).getRegion();
        String province = SharedPrefManager.getInstance(this).getProvince();
        String city = SharedPrefManager.getInstance(this).getCity();
        String barangay = SharedPrefManager.getInstance(this).getBarangay();
        String street = SharedPrefManager.getInstance(this).getStreet();

        String employed = SharedPrefManager.getInstance(this).getEmployed();
        String employedy1 = SharedPrefManager.getInstance(this).getEmployedy1();
        String employedy2 = SharedPrefManager.getInstance(this).getEmployedy2();
        String employedy3 = SharedPrefManager.getInstance(this).getEmployedy3();
        String employedy4 = SharedPrefManager.getInstance(this).getEmployedy4();
        String employedy5 = SharedPrefManager.getInstance(this).getEmployedy5();
        String employedn1 = SharedPrefManager.getInstance(this).getEmployedn1();
        String firstjob = SharedPrefManager.getInstance(this).getFirstjob();
        String firstjoby1 = SharedPrefManager.getInstance(this).getFirstjoby1();
        String firstjoby2 = SharedPrefManager.getInstance(this).getFirstjoby2();
        String firstjoby3 = SharedPrefManager.getInstance(this).getFirstjoby3();
        String firstjoby4 = SharedPrefManager.getInstance(this).getFirstjoby4();
        String firstjoby4y1 = SharedPrefManager.getInstance(this).getFirstjoby4y1();
        //civilstatus



        //postgraduate

        if (employed.equals("Yes")){
            RadioButtonqemployedyes.setChecked(true);
            LinearLayoutqemployed1yes.setVisibility(LinearLayout.VISIBLE);
            LinearLayoutqemployed1no.setVisibility(LinearLayout.GONE);
            //employedy1
            autoCompleteStringemployedy1.setText(employedy1,false);
            //employedy2
            autoCompleteStringemployedy2.setText(employedy2,false);
            //employedy3
            autoCompleteStringemployedy3.setText(employedy3,false);
            //employedy4
            autoCompleteStringemployedy4.setText(employedy4,false);
            //employedy5
            autoCompleteStringemployedy5.setText(employedy5,false);
        }
        if (employed.equals("No")){
            RadioButtonqemployedno.setChecked(true);
            autoCompleteStringemployedn1.setText(employedn1,false);
            LinearLayoutqemployed1yes.setVisibility(LinearLayout.GONE);
            LinearLayoutqemployed1no.setVisibility(LinearLayout.VISIBLE);

        }





    }




    public void checkButtonEmployed(View v) {
        //employed
        int radioIDemployed = RadioGroupqemployedyesno.getCheckedRadioButtonId();
        RadioButtonqemployedyesno = findViewById(radioIDemployed);
        String StringRadioButtonqemployedyesno = RadioButtonqemployedyesno.getText().toString();

        if (StringRadioButtonqemployedyesno.equals("Yes")){
            LinearLayoutqemployed1yes.setVisibility(LinearLayout.VISIBLE);
            LinearLayoutqemployed1no.setVisibility(LinearLayout.GONE);
        }
        if (StringRadioButtonqemployedyesno.equals("No")){
            LinearLayoutqemployed1yes.setVisibility(LinearLayout.GONE);
            LinearLayoutqemployed1no.setVisibility(LinearLayout.VISIBLE);
        }
    }


    private void updateUser(){

        String idno,postgraduate ,postgraduatey1, postgraduatey2;
        String employed,employedy1,employedy2,employedy3,employedy4,employedy5,employedn1;
        String firstjob,firstjoby1,firstjoby2,firstjoby3,firstjoby4,firstjoby4y1;


        idno = SharedPrefManager.getInstance(this).getIDno();
        String idnumber = SharedPrefManager.getInstance(this).getIDno();

        //employed
        RadioButton Checkqemployed = findViewById(RadioGroupqemployedyesno.getCheckedRadioButtonId());
        employed = Checkqemployed.getText().toString();
        employedy1 = String.valueOf(autoCompleteStringemployedy1.getText()).trim();
        employedy2 = String.valueOf(autoCompleteStringemployedy2.getText()).trim();
        employedy3 = String.valueOf(autoCompleteStringemployedy3.getText()).trim();
        employedy4 = String.valueOf(autoCompleteStringemployedy4.getText()).trim();
        employedy5 = String.valueOf(autoCompleteStringemployedy5.getText()).trim();
        employedn1 = String.valueOf(autoCompleteStringemployedn1.getText()).trim();







        //employed
        if (employed.isEmpty()){
            RadioButtonqemployedno.setError("Please fill up");
            RadioButtonqemployedno.requestFocus();
        }else{
            RadioButtonqemployedno.setError(null);
        }
        if(employed.equals("Yes")) {
            if (employedy1.isEmpty()){
                autoCompleteStringemployedy1.setError("Please fill up");
                autoCompleteStringemployedy1.requestFocus();
            }else{
                autoCompleteStringemployedy1.setError(null);
            }
            if (employedy2.isEmpty()){
                autoCompleteStringemployedy2.setError("Please fill up");
                autoCompleteStringemployedy2.requestFocus();
            }else{
                autoCompleteStringemployedy2.setError(null);
            }
            if (employedy3.isEmpty()){
                autoCompleteStringemployedy3.setError("Please fill up");
                autoCompleteStringemployedy3.requestFocus();
            }else{
                autoCompleteStringemployedy3.setError(null);
            }
            if (employedy4.isEmpty()){
                autoCompleteStringemployedy4.setError("Please fill up");
                autoCompleteStringemployedy4.requestFocus();
            }else{
                autoCompleteStringemployedy4.setError(null);
            }
            if (employedy5.isEmpty()){
                autoCompleteStringemployedy5.setError("Please fill up");
                autoCompleteStringemployedy5.requestFocus();
            }else{
                autoCompleteStringemployedy5.setError(null);
            }





        }
        if(employed.equals("No")) {
            if (employedn1.isEmpty()){
                autoCompleteStringemployedn1.setError("Please fill up");
                autoCompleteStringemployedn1.requestFocus();
            }else{
                autoCompleteStringemployedn1.setError(null);
            }

        }

        progressDialog = new ProgressDialog(otherinfo.this);
        progressDialog.setMessage("Please wait....");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_OTHERINFO,
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
                params.put("employed",employed);
                params.put("employedy1",employedy1);
                params.put("employedy2",employedy2);
                params.put("employedy3",employedy3);
                params.put("employedy4",employedy4);
                params.put("employedy5",employedy5);
                params.put("employedn1",employedn1);

                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

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
                Intent intent1 = new Intent(otherinfo.this,courseinfo.class);
                startActivity(intent1);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(otherinfo.this,trendinginfo.class);
                startActivity(intent2);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(otherinfo.this,bookmarkinfo.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(otherinfo.this,login.class);
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