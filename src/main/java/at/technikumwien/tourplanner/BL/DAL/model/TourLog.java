package at.technikumwien.tourplanner.BL.DAL.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TourLog {

    private int logid;
    private int tourid_fk;
    private Date datetime;
    private String comment;
    private int difficulty;
    private int rating;
    private String totaltime;

    public TourLog(int logid) {
        this.logid = logid;
    }

    public TourLog(int logid, int tourid_fk) {
        this.logid = logid;
        this.tourid_fk = tourid_fk;
    }

    public TourLog( int tourid_fk, int id, Date datetime, String totaltime, Integer rating, String comment) {
        this.logid = id;
        this.tourid_fk = tourid_fk;
        this.datetime = datetime;
        this.totaltime = totaltime;
        this.rating = rating;
        this.comment = comment;
    }

    public TourLog(TourLog log) {
        this.tourid_fk = log.tourid_fk;
        this.logid = log.logid;
        this.datetime = log.datetime;
        this.totaltime = log.totaltime;
        this.rating = log.rating;
        this.comment = log.comment;
    }

    public TourLog(Date date, String totaltime, Integer rating, String comment, int tourid_fk) {
        this.logid = 0;
        this.datetime = datetime;
        this.totaltime = totaltime;
        this.rating = rating;
        this.comment = comment;
        this.tourid_fk = tourid_fk;
    }

    public TourLog(int tourId, String duration, String comment, int rating, int difficulty) {
        this.tourid_fk = tourId;
        this.totaltime = duration;
        this.comment = comment;
        this.rating = rating;
        this.difficulty = difficulty;
    }


    public String toString() {
        return "[ date: "+this.datetime+", duration: "+this.totaltime+
                ", rating: "+this.rating +", report: "+this.comment+" ]";
    }

    public int getId() {
        return logid;
    }

    public void setId(int id) {
        this.logid = logid;
    }

    public Date getDate() { return datetime; }

    public void setDate(Date datetime) {
        this.datetime = datetime;
    }

    public String getTotaltime() {
        return totaltime;
    }

    public void setTotalTime(String totaltime) {
        this.totaltime = totaltime;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReport(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public int getTourId() {
        return tourid_fk;
    }

    public void setTourId(int tourId) {
        this.tourid_fk = tourid_fk;
    }

}
