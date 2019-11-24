package main.is.ru.honn.UI;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Entity.VideoType;
import main.is.ru.honn.Service.BorrowedService;
import main.is.ru.honn.Service.FriendService;
import main.is.ru.honn.Service.VideoService;
import main.is.ru.honn.Entity.Video;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * What the user sees,  the user experience
 */

public class Console {
    private VideoService vService;
    private FriendService fService;
    private BorrowedService bService;
    Friend activeUser;

    public Console() {
        vService = new VideoService();
        bService = new BorrowedService();
        fService = new FriendService();

    }

    public void seeAllVideos() {
        ArrayList<Video> temp = vService.getVideoList();
        System.out.println("Videos:");
        for (int i = 0; i < temp.size()-1; i++) {
            System.out.println(temp.get(i).getTitle());
        }
    }

    /**
     *
     * @param x
     * @return true if only char in string false otherwise
     *
     * The below two functions would have been for
     * input validation later, I did not implement
     * it as such,  stay tuned
     */
    public boolean isChar(String x) {
        for(int i = 0; i <= x.length(); i++) {
            char c = x.charAt(i);
            if(Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }

    public boolean isDigit(String x) {
        for(int i = 0; i <= x.length(); i++) {
            char c = x.charAt(i);
            if(Character.isLetter(c)){
                return false;
            }
        }
        return true;
    }

    public void friendsWithLoansByDate() {
        /**
         * Returns friends with borrowed movies
         * and which movies
         */
        Scanner scanBorrowed = new Scanner(System.in);

        int lYear, lMonth, lDay;

        System.out.println("Enter borrow year:");
        lYear = scanBorrowed.nextInt();



        System.out.println("Enter borrow month:");
        lMonth = scanBorrowed.nextInt();
        System.out.println("Enter borrow day:");
        lDay = scanBorrowed.nextInt();

        GregorianCalendar borrowedDate = new GregorianCalendar(lYear,lMonth,lDay);
        ArrayList<Friend> f = bService.getFriendsWithBorrowedVideosByDate(borrowedDate);

        System.out.println(f.size());

        for(int i = 0; i <= f.size()-1; i++) {
            String name = f.get(i).getName();
            System.out.println(name);
            f.get(i).getRentedVideos();
        }
    }

    public void returnFriendTape() {
        ArrayList<Integer> xx = activeUser.getRentedFilms();
        System.out.println("Which movie do you want to return??:");

        for(int i = 0; i < xx.size(); i++) {
            Video iVideo = vService.getVideo(xx.get(i));
            int vidId = iVideo.getVideoID();
            int bIndex = bService.getBorrowedIndex(activeUser.getFriendId(),vidId);
            Borrowed temp = bService.getBorrowByIndex(bIndex);
            if(temp.getReturnDate() == null) {
                System.out.println(iVideo.getTitle() + " : " + iVideo.getVideoID());

            }
        }
        Scanner scanInt = new Scanner(System.in);
        int returnVideo = scanInt.nextInt();
        bService.returnVideo(activeUser.getFriendId(), returnVideo);
    }

    public void borrowedFilmsByDate() {

        Scanner scanBorrowed = new Scanner(System.in);

        int lateYear, lateMonth, lateDay;
        System.out.println("Enter borrow year:");
        lateYear = scanBorrowed.nextInt();
        System.out.println("Enter borrow month:");
        lateMonth = scanBorrowed.nextInt();
        System.out.println("Enter borrow day:");
        lateDay = scanBorrowed.nextInt();

        GregorianCalendar borrowDate = new GregorianCalendar(lateYear,lateMonth,lateDay);

        ArrayList<Borrowed> temp2 = bService.getBorrowedVideosByDate(borrowDate);

        for(int i = 0; i <= temp2.size()-1; i++) {
            int videoId = temp2.get(i).getVideoId();
            int friendId = temp2.get(i).getFriendId();
            System.out.println(vService.getVideo(videoId).getTitle() + " loaned to " + fService.getFriendById(friendId).getName());
        }
    }

    public void friendsWithMonthOldFilms() {
        /**
         * Returns all those friends that
         * have had tapes on loan for
         * more than a month
         */

        Scanner scan = new Scanner(System.in);

        int month;
        int day;
        int year;

        System.out.println("Borrwed Year");
        year = scan.nextInt();
        System.out.println("Borrowed Month");
        month = scan.nextInt();
        System.out.println("Borrowed Day of month");
        day = scan.nextInt();

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(year,month,day);

        ArrayList<Friend> ff = bService.friendsWithMonthOldVideos(cal);
        ArrayList<Borrowed> fg = bService.monthOldLoans(cal);
        System.out.println(fg.size());
        for(int i = 0; i < ff.size(); i++) {
            System.out.println("Friend:" + ff.get(i).getName());
            //ff.get(i).getRentedVideos();
            System.out.println("    Late videos  " + year + "." + month + "." + day );

            for(int j = 0; j < fg.size(); j++) {

                if(fg.get(j).getFriendId() == ff.get(i).getFriendId()){
                    System.out.println("    - " + vService.getVideo(fg.get(j).getVideoId()).getTitle());

                }
            }
            continue;
        }

    }

    public void addMovie() {
        /**
         * Adds a new movie,
         * in this simple prototype if you do
         * not type
         */
        String type;
        String title;
        String director;
        String eidr;
        int year, month, day;
        int id;

        Scanner scanNewVideo = new Scanner(System.in);
        System.out.println("Title: ");
        title = scanNewVideo.nextLine();
        System.out.println("Director: ");
        director = scanNewVideo.nextLine();
        System.out.println("Type (BETAMAX/VHS): ");
        VideoType vtype = new VideoType();
        type = scanNewVideo.next();
        if(type == "BETAMAX") {
            vtype.setAsBETA();
        }
        else {
            vtype.setAsVHS();
        }

        GregorianCalendar release = new GregorianCalendar();
        System.out.println("Release year: ");
        year = scanNewVideo.nextInt();
        System.out.println("Release month: ");
        month = scanNewVideo.nextInt();
        System.out.println("Release day: ");
        day = scanNewVideo.nextInt();
        release.set(year,month,day);

        System.out.println("EIDR number: ");
        eidr = scanNewVideo.nextLine();

        id = vService.getMaxId()+1;
        Video v = new Video(vtype,title,director,release,eidr,id);
        vService.addToVideoList(v);
        vService.addToHashMap(id,v);

        System.out.println("New video: " + vService.getVideo(id).getTitle() + " added");
    }

    public void seeFriendBorrowedMovies() {
        System.out.println("My borrowed movies:");

        ArrayList<Integer> x = activeUser.getRentedFilms();
        for(int i = 0; i < x.size(); i++) {
            Video xx = vService.getVideo(x.get(i));
            int vidId = xx.getVideoID();
            int bIndex = bService.getBorrowedIndex(activeUser.getFriendId(), vidId);
            Borrowed temp = bService.getBorrowByIndex(bIndex);
            if(temp.getReturnDate() != null) {
                System.out.println(xx.getTitle() + " borrowed  " + temp.getBorrowDate().get(Calendar.DAY_OF_MONTH) + "." + temp.getBorrowDate().get(Calendar.MONTH) + "." + temp.getBorrowDate().get(Calendar.YEAR) + " ,returned " + temp.getReturnDate().get(Calendar.DAY_OF_MONTH) + "." + temp.getReturnDate().get(Calendar.MONTH) + "." + temp.getReturnDate().get(Calendar.YEAR) );
            }
            else {
                System.out.println(xx.getTitle() + " borrowed  " + temp.getBorrowDate().get(Calendar.DAY_OF_MONTH) + "." + temp.getBorrowDate().get(Calendar.MONTH) + "." + temp.getBorrowDate().get(Calendar.YEAR) );
            }
        }
    }

    public void rentMovie(int friendId) {
        /**
         * Rent a movie based on friend id,
         * friends can only borrow movies
         * they have not seen
         */
        ArrayList<Video> available = bService.getAvailableVideos(friendId);
        for(int i = 0; i < available.size()-1; i++){
            System.out.println(available.get(i).getTitle() + "     " + available.get(i).getVideoID());
        }

        System.out.println("Which film, choice by id:");
        int choiceVideo;
        Scanner scanChoice = new Scanner(System.in);
        choiceVideo = scanChoice.nextInt();
        bService.rentAVideo(choiceVideo, activeUser.getFriendId());
    }

    public void seeAllUsers() {
        ArrayList<Friend> friends = fService.getFriends();
        System.out.println("Friends:");
        for (int i = 0; i < friends.size()-1; i++) {
            System.out.println(friends.get(i).getName());
        }
    }


    public void VideoGalaxyWelcome() {
        System.out.println("====================================");
        System.out.println("Welcome to VideoMax Galaxy");
        System.out.println();
        System.out.println("with over " + (vService.getVideoList().size()-1) + " videos");
        System.out.println("and " + (fService.getFriends().size()-1) + " registered members");
        System.out.println("====================================");
    }

    public void seeFeatures() {
        /**
         * Features that registered users
         * can see
         */
        System.out.println();
        System.out.println("What would you like to do??");
        System.out.println("A: List of all movies");
        System.out.println("B: List of all registered friends");
        System.out.println("C: Rent Movie");
        System.out.println("D: Add a movie");
        System.out.println("E: Borrowed Movies");
        System.out.println("F: My stats");
        System.out.println("G: Return a movie");
        System.out.println("Q to quit");
        System.out.println();
    }

    public void seeNonRegisteredFeatures() {
        System.out.println();
        System.out.println("What would you like to do??");
        System.out.println("A: List of all movies");
        System.out.println("Q to quit");
        System.out.println("Please Register to gain access to other features");
        System.out.println();
    }

    public void loginInput() {
        System.out.println();
        String choice;
        Scanner scan = new Scanner(System.in);
        System.out.println("L: Login");
        System.out.println("R: Register");
        choice = scan.nextLine();

        switch(choice) {
            case "L":

                ArrayList<Friend> list = fService.getFriends();

                String name;
                System.out.println("Enter your name");
                name = scan.nextLine();

                for(int i = 0; i < list.size()-1; i++) {
                    String iName = list.get(i).getName();
                    if(name.equals(iName)) {
                        activeUser = list.get(i);
                    }
                }
                if (activeUser == null) {
                    System.out.println();
                    System.out.println("Name not found, do you want to register? (Y/N)");
                    choice = scan.nextLine();
                    if(choice.equals("Y")) {
                        registerInput();
                    }
                    else {
                        seeNonRegisteredFeatures();
                        choice = scan.nextLine();
                        switch(choice) {
                            case "A":
                                seeAllVideos();
                                break;

                        }
                    }

                }
                else {
                    seeFeatures();
                    userInput();
                }
                break;

            case "R":
                registerInput();
        }
    }

    public void registerInput() {
        String choice;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome, please enter the following information to register:");
        String name;
        System.out.println("Name: ");
        name = scan.nextLine();

        System.out.println("Address: ");
        String address;
        address = scan.nextLine();

        System.out.println("Email: ");
        String email;
        email = scan.nextLine();

        System.out.println("Telephone: ");
        String telephone;
        telephone = scan.nextLine();

        int id = fService.getMaxID()+1;

        Friend newFriend = new Friend();
        newFriend.setAddress(address);
        newFriend.setEmail(email);
        newFriend.setName(name);
        newFriend.setId(id);
        newFriend.setTeli(telephone);

        fService.addToHashMap(id, newFriend);
        fService.addToList(newFriend);
        bService.addToFriendsHash(id, newFriend);

        loginInput();


    }

    public void userInput() {

        String choice;
        //seeFeatures();
        do {

            Scanner scan = new Scanner(System.in);
            choice = scan.nextLine();

            switch(choice) {
                case "A":
                    seeAllVideos();
                    break;

                case "B":
                    seeAllUsers();
                    break;

                case "C":
                    rentMovie(activeUser.getFriendId());
                    break;

                case "D":
                    addMovie();
                    break;

                case "E":
                    System.out.println("i: list of borrowed films by date");
                    System.out.println("ii: list of friends with borrowed films by date");
                    System.out.println("iii: list of friends with borrowed films more than month");

                    String bChoice;
                    Scanner scanBorrowed = new Scanner(System.in);
                    bChoice = scanBorrowed.nextLine();



                    switch(bChoice) {
                        case "i":
                            borrowedFilmsByDate();
                            break;

                        case "ii":
                           friendsWithLoansByDate();
                            break;

                        case "iii":
                            friendsWithMonthOldFilms();
                            break;
                        }
                        break;

                case "F":
                   seeFriendBorrowedMovies();
                    break;

                case "G":
                    returnFriendTape();
                    break;

                case "Menu":
                    seeFeatures();
                    break;



            }

        } while(!choice.equals("q"));

    }
}
