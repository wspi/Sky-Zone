package sky.zone;

import android.location.LocationManager;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.maps.MyLocationOverlay;




public class Mapa extends MapActivity {
    /** Author Wagner Pinto */
	
	private MyLocationOverlay myLocOverlay;
	private LocationManager lm;
	private LocationListener locationlistener;
	
	private MapView mapView;
	private MapController mc;
	
		
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);    
        
        LocationListener locationlistener = new MyLocationListener();
        
        lm.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 
                0, 
                0, 
                locationlistener);
            
            mapView = (MapView) findViewById(R.id.mapview);
            mc = mapView.getController();
            
            myLocOverlay = new MyLocationOverlay(this, mapView);
    		myLocOverlay.enableMyLocation();
    		mapView.getOverlays().add(myLocOverlay);
    		
    		View zoomView = mapView.getZoomControls();
    		LinearLayout myzoom = (LinearLayout) findViewById(R.id.myzoom);
    		myzoom.addView(zoomView);
    		mapView.displayZoomControls(true);
	

        }
 
   
    protected boolean isRouteDisplayed() {
        return false;
    }


private class MyLocationListener implements LocationListener 
{
    @Override
    public void onLocationChanged(Location loc) {
        if (loc != null) {                
            
            GeoPoint p = new GeoPoint(
                    (int) (loc.getLatitude() * 1E6), 
                    (int) (loc.getLongitude() * 1E6));
            mc.animateTo(p);
            mc.setZoom(16);                
            mapView.invalidate();
        }
    }

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
}
    