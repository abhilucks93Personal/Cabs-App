package com.abhi.taxiapp.booking.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.taxiapp.R;

/**
 * Created by abhi on 22/07/17.
 */

public class BookingFragment2 extends Fragment implements View.OnClickListener {

    TextView tvSubmit;
    String tag = "BookingFragment2";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.clearDisappearingChildren();
        View view = inflater.inflate(R.layout.fragment_booking2, container, false);

        findViewById(view);

        initData();


        return view;
    }

    private void findViewById(View view) {
        tvSubmit = (TextView) view.findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(this);

    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_submit:
                ((BookingActivity) getActivity()).switchFragment(BookingFragment2.this, new BookingFragment3(), tag, null);
                break;
        }
    }
}
