package com.example.eventbustest;

import de.greenrobot.event.EventBus;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	TextView  tv_1;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        
        tv_1 = (TextView) findViewById(R.id.tv_1);
        
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	EventBus.getDefault().unregister(this);
    }
    
    public void doClick(View view){
    	if(view.getId() == R.id.btn_1){
    		Intent intent = new Intent(this, SecondActivity.class);
    		startActivity(intent);
    	} else if(view.getId() == R.id.btn_2){
    		EventBus.getDefault().postSticky(new SecondEvent("second Event btn clicked")); 
    	}
    }
    
    public void onEventMainThread(FirstEvent event){
    	 tv_1.setText(event.getMsg());  
         Toast.makeText(this, event.getMsg(), Toast.LENGTH_LONG).show();  
    }
}
