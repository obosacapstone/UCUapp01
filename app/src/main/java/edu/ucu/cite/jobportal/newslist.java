package edu.ucu.cite.jobportal;

public class newslist {

    public String heading;
    private String newsdetail, venue, address, description, category, newstopic, newsimage, newsdate;


    public newslist(String newsdetail, String venue, String address, String description, String category, String newstopic, String newsimage, String newsdate) {
        this.newsdetail = newsdetail;
        this.venue = venue;
        this.address = address;
        this.description = description;
        this.category = category;
        this.newstopic = newstopic;
        this.newsimage = newsimage;
        this.newsdate = newsdate;
    }

    public String getNewsDetail() {
        return newsdetail;
    }

    public void setNewsDetail(String newsdetail) {
        this.newsdetail = newsdetail;
    }



    public String getVenue() {return venue;}

    public void setVenue(String venue) {
        this.venue = venue;
    }



    public String getAddress() {return address;}

    public void setAddress(String address) {
        this.address = address;
    }


    public String getDescription() {return description;}

    public void setDescription(String description) {
        this.description = description;
    }


    public String getCategory() {return category;}

    public void setCategory(String category) {
        this.category = category;
    }


    public String getNewsTopic() {return newstopic;}

    public void setNewsTopic(String newstopic) {
        this.newstopic = newstopic;
    }




    public String getNewsImage() {
        return newsimage;
    }

    public void setNewsImage(String newsimage) {
        this.newsimage = newsimage;
    }


    public String getNewsDate() {return newsdate;}

    public void setNewsDate(String newsdate) {
        this.newsdate = newsdate;
    }
}
