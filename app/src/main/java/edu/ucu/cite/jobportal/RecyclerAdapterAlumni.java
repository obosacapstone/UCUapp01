package edu.ucu.cite.jobportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapterAlumni extends RecyclerView.Adapter<RecyclerAdapterAlumni.ProductViewHolder> {
    private Context mCtx;
    private List<alumnilist> alumnilists;

    public RecyclerAdapterAlumni(Context mCtx, List<alumnilist> alumnilists) {
        this.mCtx = mCtx;
        this.alumnilists = alumnilists;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.recyclealumni, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        alumnilist productalumni = alumnilists.get(position);

        String StringCollege = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getCollege();
        String StringYearGrad = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getYeargrad();
        String StringYearGrad1 = SharedPrefManager.getInstance(mCtx.getApplicationContext()).getYeargrad1();
        String StringAlumniYear = productalumni.getYeargrad();
        String StringAlumniYear1 = productalumni.getYeargrad1();


        String splitcourses[] = StringCollege.split(", ");


        for(int ii =0; ii<splitcourses.length; ii++) {

            if (ii == 0){

                    if (StringAlumniYear == StringYearGrad){
                        holder.btnevent.setVisibility(LinearLayout.VISIBLE);
                    }


            }

            if (ii == 1 ){
                if (StringAlumniYear == StringYearGrad || StringAlumniYear == StringYearGrad1 || StringAlumniYear1 == StringYearGrad || StringAlumniYear1 == StringYearGrad1){
                        holder.btnevent.setVisibility(LinearLayout.VISIBLE);
                    }


            }


            }
//        holder.btnevent.setVisibility(LinearLayout.VISIBLE);

        holder.textViewYear.setText(productalumni.getYeargrad());
        holder.textViewCourse.setText(productalumni.getCourse());
        holder.textViewAlumni.setText(productalumni.getFirstname());


    }

    @Override
    public int getItemCount() {
        return alumnilists.size();
    }

    public void filterList(List<alumnilist> filteredList) {

        alumnilists = filteredList;
        notifyDataSetChanged();

    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewCourse, textViewAlumni,textViewYear;
        CardView btnevent;
        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewYear = itemView.findViewById(R.id.year);
            textViewCourse = itemView.findViewById(R.id.course);
            textViewAlumni = itemView.findViewById(R.id.alumni);
            btnevent = itemView.findViewById(R.id.bgbutton);

        }
    }
}