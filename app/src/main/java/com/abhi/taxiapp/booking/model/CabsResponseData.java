package com.abhi.taxiapp.booking.model;

/**
 * Created by abhi on 15/05/17.
 */

public class CabsResponseData {

    private int carId;
    private String cabCategory;
    private String cabDescription;
    private String seatingCapacity;
    private String luggageCapacity;
    private String carImage;
    private int baseCharges;
    private int extraChargesPerKm;
    private int estimatedDistance;
    private int estimatedTimeHours;
    private int estimatedTimeMinutes;

    public CabsResponseData(int carId, String cabCategory, String cabDescription, String seatingCapacity, String luggageCapacity, String carImage, int baseCharges, int extraChargesPerKm, int estimatedDistance, int estimatedTimeHours, int estimatedTimeMinutes) {
        this.carId = carId;
        this.cabCategory = cabCategory;
        this.cabDescription = cabDescription;
        this.seatingCapacity = seatingCapacity;
        this.luggageCapacity = luggageCapacity;
        this.carImage = carImage;
        this.baseCharges = baseCharges;
        this.extraChargesPerKm = extraChargesPerKm;
        this.estimatedDistance = estimatedDistance;
        this.estimatedTimeHours = estimatedTimeHours;
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getCabCategory() {
        return cabCategory;
    }

    public void setCabCategory(String cabCategory) {
        this.cabCategory = cabCategory;
    }

    public String getCabDescription() {
        return cabDescription;
    }

    public void setCabDescription(String cabDescription) {
        this.cabDescription = cabDescription;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(String seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public String getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(String luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public int getBaseCharges() {
        return baseCharges;
    }

    public void setBaseCharges(int baseCharges) {
        this.baseCharges = baseCharges;
    }

    public int getExtraChargesPerKm() {
        return extraChargesPerKm;
    }

    public void setExtraChargesPerKm(int extraChargesPerKm) {
        this.extraChargesPerKm = extraChargesPerKm;
    }

    public int getEstimatedDistance() {
        return estimatedDistance;
    }

    public void setEstimatedDistance(int estimatedDistance) {
        this.estimatedDistance = estimatedDistance;
    }

    public int getEstimatedTimeHours() {
        return estimatedTimeHours;
    }

    public void setEstimatedTimeHours(int estimatedTimeHours) {
        this.estimatedTimeHours = estimatedTimeHours;
    }

    public int getEstimatedTimeMinutes() {
        return estimatedTimeMinutes;
    }

    public void setEstimatedTimeMinutes(int estimatedTimeMinutes) {
        this.estimatedTimeMinutes = estimatedTimeMinutes;
    }
}





