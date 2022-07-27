package edu.ucu.cite.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class location extends AppCompatActivity {
    Spinner SpinnerRegion,SpinnerProvince,SpinnerCity,SpinnerBarangay;

    ArrayList<String> arrayList_Region;
    ArrayAdapter<String> arrayAdapter_Region;
    String StringRegionCode;


    ArrayList<String> arrayList_Province;
    ArrayAdapter<String> arrayAdapter_Province;
    String StringProvinceCode;
    String StringProvinceCode2;
    String item2;

    ArrayList<String> arrayList_City;
    ArrayAdapter<String> arrayAdapter_City;
    String StringCityCode,StringCityCode3;
    String item3;

    ArrayList<String> arrayList_Barangay;
    ArrayAdapter<String> arrayAdapter_Barangay;
    String StringBarangayCode,StringBarangayCode3;
    String item4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        StringRegionCode  = SharedPrefManagerDatabase.getInstance(this).getRegionCode1();
        StringProvinceCode  = SharedPrefManagerDatabase.getInstance(this).getProvinceCode1();
        StringProvinceCode2  = SharedPrefManagerDatabase.getInstance(this).getProvinceCode2();
        StringCityCode  = SharedPrefManagerDatabase.getInstance(this).getCityCode2();
        StringCityCode3  = SharedPrefManagerDatabase.getInstance(this).getCityCode3();
        StringBarangayCode  = SharedPrefManagerDatabase.getInstance(this).getBarangay();
        StringBarangayCode3  = SharedPrefManagerDatabase.getInstance(this).getBarangayCode3();
        SpinnerRegion = (Spinner) findViewById(R.id.region);
        SpinnerProvince = (Spinner) findViewById(R.id.province);
        SpinnerCity = (Spinner) findViewById(R.id.city);
        SpinnerBarangay = (Spinner) findViewById(R.id.barangay);


        arrayList_Region = new ArrayList<>();
        String StringRegion = SharedPrefManagerDatabase.getInstance(this).getRegion();
        String StringRegionSplit[] = StringRegion.split(",");
        for(int i = 0; i<StringRegionSplit.length; i++){
            arrayList_Region.add(StringRegionSplit[i]);
        }

        arrayAdapter_Region = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Region);
        SpinnerRegion.setAdapter(arrayAdapter_Region);
        item2 = "";

        arrayList_Province = new ArrayList<>();
        String StringProvince = SharedPrefManagerDatabase.getInstance(this).getCity();
        String StringProvinceSplit[] = StringProvince.split(",");
        for(int i = 0; i<StringProvinceSplit.length; i++){
            arrayList_Province.add(StringProvinceSplit[i]);
        }

        arrayAdapter_Province = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Province);
        SpinnerCity.setAdapter(arrayAdapter_Province);
        item3 = "";



        arrayList_City = new ArrayList<>();
        String StringCity = SharedPrefManagerDatabase.getInstance(this).getBarangay();
        String StringCitySplit[] = StringCity.split(",");
        for(int i = 0; i<StringCitySplit.length; i++){
            arrayList_City.add(StringCitySplit[i]);
        }

        arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_City);
        SpinnerBarangay.setAdapter(arrayAdapter_City);
        item4 = "";









        SpinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String StringRegionCodeSplit[] = StringRegionCode.split(",");
                for(int y = 0; y<StringRegionCodeSplit.length; y++){
                    String StringRegionCodeSplit2[] = StringRegionCodeSplit[y].split("/");
                    if (item.equals(StringRegionCodeSplit2[0])){
                        item2 = StringRegionCodeSplit2[1];
                    }

                }



                arrayList_Province = new ArrayList<>();
                String StringProvinceSplit[] = StringProvinceCode.split(",");
                for(int y = 0; y<StringProvinceSplit.length; y++){
                    String StringProvinceSplitCode[] = StringProvinceSplit[y].split("/");
                    for(int a = 1; a<StringProvinceSplitCode.length; a++) {



                        if (item2.equals(StringProvinceSplitCode[1])) {
                            arrayList_Province.add(StringProvinceSplitCode[0]);
                        }



                    }
                }

                arrayAdapter_Province = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Province);

                SpinnerProvince.setAdapter(arrayAdapter_Province);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //city
        SpinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String StringProvinceCodeSplit[] = StringProvinceCode2.split(",");
                for(int y = 0; y<StringProvinceCodeSplit.length; y++){
                    String StringProvinceCodeSplit2[] = StringProvinceCodeSplit[y].split("/");
                    if (item.equals(StringProvinceCodeSplit2[0])){
                        item3 = StringProvinceCodeSplit2[1];
                    }

                }



                arrayList_City = new ArrayList<>();
                String StringCitySplit[] = StringCityCode.split(",");
                for(int y = 0; y<StringCitySplit.length; y++){
                    String StringCitySplitCode[] = StringCitySplit[y].split("/");
                    for(int a = 1; a<StringCitySplitCode.length; a++) {



                        if (item3.equals(StringCitySplitCode[1])) {
                            arrayList_City.add(StringCitySplitCode[0]);
                        }


                    }
                }

                arrayAdapter_City = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_City);

                SpinnerCity.setAdapter(arrayAdapter_City);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //barangay
        SpinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                String StringCityCodeSplit[] = StringCityCode3.split(",");
                for(int y = 0; y<StringCityCodeSplit.length; y++){
                    String StringCityCodeSplit2[] = StringCityCodeSplit[y].split("/");
                    if (item.equals(StringCityCodeSplit2[0])){
                        item4 = StringCityCodeSplit2[1];
                    }
//                    item4 = StringCityCodeSplit2[0];
                }



                arrayList_Barangay = new ArrayList<>();
                String StringBarangaySplit[] = StringBarangayCode3.split(",");
                for(int y = 0; y<StringBarangaySplit.length; y++){
                    String StringBarangaySplitCode[] = StringBarangaySplit[y].split("/");
                    for(int a = 1; a<StringBarangaySplitCode.length; a++) {

                        if (item4.equals(StringBarangaySplitCode[1])) {
                            arrayList_Barangay.add(StringBarangaySplitCode[0]);
                        }
//                        arrayList_Barangay.add(item4);

                    }
                }

                arrayAdapter_Barangay = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Barangay);

                SpinnerBarangay.setAdapter(arrayAdapter_Barangay);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}