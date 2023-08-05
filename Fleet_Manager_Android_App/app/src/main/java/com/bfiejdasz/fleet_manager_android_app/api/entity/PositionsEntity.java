package com.bfiejdasz.fleet_manager_android_app.api.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PositionsEntity {
    private long idPositions;
    private Long rideid;
    private Double xCord;
    private Double yCord;
    private String time;
    private RidesEntity positionsByRideid;

    public PositionsEntity(long idPositions, Long rideid, Double xCord, Double yCord, String time) {
        this.idPositions = idPositions;
        this.rideid = rideid;
        this.xCord = xCord;
        this.yCord = yCord;
        this.time = time;
    }

    public PositionsEntity() {}

    public long getIdPositions() {
        return idPositions;
    }

    public void setIdPositions(long idPositions) {
        this.idPositions = idPositions;
    }

    public Long getRideid() {
        return rideid;
    }

    public void setRideid(Long rideid) {
        this.rideid = rideid;
    }

    public Double getxCord() {
        return xCord;
    }

    public void setxCord(Double xCord) {
        this.xCord = xCord;
    }

    public Double getyCord() {
        return yCord;
    }

    public void setyCord(Double yCord) {
        this.yCord = yCord;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionsEntity that = (PositionsEntity) o;

        if (idPositions != that.idPositions) return false;
        if (rideid != null ? !rideid.equals(that.rideid) : that.rideid != null) return false;
        if (xCord != null ? !xCord.equals(that.xCord) : that.xCord != null) return false;
        if (yCord != null ? !yCord.equals(that.yCord) : that.yCord != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPositions ^ (idPositions >>> 32));
        result = 31 * result + (rideid != null ? rideid.hashCode() : 0);
        result = 31 * result + (xCord != null ? xCord.hashCode() : 0);
        result = 31 * result + (yCord != null ? yCord.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }


}
