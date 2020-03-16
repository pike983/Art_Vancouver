package ca.bcit.comp3717.artvancouver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("descriptionofwork")
    @Expose
    private String descriptionofwork;
    @SerializedName("yearofinstallation")
    @Expose
    private String yearofinstallation;
    @SerializedName("photourl")
    @Expose
    private Photourl photourl;
    @SerializedName("sitename")
    @Expose
    private String sitename;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("geom")
    @Expose
    private Geom geom;
    @SerializedName("primarymaterial")
    @Expose
    private String primarymaterial;
    @SerializedName("ownership")
    @Expose
    private String ownership;
    @SerializedName("artists")
    @Expose
    private String artists;
    @SerializedName("registryid")
    @Expose
    private Integer registryid;
    @SerializedName("geo_local_area")
    @Expose
    private String geoLocalArea;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("neighbourhood")
    @Expose
    private String neighbourhood;
    @SerializedName("locationonsite")
    @Expose
    private String locationonsite;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescriptionofwork() {
        return descriptionofwork;
    }

    public void setDescriptionofwork(String descriptionofwork) {
        this.descriptionofwork = descriptionofwork;
    }

    public String getYearofinstallation() {
        return yearofinstallation;
    }

    public void setYearofinstallation(String yearofinstallation) {
        this.yearofinstallation = yearofinstallation;
    }

    public Photourl getPhotourl() {
        return photourl;
    }

    public void setPhotourl(Photourl photourl) {
        this.photourl = photourl;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Geom getGeom() {
        return geom;
    }

    public void setGeom(Geom geom) {
        this.geom = geom;
    }

    public String getPrimarymaterial() {
        return primarymaterial;
    }

    public void setPrimarymaterial(String primarymaterial) {
        this.primarymaterial = primarymaterial;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public Integer getRegistryid() {
        return registryid;
    }

    public void setRegistryid(Integer registryid) {
        this.registryid = registryid;
    }

    public String getGeoLocalArea() {
        return geoLocalArea;
    }

    public void setGeoLocalArea(String geoLocalArea) {
        this.geoLocalArea = geoLocalArea;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getLocationonsite() {
        return locationonsite;
    }

    public void setLocationonsite(String locationonsite) {
        this.locationonsite = locationonsite;
    }

}