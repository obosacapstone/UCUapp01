package edu.ucu.cite.jobportal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class alumni extends AppCompatActivity {
    TextView TextViewAlumni;
    ProgressDialog progressDialog;
    String idno;
    String alumni = "";
    int courseoutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        TextViewAlumni = findViewById(R.id.alumni);
        String yeargrad = SharedPrefManager.getInstance(this).getYeargrad();
        idno = SharedPrefManager.getInstance(this).getIDno();
        alumni = SharedPrefManagerAlumni.getInstance(this).getAlumni();
        String StringOutput = "", StringAlumniName = "", finaloutput = "";

//        TextViewAlumni.setText(alumni);
        alumnudatebase();

        if(alumni != null){



            String splityeargrad[] = alumni.split(",/,/,/");


                String splitcourses[] = splityeargrad[1].split("///");

                for(int ii =0; ii<splitcourses.length; ii++) {


                    String splitalumnilist[] = splitcourses[ii].split("&&&&");
                    for (int iii = 1; iii < splitalumnilist.length; iii++) {

                        StringAlumniName = StringAlumniName + splitalumnilist[iii] +"<br>";

                    }
                    StringOutput = "" +splitalumnilist[0] + "<br><br>";

                    finaloutput = finaloutput  + StringOutput +  StringAlumniName;
                    StringOutput = "";
                    StringAlumniName = "";
                }


            TextViewAlumni.setText(HtmlCompat.fromHtml(finaloutput, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        }else {
            startActivity(new Intent(getApplicationContext(), alumni.class));
            finish();
        }


    }

    private void alumnudatebase() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_ALUMNI,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);



                                if (!jsonObject.getBoolean("error")) {
                                    SharedPrefManagerAlumni.getInstance(getApplicationContext())
                                            .userDatabase(
                                                    jsonObject.getString("alumni")

                                            );


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.e("anyText", response);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();

                            Toast.makeText(getApplicationContext(), "Please Reload ", Toast.LENGTH_SHORT).show();

                        }


                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("idno", idno);
                    return params;

                }

            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}