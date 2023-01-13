package edu.ucu.cite.jobportal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_IDno = "idno";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_MIDDLENAME = "middlename";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_YEARGRAD = "yeargrad";
    private static final String KEY_YEARGRAD1 = "yeargrad1";
    private static final String KEY_COLLEGE = "college";
    private static final String KEY_COURSE = "course";
    private static final String KEY_COURSE1 = "course1";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_BIRTHDATE = "birthdate";
    private static final String KEY_CIVILSTATUS = "civilstatus";
    private static final String KEY_CONTACT = "contact";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_SPECIALIZATION = "specialization";
    private static final String KEY_REGION = "region";
    private static final String KEY_PROVINCE = "province";
    private static final String KEY_CITY = "city";
    private static final String KEY_BARANGAY = "barangay";
    private static final String KEY_STREET = "street";
    private static final String KEY_FACEBOOK = "facebook";
    private static final String KEY_INSTAGRAM = "instagram";
    private static final String KEY_BOOKMARK = "bookmark";
    private static final String KEY_GRADUATEDIMAGE = "graduatedimage";
    private static final String KEY_NOTIFICATION = "notification";
    private static final String KEY_NEWSNOTIFICATION = "newsnotification";
    private static final String KEY_EVENTNOTIFICATION = "eventnotification";
    private static final String KEY_POSTGRADUATE = "postgraduate";
    private static final String KEY_POSTGRADUATEY1 = "postgraduatey1";
    private static final String KEY_POSTGRADUATEY2 = "postgraduatey2";
    private static final String KEY_EMPLOYED = "employed";
    private static final String KEY_EMPLOYEDY1 = "employedy1";
    private static final String KEY_EMPLOYEDY2 = "employedy2";
    private static final String KEY_EMPLOYEDY3 = "employedy3";
    private static final String KEY_EMPLOYEDY4 = "employedy4";
    private static final String KEY_EMPLOYEDY5 = "employedy5";
    private static final String KEY_EMPLOYEDN1 = "employedn1";
    private static final String KEY_FIRSTJOB = "firstjob";
    private static final String KEY_FIRSTJOBY1 = "firstjoby1";
    private static final String KEY_FIRSTJOBY2 = "firstjoby2";
    private static final String KEY_FIRSTJOBY3 = "firstjoby3";
    private static final String KEY_FIRSTJOBY4 = "firstjoby4";
    private static final String KEY_FIRSTJOBY4Y1 = "firstjoby4y1";
    private static final String KEY_FIRSTJOBY5 = "firstjoby5";
    private static final String KEY_FIRSTJOBY6 = "firstjoby6";









    private SharedPrefManager(Context context) {
        mCtx = context;

    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public boolean userLogin( String idno, String password, String firstname, String middlename, String lastname,  String yeargrad,  String yeargrad1, String college , String course, String course1, String gender, String birthdate, String civilstatus, String contact, String email, String specialization, String region, String province, String city, String barangay, String street,
            String facebook, String instagram, String bookmark,String graduatedimage, String notification, String newsnotification, String eventnotification
    , String postgraduate, String postgraduatey1, String postgraduatey2, String employed, String employedy1, String employedy2, String employedy3, String employedy4, String employedy5, String employedn1, String firstjob, String firstjoby1, String firstjoby2, String firstjoby3, String firstjoby4, String firstjoby4y1, String firstjoby5, String firstjoby6){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(KEY_IDno, idno);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_FIRSTNAME, firstname);
        editor.putString(KEY_MIDDLENAME, middlename);
        editor.putString(KEY_LASTNAME, lastname);
        editor.putString(KEY_YEARGRAD, yeargrad);
        editor.putString(KEY_YEARGRAD1, yeargrad1);
        editor.putString(KEY_COLLEGE, college);
        editor.putString(KEY_COURSE, course);
        editor.putString(KEY_COURSE1, course1);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_BIRTHDATE, birthdate);
        editor.putString(KEY_CIVILSTATUS, civilstatus);
        editor.putString(KEY_CONTACT, contact);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_SPECIALIZATION, specialization);
        editor.putString(KEY_REGION, region);
        editor.putString(KEY_PROVINCE, province);
        editor.putString(KEY_CITY, city);
        editor.putString(KEY_BARANGAY, barangay);
        editor.putString(KEY_STREET, street);
        editor.putString(KEY_FACEBOOK, facebook);
        editor.putString(KEY_INSTAGRAM, instagram);
        editor.putString(KEY_BOOKMARK, bookmark);
        editor.putString(KEY_GRADUATEDIMAGE, graduatedimage);
        editor.putString(KEY_NOTIFICATION, notification);
        editor.putString(KEY_NEWSNOTIFICATION, newsnotification);
        editor.putString(KEY_EVENTNOTIFICATION, eventnotification);
        editor.putString(KEY_POSTGRADUATE, postgraduate);
        editor.putString(KEY_POSTGRADUATEY1, postgraduatey1);
        editor.putString(KEY_POSTGRADUATEY2, postgraduatey2);
        editor.putString(KEY_EMPLOYED, employed);
        editor.putString(KEY_EMPLOYEDY1, employedy1);
        editor.putString(KEY_EMPLOYEDY2, employedy2);
        editor.putString(KEY_EMPLOYEDY3, employedy3);
        editor.putString(KEY_EMPLOYEDY4, employedy4);
        editor.putString(KEY_EMPLOYEDY5, employedy5);
        editor.putString(KEY_EMPLOYEDN1, employedn1);
        editor.putString(KEY_FIRSTJOB, firstjob);
        editor.putString(KEY_FIRSTJOBY1, firstjoby1);
        editor.putString(KEY_FIRSTJOBY2, firstjoby2);
        editor.putString(KEY_FIRSTJOBY3, firstjoby3);
        editor.putString(KEY_FIRSTJOBY4, firstjoby4);
        editor.putString(KEY_FIRSTJOBY4Y1, firstjoby4y1);
        editor.putString(KEY_FIRSTJOBY5, firstjoby5);
        editor.putString(KEY_FIRSTJOBY6, firstjoby6);








        editor.apply();

        return true;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_IDno, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }


    public String getIDno(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_IDno, null);
    }
    public String getPassword(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }
    public String getFirstname(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTNAME, null);
    }
    public String getMiddlename(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_MIDDLENAME, null);
    }
    public String getLastname(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LASTNAME, null);
    }
    public String getYeargrad(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_YEARGRAD, null);
    }
    public String getYeargrad1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_YEARGRAD1, null);
    }
    public String getCollege(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COLLEGE, null);
    }

    public String getCourse(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COURSE, null);
    }
    public String getCourse1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_COURSE1, null);
    }
    public String getGender(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GENDER, null);
    }
    public String getBirthdate(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BIRTHDATE, null);
    }
    public String getCivilstatus(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CIVILSTATUS, null);
    }
    public String getContact(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CONTACT, null);
    }public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null);
    }public String getSpecialization(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SPECIALIZATION, null);
    }public String getRegion(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_REGION, null);
    }public String getProvince(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PROVINCE, null);
    }public String getCity(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CITY, null);
    }public String getBarangay(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BARANGAY, null);
    }public String getStreet(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STREET, null);
    }public String getFacebook(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FACEBOOK, null);
    }public String getInstagram(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_INSTAGRAM, null);
    }public String getBookmark(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BOOKMARK, null);
    }public String getGraduatedimage(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GRADUATEDIMAGE, null);
    }public String getNotification(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NOTIFICATION, null);
    }public String getNewsnotification(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NEWSNOTIFICATION, null);
    }public String getEventnotification(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EVENTNOTIFICATION, null);
    }public String getPostgradaute(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_POSTGRADUATE, null);
    }public String getPostgradautey1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_POSTGRADUATEY1, null);
    }public String getPostgradautey2(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_POSTGRADUATEY2, null);
    }
    public String getEmployed(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYED, null);
    }public String getEmployedy1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEDY1, null);
    }public String getEmployedy2(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEDY2, null);
    }public String getEmployedy3(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEDY3, null);
    }public String getEmployedy4(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEDY4, null);
    }public String getEmployedy5(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEDY5, null);
    }public String getEmployedn1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMPLOYEDN1, null);
    }public String getFirstjob(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOB, null);
    }public String getFirstjoby1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY1, null);
    }public String getFirstjoby2(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY2, null);
    }public String getFirstjoby3(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY3, null);
    }public String getFirstjoby4(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY4, null);
    }public String getFirstjoby4y1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY4Y1, null);
    }public String getFirstjoby5() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY5, null);
    }public String getFirstjoby6() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTJOBY6, null);
    }









}
