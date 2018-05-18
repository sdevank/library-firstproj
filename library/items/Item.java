package com.sample.library.items;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private String title;
    private String author;
    private int waitListCount;
    private int availableCopies;
    public int totalCopies;

    public int getWaitListCount() {
        return waitListCount;
    }

    public void setWaitListCount(int waitListCount) {
        this.waitListCount = waitListCount;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String toString() {
        return "Book title: " + getTitle() + ", Author " + getAuthor() + ", Total copies " + totalCopies + " ,Available copies " + getAvailableCopies() +
                " , On hold count " + getWaitListCount();
    }
}
