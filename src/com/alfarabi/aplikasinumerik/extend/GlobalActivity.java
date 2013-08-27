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
package com.alfarabi.aplikasinumerik.extend;

import java.util.Timer;
import java.util.TimerTask;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.activity.MenuActivity;
import com.alfarabi.aplikasinumerik.activity.other.KritikActivity;
import com.alfarabi.aplikasinumerik.activity.other.PanduanActivity;
import com.alfarabi.aplikasinumerik.activity.other.TentangActivity;
import com.alfarabi.aplikasinumerik.interfaces.GlobalMethod;
import com.alfarabi.aplikasinumerik.tools.Constant;
import com.alfarabi.aplikasinumerik.tools.MyKeyboard;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GlobalActivity extends Activity implements GlobalMethod, OnClickListener, AnimationListener{

	//public String onActivity ;
	public RelativeLayout activityLayout ;
	public Button backButton , closeKeyboardButton;
	public LinearLayout homeButton, panduanButton, tentangButton, kritikButton, keluarButton;
	public LinearLayout groupKeyboardLayout, footerLayout ;
	public TextView titleTV ;
	
	Animation openKeyboardAnim, closeKeyboardAnim ;
	Intent intent ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity);
		
//		initDataAccepted();
//		initLocalObject();
//		initLocalUI();
	}

	@Override
	public void initDataAccepted() {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void initLocalObject() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initLocalUI() {
		// TODO Auto-generated method stub
		titleTV = (TextView)findViewById(R.id.titleTextView);
		activityLayout = (RelativeLayout)findViewById(R.id.activityLayout);
		backButton = (Button) findViewById(R.id.backButton);
		homeButton = (LinearLayout) findViewById(R.id.homeButtonLayout);
		panduanButton = (LinearLayout) findViewById(R.id.panduanButtonLayout);
		tentangButton = (LinearLayout) findViewById(R.id.tentangButtonLayout);
		kritikButton = (LinearLayout) findViewById(R.id.kritikButtonLayout);
		keluarButton = (LinearLayout) findViewById(R.id.keluarButtonLayout);
		
		footerLayout = (LinearLayout) findViewById(R.id.footerLayout);
		closeKeyboardButton = (Button) findViewById(R.id.closeKeyboardBtn);
		groupKeyboardLayout = (LinearLayout) findViewById(R.id.groupKeyboardLayout);
		openKeyboardAnim = AnimationUtils.loadAnimation(this, R.anim.keyboard_open);
		closeKeyboardAnim = AnimationUtils.loadAnimation(this, R.anim.keyboard_close);
		
	}

	@Override
	public void useDynamicDataStarting() {
		// TODO Auto-generated method stub
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		backButton.setOnClickListener(this);
		homeButton.setOnClickListener(this);
		panduanButton.setOnClickListener(this);
		tentangButton.setOnClickListener(this);
		kritikButton.setOnClickListener(this);
		keluarButton.setOnClickListener(this);
		
		closeKeyboardButton.setOnClickListener(this);
	}

	@Override
	public View getLocalLayout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId()==R.id.backButton) {
			if (groupKeyboardLayout.getVisibility()==LinearLayout.VISIBLE) {
				closeKeyboard();
			}
			onBackPressed();
		}else if(v.getId()==R.id.closeKeyboardBtn){
			if (groupKeyboardLayout.getVisibility()==LinearLayout.VISIBLE) {
				closeKeyboard();
			}
		}else if(v.getId()==R.id.homeButtonLayout){
			if (this.getClass()!=MenuActivity.class) {
				intent = new Intent(this, MenuActivity.class);
				startActivityForResult(intent, Constant.CLOSE_APPLICATION);
			}
		}else if(v.getId()==R.id.panduanButtonLayout){
			if (this.getClass()!=PanduanActivity.class) {
				intent = new Intent(this, PanduanActivity.class);
				startActivityForResult(intent, Constant.CLOSE_APPLICATION);
			}
		}else if(v.getId()==R.id.tentangButtonLayout){
			if (this.getClass()!=TentangActivity.class) {
				intent = new Intent(this, TentangActivity.class);
				startActivityForResult(intent, Constant.CLOSE_APPLICATION);
			}
		}else if(v.getId()==R.id.kritikButtonLayout){
			if (this.getClass()!=KritikActivity.class) {
				intent = new Intent(this, KritikActivity.class);
				startActivityForResult(intent, Constant.CLOSE_APPLICATION);
			}
		}else if(v.getId()==R.id.keluarButtonLayout){
			
			logoutOptions();

		}
		MyKeyboard.focusKeyType(MyKeyboard.TYPE_NORMAL);
	}
	
	public void openKeyBoard(){
		groupKeyboardLayout.setVisibility(LinearLayout.VISIBLE);
		groupKeyboardLayout.startAnimation(openKeyboardAnim);
	}
	
	public void closeKeyboard(){
		groupKeyboardLayout.startAnimation(closeKeyboardAnim);	
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode==Constant.CLOSE_APPLICATION) {
			setResult(Constant.CLOSE_APPLICATION);
			this.finish();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (this.getClass()==MenuActivity.class) {
			logoutOptions();
			return false ;
		}else{
			return super.onKeyDown(keyCode, event);
		}
		
	}
	
	public void logoutOptions(){
		new AlertDialog.Builder(this)
		.setMessage("Keluar dari aplikasi").setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					
					}
				}).setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						setResult(Constant.CLOSE_APPLICATION);
						finish();
					}
				}).show();
	}

}
