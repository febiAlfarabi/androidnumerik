package com.alfarabi.aplikasinumerik.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewtonRhapson implements Parcelable{
	Double x_nol = 0.0 ; 
	Double fx_nol = 0.0; 
	Double fx_nol_aksen = 0.0 ; 
	
	public NewtonRhapson() {
		// TODO Auto-generated constructor stub
	}
	public Double getX_nol() {
		return x_nol;
	}
	public void setX_nol(Double x_nol) {
		this.x_nol = x_nol;
	}
	public Double getFx_nol() {
		return fx_nol;
	}
	public void setFx_nol(Double fx_nol) {
		this.fx_nol = fx_nol;
	}
	public Double getFx_nol_aksen() {
		return fx_nol_aksen;
	}
	public void setFx_nol_aksen(Double fx_nol_aksen) {
		this.fx_nol_aksen = fx_nol_aksen;
	}
	
	
	public NewtonRhapson(Parcel in) {
		double[] temp = new double[3];
		in.readDoubleArray(temp);
		this.x_nol = temp[0] ; 
		this.fx_nol = temp[1]; 
		this.fx_nol_aksen = temp[2] ; 
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeDoubleArray(new double[]{
				this.x_nol, 
				this.fx_nol, 
				this.fx_nol_aksen 	
		});
	}
	
	@SuppressWarnings("rawtypes")
	public static final Creator CREATOR = new Creator() {

		@Override
		public Object createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new NewtonRhapson(source);
		}

		@Override
		public Object[] newArray(int size) {
			// TODO Auto-generated method stub
			return new NewtonRhapson[size];
		}
	};
	

}
