package edu.ucu.cite.jobportal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagerPost {
    private static SharedPrefManagerPost mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_PostID= "idpost";
    private static final String KEY_PostIdno= "postidno";
    private static final String KEY_PostPost= "postpost";
    private static final String KEY_PostGraduatedImage= "postgraduatedimage";
    private static final String KEY_PostDate= "postdate";
    private static final String KEY_PostTime= "posttime";
    private static final String KEY_PostFistname= "postfirstname";
    private static final String KEY_PostMiddlename= "postmiddlename";
    private static final String KEY_PostLastname= "postlastname";


    private SharedPrefManagerPost(Context context) {
        mCtx = context;

    }
    public static synchronized SharedPrefManagerPost getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerPost(context);
        }
        return mInstance;
    }
    public boolean userPostId(String idpost, String postidno, String postpost, String postgraduatedimage, String postdate, String posttime, String postfirstname,
                              String postmiddlename, String postlastname ){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_PostID, idpost);
        editor.putString(KEY_PostIdno, postidno);
        editor.putString(KEY_PostPost, postpost);
        editor.putString(KEY_PostGraduatedImage, postgraduatedimage);
        editor.putString(KEY_PostDate, postdate);
        editor.putString(KEY_PostTime, posttime);
        editor.putString(KEY_PostFistname, postfirstname);
        editor.putString(KEY_PostMiddlename, postmiddlename);
        editor.putString(KEY_PostLastname, postlastname);




        editor.apply();

        return true;
    }



    public String getPostId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostID, null);
    }
    public String getIdno(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostIdno, null);
    }
    public String getPost(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostPost, null);
    }
    public String getGraduatedImage(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostGraduatedImage, null);
    }
    public String getDate(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostDate, null);
    }
    public String getTime(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostTime, null);
    }
    public String getFirstname(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostFistname, null);
    }
    public String getMiddlename(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostMiddlename, null);
    }
    public String getLastname(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PostLastname, null);
    }


}
