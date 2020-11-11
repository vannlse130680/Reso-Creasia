package com.creasia.ui.outlet;

public class Outlets {
    String image;
    String locationName;
    String code;
    String outletName;
    String timeAccess;
    String location;
    String status;

    public Outlets() {

    }

    public Outlets(String image, String locationName, String code, String outletName, String timeAccess, String location, String status) {
        this.image = image;
        this.locationName = locationName;
        this.code = code;
        this.outletName = outletName;
        this.timeAccess = timeAccess;
        this.location = location;
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getTimeAccess() {
        return timeAccess;
    }

    public void setTimeAccess(String timeAccess) {
        this.timeAccess = timeAccess;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
