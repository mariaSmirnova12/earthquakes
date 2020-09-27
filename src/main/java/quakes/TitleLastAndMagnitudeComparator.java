package quakes;

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String name1 = q1.getInfo();
        String name2 = q2.getInfo();
        int ind1 = q1.getInfo().lastIndexOf(" ");
        int ind2 = q2.getInfo().lastIndexOf(" ");
        int res = name1.substring(ind1).compareTo(name2.substring(ind2));
        if(res == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        else {
            return res;
        }
}
}
