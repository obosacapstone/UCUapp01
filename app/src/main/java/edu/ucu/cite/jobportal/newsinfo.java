package edu.ucu.cite.jobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import edu.ucu.cite.jobportal.nointernetconnection.NetworkChangeListener;

public class newsinfo extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle mtoggle=null,mytoggle=null;
    androidx.appcompat.widget.Toolbar toolbar;
    ProgressBar progressBar;
    RecyclerView recyclerViewNews;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager managernews;
    private  List<newslist> productListNews;
    newsinfo newsinfo;
    DrawerLayout mydrawer;
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    SwipeRefreshLayout SwiperefreshLayout;
    TextView TextViewTitleNav;
    SearchView searchView;
    RecyclerAdapterNews adapterNews;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsinfo);


        TextViewTitleNav = findViewById(R.id.titlenav);
        TextViewTitleNav.setText("News");


        ImageViewNavProfile = findViewById(R.id.navprofile);
        TextViewNavFullname = findViewById(R.id.navfullname);
        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);

        String firstname = SharedPrefManager.getInstance(this).getFirstname();
        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
        String lastname = SharedPrefManager.getInstance(this).getLastname();
        String idno = SharedPrefManager.getInstance(this).getIDno();
        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();



        Glide.with(newsinfo.this).load(graduatedimage).into(ImageViewNavProfile);
        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
        TextViewNavIdno.setText(idno);



        recyclerViewNews =findViewById(R.id.recycleviewnews);
        managernews = new GridLayoutManager(newsinfo.this, 1);
        recyclerViewNews.setLayoutManager(managernews);
        productListNews = new ArrayList<>();

        loadNews();
        mydrawer = findViewById(R.id.mydrawer);
        navigationView = findViewById(R.id.navigationdrawer);

        toolbar=findViewById(R.id.sidetoolbar);
        mytoggle = new ActionBarDrawerToggle(this,mydrawer, toolbar, R.string.open, R.string.close);

        mydrawer.addDrawerListener(mytoggle);
        navigationView.bringToFront();
        mytoggle.syncState();

        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);

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
        List<newslist>filteredList = new ArrayList<>();
        for (newslist item : productListNews){
            if (item.getNewsDetail().toLowerCase().contains(newText.toLowerCase()) || item.getNewsTopic().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);

            }
        }
        adapterNews.filterList(filteredList);
    }




    private void loadNews() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.URL_NEWS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        productListNews.clear();
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject news = array.getJSONObject(i);


                                productListNews.add(new newslist(
                                        news.getString("newsdetail"),
                                        news.getString("venue"),
                                        news.getString("address"),
                                        news.getString("description"),
                                        news.getString("category"),
                                        news.getString("newstopic"),
                                        news.getString("newsimage"),
                                        news.getString("newsdate")
                                ));





                            }

                            //creating adapter object and setting it to recyclerview
                            adapterNews = new RecyclerAdapterNews(newsinfo.this, productListNews);
                            recyclerViewNews.setAdapter(adapterNews);
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
                Intent intent1 = new Intent(newsinfo.this,profile.class);
                startActivity(intent1);
                break;
            case R.id.jobhiring:
                Intent intent2 = new Intent(newsinfo.this,jobhiringinfo.class);
                startActivity(intent2);
                break;
            case R.id.news:
                Intent intent3 = new Intent(newsinfo.this,newsinfo.class);
                startActivity(intent3);
                break;
            case R.id.event:
                Intent intent4 = new Intent(newsinfo.this,eventinfo.class);
                startActivity(intent4);
                break;
            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finishAffinity();
                Intent intent5 = new Intent(newsinfo.this,login.class);
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