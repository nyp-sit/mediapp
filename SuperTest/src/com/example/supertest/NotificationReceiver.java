package com.example.supertest;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationReceiver extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewmedicine);

		TextView tv = new TextView(this);
		tv.setText("Yo!");
		
		setContentView(tv);
	}
}