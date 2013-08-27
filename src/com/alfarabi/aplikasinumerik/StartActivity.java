/*============== Dibuat oleh ===================*										*
 * 												* 
 * 		Nama 		: Alfarabi Dwi Karuniawan	*
 *		NIM			: 2010140263				*	
 * 		UNIV		: Universitas Pamulang		*
 * 		Fakultas	: Tekhnik Informatika		*
 * 		Email		: alfarabidwik@gmail.com	*
 * 												*
 *==============================================*
 */	
package com.alfarabi.aplikasinumerik;

import java.util.Timer;
import java.util.TimerTask;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.activity.MenuActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class StartActivity extends Activity{

	Timer timer ;
	Intent intent;
	ProgressBar waitingSeekBar ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_activity);
		timer = new Timer();
		waitingSeekBar = (ProgressBar) findViewById(R.id.waitingSeekBar);
		timer.scheduleAtFixedRate(new TimerTask() {
			int i = 1 ;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (i==100) {
							intent = new Intent(StartActivity.this, MenuActivity.class);
							startActivity(intent);
							timer.cancel();
							StartActivity.this.finish();
						}
						waitingSeekBar.setProgress(i);
						i++ ;
					}
				});
			}
		}, 0, 100);

	}

}
