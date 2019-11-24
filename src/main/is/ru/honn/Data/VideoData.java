package main.is.ru.honn.Data;

import main.is.ru.honn.JSONReader.JsonVideoFile;
import main.is.ru.honn.Service.VideoService;
import main.is.ru.honn.Entity.Video;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a database for video,
 */

public class VideoData {
    private VideoService service;
    private HashMap<Integer, Video> videoList;
    private ArrayList<Video> videos;
    private JsonVideoFile json;

    public VideoData() {
    }

    public void init() {
        json = new JsonVideoFile();
        videoList = json.getVideos();
        videos = json.getVideoList();
    }

    public Video getVideoById(int id) {
        Video temp = videoList.get(id);
        return temp;
    }

    public int getMaxID () {
        int max = 0;
        for (int i = 0; i < videos.size()-1; i++) {
            if(videos.get(i).getVideoID() > max) {
                max = videos.get(i).getVideoID();
            }
        }
        return max;
    }

    public void printVideos() {
        for(int i = 0; i < videos.size()-1; i++) {
            System.out.println(videos.get(i).getTitle());
        }
    }

    public void addToHashMap(int id, Video v) {
        videoList.put(id,v);
        videos.add(v);
    }

    public void addToVideoList (Video v) {
        videos.add(v);
    }

    public ArrayList<Video> getVidjo() {
        return videos;
    }

    public HashMap<Integer, Video>getVideoAndId() {
        return videoList;
    }
}
