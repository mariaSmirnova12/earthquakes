import org.junit.Before;
import org.junit.Test;
import quakes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EarthquakesFilterTester {

    EarthQuakeParser parser;

    @Before
    public void setUp()
    {
        parser = new EarthQuakeParser();
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }

        return answer;
    }

    @Test
    public void quakesWithMagnitudeAndDepthFilter() {
        String source = "dataQuakes/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        Filter f1 = new MagnitudeFilter(4.0, 5.0);
        Filter f2 = new DepthFilter(-35000.0, -12000.0);
        ArrayList<QuakeEntry> m1  = filter(list, f1);
        ArrayList<QuakeEntry> m2  = filter(m1, f2);
        for (QuakeEntry qe: m2) {
            System.out.println(qe);
        }
        QuakeEntry one = new QuakeEntry(8.5314, -71.3386, 5, "5km ENE of Lagunillas, Venezuela", -25160);
        QuakeEntry two = new QuakeEntry(38.2674, 142.5348, 4.6, "109km E of Ishinomaki, Japan", -30500);

        List<QuakeEntry> res = Arrays.asList(one, two);
        assertEquals(res.size(), m2.size());
        assertEquals("filter list", res, m2);
    }

    @Test
    public void quakesWithDistanceAndPhraseFilter() {
        String source = "dataQuakes/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        Location japan  = new Location(35.42, 139.43);
        Filter f1 = new DistanceFilter(japan, 10000000);
        Filter f2 = new PhraseFilter("end", "Japan");
        ArrayList<QuakeEntry> m1  = filter(list, f1);
        ArrayList<QuakeEntry> m2  = filter(m1, f2);
        for (QuakeEntry qe: m2) {
            System.out.println(qe);
        }
        QuakeEntry one = new QuakeEntry(26.3794, 142.7055, 5.5, "91km SSE of Chichi-shima, Japan", -12890);
        QuakeEntry two = new QuakeEntry(38.2674, 142.5348, 4.6, "109km E of Ishinomaki, Japan", -30500);

        List<QuakeEntry> res = Arrays.asList(one, two);
        assertEquals(res.size(), m2.size());
        assertEquals("filter list", res, m2);
    }

    @Test
    public void testMatchAllFilter(){
        String source = "dataQuakes/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter filters = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 2.0);
        filters.addFilter(f1);
        Filter f2 = new DepthFilter(-100000.0, -10000.0);
        filters.addFilter(f2);
        Filter f3 = new PhraseFilter("middle", "a");
        filters.addFilter(f3);
        ArrayList<QuakeEntry> res = filter(list, filters);
        System.out.println("size is "+res.size());

        for (QuakeEntry qe: res) {
            System.out.println(qe);
        }

        QuakeEntry one = new QuakeEntry(33.542, -116.6618333, 0.3, "2km SE of Anza, California", -10410);
        QuakeEntry two = new QuakeEntry(63.2496, -150.4283, 1.7, "75km WSW of Cantwell, Alaska", -99900);

        List<QuakeEntry> quakes = Arrays.asList(one, two);
        assertEquals(quakes.size(), res.size());
        assertEquals("filter list", quakes, res);
    }
}
