package com.bfiejdasz.fleet_manager_rest_api.entity;

import com.bfiejdasz.fleet_manager_rest_api.LocalDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "positions", schema = "moloft", catalog = "postgres")
public class PositionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_positions")
    private long idPositions;
    @Basic
    @Column(name = "rideid")
    private Long rideid;
    @Basic
    @Column(name = "x_cord")
    private Double xCord;
    @Basic
    @Column(name = "y_cord")
    private Double yCord;
    @Basic
    @Column(name = "time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "rideid", referencedColumnName = "id_rides", insertable=false, updatable=false)
    @JsonIgnore
    private RidesEntity positionsByRideid;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getTime() {
        return time;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public void setTime(LocalDateTime time) {
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
