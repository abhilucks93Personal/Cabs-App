package com.abhi.taxiapp.settings;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.profile.view.IntroActivity;
import com.abhi.taxiapp.profile.view.ProfileActivity;

/**
 * Created by Abhishek on 2/3/17.
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLogout, tvSelectedTone;
    RelativeLayout llNotificationSounds;
    LinearLayout llChangePassword;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById();
        initUi();
        setUpActionBar();
        if (Utility.getPreferences(this, "selectedTone").equalsIgnoreCase("")) {
            tvSelectedTone.setText("None");
        } else {
            tvSelectedTone.setText(Utility.getPreferences(this, "selectedTone"));
        }
    }

    private void initUi() {
        Boolean isLoggedIn = Utility.getPreferences(this, Constants.IsLogin, false);
        if (!isLoggedIn) {
            llChangePassword.setEnabled(false);
            tvLogout.setEnabled(false);
        }
    }

    private void setUpActionBar() {
        setTitle("My Settings");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void findViewById() {


        llNotificationSounds = (RelativeLayout) findViewById(R.id.setting_sound);
        llChangePassword = (LinearLayout) findViewById(R.id.setting_change_password);
        tvLogout = (TextView) findViewById(R.id.setting_logout);

        tvSelectedTone = (TextView) findViewById(R.id.setting_selected_ringtone);

        llNotificationSounds.setOnClickListener(this);
        llChangePassword.setOnClickListener(this);
        tvLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.setting_sound:
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Notification Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(Utility.getPreferences(SettingsActivity.this, "selectedToneUri")));
                startActivityForResult(intent, 5);
                break;

            case R.id.setting_change_password:
                Intent mIntent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(mIntent);
                break;

            case R.id.setting_logout:
                showLogoutDialog();
                break;
        }
    }


    private void showLogoutDialog() {
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(SettingsActivity.this);

        // set title
        alertDialogBuilder.setTitle("Alert");

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you want to Logout ?")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utility.clearPreferenceData(SettingsActivity.this);
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        // create alert dialog
        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == 5) {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);

            if (uri != null) {
                String selectedTone = RingtoneManager.getRingtone(SettingsActivity.this, uri).getTitle(SettingsActivity.this);
                tvSelectedTone.setText(selectedTone);
                Utility.addPreferences(SettingsActivity.this, "selectedToneUri", uri.toString());
                Utility.addPreferences(SettingsActivity.this, "selectedTone", selectedTone);
            }
        }
    }
}
