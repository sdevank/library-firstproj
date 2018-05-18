package com.sample.library.items;

public class Video extends Item {
    private int duration;
    private int year;
    private String videoType;
    private boolean isAdultRated;

    public Video(String setTitle, String setAuthor, int setDuration, boolean setAdultRated, String setVideoType, int setYear,
                                 int totalCopies, int setAvailableCopies, int setWaitListCount) {

        super.setTitle(setTitle);
        super.setAuthor(setAuthor);
        this.duration=setDuration;
        this.isAdultRated=setAdultRated;
        this.videoType=setVideoType;
        this.year=setYear;
        super.totalCopies = totalCopies;
        super.setAvailableCopies(setAvailableCopies);
        super.setWaitListCount(setWaitListCount);

    }

    public int getDuration() {
        return duration;
    }

    public int getYear() {
        return year;
    }

    public String getVideoType() {
        return videoType;
    }

    public boolean isAdultRated() {
        return isAdultRated;
    }

    @Override
    public boolean equals(Object obj) {

        if(this==obj){
            return true;
        }
        if((obj == null)||(this.getClass()!=obj.getClass())) {
            return false;
        }

        String objIsbn = ((Book)obj).getTitle();

        if (this.getTitle()== objIsbn){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getTitle().hashCode();
    }

    public String toString(){
        return "Video title: "+getTitle()+", Author "+getAuthor()+", Total copies "+totalCopies+" ,Available copies "+getAvailableCopies()+
                " , On hold count "+getWaitListCount();
    }
}
