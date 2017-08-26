package com.abhi.taxiapp.profile.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Utility;

/**
 * Created by abhi on 02/04/17.
 */

public class OtpVerificationActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    TextView tvResend;
    EditText etOtp;
    private String otpCode = "0000";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_otp);

        findViewById();
    }

    private void findViewById() {

        tvResend = (TextView) findViewById(R.id.otp_tv_resend);
        tvResend.setOnClickListener(this);
        etOtp = (EditText) findViewById(R.id.otp_et_code);
        etOtp.addTextChangedListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.otp_tv_resend:
                break;

        }
    }


    private boolean isValidated(String strOtp) {
        if (strOtp.equals(otpCode)) {
            return true;
        }
        return false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (isValidated(s.toString())) {
            Utility.showToast(OtpVerificationActivity.this, "Success");
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
