package model;

/**
 * Created by Stephen on 3/23/2017.
 */

public class Place {

    private float lon;
    private float lat;
    private long sunset;
    private long sunrise;
    private String country;
    private String city;
    private long lastupdate;

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }

    public long getSunset() {
        return sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public long getLastupdate() {
        return lastupdate;
    }

    public void setLon( float lon ) {
        this.lon = lon;
    }

    public void setLat( float lat ) {
        this.lat = lat;
    }

    public void setSunset( long sunset ) {
        this.sunset = sunset;
    }

    public void setSunrise( long sunrise ) {
        this.sunrise = sunrise;
    }

    public void setCountry( String country ) {
        this.country = country;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public void setLastupdate( long lastupdate ) {
        this.lastupdate = lastupdate;
    }
}
