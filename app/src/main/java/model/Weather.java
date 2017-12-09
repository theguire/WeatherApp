package model;

import android.graphics.Bitmap;

/**
 * Created by Stephen on 3/23/2017.
 */

public class Weather {
    public Place place;
    public String iconData;
    public Bitmap bitmapIcon;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
