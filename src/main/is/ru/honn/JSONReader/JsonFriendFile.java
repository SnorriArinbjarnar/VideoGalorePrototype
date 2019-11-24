package main.is.ru.honn.JSONReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Entity.Video;
import main.is.ru.honn.Service.VideoService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Reads from Friends.json file and writes
 * into a list that FriendData and BorrowedData
 * then fetches
 */

public class JsonFriendFile {
    private ArrayList<Friend> friends;
    private ArrayList<Borrowed> borrowList;
    private HashMap<Integer,Friend> friendList;

    public JsonFriendFile() {
        JSONParser parser = new JSONParser();

        friends = new ArrayList<Friend>();
        borrowList = new ArrayList<Borrowed>();
        friendList = new HashMap<Integer,Friend>();

        int borrowID = 0;

        VideoService v = new VideoService();


        try {
            Object obj = parser.parse(new FileReader("Friends2.json"));
            JSONArray array = (JSONArray)obj;



            for(int i = 0; i < array.size(); i++) {
                JSONObject o = (JSONObject) array.get(i);
                //System.out.println(o);
                String ffId = String.valueOf(o.get("id"));
                //String fId = (String)o.get("id");
                int frId = Integer.parseInt(ffId);
                long friendId = (Long)o.get("id");
                String name = (String)o.get("first_name") + " " + (String)o.get("last_name");
                String email = (String)o.get("email");
                String address = (String)o.get("address");
                String phone = (String)o.get("phone");
                //String[] videos = (String[])o.get("tapes");

                Friend f = new Friend();
                f.setAddress(address);
                f.setTeli(phone);
                f.setEmail(email);
                f.setName(name);
                f.setId(frId);

                if(o.containsKey("tapes")) {
                    JSONArray arr = (JSONArray)o.get("tapes");


                    if(arr.size() > 0){
                        for(int j = 0; j < arr.size(); j++) {
                            JSONObject d = (JSONObject)arr.get(j);
                            String borrow = (String)d.get("borrow_date");
                            GregorianCalendar borrowDate = new GregorianCalendar();
                            String[] dates = borrow.split("-");
                            int year = Integer.parseInt(dates[0]);
                            int month = Integer.parseInt(dates[1]);
                            int date = Integer.parseInt(dates[2]);
                            borrowDate.set(year,month,date);

                            Calendar returnBorrowed = new GregorianCalendar();
                            String returnD = (String)d.get("return_date");

                            if(returnD != null){
                                String[] returnDates = returnD.split("-");
                                int returnyear = Integer.parseInt(returnDates[0]);
                                int returnMonth = Integer.parseInt(returnDates[1]);
                                int returnDay = Integer.parseInt(returnDates[2]);
                                returnBorrowed.set(returnyear,returnMonth,returnDay);
                            }
                            else {
                                returnBorrowed = null;
                            }




                            long videoId = (Long)d.get("id");
                            //String vidId = (String)d.get("id");
                            String vidId = String.valueOf(d.get("id"));
                            int videId = Integer.parseInt(vidId);
                            Video rented = v.getVideo(videId);
                            //f.rentVideo(videId, rented);
                            f.rentVideo(rented, videId);

                            // sett í array af friend hvað hann leigði
                            f.rentVideo(videId);

                            Borrowed b = new Borrowed(frId,videId,borrowDate,returnBorrowed);
                            b.setID(borrowID);
                            borrowList.add(b);
                            borrowID++;

                        }
                    }
                }


                friends.add(f);
                friendList.put(frId,f);


                //System.out.println(o);
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

    public ArrayList<Friend> getFriends() {
        return friends;
    }
    public HashMap<Integer,Friend> getFriendList() {return friendList;}

    public ArrayList<Borrowed> getBorrowedVideos() {
        return borrowList;
    }
}
