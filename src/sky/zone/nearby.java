package sky.zone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class nearby extends Activity {
    /** Author Wagner Pinto */
    
private EditText txtMiles;
private Button btnLocate;

@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nearby);
        txtMiles = (EditText) findViewById(R.id.txt_miles);  
        btnLocate = (Button) findViewById(R.id.btn_locate);
        
        btnLocate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String str = "Looking for Dropzones at a distance of " + 
                      txtMiles.getText().toString() + " miles from here...";
                Toast.makeText(getBaseContext(), str, 
                 Toast.LENGTH_SHORT).show();
                
                Intent mapa = new Intent(view.getContext(), Mapa.class);
                startActivityForResult(mapa, 0);
            }
        });                
        
    }
    
   
}