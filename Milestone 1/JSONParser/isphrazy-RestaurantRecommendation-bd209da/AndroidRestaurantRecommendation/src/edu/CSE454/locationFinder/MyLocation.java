package edu.CSE454.locationFinder;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class MyLocation extends Activity{

    Timer timer;
    LocationManager locationManager;
    LocationResult locationResult;
    boolean gpsEnable=false;
    boolean networkEnable=false;

    public boolean getLocation(Context context, LocationResult result){
        //I use LocationResult callback class to pass location value from MyLocation to user code.
        locationResult = result;
        if(locationManager == null)
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        //exceptions will be thrown if provider is not permitted.
        try{
        	gpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        	networkEnable=locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch(Exception e){
        	e.printStackTrace();
        }

        
        //don't start listeners if no provider is enabled
        if(!gpsEnable && !networkEnable)
            return false;

        if(gpsEnable)
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListenerGps);
        if(networkEnable)
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
        
        timer = new Timer();


        timer.schedule(new GetLastLocation(), 0);
        return true;
    }

    LocationListener locationListenerGps = new LocationListener() {
    	
        public void onLocationChanged(Location location) {
        	updateLocation(location, this);

        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
        	updateLocation(location, this);
        }
        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    };

    class GetLastLocation extends TimerTask {
        @Override

        public void run() {

            //Context context = getClass().getgetApplicationContext();

             Location notLocation = null, 
            		  gpsLocation = null;
             
             if(gpsEnable)
                 gpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             if(networkEnable)
                 notLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

             //if there are both values use the latest one
             if(gpsLocation != null && notLocation != null){
                 if(gpsLocation.getTime() > notLocation.getTime())
                     locationResult.gotLocation(gpsLocation);
                 else
                     locationResult.gotLocation(notLocation);
             }else if(gpsLocation!=null){
                 locationResult.gotLocation(gpsLocation);
             }else if(notLocation!=null){
                 locationResult.gotLocation(notLocation);
             }
        }
    }

	protected void updateLocation(Location location, LocationListener listener) {
        timer.cancel();
        locationResult.gotLocation(location);
        locationManager.removeUpdates(listener);
        locationManager.removeUpdates(locationListenerNetwork);
	}

}
