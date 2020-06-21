
package com.example.finalproject.model.detail_info;

import com.squareup.moshi.Json;

public class DetailItem {

    @Json(name = "agelimit")
    private String agelimit;
    @Json(name = "bookingplace")
    private String bookingplace;
    @Json(name = "contentid")
    private Long contentid;
    @Json(name = "contenttypeid")
    private Long contenttypeid;
    @Json(name = "discountinfofestival")
    private String discountinfofestival;
    @Json(name = "eventenddate")
    private Long eventenddate;
    @Json(name = "eventplace")
    private String eventplace;
    @Json(name = "eventstartdate")
    private Long eventstartdate;
    @Json(name = "placeinfo")
    private String placeinfo;
    @Json(name = "playtime")
    private String playtime;
    @Json(name = "program")
    private String program;
    @Json(name = "spendtimefestival")
    private String spendtimefestival;
    @Json(name = "sponsor1")
    private String sponsor1;
    @Json(name = "sponsor1tel")
    private String sponsor1tel;
    @Json(name = "sponsor2tel")
    private String sponsor2tel;
    @Json(name = "subevent")
    private String subevent;
    @Json(name = "usetimefestival")
    private String usetimefestival;

    public String getAgelimit() {
        return agelimit;
    }

    public void setAgelimit(String agelimit) {
        this.agelimit = agelimit;
    }

    public String getBookingplace() {
        return bookingplace;
    }

    public void setBookingplace(String bookingplace) {
        this.bookingplace = bookingplace;
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

    public String getDiscountinfofestival() {
        return discountinfofestival;
    }

    public void setDiscountinfofestival(String discountinfofestival) {
        this.discountinfofestival = discountinfofestival;
    }

    public Long getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(Long eventenddate) {
        this.eventenddate = eventenddate;
    }

    public String getEventplace() {
        return eventplace;
    }

    public void setEventplace(String eventplace) {
        this.eventplace = eventplace;
    }

    public Long getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(Long eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public String getPlaceinfo() {
        return placeinfo;
    }

    public void setPlaceinfo(String placeinfo) {
        this.placeinfo = placeinfo;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSpendtimefestival() {
        return spendtimefestival;
    }

    public void setSpendtimefestival(String spendtimefestival) {
        this.spendtimefestival = spendtimefestival;
    }

    public String getSponsor1() {
        return sponsor1;
    }

    public void setSponsor1(String sponsor1) {
        this.sponsor1 = sponsor1;
    }

    public String getSponsor1tel() {
        return sponsor1tel;
    }

    public void setSponsor1tel(String sponsor1tel) {
        this.sponsor1tel = sponsor1tel;
    }

    public String getSponsor2tel() {
        return sponsor2tel;
    }

    public void setSponsor2tel(String sponsor2tel) {
        this.sponsor2tel = sponsor2tel;
    }

    public String getSubevent() {
        return subevent;
    }

    public void setSubevent(String subevent) {
        this.subevent = subevent;
    }

    public String getUsetimefestival() {
        return usetimefestival;
    }

    public void setUsetimefestival(String usetimefestival) {
        this.usetimefestival = usetimefestival;
    }

}
