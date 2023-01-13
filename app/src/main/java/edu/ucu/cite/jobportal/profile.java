package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,PopupMenu.OnMenuItemClickListener{
    TextView TextViewfullname , TextViewidno,TextViewcourse ,TextViewcourse1, TextViewyeargrad,TextViewyeargrad1, TextViewbirthdate , TextViewgender , TextViewcivilstatus , TextViewcontact , TextViewemail , TextViewskill , TextViewaddress ;
    TextView TextViewnotification , TextViewnewsnotification, TextVieweventnotification, TextViewpostgraduate, TextViewpostgraduatey1, TextViewpostgraduatey2, TextViewemployed, TextViewemployedy1, TextViewemployedy2, TextViewemployedy3, TextViewemployedy4, TextViewemployedy5, TextViewemployedn1, TextViewfirstjob, TextViewfirstjoby1, TextViewfirstjoby2, TextViewfirstjoby3, TextViewfirstjoby4, TextViewfirstjoby4y1,TextViewfirstjoby5,TextViewfirstjoby6;
    TextView qTextViewpostgraduate, qTextViewpostgraduatey1, qTextViewpostgraduatey2, qTextViewemployed, qTextViewemployedy1, qTextViewemployedy2, qTextViewemployedy3, qTextViewemployedy4, qTextViewemployedy5, qTextViewemployedn1, qTextViewfirstjob, qTextViewfirstjoby1, qTextViewfirstjoby2, qTextViewfirstjoby3, qTextViewfirstjoby4, qTextViewfirstjoby4y1,qTextViewfirstjoby5,qTextViewfirstjoby6;
    ImageView ImageViewgraduatedimage;
    Button ButtonOtherInfo;
    Button ButtonPassword;
    Button ButtonProfilepic;
    TextView TextViewTitleNav;
    NavigationView navigationView;
    DrawerLayout mydrawer;
    Toolbar toolbar;
    ActionBarDrawerToggle mytoggle=null;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    CardView CardViewfivepercent,CardViewonepercent;
    //postgraduate
    LinearLayout LinearlayoutPostgraduatedGuide,LinearlayoutPostgraduatedYes,LinearlayoutPostgraduatedNo;
    //employed
    LinearLayout LinearlayoutEmployedGuide,LinearlayoutEmployedYes,LinearlayoutEmployedNo;
    //firstjob
    LinearLayout LinearlayoutFirstjobGuide,LinearlayoutFirstjobYes;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //postgraduate
        LinearlayoutPostgraduatedGuide = findViewById(R.id.postgraduateguide);
        LinearlayoutPostgraduatedYes = findViewById(R.id.postgraduateyes);
        //employed
        LinearlayoutEmployedGuide = findViewById(R.id.employedguide);
        LinearlayoutEmployedYes = findViewById(R.id.employedyes);
        LinearlayoutEmployedNo = findViewById(R.id.employedno);

        //firstjob
        LinearlayoutFirstjobGuide = findViewById(R.id.firstjobguide);
        LinearlayoutFirstjobYes = findViewById(R.id.firstjobyes);


        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);


        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();



        Glide.with(profile.this).load(graduatedimage).into(ImageViewNavProfile);
        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewNavIdno.setText(idno);





        TextViewfullname = findViewById(R.id.fullname);
        TextViewidno = findViewById(R.id.idno);
        TextViewcourse = findViewById(R.id.course);
        TextViewyeargrad = findViewById(R.id.yeargrad);
        TextViewgender = findViewById(R.id.gender);
        TextViewbirthdate = findViewById(R.id.birthdate);
        TextViewcivilstatus = findViewById(R.id.civilstatus);
        TextViewcontact = findViewById(R.id.contact);
        TextViewemail = findViewById(R.id.email);
        TextViewskill = findViewById(R.id.specialization);
        TextViewaddress = findViewById(R.id.address);
        ImageViewgraduatedimage = findViewById(R.id.graduatedimage);
        TextViewnotification = findViewById(R.id.notification);
        TextViewnewsnotification = findViewById(R.id.newsnotification);
        TextVieweventnotification = findViewById(R.id.eventnotification);
        //answer
        TextViewpostgraduate = findViewById(R.id.postgraduate);
        TextViewpostgraduatey1 = findViewById(R.id.postgraduatey1);
        TextViewpostgraduatey2 = findViewById(R.id.postgraduatey2);
        TextViewemployed = findViewById(R.id.employed);
        TextViewemployedy1 = findViewById(R.id.employedy1);
        TextViewemployedy2 = findViewById(R.id.employedy2);
        TextViewemployedy3 = findViewById(R.id.employedy3);
        TextViewemployedy4 = findViewById(R.id.employedy4);
        TextViewemployedy5 = findViewById(R.id.employedy5);
        TextViewemployedn1 = findViewById(R.id.employedn1);
        TextViewfirstjob = findViewById(R.id.firstjob);
        TextViewfirstjoby1 = findViewById(R.id.firstjoby1);
        TextViewfirstjoby2 = findViewById(R.id.firstjoby2);
        TextViewfirstjoby3 = findViewById(R.id.firstjoby3);
        TextViewfirstjoby4 = findViewById(R.id.firstjoby4);
        TextViewfirstjoby4y1 = findViewById(R.id.firstjoby4y1);
        TextViewfirstjoby5 = findViewById(R.id.firstjoby5);
        TextViewfirstjoby6 = findViewById(R.id.firstjoby6);
        //question
        qTextViewpostgraduate = findViewById(R.id.qpostgraduate);
        qTextViewpostgraduatey1 = findViewById(R.id.qpostgraduatey1);
        qTextViewpostgraduatey2 = findViewById(R.id.qpostgraduatey2);
        qTextViewemployed = findViewById(R.id.qemployed);
        qTextViewemployedy1 = findViewById(R.id.qemployedy1);
        qTextViewemployedy2 = findViewById(R.id.qemployedy2);
        qTextViewemployedy3 = findViewById(R.id.qemployedy3);
        qTextViewemployedy4 = findViewById(R.id.qemployedy4);
        qTextViewemployedy5 = findViewById(R.id.qemployedy5);
        qTextViewemployedn1 = findViewById(R.id.qemployedn1);
        qTextViewfirstjob = findViewById(R.id.qfirstjob);
        qTextViewfirstjoby1 = findViewById(R.id.qfirstjoby1);
        qTextViewfirstjoby2 = findViewById(R.id.qfirstjoby2);
        qTextViewfirstjoby3 = findViewById(R.id.qfirstjoby3);
        qTextViewfirstjoby4 = findViewById(R.id.qfirstjoby4);
        qTextViewfirstjoby4y1 = findViewById(R.id.qfirstjoby4y1);
        qTextViewfirstjoby5 = findViewById(R.id.qfirstjoby5);
        qTextViewfirstjoby6 = findViewById(R.id.qfirstjoby6);

        //nav bar
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Profile Preview");





//        ImageViewNavProfile = findViewById(R.id.navprofile);
//        TextViewNavFullname = findViewById(R.id.navfullname);
//        TextViewNavIdno = findViewById(R.id.navidno);




        String course = SharedPrefManager.getInstance(this).getCourse();
        String course1 = SharedPrefManager.getInstance(this).getCourse1();
        String yeargrad = SharedPrefManager.getInstance(this).getYeargrad();
        String yeargrad1 = SharedPrefManager.getInstance(this).getYeargrad1();
        String gender = SharedPrefManager.getInstance(this).getGender();
        String birthdate = SharedPrefManager.getInstance(this).getBirthdate();
        String civilstatus = SharedPrefManager.getInstance(this).getCivilstatus();
        String contact = SharedPrefManager.getInstance(this).getContact();
        String email = SharedPrefManager.getInstance(this).getEmail();
        String specialization = SharedPrefManager.getInstance(this).getSpecialization();
        String region = SharedPrefManager.getInstance(this).getRegion();
        String province = SharedPrefManager.getInstance(this).getProvince();
        String city = SharedPrefManager.getInstance(this).getCity();
        String barangay = SharedPrefManager.getInstance(this).getBarangay();
        String street = SharedPrefManager.getInstance(this).getStreet();
        String notification = SharedPrefManager.getInstance(this).getNotification();
        String newsnotification = SharedPrefManager.getInstance(this).getNewsnotification();
        String eventnotification = SharedPrefManager.getInstance(this).getEventnotification();
        String postgraduate = SharedPrefManager.getInstance(this).getPostgradaute();
        String postgraduatey1 = SharedPrefManager.getInstance(this).getPostgradautey1();
        String postgraduatey2 = SharedPrefManager.getInstance(this).getPostgradautey2();
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
        String firstjoby5 = SharedPrefManager.getInstance(this).getFirstjoby5();
        String firstjoby6 = SharedPrefManager.getInstance(this).getFirstjoby6();





        TextViewfullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewidno.setText(idno);
        TextViewcourse.setText(course);
        TextViewyeargrad.setText(yeargrad);
        if (course1 != ""){
            TextViewcourse.setText(course + ", " + course1);
            TextViewyeargrad.setText(yeargrad+ ", " + yeargrad1);
        }

        TextViewgender.setText(gender);
        TextViewbirthdate.setText(birthdate);
        TextViewcivilstatus.setText(civilstatus);
        TextViewcontact.setText(contact);
        TextViewemail.setText(email);
        TextViewskill.setText(specialization);
        TextViewaddress.setText(street + "" + barangay + "" + city + "" + province + "" + region );
        Glide.with(profile.this).load(graduatedimage).into(ImageViewgraduatedimage);
        TextViewnotification.setText(" - " + notification);
        TextViewnewsnotification.setText(" - " + newsnotification);
        TextVieweventnotification.setText(" - " + eventnotification);
        TextViewpostgraduate.setText( " - " + postgraduate);
        TextViewpostgraduatey1.setText(" - " + postgraduatey1);
        TextViewpostgraduatey2.setText(" - " + postgraduatey2);
        TextViewemployed.setText(" - " + employed);
        TextViewemployedy1.setText(" - " + employedy1);
        TextViewemployedy2.setText(" - " + employedy2);
        TextViewemployedy3.setText(" - " + employedy3);
        TextViewemployedy4.setText(" - " + employedy4);
        TextViewemployedy5.setText(" - " + employedy5);
        TextViewemployedn1.setText(" - " + employedn1);
        TextViewfirstjob.setText(" - " + firstjob);
        TextViewfirstjoby1.setText(" - " + firstjoby1);
        TextViewfirstjoby2.setText(" - " + firstjoby2);
        TextViewfirstjoby3.setText(" - " + firstjoby3);
        TextViewfirstjoby4.setText(" - " + firstjoby4);
        TextViewfirstjoby4y1.setText(" - " + firstjoby4y1);
        TextViewfirstjoby5.setText(" - " + firstjoby5);
        TextViewfirstjoby6.setText(" - " + firstjoby6);


        //nav bar
//        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
//        TextViewNavIdno.setText(idno);
//        Glide.with(profile.this).load(graduatedimage).into(ImageViewNavProfile);

        //VISIBLE


        //post graduated



        if (postgraduate.equals("Yes")){
            LinearlayoutPostgraduatedGuide.setVisibility(TextView.VISIBLE);
            LinearlayoutPostgraduatedYes.setVisibility(TextView.VISIBLE);
            qTextViewpostgraduate.setText("Post graduate studies.");
            qTextViewpostgraduatey1.setText("Current post graduate studies.");
            qTextViewpostgraduatey2.setText("Pursue advance studies.");
        }
        if (postgraduate.equals("No")){
            LinearlayoutPostgraduatedGuide.setVisibility(TextView.VISIBLE);
            qTextViewpostgraduate.setText("Post graduate studies.");
        }


        //employed



        if (employed.equals("Yes")){
            LinearlayoutEmployedGuide.setVisibility(TextView.VISIBLE);
            LinearlayoutEmployedYes.setVisibility(TextView.VISIBLE);
            qTextViewemployed.setText("Current Employed.");
            qTextViewemployedy1.setText("Present employment status.");
            qTextViewemployedy2.setText("Place of work.");
            qTextViewemployedy3.setText("Present Occupation.");
            qTextViewemployedy4.setText("Company you are employed.");
            qTextViewemployedy5.setText("College relevant to your present job.");
        }
        if (employed.equals("No")){
            LinearlayoutEmployedGuide.setVisibility(TextView.VISIBLE);
            LinearlayoutEmployedNo.setVisibility(TextView.VISIBLE);
            qTextViewemployed.setText("Current Employed.");
            qTextViewemployedn1.setText("Main reason why you are not yet employed.");
        }
        if (employed.equals("")){
            LinearlayoutEmployedGuide.setVisibility(TextView.GONE);
        }

        //firstjob



        if (firstjob.equals("Yes")){
            LinearlayoutFirstjobGuide.setVisibility(TextView.VISIBLE);
            LinearlayoutFirstjobYes.setVisibility(TextView.VISIBLE);
            qTextViewfirstjob.setText("Do you have your first job after college.");
            qTextViewfirstjoby1.setText("Find first job.");
            qTextViewfirstjoby2.setText("Reasons for accepting the first job.");
            qTextViewfirstjoby3.setText("Level Position in your first job.");
            qTextViewfirstjoby4.setText("College relevant to your first job.");
            qTextViewfirstjoby5.setText("What is your initial gross monthly earning in your first job after college?");
            qTextViewfirstjoby6.setText("How long did it take you to land your first job?");

        }
        if (firstjob.equals("No")){
            LinearlayoutFirstjobGuide.setVisibility(TextView.VISIBLE);
            qTextViewfirstjob.setText("Do you have your first job after college.");
        }
        if (firstjoby4.equals("Yes")){
            qTextViewfirstjoby4y1.setText("Competencies learned in college did you find very useful  in first job.");
            qTextViewfirstjoby4y1.setVisibility(TextView.VISIBLE);
            TextViewfirstjoby4y1.setVisibility(TextView.VISIBLE);

        }




        mydrawer = findViewById(R.id.mydrawer);
        navigationView = findViewById(R.id.navigationdrawer);

        toolbar=findViewById(R.id.sidetoolbar);
        mytoggle = new ActionBarDrawerToggle(this,mydrawer, toolbar, R.string.open, R.string.close);

        mydrawer.addDrawerListener(mytoggle);
        navigationView.bringToFront();
        mytoggle.syncState();

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);



        CardViewfivepercent = findViewById(R.id.fivepercent);
        CardViewonepercent = findViewById(R.id.onepercent);
        if (postgraduate.isEmpty() || employed.isEmpty() || firstjob.isEmpty()){
            CardViewfivepercent.setVisibility(TextView.GONE);
        }else{
            CardViewonepercent.setVisibility(TextView.GONE);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.naview);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.Profile);
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

                    return true;


            }
            return false;
        });


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
                Intent intent1 = new Intent(profile.this,alumni.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(profile.this,trendinginfo.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(profile.this,bookmarkinfo.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(profile.this,login.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;


        }

        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profilepic:
                Intent intent1 = new Intent(profile.this,profilepic.class);
                startActivity(intent1);
                return true;
            case R.id.password:
                Intent intent2 = new Intent(profile.this,password.class);
                startActivity(intent2);
                return true;
            case R.id.otherinfo:
                Intent intent3 = new Intent(profile.this,generalinfo.class);
                startActivity(intent3);
                return true;
            default:
                return false;
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


    public void GeneralInfo(View view) {
        Intent intent = new Intent(profile.this,generalinfo.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void AdvanceStudy(View view) {
        Intent intent = new Intent(profile.this,advancestudy.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void FirstJob(View view) {
        Intent intent = new Intent(profile.this,firstjob.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void Employment(View view) {
        Intent intent = new Intent(profile.this,employment.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void ChangeProfile(View view) {
        Intent intent = new Intent(profile.this,profilepic.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void Password(View view) {
        Intent intent = new Intent(profile.this,password.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}