package com.example.eventbustest;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity{
	TextView tv_2;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getDefault().register(this);
        tv_2 = (TextView) findViewById(R.id.tv_2);
    }
	 
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}
	
	 public void doClick(View view){
		 EventBus.getDefault().post(new FirstEvent("FirstEvent btn clicked"));  
	 }
	 
	 public void onEventMainThread(SecondEvent event){
    	 tv_2.setText(event.getMsg());  
         Toast.makeText(this, event.getMsg(), Toast.LENGTH_LONG).show();  
    }
}
