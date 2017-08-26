package com.abhi.taxiapp.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.profile.model.SignUpResponseData;
import com.abhi.taxiapp.profile.model.SignUpResponseMain;
import com.abhi.taxiapp.profile.view.SignUpActivity;
import com.abhi.taxiapp.retrofit.RetrofitApi;

/**
 * Created by neha on 3/3/17.
 */

public class ChangePasswordActivity extends AppCompatActivity implements RetrofitApi.ResponseListener {

    EditText etOldPwd, etNewPwd, etConfirmPwd;
    String strOldPwd, strNewPwd, strConfirmPwd;
    TextView tvDone;
    private String uniqueDeviceId, deviceId;
    private String id;
    private SignUpResponseData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        findViewByID();
        setUpActionBar();
        initUI();

    }

    private void initUI() {
        uniqueDeviceId = Utility.getUniqueId(ChangePasswordActivity.this);
        deviceId = Utility.getPreferences(ChangePasswordActivity.this, Constants.DeviceId);
        data = Utility.getProfileData(this, Constants.ProfileData);


        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.hideKeyboard(ChangePasswordActivity.this);
                strOldPwd = etOldPwd.getText().toString().trim();
                strNewPwd = etNewPwd.getText().toString().trim();
                strConfirmPwd = etConfirmPwd.getText().toString().trim();
                if (isValid()) {
                    callChangePasswordApi();
                }
            }
        });
    }

    private void callChangePasswordApi() {

        RetrofitApi.getInstance().changePassword(this, this, uniqueDeviceId, deviceId, "" + data.getId(), strNewPwd);
    }

    private boolean isValid() {

        if (strOldPwd.length() == 0) {
            Utility.showSnackBar(this, getResources().getString(R.string.error_fields_mandatory));
            etOldPwd.setText("");
            return false;
        }

        if (!strOldPwd.equals(data.getPassword())) {
            Utility.showSnackBar(this, "Please enter the correct old password");
            return false;
        }

        if (strNewPwd.length() == 0) {
            Utility.showSnackBar(this, getResources().getString(R.string.error_fields_mandatory));
            etNewPwd.setText("");
            return false;
        }
        if (strConfirmPwd.length() == 0) {
            Utility.showSnackBar(this, getResources().getString(R.string.error_fields_mandatory));
            etConfirmPwd.setText("");
            return false;
        }
        if (strNewPwd.length() < 5) {
            Utility.showSnackBar(this, getResources().getString(R.string.error_password_length));
            return false;
        }
        if (!strNewPwd.equals(strConfirmPwd)) {
            Utility.showSnackBar(this, getResources().getString(R.string.error_password_mismatch));
            return false;
        }
        return true;
    }

    private void findViewByID() {

        tvDone = (TextView) findViewById(R.id.change_password_save);
        etOldPwd = (EditText) findViewById(R.id.change_password_old);
        etNewPwd = (EditText) findViewById(R.id.change_password_new);
        etConfirmPwd = (EditText) findViewById(R.id.change_password_confirm);
    }

    private void setUpActionBar() {

        setTitle("Change Password");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void _onNext(Object obj) {

        SignUpResponseMain responseMain = (SignUpResponseMain) obj;
        if (responseMain.getStatusCode().equals(Constants.StatusCodeSuccess)) {
            data = responseMain.getData().get(0);
            Utility.addPreferences(ChangePasswordActivity.this, Constants.ProfileData, data);
            Utility.showToast(ChangePasswordActivity.this, "Password changed successfully.");
        } else {
            Utility.showToast(ChangePasswordActivity.this, responseMain.getStatusMsg());
        }
    }

    @Override
    public void _onComplete() {

    }

    @Override
    public void _onError(Throwable e) {

    }
}
