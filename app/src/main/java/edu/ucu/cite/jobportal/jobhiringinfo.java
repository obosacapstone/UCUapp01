package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

public class jobhiringinfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar progressBar;
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager manager;
    NavigationView navigationView;
    DrawerLayout mydrawer ,drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle mtoggle=null ,  mytoggle=null;
    private  List<jobhiringlist> productList;
    jobhiringlist jobhiringlist;
    TextView TextViewTitleNav;
    SearchView searchView;
    private MenuItem item;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    RecyclerAdapter adapter;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager mmager;
    ArrayList list;
    TextView TextViewsearchempty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobhiringinfo);
        TextViewsearchempty = findViewById(R.id.searchempty);
        searchView = findViewById(R.id.search_view);

        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);
        progressBar = findViewById(R.id.progress);

        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();



        Glide.with(jobhiringinfo.this).load(graduatedimage).into(ImageViewNavProfile);
        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewNavIdno.setText(idno);

        recyclerView =findViewById(R.id.recycleview);
        manager = new GridLayoutManager(jobhiringinfo.this, 1);
        recyclerView.setLayoutManager(manager);
        productList = new ArrayList<>();

        loadProducts();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextViewsearchempty.setVisibility(LinearLayout.VISIBLE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);



                return true;
            }
        });
//        searchView.setQueryHint("Search Here.");
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
        TextViewTitleNav.setText("Recommended Jobs");

        mmager = new LinearLayoutManager(this);

//        String[] a = {"asd","asdas","asd","asdas","asd","asdas","asd","asdas","asd","asdas"};
            list = new ArrayList(Arrays.asList(productList));
//           adapter = new RecyclerAdapter(this, productList);

         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(manager);
         recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                 super.onScrollStateChanged(recyclerView, newState);
                 if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                     isScrolling = true;
                 }
             }

             @Override
             public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                 super.onScrolled(recyclerView, dx, dy);
                 currentItems = mmager.getChildCount();
                 totalItems = mmager.getItemCount();
                 scrollOutItems = mmager.findFirstVisibleItemPosition();

                 if (isScrolling && (currentItems + scrollOutItems == totalItems)){
                     isScrolling = false;
                     fetchData();

                 }
             }
         });


    }

    private void fetchData() {
        progressBar.setVisibility(View.VISIBLE);
     new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
            for (int i = 0 ; i < 2 ; i++){
                list.add(Math.floor(Math.random()*100) + "");
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
         }
     },5000);
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

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_JOBHIRING,
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
                                        product.getString("jobpostdate")


                                ));





                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new RecyclerAdapter(jobhiringinfo.this, productList);
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
                });

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

            case R.id.profile:
                Intent intent1 = new Intent(jobhiringinfo.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.jobhiring:
                Intent intent2 = new Intent(jobhiringinfo.this,jobhiringinfo.class);
                startActivity(intent2);
                break;
            case R.id.news:
                Intent intent3 = new Intent(jobhiringinfo.this,newsinfo.class);
                startActivity(intent3);
                break;
            case R.id.event:
                Intent intent4 = new Intent(jobhiringinfo.this,eventinfo.class);
                startActivity(intent4);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                Intent intent5 = new Intent(jobhiringinfo.this,login.class);
                startActivity(intent5);
                break;


        }

        return true;
    }


    public void Recommendedjobs(View view) {
        Intent intent = new Intent(jobhiringinfo.this,jobhiringinfoall.class);
        startActivity(intent);
    }
}