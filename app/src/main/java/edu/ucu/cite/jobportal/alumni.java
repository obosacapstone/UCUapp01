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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class alumni extends AppCompatActivity{
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
    TextView TextViewTitleNav,TextViewCourse;

    String idno,yeargrad1,selectedcourse;
    String alumni = "";
    int courseoutput;
    TextView TextViewAlumni,TextViewAlumni1,TextViewBatch,TextViewBatch1;
    String OutputBatch,OutputBatch1,StringTitleLength,StringTitleOverflow;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    Button ButtonTitleJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        TextViewAlumni = findViewById(R.id.alumni);
        TextViewBatch = findViewById(R.id.batch);
        TextViewBatch1 = findViewById(R.id.batch1);
        TextViewAlumni = findViewById(R.id.alumni);
        TextViewAlumni1 = findViewById(R.id.alumni1);

        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);




        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();






        ButtonTitleJob = findViewById(R.id.TitleCourse);
        StringTitleLength =  getIntent().getStringExtra("course");
        StringTitleOverflow = getSafeSubstring(getIntent().getStringExtra("course"), 17);
        ButtonTitleJob.setText(StringTitleOverflow + "...");







        String listalumni = SharedPrefManagerAlumni.getInstance(this).getAlumni();


        String yeargrad = SharedPrefManager.getInstance(this).getYeargrad();
        String StringOutput = "", StringAlumniName = "", finaloutput = "";

        selectedcourse = getIntent().getStringExtra("course");






            String splityeargrad[] = listalumni.split(",/,/,/");


            String splitcourses[] = splityeargrad[1].split("/&/&/");

            for(int ii =0; ii<splitcourses.length; ii++) {


                String splitalumnilist[] = splitcourses[ii].split("///");
                for (int iii = 1; iii < splitalumnilist.length; iii++) {
                    if(splitalumnilist[0].equals(selectedcourse)) {

                        String splitalumnilist1[] = splitalumnilist[1].split("&&&&");
                        for (int iiii = 1; iiii < splitalumnilist1.length; iiii++) {

                            StringAlumniName = StringAlumniName + "<br>" + "  -  " + splitalumnilist1[iiii];

                        }
                    }

                }
                StringOutput = "<b>" +splitalumnilist[0] + "</b>";

                finaloutput = finaloutput  +  StringAlumniName;
                StringOutput = "";
                StringAlumniName = "";
            }

        OutputBatch = "Batch "+yeargrad;
        TextViewBatch.setText(HtmlCompat.fromHtml(OutputBatch, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
        TextViewAlumni.setText(HtmlCompat.fromHtml(finaloutput, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        finaloutput = "";

            yeargrad1 = SharedPrefManager.getInstance(this).getYeargrad1();
            if (yeargrad1.length() == 0){
                TextViewBatch1.setVisibility(View.GONE);
                TextViewAlumni1.setVisibility(View.GONE);
            }else {
                TextViewBatch1.setVisibility(View.VISIBLE);
                TextViewAlumni1.setVisibility(View.VISIBLE);
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
                StringOutput = "<br><br><b>" +splitalumnilist1[0] + "</b>";

                finaloutput = finaloutput +  StringAlumniName;
                StringOutput = "";
                StringAlumniName = "";

            }
        OutputBatch1 = "Batch "+yeargrad1+"";
        TextViewBatch1.setText(HtmlCompat.fromHtml(OutputBatch1, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));
        TextViewAlumni1.setText(HtmlCompat.fromHtml(finaloutput, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));




    }
    public String getSafeSubstring(String s, int maxLength){
        if(!TextUtils.isEmpty(s)){
            if(s.length() >= maxLength){
                return s.substring(0, maxLength);
            }
        }
        return s;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mytoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

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