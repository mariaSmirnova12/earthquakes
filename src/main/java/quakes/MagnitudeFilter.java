package quakes;

public class MagnitudeFilter implements Filter{

    private double minMagn;
    private double maxMagn;

    public MagnitudeFilter(double minMagn, double maxMagn){
        this.minMagn = minMagn;
        this.maxMagn = maxMagn;
    }
    public boolean satisfies(QuakeEntry qe){
        return (qe.getMagnitude()>=minMagn && qe.getMagnitude()<=maxMagn); 
    }

    public String getName(){
        return "MagnitudeFilter";
    }
}
