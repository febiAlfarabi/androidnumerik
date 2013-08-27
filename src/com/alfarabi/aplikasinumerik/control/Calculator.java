package com.alfarabi.aplikasinumerik.control;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.text.SpannableString;
import android.util.Log;
import android.widget.Toast;

import com.alfarabi.aplikasinumerik.model.Input;
import com.alfarabi.aplikasinumerik.model.NewtonRhapson;

public class Calculator {

	
	public List<Input> inputStrings;
	Double x_n_minus_one = 0.0, x_n = 0.0, fx_n_minus_one = 0.0,fx_n = 0.0 , x_n_plus_one = 0.0;
	Double iterasionFXAt = 0.0 ;
	NumberFormat numberFormat ;
	Context context ;
	
	public Calculator(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}
	
	public List<Input> solveOneDifferential(List<Input> inputs, int counter)throws Exception{

		int sup = 0;
		for (int i = 0; i < inputs.size(); i++) {
			if (i+1<=inputs.size()-1) {
				if (i+1==inputs.size()-1) {
					if (inputs.get(i).getType().equals(Input.TYPE_NUMBER)&&inputs.get(i+1).getType().equals(Input.TYPE_X)) {
						inputs.remove(i+1);
					}else if (inputs.get(i).getType().equals(Input.TYPE_NUMBER)&&inputs.get(i+1).getType().equals(Input.TYPE_MATH)) {
						inputs.remove(i);
						inputs.remove(i+1);
					}
				}else if (i+2<=inputs.size()-1) {
					if (inputs.get(i).getType().equals(Input.TYPE_NUMBER)&&inputs.get(i+1).getType().equals(Input.TYPE_X)&&!inputs.get(i+2).getType().equals(Input.TYPE_SUP)) {
						inputs.remove(i+1);
						
					}else if (inputs.get(i).getType().equals(Input.TYPE_NUMBER)&&inputs.get(i+1).getType().equals(Input.TYPE_MATH)) {
						inputs.remove(i);
						inputs.remove(i+1);
					}
				}
			}

			if (inputs.get(i).getType().equals(Input.TYPE_SUP)) {
				sup++;
			}else if ((inputs.get(i).getType().equals(Input.TYPE_X)) && i+1<=inputs.size()-1) {
				if (!inputs.get(i+1).getType().equals(Input.TYPE_SUP)) {
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString("1")));
				}

			}else if ((inputs.get(i).getType().equals(Input.TYPE_NUMBER))) {
				if (i==inputs.size()-1){
					if (inputs.get(i).getType().equals(Input.TYPE_NUMBER)) {
						inputs.remove(i);
						if (inputs.get(i-1).getType().equals(Input.TYPE_MATH)) {
							inputs.remove(i-1);	
						}
					}
				}
			}else if ((inputs.get(i).getType().equals(Input.TYPE_X))&& i+2<inputs.size()) {

			}
		}
		for (int i = 0; i < inputs.size(); i++) {
			if ((inputs.get(i).getType().equals(Input.TYPE_X)) && i+1<=inputs.size()-1) {
				if (!inputs.get(i+1).getType().equals(Input.TYPE_SUP)) {
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString("1")));
				}

			}
		}
		
		int i = 0;
		while (sup!=0) {
			if (sup>0) {
				if ((inputs.get(i).getType().equals(Input.TYPE_SUP))) {
					Double pangkat = new Double(inputs.get(i).getSpannableString().toString());
					Double pangkatTerkurangi = pangkat - 1 ;
					if (inputs.get(i-1).getType().equals(Input.TYPE_NUMBER)) {
						inputs.set(i, new Input(Input.TYPE_SUP, new SpannableString(String.valueOf(pangkatTerkurangi))));
						inputs.add(i-1, new Input(Input.TYPE_MATH, new SpannableString(String.valueOf("*"))));
						inputs.add(i-1, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(pangkat))));
						i=i+2;
						sup--;
					}else if(inputs.get(i).getType().equals(Input.TYPE_SUP) && inputs.get(i-1).getType().equals(Input.TYPE_X)){
						if (inputs.get(i-1).getType().equals(Input.TYPE_NUMBER)) {
							inputs.set(i, new Input(Input.TYPE_SUP, new SpannableString(String.valueOf(pangkatTerkurangi))));
							inputs.add(i-1, new Input(Input.TYPE_MATH, new SpannableString(String.valueOf("*"))));
							inputs.add(i-1, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(pangkat))));	
							
						}else if (inputs.get(i-1).getType().equals(Input.TYPE_X)&& i-2==-1){
							inputs.set(i, new Input(Input.TYPE_SUP, new SpannableString(String.valueOf(pangkatTerkurangi))));
							inputs.add(i-1, new Input(Input.TYPE_MATH, new SpannableString(String.valueOf("*"))));
							inputs.add(i-1, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(pangkat))));	
						}else if (inputs.get(i-1).getType().equals(Input.TYPE_X)&&inputs.get(i-2).getType().equals(Input.TYPE_NUMBER)){
							inputs.set(i, new Input(Input.TYPE_SUP, new SpannableString(String.valueOf(pangkatTerkurangi))));
							inputs.add(i-2, new Input(Input.TYPE_MATH, new SpannableString(String.valueOf("*"))));
							inputs.add(i-2, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(pangkat))));	
						}else{
							inputs.set(i, new Input(Input.TYPE_SUP, new SpannableString(String.valueOf(pangkatTerkurangi))));
							inputs.add(i-1, new Input(Input.TYPE_MATH, new SpannableString(String.valueOf("*"))));
							inputs.add(i-1, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(pangkat))));	
						}
						i=i+2;
						sup--;
					}
						
				}else{
					//Toast.makeText(context, "Masih Error", Toast.LENGTH_SHORT).show();
				}
			}else{
				break;
			}
			i++;
		}
		return inputs;
	}
	
	public List<Input> solveMath(List<Input> inputs, int counter)throws Exception{	
		String temp = ""; 
		int perkalian = 0 , penjumlahan = 0, pengurangan = 0, pembagian = 0;

		
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i).getSpannableString().toString().equals("*")) {
				perkalian++;
			}else if (inputs.get(i).getSpannableString().toString().equals("+")) {
				penjumlahan++;
			}else if (inputs.get(i).getSpannableString().toString().equals("-")) {
				pengurangan++;
			}else if (inputs.get(i).getSpannableString().toString().equals("/")) {
				pembagian++;
			}
		}
		int i = 0;
		
		i = 0 ;
		while (perkalian!=0) {
			
			if (perkalian>0) {
				if (inputs.get(i).getType().equals(Input.TYPE_MATH) && inputs.get(i).getSpannableString().toString().equals("*")) {
					String pertama = inputs.get(i-1).getSpannableString().toString();
					String kedua = inputs.get(i+1).getSpannableString().toString();
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(new Double(pertama)*new Double(kedua)))));
					inputs.remove(i+1);
					inputs.remove(i-1);
					i = i-2 ;
					perkalian--;
				}
			}
			
			i++;
		}
		
		i = 0 ;
		while (pembagian!=0) {
			
			if (pembagian>0) {
				if (inputs.get(i).getType().equals(Input.TYPE_MATH) && inputs.get(i).getSpannableString().toString().equals("/")) {
					String pertama = inputs.get(i-1).getSpannableString().toString();
					String kedua = inputs.get(i+1).getSpannableString().toString();
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(new Double(pertama)/new Double(kedua)))));
					inputs.remove(i+1);
					inputs.remove(i-1);
					i = i-2 ;
					pembagian--;
				}
			}
			i++;
		}
		
		i = 0 ;
		while (pengurangan!=0) {
			
			if (pengurangan>0) {
				if (inputs.get(i).getType().equals(Input.TYPE_MATH) && inputs.get(i).getSpannableString().toString().equals("-")) {
					String pertama = inputs.get(i-1).getSpannableString().toString();
					String kedua = inputs.get(i+1).getSpannableString().toString();
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(new Double(pertama) - new Double(kedua)))));
					inputs.remove(i+1);
					inputs.remove(i-1);
					i = i-2 ;
					pengurangan--;
				}
			}
			i++;
		}
		
		i = 0 ;
		while (penjumlahan!=0) {
			
			if (penjumlahan>0) {
				if (inputs.get(i).getType().equals(Input.TYPE_MATH) && inputs.get(i).getSpannableString().toString().equals("+")) {
					String pertama = inputs.get(i-1).getSpannableString().toString();
					String kedua = inputs.get(i+1).getSpannableString().toString();
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(new Double(pertama) + new Double(kedua)))));
					inputs.remove(i+1);
					inputs.remove(i-1);
					i = i-2 ;
					penjumlahan--;
				}
			}
			i++;
		}
		
		return inputs;
		
	}
	
	public List<Input> solveVarX(List<Input> inputs, int counter, Double x_n)throws Exception{
		int x = 0;
		
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i).getType().equals(Input.TYPE_X)) {
				x++;
			}
		}
		
		int i = 0;
		while (x!=0) {
			if (x>0) {
				if (inputs.get(i).getType().equals(Input.TYPE_X) && i!=0) {
					if (inputs.get(i-1).getType().equals(Input.TYPE_NUMBER)) {
						inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(x_n))));
						inputs.add(i, new Input(Input.TYPE_MATH, new SpannableString(String.valueOf("*"))));
						i = i+1 ;
						x--;
					}else{
						inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(x_n))));
						x--;	
					}
						
				}else if (inputs.get(i).getType().equals(Input.TYPE_X) && i==0) {
					inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(x_n))));
					x--;	
				}
			}else{
				break;
			}
			i++;
		}
		return inputs;
	}

	public List<Input> solveSupscript(List<Input> inputs, int counter)throws Exception{
		int superscript = 0 , subscript = 0 ;
		for (int i = 0; i < inputs.size(); i++) {
			if (inputs.get(i).getType().equals(Input.TYPE_SUP)) {
				superscript++;
			}else if (inputs.get(i).getType().equals(Input.TYPE_SUB)) {
				subscript++;
			}
		}
		
		int i = 0;
		while (superscript!=0) {
			if (superscript>0) {
				if (inputs.get(i).getType().equals(Input.TYPE_SUP) && i!=0) {
					if (inputs.get(i-1).getType().equals(Input.TYPE_NUMBER)) {
						String pertama = inputs.get(i-1).getSpannableString().toString();
						String kedua = inputs.get(i).getSpannableString().toString();
						Double temp = Math.pow(new Double(pertama), new Double(kedua));//new Double(pertama)*new Double(x_n);
						inputs.set(i, new Input(Input.TYPE_NUMBER, new SpannableString(String.valueOf(temp))));
						inputs.remove(i-1);
						i = i-1 ;
						superscript--;	
					}
				}
			}
			i++;
		}
		return inputs;
	}
	
	public Double getValueOfVAr(List<Input> varX)throws Exception{
		String normal = "";
		String sup = "";
		for (int i = 0; i < varX.size(); i++) {
			if (varX.get(i).getType().equals(Input.TYPE_SUP)) {
				sup += varX.get(i).getSpannableString();
			}else{
				normal += varX.get(i).getSpannableString();
			}
		}
		if (!sup.equals("")) {
			Double tempD = Math.pow(new Double(normal), new Double(sup));
			return tempD;
		}else{
			if(normal!="")return new Double(normal); else return new Double(0.0) ;	
		}	
	}
	

	public List<Input> groupingChar(List<Input> textResult, List<Input> textInput, int start){
		
		String number = "";
		String math = "";
		String group = "";
		String x = "";
		String sup = "";
		String sub = "";
		for (int i = start ; i < textInput.size(); i++) {
			
			if (textInput.get(i).getType().equals(Input.TYPE_MATH)) {
				
				math += textInput.get(i).getSpannableString().toString();
				if (i<textInput.size()-1) {
					if (!textInput.get(i+1).getType().equals(Input.TYPE_MATH)){
						textResult.add(new Input(Input.TYPE_MATH, new SpannableString(math)));
						groupingChar(textResult, textInput, i+1);
						break;
					}
				}else if (i==textInput.size()-1){
					textResult.add(new Input(Input.TYPE_MATH, new SpannableString(math)));
				}
				
			}else if (textInput.get(i).getType().equals(Input.TYPE_KURUNG_BUKA)) {
				
			}else if (textInput.get(i).getType().equals(Input.TYPE_NEGATIVE)) {
			
			}else if (textInput.get(i).getType().equals(Input.TYPE_SUP)) {
				
				sup += textInput.get(i).getSpannableString().toString();
				if (i<textInput.size()-1) {
					if (!textInput.get(i+1).getType().equals(Input.TYPE_SUP)){
						textResult.add(new Input(Input.TYPE_SUP, new SpannableString(sup)));
						groupingChar(textResult, textInput, i+1);
						break;
					}
				}else if (i==textInput.size()-1){
					textResult.add(new Input(Input.TYPE_SUP, new SpannableString(sup)));
				}
			
			}else if (textInput.get(i).getType().equals(Input.TYPE_SUB)) {
				
				sub += textInput.get(i).getSpannableString().toString();
				if (i<textInput.size()-1) {
					if (!textInput.get(i+1).getType().equals(Input.TYPE_SUB)){
						textResult.add(new Input(Input.TYPE_SUP, new SpannableString(sub)));
						groupingChar(textResult, textInput, i+1);
						break;
					}
				}else if (i==textInput.size()-1){
					textResult.add(new Input(Input.TYPE_SUB, new SpannableString(sub)));
				}
				
			}else if (textInput.get(i).getType().equals(Input.TYPE_NUMBER) || textInput.get(i).getType().equals(Input.TYPE_DOT)) {
			
				number += textInput.get(i).getSpannableString().toString();
				if (i<textInput.size()-1) {
					if (!textInput.get(i+1).getType().equals(Input.TYPE_NUMBER) && !textInput.get(i+1).getType().equals(Input.TYPE_DOT)){
						textResult.add(new Input(Input.TYPE_NUMBER, new SpannableString(number)));
						groupingChar(textResult, textInput, i+1);
						break;
					}
				
				}else if (i==textInput.size()-1){
					textResult.add(new Input(Input.TYPE_NUMBER, new SpannableString(number)));
				}
			
			}else if (textInput.get(i).getType().equals(Input.TYPE_X)) {
			
				x += textInput.get(i).getSpannableString().toString();
				
				if (i<textInput.size()-1) {
					textResult.add(new Input(Input.TYPE_X, new SpannableString(x)));
					groupingChar(textResult, textInput, i+1);
					break ;
				}else if (i==textInput.size()-1){
					textResult.add(new Input(Input.TYPE_X, new SpannableString(x)));
				}
			}else{
				break ;
			}
		}
		return textResult ;
	}
	
	public Boolean validationInput(String input){
		String last = String.valueOf(input.charAt(input.length()-1));
		String first = String.valueOf(input.charAt(0));
		
		if (first.equals("*")||first.equals("/")||first.equals("+")) {
			return false;
		}else if (last.equals("*")||last.equals("/")||last.equals("+")||last.equals("-")) {
			return false;
		}else{
			return true;
		}
	}
	
	public Boolean isNumber(String text){
		try {
			Integer.parseInt(text);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
