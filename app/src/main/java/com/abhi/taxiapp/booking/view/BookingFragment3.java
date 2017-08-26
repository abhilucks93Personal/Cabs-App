package com.abhi.taxiapp.booking.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abhi.taxiapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by abhi on 22/07/17.
 */

public class BookingFragment3 extends Fragment implements OnMapReadyCallback, View.OnClickListener {

    Context mContext;
    String tag = "BookingFragment3";

    SupportMapFragment supportMapFragment;
    GoogleMap map;
    ArrayList<Marker> markers;
    int mapBoundsPadding = 150; // offset from edges of the map in pixels

    TextView tvConfirm;
    private AppBarLayout appBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container.clearDisappearingChildren();
        View view = inflater.inflate(R.layout.fragment_booking3, container, false);

        findViewById(view);

        initData();

        initMap();


        return view;
    }

    private void findViewById(View view) {
        appBar = (AppBarLayout) view.findViewById(R.id.appbar);
        tvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        tvConfirm.setOnClickListener(this);

    }

    private void initMap() {
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container2);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container2, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }

    private void initData() {
        mContext = getActivity();
        markers = new ArrayList<>();

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        params.setBehavior(behavior);
    }

    private void setMarker(LatLng latLng, String title, String snippet) {
        if (map != null) {

            Marker marker = map.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(title)
                    .snippet(snippet));
            markers.add(marker);


        }
    }

    private void animateMapView() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, mapBoundsPadding);
        map.animateCamera(cu);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_confirm:
                ((BookingActivity) getActivity()).switchFragment(BookingFragment3.this, new BookingFragment4(), tag, null);
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        setMarker(new LatLng(28.704059, 77.102490), "Source", "Delhi");
        setMarker(new LatLng(31.104814, 77.173403), "Destination", "Shimla");
        animateMapView();


    }
}
