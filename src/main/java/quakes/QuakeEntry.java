package quakes;

public class QuakeEntry implements Comparable<QuakeEntry>{
	
	private Location myLocation;
	private String title;
	private double depth;
	private double magnitude;

	public QuakeEntry(double lat, double lon, double mag, 
	                  String t, double d) {
		myLocation = new Location(lat,lon);
		
		magnitude = mag;
		title = t;
		depth = d;
	}
	
	public Location getLocation(){
		return myLocation;
	}
	
	public double getMagnitude(){
		return magnitude;
	}

	public String getInfo(){
		return title;
	}

	public double getDepth(){
		return depth;
	}

	@Override
	public int compareTo(QuakeEntry loc) {
	    if (magnitude < loc.getMagnitude()){
	        return -1;
	       }
	    if (magnitude > loc.getMagnitude()){
	        return 1;
	       }
	       else{
	           return Double.compare(depth, loc.getDepth());
	       }
	}
	
	public String toString(){
		return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof QuakeEntry))
			return false;
		QuakeEntry other = (QuakeEntry)o;

		if (this.getLocation().getLongitude() == other.getLocation().getLongitude() && this.getLocation().getLatitude() == other.getLocation().getLatitude() &&
		this.getMagnitude() == other.getMagnitude() &&
		this.getInfo().equals(other.getInfo()) &&
		this.getDepth() == other.getDepth()) return true;
		else return false;
	}
	
}