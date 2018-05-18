package com.sample.library;

import com.sample.library.items.Audio;
import com.sample.library.items.Book;
import com.sample.library.items.Item;
import com.sample.library.items.Video;
import com.sample.library.membership.Membership;
import com.sample.library.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private String name;
    private Address address;
    private Membership[] memberships;
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private ArrayList<Audio> audios;
    private ArrayList<Video> videos;

    Library(String name) {
        this.name = name;
    }

    public Map<Integer, Item> searchItem(String searchString) {
        int keyCount = 0;
        Map<Integer, Item> searchMap = new HashMap<>();
        try {
            for (Item x : getAllItems()) {
                if (x.getTitle().contains(searchString)) {
                    searchMap.put(keyCount, x);
                    keyCount++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Search completed in Books .....");
        }
        return searchMap;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        items.addAll(books);
        items.addAll(audios);
        items.addAll(videos);
        return items;
    }

    public void addItem(Item item) {

        int i = getAllItems().indexOf(item);
        if (i == -1) {
            if (item instanceof Book) {
                books.add((Book) item);
            } else if (item instanceof Audio) {
                audios.add((Audio) item);
            } else {
                videos.add((Video) item);
            }
            System.out.println("Item added to Library: " + item);
        } else {
            int newAvailableCopies = getAllItems().get(i).getAvailableCopies() + item.getAvailableCopies();
            getAllItems().get(i).setAvailableCopies(newAvailableCopies);
            System.out.println("Item count updated: " + getAllItems().get(i).getAvailableCopies());
        }
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public Membership[] getMemberships() {
        return memberships;
    }

    public void setMemberships(Membership[] memberships) {
        this.memberships = memberships;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public String toString() {
        return "Library name: " + name + " and address: " + address;
    }
}
