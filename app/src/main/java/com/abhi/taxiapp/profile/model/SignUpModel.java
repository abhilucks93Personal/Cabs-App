package com.abhi.taxiapp.profile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abhi on 21/04/17.
 */

public class SignUpModel {

    @SerializedName("UniqueDeviceId")
    private String UniqueDeviceId;
    @SerializedName("DeviceId")
    private String DeviceId;
    @SerializedName("ProfileImage")
    private String profileImage;
    @SerializedName("Name")
    private String name;
    @SerializedName("Number")
    private String number;
    @SerializedName("Password")
    private String password;
    @SerializedName("UserEmail")
    private String email;
    @SerializedName("Address")
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
