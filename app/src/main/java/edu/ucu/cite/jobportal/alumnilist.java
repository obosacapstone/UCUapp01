package edu.ucu.cite.jobportal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class alumnilist{
        public String heading;
        private String idno, course, course1, yeargrad, yeargrad1, firstname, middlename, lastname;


    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse1() {
        return course1;
    }

    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    public String getYeargrad() {
        return yeargrad;
    }

    public void setYeargrad(String yeargrad) {
        this.yeargrad = yeargrad;
    }

    public String getYeargrad1() {
        return yeargrad1;
    }

    public void setYeargrad1(String yeargrad1) {
        this.yeargrad1 = yeargrad1;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public alumnilist(String idno, String course, String course1, String yeargrad, String yeargrad1, String firstname, String middlename, String lastname) {
            this.idno = idno;
            this.course = course;
            this.course1 = course1;
            this.yeargrad = yeargrad;
            this.yeargrad1 = yeargrad1;
            this.firstname = firstname;
            this.middlename = middlename;
            this.lastname = lastname;



        }





}
