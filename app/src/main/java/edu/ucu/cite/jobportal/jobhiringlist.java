package edu.ucu.cite.jobportal;

public class jobhiringlist {
    private String id, jobtitle, companyname, email, contact, startdate, enddate, jobtype, location, specialization,link, qualification,description, jobstatus, courseuploaded, jobpostdate, minimumsalary, maximumsalary, views;


    public jobhiringlist(String id,String jobtitle, String companyname, String email, String contact, String startdate, String enddate, String jobtype, String location, String specialization, String link, String qualification, String description, String jobstatus,
                         String courseuploaded, String jobpostdate, String minimumsalary, String maximumsalary, String views) {

        this.id = id;
        this.jobtitle = jobtitle;
        this.companyname = companyname;
        this.email = email;
        this.contact = contact;
        this.startdate = startdate;
        this.enddate = enddate;
        this.jobtype = jobtype;
        this.location = location;
        this.specialization = specialization;
        this.link = link;
        this.qualification = qualification;
        this.description = description;
        this.jobstatus = jobstatus;
        this.courseuploaded = courseuploaded;
        this.jobpostdate = jobpostdate;
        this.minimumsalary = minimumsalary;
        this.maximumsalary = maximumsalary;
        this.views = views;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public String getJobTitle() {
        return jobtitle;
    }

    public void setJobTitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }


    public String getCompanyName() {
        return companyname;
    }

    public void setCompanyName(String companyname) {
        this.companyname = companyname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public String getStartDate() {
        return startdate;
    }

    public void setStartDate(String startdate) {
        this.startdate = startdate;
    }


    public String getEndDate() {
        return enddate;
    }

    public void setEndDate(String enddate) {
        this.enddate = enddate;
    }


    public String getJobType() {
        return jobtype;
    }

    public void setJobType(String jobtype) {
        this.jobtype = jobtype;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getJobStatus() {
        return jobstatus;
    }

    public void setJobStatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }


    public String getCourseUploaded() {
        return courseuploaded;
    }

    public void setCourseUploaded(String courseuploaded) {
        this.courseuploaded = courseuploaded;
    }


    public String getJobPostDate() {return jobpostdate;}

    public void setJobPostDate(String jobpostdate) {
        this.jobpostdate = jobpostdate;
    }

    public String getMinimumSalary() {return minimumsalary;}
    public void setMinimumSalary(String minimumsalary) {
        this.minimumsalary = minimumsalary;
    }

    public String getMaximumSalary() {return maximumsalary;}
    public void setMaximumSalary(String maximumsalary) {
        this.maximumsalary = maximumsalary;
    }

    public String getViews() {return views;}
    public void setViews(String views) {
        this.views = views;
    }









}
