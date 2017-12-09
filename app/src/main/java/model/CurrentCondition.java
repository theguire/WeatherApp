package model;

/**
 * Created by Stephen on 3/23/2017.
 */

public class CurrentCondition {
    private int weatherIf;
    private String condition;
    private String icon;
    private String description;
    private float pressure;
    private float hunidity;
    private float maxTemp;
    private float minTemp;
    private double temperature;

    public int getWeatherIf() {
        return weatherIf;
    }

    public String getCondition() {
        return condition;
    }

    public String getIcon() {
        return icon;
    }

    public String getDescription() {
        return description;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHunidity() {
        return hunidity;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setWeatherIf( int weatherIf ) {
        this.weatherIf = weatherIf;
    }

    public void setCondition( String condition ) {
        this.condition = condition;
    }

    public void setIcon( String icon ) {
        this.icon = icon;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setPressure( float pressure ) {
        this.pressure = pressure;
    }

    public void setHunidity( float hunidity ) {
        this.hunidity = hunidity;
    }

    public void setMaxTemp( float maxTemp ) {
        this.maxTemp = maxTemp;
    }

    public void setMinTemp( float minTemp ) {
        this.minTemp = minTemp;
    }

    public void setTemperature( double temperature ) {
        this.temperature = temperature;
    }
}
