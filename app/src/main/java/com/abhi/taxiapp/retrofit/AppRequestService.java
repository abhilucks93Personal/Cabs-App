package com.abhi.taxiapp.retrofit;


import com.abhi.taxiapp.booking.model.CabsResponseMain;
import com.abhi.taxiapp.booking.model.CitiesResponseMain;
import com.abhi.taxiapp.profile.model.SignUpModel;
import com.abhi.taxiapp.profile.model.SignUpResponseMain;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * * Interface through which all the api calls will be performed
 */
public interface AppRequestService {


    @FormUrlEncoded
    @POST("CabsApp/UserLogin")
    Observable<SignUpResponseMain> LoginMethod(@Field("UniqueDeviceId") String str1, @Field("DeviceId") String str2, @Field("Number") String str3, @Field("Password") String str4);

    @POST("CabsApp/Registration")
    Observable<SignUpResponseMain> SignUpMethod(@Body SignUpModel data);

    @FormUrlEncoded
    @POST("CabsApp/GetSourceLocations")
    Observable<CitiesResponseMain> getSourceCitiesMethod(@Field("UniqueDeviceId") String str1, @Field("DeviceId") String str2);


    @FormUrlEncoded
    @POST("CabsApp/GetDestinationLocations")
    Observable<CitiesResponseMain> getCitiesMethod(@Field("UniqueDeviceId") String str1, @Field("DeviceId") String str2, @Field("SourceLocationId") int cityId);

    @FormUrlEncoded
    @POST("CabsApp/GetOneWayTripCars")
    Observable<CabsResponseMain> getCabsMethod(@Field("UniqueDeviceId") String uniqueDeviceId, @Field("DeviceId") String deviceId, @Field("FromLocationId") int fromLocationId, @Field("ToLocationId") int toLocationId);

    @FormUrlEncoded
    @POST("CabsApp/ChangePassword")
    Observable<SignUpResponseMain> changePassword(@Field("UniqueDeviceId") String uniqueDeviceId,@Field("DeviceId")  String deviceId,@Field("Id")  String id,@Field("Password")  String strNewPwd);


  /*  @POST("user/UserRegistration")
    Observable<PojoSignup> userSignUp(@Body ModelVendorRegistration modelVendorRegistration);

    @FormUrlEncoded
    @POST("user/LoginNew")
    Observable<PojoSignup> login(@Field("Email") String email, @Field("Password") String password, @Field("LoginType") int LoginType);

    @GET("Pinned/GetUserPinnedByUserId/{userId}/{size}/{pageNum}")
    Observable<PojoGetPinnedImages> getPinned(@Path("userId") String userId, @Path("size") int size, @Path("pageNum") int pageNum);

    @GET("Master/GetAllCategory")
    Observable<CategoriesResponse> getCategories();

    @Multipart
    @POST("http://net.site4demo.com/bizzalley/api/bzl/Image/Upload")
    Observable<BasicMultipartModel> uploadMedia(@PartMap Map<String, RequestBody> mediaPath);*/

}

