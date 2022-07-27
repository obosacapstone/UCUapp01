package edu.ucu.cite.jobportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class RecyclerAdapterEvent extends RecyclerView.Adapter<RecyclerAdapterEvent.ProductViewHolder>{
    private Context mCtx;
    private List<eventlist> eventlists;
    Date DateUploaded,DateStartDate , DateEndDate,qEndDate,qCurrentDate;
    String StringStartDate,StringEndDate,StartTime,EndTime,StringqEndDate,StringqCurrentDate;

    String StringInterested, StringNotInterested,StringIdno;
    Integer IntegerInterested;
    public RecyclerAdapterEvent(Context mCtx, List<eventlist> eventlists) {
        this.mCtx = mCtx;
        this.eventlists = eventlists;
    }

    @Override
    public RecyclerAdapterEvent.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recycleevent, null);

        return new RecyclerAdapterEvent.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        eventlist productevent = eventlists.get(position);
        holder.textViewEventDetail.setText(productevent.getEventDetaill());
        holder.textViewInterested.setText(productevent.getEventid());
        holder.btnevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx,interested.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("eventid", productevent.getEventid());
                intent.putExtra("eventdetail", productevent.getEventDetaill());
                intent.putExtra("eventdate", productevent.getEventDate());
                intent.putExtra("eventstartdate", productevent.getStartDate());
                intent.putExtra("eventstarttime", productevent.getStartTime());
                intent.putExtra("eventenddate", productevent.getEndDate());
                intent.putExtra("eventendtime", productevent.getEndTime());
                intent.putExtra("eventimage", productevent.getEventImage());
                intent.putExtra("eventaddress", productevent.getAddress());
                intent.putExtra("eventvenue", productevent.getVenue());
                intent.putExtra("eventdescription", productevent.getDescription());
                intent.putExtra("eventsponsor", productevent.getSponsor());
                intent.putExtra("eventorganizer", productevent.getOrganizer());
                intent.putExtra("eventinterested", productevent.getInterested());
                intent.putExtra("eventnotinterested", productevent.getNotinterested());

                mCtx.startActivity(intent);
            }
        });
        //uploaded date
        String date = productevent.getEventDate();
        SimpleDateFormat input = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat output = new SimpleDateFormat("dd MMM yyyy");
        try {
            DateUploaded = input.parse(date);                 // parse input
            holder.textViewEventdate.setText(output.format(DateUploaded));    // format output
        } catch (ParseException e) {
            e.printStackTrace();
        }



        //startdate
        String date1 = productevent.getStartDate();
        SimpleDateFormat input1 = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat output1 = new SimpleDateFormat("dd MMM yyyy");
        try {
            DateStartDate = input.parse(date1);
            StringStartDate = output.format(DateStartDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //enddate
        String date2 = productevent.getEndDate();
        SimpleDateFormat input2 = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat output2 = new SimpleDateFormat("dd MMM yyyy");
        try {
            DateEndDate = input.parse(date2);
            StringEndDate = output.format(DateEndDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //time
        String dateString3 = productevent.getStartTime();
        String dateString4 = productevent.getEndTime();
        //old format
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        try{
            Date date3 = sdf.parse(dateString3);
            Date date4 = sdf.parse(dateString4);
            //new format
            SimpleDateFormat sdf3 = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat sdf4 = new SimpleDateFormat("hh:mm aa");
            //formatting the given time to new format with AM/PM
            StartTime = sdf3.format(date3);
            EndTime = sdf4.format(date4);
        }catch(ParseException e){
            e.printStackTrace();
        }


        String StringDateeDuration = "<b>Date: </b>" + StringStartDate + " to " + StringEndDate + "<br>"
                + "<b>Time: </b>" + StartTime + " to " + EndTime;

        holder.textViewDateDuration.setText(HtmlCompat.fromHtml(StringDateeDuration, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        Glide.with(mCtx).load(productevent.getEventImage()).into(holder.imageViewEventImage);

        String StringAddress = "<b> Address: </b>" + productevent.getAddress();
        holder.textViewAddress.setText(HtmlCompat.fromHtml(StringAddress, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        String StringVenue = "<b> Venue: </b>" + productevent.getVenue();
        holder.textViewVenue.setText(HtmlCompat.fromHtml(StringVenue, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

        String StringSponsor = productevent.getSponsor();
        String splitted[] = StringSponsor.split(",,,");
        String StringAllSponsor = "";
        for(int i =0; i<splitted.length; i++){
            StringAllSponsor +=   splitted[i] + "<br>";
        }

        String StringOrganizer = productevent.getOrganizer();
        String splitted1[] = StringOrganizer.split(",,,");
        String StringAllOrganizer = "";
        for(int i =0; i<splitted1.length; i++){
            StringAllOrganizer +=   splitted1[i] + "<br>";
        }

//        String StringDescription = productevent.getDescription() +
//                "<br><br><br><p><b>Sponsor</b><br>" + StringAllSponsor + "</p><br>" +
//                "<p><b>Organizer/s</b><br>" + StringAllOrganizer + "</p>";

        String StringDescription = productevent.getDescription();




        holder.textViewDescription.setText(HtmlCompat.fromHtml(StringDescription, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_PARAGRAPH));

//        holder.textViewSponsor.setText(productevent.getSponsor());
//
//        holder.textViewOrganizer.setText(productevent.getOrganizer());


        //enddate
        String date3 = productevent.getEndDate();
        SimpleDateFormat input3 = new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat output3 = new SimpleDateFormat("yMMdd");
        try {
            qEndDate = input.parse(date3);
            StringqEndDate = output3.format(qEndDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //currentdate
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yMMdd");
        StringqCurrentDate = sdf1.format(c.getTime());
        int IntegerCurrentDate = Integer.parseInt(StringqCurrentDate);
        int IntegerEnddate = Integer.parseInt(StringqEndDate);
        if (IntegerEnddate > IntegerCurrentDate){
//            holder.linearLayoutdatevalidation.setVisibility(LinearLayout.VISIBLE);
        }

        //related event
        holder.btnevent.setVisibility(LinearLayout.GONE);


        String StringCourse = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getYeargrad();
        String StringUploaded = productevent.getUploadedevent();
        String splitteduploadedevent[] = StringUploaded.split(",,,");


            for(int x=0; x<splitteduploadedevent.length; x++){
                if (splitteduploadedevent[x].equals(StringCourse)){
                    holder.btnevent.setVisibility(LinearLayout.VISIBLE);
                }



        }




    }

    @Override
    public int getItemCount() {
        return eventlists.size();
    }
    public void filterList(List<eventlist> filteredList){

        eventlists = filteredList;
        notifyDataSetChanged();

    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewEventDetail,textViewEventdate ,textViewDateDuration ,textViewAddress ,textViewVenue ,textViewDescription ,textViewInterested ;
        CardView btnevent;
        ImageView imageViewEventImage;
        LinearLayout linearLayoutdatevalidation;
        Button ButtonInterested;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewEventDetail = itemView.findViewById(R.id.eventdetail);
            textViewEventdate = itemView.findViewById(R.id.eventdate);
            textViewDateDuration = itemView.findViewById(R.id.dateduration);
            imageViewEventImage  = itemView.findViewById(R.id.eventimage);
            textViewAddress = itemView.findViewById(R.id.address);
            textViewVenue = itemView.findViewById(R.id.venue);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewInterested = itemView.findViewById(R.id.qinterested);
            linearLayoutdatevalidation = itemView.findViewById(R.id.datevalidation);
            btnevent = itemView.findViewById(R.id.bgbutton);


        }





    }




}