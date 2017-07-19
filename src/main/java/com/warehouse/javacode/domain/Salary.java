package com.warehouse.javacode.domain;

public class Salary {
    private String id;

    private String stuffid;

    private String pluss;

    private String minuss;

    private Double should;

    private Double actual;

    private String dayoff;

    private Double quantity;

    private String remark;

    private Integer dlt;

    private Integer year;

    private Integer month;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStuffid() {
        return stuffid;
    }

    public void setStuffid(String stuffid) {
        this.stuffid = stuffid == null ? null : stuffid.trim();
    }

    public String getPluss() {
        return pluss;
    }

    public void setPluss(String pluss) {
        this.pluss = pluss == null ? null : pluss.trim();
    }

    public String getMinuss() {
        return minuss;
    }

    public void setMinuss(String minuss) {
        this.minuss = minuss == null ? null : minuss.trim();
    }

    public Double getShould() {
        return should;
    }

    public void setShould(Double should) {
        this.should = should;
    }

    public Double getActual() {
        return actual;
    }

    public void setActual(Double actual) {
        this.actual = actual;
    }

    public String getDayoff() {
        return dayoff;
    }

    public void setDayoff(String dayoff) {
        this.dayoff = dayoff == null ? null : dayoff.trim();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDlt() {
        return dlt;
    }

    public void setDlt(Integer dlt) {
        this.dlt = dlt;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}