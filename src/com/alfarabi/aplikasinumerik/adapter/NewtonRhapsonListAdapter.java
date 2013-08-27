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
package com.alfarabi.aplikasinumerik.adapter;
import java.util.List;

import com.alfarabi.aplikasinumerik.model.NewtonRhapson;
import com.alfarabi.aplikasinumerik.model.Secant;
import com.alfarabi.aplikasinumerik.tools.Constant;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class NewtonRhapsonListAdapter extends ArrayAdapter<NewtonRhapson>{

	String tagAdapter ;
	Context context ;
	List<NewtonRhapson> newtonRhapsons ;
	
	public NewtonRhapsonListAdapter(Context context, int resource, List<NewtonRhapson> newtonRhapsons) {
		super(context, resource, resource, newtonRhapsons);
		// TODO Auto-generated constructor stub
		this.context = context ;
		this.newtonRhapsons = newtonRhapsons;
	}
	
	@Override
	public int getCount() {
		return newtonRhapsons.size()+1;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = lif.inflate(com.alfarabi.aplikasinumerik.R.layout.result_newton_rhapson_list_layout, null);
		if (position<=0) {
			TextView numberTextView = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.numberTV);
			numberTextView.setText("No");
			numberTextView.setBackgroundColor(Color.parseColor("#5F8AB4"));
			numberTextView.setTextColor(Color.WHITE);
			
			TextView x_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.x_nol_tv);
			x_n_minus_oneTV.setText(Html.fromHtml("X<sub>0</sub>"));
			x_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			x_n_minus_oneTV.setTextColor(Color.WHITE);
			
			TextView x_n__oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_nol_tv);
			x_n__oneTV.setText(Html.fromHtml("F<sub>x0</sub>"));
			x_n__oneTV.setBackgroundColor(Color.parseColor("#5F8AB4"));
			x_n__oneTV.setTextColor(Color.WHITE);
			
			TextView fx_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_nol_aksen_tv);
			fx_n_minus_oneTV.setText(Html.fromHtml("F<sup>l</sup><sub>x0</sub>"));
			fx_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			fx_n_minus_oneTV.setTextColor(Color.WHITE);
			
		}else{
			position = position-1 ;
			TextView numberTextView = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.numberTV);
			numberTextView.setText(String.valueOf(position));
			numberTextView.setBackgroundColor(Color.WHITE);
			numberTextView.setTextColor(Color.BLACK);
			
			TextView x_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.x_nol_tv);
			x_n_minus_oneTV.setText(String.valueOf(newtonRhapsons.get(position).getX_nol()));
			x_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			x_n_minus_oneTV.setTextColor(Color.WHITE);
			
			TextView x_n__oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_nol_tv);
			x_n__oneTV.setText(Html.fromHtml(String.valueOf(newtonRhapsons.get(position).getFx_nol())));
			x_n__oneTV.setBackgroundColor(Color.parseColor("#5F8AB4"));
			x_n__oneTV.setTextColor(Color.WHITE);
			
			TextView fx_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_nol_aksen_tv);
			fx_n_minus_oneTV.setText(String.valueOf(newtonRhapsons.get(position).getFx_nol_aksen()));
			fx_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			fx_n_minus_oneTV.setTextColor(Color.WHITE);
			
			
		}
		
		return convertView;
	}

}
