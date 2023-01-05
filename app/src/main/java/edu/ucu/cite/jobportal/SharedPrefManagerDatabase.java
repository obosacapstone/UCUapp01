package edu.ucu.cite.jobportal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagerDatabase {
    private static SharedPrefManagerDatabase mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mysharedpref12";

    private static final String KEY_Courses = "courses";
    private static final String KEY_Region = "region";
    private static final String KEY_RegionCode1 = "regioncode1";
    private static final String KEY_Province = "province";
    private static final String KEY_ProvinceCode1 = "provincecode1";
    private static final String KEY_ProvinceCode2 = "provincecode2";
    private static final String KEY_City = "city";
    private static final String KEY_CityCode1 = "citycode1";
    private static final String KEY_CityCode2 = "citycode2";
    private static final String KEY_CityCode3 = "citycode3";
    private static final String KEY_Barangay = "barangay";
    private static final String KEY_BarngayCode1 = "barangaycode1";
    private static final String KEY_BarngayCode2 = "barangaycode2";
    private static final String KEY_BarngayCode3 = "barangaycode3";








    private SharedPrefManagerDatabase(Context context) {
        mCtx = context;

    }
    public static synchronized SharedPrefManagerDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerDatabase(context);
        }
        return mInstance;
    }
    public boolean userDatabase(String courses,String region, String regioncode1, String province , String provincecode1, String provincecode2, String city, String citycode1, String citycode2, String citycode3,
                                 String barangay, String barangaycode1,String barangaycode2,String barangaycode3){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(KEY_Courses, courses);
        editor.putString(KEY_Region, region);
        editor.putString(KEY_RegionCode1, regioncode1);
        editor.putString(KEY_Province, province);
        editor.putString(KEY_ProvinceCode1, provincecode1);
        editor.putString(KEY_ProvinceCode2, provincecode2);
        editor.putString(KEY_City,city);
        editor.putString(KEY_CityCode1, citycode1);
        editor.putString(KEY_CityCode2, citycode2);
        editor.putString(KEY_CityCode3, citycode3);
        editor.putString(KEY_Barangay,barangay);
        editor.putString(KEY_BarngayCode1, barangaycode1);
        editor.putString(KEY_BarngayCode2, barangaycode2);
        editor.putString(KEY_BarngayCode3, barangaycode3);



        editor.apply();

        return true;
    }




    public String getCourses(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Courses, null);
    }
    public String getRegion(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Region, null);
    }
    public String getRegionCode1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_RegionCode1, null);
    }
    public String getProvince(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Province, null);
    }
    public String getProvinceCode1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ProvinceCode1, null);
    }
    public String getProvinceCode2(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ProvinceCode2, null);
    }
    public String getCity(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_City, null);
    }
    public String getCityCode1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CityCode1, null);
    }
    public String getCityCode2(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CityCode2, null);
    }
    public String getCityCode3(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CityCode3, null);
    }
    public String getBarangay(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Barangay, null);
    }
    public String getBarangayCode1(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BarngayCode1, null);
    }
    public String getBarangayCode2(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BarngayCode2, null);
    }
    public String getBarangayCode3(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BarngayCode3, null);
    }



}
