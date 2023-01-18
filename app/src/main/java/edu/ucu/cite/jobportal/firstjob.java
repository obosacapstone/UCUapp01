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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;
public class firstjob extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
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

    //firstjob
    RadioGroup RadioGroupqfirstjobyesno;
    RadioButton RadioButtonqfirstjobyesno,RadioButtonqfirstjobyes,RadioButtonqfirstjobno;
    LinearLayout LinearLayoutqfirstjob1yes;
    RadioGroup RadioGroupqfirstjobyesno4;
    RadioButton RadioButtonqfirstjobyesno4,RadioButtonqfirstjobyes4,RadioButtonqfirstjobno4;
    LinearLayout LinearLayoutqfirstjob1yes4;
    //firstjobyes1
    String[] Stringfirstjobyes1 = {"Response to an advertisement","As walk-in applicant","Recommended by someone","Information from some friendes","Family Business","Job Fair/ Public Employment Service Office (PESO)"};
    AutoCompleteTextView autoCompleteStringfirstjobyes1;
    ArrayAdapter<String> adapterStringfirstjobyes1;
    //firstjobyes2
    TextView TextViewfirstjobyes2;
    boolean[] selectedfirstjobyes2;
    ArrayList<Integer> firstjobyes2list = new ArrayList<>();
    String[] Stringfirstjobyes2Array = {"Salaries & Benifits","Career Challenge","Related to special skills"};
    //firstjobyes3
    String[] Stringfirstjobyes3 = {"Rank or Clerical","Professional, Technical or Supervisory","Managerial/ Executive","Self-employed"};
    AutoCompleteTextView autoCompleteStringfirstjobyes3;
    ArrayAdapter<String> adapterStringfirstjobyes3;
    //firstjobyes4y1
    TextView TextViewfirstjobyes4y1;
    boolean[] selectedfirstjobyes4y1;
    ArrayList<Integer> firstjobyes4y1list = new ArrayList<>();
    String[] Stringfirstjobyes4y1Array = {"Communication Skills","Human Relation Skills","Entrepreneural Skills","Information Technology Skills","Problem-solving Skills","Critical Thinking Skills"};
    //firstjobyes5
    String[] Stringfirstjobyes5 = {"Below P5,000.00","P5.000.00 to less than P10,000.00","P10,000.00 to less than P15,000.00","P15.000.00 to less than P20,000.00","P20,000.00 to less than P25,000.00","P25,000.00 and above"};
    AutoCompleteTextView autoCompleteStringfirstjobyes5;
    ArrayAdapter<String> adapterStringfirstjobyes5;
    //firstjobyes6
    String[] Stringfirstjobyes6 = {"1 to 6 months","7 to 11 months","1 year to less than 2 years","2 years to less than 3 years","3 years to less than 4 years","4 years above"};
    AutoCompleteTextView autoCompleteStringfirstjobyes6;
    ArrayAdapter<String> adapterStringfirstjobyes6;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstjob);
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        //motherfirstjob
        RadioGroupqfirstjobyesno = findViewById(R.id.qfirstjobyesno);
        LinearLayoutqfirstjob1yes = findViewById(R.id.qfirstjob1yes);
        RadioGroupqfirstjobyesno4 = findViewById(R.id.qfirstjobyesno4);
        LinearLayoutqfirstjob1yes4 = findViewById(R.id.qfirstjob4yes);
        //firstjob
        RadioButtonqfirstjobyes = findViewById(R.id.qfirstjobyes);
        RadioButtonqfirstjobno = findViewById(R.id.qfirstjobno);
        //firstjobyes1
        autoCompleteStringfirstjobyes1 = findViewById(R.id.selectqfirstjob1yes1);
        adapterStringfirstjobyes1 = new ArrayAdapter<String>(this,R.layout.list_item,Stringfirstjobyes1);
        autoCompleteStringfirstjobyes1.setAdapter(adapterStringfirstjobyes1);
        //firstjobyes2
        TextViewfirstjobyes2 = findViewById(R.id.selectqfirstjob1yes2);
        selectedfirstjobyes2 = new boolean[Stringfirstjobyes2Array.length];
        TextViewfirstjobyes2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        firstjob.this
                );
                builder.setTitle("Select Item");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(Stringfirstjobyes2Array, selectedfirstjobyes2, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b){
                            firstjobyes2list.add(i);
                            Collections.sort(firstjobyes2list);
                        }else{
                            for (int j=0;j<firstjobyes2list.size();j++) {
                                if (firstjobyes2list.get(j) == i) {
                                    firstjobyes2list.remove(j);
                                }
                            }
                        }
                    }
                });
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j<firstjobyes2list.size(); j++){
                            stringBuilder.append(Stringfirstjobyes2Array[firstjobyes2list.get(j)]);
                            if (j != firstjobyes2list.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        TextViewfirstjobyes2.setText(stringBuilder.toString());
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
        //firstjobyes3
        autoCompleteStringfirstjobyes3 = findViewById(R.id.selectqfirstjob1yes3);
        adapterStringfirstjobyes3 = new ArrayAdapter<String>(this,R.layout.list_item,Stringfirstjobyes3);
        autoCompleteStringfirstjobyes3.setAdapter(adapterStringfirstjobyes3);
        //firstjobyes4
        RadioButtonqfirstjobyes4 = findViewById(R.id.qfirstjobyes4);
        RadioButtonqfirstjobno4 = findViewById(R.id.qfirstjobno4);
        //firstjobyes4y1
        TextViewfirstjobyes4y1 = findViewById(R.id.selectqfirstjob1yes4y1);
        selectedfirstjobyes4y1 = new boolean[Stringfirstjobyes4y1Array.length];
        TextViewfirstjobyes4y1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        firstjob.this
                );
                builder.setTitle("Select Item");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(Stringfirstjobyes4y1Array, selectedfirstjobyes4y1, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b){
                            firstjobyes4y1list.add(i);
                            Collections.sort(firstjobyes4y1list);
                        }else{
                            for (int j=0;j<firstjobyes4y1list.size();j++) {
                                if (firstjobyes4y1list.get(j) == i) {
                                    firstjobyes4y1list.remove(j);
                                }
                            }
                        }
                    }
                });
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j=0; j<firstjobyes4y1list.size(); j++){
                            stringBuilder.append(Stringfirstjobyes4y1Array[firstjobyes4y1list.get(j)]);
                            if (j != firstjobyes4y1list.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        TextViewfirstjobyes4y1.setText(stringBuilder.toString());
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
        //firstjobyes5
        autoCompleteStringfirstjobyes5 = findViewById(R.id.selectqfirstjob1yes5);
        adapterStringfirstjobyes5 = new ArrayAdapter<String>(this,R.layout.list_item,Stringfirstjobyes5);
        autoCompleteStringfirstjobyes5.setAdapter(adapterStringfirstjobyes5);
        //firstjobyes6
        autoCompleteStringfirstjobyes6 = findViewById(R.id.selectqfirstjob1yes6);
        adapterStringfirstjobyes6 = new ArrayAdapter<String>(this,R.layout.list_item,Stringfirstjobyes6);
        autoCompleteStringfirstjobyes6.setAdapter(adapterStringfirstjobyes6);

        BtnSubmit = findViewById(R.id.submit);
        progressDialog=new ProgressDialog(this);

        BtnSubmit.setOnClickListener(this);
        String firstjob = SharedPrefManager.getInstance(this).getFirstjob();
        String firstjoby1 = SharedPrefManager.getInstance(this).getFirstjoby1();
        String firstjoby2 = SharedPrefManager.getInstance(this).getFirstjoby2();
        String firstjoby3 = SharedPrefManager.getInstance(this).getFirstjoby3();
        String firstjoby4 = SharedPrefManager.getInstance(this).getFirstjoby4();
        String firstjoby4y1 = SharedPrefManager.getInstance(this).getFirstjoby4y1();
        String firstjoby5 = SharedPrefManager.getInstance(this).getFirstjoby5();
        String firstjoby6 = SharedPrefManager.getInstance(this).getFirstjoby6();

        if (firstjob.equals("Yes")){
            RadioButtonqfirstjobyes.setChecked(true);
            LinearLayoutqfirstjob1yes.setVisibility(LinearLayout.VISIBLE);
            //employedy1
            autoCompleteStringfirstjobyes1.setText(firstjoby1,false);
            //employedy2
            TextViewfirstjobyes2.setText(firstjoby2);
            //employedy3
            autoCompleteStringfirstjobyes3.setText(firstjoby3,false);
            //employedy4
            RadioButtonqfirstjobyes4.setChecked(true);
            //employedy5
            autoCompleteStringfirstjobyes5.setText(firstjoby5,false);
            //employedy6
            autoCompleteStringfirstjobyes6.setText(firstjoby6,false);

        }
        if (firstjob.equals("No")) {
            RadioButtonqfirstjobno.setChecked(true);
            LinearLayoutqfirstjob1yes.setVisibility(LinearLayout.GONE);
            LinearLayoutqfirstjob1yes4.setVisibility(LinearLayout.GONE);
        }
        if (firstjoby4.equals("Yes")){
            TextViewfirstjobyes4y1.setText(firstjoby4y1);
            LinearLayoutqfirstjob1yes4.setVisibility(LinearLayout.VISIBLE);
        }
        if (firstjoby4.equals("No")){
            RadioButtonqfirstjobno4.setChecked(true);
            LinearLayoutqfirstjob1yes4.setVisibility(LinearLayout.GONE);

        }
    }
    public void checkButtonFirstJob(View v) {
        //employed
        int radioIDfirstjob = RadioGroupqfirstjobyesno.getCheckedRadioButtonId();

        RadioButtonqfirstjobyesno = findViewById(radioIDfirstjob);

        String StringRadioButtonqfirstjobyesno = RadioButtonqfirstjobyesno.getText().toString();

        if (StringRadioButtonqfirstjobyesno.equals("Yes")){
            LinearLayoutqfirstjob1yes.setVisibility(LinearLayout.VISIBLE);
        }
        if (StringRadioButtonqfirstjobyesno.equals("No")){
            LinearLayoutqfirstjob1yes.setVisibility(LinearLayout.GONE);
            LinearLayoutqfirstjob1yes4.setVisibility(LinearLayout.GONE);
        }
    }
    public void checkButtonFirstJobY4(View v) {
        //employed
        int radioIDfirstjoby4 = RadioGroupqfirstjobyesno4.getCheckedRadioButtonId();
        RadioButtonqfirstjobyesno4 = findViewById(radioIDfirstjoby4);

        String StringRadioButtonqfirstjobyesno4 = RadioButtonqfirstjobyesno4.getText().toString();

        if (StringRadioButtonqfirstjobyesno4.equals("Yes")){
            LinearLayoutqfirstjob1yes4.setVisibility(LinearLayout.VISIBLE);
        }
        if (StringRadioButtonqfirstjobyesno4.equals("No")){
            LinearLayoutqfirstjob1yes4.setVisibility(LinearLayout.GONE);
        }
    }


    private void updateUser(){

        String idno;

        String firstjob,firstjoby1,firstjoby2,firstjoby3,firstjoby4,firstjoby4y1,firstjoby5,firstjoby6;


        idno = SharedPrefManager.getInstance(this).getIDno();
        String idnumber = SharedPrefManager.getInstance(this).getIDno();


        //firstjob
        RadioButton Checkqfirstjob = findViewById(RadioGroupqfirstjobyesno.getCheckedRadioButtonId());
        firstjob = Checkqfirstjob.getText().toString();
        firstjoby1 = String.valueOf(autoCompleteStringfirstjobyes1.getText()).trim();
        firstjoby2 = String.valueOf(TextViewfirstjobyes2.getText()).trim();
        firstjoby3 = String.valueOf(autoCompleteStringfirstjobyes3.getText()).trim();
        RadioButton Checkqfirstjob4 = findViewById(RadioGroupqfirstjobyesno4.getCheckedRadioButtonId());
        firstjoby4 = Checkqfirstjob4.getText().toString();
        firstjoby4y1 = String.valueOf(TextViewfirstjobyes4y1.getText()).trim();
        firstjoby5 = String.valueOf(autoCompleteStringfirstjobyes5.getText()).trim();
        firstjoby6 = String.valueOf(autoCompleteStringfirstjobyes6.getText()).trim();








        //firstjob
        if (firstjob.isEmpty()){
            RadioButtonqfirstjobno.setError("Please fill up");
            RadioButtonqfirstjobno.requestFocus();
        }else{
            RadioButtonqfirstjobno.setError(null);
        }
        if (firstjob.equals("Yes")){
            if (firstjoby1.isEmpty()){
                autoCompleteStringfirstjobyes1.setError("Please fill up");
                autoCompleteStringfirstjobyes1.requestFocus();
            }else{
                autoCompleteStringfirstjobyes1.setError(null);
            }
            if (firstjoby2.isEmpty()){
                TextViewfirstjobyes2.setError("Please fill up");
                TextViewfirstjobyes2.requestFocus();
            }else{
                TextViewfirstjobyes2.setError(null);
            }
            if (firstjoby3.isEmpty()){
                autoCompleteStringfirstjobyes3.setError("Please fill up");
                autoCompleteStringfirstjobyes3.requestFocus();
            }else{
                autoCompleteStringfirstjobyes3.setError(null);
            }


            if (firstjoby4.isEmpty()){
                RadioButtonqfirstjobno4.setError("Please fill up");
                RadioButtonqfirstjobno4.requestFocus();
            }else{
                RadioButtonqfirstjobno4.setError(null);
            }
            if (firstjoby4y1.isEmpty()){
                TextViewfirstjobyes4y1.setError("Please fill up");
                TextViewfirstjobyes4y1.requestFocus();
            }else{
                TextViewfirstjobyes4y1.setError(null);
            }

            if (firstjoby5.isEmpty()){
                autoCompleteStringfirstjobyes5.setError("Please fill up");
                autoCompleteStringfirstjobyes5.requestFocus();
            }else{
                autoCompleteStringfirstjobyes5.setError(null);
            }

            if (firstjoby6.isEmpty()){
                autoCompleteStringfirstjobyes6.setError("Please fill up");
                autoCompleteStringfirstjobyes6.requestFocus();
            }else{
                autoCompleteStringfirstjobyes6.setError(null);
            }


        }


        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_FIRSTJOB,
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
                params.put("firstjob",firstjob);
                params.put("firstjoby1",firstjoby1);
                params.put("firstjoby2",firstjoby2);
                params.put("firstjoby3",firstjoby3);
                params.put("firstjoby4",firstjoby4);
                params.put("firstjoby4y1",firstjoby4y1);
                params.put("firstjoby5",firstjoby5);
                params.put("firstjoby6",firstjoby6);

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
                Intent intent1 = new Intent(firstjob.this,courseinfo.class);
                startActivity(intent1);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(firstjob.this,trendinginfo.class);
                startActivity(intent2);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(firstjob.this,bookmarkinfo.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(firstjob.this,login.class);
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