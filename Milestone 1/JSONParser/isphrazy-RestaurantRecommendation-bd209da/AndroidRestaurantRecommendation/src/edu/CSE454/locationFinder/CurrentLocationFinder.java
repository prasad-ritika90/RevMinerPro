package edu.CSE454.locationFinder;

import java.util.List;
import java.util.Observable;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.util.Log;

public class CurrentLocationFinder extends Observable{
	
	private Context context;
	private LocationResult locationResult;
	
	public CurrentLocationFinder(Context context){
		this.context = context;
        locationResult = new MLocationResult();
	}
	
	public void getLocation(){
    	MyLocation myLocation = new MyLocation();
        myLocation.getLocation(context.getApplicationContext(), locationResult);
	}
	
	
	private class MLocationResult extends LocationResult{

		@Override
		public void gotLocation(Location location) {

            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Log.d("locationResult", "long: " + longitude + " lat: " + latitude);
			ReverseGeocode gcd = new ReverseGeocode();
			List<Address> addresses = gcd.getFromLocation(latitude, longitude, 2);
			if (addresses.size() > 0){
				Address address = addresses.get(0);
				String city = address.getLocality();
				if(city == null) city = "";
				else city += ", ";
				String state = address.getAdminArea();
				if(state == null) state = "";
				setChanged();
				notifyObservers(city + state);
			}
		}
	}
}
