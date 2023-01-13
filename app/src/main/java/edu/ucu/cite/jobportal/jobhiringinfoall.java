package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class jobhiringinfoall extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager manager;
    NavigationView navigationView;
    DrawerLayout mydrawer ,drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle mtoggle=null ,  mytoggle=null;
    private List<jobhiringlist> productList;
    jobhiringlist jobhiringlist;
    TextView TextViewTitleNav;
    RecyclerAdapterAll adapter;
    private MenuItem item;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    SearchView searchView;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    ProgressBar progressBar;
    RelativeLayout relativeLayoutProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobhiringinfoall);
        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);
        progressBar = findViewById(R.id.progress);
        relativeLayoutProgressBar = findViewById(R.id.relativeprogress);

        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();

        BottomNavigationView bottomNavigationView = findViewById(R.id.naview);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setSelectedItemId(R.id.Jobs);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){

                case R.id.Jobs:
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
                    startActivity(new Intent(getApplicationContext(),profile.class));
                    finish();
                    overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                    return true;


            }
            return false;
        });

        Glide.with(jobhiringinfoall.this).load(graduatedimage).into(ImageViewNavProfile);
        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewNavIdno.setText(idno);

        recyclerView =findViewById(R.id.recycleview);
        manager = new GridLayoutManager(jobhiringinfoall.this, 1);
        recyclerView.setLayoutManager(manager);
        productList = new ArrayList<>();

        loadProducts();

        mydrawer = findViewById(R.id.mydrawer);
        navigationView = findViewById(R.id.navigationdrawer);

        toolbar=findViewById(R.id.sidetoolbar);
        mytoggle = new ActionBarDrawerToggle(this,mydrawer, toolbar, R.string.open, R.string.close);

        mydrawer.addDrawerListener(mytoggle);
        navigationView.bringToFront();
        mytoggle.syncState();

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        //nav bar
        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("All Jobs");

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }
    private void filter(String newText) {
        List<jobhiringlist>filteredList = new ArrayList<>();
        for (jobhiringlist item : productList){
            if (item.getCompanyName().toLowerCase().contains(newText.toLowerCase()) || item.getJobTitle().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);

            }
        }
        adapter.filterList(filteredList);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mytoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    private void loadProducts() {
        progressBar.setVisibility(View.VISIBLE);
        relativeLayoutProgressBar.setVisibility(View.VISIBLE);
        String skills = SharedPrefManager.getInstance(this).getSpecialization();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_JOBHIRINGALL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        productList.clear();
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list

                                    productList.add(new jobhiringlist(
                                            product.getString("id"),
                                            product.getString("jobtitle"),
                                            product.getString("companyname"),
                                            product.getString("email"),
                                            product.getString("contact"),
                                            product.getString("startdate"),
                                            product.getString("enddate"),
                                            product.getString("jobtype"),
                                            product.getString("location"),
                                            product.getString("specialization"),
                                            product.getString("link"),
                                            product.getString("qualification"),
                                            product.getString("description"),
                                            product.getString("jobstatus"),
                                            product.getString("courseuploaded"),
                                            product.getString("jobpostdate"),
                                            product.getString("minimumsalary"),
                                            product.getString("maximumsalary"),
                                            product.getString("views")


                                    ));

                                progressBar.setVisibility(View.GONE);
                                relativeLayoutProgressBar.setVisibility(View.GONE);




                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new RecyclerAdapterAll(jobhiringinfoall.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("anyText",response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("skills",skills);


                return params;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        Volley.newRequestQueue(this).add(stringRequest);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.Alumni:
                Intent intent1 = new Intent(jobhiringinfoall.this,alumni.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Trending:
                Intent intent2 = new Intent(jobhiringinfoall.this,trendinginfo.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.Bookmark:
                Intent intent3 = new Intent(jobhiringinfoall.this,bookmarkinfo.class);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(jobhiringinfoall.this,login.class);
                startActivity(intent5);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;


        }

        return true;
    }


    public void Alljobs(View view) {
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        Intent intent = new Intent(jobhiringinfoall.this,jobhiringinfo.class);
        startActivity(intent);
        overridePendingTransition(0, 0);

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