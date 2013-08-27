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
package com.alfarabi.aplikasinumerik.activity.newtonraphson;


import java.util.List;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.adapter.NewtonRhapsonListAdapter;
import com.alfarabi.aplikasinumerik.adapter.ResultSecantListAdapter;
import com.alfarabi.aplikasinumerik.extend.GlobalActivity;
import com.alfarabi.aplikasinumerik.model.NewtonRhapson;
import com.alfarabi.aplikasinumerik.model.Secant;
import com.alfarabi.aplikasinumerik.tools.Constant;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ResultNewtonRaphsonActivity extends GlobalActivity{

	ListView resultSecantListView ;
	List<NewtonRhapson> newtonRhapsons ;
	NewtonRhapsonListAdapter newtonRhapsonListAdapter ;
	
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
		newtonRhapsons = getIntent().getExtras().getParcelableArrayList(Constant.BUNDLE_RESULT_NEWTON_RHAPSON);
	}

	@Override
	public void initLocalObject() {
		// TODO Auto-generated method stub
		super.initLocalObject();
		newtonRhapsonListAdapter = new NewtonRhapsonListAdapter(this, com.alfarabi.aplikasinumerik.R.layout.result_secant_list_layout, newtonRhapsons);
	}

	@Override
	public void initLocalUI() {
		// TODO Auto-generated method stub
		super.initLocalUI();
		activityLayout.addView(getLocalLayout());
		resultSecantListView = (ListView) findViewById(R.id.resultSecantLV);
	}

	@Override
	public void useDynamicDataStarting() {
		// TODO Auto-generated method stub
		super.useDynamicDataStarting();
		titleTV.setText("Table - NR");
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
		resultSecantListView.setAdapter(newtonRhapsonListAdapter);
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		super.setListener();
	}

	@Override
	public View getLocalLayout() {
		// TODO Auto-generated method stub
		RelativeLayout localLayout = (RelativeLayout) getLayoutInflater().inflate(com.alfarabi.aplikasinumerik.R.layout.result_newton_rhapson_activity, null, false);
		return localLayout;
	}
	
}
