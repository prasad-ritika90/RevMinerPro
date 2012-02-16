package edu.CSE454.locationFinder;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;

public class LocationFinderModel extends Observable{
	
	private Observer observer;
	private Context context;
	
	public LocationFinderModel(Context context){
		this.context = context;
		observer = null;
	}
	
	/**
	 * get the location of the user from gps/internet, update to the observer
	 */
	public void updateCurrentLocation(){
		CurrentLocationFinder locationFinder = new CurrentLocationFinder(context);
		locationFinder.addObserver(observer);
		locationFinder.getLocation();
	}
	
	public void addObserver(Observer o){
		super.addObserver(o);
		observer = o;
	}
}
