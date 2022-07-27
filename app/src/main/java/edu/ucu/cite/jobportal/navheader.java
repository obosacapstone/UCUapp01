package edu.ucu.cite.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class navheader extends AppCompatActivity {
    TextView TextViewNavFullname, TextViewNavIdno;
    ImageView ImageViewNavProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navheader);

//        ImageViewNavProfile = findViewById(R.id.navprofile);
//        TextViewNavFullname = findViewById(R.id.navfullname);
//        TextViewNavIdno = (TextView)  findViewById(R.id.navidno);
//
//
//        String firstname = SharedPrefManager.getInstance(this).getFirstname();
//        String middlename = SharedPrefManager.getInstance(this).getMiddlename();
//        String lastname = SharedPrefManager.getInstance(this).getLastname();
//        String idno = SharedPrefManager.getInstance(this).getIDno();
//        String graduatedimage = SharedPrefManager.getInstance(this).getGraduatedimage();
//
//
//
//        Glide.with(navheader.this).load(graduatedimage).into(ImageViewNavProfile);
//        TextViewNavFullname.setText(firstname + " " + middlename + " " + lastname);
//        TextViewNavIdno.setText("0000000");

    }
}