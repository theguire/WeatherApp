package desertsystems.com.weatherapp;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.icu.text.DecimalFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import Util.Utils;
import data.CityPreference;
import data.JSONWeatherParser;
import data.WeatherHttpClient;
import model.Weather;

public class MainActivity extends AppCompatActivity {

    private TextView cityName;
    private TextView temp;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;
    private ImageView iconView;

    Weather weather = new Weather();

    @Override protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Toolbar toolbar = ( Toolbar )findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        FloatingActionButton fab = ( FloatingActionButton )findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View view )
            {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                        .setAction( "Action", null ).show();
            }
        } );
        cityName = (TextView)findViewById( R.id.cityText );
        temp = (TextView)findViewById( R.id.tempText );
        description= (TextView)findViewById( R.id.cloudText );
        humidity = (TextView)findViewById( R.id.humidText );
        pressure = (TextView)findViewById( R.id.pressureText );
        wind = (TextView)findViewById( R.id.windText );
        sunrise = (TextView)findViewById( R.id.riseText );
        sunset = (TextView)findViewById( R.id.setText );
        updated= (TextView)findViewById( R.id.updateText );
        iconView = (ImageView)findViewById( R.id.thumbnailIcon );

        CityPreference cityPreference = new CityPreference( MainActivity.this );

        renderWeatherData( cityPreference.getCity() );
    }

    public void renderWeatherData( String city ){

        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute( new String[] { city + "&units=metric" }  );
        weather.iconData = weather.currentCondition.getIcon();
        new DownloadImageAsynchTask().execute( weather.iconData );

    }



    private class WeatherTask extends AsyncTask<String, Void, Weather > {


        @Override protected void onPostExecute( Weather weather ) {
            //super.onPostExecute( weather );
            DateFormat df = DateFormat.getTimeInstance();
            String sunrizeDate = df.format( new Date( weather.place.getSunrise() ) );
            String sunsetDate = df.format( new Date( weather.place.getSunset() ) );
            String updateDate = df.format( new Date( weather.place.getLastupdate() ) );

            DecimalFormat decimalFormat = new DecimalFormat( "#.#" );
            String tempFormat = decimalFormat.format( weather.currentCondition.getTemperature() );

            cityName.setText( weather.place.getCity() + "," + weather.place.getCountry() );
            temp.setText( "" + tempFormat + "C" );
            humidity.setText( "Humidity: " + weather.currentCondition.getHunidity() + "%" );
            pressure.setText( "Pressure: " + weather.currentCondition.getPressure() + "hPa" );
            wind.setText( "Wind: " + weather.wind.getSpeed() + "mps" );
            sunrise.setText( "Sunrise: " + sunrizeDate );
            sunset.setText( "Sunset: " + sunsetDate );
            updated.setText( "Last Updated: " + updateDate );
            description.setText(
                    "Condition: " + weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescription() + ")" );
        }

        @Override protected Weather doInBackground( String... params ) {
            String data = ( ( new WeatherHttpClient().getWeatherData( params[ 0 ] ) ) );
            weather = JSONWeatherParser.getWeather( data );
            weather.iconData = weather.currentCondition.getIcon();
            //new DownloadImageAsynchTask().execute(weather.iconData );

            Log.v( "Data: ", weather.place.getCity() );



            return weather;
        }
    }

    private class DownloadImageAsynchTask extends AsyncTask<String, Void, Bitmap > {
        @Override protected Bitmap doInBackground( String... params ) {

            return downloadImage( params[0] );
        }

        @Override protected void onPostExecute( Bitmap bitmap ) {
            iconView.setImageBitmap( bitmap );
        }

        private Bitmap downloadImage( String code ){


            try {
                final HttpURLConnection connection  = (HttpURLConnection)( new URL( Utils.ICON_URL + code + ".png" + "&APPID=" + Utils.API_KEY )).openConnection();
                //connection.setRequestMethod( "GET" );
                connection.setDoInput( true );
                connection.connect();
                int responseCode = connection.getResponseCode();
                if ( responseCode == HttpURLConnection.HTTP_OK )
                {
                    InputStream inputStream = connection.getInputStream();
                    final Bitmap myBitmap = BitmapFactory.decodeStream( inputStream );
                    inputStream.close();
                    return myBitmap;
                }

                connection.disconnect();
                return null;


            }
            catch ( MalformedURLException e ) {
                e.printStackTrace();
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void showInputDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
        builder.setTitle( "Change City" );
        final EditText cityInput = new EditText( MainActivity.this );
        cityInput.setInputType( InputType.TYPE_CLASS_TEXT );
        cityInput.setHint( "Portland, US" );
        builder.setView( cityInput );
        builder.setPositiveButton( "Submnit", new DialogInterface.OnClickListener() {
            @Override public void onClick( DialogInterface dialog, int which ) {
                CityPreference cityPreference = new CityPreference( MainActivity.this );
                cityPreference.setCity( cityInput.getText().toString() );
                String newCity = cityPreference.getCity();
                renderWeatherData( newCity );
            }
        } );
        builder.show();
    }

    @Override public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();
        if ( id == R.id.change_cityId )
        {
            showInputDialog();
        }
        return super.onOptionsItemSelected( item );
    }

    @Override public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }
}
