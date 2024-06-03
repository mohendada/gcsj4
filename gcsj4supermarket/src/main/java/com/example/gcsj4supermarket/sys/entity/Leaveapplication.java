package com.example.gcsj4supermarket.sys.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author li
 * @since 2024-05-30
 */
public class Leaveapplication implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String applicant;

    private String approval;

    private LocalDateTime btime;

    private LocalDateTime etime;

    private String reason;

    private String yn;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }
    public LocalDateTime getBtime() {
        return btime;
    }

    public void setBtime(LocalDateTime btime) {
        this.btime = btime;
    }
    public LocalDateTime getEtime() {
        return etime;
    }

    public void setEtime(LocalDateTime etime) {
        this.etime = etime;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getYn() {
        return yn;
    }

    public void setYn(String yn) {
        this.yn = yn;
    }

    @Override
    public String toString() {
        return "Leaveapplication{" +
            "applicant=" + applicant +
            ", approval=" + approval +
            ", btime=" + btime +
            ", etime=" + etime +
            ", reason=" + reason +
            ", yn=" + yn +
        "}";
    }
}
