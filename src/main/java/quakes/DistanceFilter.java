package quakes;

public class DistanceFilter implements Filter {
    private Location myLocation;
    double distance;
    public DistanceFilter(Location location, double distance){
        myLocation = location;
        this.distance = distance;
    }

    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(myLocation) < distance;
    }

    public String getName(){
        return "DistanceFilter";
    }
}
