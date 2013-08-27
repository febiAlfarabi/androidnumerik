package com.alfarabi.aplikasinumerik.activity.newtonraphson;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.activity.secant.ResultSecantActivity;
import com.alfarabi.aplikasinumerik.activity.secant.SecantActivity;
import com.alfarabi.aplikasinumerik.control.Calculator;
import com.alfarabi.aplikasinumerik.extend.GlobalActivity;
import com.alfarabi.aplikasinumerik.interfaces.GlobalMethod;
import com.alfarabi.aplikasinumerik.model.Input;
import com.alfarabi.aplikasinumerik.model.NewtonRhapson;
import com.alfarabi.aplikasinumerik.model.Secant;
import com.alfarabi.aplikasinumerik.tools.Constant;
import com.alfarabi.aplikasinumerik.tools.MyKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.InputType;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NewtonRaphsonActivity extends GlobalActivity implements GlobalMethod, OnClickListener, OnFocusChangeListener{
	
	EditText questionET, x0ET, tolnET; 
	MyKeyboard fieldQuestion, fieldX0, fieldToln ;
	Button hitungButton ;
	LinearLayout keyboardLinLayout ;
	List<Input> textResult ;
	Intent intent ;
	Double x_nol = 0.0, fx_nol = 0.0, fx_nol_aksen = 0.0 ; 
	Double iterasionFXAt = 0.0 ;
	NumberFormat numberFormat ;
	Calculator calculator ;
	
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
		super.initDataAccepted();
	}

	@Override
	public void initLocalObject() {
		super.initLocalObject();
		fieldQuestion = new MyKeyboard();
		fieldX0 = new MyKeyboard();
		fieldToln = new MyKeyboard();
		calculator = new Calculator(this);
	}

	@Override
	public void initLocalUI() {
		super.initLocalUI();
		activityLayout.addView(getLocalLayout());
		questionET = (EditText) findViewById(R.id.questionET);
		x0ET = (EditText) findViewById(R.id.x0ET);
		tolnET = (EditText) findViewById(R.id.tolnET);
		keyboardLinLayout = (LinearLayout) findViewById(R.id.keyboardLinLayout);
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
		hitungButton = (Button) findViewById(R.id.hitungButton);
	}

	@Override
	public void useDynamicDataStarting() {
		// TODO Auto-generated method stub
		super.useDynamicDataStarting();
		titleTV.setText("Newton Raphson");
		questionET.setInputType(InputType.TYPE_NULL);
		x0ET.setInputType(InputType.TYPE_NULL);
		tolnET.setInputType(InputType.TYPE_NULL);
		fieldToln.initializeLayout(keyboardLinLayout, tolnET);
		fieldToln.writingText(Input.TYPE_NUMBER, new SpannableString("10"));
	}

	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		super.setListener();
		questionET.setOnFocusChangeListener(this);
		x0ET.setOnFocusChangeListener(this);
		tolnET.setOnFocusChangeListener(this);
		questionET.setOnClickListener(this);
		x0ET.setOnClickListener(this);
		tolnET.setOnClickListener(this);
		hitungButton.setOnClickListener(this);
	}

	@Override
	public View getLocalLayout() {
		RelativeLayout localLayout = (RelativeLayout) getLayoutInflater().inflate(com.alfarabi.aplikasinumerik.R.layout.newton_raphson_activity, null, false);
		return localLayout;
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		super.onClick(v);

		if (v.getId()==R.id.questionET ||v.getId()==R.id.x0ET || v.getId()==R.id.tolnET) {
			
			if (groupKeyboardLayout.getVisibility()==LinearLayout.INVISIBLE) {
				super.openKeyBoard();
			}
			
		}else if (v.getId()==R.id.hitungButton) {
			
			if (questionET.getText().toString().equals("") || x0ET.getText().toString().equals("") 
					|| tolnET.getText().toString().equals("")) {
				Toast.makeText(this, "Input yang anda masukan belum lengkap", Toast.LENGTH_LONG).show();
			
			}else if (!calculator.validationInput(questionET.getText().toString())||!calculator.validationInput(x0ET.getText().toString())
					|| !calculator.validationInput(tolnET.getText().toString())) {
				Toast.makeText(this, "Input yang anda masukan mengandung kesalahan", Toast.LENGTH_LONG).show();
			
			}else if (!fieldToln.getTexts().get(fieldToln.getTexts().size()-1).getType().equals(Input.TYPE_SUP)
					|| !calculator.isNumber(String.valueOf(fieldToln.getTexts().get(fieldToln.getTexts().size()-1).getSpannableString()))) {
				Toast.makeText(this, "Input yang anda masukan mengandung kesalahan", Toast.LENGTH_LONG).show();
			
			}else{
				//http://id.wikipedia.org/wiki/Turunan_fungsi
				/*
				 * Refference
				 * http://aimprof08.wordpress.com/2012/08/31/metode-newton-raphson-newton-raphson-method/
				 */
//				String temp = "" ;
//				for (int j = 0; j < textQuestions.size(); j++) {
//					temp += textQuestions.get(j).getSpannableString().toString();
//				}
//				Toast.makeText(this, temp, Toast.LENGTH_LONG).show();
//				
				try{
				Calculator calculator = new Calculator(this);
				List<Input> textQuestions = new ArrayList<Input>();
				textResult = new ArrayList<Input>();
				textQuestions = calculator.groupingChar(textResult, fieldQuestion.getTexts(), 0);
			
				List<Input> textX0 = new ArrayList<Input>(); 
				textResult = new ArrayList<Input>();
				textX0.addAll(calculator.groupingChar(textResult, fieldX0.getTexts(), 0));
				
				List<Input> textToln = new ArrayList<Input>();
				textResult = new ArrayList<Input>();
				textToln.addAll(calculator.groupingChar(textResult, fieldToln.getTexts(), 0));
				
			
				List<NewtonRhapson> resultList = new ArrayList<NewtonRhapson>(); 
				resultList.addAll(getTableResult(textQuestions, textX0, textToln));
				//Log.i("Kelar Result", "kelarrr");
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList(Constant.BUNDLE_RESULT_NEWTON_RHAPSON, (ArrayList<? extends Parcelable>) resultList);
				intent = new Intent(NewtonRaphsonActivity.this, ResultNewtonRaphsonActivity.class);
				intent.putExtras(bundle);
				startActivityForResult(intent, Constant.CLOSE_APPLICATION);
				
				}catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(this, "Kemungkinan input yang anda masukan mengandung kesalahan : Kode kesalahan (8)  : "+e.getMessage(), Toast.LENGTH_SHORT).show();
					//e.printStackTrace();
				}
				
				
			}	
		}
	}
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		questionET.setBackgroundResource(R.drawable.normal_editext);
		x0ET.setBackgroundResource(R.drawable.normal_editext);
		tolnET.setBackgroundResource(R.drawable.normal_editext);
		if (v.getId()==R.id.questionET) {
			questionET.setBackgroundResource(R.drawable.selected_editext);
			fieldQuestion.initializeLayout(keyboardLinLayout, questionET);
		}else if (v.getId()==R.id.x0ET){
			x0ET.setBackgroundResource(R.drawable.selected_editext);
			fieldX0.initializeLayout(keyboardLinLayout, x0ET);
		}else if (v.getId()==R.id.tolnET){
			tolnET.setBackgroundResource(R.drawable.selected_editext);
			fieldToln.initializeLayout(keyboardLinLayout, tolnET);
		}
		
		if (groupKeyboardLayout.getVisibility()==LinearLayout.INVISIBLE) {
			super.openKeyBoard();
		}
	}
	
	
	private List<NewtonRhapson> getTableResult(List<Input> textQuestion, List<Input> textX0, List<Input> textToln)throws Exception{
		
		x_nol = calculator.getValueOfVAr(textX0); // == X0
		Double tolnPositif = calculator.getValueOfVAr(textToln);
		Double tolnNegatif = 0.0-tolnPositif;
		String decimalFormat = "#.";
		
		for (int i = 0; i < String.valueOf(tolnPositif).length()-1; i++) {
			decimalFormat = decimalFormat+"#";
		}
		numberFormat = new DecimalFormat(decimalFormat);
		
		List<NewtonRhapson> result = new ArrayList<NewtonRhapson>();
		int i = 0;
		boolean loop = true; 
		while(loop) {
			List<Input> inputs = new ArrayList<Input>();
			inputs.addAll(textQuestion);
			NewtonRhapson newtonRhapson = new NewtonRhapson();
			newtonRhapson.setX_nol(new Double(numberFormat.format(x_nol)));
			
			inputs = calculator.solveVarX(inputs, 0, x_nol);
			//myLogJ(inputs, "solveVarX");
			inputs = calculator.solveSupscript(inputs, 0);
			//myLogJ(inputs, "solveSupscript");
			inputs = calculator.solveMath(inputs, 0); 
			//myLogJ(inputs, "solveMath");
			fx_nol = new Double(inputs.get(0).getSpannableString().toString());
			
			newtonRhapson.setFx_nol(new Double(numberFormat.format(fx_nol)));
			inputs = new ArrayList<Input>();
			inputs.addAll(textQuestion);
			inputs = calculator.solveOneDifferential(inputs, 0);
			//myLogJ(inputs, "solveOneDifferential");
			inputs = calculator.solveVarX(inputs, 0, x_nol);
			//myLogJ(inputs, "solveVarX");
			inputs = calculator.solveSupscript(inputs, 0);
//			/myLogJ(inputs, "solveSupscript");
			inputs = calculator.solveMath(inputs, 0);
			//myLogJ(inputs, "solveMath");
			fx_nol_aksen = new Double(inputs.get(0).getSpannableString().toString());
			newtonRhapson.setFx_nol_aksen(new Double(numberFormat.format(fx_nol_aksen)));
			Double temp = x_nol ;
			x_nol = temp - (fx_nol/fx_nol_aksen) ;
			
			result.add(newtonRhapson);
			if (newtonRhapson.getFx_nol()==0.0 || newtonRhapson.getFx_nol_aksen()==0.0) {
				break;
			}
			
			
			/*if ((fx_nol_aksen>0.0 && fx_nol_aksen<tolnPositif)||(fx_nol_aksen<0.0 && fx_nol_aksen >- tolnNegatif)) {
				loop = false;
				break;
			}else if (fx_nol_aksen.isNaN()) {
				loop = false;
				break;
			}else if (i==200) {
				loop = false;
				Toast.makeText(this, "Iterasi berpotensi melebihi batas penggunaan memory, di hentikan pada perhitungan ke "+String.valueOf(i), Toast.LENGTH_LONG).show();
				break;
				
			}*/
			i++;
			
		}
		
		return result;
	}
	private void myLogJ(List<Input> inputs, String tag){
		String temp = "";
		for (int i = 0; i < inputs.size(); i++) {
			temp += inputs.get(i).getSpannableString().toString();
		}
		Log.i(tag, temp);
	}
}
