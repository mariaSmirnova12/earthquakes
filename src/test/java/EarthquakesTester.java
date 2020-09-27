import org.junit.Before;
import org.junit.Test;
import quakes.*;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class EarthquakesTester {

    EarthQuakeParser parser;

    @Before
    public void setUp()
    {
        parser = new EarthQuakeParser();
    }

    @Test
    public void testCorrectSort()
    {
        String source = "dataQuakes/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        QuakeEntry quake = new QuakeEntry(36.7496, -116.147, -0.2, "57km ESE of Beatty, Nevada", -4200);
        assertEquals("quake in "+quakeNumber, quake, list.get(quakeNumber));
    }

    @Test
    public void sortByTitleAndDepth(){
        String source = "dataQuakes/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleAndDepthComparator());
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        QuakeEntry quake = new QuakeEntry(49.7592, 155.8263, 4.7, "104km SSW of Severo-Kuril'sk, Russia", -58380);
        assertEquals("quake in "+quakeNumber, quake, list.get(quakeNumber));
    }

    @Test
    public void sortByLastWordInTitleThenByMagnitude(){
        String source = "dataQuakes/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        int quakeNumber = 10;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
        QuakeEntry quake = new QuakeEntry(64.4747, -149.4837, 0.4, "21km WSW of North Nenana, Alaska", -16300);
        assertEquals("quake in "+quakeNumber, quake, list.get(quakeNumber));
    }

}
