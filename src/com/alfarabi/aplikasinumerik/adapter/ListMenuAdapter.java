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
import com.alfarabi.aplikasinumerik.tools.Constant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListMenuAdapter extends ArrayAdapter<String>{

	String tagAdapter ;
	Context context ;
	String[] menus ;
	
	public ListMenuAdapter(Context context, int resource, String[] objects) {
		super(context, resource, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context ;
		this.menus = objects;
	}
	
	@Override
	public int getCount() {
		return menus.length;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater lif = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (menus[position].equals(Constant.METODE_NEWTON_RAPSON)||menus[position].equals(Constant.METODE_SECANT)){
			convertView = lif.inflate(com.alfarabi.aplikasinumerik.R.layout.list_menu_layout, null);
		}else{
			convertView = lif.inflate(com.alfarabi.aplikasinumerik.R.layout.disable_list_menu_layout, null);
				
		}
		TextView menuNameTV = (TextView) convertView.findViewById(com.alfarabi.aplikasinumerik.R.id.menuNameTV);
		menuNameTV.setText(menus[position]);
		return convertView;
	}

}
