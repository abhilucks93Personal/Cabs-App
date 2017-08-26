package com.abhi.taxiapp.profile.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.booking.view.BookingActivity;
import com.abhi.taxiapp.profile.model.SignUpModel;
import com.abhi.taxiapp.profile.model.SignUpResponseMain;
import com.abhi.taxiapp.retrofit.RetrofitApi;

/**
 * Created by abhi on 02/04/17.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, RetrofitApi.ResponseListener {

    String strProfile, strName, strNumber, strEmail, strPassword, strAddress;
    EditText etName, etNumber, etMail, etPassword;
    TextView tvSubmit, tvLogin, tvSkip;
    String uniqueDeviceId, deviceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.setStatusBarTranslucent(this, true);
        setContentView(R.layout.activity_sign_up);

        findViewById();
        initData();
    }

    private void findViewById() {

        etName = (EditText) findViewById(R.id.signUp_et_name);
        etNumber = (EditText) findViewById(R.id.signUp_et_number);
        etMail = (EditText) findViewById(R.id.signUp_et_email);
        etPassword = (EditText) findViewById(R.id.signUp_et_password);

        tvSubmit = (TextView) findViewById(R.id.signUp_tv_submit);
        tvSubmit.setOnClickListener(this);

        tvLogin = (TextView) findViewById(R.id.signUp_tv_login);
        tvLogin.setOnClickListener(this);

        tvSkip = (TextView) findViewById(R.id.signUp_tv_skip);
        tvSkip.setOnClickListener(this);

    }

    private void initData() {
        uniqueDeviceId = Utility.getUniqueId(getApplicationContext());
        deviceId = Utility.getPreferences(this, Constants.DeviceId);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.signUp_tv_submit:
                fetchData();
                break;


            case R.id.signUp_tv_login:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.signUp_tv_skip:
                startActivity(new Intent(SignUpActivity.this, BookingActivity.class));
                finish();
                break;

        }
    }

    private void fetchData() {
        strName = etName.getText().toString().trim();
        strNumber = etNumber.getText().toString().trim();
        strEmail = etMail.getText().toString().trim();
        strPassword = etPassword.getText().toString().trim();
        strProfile = "";

        if (isValidated()) {

            SignUpModel data = new SignUpModel();
            data.setUniqueDeviceId(uniqueDeviceId);
            data.setDeviceId(deviceId);
            data.setProfileImage(strProfile);
            data.setName(strName);
            data.setNumber(strNumber);
            data.setAddress(strAddress);
            data.setPassword(strPassword);
            data.setEmail(strEmail);

            RetrofitApi.getInstance().signUp(this, this, data);

        }
    }

    private boolean isValidated() {
        if (strName.length() == 0) {
            Utility.showToast(SignUpActivity.this, "Name field is mandatory.");
            etName.setText("");
            return false;
        }

        if (strName.length() < 3) {
            Utility.showToast(SignUpActivity.this, "Name field must be at least 3 characters long.");
            return false;
        }

        if (strNumber.length() == 0) {
            Utility.showToast(SignUpActivity.this, "Mobile Number field is mandatory");
            etNumber.setText("");
            return false;
        }

        if (strNumber.length() < 10) {
            Utility.showToast(SignUpActivity.this, "Please  enter a valid Mobile Number");
            return false;
        }

        if (strEmail.length() == 0) {
            Utility.showToast(SignUpActivity.this, "Email field is mandatory");
            etMail.setText("");
            return false;
        }

        if (!Utility.isValidEmail(strEmail)) {
            Utility.showToast(SignUpActivity.this, "Please  enter a valid Email Id");
            return false;
        }

        if (strPassword.length() == 0) {
            Utility.showToast(SignUpActivity.this, "Please input the Password");
            etNumber.setText("");
            return false;
        }

        if (strPassword.length() < 5) {
            Utility.showToast(SignUpActivity.this, "Password must be at least 5 characters long");
            etNumber.setText("");
            return false;
        }

        return true;
    }

    @Override
    public void _onNext(Object obj) {
        SignUpResponseMain responseMain = (SignUpResponseMain) obj;
        if (responseMain.getStatusCode().equals(Constants.StatusCodeSuccess)) {
            Utility.addPreferences(SignUpActivity.this, Constants.ProfileData, responseMain.getData().get(0));
            Utility.addPreferences(SignUpActivity.this, Constants.IsLogin, true);
            finish();
        } else {
            Utility.showToast(SignUpActivity.this, responseMain.getStatusMsg());
        }
    }

    @Override
    public void _onComplete() {

    }

    @Override
    public void _onError(Throwable e) {

    }
}
