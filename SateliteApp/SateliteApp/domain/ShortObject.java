package pl.ug.SateliteApp.SateliteApp.domain;

public class ShortObject {

    private String name;
    private String launchDate;
    private String country;
    private String tleLine1;
    private String tleLine2;
    private String latitude;
    private String longitude;

    private String NORADCatId;

    public ShortObject() {
    }

    public ShortObject(String name, String launchDate, String country, String tleLine1, String tleLine2, String NORADCatId) {
        this.name = name;
        this.launchDate = launchDate;
        this.country = country;
        this.tleLine1 = tleLine1;
        this.tleLine2 = tleLine2;
        this.NORADCatId = NORADCatId;
    }


    public String getNORADCatId() {
        return NORADCatId;
    }

    public void setNORADCatId(String NORADCatId) {
        this.NORADCatId = NORADCatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTleLine1() {
        return tleLine1;
    }

    public void setTleLine1(String tleLine1) {
        this.tleLine1 = tleLine1;
    }

    public String getTleLine2() {
        return tleLine2;
    }

    public void setTleLine2(String tleLine2) {
        this.tleLine2 = tleLine2;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}