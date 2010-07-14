package sky.zone;

import android.os.Bundle;
import android.app.*;
import android.content.Intent;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;


public class Search extends ListActivity {
    /** Author Wagner */
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.result);
	        
	        //String searched = "CN";
	        
	        Bundle bundle = this.getIntent().getExtras();
	        String searched = bundle.getString("search");
	      
	        
	        CarregaLista(searched);
	 
	       }
	   
	   public void CarregaLista(String searched)
	 	 {
	 		SQLiteDatabase myDB = null; 
	        try { 
	             myDB = this.openOrCreateDatabase("skyzone", MODE_PRIVATE, null); 
	                         
	             Cursor mCursor = myDB.rawQuery("SELECT * FROM Zones WHERE Name LIKE '" + searched + "%';", null);
	                     
	             startManagingCursor(mCursor); 
	              
	             ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.list, mCursor, new String[] {"Icon", "Name", "Country", "State", "City", "Phone"}, new int[] {R.id.Icon, R.id.Name, R.id.Country, R.id.State, R.id.City, R.id.Phone});            
	             this.setListAdapter(adapter); 
	              
	             this.getListView().setTextFilterEnabled(true); 
	           } finally { 
	             if (myDB != null) { 
	                  myDB.close(); 
	             } 
	           } 
	        
	 	 }
	
}

