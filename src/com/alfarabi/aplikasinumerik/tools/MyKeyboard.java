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

package com.alfarabi.aplikasinumerik.tools;

import java.util.ArrayList;
import java.util.List;
import com.alfarabi.aplikasinumerik.R;
import com.alfarabi.aplikasinumerik.model.Input;

import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyKeyboard implements OnFocusChangeListener, OnClickListener{

	public static final String TYPE_NORMAL = "TYPE_NORMAL";
	public static final String TYPE_SUP = "TYPE_SUP";
	public static final String TYPE_SUB = "TYPE_SUB";
	
	LinearLayout keyboardLayout ;
	EditText textView ;
	String text ;
	public static String inputType ;
	
	static Button keySupn, keySubn, keyNormal;
	
	Button key1, key2, key3 , keyX, keyKurungBuka, keyKurungTutup, key4, key5, key6
	, keyKali, keyPositif, keyDot , key7 , key8, key9 , keyNegatif, keyPembagi, keyPlusMinus, keyBackspace , key0;
	List<Input> texts ;
	boolean plusMinus ;
	
	public MyKeyboard() {
		// TODO Auto-generated constructor stub
		this.texts = new ArrayList<Input>();
        
	}
	
	public List<Input> getTexts() {
		return texts;
	}
	
	public void initializeLayout(LinearLayout keyboardLayout, EditText textView){
		this.plusMinus = true ;
		this.keyboardLayout = keyboardLayout ;
		this.textView = textView ;
		initializeKeyboard();
		initClickListener();
	}
	
	private void initializeKeyboard(){
		
		keySupn = (Button) keyboardLayout.findViewById(R.id.supnBtn);
		keySubn = (Button) keyboardLayout.findViewById(R.id.subnBtn);
		keyNormal = (Button) keyboardLayout.findViewById(R.id.normalBtn);
		key1 = (Button) keyboardLayout.findViewById(R.id.key_1);
		key2 = (Button) keyboardLayout.findViewById(R.id.key_2);
		key3 = (Button) keyboardLayout.findViewById(R.id.key_3);
		
		keyX = (Button) keyboardLayout.findViewById(R.id.x_btn);
		keyKurungBuka = (Button) keyboardLayout.findViewById(R.id.kurungbuka_btn);
		keyKurungTutup = (Button) keyboardLayout.findViewById(R.id.kurungtutup_btn);
		key4 = (Button) keyboardLayout.findViewById(R.id.key_4);
		key5 = (Button) keyboardLayout.findViewById(R.id.key_5);
		key6 = (Button) keyboardLayout.findViewById(R.id.key_6);
		
		keyKali = (Button) keyboardLayout.findViewById(R.id.kali_btn);
		keyPositif = (Button) keyboardLayout.findViewById(R.id.positif_btn);
		keyDot = (Button) keyboardLayout.findViewById(R.id.dot_btn);
		key7 = (Button) keyboardLayout.findViewById(R.id.key_7);
		key8 = (Button) keyboardLayout.findViewById(R.id.key_8);
		key9 = (Button) keyboardLayout.findViewById(R.id.key_9);
		
		keyNegatif = (Button) keyboardLayout.findViewById(R.id.negative_btn);
		keyPembagi = (Button) keyboardLayout.findViewById(R.id.pembagi_btn);
		keyPlusMinus = (Button) keyboardLayout.findViewById(R.id.plus_minus_btn);
		keyBackspace = (Button) keyboardLayout.findViewById(R.id.backspace_btn);
		key0 = (Button) keyboardLayout.findViewById(R.id.key_0);
		
		if (inputType==null) {
			focusKeyType(TYPE_NORMAL);
		}
		
	}
	
	private void initClickListener(){
		
		keySupn.setOnClickListener(this);
		//keySubn.setOnClickListener(this);
		keyNormal.setOnClickListener(this);
		key1.setOnClickListener(this);
		key2.setOnClickListener(this);
		key3.setOnClickListener(this);
		
		keyX.setOnClickListener(this);
		//keyKurungBuka.setOnClickListener(this);
		//keyKurungTutup.setOnClickListener(this);
		key4.setOnClickListener(this);
		key5.setOnClickListener(this);
		key6.setOnClickListener(this);
		
		keyKali.setOnClickListener(this);
		keyPositif.setOnClickListener(this);
		keyDot.setOnClickListener(this);
		key7.setOnClickListener(this);
		key8.setOnClickListener(this);
		key9.setOnClickListener(this);
		
		keyNegatif.setOnClickListener(this);
		keyPembagi.setOnClickListener(this);
		keyPlusMinus.setOnClickListener(this);
		keyBackspace.setOnClickListener(this);
		key0.setOnClickListener(this);
		
		keySubn.setClickable(false);
		
		
		
//		if (this.textView.getId()==com.alfarabi.aplikasinumerik.R.id.tolnET) {
//			keyNormal.setClickable(false);
//			focusKeyType(TYPE_SUP);
//		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (((TextView)v).getText().toString().equals("")) {
			((TextView)v).focusSearch(TextView.FOCUS_LEFT);
		}else{
			((TextView)v).focusSearch(TextView.FOCUS_RIGHT);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.supnBtn:
			focusKeyType(TYPE_SUP);
			break;
		case R.id.subnBtn:
			
			//focusKeyType(TYPE_SUB);
//			if (texts.size()>=1){
//				c = texts.get(texts.size()-1).getSpannableString();
//				backSpace();
//				c.setSpan(new RelativeSizeSpan(0.6f), 0, 1, 0);
//				c.setSpan(new SubscriptSpan(), 0, 1, 0);
//				writingText(Input.TYPE_SUB, c);
//			}
			
		break;
		case R.id.normalBtn:
			focusKeyType(TYPE_NORMAL);
			break;
		case R.id.key_1:
			writingText(Input.TYPE_NUMBER, new SpannableString("1"));
			break;
		case R.id.key_2:
			writingText(Input.TYPE_NUMBER, new SpannableString("2"));
			break;
		case R.id.key_3:
			writingText(Input.TYPE_NUMBER, new SpannableString("3"));
			break;
		
		///////////////////////////////////////////////////////////////////////////////////////
		
		case R.id.x_btn:
			writingText(Input.TYPE_X, new SpannableString("x"));
			break;
		case R.id.kurungbuka_btn:
			//writingText(Input.TYPE_KURUNG_BUKA, new SpannableString("("));
			break;
		case R.id.kurungtutup_btn:
			//writingText(Input.TYPE_KURUNG_TUTUP, new SpannableString(")"));
			break;
		case R.id.key_4:
			writingText(Input.TYPE_NUMBER, new SpannableString("4"));
			break;
		case R.id.key_5:
			writingText(Input.TYPE_NUMBER, new SpannableString("5"));
			break;
		case R.id.key_6:
			writingText(Input.TYPE_NUMBER, new SpannableString("6"));
			break;
			
		///////////////////////////////////////////////////////////////////////////////////////
		
		case R.id.kali_btn:
			writingText(Input.TYPE_MATH, new SpannableString("*"));
			break;
		case R.id.positif_btn:
			writingText(Input.TYPE_MATH, new SpannableString("+"));
			break;
		case R.id.dot_btn:
			writingText(Input.TYPE_DOT, new SpannableString("."));
			break;
		case R.id.key_7:
			writingText(Input.TYPE_NUMBER, new SpannableString("7"));
			break;
		case R.id.key_8:
			writingText(Input.TYPE_NUMBER, new SpannableString("8"));
			break;
		case R.id.key_9:
			writingText(Input.TYPE_NUMBER, new SpannableString("9"));
			break;
		//////////////////////////////////////////////////////////////////////////////////////
		
		case R.id.negative_btn:
			writingText(Input.TYPE_MATH, new SpannableString("-"));
			break;
		case R.id.pembagi_btn:
			writingText(Input.TYPE_MATH, new SpannableString("/"));
			break;
		case R.id.plus_minus_btn:
			//writingText("1");
			break;
		case R.id.backspace_btn:
			
			if (textView.getId()==R.id.tolnET && textView.getText().equals("10")) {
				
			}else{
				backSpace();
			}
			break;
		case R.id.key_0:
			writingText(Input.TYPE_NUMBER, new SpannableString("0"));
			break;
		/////////////////////////////////////////////////////////////////////////////////////	
			
		default:
			break;
		}
	}
	
	public static void focusKeyType(String type){
		if (keyNormal!=null&&keySupn!=null) {
			inputType = type ;
			keyNormal.setBackgroundResource(com.alfarabi.aplikasinumerik.R.drawable.keyboard_button_selector);
			keySupn.setBackgroundResource(com.alfarabi.aplikasinumerik.R.drawable.keyboard_button_selector);
			//keySubn.setBackgroundResource(com.alfarabi.aplikasinumerik.R.drawable.keyboard_button_selector);
			if (inputType.equals(TYPE_NORMAL)) {
				keyNormal.setBackgroundResource(com.alfarabi.aplikasinumerik.R.drawable.bg_blue_keypad);
				keyNormal.setTextColor(Color.RED);
				keySupn.setTextColor(Color.WHITE);
			}else if (inputType.equals(TYPE_SUP)) {
				keySupn.setBackgroundResource(com.alfarabi.aplikasinumerik.R.drawable.bg_blue_keypad);
				keySupn.setTextColor(Color.RED);
				keyNormal.setTextColor(Color.WHITE);
			}
		}
		
		
//		else if (inputType.equals(TYPE_SUB)) {
//			keySubn.setBackgroundResource(com.alfarabi.aplikasinumerik.R.drawable.bg_blue_keypad);	
//		}
	}
	
	public void writingText(String inputValue, SpannableString spannableString){

		if (inputType.equals(TYPE_NORMAL)) {
			this.texts.add(new Input(inputValue, spannableString));
			textView.setText("");
			for (int i = 0; i < texts.size(); i++) {
				textView.append(texts.get(i).getSpannableString());
			}
		
		}else if (inputType.equals(TYPE_SUP)) {
			spannableString.setSpan(new RelativeSizeSpan(0.6f), 0, 1, 0);
			spannableString.setSpan(new SuperscriptSpan(), 0, 1, 0);
			this.texts.add(new Input(Input.TYPE_SUP, spannableString));
			textView.setText("");
			
			for (int i = 0; i < texts.size(); i++) {
				textView.append(texts.get(i).getSpannableString());
				
			}
		}
	}
	public void backSpace(){
		if (texts.size()>=1) 
			this.texts.remove(texts.size()-1);
			textView.setText("");
			for (int i = 0; i < texts.size(); i++) {
				textView.append(texts.get(i).getSpannableString());
			}	
	}
	
}
