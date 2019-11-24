package main.is.ru.honn.JSONReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import main.is.ru.honn.Entity.Video;
import main.is.ru.honn.Entity.VideoType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Reads from Video.json into a list
 * that VideoData then fetches
 */

public class JsonVideoFile {
    private ArrayList<Video> videos;
    private HashMap<Integer, Video> videoList;


    public JsonVideoFile() {
        JSONParser parser = new JSONParser();

        videos = new ArrayList<Video>();
        videoList = new HashMap<Integer, Video>();

        try {
            Object obj = parser.parse(new FileReader("Videotapes.json"));
            JSONArray array = (JSONArray)obj;


            for(int i = 0; i < array.size(); i++) {
                JSONObject o = (JSONObject) array.get(i);

                String title = (String)o.get("title");
                String director = (String)o.get("director_first_name") + " " + (String)o.get("director_last_name");
                String typeStr = (String)o.get("type");
                VideoType type = new VideoType();
                type.setType(typeStr);

                //String sid = (String)o.get("id");
                String ssid = String.valueOf(o.get("id"));
                int id = Integer.parseInt(ssid);
                GregorianCalendar release = new GregorianCalendar();
                String date = (String)o.get("release_date");
                String[] dates = date.split("-");
                int year = Integer.parseInt(dates[0]);
                int month = Integer.parseInt(dates[1]);
                int day = Integer.parseInt(dates[2]);
                String eidr = (String)o.get("eidr");
                release.set(year, month, day);
                Video x = new Video(type, title, director, release, eidr, id);
                videoList.put(id, x);
                videos.add(x);
            }
        }
        catch (ParseException e) {
            System.out.println("position: " + e.getPosition());
            System.out.println(e);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Video> getVideoList() {
        return videos;
    }

    public Video getVideo(int id) {

        return  videoList.get(id);
    }

    public HashMap<Integer,Video> getVideos(){
        return videoList;
    }


}
