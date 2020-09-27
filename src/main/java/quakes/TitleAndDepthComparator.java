package quakes;

import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    
public int compare(QuakeEntry q1, QuakeEntry q2) {
    
    int res = q1.getInfo().compareTo(q2.getInfo());
    if(res == 0){
        return Double.compare(q1.getDepth(), q2.getDepth());
    }
    else {
       return res;
    }
    
}
}
