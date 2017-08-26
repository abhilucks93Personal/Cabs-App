package com.abhi.taxiapp.booking.controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.booking.model.CabsResponseData;

import java.util.ArrayList;

public class CabsAdapter extends BaseAdapter {


    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    ArrayList<CabsResponseData> cabsBean = new ArrayList<>();


    public CabsAdapter(Activity activity, ArrayList<CabsResponseData> cabsBean) {
        this.activity = activity;
        this.cabsBean = cabsBean;

        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        notifyDataSetChanged();

    }


    public int getCount() {
        return cabsBean.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {

        public TextView tvCategory, tvDescription, tvCapacity, tvLuggagrCapacity, tvBasePrice, tvNote;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.cabs_item, null);

            holder = new ViewHolder();
            holder.tvCategory = (TextView) vi.findViewById(R.id.tv_category);
            holder.tvDescription = (TextView) vi.findViewById(R.id.tv_description);
            holder.tvCapacity = (TextView) vi.findViewById(R.id.tv_capacity);
            holder.tvLuggagrCapacity = (TextView) vi.findViewById(R.id.tv_luggageCapacity);
            holder.tvBasePrice = (TextView) vi.findViewById(R.id.tv_basePrice);
            holder.tvNote = (TextView) vi.findViewById(R.id.tv_note);


            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

      /*  holder.tvCategory.setText(cabsBean.get(position).getCarCompany());
        holder.tvDescription.setText("(" + cabsBean.get(position).getCarModel() + ")");
        holder.tvCapacity.setText(cabsBean.get(position).getSeatingCapacity());
        holder.tvLuggagrCapacity.setText(cabsBean.get(position).getLuggageCapacity());
        holder.tvBasePrice.setText("INR " + cabsBean.get(position).getBaseCharges());
        holder.tvNote.setText("Note: Extra charge after " + cabsBean.get(position).getDistanceInKM() + " km. = Rs " + cabsBean.get(position).getExtraChargesPerKm() + "/km");

*/
        return vi;
    }


}