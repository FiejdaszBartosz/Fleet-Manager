package com.bfiejdasz.fleet_manager_rest_api.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "positions", schema = "moloft", catalog = "postgres")
public class PositionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_positions")
    private long idPositions;
    @Basic
    @Column(name = "rideid")
    private Long rideId;
    @Basic
    @Column(name = "x_cord")
    private Double xCord;
    @Basic
    @Column(name = "y_cord")
    private Double yCord;
    @Basic
    @Column(name = "time")
    private Timestamp time;

    public long getIdPositions() {
        return idPositions;
    }

    public void setIdPositions(long idPositions) {
        this.idPositions = idPositions;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionsEntity that = (PositionsEntity) o;

        if (idPositions != that.idPositions) return false;
        if (rideId != null ? !rideId.equals(that.rideId) : that.rideId != null) return false;
        if (xCord != null ? !xCord.equals(that.xCord) : that.xCord != null) return false;
        if (yCord != null ? !yCord.equals(that.yCord) : that.yCord != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPositions ^ (idPositions >>> 32));
        result = 31 * result + (rideId != null ? rideId.hashCode() : 0);
        result = 31 * result + (xCord != null ? xCord.hashCode() : 0);
        result = 31 * result + (yCord != null ? yCord.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
