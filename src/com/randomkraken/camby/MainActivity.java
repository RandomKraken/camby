package com.randomkraken.camby;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final int LED_NOTIFICATION_ID = 0;

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void createNotification()
	{
		// Prepare intent which is triggered if the
		// notification is selected
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		// Build notification
		Notification notif = new Notification.Builder(this)
		        .setContentTitle("The camera is activated!")
		        .setContentText("An application is using the camera!")
		        .setSmallIcon(R.drawable.ic_launcher)
		        .setContentIntent(pIntent)
		        .setAutoCancel(false)
		        .setOngoing(true)
		        .build();
		
		// Create glowing red LED
		notif.flags = notif.flags | Notification.FLAG_SHOW_LIGHTS;
		notif.ledARGB = 0xFFff0000;
		notif.ledOnMS = 1;
		notif.ledOffMS = 0;
		
		// Post notification
		NotificationManager notificationManager = 
		  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		notificationManager.notify(LED_NOTIFICATION_ID, notif);
	}
	
	public void button1_onClick(View v) throws InterruptedException
	{
		createNotification();
	}
	
	public void button2_onClick(View v)
	{
		// Delete notification
		NotificationManager notificationManager = 
				  (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				
		notificationManager.cancel(LED_NOTIFICATION_ID);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
