package com.warehouse.javacode.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Salaryminus {
    private String minusid;

    private String salaryid;

    private String minusname;

    private BigDecimal minusmoney;

    private Integer dlt;

    private Date createtime;

    private Date updatetime;
    
    private String remark;
    

    public String getMinusid() {
        return minusid;
    }

    public void setMinusid(String minusid) {
        this.minusid = minusid == null ? null : minusid.trim();
    }

    public String getSalaryid() {
        return salaryid;
    }

    public void setSalaryid(String salaryid) {
        this.salaryid = salaryid == null ? null : salaryid.trim();
    }

    public String getMinusname() {
        return minusname;
    }

    public void setMinusname(String minusname) {
        this.minusname = minusname == null ? null : minusname.trim();
    }

    public BigDecimal getMinusmoney() {
        return minusmoney;
    }

    public void setMinusmoney(BigDecimal minusmoney) {
        this.minusmoney = minusmoney;
    }

    public Integer getDlt() {
        return dlt;
    }

    public void setDlt(Integer dlt) {
        this.dlt = dlt;
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
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}