package model;

/**
 * Created by Stephen on 3/23/2017.
 */

public class Temperature {

    private double temp;
    private float minTemp;
    private float maxTemp;

    public double getTemp() {
        return temp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setTemp( double temp ) {
        this.temp = temp;
    }

    public void setMinTemp( float minTemp ) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp( float maxTemp ) {
        this.maxTemp = maxTemp;
    }
}
