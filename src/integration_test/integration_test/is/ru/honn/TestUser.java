package integration_test.is.ru.honn;

import main.is.ru.honn.Entity.Borrowed;
import main.is.ru.honn.Entity.Friend;
import main.is.ru.honn.Entity.Video;
import main.is.ru.honn.Entity.VideoType;
import main.is.ru.honn.Service.BorrowedService;
import main.is.ru.honn.Service.FriendService;
import main.is.ru.honn.Service.VideoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;


public class TestUser {
    private FriendService fService;
    private VideoService vService;
    private BorrowedService bService;

    @Test
    public void testNewUser() {

        fService = new FriendService();

        Friend x = new Friend("Snorri","Dúfnahólum 10","snorriatsnorri","555666");
        int id = fService.getMaxID()+1;

        fService.addToList(x);
        fService.addToHashMap(id,x);

        assertSame(x,fService.getFriendById(id));
        assertTrue(fService.getFriends().contains(x));

    }


}
