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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.android.material.navigation.NavigationView;
import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class advancestudy extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
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

    //postgraduate
    RadioGroup RadioGroupqpostgraduateyesno;
    RadioButton RadioButtonqpostgraduatedyesno,RadioButtonqpostgraduatedyes,RadioButtonqpostgraduatedno;
    LinearLayout LinearLayoutqpostgraduate1yes;
    //postgraduateyes1
    String[] Stringpostgraduateyes1 = {"Doctoral Degree Holder","with Doctoral Degree units","Masteral Degree Holder","with Masteral Degree units"};
    AutoCompleteTextView autoCompleteStringpostgraduateyes1;
    ArrayAdapter<String> adapterStringpostgraduateyes1;
    //postgraduateyes2
    String[] Stringpostgraduateyes2 = {"For promotion","For professional Development"};
    AutoCompleteTextView autoCompleteStringpostgraduateyes2;
    ArrayAdapter<String> adapterStringpostgraduateyes2;

    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advancestudy);
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        //motherpostgraduate
        RadioGroupqpostgraduateyesno = findViewById(R.id.qpostgraduateyesno);
        LinearLayoutqpostgraduate1yes = findViewById(R.id.qpostgraduate1yes);
        //postgraduate
        RadioButtonqpostgraduatedyes = findViewById(R.id.qpostgraduateyes);
        RadioButtonqpostgraduatedno = findViewById(R.id.qpostgraduateno);
        //postgraduateyes1
        autoCompleteStringpostgraduateyes1 = findViewById(R.id.selectqpostgraduate1yes1);
        adapterStringpostgraduateyes1 = new ArrayAdapter<String>(this,R.layout.list_item,Stringpostgraduateyes1);
        autoCompleteStringpostgraduateyes1.setAdapter(adapterStringpostgraduateyes1);
        //postgraduateyes2
        autoCompleteStringpostgraduateyes2 = findViewById(R.id.selectqpostgraduate1yes2);
        adapterStringpostgraduateyes2 = new ArrayAdapter<String>(this,R.layout.list_item,Stringpostgraduateyes2);
        autoCompleteStringpostgraduateyes2.setAdapter(adapterStringpostgraduateyes2);


        BtnSubmit = findViewById(R.id.submit);
        progressDialog=new ProgressDialog(this);

        BtnSubmit.setOnClickListener(this);


        String postgraduate = SharedPrefManager.getInstance(this).getPostgradaute();
        String postgraduatey1 = SharedPrefManager.getInstance(this).getPostgradautey1();
        String postgraduatey2 = SharedPrefManager.getInstance(this).getPostgradautey2();

        if (postgraduate.equals("Yes")){
            RadioButtonqpostgraduatedyes.setChecked(true);
            LinearLayoutqpostgraduate1yes.setVisibility(LinearLayout.VISIBLE);
            //postgraduatey1
            autoCompleteStringpostgraduateyes1.setText(postgraduatey1,false);
            //postgraduatey2
            autoCompleteStringpostgraduateyes2.setText(postgraduatey2,false);
        }
        if (postgraduate.equals("No")){
            RadioButtonqpostgraduatedno.setChecked(true);
            LinearLayoutqpostgraduate1yes.setVisibility(LinearLayout.GONE);
        }

    }
    public void checkButtonPostGraduate(View v){
        //postgraduate
        int radioID = RadioGroupqpostgraduateyesno.getCheckedRadioButtonId();
        RadioButtonqpostgraduatedyesno = findViewById(radioID);
        String StringRadioButtonqpostgraduatedyesno = RadioButtonqpostgraduatedyesno.getText().toString();

        if (StringRadioButtonqpostgraduatedyesno.equals("Yes")){
            LinearLayoutqpostgraduate1yes.setVisibility(LinearLayout.VISIBLE);
        }
        if (StringRadioButtonqpostgraduatedyesno.equals("No")){
            LinearLayoutqpostgraduate1yes.setVisibility(LinearLayout.GONE);
        }


    }
    private void updateUser(){

        String idno,postgraduate ,postgraduatey1, postgraduatey2;

        idno = SharedPrefManager.getInstance(this).getIDno();
        String idnumber = SharedPrefManager.getInstance(this).getIDno();
        //postgraduate
        RadioButton Checkqpostgraduate = findViewById(RadioGroupqpostgraduateyesno.getCheckedRadioButtonId());
        postgraduate = Checkqpostgraduate.getText().toString();
        postgraduatey1 = String.valueOf(autoCompleteStringpostgraduateyes1.getText()).trim();
        postgraduatey2 = String.valueOf(autoCompleteStringpostgraduateyes2.getText()).trim();





        //postgraduate
        if(postgraduate.isEmpty()){
            RadioButtonqpostgraduatedno.setError("Please fill up");
            RadioButtonqpostgraduatedno.requestFocus();

        }else{
            RadioButtonqpostgraduatedno.setError(null);
        }
        if(postgraduate.equals("Yes")) {

            if(postgraduatey1.isEmpty()){
                autoCompleteStringpostgraduateyes1.setError("Please fill up");
                autoCompleteStringpostgraduateyes1.requestFocus();
            }else{
                autoCompleteStringpostgraduateyes1.setError(null);
            }
            if(postgraduatey2.isEmpty()){
                autoCompleteStringpostgraduateyes2.setError("Please fill up");
                autoCompleteStringpostgraduateyes2.requestFocus();
            }else{
                autoCompleteStringpostgraduateyes2.setError(null);
            }

        }




        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_ADVANCESTUDY,
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


                            }
                            progressBar.setVisibility(View.GONE);
                            relativeLayoutProgressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), profile.class));
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
                })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idno",idno);
                params.put("idno",idnumber);
                params.put("postgraduate",postgraduate);
                params.put("postgraduatey1",postgraduatey1);
                params.put("postgraduatey2",postgraduatey2);

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
                Intent intent1 = new Intent(advancestudy.this,alumni.class);
                startActivity(intent1);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(advancestudy.this,trendinginfo.class);
                startActivity(intent2);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(advancestudy.this,bookmarkinfo.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(advancestudy.this,login.class);
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