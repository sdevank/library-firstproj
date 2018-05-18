package com.sample.library;

import com.sample.library.items.Audio;
import com.sample.library.items.Book;
import com.sample.library.items.Video;

import java.sql.*;

public class DatabaseSource {
    private static final String url = "jdbc:mysql://localhost:3306/?user=root&password=rootroot&autoReconnect=true&useSSL=false&serverTimezone=UTC";

    public static final String SCHEMA = "JAVA.";
    public static final String DATATYPE_INT = "integer";
    public static final String DATATYPE_TEXT = "text";
    public static final String DATATYPE_BOOLEAN = "boolean";
    public static final String TABLE_BOOKS = SCHEMA + "books";
    public static final String TABLE_AUDIOS = SCHEMA + "audios";
    public static final String TABLE_VIDEOS = SCHEMA + "videos";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_VERSION = "version";
    public static final String COLUMN_ISBN = "isbn";
    public static final String COLUMN_NO_OF_PAGES = "no_of_pages";
    public static final String COLUMN_TOTAL_COPIES = "total_copies";
    public static final String COLUMN_AVAILABLE_COPIES = "available_copies";
    public static final String COLUMN_WAIT_LIST = "wait_list";
    public static final String COLUMN_NARRATED_BY = "narrated_by";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_DOWNLOADABLE = "downloadable";
    public static final String COLUMN_ADULT_RATED = "adult_rated";
    public static final String COLUMN_VIDEO_TYPE = "video_type";
    public static final String COLUMN_RELEASE_YEAR = "release_year";

    //    create table IF NOT EXISTS JAVA.books(_id integer PRIMARY KEY AUTO_INCREMENT,author text,title text,version text,
    // isbn integer, no_of_pages integer,total_copies integer, available_copies integer,
    // wait_list integer);
    public static final String CREATE_TABLE_BOOKS = "CREATE TABLE " + "IF NOT EXISTS " + TABLE_BOOKS + "(" + COLUMN_ID + " " + DATATYPE_INT + " PRIMARY KEY " + " AUTO_INCREMENT "
            + ", " + COLUMN_AUTHOR + " " + DATATYPE_TEXT + ", " + COLUMN_TITLE + " " + DATATYPE_TEXT + ", " + COLUMN_VERSION + " " + DATATYPE_TEXT + ", " +
            "" + COLUMN_ISBN + " " + DATATYPE_INT + ", " + COLUMN_NO_OF_PAGES + " " + DATATYPE_INT + ", " + COLUMN_TOTAL_COPIES + " " + DATATYPE_INT + ", " + COLUMN_AVAILABLE_COPIES + " "
            + DATATYPE_INT + ", " + COLUMN_WAIT_LIST + " " + DATATYPE_INT + ");";

    //   create table IF NOT EXISTS java.audios(_id integer PRIMARY KEY AUTO_INCREMENT,author text,title text,narrated_by text,duration integer,
    //size integer,downloadable boolean,total_copies integer, available_copies integer,wait_list integer);
    public static final String CREATE_TABLE_AUDIOS = "CREATE TABLE " + "IF NOT EXISTS " + TABLE_AUDIOS + "(" + COLUMN_ID + " " + DATATYPE_INT + " PRIMARY KEY " + "AUTO_INCREMENT"
            + ", " + COLUMN_AUTHOR + " " + DATATYPE_TEXT + ", " + COLUMN_TITLE + " " + DATATYPE_TEXT + ", " + COLUMN_NARRATED_BY + " " + DATATYPE_TEXT + ", " + COLUMN_DURATION + " " +
            DATATYPE_INT + ", " + COLUMN_SIZE + " " + DATATYPE_INT + ", " + COLUMN_DOWNLOADABLE + " " + DATATYPE_BOOLEAN + ", " + COLUMN_TOTAL_COPIES + " " + DATATYPE_INT + ", " +
            COLUMN_AVAILABLE_COPIES + " " + DATATYPE_INT + ", " + COLUMN_WAIT_LIST + " " + DATATYPE_INT + ");";

    //     create table IF NOT EXISTS java.videos(_id integer PRIMARY KEY AUTO_INCREMENT,author text,title text,duration integer,
    // adult_rated boolean, video_type text,release_year int,total_copies integer, available_copies integer,
    // wait_list integer);
    public static final String CREATE_TABLE_VIDEOS = "CREATE TABLE " + "IF NOT EXISTS " + TABLE_VIDEOS + "(" + COLUMN_ID + " " + DATATYPE_INT + " PRIMARY KEY " + "AUTO_INCREMENT"
            + ", " + COLUMN_AUTHOR + " " + DATATYPE_TEXT + ", " + COLUMN_TITLE + " " + DATATYPE_TEXT + ", " + COLUMN_DURATION + " " + DATATYPE_INT + ", " + COLUMN_ADULT_RATED + " " +
            DATATYPE_BOOLEAN + ", " + COLUMN_VIDEO_TYPE + " " + DATATYPE_TEXT + ", " + COLUMN_RELEASE_YEAR + " " + DATATYPE_INT + ", " + COLUMN_TOTAL_COPIES + " " + DATATYPE_INT + ", "
            + COLUMN_AVAILABLE_COPIES + " " + DATATYPE_INT + ", " + COLUMN_WAIT_LIST + " " + DATATYPE_INT + ");";



    public static final String INSERT_BOOK = "INSERT INTO " + TABLE_BOOKS + "(" + COLUMN_AUTHOR + "," + COLUMN_TITLE + "," + COLUMN_VERSION + "," + COLUMN_ISBN + "," + COLUMN_NO_OF_PAGES
            + "," + COLUMN_TOTAL_COPIES + "," + COLUMN_AVAILABLE_COPIES + "," + COLUMN_WAIT_LIST + ") " + " VALUES (?,?,?,?,?,?,?,?);";

    public static final String INSERT_AUDIO = "INSERT INTO " + TABLE_AUDIOS + "(" + COLUMN_AUTHOR + "," + COLUMN_TITLE + "," + COLUMN_NARRATED_BY + "," + COLUMN_DURATION + "," + COLUMN_SIZE + "," +
            COLUMN_DOWNLOADABLE + "," + COLUMN_TOTAL_COPIES + "," + COLUMN_AVAILABLE_COPIES + "," + COLUMN_WAIT_LIST + ") " + "VALUES(?,?,?,?,?,?,?,?,?);";

    public static final String INSERT_VIDEO = "INSERT INTO " + TABLE_VIDEOS + "(" + COLUMN_AUTHOR + "," + COLUMN_TITLE + "," + COLUMN_DURATION + "," + COLUMN_ADULT_RATED + "," + COLUMN_VIDEO_TYPE + "," + COLUMN_RELEASE_YEAR + "," +
            COLUMN_TOTAL_COPIES + "," + COLUMN_AVAILABLE_COPIES + "," + COLUMN_WAIT_LIST + ") " + "VALUES(?,?,?,?,?,?,?,?,?);";



    public static final String QUERY_BOOKS = "SELECT " + COLUMN_TITLE + " FROM " + TABLE_BOOKS + " WHERE " + COLUMN_ID + " = " + "?;";
    public static final String QUERY_AUDIOS = "SELECT " + COLUMN_TITLE + " FROM " + TABLE_AUDIOS + " WHERE " + COLUMN_ID + " = " + "?;";
    public static final String QUERY_VIDEOS = "SELECT " + COLUMN_TITLE + " FROM " + TABLE_VIDEOS + " WHERE " + COLUMN_ID + " = " + "?;";


    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public boolean open() {

        try {
            conn = DriverManager.getConnection(url);
            return true;
        } catch (SQLException e) {
            System.out.println("Can not establish DB connection :(" + e.getMessage());
        }
        return false;
    }

    public void createTable(String tableSQL) {
        try (Statement statement = conn.createStatement()) {
            statement.execute(tableSQL);
        } catch (SQLException e) {
            System.out.println("Error while creating table. ErrMsg: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertBookData(Book dataList) {
        try {
            preparedStatement = conn.prepareStatement(INSERT_BOOK);
            preparedStatement.setString(1, dataList.getAuthor());
            preparedStatement.setString(2, dataList.getTitle());
            preparedStatement.setString(3, dataList.getBookVersion());
            preparedStatement.setInt(4, dataList.getIsbn());
            preparedStatement.setInt(5, dataList.getNumberOfPages());
            preparedStatement.setInt(6, dataList.getTotalCopies());
            preparedStatement.setInt(7, dataList.getAvailableCopies());
            preparedStatement.setInt(8, dataList.getWaitListCount());
//            System.out.println("Prepared SQL: "+preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while inserting data in to Books table, Error messgae: " + e.getMessage());
        }
    }

    public void insertAudioData(Audio dataList) {
        try {
            conn.setAutoCommit(false);
            preparedStatement = conn.prepareStatement(INSERT_AUDIO);
            preparedStatement.setString(1, dataList.getAuthor());
            preparedStatement.setString(2, dataList.getTitle());
            preparedStatement.setString(3, dataList.getNarratedBy());
            preparedStatement.setInt(4, dataList.getDuration());
            preparedStatement.setInt(5, dataList.getAudioSize());
            preparedStatement.setBoolean(6, dataList.isDownloadable());
            preparedStatement.setInt(7, dataList.getTotalCopies());
            preparedStatement.setInt(8, dataList.getAvailableCopies());
            preparedStatement.setInt(9, dataList.getWaitListCount());
//            System.out.println("Prepared SQL: "+preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ;
            System.out.println("Error while inserting data in to Audios table, Error messgae: " + e.getMessage());
        }
    }

    public void insertVideoData(Video dataList) {
        try {
            preparedStatement = conn.prepareStatement(INSERT_VIDEO);
            preparedStatement.setString(1, dataList.getAuthor());
            preparedStatement.setString(2, dataList.getTitle());
            preparedStatement.setInt(3, dataList.getDuration());
            preparedStatement.setBoolean(4, dataList.isAdultRated());
            preparedStatement.setString(5, dataList.getVideoType());
            preparedStatement.setInt(6, dataList.getYear());
            preparedStatement.setInt(7, dataList.getTotalCopies());
            preparedStatement.setInt(8, dataList.getAvailableCopies());
            preparedStatement.setInt(9, dataList.getWaitListCount());
//            System.out.println("Prepared SQL: "+preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while inserting data in to Videos table, Error messgae: " + e.getMessage());
        }
    }

    public void queryTableById(String querySQL, int id) {
        try {
            preparedStatement = conn.prepareStatement(querySQL);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                System.out.println(result.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public void setCommitOff() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCommitOn() {
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            if(result!=null){
                result.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Unable to close connections. " + e.getMessage());
            e.printStackTrace();
        }
    }

}
