package at.technikumwien.tourplanner.BL.DAL.dto;

import lombok.*;


//this class is the model for the database
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourDto {

    private String description;
    private float distance;
    private String from;
    private String picture;
    private String name;
    private String time;
    private String to;
    private String type;

}

