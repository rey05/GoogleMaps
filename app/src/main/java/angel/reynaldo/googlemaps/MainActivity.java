package angel.reynaldo.googlemaps;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity implements GoogleMap.OnMapClickListener {


//Declaramos los atributos que se utilizaran, y la posicion que utilizaremos
    private final LatLng MI_POSICION= new LatLng(19.768333, -101.189444);
    private GoogleMap mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(MI_POSICION, 15));

        //mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(MI_POSICION, 15));
        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.getUiSettings().setCompassEnabled(true);

        mapa.addMarker(new MarkerOptions().position(MI_POSICION).title("ITCA").snippet("Instituto Tecnologico ").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);

        //google.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //google.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //google.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        //google.setMapType(GoogleMap.MAP_TYPE_NONE);
    }

    public void moveCamera(View view){
        mapa.moveCamera(CameraUpdateFactory.newLatLng(MI_POSICION));
    }

    public void animateCamera (View view){

        if (mapa.getMyLocation()!= null)
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mapa.getMyLocation().getLatitude(), mapa.getMyLocation().getLongitude()), 15));
    }
            public void addMarker(View view){

    mapa.addMarker(new MarkerOptions().position(new LatLng(mapa.getCameraPosition().target.latitude, mapa.getCameraPosition().target.longitude)));

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            mapa.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.action_settings2){

            mapa.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }

        if (id==R.id.action_settings3){
            mapa.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }

        if (id==R.id.action_settings4){
            mapa.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }

        if (id== R.id.action_settings5){
            mapa.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapClick(LatLng puntoPulsado) {

        mapa.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }
}