package at.technikumwien.tourplanner.viewmodel;

import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.managers.TourManager;
import javafx.beans.property.*;
import javafx.scene.control.Alert;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddLogViewModel {

    private final ObjectProperty<Date> logDate = new SimpleObjectProperty<>();
    private final StringProperty logDuration = new SimpleStringProperty("");
    private final DoubleProperty logRating = new SimpleDoubleProperty();
    private final StringProperty logRatingLabel = new SimpleStringProperty();
    private final DoubleProperty logDifficulty = new SimpleDoubleProperty();
    private final StringProperty logDifficultyLabel = new SimpleStringProperty();
    private final StringProperty logComment = new SimpleStringProperty("");


    private TourManager manager;

    private int tourId;

    private TourLog selectedLog;


    public void initData(TourManager manager, int tourId) {
        this.manager = manager;
        this.tourId = tourId;
        logRating.addListener(obs -> {
            var rating = 0;
            rating = (int) logRating.get();
            logRatingLabel.set(String.valueOf(rating));
        });
    }

    public boolean saveTourLog() {
        if(validateFields()) {
               manager.createTourLog(
                        new TourLog(
                                tourId,
                                logDuration.get(),
                                logComment.get(),
                                (int) logRating.get(),
                                (int) logDifficulty.get()));
            return true;
        } else {
            var alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please fill out all required fields correctly");
            alert.show();
            return false;
        }
    }


    private boolean validateFields() {
        var check = true;
        /*(logDate.getValue() == null)
        {
            check = false;
        }*/
        if(logDuration.get().isEmpty())
        {
            check = false;
        }

        return check;
    }

    public Property<LocalDate> logDateProperty() {
        Date date = new Date(String.valueOf(logDate));
        return (Property<LocalDate>) date;
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }


    public StringProperty logDurationProperty() {
        return logDuration;
    }


    public DoubleProperty logRatingProperty() {
        return logRating;
    }

    public StringProperty logRatingLabelProperty() {
        return logRatingLabel;
    }

    public StringProperty logCommentProperty() {
        return logComment;
    }

    public DoubleProperty logDifficulty() {
        return logDifficulty;
    }

    public StringProperty logDifficultyLabel() {
        return logDifficultyLabel;
    }

}
