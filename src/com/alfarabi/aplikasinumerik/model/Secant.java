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

import java.text.DecimalFormat;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Secant implements Parcelable{

	private int batasanToln;
	
	private Double x_n_minus_one = 0.0 ;
	private Double x_n = 0.0 ;
	private Double x_n_plus_one = 0.0 ;
	private Double fungsi_x_n_minus_one = 0.0 ;
	private Double fungsi_x_n = 0.0 ;
	private Double fungsi_x_n_plus_one = 0.0 ;
	
	public Double getX_n_minus_one() {
		return x_n_minus_one;
	}
	public Double getX_n() {
		return x_n;
	}
	public Double getX_n_plus_one() {
		return x_n_plus_one;
	}
	public Double getFungsi_x_n_minus_one() {
		return fungsi_x_n_minus_one;
	}
	public Double getFungsi_x_n() {
		return fungsi_x_n;
	}
	public Double getFungsi_x_n_plus_one() {
		return fungsi_x_n_plus_one;
	}
	public void setX_n_minus_one(Double x_n_minus_one) {
		this.x_n_minus_one = x_n_minus_one;
	}
	public void setX_n(Double x_n) {
		this.x_n = x_n;
	}
	public void setX_n_plus_one(Double x_n_plus_one) {
		this.x_n_plus_one = x_n_plus_one;
	}
	public void setFungsi_x_n_minus_one(Double fungsi_x_n_minus_one) {
		this.fungsi_x_n_minus_one = fungsi_x_n_minus_one;
	}
	public void setFungsi_x_n(Double fungsi_x_n) {
		this.fungsi_x_n = fungsi_x_n;
	}
	public void setFungsi_x_n_plus_one(Double fungsi_x_n_plus_one) {
		this.fungsi_x_n_plus_one = fungsi_x_n_plus_one;
	}
	
	
	public Secant(int batasanToln) {
		this.batasanToln = batasanToln;
		// TODO Auto-generated constructor stub
	}
	
	public Secant(Parcel in) {
		// TODO Auto-generated constructor stub
		double[] data = new double[6];
		in.readDoubleArray(data);
		this.x_n_minus_one = data[0];
		this.x_n = data[1];
		this.x_n_plus_one = data[2];
		this.fungsi_x_n_minus_one = data[3];
		this.fungsi_x_n = data[4];
		this.fungsi_x_n_plus_one = data[5];
		
	}
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		arg0.writeDoubleArray(new double[]{
				this.x_n_minus_one,
				this.x_n,
				this.x_n_plus_one,
				this.fungsi_x_n_minus_one,
				this.fungsi_x_n,
				this.fungsi_x_n_plus_one
				
		});
	}
	
	@SuppressWarnings("rawtypes")
	public static final Creator CREATOR  = new Creator() {

		@Override
		public Object createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Secant(source);
		}

		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Secant[size];
		}
	};
}
