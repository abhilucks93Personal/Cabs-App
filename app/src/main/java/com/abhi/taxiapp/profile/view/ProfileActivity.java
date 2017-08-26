package com.abhi.taxiapp.profile.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.BlurTransformation;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.profile.model.SignUpResponseData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by abhi on 02/04/17.
 */

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_name, tv_email, tv_number, tv_primary_addrss, tv_more, tv_address1, tv_address2, tv_address3, tv_address4;
    SignUpResponseData _data;
    CircleImageView iv_profileImage;
    ImageView iv_edit, iv_profileImage_bg;
    ArrayList<TextView> tv_addrses = new ArrayList<>();
    private LinearLayout ll_addrss2, ll_addrss3, ll_addrss4, ll_addrss5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile2);

        findViewByID();
        setUpActionBar();

    }

    private void setUpActionBar() {

        setTitle("My Profile");
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

    @Override
    public void onResume() {
        super.onResume();
        initUI();
    }

    private void initUI() {

        _data = Utility.getProfileData(this, Constants.ProfileData);

        ll_addrss2.setVisibility(View.GONE);
        ll_addrss3.setVisibility(View.GONE);
        ll_addrss4.setVisibility(View.GONE);
        ll_addrss5.setVisibility(View.GONE);
        if (_data.getProfileImage() != null)
            if (_data.getProfileImage().length() > 0) {
                Picasso.with(getApplicationContext())
                        .load(_data.getProfileImage())
                        .placeholder(R.drawable.dummy_profile)
                        .error(R.drawable.dummy_profile)
                        .into(iv_profileImage);
                Picasso.with(getApplicationContext())
                        .load(_data.getProfileImage())
                        .transform(new BlurTransformation(ProfileActivity.this))
                        .into(iv_profileImage_bg);
            }

        if (_data.getName() != null) {
            tv_name.setText(_data.getName());
        }

        if (_data.getEmail() != null)
            tv_email.setText(_data.getEmail());

        if (_data.getNumber() != null)
            tv_number.setText("+91 " + _data.getNumber());


        for (int i = 0; i < 5; i++) {
            tv_addrses.get(i).setText("");

        }

        tv_addrses.get(0).setText(_data.getAddress());

      /*  if (_data.getCustAddress() != null) {
            if (_data.getCustAddress().size() > 1) {
                tv_more.setVisibility(View.VISIBLE);
            } else {
                tv_more.setVisibility(View.GONE);
            }

            if (_data.getCustAddress().size() > 0) {
                int index = 0;
                for (int i = 0; i < _data.getCustAddress().size(); i++) {
                    if (index < 5)
                        tv_addrses.get(i).setText(_data.getCustAddress().get(i).getAddess()
                                + "\n" + _data.getCustAddress().get(i).getStreet()
                                + "\n" + _data.getCustAddress().get(i).getCity()
                                + "\n" + _data.getCustAddress().get(i).getPostalCode());

                    index++;
                }
            }
        }*/
    }

    private void findViewByID() {


        iv_profileImage_bg = (ImageView) findViewById(R.id.frag_consumer_profile_iv_display);
        iv_profileImage = (CircleImageView) findViewById(R.id.frag_consumer_profile_iv_profile);
        iv_profileImage.setOnClickListener(this);
        iv_edit = (ImageView) findViewById(R.id.fragment_consumer_profile_iv_edit);
        iv_edit.setOnClickListener(this);

        tv_name = (TextView) findViewById(R.id.frag_consumer_profile_tv_name);
        tv_email = (TextView) findViewById(R.id.frag_consumer_profile_tv_email);
        tv_number = (TextView) findViewById(R.id.frag_consumer_profile_tv_number);
        tv_primary_addrss = (TextView) findViewById(R.id.frag_consumer_profile_tv_primary_addrss);
        tv_addrses.add(tv_primary_addrss);
        tv_more = (TextView) findViewById(R.id.frag_consumer_profile_tv_more);
        tv_more.setOnClickListener(this);
        tv_address1 = (TextView) findViewById(R.id.frag_consumer_profile_tv_address1);
        tv_addrses.add(tv_address1);
        tv_address2 = (TextView) findViewById(R.id.frag_consumer_profile_tv_address2);
        tv_addrses.add(tv_address2);
        tv_address3 = (TextView) findViewById(R.id.frag_consumer_profile_tv_address3);
        tv_addrses.add(tv_address3);
        tv_address4 = (TextView) findViewById(R.id.frag_consumer_profile_tv_address4);
        tv_addrses.add(tv_address4);

        ll_addrss2 = (LinearLayout) findViewById(R.id.frag_consumer_profile_ll_address1);
        ll_addrss3 = (LinearLayout) findViewById(R.id.frag_consumer_profile_ll_address2);
        ll_addrss4 = (LinearLayout) findViewById(R.id.frag_consumer_profile_ll_address3);
        ll_addrss5 = (LinearLayout) findViewById(R.id.frag_consumer_profile_ll_address4);

    }

    public void startImageDialog(String imageUrl) {
        if (imageUrl == null)
            return;
        if (imageUrl.length() == 0)
            return;

        // startActivity(new Intent(ProfileActivity.this, ZoomClass.class).putExtra("offerurl", imageUrl));

       /* DialogFragment frag = ImageDialogFragment.newInstance("", imageUrl);
        frag.show(getSupportFragmentManager(), "imageDialog");*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.frag_consumer_profile_tv_more:
                if (tv_address1.getText().length() > 0)
                    ll_addrss2.setVisibility(View.VISIBLE);
                else
                    ll_addrss2.setVisibility(View.GONE);

                if (tv_address2.getText().length() > 0)
                    ll_addrss3.setVisibility(View.VISIBLE);
                else
                    ll_addrss3.setVisibility(View.GONE);

                if (tv_address3.getText().length() > 0)
                    ll_addrss4.setVisibility(View.VISIBLE);
                else
                    ll_addrss4.setVisibility(View.GONE);

                if (tv_address4.getText().length() > 0)
                    ll_addrss5.setVisibility(View.VISIBLE);
                else
                    ll_addrss5.setVisibility(View.GONE);

                tv_more.setVisibility(View.GONE);
                break;

            case R.id.fragment_consumer_profile_iv_edit:
                //  startActivityForResult(new Intent(ProfileActivity.this, EditProfileActivity.class), 100);
                break;
        }
    }
}
