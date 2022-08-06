package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class profilepic extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener{
    EditText EditTextpassword, EditTextnewpassword, EditTextconfirmpassword;
    Button Buttonsubmit;
    Button btnChoose, btnUpload;
    ImageView imageUpload ,ImageProfile;
    final int CODE_GALLERY_REQUEST = 999;
    Bitmap bitmap;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mtoggle=null,mytoggle=null;
    androidx.appcompat.widget.Toolbar toolbar;
    DrawerLayout mydrawer;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    NavigationView navigationView;
    ProgressDialog progressDialog;
    TextView TextViewTitleNav;
    ImageView ImageViewtambling;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepic);
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("Profile Picture");

        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);
        ImageProfile = findViewById(R.id.imageUpload);
        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();


        Glide.with(profilepic.this).load(graduatedimage).into(ImageProfile);
        Glide.with(profilepic.this).load(graduatedimage).into(ImageViewNavProfile);
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

        EditTextpassword = findViewById(R.id.password);
        EditTextnewpassword = findViewById(R.id.newpassword);
        EditTextconfirmpassword = findViewById(R.id.confirmpassword);


        btnChoose = findViewById(R.id.btnChoose);
        btnUpload = findViewById(R.id.btnUpload);
        imageUpload = findViewById(R.id.imageUpload);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        profilepic.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        CODE_GALLERY_REQUEST
                );
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressDialog = new ProgressDialog(profilepic.this);
                progressDialog.setTitle("Uploading");
                progressDialog.setMessage("Please wait....");
                progressDialog.show();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_PROFILEPIC, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
//                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();\

                            Toast.makeText(getApplicationContext(),"Change Profile Sucess",Toast.LENGTH_LONG).show();
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

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Please Select Picture",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        String imageData = imageToString(bitmap);
                        params.put("image",imageData);
                        params.put("idno",idno);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(profilepic.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CODE_GALLERY_REQUEST){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"Select Image"),CODE_GALLERY_REQUEST);

            }else{
                Toast.makeText(getApplicationContext(),"You dont have permission to access gallery",Toast.LENGTH_SHORT).show();

            }
            return;
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODE_GALLERY_REQUEST && resultCode == RESULT_OK && data !=  null){
            Uri filePath = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageUpload.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 ,outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }

    @Override
    public void onClick(View view) {

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
                Intent intent1 = new Intent(profilepic.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.jobhiring:
                Intent intent2 = new Intent(profilepic.this,jobhiringinfo.class);
                startActivity(intent2);
                break;
            case R.id.news:
                Intent intent3 = new Intent(profilepic.this,newsinfo.class);
                startActivity(intent3);
                break;
            case R.id.event:
                Intent intent4 = new Intent(profilepic.this,eventinfo.class);
                startActivity(intent4);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                Intent intent5 = new Intent(profilepic.this,login.class);
                startActivity(intent5);
                break;


        }

        return true;
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