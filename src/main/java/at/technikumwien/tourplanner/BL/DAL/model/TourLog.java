package at.technikumwien.tourplanner.BL.DAL.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TourLog {

    private int logid;
    private int tourid_fk;
    private String datetime;
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
}
