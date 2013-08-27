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

import java.util.Timer;
import java.util.TimerTask;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.extend.GlobalActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class KritikActivity extends GlobalActivity implements OnClickListener, OnFocusChangeListener{
	
	private static final String phoneNumber = "08986560230";
	Spinner statusUserSpinner ;
	EditText namaSmsET, bodySmsET ;
	Button submitSmsButton;
	SmsManager smsManager ;
	StringBuffer sb ;
	ProgressDialog pd ;
	Timer timer ;
	AlertDialog.Builder alertBuilder ;
	
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
		super.initLocalUI();
		activityLayout.addView(getLocalLayout());
		statusUserSpinner = (Spinner) findViewById(R.id.statusUserSpinner);
		
		namaSmsET = (EditText) findViewById(R.id.namaSmsET);
		bodySmsET = (EditText) findViewById(R.id.bodySmsET);
		submitSmsButton = (Button) findViewById(R.id.submitSmsButton);
		
		kritikButton.setBackgroundResource(R.drawable.orange);

	}

	@Override
	public void useDynamicDataStarting() {
		super.useDynamicDataStarting();
		titleTV.setText("Kritik Dan Saran");
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
	}

	@Override
	public void setListener() {
		
		super.setListener();
		submitSmsButton.setOnClickListener(this);
		namaSmsET.setOnFocusChangeListener(this);
		bodySmsET.setOnFocusChangeListener(this);
		submitSmsButton.setOnFocusChangeListener(this);
		statusUserSpinner.setOnFocusChangeListener(this);
	}

	@Override
	public View getLocalLayout() {
		// TODO Auto-generated method stub
		RelativeLayout localLayout = (RelativeLayout) getLayoutInflater().inflate(com.alfarabi.aplikasinumerik.R.layout.kritik_activity, null, false);
		return localLayout;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.submitSmsButton:
			if (namaSmsET.getText().toString().equals("")||bodySmsET.getText().equals("")) {
				Toast.makeText(this, "Data yang anda masukan belum lengkap", Toast.LENGTH_SHORT).show();
			}else{
				pd = new ProgressDialog(this);
				alertBuilder = new AlertDialog.Builder(this);
				alertBuilder.setMessage("Anda akan dikenakan biaya sms sesuai harga ketentuan biaya sms dari Pihak Provider yang di gunakan oleh anda" +
						", terimakasih. apakah anda hendak melanjutkan ?").setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
							
							}
						}).setPositiveButton("Setuju", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								pd.show();
								timer = new Timer();
								timer.scheduleAtFixedRate(new TimerTask() {
									int i = 0;
									@Override
									public void run() {
										i++ ;
										if (i==100) {
											pd.dismiss();
											timer.cancel();
										}
									}
								}, 0, 10);
								sendMessage();
							}
						}).show();
								
			}
			break;

		default:
			break;
		}
		
	}
private void sendMessage(){
	try {
		sb = new StringBuffer();
		sb.append("[Pengguna Aplikasi Numerik]");
		sb.append("\n");
		sb.append("Nama : ");
		sb.append(namaSmsET.getText());
		sb.append("\n");
		sb.append("Status : ");
		sb.append(statusUserSpinner.getSelectedItem().toString());
		sb.append("\n");
		sb.append(bodySmsET.getText());
		smsManager = SmsManager.getDefault();
//		if (SmsManager.STATUS_ON_ICC_UNSENT==smsManager.) {
//			
//		}
		smsManager.sendTextMessage(phoneNumber, null, sb.toString(), null, null);
		namaSmsET.setText("");
		bodySmsET.setText("");
		Toast.makeText(this, "Pengiriman berhasil, Terimakasih untuk kritik, saran ataupun masukannya", Toast.LENGTH_SHORT).show();
	} catch (Exception e) {
		// TODO: handle exception
		//e.printStackTrace();
		//e.printStackTrace();
		Toast.makeText(this, "Pengiriman gagal, cek layanan provider anda", Toast.LENGTH_SHORT).show();
	}
	
}

@Override
public void onFocusChange(View v, boolean hasFocus) {
	// TODO Auto-generated method stub
//	if (namaSmsET.isFocused()||bodySmsET.isFocused()) {
//		footerLayout.setVisibility(LinearLayout.GONE);
//	}else{
//		footerLayout.setVisibility(LinearLayout.VISIBLE);
//	}
//	
//	
	}
}
