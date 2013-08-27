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
package com.alfarabi.aplikasinumerik.activity;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.activity.newtonraphson.NewtonRaphsonActivity;
import com.alfarabi.aplikasinumerik.activity.secant.SecantActivity;
import com.alfarabi.aplikasinumerik.adapter.ListMenuAdapter;
import com.alfarabi.aplikasinumerik.extend.GlobalActivity;
import com.alfarabi.aplikasinumerik.tools.Constant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MenuActivity extends GlobalActivity implements OnItemClickListener{
	
	ListView listMenu ;
	ListMenuAdapter listMenuAdapter ;
	
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
		
		listMenuAdapter = new ListMenuAdapter(this, com.alfarabi.aplikasinumerik.R.layout.list_menu_layout
				, getResources().getStringArray(com.alfarabi.aplikasinumerik.R.array.list_menu_numeric_fixed));
	}

	@Override
	public void initLocalUI() {
		// TODO Auto-generated method stub
		super.initLocalUI();
		
		activityLayout.addView(getLocalLayout());
		listMenu = (ListView) findViewById(com.alfarabi.aplikasinumerik.R.id.listMenu);
		
	}

	@Override
	public void useDynamicDataStarting() {
		// TODO Auto-generated method stub
		super.useDynamicDataStarting();
		homeButton.setBackgroundResource(R.drawable.orange);
		listMenu.setAdapter(listMenuAdapter);
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
		backButton.setVisibility(Button.INVISIBLE);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		super.setListener();
		listMenu.setOnItemClickListener(this);
		
	}
	@Override
	public View getLocalLayout() {
		RelativeLayout localLayout = (RelativeLayout) getLayoutInflater().inflate(com.alfarabi.aplikasinumerik.R.layout.menu_acitivity, null, false);
		return localLayout;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
		// TODO Auto-generated method stub
		
		if (adapter.getItemAtPosition(position).toString().equals(Constant.METODE_SECANT)) {
			Intent intent = new Intent(this, SecantActivity.class);
			startActivityForResult(intent, Constant.CLOSE_APPLICATION);
			
		}else if (adapter.getItemAtPosition(position).toString().equals(Constant.METODE_NEWTON_RAPSON)) {
			//Toast.makeText(this, "Fungsi ini belum tersedia.", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, NewtonRaphsonActivity.class);
			startActivityForResult(intent, Constant.CLOSE_APPLICATION);
		}else if (adapter.getItemAtPosition(position).toString().equals(Constant.METODE_ELIMINASI_GAUSS)) {
			Toast.makeText(this, "Fungsi ini belum tersedia.", Toast.LENGTH_SHORT).show();
			
		}
		
	}


}
