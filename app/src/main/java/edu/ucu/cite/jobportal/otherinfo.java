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

    //notification job
    Switch Switchnotificationjob;
    String Stringnotificationjob;
    //notification News
    Switch Switchnotificationnews;
    String Stringnotificationnews;
    //notification Event
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
    //skills
    TextView TextViewskill;
    boolean[] selectedskill;
    ArrayList<Integer> skilllist = new ArrayList<>();
    String[] StringskillArray = {"Accounting/Finance & Benifits","Admin/Human Resources","Sales/Marketing","Arts/Media/Communications","Services","Hotel/Restaurant","Education/Training",
    "Computer/Information Technology","Engineering","Manufacturing","Building/Construction","Sciences","Healthcare","Journalst/Editors","General Work","Publishing"};
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
                        otherinfo.this
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
        //motheremployed
        RadioGroupqemployedyesno= findViewById(R.id.qemployedyesno);
        LinearLayoutqemployed1yes = findViewById(R.id.qemployed1yes);
        LinearLayoutqemployed1no = findViewById(R.id.qemployed1no);
        //employed
        RadioButtonqemployedyes = findViewById(R.id.qemployedyes);
        RadioButtonqemployedno = findViewById(R.id.qemployedno);
        //employedyes1
        autoCompleteStringemployedy1 = findViewById(R.id.selectqemployed1yes1);
        adapterStringemployedy1 = new ArrayAdapter<String>(this,R.layout.list_item,Stringemployedy1);
        autoCompleteStringemployedy1.setAdapter(adapterStringemployedy1);
        //employedyes2
        autoCompleteStringemployedy2 = findViewById(R.id.selectqemployed1yes2);
        adapterStringemployedy2 = new ArrayAdapter<String>(this,R.layout.list_item,Stringemployedy2);
        autoCompleteStringemployedy2.setAdapter(adapterStringemployedy2);
        //employedyes3
        autoCompleteStringemployedy3 = findViewById(R.id.selectqemployed1yes3);
        adapterStringemployedy3 = new ArrayAdapter<String>(this,R.layout.list_item,Stringemployedy3);
        autoCompleteStringemployedy3.setAdapter(adapterStringemployedy3);
        //employedyes4
        autoCompleteStringemployedy4 = findViewById(R.id.selectqemployed1yes4);
        adapterStringemployedy4 = new ArrayAdapter<String>(this,R.layout.list_item,Stringemployedy4);
        autoCompleteStringemployedy4.setAdapter(adapterStringemployedy4);
        //employedyes5
        autoCompleteStringemployedy5 = findViewById(R.id.selectqemployed1yes5);
        adapterStringemployedy5 = new ArrayAdapter<String>(this,R.layout.list_item,Stringemployedy5);
        autoCompleteStringemployedy5.setAdapter(adapterStringemployedy5);
        //employedno1
        autoCompleteStringemployedn1 = findViewById(R.id.selectqemployed1no1);
        adapterStringemployedn1 = new ArrayAdapter<String>(this,R.layout.list_item,Stringemployedn1);
        autoCompleteStringemployedn1.setAdapter(adapterStringemployedn1);
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
                        otherinfo.this
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
                        otherinfo.this
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


        BtnSubmit = findViewById(R.id.submit);
        progressDialog=new ProgressDialog(this);

        BtnSubmit.setOnClickListener(this);


        String course = SharedPrefManager.getInstance(this).getCourse();
        String yeargrad = SharedPrefManager.getInstance(this).getYeargrad();
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
        //civilstatus


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
        //postgraduate
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
        String civilstatus,contact,email,skills,notificationjob,notificationnews,notificationevent;
        String idno,postgraduate ,postgraduatey1, postgraduatey2;
        String employed,employedy1,employedy2,employedy3,employedy4,employedy5,employedn1;
        String firstjob,firstjoby1,firstjoby2,firstjoby3,firstjoby4,firstjoby4y1;
        //information
        Switchnotificationjob.setText(Stringnotificationjob);
        notificationjob = String.valueOf(Switchnotificationjob.getText()).trim();
        Switchnotificationnews.setText(Stringnotificationnews);
        notificationnews = String.valueOf(Switchnotificationnews.getText()).trim();
        Switchnotificationevent.setText(Stringnotificationevent);
        notificationevent = String.valueOf(Switchnotificationevent.getText()).trim();

        skills = String.valueOf(TextViewskill.getText()).trim();
        contact = String.valueOf(EditTextcontact.getText()).trim();
        civilstatus = String.valueOf(autoCompleteStringcivilstatus.getText()).trim();
        email = String.valueOf(EditTextemail.getText()).trim();
        idno = SharedPrefManager.getInstance(this).getIDno();
        String idnumber = SharedPrefManager.getInstance(this).getIDno();
        //postgraduate
        RadioButton Checkqpostgraduate = findViewById(RadioGroupqpostgraduateyesno.getCheckedRadioButtonId());
        postgraduate = Checkqpostgraduate.getText().toString();
        postgraduatey1 = String.valueOf(autoCompleteStringpostgraduateyes1.getText()).trim();
        postgraduatey2 = String.valueOf(autoCompleteStringpostgraduateyes2.getText()).trim();
        //employed
        RadioButton Checkqemployed = findViewById(RadioGroupqemployedyesno.getCheckedRadioButtonId());
        employed = Checkqemployed.getText().toString();
        employedy1 = String.valueOf(autoCompleteStringemployedy1.getText()).trim();
        employedy2 = String.valueOf(autoCompleteStringemployedy2.getText()).trim();
        employedy3 = String.valueOf(autoCompleteStringemployedy3.getText()).trim();
        employedy4 = String.valueOf(autoCompleteStringemployedy4.getText()).trim();
        employedy5 = String.valueOf(autoCompleteStringemployedy5.getText()).trim();
        employedn1 = String.valueOf(autoCompleteStringemployedn1.getText()).trim();
        //firstjob
        RadioButton Checkqfirstjob = findViewById(RadioGroupqfirstjobyesno.getCheckedRadioButtonId());
        firstjob = Checkqfirstjob.getText().toString();
        firstjoby1 = String.valueOf(autoCompleteStringfirstjobyes1.getText()).trim();
        firstjoby2 = String.valueOf(TextViewfirstjobyes2.getText()).trim();
        firstjoby3 = String.valueOf(autoCompleteStringfirstjobyes3.getText()).trim();
        RadioButton Checkqfirstjob4 = findViewById(RadioGroupqfirstjobyesno4.getCheckedRadioButtonId());
        firstjoby4 = Checkqfirstjob4.getText().toString();
        firstjoby4y1 = String.valueOf(TextViewfirstjobyes4y1.getText()).trim();



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
                                                jsonObject.getString("course"),
                                                jsonObject.getString("yeargrad"),
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
                                                jsonObject.getString("firstjoby4y1")




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
                params.put("notificationjob",notificationjob);
                params.put("notificationnews",notificationnews);
                params.put("notificationevent",notificationevent);
                params.put("civilstatus",civilstatus);
                params.put("contact",contact);
                params.put("email",email);
                params.put("skills",skills);
                params.put("postgraduate",postgraduate);
                params.put("postgraduatey1",postgraduatey1);
                params.put("postgraduatey2",postgraduatey2);
                params.put("employed",employed);
                params.put("employedy1",employedy1);
                params.put("employedy2",employedy2);
                params.put("employedy3",employedy3);
                params.put("employedy4",employedy4);
                params.put("employedy5",employedy5);
                params.put("employedn1",employedn1);
                params.put("firstjob",firstjob);
                params.put("firstjoby1",firstjoby1);
                params.put("firstjoby2",firstjoby2);
                params.put("firstjoby3",firstjoby3);
                params.put("firstjoby4",firstjoby4);
                params.put("firstjoby4y1",firstjoby4y1);

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

            case R.id.profile:
                Intent intent1 = new Intent(otherinfo.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.jobhiring:
                Intent intent2 = new Intent(otherinfo.this,jobhiringinfo.class);
                startActivity(intent2);
                break;
            case R.id.news:
                Intent intent3 = new Intent(otherinfo.this,newsinfo.class);
                startActivity(intent3);
                break;
            case R.id.event:
                Intent intent4 = new Intent(otherinfo.this,eventinfo.class);
                startActivity(intent4);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                Intent intent5 = new Intent(otherinfo.this,login.class);
                startActivity(intent5);
                break;


        }

        return true;
    }
}