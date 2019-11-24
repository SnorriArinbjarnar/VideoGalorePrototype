package integration_test.is.ru.honn;

import main.is.ru.honn.Entity.Video;
import main.is.ru.honn.Entity.VideoType;
import main.is.ru.honn.Service.VideoService;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TestVideo {
    private VideoService vService;

    @Test
    public void testNewVideo() {
        vService = new VideoService();
        VideoType type = new VideoType();
        type.setAsVHS();

        int id = vService.getMaxId();

        Video x = new Video(type,"Markings of the Father","Jason Bourne",new GregorianCalendar(Calendar.YEAR,Calendar.MONTH, Calendar.DAY_OF_MONTH),"ee1122",id);
        vService.addToHashMap(id,x);
        vService.addToVideoList(x);

        assertSame(x, vService.getVideo(id));
        assertTrue(vService.getVideoList().contains(x));
    }
}
