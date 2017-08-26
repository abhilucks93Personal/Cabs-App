package com.abhi.taxiapp.booking.controller;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.booking.model.CabsData;
import com.abhi.taxiapp.booking.model.CabsResponseData;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CabsDataAdapter extends RecyclerView.Adapter<CabsDataAdapter.MyViewHolder> {

    private List<CabsResponseData> cabsDataList;
    private Activity context;
    private int selectedIndex = -1;

    public CabsResponseData getSelectedCabData() {
        if (selectedIndex == -1)
            return null;
        else
            return cabsDataList.get(selectedIndex);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView category, fare;
        CircleImageView imageView;


        MyViewHolder(View view) {
            super(view);
            imageView = (CircleImageView) view.findViewById(R.id.cabs_image);
            category = (TextView) view.findViewById(R.id.cabs_category);
            fare = (TextView) view.findViewById(R.id.cabs_fare);

        }
    }


    public CabsDataAdapter(List<CabsResponseData> cabsDataList, Activity context) {
        this.cabsDataList = cabsDataList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cabs_list_item, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CabsResponseData cabsData = cabsDataList.get(position);

        if (position == selectedIndex) {
            holder.imageView.setBorderColorResource(R.color.colorPrimary);
        } else {
            holder.imageView.setBorderColorResource(R.color.colorThemeWhite);
        }

        holder.category.setText(cabsData.getCabCategory());
        Picasso.with(context)
                .load(cabsData.getCarImage())
                .placeholder(R.color.colorGrey)
                .into(holder.imageView);
        holder.fare.setText("Rs. " + cabsData.getBaseCharges());


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog(cabsData, position);
            }
        });

    }

    private void customDialog(CabsResponseData cabsData, final int index) {
        // Create custom dialog object
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialog_cabs);
        // Set dialog title
        // dialog.setTitle("Custom Dialog");
        dialog.setCancelable(true);

        CircleImageView ivImage = (CircleImageView) dialog.findViewById(R.id.iv_dialog_image);
        TextView tvCategory = (TextView) dialog.findViewById(R.id.tv_dialog_category);
        TextView tvDescription = (TextView) dialog.findViewById(R.id.tv_dialog_desc);
        TextView tvBaseFare = (TextView) dialog.findViewById(R.id.tv_dialog_baseCharge);
        TextView tvSeatingCapacity = (TextView) dialog.findViewById(R.id.tv_dialog_seatCapacity);
        TextView tvLuggageCapacity = (TextView) dialog.findViewById(R.id.tv_dialog_luggageCapacity);
        TextView tvSelect = (TextView) dialog.findViewById(R.id.tv_dialog_select);

        Picasso.with(context)
                .load(cabsData.getCarImage())
                .placeholder(R.color.colorGrey)
                .into(ivImage);
        tvCategory.setText(cabsData.getCabCategory());
        tvDescription.setText(cabsData.getCabDescription());
        tvBaseFare.setText("Base Charges : " + cabsData.getBaseCharges());
        tvSeatingCapacity.setText("Seating Capacity : " + cabsData.getSeatingCapacity());
        tvLuggageCapacity.setText("Luggage Capacity : " + cabsData.getLuggageCapacity());

        dialog.show();
        tvSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedIndex = index;
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });


    }

    @Override
    public int getItemCount() {
        return cabsDataList.size();
    }


}