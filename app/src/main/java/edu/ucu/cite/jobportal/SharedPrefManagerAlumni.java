package edu.ucu.cite.jobportal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagerAlumni {
    private static SharedPrefManagerAlumni mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_Alumni = "alumni";


    private SharedPrefManagerAlumni(Context context) {
        mCtx = context;

    }
    public static synchronized SharedPrefManagerAlumni getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerAlumni(context);
        }
        return mInstance;
    }
    public boolean userDatabase(String alumni){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_Alumni, alumni);



        editor.apply();

        return true;
    }



    public String getAlumni(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_Alumni, null);
    }


}
