package edu.ucu.cite.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class newsinfodata extends AppCompatActivity {
    TextView textviewnewsdetaildata,textviewnewstopicdata;
    ImageView imageviewnewwsimagedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsinfodata);
        textviewnewsdetaildata = findViewById(R.id.newsdetaildata);
        textviewnewstopicdata = findViewById(R.id.newstopicdata);
        imageviewnewwsimagedata = findViewById(R.id.newsimagedata);
        String image = getIntent().getStringExtra("newsimage");


        textviewnewsdetaildata.setText(getIntent().getStringExtra("newsdetail"));
        textviewnewstopicdata.setText(getIntent().getStringExtra("newstopic"));
        Glide.with(newsinfodata.this).load(image).into(imageviewnewwsimagedata);
    }
}