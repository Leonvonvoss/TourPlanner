package at.technikumwien.tourplanner.Tours;

public class Tour {
    private int id;

    private String name;
    private String description;
    private String info;
    private Double distance;
    private String origin;
    private String destination;
    private String modeOfTransportation;

    public Tour(int id, String name, String description, String info, Double distance, String origin, String destination, String modeOfTransportation) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.info = info;
        this.distance = distance;
        this.origin = origin;
        this.destination = destination;
        this.modeOfTransportation = modeOfTransportation;
    }

    public Tour(Tour tour) {
        this.id = tour.id;
        this.name = tour.name;
        this.description = tour.description;
        this.info = tour.info;
        this.distance = tour.distance;
        this.origin = tour.origin;
        this.destination = tour.destination;
        this.modeOfTransportation = tour.modeOfTransportation;
    }

    public Tour(String name, String description, String origin, String destination, String modeOfTransportation) {
        this.id = 0;
        this.name = name;
        this.description = description;
        this.info = null;
        this.distance = null;
        this.origin = origin;
        this.destination = destination;
        this.modeOfTransportation = modeOfTransportation;
    }

    public String toString() {
        String tour;
        tour = this.name;
        return tour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getModeOfTransportation() {
        return modeOfTransportation;
    }

    public void setModeOfTransportation(String modeOfTransportation) {
        this.modeOfTransportation = modeOfTransportation;
    }
}

