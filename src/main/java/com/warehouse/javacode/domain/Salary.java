package com.warehouse.javacode.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Salary {
    private String id;

    private String stuffid;

    private BigDecimal should;

    private BigDecimal actual;

    private BigDecimal dayoff;

    private BigDecimal quantity;

    private BigDecimal balance;

    private String remark;

    private Integer dlt;

    private Integer year;

    private Integer month;

    private Date createtime;

    private Date updatetime;
    
    private BigDecimal shouldplus;
    
    private BigDecimal shouldminus;
    
    private BigDecimal history;

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

    public BigDecimal getShould() {
        return should;
    }

    public void setShould(BigDecimal should) {
        this.should = should;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public BigDecimal getDayoff() {
        return dayoff;
    }

    public void setDayoff(BigDecimal dayoff) {
        this.dayoff = dayoff;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public BigDecimal getShouldplus() {
		return shouldplus;
	}

	public void setShouldplus(BigDecimal shouldplus) {
		this.shouldplus = shouldplus;
	}

	public BigDecimal getShouldminus() {
		return shouldminus;
	}

	public void setShouldminus(BigDecimal shouldminus) {
		this.shouldminus = shouldminus;
	}

	public BigDecimal getHistory() {
		return history;
	}

	public void setHistory(BigDecimal history) {
		this.history = history;
	}
}