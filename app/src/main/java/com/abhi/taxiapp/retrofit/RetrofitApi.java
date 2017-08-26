package com.abhi.taxiapp.retrofit;


import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.abhi.taxiapp.Utils.Constants;
import com.abhi.taxiapp.Utils.Utility;
import com.abhi.taxiapp.booking.model.CabsResponseMain;
import com.abhi.taxiapp.booking.model.CitiesResponseMain;
import com.abhi.taxiapp.profile.model.SignUpModel;
import com.abhi.taxiapp.profile.model.SignUpResponseMain;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by abhishekagarwal on 3/21/17.
 */

public class RetrofitApi {

    private ProgressDialog mProgressDialog;
    private static RetrofitApi retrofitApi = null;
    private ResponseListener mlistener_response;

    public static RetrofitApi getInstance() {


        if (retrofitApi != null)
            return retrofitApi;
        else
            return new RetrofitApi();
    }


    public interface ResponseListener {

        void _onNext(Object obj);

        void _onComplete();

        void _onError(Throwable e);

    }


    // --------------------- Retrofit Api Methods ----------------------------------------------------------


    public void login(final Activity activity, final ResponseListener _mlistener_response, String uniqueId, String deviceId, String number, String password) {

        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();


        RetrofitClient.getClient().LoginMethod(uniqueId, deviceId, number, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpResponseMain>() {
                    @Override
                    public void onCompleted() {
                        Log.e("response", "");
                        mlistener_response._onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response error", "" + e.getStackTrace());
                        for (StackTraceElement s : e.getStackTrace()) {
                            Log.e("StackTraceElement ", "" + s.toString());
                        }
                        Utility.showToast(activity, Constants.errorMsg);
                        mProgressDialog.dismiss();
                        mlistener_response._onError(e);
                    }

                    @Override
                    public void onNext(SignUpResponseMain signUpResponseMain) {
                        Log.e("response", "" + signUpResponseMain);
                        mProgressDialog.dismiss();
                        mlistener_response._onNext(signUpResponseMain);

                    }
                });

    }


    public void signUp(final Activity activity, final ResponseListener _mlistener_response, SignUpModel data) {
        this.mlistener_response = _mlistener_response;
        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();


        RetrofitClient.getClient().SignUpMethod(data)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpResponseMain>() {
                    @Override
                    public void onCompleted() {
                        Log.e("response", "");
                        mlistener_response._onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response error", "" + e.getStackTrace());
                        for (StackTraceElement s : e.getStackTrace()) {
                            Log.e("StackTraceElement ", "" + s.toString());
                        }
                        Utility.showToast(activity, Constants.errorMsg);
                        mProgressDialog.dismiss();
                        mlistener_response._onError(e);
                    }

                    @Override
                    public void onNext(SignUpResponseMain signUpResponseMain) {
                        Log.e("response", "" + signUpResponseMain);
                        mProgressDialog.dismiss();
                        mlistener_response._onNext(signUpResponseMain);

                    }
                });
    }


    public void getSourceCities(final Activity activity, final ResponseListener _mlistener_response, String uniqueDeviceId, String deviceId) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().getSourceCitiesMethod(uniqueDeviceId, deviceId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CitiesResponseMain>() {
                    @Override
                    public void onCompleted() {
                        Log.e("response", "");
                        mlistener_response._onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response error", "" + e.getStackTrace());
                        for (StackTraceElement s : e.getStackTrace()) {
                            Log.e("StackTraceElement ", "" + s.toString());
                        }
                        Utility.showToast(activity, Constants.errorMsg);
                        mlistener_response._onError(e);
                    }

                    @Override
                    public void onNext(CitiesResponseMain citiesResponseMain) {
                        Log.e("response", "" + citiesResponseMain);
                        mlistener_response._onNext(citiesResponseMain);

                    }
                });
    }

    public void getDestinationCities(final Activity activity, final ResponseListener _mlistener_response, String uniqueDeviceId, String deviceId, int cityId) {
        this.mlistener_response = _mlistener_response;


        RetrofitClient.getClient().getCitiesMethod(uniqueDeviceId, deviceId, cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CitiesResponseMain>() {
                    @Override
                    public void onCompleted() {
                        Log.e("response", "");
                        mlistener_response._onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response error", "" + e.getStackTrace());
                        for (StackTraceElement s : e.getStackTrace()) {
                            Log.e("StackTraceElement ", "" + s.toString());
                        }
                        Utility.showToast(activity, Constants.errorMsg);
                        mlistener_response._onError(e);
                    }

                    @Override
                    public void onNext(CitiesResponseMain citiesResponseMain) {
                        Log.e("response", "" + citiesResponseMain);
                        mlistener_response._onNext(citiesResponseMain);

                    }
                });
    }

    public void getCabs(final Activity activity, final ResponseListener _mlistener_response, String uniqueDeviceId, String deviceId, int fromLocationId, int toLocationId) {
        this.mlistener_response = _mlistener_response;

        RetrofitClient.getClient().getCabsMethod(uniqueDeviceId, deviceId, fromLocationId, toLocationId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CabsResponseMain>() {
                    @Override
                    public void onCompleted() {
                        Log.e("response", "");
                        mlistener_response._onComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response error", "" + e.getStackTrace());
                        for (StackTraceElement s : e.getStackTrace()) {
                            Log.e("StackTraceElement ", "" + s.toString());
                        }
                        Utility.showToast(activity, Constants.errorMsg);
                        mlistener_response._onError(e);
                    }

                    @Override
                    public void onNext(CabsResponseMain cabsResponseMain) {
                        Log.e("response", "" + cabsResponseMain);
                        mlistener_response._onNext(cabsResponseMain);

                    }
                });
    }

    public void changePassword(final Activity activity, final ResponseListener _mlistener_response, String uniqueDeviceId, String deviceId, String id, String strNewPwd) {

        this.mlistener_response = _mlistener_response;

        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setMessage("Please Wait");
        mProgressDialog.setCancelable(true);
        mProgressDialog.show();

        RetrofitClient.getClient().changePassword(uniqueDeviceId, deviceId, id, strNewPwd)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignUpResponseMain>() {
                    @Override
                    public void onCompleted() {
                        Log.e("response", "");
                        mlistener_response._onComplete();
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("response error", "" + e.getStackTrace());
                        for (StackTraceElement s : e.getStackTrace()) {
                            Log.e("StackTraceElement ", "" + s.toString());
                        }
                        Utility.showToast(activity, Constants.errorMsg);
                        mlistener_response._onError(e);
                    }

                    @Override
                    public void onNext(SignUpResponseMain signUpResponseMain) {
                        Log.e("response", "" + signUpResponseMain);
                        mlistener_response._onNext(signUpResponseMain);

                    }
                });

    }
}
