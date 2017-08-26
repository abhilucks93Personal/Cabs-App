package com.abhi.taxiapp.booking.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.abhi.taxiapp.R;
import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.booking.controller.CabsDataAdapter;
import com.abhi.taxiapp.booking.controller.CityAdapter;
import com.abhi.taxiapp.booking.model.CabsResponseData;
import com.abhi.taxiapp.booking.model.CitiesResponseData;
import com.abhi.taxiapp.booking.model.CitiesResponseMain;
import com.abhi.taxiapp.retrofit.RetrofitApi;
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
import java.util.List;


public class BookingFragment1 extends Fragment implements OnMapReadyCallback, View.OnClickListener, RetrofitApi.ResponseListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    String tag = "BookingFragment1";
    Context mContext;

    SupportMapFragment supportMapFragment;
    GoogleMap map;
    ArrayList<Marker> markers;
    int mapBoundsPadding = 200; // offset from edges of the map in pixels

    RelativeLayout rlGetCabs;
    ProgressBar pbGetCabs;
    LinearLayout llBookingCabs;
    CabsDataAdapter mAdapter;
    List<CabsResponseData> cabsDataList = new ArrayList<>();
    RecyclerView recyclerView;
    Handler handler;

    TextView tvOneWay, tvTwoWay, tvMultiWay;
    TextView tvDate, tvTime;
    TextView tvPickupHint, tvPickup, tvDropHint, tvDrop;

    TextView tvProceed;

    private CityAdapter adapter;
    private String uniqueDeviceId;
    private String deviceId;
    private ArrayList<CitiesResponseData> _data;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_booking1, container, false);

        findViewById(view);

        initData();

        initMap();

        initRecyclerView();


        return view;
    }

    private void findViewById(View view) {

        tvOneWay = (TextView) view.findViewById(R.id.tv_one_way);
        tvOneWay.setOnClickListener(this);
        tvTwoWay = (TextView) view.findViewById(R.id.tv_two_way);
        tvTwoWay.setOnClickListener(this);
        tvMultiWay = (TextView) view.findViewById(R.id.tv_multi_way);
        tvMultiWay.setOnClickListener(this);

        tvDate = (TextView) view.findViewById(R.id.tv_date);
        tvDate.setOnClickListener(this);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvTime.setOnClickListener(this);

        tvPickupHint = (TextView) view.findViewById(R.id.tv_pickup_hint);
        tvPickupHint.setOnClickListener(this);
        tvPickup = (TextView) view.findViewById(R.id.tv_pickup);
        tvPickup.setOnClickListener(this);

        tvDropHint = (TextView) view.findViewById(R.id.tv_drop_hint);
        tvDropHint.setOnClickListener(this);
        tvDrop = (TextView) view.findViewById(R.id.tv_drop);
        tvDrop.setOnClickListener(this);

        rlGetCabs = (RelativeLayout) view.findViewById(R.id.rl_get_cabs);
        rlGetCabs.setOnClickListener(this);
        pbGetCabs = (ProgressBar) view.findViewById(R.id.pb_get_cabs);
        llBookingCabs = (LinearLayout) view.findViewById(R.id.ll_booking_cabs);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_cabs);

        tvProceed = (TextView) view.findViewById(R.id.tv_proceed);
        tvProceed.setOnClickListener(this);


    }

    private void initData() {
        handler = new Handler(Looper.getMainLooper());

        markers = new ArrayList<>();

        mContext = getActivity();
        pbGetCabs.getIndeterminateDrawable()
                .setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        pbGetCabs.setVisibility(View.INVISIBLE);
    }

    private void initRecyclerView() {

        addDummyCabsData();


    }

    private void addDummyCabsData() {

        cabsDataList.add(new CabsResponseData(1, "Compact", "Indica, swift, Alto, or equivalent", "4+1", "0+2",
                "https://imgct2.aeplcdn.com/img/800x600/car-data/big/datsun-redi-go-default-image.png-version201707131518.png",
                2500, 100, 200, 4, 0));

        cabsDataList.add(new CabsResponseData(1, "Sedan", "Dzire, Etios, Indigo or equivalent", "4+1", "2+1",
                "https://imgct2.aeplcdn.com/img/400/cars/generic/Maruti-Suzuki-Ciaz-Top-Fuel-Saving-Sedan-Car-In-India.png",
                3500, 200, 200, 4, 0));

        cabsDataList.add(new CabsResponseData(1, "Suv", "Innova, Xylo, Tavera or equivalent", "6+1", "3+2",
                "http://cdn.hamrobazaar.com/540045_05262d36aa66ag74_large.jpg",
                5500, 300, 200, 4, 0));

        cabsDataList.add(new CabsResponseData(1, "Compact", "Indica, swift, Alto, or equivalent", "4+1", "0+2",
                "https://imgct2.aeplcdn.com/img/800x600/car-data/big/datsun-redi-go-default-image.png-version201707131518.png",
                2500, 100, 200, 4, 0));

        cabsDataList.add(new CabsResponseData(1, "Sedan", "Dzire, Etios, Indigo or equivalent", "4+1", "2+1",
                "https://imgct2.aeplcdn.com/img/400/cars/generic/Maruti-Suzuki-Ciaz-Top-Fuel-Saving-Sedan-Car-In-India.png",
                3500, 200, 200, 4, 0));

        cabsDataList.add(new CabsResponseData(1, "Suv", "Innova, Xylo, Tavera or equivalent", "6+1", "3+2",
                "http://cdn.hamrobazaar.com/540045_05262d36aa66ag74_large.jpg",
                5500, 300, 200, 4, 0));

        mAdapter = new CabsDataAdapter(cabsDataList, getActivity());
        recyclerView.setAdapter(mAdapter);
    }

    private void initMap() {
        FragmentManager fm = getActivity().getSupportFragmentManager();/// getChildFragmentManager();
        supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }


    private void getPickupCities() {
        getCities(-1);
    }

    private void getDropCities() {
        getCities(1);
    }


    private void setupOneWayMode() {
        llBookingCabs.setVisibility(View.GONE);
        rlGetCabs.setVisibility(View.VISIBLE);

        tvOneWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_selected));
        tvTwoWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_normal));
        tvMultiWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_normal));

        tvOneWay.setTextColor(getResources().getColor(R.color.colorBlack));
        tvTwoWay.setTextColor(getResources().getColor(R.color.colorGrey));
        tvMultiWay.setTextColor(getResources().getColor(R.color.colorGrey));
    }

    private void setupTwoWayMode() {
        llBookingCabs.setVisibility(View.GONE);
        rlGetCabs.setVisibility(View.VISIBLE);

        tvOneWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_normal));
        tvTwoWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_selected));
        tvMultiWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_normal));

        tvOneWay.setTextColor(getResources().getColor(R.color.colorGrey));
        tvTwoWay.setTextColor(getResources().getColor(R.color.colorBlack));
        tvMultiWay.setTextColor(getResources().getColor(R.color.colorGrey));

    }

    private void setupMultiWayMode() {
        llBookingCabs.setVisibility(View.GONE);
        rlGetCabs.setVisibility(View.VISIBLE);

        tvOneWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_normal));
        tvTwoWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_normal));
        tvMultiWay.setBackground(getResources().getDrawable(R.drawable.layout_bg_selected));

        tvOneWay.setTextColor(getResources().getColor(R.color.colorGrey));
        tvTwoWay.setTextColor(getResources().getColor(R.color.colorGrey));
        tvMultiWay.setTextColor(getResources().getColor(R.color.colorBlack));

    }


    private void getCities(final int cityId) {
        _data = new ArrayList<>();
        addCitiesDummyData();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_country, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();

        progressBar = (ProgressBar) dialogView.findViewById(R.id.dialog_filter_category_progress);
        final ListView lvCity = (ListView) dialogView.findViewById(R.id.dialog_lv_filter_category);
        adapter = new CityAdapter(getActivity(), _data);
        lvCity.setAdapter(adapter);
        lvCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cityItemClick(_data.get(i), cityId);
                b.dismiss();
            }
        });

        EditText et_search = (EditText) dialogView.findViewById(R.id.dialog_et_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.initSearch(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        b.show();

      /*  if (cityId == -1)
            RetrofitApi.getInstance().getSourceCities(getActivity(), this, uniqueDeviceId, deviceId);
        else
            RetrofitApi.getInstance().getDestinationCities(getActivity(), this, uniqueDeviceId, deviceId, cityId);*/
    }

    private void addCitiesDummyData() {
        _data.add(new CitiesResponseData("Shimla", 31.104814, 77.173403));
        _data.add(new CitiesResponseData("Chandigarh", 30.733315, 76.779418));
        _data.add(new CitiesResponseData("Delhi", 28.704059, 77.102490));
        _data.add(new CitiesResponseData("Lucknow", 26.846694, 80.946166));
    }

    private void setCityAdapter(final ArrayList<CitiesResponseData> data) {
        if (progressBar != null && progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.GONE);
        _data.clear();
        _data.addAll(data);
        adapter.refreshData(data);

    }

    private void cityItemClick(CitiesResponseData data, int value) {
        llBookingCabs.setVisibility(View.GONE);
        rlGetCabs.setVisibility(View.VISIBLE);

        switch (value) {

            case -1:
                tvPickup.setVisibility(View.VISIBLE);
                tvPickup.setText(data.getLocationName());
                tvPickupHint.setText("Pick From");

                setMarker(new LatLng(data.getLatitude(), data.getLongitude()), "Source", data.getLocationName(), 0);
                break;

            default:
                tvDrop.setVisibility(View.VISIBLE);
                tvDrop.setText(data.getLocationName());
                tvDropHint.setText("Drop To");

                setMarker(new LatLng(data.getLatitude(), data.getLongitude()), "Destination", data.getLocationName(), 1);
                break;
        }

    }

    private void setMarker(LatLng latLng, String title, String snippet, int markerIndex) {
        if (map != null) {
            if (markerIndex < markers.size()) {
                markers.get(markerIndex).remove();
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(title)
                        .snippet(snippet));

                markers.set(markerIndex, marker);
            } else {
                Marker marker = map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(title)
                        .snippet(snippet));
                markers.add(marker);
            }
            animateMapView();
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

    private void getCabs() {

        if (isDetailsValidated()) {
            pbGetCabs.setVisibility(View.VISIBLE);


            handler.postDelayed(new Runnable() {
                public void run() {
                    pbGetCabs.setVisibility(View.INVISIBLE);
                    rlGetCabs.setVisibility(View.GONE);
                    mAdapter = new CabsDataAdapter(cabsDataList, getActivity());
                    recyclerView.setAdapter(mAdapter);
                    llBookingCabs.setVisibility(View.VISIBLE);
                }
            }, 2000);
        }

    }

    private boolean isDetailsValidated() {

        if (tvPickup.getText().length() == 0) {
            Utility.showToast(getActivity(), "Please fill all the info !");
            return false;
        }

        if (tvDrop.getText().length() == 0) {
            Utility.showToast(getActivity(), "Please fill all the info !");
            return false;
        }

        if (tvDate.getText().length() == 0) {
            Utility.showToast(getActivity(), "Please fill all the info !");
            return false;
        }

        if (tvTime.getText().length() == 0) {
            Utility.showToast(getActivity(), "Please fill all the info !");
            return false;
        }

        return true;
    }


    private void submitDetails() {
        if (isCabSelected())
            ((BookingActivity) getActivity()).switchFragment(BookingFragment1.this, new BookingFragment2(), tag, null);
    }

    private boolean isCabSelected() {

        CabsResponseData data = mAdapter.getSelectedCabData();
        if (data == null) {
            Utility.showToast(getActivity(), "Please select a cab first !");
            return false;
        }
        return true;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_one_way:
                setupOneWayMode();
                break;

            case R.id.tv_two_way:
                setupTwoWayMode();
                break;

            case R.id.tv_multi_way:
                //  setupMultiWayMode();
                break;

            case R.id.tv_date:
                Utility.datePickerDialog(getActivity(), this);
                break;

            case R.id.tv_time:
                Utility.timePickerDialog(getActivity(), this);
                break;

            case R.id.tv_pickup_hint:
                getPickupCities();
                break;

            case R.id.tv_pickup:
                getPickupCities();
                break;

            case R.id.tv_drop_hint:
                getDropCities();
                break;

            case R.id.tv_drop:
                getDropCities();
                break;

            case R.id.rl_get_cabs:
                getCabs();
                break;

            case R.id.tv_proceed:
                submitDetails();
                break;

        }
    }


    @Override
    public void _onNext(Object obj) {
        CitiesResponseMain responseMain = (CitiesResponseMain) obj;
        if (responseMain.getStatusCode().equals(Constants.StatusCodeSuccess)) {
            setCityAdapter(responseMain.getData());
        } else {
            Utility.showToast(getActivity(), responseMain.getStatusMsg());
        }

    }

    @Override
    public void _onComplete() {

    }

    @Override
    public void _onError(Throwable e) {

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "0" + (view.getMonth() + 1)
                + "-" + view.getDayOfMonth()
                + "-" + view.getYear();

        tvDate.setText(Utility.formatDateForDisplay(date));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        String time = hourOfDay
                + ":" + minute;

        tvTime.setText(Utility.formatTimeForDisplay(time));

    }
}
