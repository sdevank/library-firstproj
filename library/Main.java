package com.sample.library;

import com.sample.library.items.Audio;
import com.sample.library.items.Book;
import com.sample.library.items.Item;
import com.sample.library.items.Video;
import com.sample.library.membership.Membership;
import com.sample.library.user.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Library hennepin;
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Audio> audios = new ArrayList<>();
    private static ArrayList<Video> videos = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static File booksFile = new File("books.csv");
    private static File audiosFile = new File("audios.csv");
    private static File videosFile = new File("videos.csv");
    private static File usersFile = new File("users.csv");
    private static int keyCount = 1;

    private static String readString(Scanner sc, String msg) {
        System.out.println(msg);
        return sc.nextLine();
    }

    private static int readInt(Scanner sc, String msg) {
        System.out.println(msg);
        int temp = sc.nextInt();
        sc.nextLine();
        return temp;
    }

    public static void main(String[] args) {

//        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=root&serverTimezone=UTC");
//            Statement statement = connection.createStatement();
//            statement.execute("CREATE TABLE springdatasupport.contacts (name text, phone integer,email text)");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        DatabaseSource db = new DatabaseSource();
//        if(db.open()) {
//            db.createTable(db.CREATE_TABLE_BOOKS);
//            db.createTable(db.CREATE_TABLE_AUDIOS);
//            db.createTable(db.CREATE_TABLE_VIDEOS);
//        }
//        db.close();


       hennepin = new Library("Hennepin");
        Address addressLibrary = new Address("9810 Drew Ave S", "Apt", "Bloomington", "MN", "55431");
        hennepin.setAddress(addressLibrary);
        System.out.println(hennepin);
        hennepin.setMemberships(Membership.values());

        readData();

        DatabaseSource db = new DatabaseSource();
        db.open();

        if(db.open()) {
            db.createTable(db.CREATE_TABLE_BOOKS);
            db.createTable(db.CREATE_TABLE_AUDIOS);
            db.createTable(db.CREATE_TABLE_VIDEOS);
        }

        db.setCommitOff();
        for(Book b: books){
            db.insertBookData(b);
        }
        for(Audio a: audios){
            db.insertAudioData(a);
        }

        for(Video v: videos){
            db.insertVideoData(v);
        }
        db.setCommitOn();

        db.queryTableById(db.QUERY_BOOKS,10);

        db.close();

//        hennepin.setBooks(books);
//        hennepin.setAudios(audios);
//        hennepin.setVideos(videos);
//        hennepin.setUsers(users);
//
//        Book b1Test = new Book("Stephen King","ABCD","1.0",123456710,200,10
//                          ,2,0);
//
//        hennepin.addItem(b1Test);
//
//        Book b2Test = new Book("Stephen King","ABCD","1.0",123456710,200,10
//                ,2,0);
//
//        hennepin.addItem(b2Test);


//        Scanner sc = new Scanner(System.in);
//        int tempUserId = readInt(sc, "Please enter your user ID: ");
//
//        for (User u : users) {
//            if (tempUserId == u.getUserId()) {
//                System.out.println("Proceed to search data.");
//            }
//        }
//
//        String searchString = readString(sc, "Search Items");
//
//        Map<Integer, Item> searchMap = hennepin.searchItem(searchString);
//        printLines(searchMap);
//
//        checkoutProcess(tempUserId, readInt(sc, "Enter a number from the item list that you wish to checkout"), searchMap);

    }

    private static void printLines(Map<Integer, Item> searchMap) {
        System.out.println("\n===============================================");
        System.out.println("Search Results:");
        for (int i : searchMap.keySet()) {
            System.out.println(i + ". " + searchMap.get(i).getTitle());
        }
        System.out.println("\n===============================================");
    }

    public static void checkoutProcess(int userId, int searchNum, Map<Integer, Item> searchMap) {

        Item checkoutItem = searchMap.get(searchNum);
        if (checkoutItem.getAvailableCopies() > 0) {
            for (User u : users) {
                if (userId == u.getUserId()) {
                    if (u.getRentedTitles().isEmpty()) {
                        u.addRentedItem(checkoutItem.getTitle());
                        System.out.println(u.getUserName() + " user rented an item: '" + checkoutItem.getTitle() + "' Successfully");
                        System.out.println("Total items rented by this user: " + u.getTotalItems());
                    } else {
                        for (String s : u.getRentedTitles()) {
                            System.out.println("3");
                            System.out.println("Inside Rented Titles");
                            if (s.equalsIgnoreCase(checkoutItem.getTitle())) {
                                System.out.println("Already rented this Title. Only one item per title.");
                            } else {
                                System.out.println("4");
                                u.addRentedItem(checkoutItem.getTitle());
                                System.out.println(u.getUserName() + " user rented an item: " + checkoutItem.getTitle() + " Successfully");
                                System.out.println("Total items rented by this user: " + u.getTotalItems());
                            }
                        }
                    }
                }
            }
        }
    }

    public static <T> void printItems(ArrayList<T> item){
        for (T i: item ){
            System.out.println(i);
        }
    }

    public static void readData() {
        try {
            readFromFile("Book");
//            printItems(books);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            readFromFile("Audio");
//            printItems(audios);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            readFromFile("Video");
//          printItems(videos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            readFromFile("User");
//            printItems(users);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(String type) throws IOException {
        FileReader fr = null;

        switch (type) {
            case "Book":
                fr = new FileReader(booksFile);
                break;
            case "Audio":
                fr = new FileReader(audiosFile);
                break;
            case "Video":
                fr = new FileReader(videosFile);
                break;
            case "User":
                fr = new FileReader(usersFile);
                break;
            default:
                break;
        }

        BufferedReader br = new BufferedReader(fr);
        String line;
        String[] temp;
        br.readLine();
        try {
            while ((line = br.readLine()) != null) {
                temp = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if (type.equalsIgnoreCase("Book")) {
                    addBookValues(temp);
                } else if (type.equalsIgnoreCase("Audio")) {
                    addAudioValues(temp);
                } else if (type.equalsIgnoreCase("Video")) {
                    addVideoValues(temp);
                } else if (type.equalsIgnoreCase("User")) {
                    addUserValues(temp);
                } else
                    System.out.println("What are we looking for?");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void addBookValues(String[] temp) {

        books.add(new Book(temp[0], temp[1], temp[2], Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),
                Integer.parseInt(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]))
        );
    }

    public static void addAudioValues(String[] temp) {

        audios.add(new Audio(temp[0], temp[1], temp[2], Integer.parseInt(temp[3]),
                Integer.parseInt(temp[4]), Integer.parseInt(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]),
                Boolean.parseBoolean(temp[8])));
    }

    public static void addVideoValues(String[] temp) {

        videos.add(new Video(temp[0], temp[1], Integer.parseInt(temp[2]), Boolean.parseBoolean(temp[3]), temp[4],
                Integer.parseInt(temp[5]), Integer.parseInt(temp[6]), Integer.parseInt(temp[7]), Integer.parseInt(temp[8])));
    }

    public static void addUserValues(String[] temp) {
        users.add(new User(Integer.parseInt(temp[0]), temp[1], new Address(temp[2], temp[3], temp[4], temp[5], temp[6]), temp[7],
                Boolean.parseBoolean(temp[8]), Integer.parseInt(temp[9]), Integer.parseInt(temp[10]), temp[11]));
    }

}
