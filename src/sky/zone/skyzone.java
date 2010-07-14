package sky.zone;

import java.io.IOException;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class skyzone extends TabActivity {
    /** Author Wagner Pinto */

	private TabHost mTabHost;
	private EditText txtMiles;
	private Button btnLocate;
	private EditText txtsearch;
	private Button btnsearch;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    mTabHost = getTabHost();
	    
	    mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("About").setContent(R.id.home));
	    mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Search").setContent(R.id.search));
	    mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("DZ Nearby").setContent(R.id.nearby));
	    
	    mTabHost.setCurrentTab(0);
	    
	    CreateDabase();
	   
	    txtMiles = (EditText) findViewById(R.id.txt_miles);  
        btnLocate = (Button) findViewById(R.id.btn_locate);
        
        btnLocate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View maps) {

                String str = "Looking for Dropzones at a distance of " + 
                      txtMiles.getText().toString() + " miles from here...";
                	  Toast.makeText(getBaseContext(), str, 
                      Toast.LENGTH_SHORT).show();
                
                Intent mapa = new Intent(maps.getContext(), Mapa.class);
                startActivityForResult(mapa, 0);
                
                
            }
        });                
	    
        txtsearch = (EditText) findViewById(R.id.txt_search);
        btnsearch = (Button) findViewById(R.id.btn_search);
        
        
        btnsearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	String str = "Searching for " + 
                      txtsearch.getText().toString() + "...";
                Toast.makeText(getBaseContext(), str, 
                 Toast.LENGTH_SHORT).show();
                
                Bundle bundle = new Bundle();
                bundle.putString("search", txtsearch.getText().toString());
                
                Intent search = new Intent(view.getContext(), Search.class);
                search.putExtras(bundle);
                startActivityForResult(search, 0);

            }
        });                
            	                	    
	}
	
	public void CreateDabase(){
		
		DataBaseHelper myDbHelper = new DataBaseHelper(this);
        myDbHelper = new DataBaseHelper(this);
 
        try {
 
        	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
 
 	try {
 
 		myDbHelper.openDataBase();
 
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}

		
		
	}
	}
	
