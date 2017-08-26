package com.abhi.taxiapp.profile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 21/04/17.
 */

public class SignUpResponseData {

    @SerializedName("uniqueDeviceId")
    private String UniqueDeviceId;
    @SerializedName("deviceId")
    private String DeviceId;
    @SerializedName("id")
    private Integer id;
    @SerializedName("profileImage")
    private String profileImage;
    @SerializedName("name")
    private String name;
    @SerializedName("userName")
    private String number;
    @SerializedName("password")
    private String password;
    @SerializedName("userEmail")
    private String email;
    @SerializedName("address")
    private String address;

    public String getUniqueDeviceId() {
        return UniqueDeviceId;
    }

    public void setUniqueDeviceId(String uniqueDeviceId) {
        UniqueDeviceId = uniqueDeviceId;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        if (number == null)
            return "";
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        if (email == null)
            return "";
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
