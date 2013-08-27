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
package com.alfarabi.aplikasinumerik.activity.other;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.R.layout;
import com.alfarabi.aplikasinumerik.R.menu;
import com.alfarabi.aplikasinumerik.extend.GlobalActivity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class TentangActivity extends GlobalActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initDataAccepted();
		initLocalObject();
		initLocalUI();
		useDynamicDataStarting();
		setListener();
	}

	@Override
	public void initDataAccepted() {
		// TODO Auto-generated method stub
		super.initDataAccepted();
	}

	@Override
	public void initLocalObject() {
		// TODO Auto-generated method stub
		super.initLocalObject();
	}

	@Override
	public void initLocalUI() {
		// TODO Auto-generated method stub
		super.initLocalUI();
		activityLayout.addView(getLocalLayout());
		tentangButton.setBackgroundResource(R.drawable.orange);
		
	}

	@Override
	public void useDynamicDataStarting() {
		// TODO Auto-generated method stub
		super.useDynamicDataStarting();
		titleTV.setText("Tentang Aplikasi");
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		super.setListener();
	}

	@Override
	public View getLocalLayout() {
		// TODO Auto-generated method stub
		RelativeLayout localLayout = (RelativeLayout) getLayoutInflater().inflate(com.alfarabi.aplikasinumerik.R.layout.tentang_activity, null, false);
		return localLayout;
	}


}
