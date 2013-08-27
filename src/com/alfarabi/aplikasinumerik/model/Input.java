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
package com.alfarabi.aplikasinumerik.model;

import android.text.SpannableString;

public class Input {
	
	public static final String TYPE_NUMBER = "TYPE_NUMBER";
	public static final String TYPE_NUMBER_NEGATIVE = "TYPE_NUMBER_NEGATIVE";
	
	public static final String TYPE_MATH = "TYPE_MATH";
	
	public static final String TYPE_X = "TYPE_X";
	public static final String TYPE_KURUNG_BUKA = "TYPE_KURUNG_BUKA";
	public static final String TYPE_KURUNG_TUTUP = "TYPE_KURUNG_TUTUP";
	public static final String TYPE_DOT = "TYPE_DOT";
	
	public static final String TYPE_POSITIF = "TYPE_POSITIF";
	public static final String TYPE_NEGATIVE = "TYPE_NEGATIVE";
	public static final String TYPE_SUP = "TYPE_SUP";
	public static final String TYPE_SUB = "TYPE_SUB";
	
	
	private String type ;
	//private Boolean posNegType ;
	private SpannableString spannableString ;

	public Input(String type, SpannableString spannableString) {
		this.type = type ;
		this.spannableString = spannableString ;
		// TODO Auto-generated constructor stub
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public Boolean getPosNegType() {
//		return posNegType;
//	}
//	public void setPosNegType(Boolean posNegType) {
//		this.posNegType = posNegType;
//	}

	public SpannableString getSpannableString() {
		return spannableString;
	}
	public void setSpannableString(SpannableString spannableString) {
		this.spannableString = spannableString;
	}
}
