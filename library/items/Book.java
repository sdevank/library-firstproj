package com.sample.library.items;

public class Book extends Item {
    private String bookVersion;
    private Integer isbn;
    private int numberOfPages;

    public Book(String setAuthor, String setTitle, String setBookVersion, int setIsbn, int setNumberOfPages,
                int totalCopies, int setAvailableCopies, int setWaitListCount) {
        super.setAuthor(setAuthor);
        super.setTitle(setTitle);
        super.totalCopies = totalCopies;
        super.setAvailableCopies(setAvailableCopies);
        super.setWaitListCount(setWaitListCount);
        bookVersion = setBookVersion;
        isbn = setIsbn;
        numberOfPages = setNumberOfPages;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    @Override
    public int getWaitListCount() {
        return super.getWaitListCount();
    }

    @Override
    public int getAvailableCopies() {
        return super.getAvailableCopies();
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public String getAuthor() {
        return super.getAuthor();
    }

    public Integer getIsbn() {
        return isbn;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public int getTotalCopies() {
        return super.getTotalCopies();
    }

    @Override
    public boolean equals(Object obj) {

        if(this==obj){
            return true;
        }
        if((obj == null)||(this.getClass()!=obj.getClass())) {
            return false;
        }

        Integer objIsbn = ((Book)obj).getIsbn();

        if (this.getIsbn().equals(objIsbn)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getIsbn().hashCode();
    }

    public String toString() {
        return "Book title: " + getTitle() + ", Author " + getAuthor() + ", Total copies " + totalCopies + " ,Available copies " + getAvailableCopies() +
                " , On hold count " + getWaitListCount();
    }

}
