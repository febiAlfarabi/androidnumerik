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

public class ResultSecantListAdapter extends ArrayAdapter<Secant>{

	String tagAdapter ;
	Context context ;
	List<Secant> secants ;
	
	public ResultSecantListAdapter(Context context, int resource, List<Secant> secants) {
		super(context, resource, resource, secants);
		// TODO Auto-generated constructor stub
		this.context = context ;
		this.secants = secants;
	}
	
	@Override
	public int getCount() {
		return secants.size()+1;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = lif.inflate(com.alfarabi.aplikasinumerik.R.layout.result_secant_list_layout, null);
		if (position<=0) {
			TextView numberTextView = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.numberTV);
			numberTextView.setText("No");
			numberTextView.setBackgroundColor(Color.parseColor("#5F8AB4"));
			numberTextView.setTextColor(Color.WHITE);
			
			TextView x_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.x_n_minus_one_tv);
			x_n_minus_oneTV.setText(Html.fromHtml("X<sub>n-1</sub>"));
			x_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			x_n_minus_oneTV.setTextColor(Color.WHITE);
			
			TextView x_n__oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.x_n_tv);
			x_n__oneTV.setText(Html.fromHtml("X<sub>n</sub>"));
			x_n__oneTV.setBackgroundColor(Color.parseColor("#5F8AB4"));
			x_n__oneTV.setTextColor(Color.WHITE);
			
			TextView fx_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_n_minus_one_tv);
			fx_n_minus_oneTV.setText(Html.fromHtml("Fx<sub>n-1</sub>"));
			fx_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			fx_n_minus_oneTV.setTextColor(Color.WHITE);
			
			TextView fx_nTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_n_tv);
			fx_nTV.setText(Html.fromHtml("Fx<sub>n</sub>"));
			fx_nTV.setBackgroundColor(Color.parseColor("#5F8AB4"));
			fx_nTV.setTextColor(Color.WHITE);
			
		}else{
			position = position-1 ;
			TextView numberTextView = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.numberTV);
			numberTextView.setText(String.valueOf(position));
			numberTextView.setBackgroundColor(Color.WHITE);
			numberTextView.setTextColor(Color.BLACK);
			
			TextView x_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.x_n_minus_one_tv);
			x_n_minus_oneTV.setText(String.valueOf(secants.get(position).getX_n_minus_one()));
			x_n_minus_oneTV.setBackgroundColor(Color.parseColor("#696969"));
			x_n_minus_oneTV.setTextColor(Color.WHITE);
			
			TextView x_n__oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.x_n_tv);
			x_n__oneTV.setText(String.valueOf(secants.get(position).getX_n()));
			x_n__oneTV.setBackgroundColor(Color.WHITE);
			x_n__oneTV.setTextColor(Color.BLACK);
			
			TextView fx_n_minus_oneTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_n_minus_one_tv);
			fx_n_minus_oneTV.setText(String.valueOf(secants.get(position).getFungsi_x_n_minus_one()));
			fx_n_minus_oneTV.setBackgroundColor(Color.parseColor("#0055FF"));
			fx_n_minus_oneTV.setTextColor(Color.WHITE);
			
			TextView fx_nTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.fx_n_tv);
			fx_nTV.setText(String.valueOf(secants.get(position).getFungsi_x_n()));
			fx_nTV.setBackgroundColor(Color.parseColor("#696969"));
			fx_nTV.setTextColor(Color.WHITE);
			
		}
		
		return convertView;
	}

}
