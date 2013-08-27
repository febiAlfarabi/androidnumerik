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

package com.alfarabi.aplikasinumerik.activity.secant;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.control.Calculator;
import com.alfarabi.aplikasinumerik.extend.GlobalActivity;
import com.alfarabi.aplikasinumerik.model.Input;
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
import android.widget.Toast;

import android.widget.RelativeLayout;

public class SecantActivity extends GlobalActivity implements OnClickListener, OnFocusChangeListener{

	EditText questionET, x0ET, x1ET, tolnET; 
	MyKeyboard fieldQuestion, fieldX0, fieldX1, fieldToln ;
	Button hitungButton ;
	LinearLayout keyboardLinLayout ;
	List<Input> textResult ;
	Intent intent ;
	Double x_n_minus_one = 0.0, x_n = 0.0, fx_n_minus_one = 0.0,fx_n = 0.0 , x_n_plus_one = 0.0;
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
		fieldX1 = new MyKeyboard();
		fieldToln = new MyKeyboard();
		calculator = new Calculator(this);
	}

	@Override
	public void initLocalUI() {
		super.initLocalUI();
		activityLayout.addView(getLocalLayout());
		questionET = (EditText) findViewById(R.id.questionET);
		x0ET = (EditText) findViewById(R.id.x0ET);
		x1ET = (EditText) findViewById(R.id.x1ET);
		tolnET = (EditText) findViewById(R.id.tolnET);
		keyboardLinLayout = (LinearLayout) findViewById(R.id.keyboardLinLayout);
		groupKeyboardLayout.setVisibility(LinearLayout.INVISIBLE);
		hitungButton = (Button) findViewById(R.id.hitungButton);
	}

	@Override
	public void useDynamicDataStarting() {
		super.useDynamicDataStarting();
		titleTV.setText("Metode Secant");
		questionET.setInputType(InputType.TYPE_NULL);
		x0ET.setInputType(InputType.TYPE_NULL);
		x1ET.setInputType(InputType.TYPE_NULL);
		tolnET.setInputType(InputType.TYPE_NULL);
		fieldToln.initializeLayout(keyboardLinLayout, tolnET);
		fieldToln.writingText(Input.TYPE_NUMBER, new SpannableString("10"));
	}

	@Override
	public void setListener() {
		super.setListener();
		questionET.setOnFocusChangeListener(this);
		x0ET.setOnFocusChangeListener(this);
		x1ET.setOnFocusChangeListener(this);
		tolnET.setOnFocusChangeListener(this);
		questionET.setOnClickListener(this);
		x0ET.setOnClickListener(this);
		x1ET.setOnClickListener(this);
		tolnET.setOnClickListener(this);
		hitungButton.setOnClickListener(this);
	}

	@Override
	public View getLocalLayout() {
		RelativeLayout localLayout = (RelativeLayout) getLayoutInflater().inflate(com.alfarabi.aplikasinumerik.R.layout.secant_activity, null, false);
		return localLayout;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		
		if (v.getId()==R.id.questionET ||v.getId()==R.id.x0ET || v.getId()==R.id.x1ET || v.getId()==R.id.tolnET) {
			
			if (groupKeyboardLayout.getVisibility()==LinearLayout.INVISIBLE) {
				super.openKeyBoard();
			}
			
		}else if (v.getId()==R.id.hitungButton) {
			
			if (questionET.getText().toString().equals("") || x0ET.getText().toString().equals("") 
					|| x1ET.getText().toString().equals("") || tolnET.getText().toString().equals("")) {
				Toast.makeText(this, "Input yang anda masukan belum lengkap", Toast.LENGTH_LONG).show();
			
			}else if (!calculator.validationInput(questionET.getText().toString())||!calculator.validationInput(x0ET.getText().toString())
					|| !calculator.validationInput(x1ET.getText().toString()) || !calculator.validationInput(tolnET.getText().toString())) {
				Toast.makeText(this, "Input yang anda masukan mengandung kesalahan", Toast.LENGTH_LONG).show();
			
			}else if (!fieldToln.getTexts().get(fieldToln.getTexts().size()-1).getType().equals(Input.TYPE_SUP)
					|| !calculator.isNumber(String.valueOf(fieldToln.getTexts().get(fieldToln.getTexts().size()-1).getSpannableString()))) {
				Toast.makeText(this, "Input yang anda masukan mengandung kesalahan", Toast.LENGTH_LONG).show();
			
			}else{
				try{
					textResult = new ArrayList<Input>();
					List<Input> textQuestions = new ArrayList<Input>();
					textQuestions = calculator.groupingChar(textResult, fieldQuestion.getTexts(), 0);
				
					textResult = new ArrayList<Input>();
					List<Input> textX0 = new ArrayList<Input>(); 
					textX0.addAll(calculator.groupingChar(textResult, fieldX0.getTexts(), 0));
				
					textResult = new ArrayList<Input>();
					List<Input> textX1 = new ArrayList<Input>();
					textX1.addAll(calculator.groupingChar(textResult,fieldX1.getTexts(), 0));
				
					textResult = new ArrayList<Input>();
					List<Input> textToln = new ArrayList<Input>();
					textToln.addAll(calculator.groupingChar(textResult, fieldToln.getTexts(), 0));
				
					List<Secant> resultList = new ArrayList<Secant>(); 
					resultList.addAll(getTableResult(textQuestions, textX0, textX1, textToln));
					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList(Constant.BUNDLE_RESULT_SECANT, (ArrayList<? extends Parcelable>) resultList);
					intent = new Intent(SecantActivity.this, ResultSecantActivity.class);
					intent.putExtras(bundle);
					startActivityForResult(intent, Constant.CLOSE_APPLICATION);
				}catch (Exception e) {
					// TODO: handle exception
					Toast.makeText(this, "Kemungkinan input yang anda masukan mengandung kesalahan : Kode kesalahan - 8", Toast.LENGTH_SHORT);
				}
			}	
		}
	}


	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		
		questionET.setBackgroundResource(R.drawable.normal_editext);
		x0ET.setBackgroundResource(R.drawable.normal_editext);
		x1ET.setBackgroundResource(R.drawable.normal_editext);
		tolnET.setBackgroundResource(R.drawable.normal_editext);
		
		if (v.getId()==R.id.questionET) {
			questionET.setBackgroundResource(R.drawable.selected_editext);
			fieldQuestion.initializeLayout(keyboardLinLayout, questionET);
		}else if (v.getId()==R.id.x0ET){
			x0ET.setBackgroundResource(R.drawable.selected_editext);
			fieldX0.initializeLayout(keyboardLinLayout, x0ET);
		}else if (v.getId()==R.id.x1ET){
			x1ET.setBackgroundResource(R.drawable.selected_editext);
			fieldX1.initializeLayout(keyboardLinLayout, x1ET);
		}else if (v.getId()==R.id.tolnET){
			tolnET.setBackgroundResource(R.drawable.selected_editext);
			fieldToln.initializeLayout(keyboardLinLayout, tolnET);
		}
		
		if (groupKeyboardLayout.getVisibility()==LinearLayout.INVISIBLE) {
			super.openKeyBoard();
		}
	}
	
	
	
	//http://aimprof08.wordpress.com/2012/09/01/metode-secant-secant-method/
	private List<Secant> getTableResult(List<Input> textQuestion, List<Input> textX0, List<Input> textX1, List<Input> textToln)throws Exception{
		x_n_minus_one = calculator.getValueOfVAr(textX0);
		x_n = calculator.getValueOfVAr(textX1);
		Double tolnPositif = calculator.getValueOfVAr(textToln);
		Double tolnNegatif = 0.0-tolnPositif;
		String decimalFormat = "#.";
		
		for (int i = 0; i < String.valueOf(tolnPositif).length()-1; i++) {
			decimalFormat = decimalFormat+"#";
		}
		numberFormat = new DecimalFormat(decimalFormat);
		
		/*
		 * 1 = ganti nilai x menjadi nilai input x_n_minus_one || x_n
		 * 2 = dahulukan menghitung akar pangkat
		 * 3 = hitung perkalian
		 * 4 = Hitung Pembagian
		 */
		//List<Input> inputs ;
		int batasanToln = 0 ;
		List<Secant> result = new ArrayList<Secant>();
		int i = 0;
		boolean loop = true; 
		while(loop) {
			List<Input> inputs = new ArrayList<Input>();
			inputs.addAll(textQuestion);
			
			Secant model = new Secant(batasanToln);
			model.setX_n_minus_one(new Double(numberFormat.format(x_n_minus_one)));
			model.setX_n(new Double(numberFormat.format(x_n))); //myLogJ(inputs, "Before - - - - SolveVar x");
			
			inputs = calculator.solveVarX(inputs, 0, x_n_minus_one); //myLogJ(inputs, "SolveVar x");
			inputs = calculator.solveSupscript(inputs, 0); //myLogJ(inputs, "SolveSup Script");
			inputs = calculator.solveMath(inputs, 0); //myLogJ(inputs, "SolveVar Math");
			fx_n_minus_one = new Double(String.valueOf(inputs.get(0).getSpannableString().toString()));
			model.setFungsi_x_n_minus_one(new Double(numberFormat.format(fx_n_minus_one)));
			
			inputs = new ArrayList<Input>();
			inputs.addAll(textQuestion);
			inputs = calculator.solveVarX(inputs, 0, x_n);
			inputs = calculator.solveSupscript(inputs, 0);
			inputs = calculator.solveMath(inputs, 0);
			fx_n = new Double(String.valueOf(inputs.get(0).getSpannableString().toString()));
			model.setFungsi_x_n(new Double(numberFormat.format(fx_n)));
			
			x_n_plus_one = x_n -((fx_n*(x_n - x_n_minus_one))/(fx_n-fx_n_minus_one)); 
			x_n_minus_one = x_n;
			x_n = x_n_plus_one ;
			
			result.add(model);
			if ((fx_n>0.0 && fx_n<tolnPositif)||(fx_n<0.0 && fx_n >- tolnNegatif)) {
				loop = false;
				break;
			}else if (x_n_plus_one.isNaN()) {
				loop = false;
				break;
			}else if (i==200) {
				loop = false;
				Toast.makeText(this, "Iterasi berpotensi melebihi batas penggunaan memory, di hentikan pada perhitungan ke "+String.valueOf(i), Toast.LENGTH_LONG).show();
				break;
				
			}
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
