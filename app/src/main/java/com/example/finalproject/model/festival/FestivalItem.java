
package com.example.finalproject.model.festival;


import com.squareup.moshi.Json;

public class FestivalItem {

    @Json(name = "addr1")
    private String addr1;
    @Json(name = "areacode")
    private Long areacode;
    @Json(name = "cat1")
    private String cat1;
    @Json(name = "cat2")
    private String cat2;
    @Json(name = "cat3")
    private String cat3;
    @Json(name = "contentid")
    private Long contentid;
    @Json(name = "contenttypeid")
    private Long contenttypeid;
    @Json(name = "createdtime")
    private Long createdtime;
    @Json(name = "eventenddate")
    private Long eventenddate;
    @Json(name = "eventstartdate")
    private Long eventstartdate;
    @Json(name = "firstimage")
    private String firstimage;
    @Json(name = "firstimage2")
    private String firstimage2;
    @Json(name = "mapx")
    private Double mapx;
    @Json(name = "mapy")
    private Double mapy;
    @Json(name = "mlevel")
    private Long mlevel;
    @Json(name = "modifiedtime")
    private Long modifiedtime;
    @Json(name = "readcount")
    private Long readcount;
    @Json(name = "sigungucode")
    private Long sigungucode;
    @Json(name = "tel")
    private String tel;
    @Json(name = "title")
    private String title;
    @Json(name = "addr2")
    private String addr2;

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public Long getAreacode() {
        return areacode;
    }

    public void setAreacode(Long areacode) {
        this.areacode = areacode;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public String getCat3() {
        return cat3;
    }

    public void setCat3(String cat3) {
        this.cat3 = cat3;
    }

    public Long getContentid() {
        return contentid;
    }

    public void setContentid(Long contentid) {
        this.contentid = contentid;
    }

    public Long getContenttypeid() {
        return contenttypeid;
    }

    public void setContenttypeid(Long contenttypeid) {
        this.contenttypeid = contenttypeid;
    }

    public Long getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Long createdtime) {
        this.createdtime = createdtime;
    }

    public Long getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(Long eventenddate) {
        this.eventenddate = eventenddate;
    }

    public Long getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(Long eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public String getFirstimage() {
        return firstimage;
    }

    public void setFirstimage(String firstimage) {
        this.firstimage = firstimage;
    }

    public String getFirstimage2() {
        return firstimage2;
    }

    public void setFirstimage2(String firstimage2) {
        this.firstimage2 = firstimage2;
    }

    public Double getMapx() {
        return mapx;
    }

    public void setMapx(Double mapx) {
        this.mapx = mapx;
    }

    public Double getMapy() {
        return mapy;
    }

    public void setMapy(Double mapy) {
        this.mapy = mapy;
    }

    public Long getMlevel() {
        return mlevel;
    }

    public void setMlevel(Long mlevel) {
        this.mlevel = mlevel;
    }

    public Long getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Long modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public Long getReadcount() {
        return readcount;
    }

    public void setReadcount(Long readcount) {
        this.readcount = readcount;
    }

    public Long getSigungucode() {
        return sigungucode;
    }

    public void setSigungucode(Long sigungucode) {
        this.sigungucode = sigungucode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

}