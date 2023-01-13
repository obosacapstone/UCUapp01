package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class register extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    EditText EditTextlastname, EditTextfirstname, EditTextmiddlename, EditTextcontact, EditTextemail, EditTextidno, EditTextbirthdate;
    RadioGroup RadioGroupqqgenderyesno;
    RadioButton RadioButtonqqgenderyes, RadioButtonqqgenderno;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    private ProgressDialog progressDialog;
    private SimpleAdapter mAdapter;
    TextView TextViewyeargradko;
    //civilstatus
    String[] Stringcivilstatus = {"Single", "Married", "Separated", "Single Parent", "Widow/er"};
    AutoCompleteTextView autoCompleteStringcivilstatus;
    ArrayAdapter<String> adapterStringcivilstatus;

    //course
    AutoCompleteTextView autoCompleteStringcouse;
    ArrayAdapter<String> adapterStringcourse;
    //skills
    TextView TextViewskill;
    boolean[] selectedskill;
    ArrayList<Integer> skilllist = new ArrayList<>();
    String[] StringskillArray = {"Accounting/Finance & Benifits", "Admin/Human Resources", "Sales/Marketing", "Arts/Media/Communications", "Services", "Hotel/Restaurant", "Education/Training",
            "Computer/Information Technology", "Engineering", "Manufacturing", "Building/Construction", "Sciences", "Healthcare", "Journalst/Editors", "General Work", "Publishing"};
    //classification
    Spinner Spinnerclassification;
    ArrayList<String> arrayList_classification;
    ArrayAdapter<String> arrayAdapter_classification;
    //yeargrad
    Spinner Spinneryeargrad;
    ArrayList<String> arrayList_yeargrad;
    ArrayAdapter<String> arrayAdapter_yeargrad;
    ArrayList<String> arrayList_yeargrad1;


    String Stringclassification,Stringyeargrad;
    String StringqCurrentDate;

    Button ButtonSubmit;
    //location
    Spinner SpinnerRegion,SpinnerProvince,SpinnerCity,SpinnerBarangay;

    ArrayList<String> arrayList_Region;
    ArrayAdapter<String> arrayAdapter_Region;
    String StringRegionCode;


    ArrayList<String> arrayList_Province;
    ArrayAdapter<String> arrayAdapter_Province;
    String StringProvinceCode;
    String StringProvinceCode2;
    String item2;

    ArrayList<String> arrayList_City;
    ArrayAdapter<String> arrayAdapter_City;
    String StringCityCode,StringCityCode3;
    String item3;

    ArrayList<String> arrayList_Barangay;
    ArrayAdapter<String> arrayAdapter_Barangay;
    String StringBarangayCode,StringBarangayCode3;
    String item4;

    EditText EditTextHouse;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        EditTextlastname = findViewById(R.id.lastname);
        EditTextfirstname = findViewById(R.id.firstname);
        EditTextmiddlename = findViewById(R.id.middlename);
        EditTextcontact = findViewById(R.id.contact);
        EditTextemail = findViewById(R.id.email);
        EditTextidno = findViewById(R.id.idno);


        RadioGroupqqgenderyesno = findViewById(R.id.qgenderyesno);
        RadioButtonqqgenderyes = findViewById(R.id.qgenderyes);
        RadioButtonqqgenderno = findViewById(R.id.qgenderno);

        //civil status
        autoCompleteStringcivilstatus = findViewById(R.id.selectqcivilstatus);
        adapterStringcivilstatus = new ArrayAdapter<String>(this, R.layout.list_item, Stringcivilstatus);
        autoCompleteStringcivilstatus.setAdapter(adapterStringcivilstatus);


        //course
        String Stringcourse = SharedPrefManagerDatabase.getInstance(this).getCourses();
        String coursesplitted[] = Stringcourse.split(",");

        autoCompleteStringcouse = findViewById(R.id.selectqcourse);
        adapterStringcourse = new ArrayAdapter<String>(this, R.layout.list_item, coursesplitted);
        autoCompleteStringcouse.setAdapter(adapterStringcourse);

        //birthdate

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        //skills
        TextViewskill = findViewById(R.id.selectskill);
        selectedskill = new boolean[StringskillArray.length];
        TextViewskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        register.this
                );
                builder.setTitle("Select Item");
                builder.setCancelable(false);

                builder.setMultiChoiceItems(StringskillArray, selectedskill, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b) {
                            skilllist.add(i);
                            Collections.sort(skilllist);
                        } else {
                            for (int j = 0; j < skilllist.size(); j++) {
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
                        for (int j = 0; j < skilllist.size(); j++) {
                            stringBuilder.append(StringskillArray[skilllist.get(j)]);
                            if (j != skilllist.size() - 1) {
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

        Spinnerclassification = findViewById(R.id.classification);
        Spinneryeargrad = findViewById(R.id.yeargrad);

        arrayList_classification = new ArrayList<>();
        arrayList_classification.add("Alumni");
        arrayList_classification.add("Graduating");

        arrayAdapter_classification = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_classification);
        Spinnerclassification.setAdapter(arrayAdapter_classification);

        //yeargrad
        arrayList_yeargrad = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("y");
        StringqCurrentDate = sdf1.format(c.getTime());
        int IntegerCurrentDate = Integer.parseInt(StringqCurrentDate);
        int x = IntegerCurrentDate + 2;
        for(int i = IntegerCurrentDate ; i<x; i++){
            String StringDate = String.valueOf(i);
            arrayList_yeargrad.add(StringDate);
        }
        //yeargrad1
        arrayList_yeargrad1 = new ArrayList<>();

        int y = IntegerCurrentDate ;
        for(int i = 2005 ; i<y; i++){
            String StringDate1 = String.valueOf(i);
            arrayList_yeargrad1.add(StringDate1);
        }



        Spinnerclassification.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0 ){

                    arrayAdapter_yeargrad = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_yeargrad1);

                }
                if (position == 1 ){
                    arrayAdapter_yeargrad = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_yeargrad);
                }

                Spinneryeargrad.setAdapter(arrayAdapter_yeargrad);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    //location

        EditTextHouse = findViewById(R.id.house);

        StringRegionCode  = SharedPrefManagerDatabase.getInstance(this).getRegionCode1();
        StringProvinceCode  = SharedPrefManagerDatabase.getInstance(this).getProvinceCode1();
        StringProvinceCode2  = SharedPrefManagerDatabase.getInstance(this).getProvinceCode2();
        StringCityCode  = SharedPrefManagerDatabase.getInstance(this).getCityCode2();
        StringCityCode3  = SharedPrefManagerDatabase.getInstance(this).getCityCode3();
        StringBarangayCode  = SharedPrefManagerDatabase.getInstance(this).getBarangay();
        StringBarangayCode3  = SharedPrefManagerDatabase.getInstance(this).getBarangayCode3();
        SpinnerRegion = (Spinner) findViewById(R.id.region);
        SpinnerProvince = (Spinner) findViewById(R.id.province);
        SpinnerCity = (Spinner) findViewById(R.id.city);
        SpinnerBarangay = (Spinner) findViewById(R.id.barangay);


        arrayList_Region = new ArrayList<>();
        String StringRegion = SharedPrefManagerDatabase.getInstance(this).getRegion();
        String StringRegionSplit[] = StringRegion.split(",");
        for(int i = 0; i<StringRegionSplit.length; i++){
            arrayList_Region.add(StringRegionSplit[i]);
        }

        arrayAdapter_Region = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Region);
        SpinnerRegion.setAdapter(arrayAdapter_Region);
        item2 = "";

        arrayList_Province = new ArrayList<>();
        String StringProvince = SharedPrefManagerDatabase.getInstance(this).getCity();
        String StringProvinceSplit[] = StringProvince.split(",");
        for(int i = 0; i<StringProvinceSplit.length; i++){
            arrayList_Province.add(StringProvinceSplit[i]);
        }

        arrayAdapter_Province = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Province);
        SpinnerCity.setAdapter(arrayAdapter_Province);
        item3 = "";



        arrayList_City = new ArrayList<>();
        String StringCity = SharedPrefManagerDatabase.getInstance(this).getBarangay();
        String StringCitySplit[] = StringCity.split(",");
        for(int i = 0; i<StringCitySplit.length; i++){
            arrayList_City.add(StringCitySplit[i]);
        }

        arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_City);
        SpinnerBarangay.setAdapter(arrayAdapter_City);
        item4 = "";









        SpinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String StringRegionCodeSplit[] = StringRegionCode.split(",");
                for(int y = 0; y<StringRegionCodeSplit.length; y++){
                    String StringRegionCodeSplit2[] = StringRegionCodeSplit[y].split("/");
                    if (item.equals(StringRegionCodeSplit2[0])){
                        item2 = StringRegionCodeSplit2[1];
                    }

                }



                arrayList_Province = new ArrayList<>();
                String StringProvinceSplit[] = StringProvinceCode.split(",");
                for(int y = 0; y<StringProvinceSplit.length; y++){
                    String StringProvinceSplitCode[] = StringProvinceSplit[y].split("/");
                    for(int a = 1; a<StringProvinceSplitCode.length; a++) {



                        if (item2.equals(StringProvinceSplitCode[1])) {
                            arrayList_Province.add(StringProvinceSplitCode[0]);
                        }



                    }
                }

                arrayAdapter_Province = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Province);

                SpinnerProvince.setAdapter(arrayAdapter_Province);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //city
        SpinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String StringProvinceCodeSplit[] = StringProvinceCode2.split(",");
                for(int y = 0; y<StringProvinceCodeSplit.length; y++){
                    String StringProvinceCodeSplit2[] = StringProvinceCodeSplit[y].split("/");
                    if (item.equals(StringProvinceCodeSplit2[0])){
                        item3 = StringProvinceCodeSplit2[1];
                    }

                }



                arrayList_City = new ArrayList<>();
                String StringCitySplit[] = StringCityCode.split(",");
                for(int y = 0; y<StringCitySplit.length; y++){
                    String StringCitySplitCode[] = StringCitySplit[y].split("/");
                    for(int a = 1; a<StringCitySplitCode.length; a++) {



                        if (item3.equals(StringCitySplitCode[1])) {
                            arrayList_City.add(StringCitySplitCode[0]);
                        }


                    }
                }

                arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_City);

                SpinnerCity.setAdapter(arrayAdapter_City);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //barangay
        SpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String StringCityCodeSplit[] = StringCityCode3.split(",");
                for(int y = 0; y<StringCityCodeSplit.length; y++){
                    String StringCityCodeSplit2[] = StringCityCodeSplit[y].split("/");
                    String codecut = StringCityCodeSplit2[1].substring(0, 2);
                    if (item.equals(StringCityCodeSplit2[0])){
                        if (codecut.equals(item2)){
                            item4 = StringCityCodeSplit2[1];
                        }

                    }
//                    item4 = StringCityCodeSplit2[0];
                }



                arrayList_Barangay = new ArrayList<>();
                String StringBarangaySplit[] = StringBarangayCode3.split(",");
                for(int y = 0; y<StringBarangaySplit.length; y++){
                    String StringBarangaySplitCode[] = StringBarangaySplit[y].split("/");
                    for(int a = 1; a<StringBarangaySplitCode.length; a++) {

                        if (item4.equals(StringBarangaySplitCode[1])) {
                            arrayList_Barangay.add(StringBarangaySplitCode[0]);
                        }
//                        arrayList_Barangay.add(item4);

                    }
                }

                arrayAdapter_Barangay = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Barangay);

                SpinnerBarangay.setAdapter(arrayAdapter_Barangay);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        //submit
        ButtonSubmit = findViewById(R.id.submit);
        ButtonSubmit.setOnClickListener(this);


        progressBar.setVisibility(View.GONE);
        relativeLayoutProgressBar.setVisibility(View.GONE);


        



    }

    //birthdate
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }
    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return year + "-" + getMonthFormat(month) + "-" + day ;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "01";
        if(month == 2)
            return "02";
        if(month == 3)
            return "03";
        if(month == 4)
            return "04";
        if(month == 5)
            return "05";
        if(month == 6)
            return "06";
        if(month == 7)
            return "07";
        if(month == 8)
            return "08";
        if(month == 9)
            return "09";
        if(month == 10)
            return "10";
        if(month == 11)
            return "11";
        if(month == 12)
            return "12";

        //default should never happen
        return "01";
    }








    private void RegisterUser(){

        String lastname,firstname,middlename,email,contact,birthdate,idno;
        String gender,civilstatus,course,skills,classification,yeargrad;
        String region,province,city,barangay,house;

        lastname = String.valueOf(EditTextlastname.getText()).trim();
        firstname = String.valueOf(EditTextfirstname.getText()).trim();
        middlename = String.valueOf(EditTextmiddlename.getText()).trim();
        email = String.valueOf(EditTextemail.getText()).trim();
        contact = String.valueOf(EditTextcontact.getText()).trim();
        birthdate = String.valueOf(dateButton.getText()).trim();
        idno = String.valueOf(EditTextidno.getText()).trim();
        //register
        RadioButton Checkqgender = findViewById(RadioGroupqqgenderyesno.getCheckedRadioButtonId());
        gender = Checkqgender.getText().toString();
        civilstatus = String.valueOf(autoCompleteStringcivilstatus.getText()).trim();
        course = String.valueOf(autoCompleteStringcouse.getText()).trim();
        skills = String.valueOf(TextViewskill.getText()).trim();
        classification = Spinnerclassification.getSelectedItem().toString();
        yeargrad = Spinneryeargrad.getSelectedItem().toString();
        //location
        region = SpinnerRegion.getSelectedItem().toString();
        province = SpinnerProvince.getSelectedItem().toString();
        city = SpinnerCity.getSelectedItem().toString();
        barangay = SpinnerBarangay.getSelectedItem().toString();
        house = String.valueOf(EditTextHouse.getText()).trim();

        EditTextlastname.setError(null);
        EditTextfirstname.setError(null);
        EditTextmiddlename.setError(null);
        EditTextemail.setError(null);
        EditTextcontact.setError(null);
        EditTextidno.setError(null);
        RadioButtonqqgenderno.setError(null);
        autoCompleteStringcivilstatus.setError(null);
        autoCompleteStringcouse.setError(null);
        TextViewskill.setError(null);
        EditTextHouse.setError(null);
        if (lastname.isEmpty()) {
            EditTextlastname.setError("Please fill up");
            EditTextlastname.requestFocus();
        }
        if (firstname.isEmpty()) {
            EditTextfirstname.setError("Please fill up");
            EditTextfirstname.requestFocus();
        }
        if (middlename.isEmpty()) {
            EditTextmiddlename.setError("Please fill up");
            EditTextmiddlename.requestFocus();
        }
        if (email.isEmpty()) {
            EditTextemail.setError("Please fill up");
            EditTextemail.requestFocus();
        }
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern)){
            EditTextemail.setError("Please Enter Valid Email");
            EditTextemail.requestFocus();
        }else{
            EditTextemail.setError(null);
        }
        if (contact.isEmpty()) {
            EditTextcontact.setError("Please fill up");
            EditTextcontact.requestFocus();
        }
        if (contact.length() != 11){
            EditTextcontact.setError("Please Enter 11 Digit");
            EditTextcontact.requestFocus();
        }else{
            EditTextcontact.setError(null);
        }
        if (birthdate.isEmpty()) {
            EditTextbirthdate.setError("Please fill up");
            EditTextbirthdate.requestFocus();
        }
        if(classification.equals("Graduating")){
            if (idno.isEmpty()) {
                EditTextidno.setError("Please fill up");
                EditTextidno.requestFocus();
            }

        }



//        if(classification.equals("Alumni")){
//
//        }else{
//
//        }


        if (gender.isEmpty()) {
            RadioButtonqqgenderno.setError("Please fill up");
            RadioButtonqqgenderno.requestFocus();
        }
        if (civilstatus.isEmpty()) {
            autoCompleteStringcivilstatus.setError("Please fill up");
            autoCompleteStringcivilstatus.requestFocus();
        }
        if (course.isEmpty()) {
            autoCompleteStringcouse.setError("Please fill up");
            autoCompleteStringcouse.requestFocus();
        }
        if (skills.isEmpty()) {
            TextViewskill.setError("Please fill up");
            TextViewskill.requestFocus();
        }
        if (house.isEmpty()) {
            EditTextHouse.setError("Please fill up");
            EditTextHouse.requestFocus();
        }








            if (!lastname.isEmpty() && !firstname.isEmpty() && !middlename.isEmpty() && !email.isEmpty() && email.matches(emailPattern) && !contact.isEmpty() && contact.length() == 11 && !birthdate.isEmpty()&& !gender.isEmpty() && !civilstatus.isEmpty()  && !course.isEmpty() && !skills.isEmpty() && !classification.isEmpty() &&
                !yeargrad.isEmpty()){
                if(classification.equals("Alumni")){
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_REGISTER,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);





                                        if (!jsonObject.getBoolean("error")) {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(), login.class));
                                            finish();
                                        }else{
//                                            Toast.makeText(getApplicationContext(), "Email Already Exist", Toast.LENGTH_SHORT).show();
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                            params.put("lastname",lastname);
                            params.put("firstname",firstname);
                            params.put("middlename",middlename);
                            params.put("gender",gender);
                            params.put("email",email);
                            params.put("contact",contact);
                            params.put("birthdate",birthdate);
                            params.put("civilstatus",civilstatus);
                            params.put("idno",idno);
                            params.put("course",course);
                            params.put("skills",skills);
                            params.put("classification",classification);
                            params.put("yeargrad",yeargrad);
                            params.put("region",region);
                            params.put("province",province);
                            params.put("city",city);
                            params.put("barangay",barangay);
                            params.put("house",house);

                            return params;

                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
                }
                if(classification.equals("Graduating") && !idno.isEmpty()){
                    progressDialog.show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            Constants.URL_REGISTER,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressDialog.dismiss();
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);


                                        if (!jsonObject.getBoolean("error")) {
                                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getApplicationContext(), login.class));
                                            finish();
                                        }else{
//                                            Toast.makeText(getApplicationContext(), "Email Already Exist", Toast.LENGTH_SHORT).show();

                                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
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
                            params.put("lastname",lastname);
                            params.put("firstname",firstname);
                            params.put("middlename",middlename);
                            params.put("gender",gender);
                            params.put("email",email);
                            params.put("contact",contact);
                            params.put("birthdate",birthdate);
                            params.put("civilstatus",civilstatus);
                            params.put("idno",idno);
                            params.put("course",course);
                            params.put("skills",skills);
                            params.put("classification",classification);
                            params.put("yeargrad",yeargrad);
                            params.put("region",region);
                            params.put("province",province);
                            params.put("city",city);
                            params.put("barangay",barangay);
                            params.put("house",house);

                            return params;

                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
                }
                if (classification.equals("Graduating") && idno.isEmpty()){
                    Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
                }

        }else{
            Toast.makeText(getApplicationContext(), "All Fields are required", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onClick(View view) {
        if (view == ButtonSubmit)
            RegisterUser();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
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