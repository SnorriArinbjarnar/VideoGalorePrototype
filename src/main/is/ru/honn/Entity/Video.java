package main.is.ru.honn.Entity;

import java.util.GregorianCalendar;

/**
 * Represents a single Video tape
 * Since the prototype only one person
 * at at time can rent each movie, so it could
 * also have been possible letting video have friendId of borrower
 */

public class Video {
    private VideoType type;
    private String title;
    private String director;
    private GregorianCalendar releaseDate;
    private String EIDR;
    private int videoID;

    public Video() {

    }

    public Video(VideoType type, String title, String director, GregorianCalendar release, String EIDR, int id) {
        this.type = new VideoType();
        this.type = type;
        this.title = title;
        this.director = director;
        this.releaseDate = release;
        this.EIDR = EIDR;
        this.videoID = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setType(VideoType type) {
        this.type = type;
    }

    public void setEIDR(String eidr) {
        this.EIDR = eidr;
    }

    public void setRelease(GregorianCalendar date) {
        this.releaseDate = date;
    }

    public void setVideoID(int id) {
        this.videoID = id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getVideoID() {
        return this.videoID;
    }

    public String getDirector() {
        return this.director;
    }

    public String getEIDR() {
        return this.EIDR;
    }

    public String toString() {
        return "Title: " + title + ",\n"  + "Director: " + director + ", \n" + "Year: " + releaseDate.getTime();
    }


}
