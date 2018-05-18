package com.sample.library.items;

public class Audio extends Item {
    private String narratedBy;
    private int duration;
    private boolean isDownloadable;
    private int audioSize;
    public final static int USER_LIMIT = 5;

    public Audio(String setAuthor, String setTitle, String setNarratedBy, int setAvailableCopies, int setWaitListCount, int totalCopies,
                                 int setDuration, int setAudioSize, boolean setDownloadable) {

        super.setAuthor(setAuthor);
        super.setTitle(setTitle);
        this.narratedBy=setNarratedBy;
        super.setAvailableCopies(setAvailableCopies);
        super.setWaitListCount(setWaitListCount);
        super.totalCopies = totalCopies;
        this.duration=setDuration;
        this.audioSize=setAudioSize;
        this.isDownloadable=true;
    }

    public String getNarratedBy() {
        return narratedBy;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public int getAudioSize() {
        return audioSize;
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

    public String toString() {
        return "Audio title: " + getTitle() + ", Author " + getAuthor() + ", Total copies " + totalCopies + " ,Available copies " + getAvailableCopies() +
                " , On hold count " + getWaitListCount();
    }
}
