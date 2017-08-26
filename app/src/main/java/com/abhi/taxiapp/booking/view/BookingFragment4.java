package com.abhi.taxiapp.booking.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.abhi.taxiapp.R;

/**
 * Created by abhi on 22/07/17.
 */

public class BookingFragment4 extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    TextView tvBook;
    String tag = "BookingFragment4";
    CheckBox cbOnline, cbCOD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.clearDisappearingChildren();
        View view = inflater.inflate(R.layout.fragment_booking4, container, false);

        findViewById(view);

        initData();


        return view;
    }

    private void findViewById(View view) {
        tvBook = (TextView) view.findViewById(R.id.tv_book);
        tvBook.setOnClickListener(this);

        cbOnline = (CheckBox) view.findViewById(R.id.cb_online);
        cbOnline.setOnCheckedChangeListener(this);
        cbCOD = (CheckBox) view.findViewById(R.id.cb_cod);
        cbCOD.setOnCheckedChangeListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_book:
               // ((BookingActivity) getActivity()).switchFragment(BookingFragment4.this, new BookingFragment4(), tag, null);
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.getId() == R.id.cb_cod) {
            if (isChecked) {
                cbOnline.setChecked(false);
                tvBook.setText("BOOK");
            }
        } else if (buttonView.getId() == R.id.cb_online) {
            if (isChecked) {
                cbCOD.setChecked(false);
                tvBook.setText("PAY");
            }
        }
    }
}
