package edu.CSE454.locationFinder;

import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class LocationFinderPage extends Activity {
	
	protected final Handler HANDLER = new Handler();
	
	private TextView tv;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        tv = new TextView(this);
        LocationFinderModel lfm = new LocationFinderModel(this);
        lfm.addObserver(new LocationObserver());
        lfm.updateCurrentLocation();
        
        setContentView(tv);
    }
    
    protected class LocationObserver implements Observer{
		
		public void update(Observable arg0, Object location) {
			
			//update users' current location to the tv
			final String finalLocation = (String)location;
			final Runnable updateCurrentLocation = new Runnable(){
				public void run(){
					tv.setText("" + finalLocation);
			    }
			};
			
			new Thread() {
	            public void run() {
	            	HANDLER.post(updateCurrentLocation);
	            }
	        }.start();
		}
	}
    
}