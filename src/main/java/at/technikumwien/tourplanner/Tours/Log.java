package at.technikumwien.tourplanner.Tours;

import java.util.Date;

public class Log {
    private int id;
    private Date date;
    private Double duration; //duration in hours
    private Double distance;
    private Integer rating; // rating is an integer between 1 and 10, the higher the number, the better the rating

    /**
     * additional features
     **/
    private Double avgSpeed;
    private Integer teamSize;
    private String modeOfTransportation;
    private String weatherCondition;
    private String mood;
    private String report;
    private int tourId;

    public Log() {}

    public Log(int id, Date date, Double duration, Double distance, Integer rating, Double avgSpeed, Integer teamSize, String modeOfTransportation, String weatherCondition, String mood, String report, int tourId) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.rating = rating;
        this.avgSpeed = avgSpeed;
        this.teamSize = teamSize;
        this.modeOfTransportation = modeOfTransportation;
        this.weatherCondition = weatherCondition;
        this.mood = mood;
        this.report = report;
        this.tourId = tourId;
    }

    public Log(Log log) {
        this.id = log.id;
        this.date = log.date;
        this.duration = log.duration;
        this.distance = log.distance;
        this.rating = log.rating;
        this.avgSpeed = log.avgSpeed;
        this.teamSize = log.teamSize;
        this.modeOfTransportation = log.modeOfTransportation;
        this.weatherCondition = log.weatherCondition;
        this.mood = log.mood;
        this.report = log.report;
        this.tourId = log.tourId;
    }

    public Log(Date date, Double duration, Double distance, Integer rating, Double avgSpeed, Integer teamSize, String modeOfTransportation, String weatherCondition, String mood, String report, int tourId) {
        this.id = 0;
        this.date = date;
        this.duration = duration;
        this.distance = distance;
        this.rating = rating;
        this.avgSpeed = avgSpeed;
        this.teamSize = teamSize;
        this.modeOfTransportation = modeOfTransportation;
        this.weatherCondition = weatherCondition;
        this.mood = mood;
        this.report = report;
        this.tourId = tourId;
    }

    public String toString() {
        return "[ date: "+this.date+", duration: "+this.duration+", distance: "+this.distance+", rating: "+this.rating
                +", avgSpeed: "+this.avgSpeed+", teamSize: "+this.teamSize+" modeOfTransportation: "+this.modeOfTransportation
                +", weatherCondition: "+this.weatherCondition+", mood: "+this.mood+", report: "+this.report+" ]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Integer getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(Integer teamSize) {
        this.teamSize = teamSize;
    }

    public String getModeOfTransportation() {
        return modeOfTransportation;
    }

    public void setModeOfTransportation(String modeOfTransportation) {
        this.modeOfTransportation = modeOfTransportation;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReport() {
        return report;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }
}
