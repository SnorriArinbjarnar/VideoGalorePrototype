package main.is.ru.honn.Service;

import java.util.ArrayList;
import java.util.HashMap;

import main.is.ru.honn.Entity.Video;
import main.is.ru.honn.Data.VideoData;

public class VideoService {
    private VideoData vData;

    /**
     * Reads from and writes to VideoData
     */

    public VideoService() {
        vData = new VideoData();
        vData.init();

    }

    public ArrayList<Video> getVideoList() {
        return vData.getVidjo();
    }

    public Video getVideo(int id) {

        return vData.getVideoById(id);
    }

    public HashMap<Integer,Video> getVideos(){
        return vData.getVideoAndId();
    }

    public void addToHashMap (int id, Video v) {
        vData.addToHashMap(id,v);
    }

    public int getMaxId() {
        return vData.getMaxID();
    }

    public void addToVideoList(Video v) {
        vData.addToVideoList(v);
    }


}
