package edu.ucu.cite.jobportal;

public class eventlist {
    private String uploadedevent,eventcollege,eventdetail, startdate, starttime, enddate, endtime, venue, address, description, eventtype, eventtopic, organizer, sponsor, eventimage, eventdate,interested, notinterested;
    private String eventid;

    public eventlist(String uploadedevent,String eventcollege,String eventdetail, String startdate, String starttime, String enddate, String endtime, String venue, String address, String description, String eventtype, String eventtopic,
                     String organizer, String sponsor, String eventimage, String eventdate, String interested, String notinterested,String eventid) {
        this.uploadedevent = uploadedevent;
        this.eventcollege = eventcollege;
        this.eventdetail = eventdetail;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
        this.venue = venue;
        this.address = address;
        this.description = description;
        this.eventtype = eventtype;
        this.eventtopic = eventtopic;
        this.organizer = organizer;
        this.sponsor = sponsor;
        this.eventimage = eventimage;
        this.eventdate = eventdate;
        this.interested = interested;
        this.notinterested = notinterested;
        this.eventid = eventid;


    }
    public String getUploadedevent() {
        return uploadedevent;
    }
    public void setUploadedevent(String uploadedevent) {
        this.uploadedevent = uploadedevent;
    }

    public String getEventCollege() {
        return eventcollege;
    }
    public void setEventCollege(String eventcollege) {
        this.eventcollege = eventcollege;
    }

    public String getEventDetaill() {
        return eventdetail;
    }
    public void setEventDetail(String eventdetail) {
        this.eventdetail = eventdetail;
    }


    public String getStartDate() {return startdate;}
    public void setStartDate(String startdate) {
        this.startdate = startdate;
    }



    public String getStartTime() {
        return starttime;
    }
    public void setStartTime(String starttime) {
        this.starttime = starttime;
    }



    public String getEndDate() {
        return enddate;
    }
    public void setEndDate(String enddate) {
        this.enddate = enddate;
    }



    public String getEndTime() {
        return endtime;
    }
    public void setEndTime(String endtime) {
        this.endtime = endtime;
    }



    public String getVenue() {
        return venue;
    }
    public void setVenue(String venue) {
        this.venue = venue;
    }



    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }



    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }



    public String getEventType() {
        return eventtype;
    }
    public void setEventType(String eventtype) {this.eventtype = eventtype;}



    public String getEventTopic() {
        return eventtopic;
    }
    public void setEventTopic(String eventtopic) {
        this.eventtopic = eventtopic;
    }



    public String getOrganizer() {
        return organizer;
    }
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }



    public String getSponsor() {
        return sponsor;
    }
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }



    public String getEventImage() {
        return eventimage;
    }
    public void setEventImage(String eventimage) {
        this.eventimage = eventimage;
    }



    public String getEventDate() {
        return eventdate;
    }
    public void setEventDate(String eventdate) {
        this.eventdate = eventdate;
    }


    public String getInterested() {
        return interested;
    }
    public void setInterested(String interested) {
        this.interested = interested;
    }


    public String getNotinterested() {
        return notinterested;
    }
    public void setNotinterested(String notinterested) {
        this.notinterested = notinterested;
    }


    public String getEventid() {
        return eventid;
    }
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }






}
