package com.warehouse.javacode.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Salaryplus {
    private String plusid;

    private String salaryid;

    private String plusname;

    private BigDecimal plusmoney;

    private Integer dlt;

    private Date createtime;

    private Date updatetime;
    
    private String remark;

    public String getPlusid() {
        return plusid;
    }

    public void setPlusid(String plusid) {
        this.plusid = plusid == null ? null : plusid.trim();
    }

    public String getSalaryid() {
        return salaryid;
    }

    public void setSalaryid(String salaryid) {
        this.salaryid = salaryid == null ? null : salaryid.trim();
    }

    public String getPlusname() {
        return plusname;
    }

    public void setPlusname(String plusname) {
        this.plusname = plusname == null ? null : plusname.trim();
    }

    public BigDecimal getPlusmoney() {
        return plusmoney;
    }

    public void setPlusmoney(BigDecimal plusmoney) {
        this.plusmoney = plusmoney;
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