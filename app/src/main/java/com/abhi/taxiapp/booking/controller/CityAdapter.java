package com.abhi.taxiapp.booking.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.booking.model.CabsResponseData;
import com.abhi.taxiapp.booking.model.CitiesResponseData;

import java.util.ArrayList;

public class CityAdapter extends BaseAdapter {


    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    ArrayList<CitiesResponseData> cityBean = new ArrayList<>();
    ArrayList<CitiesResponseData> masterCityBean = new ArrayList<>();


    public CityAdapter(Activity activity, ArrayList<CitiesResponseData> cityBean) {
        this.activity = activity;
        this.cityBean = cityBean;
        this.masterCityBean.clear();
        this.masterCityBean.addAll(cityBean);

        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        notifyDataSetChanged();

    }


    public int getCount() {
        return cityBean.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public void initSearch(String cs) {
        cityBean.clear();
        for (CitiesResponseData data : masterCityBean) {
            if (data.getLocationName().toUpperCase().contains(cs.toUpperCase())) {
                cityBean.add(data);
            }
        }
        notifyDataSetChanged();
    }

    public void refreshData(ArrayList<CitiesResponseData> _data) {
        notifyDataSetChanged();
        masterCityBean.clear();
        masterCityBean.addAll(_data);
    }

    public CabsResponseData getSelectedCabData() {
        return null;
    }


    public static class ViewHolder {

        public TextView countryName;
        public ImageView flag;

    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.city_item, null);

            holder = new ViewHolder();
            holder.countryName = (TextView) vi.findViewById(R.id.tv_cityName);


            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.countryName.setText(cityBean.get(position).getLocationName());
        return vi;
    }


}