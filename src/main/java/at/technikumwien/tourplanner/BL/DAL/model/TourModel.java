package at.technikumwien.tourplanner.BL.DAL.model;

import lombok.*;
import java.util.regex.Pattern;
import java.util.regex.Pattern;


//this class is the model for the database
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TourModel {

    private int tourId;
    private String name;
    private String description;
    private String totaldistance;
    private String totalduration;
    private String locationfrom;
    private String locationto;
    private String transporttype;

    public TourModel(int tourId) {
        this.tourId = tourId;
    }

    public TourModel(int tourId, String name) {
        this.tourId = tourId;
        this.name = name;
    }

    public TourModel(String name) {
        this.name = name;
    }
}

