package com.abhi.taxiapp.bookingHistory;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Utility;

/**
 * Created by abhi on 02/04/17.
 */

public class HistoryActivity extends Activity {

    private FloatingActionButton fabIcon;
    private TextView tvStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.setStatusBarTranslucent(this, false);
        setContentView(R.layout.activity_history);

        tvStatus = (TextView) findViewById(R.id.history_tv_status);
        fabIcon = (FloatingActionButton) findViewById(R.id.fab_icon_history);

        fabIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvStatus.getText().equals("Pending"))
                    tvStatus.setText("Completed");
                else
                    tvStatus.setText("Pending");


            }
        });
    }
}
