package com.abhi.taxiapp.booking.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.booking.MainLayout;


public class BookingActivity extends FragmentActivity implements OnClickListener {

    MainLayout mainLayout;
    FragmentManager fm;
    TextView tv_title;
    ImageView iv_menu_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utility.setStatusBarTranslucent(this, true);

        // Inflate the mainLayout
        mainLayout = (MainLayout) this.getLayoutInflater().inflate(
                R.layout.activity_booking, null);
        setContentView(mainLayout);

        findViewById();


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new BookingFragment1();
        ft.add(R.id.activity_main_content_fragment, fragment, "tag1");
        ft.commit();
    }

    private void findViewById() {

        fm = BookingActivity.this.getSupportFragmentManager();

        tv_title = (TextView) findViewById(R.id.activity_main_content_tv_title);

        iv_menu_icon = (ImageView) findViewById(R.id.activity_main_content_button_menu);

    }

    public void switchFragment(Fragment sourceFrag, Fragment _fragment, String tag, Object data) {


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right);
        ft.add(R.id.activity_main_content_fragment, _fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.activity_main_content_button_menu:
                toggleMenu(v);
                break;


        }
    }


    public void toggleMenu(View v) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isAcceptingText()) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        mainLayout.toggleMenu();


    }

    @Override
    public void onBackPressed() {
        if (mainLayout.isMenuShown()) {
            mainLayout.toggleMenu();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (mainLayout.isMenuShown()) {
            mainLayout.toggleMenu();
        }
    }

}



