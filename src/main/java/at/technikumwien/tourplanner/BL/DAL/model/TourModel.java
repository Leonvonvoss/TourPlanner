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

    private String tourId;
    private String description;
    private float distance;
    private String from;
    private String picture;
    private String name;
    private String time;
    private String to;
    private String type;

}

