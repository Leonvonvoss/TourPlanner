package at.technikumwien.tourplanner.BL.DAL.dao;

import at.technikumwien.tourplanner.BL.DAL.model.TourLog;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.DAL.postgres.DBConnection;
import lombok.AllArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Optional;
import java.sql.Timestamp;

@AllArgsConstructor
public class TourLogDao {

    private static TourLogDao instance;

    public static TourLogDao getInstance() {
        if(instance == null) {
            instance = new TourLogDao();
        }
        return instance;
    }

    public Collection<TourLog> getAllTourLogs() {
        ArrayList<TourLog> result = new ArrayList<>();

        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tourlog 
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add( new TourLog(
                        resultSet.getInt(1),
                        resultSet.getInt( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getInt( 5 ),
                        resultSet.getInt( 6 ),
                        resultSet.getString( 7 )
                ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Optional<TourLog> getTourLogByLogId(TourLog tourLog) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tourlog 
                WHERE logid = ?
                """)) {

            statement.setInt( 1, tourLog.getLogid());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return Optional.of( new TourLog(
                        resultSet.getInt(1),
                        resultSet.getInt( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getInt( 5 ),
                        resultSet.getInt( 6 ),
                        resultSet.getString( 7 )
                ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Collection<TourLog> getTourLogsOfTourByTourid(TourLog tourLog) {
        ArrayList<TourLog> result = new ArrayList<>();

        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tourlog
                WHERE tourid_fk = ?;
                """)
        ) {
            statement.setInt(1, tourLog.getTourid_fk());

            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                result.add( new TourLog(
                        resultSet.getInt(1),
                        resultSet.getInt( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getInt( 5 ),
                        resultSet.getInt( 6 ),
                        resultSet.getString( 7 )
                ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean saveTourLog(TourLog tourLog) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                INSERT INTO tourlog
                (tourid_fk, datetime, comment, difficulty, rating, totaltime) VALUES (?, ?, ?, ?, ?, ?);
                """ )) {

            statement.setInt(1, tourLog.getTourid_fk());
            /*Calendar calendar = Calendar.getInstance();
            statement.setDate(2, new java.sql.Date(calendar.getTimeInMillis()));*/
            statement.setTimestamp(2, Timestamp.from(Instant.now()));
            statement.setString( 3, tourLog.getComment());
            statement.setInt( 4, tourLog.getDifficulty());
            statement.setInt( 5, tourLog.getRating());
            statement.setString( 6, tourLog.getTotaltime());
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateTourLogById(TourLog tourLog) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                UPDATE tourlog
                SET tourid_fk = ?, comment = ?, difficulty = ?, rating = ?, totaltime = ? WHERE logid = ?;
                """ )) {

            statement.setInt(1, tourLog.getTourid_fk() );
            statement.setString( 2, tourLog.getComment());
            statement.setInt( 3, tourLog.getDifficulty());
            statement.setInt( 4, tourLog.getRating());
            statement.setString( 5, tourLog.getTotaltime());

            statement.setInt(6, tourLog.getLogid() );
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteTourLogById(TourLog tourLog) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                DELETE FROM tourlog WHERE logid = ?;
                """ )) {

            statement.setInt(1, tourLog.getLogid() );
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteTourLogsOfTourByTourId(TourModel tourModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                DELETE FROM tourlog WHERE tourid_fk = ?;
                """ )) {

            statement.setInt(1, tourModel.getTourId() );
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteTourLogsOfTourByTourId(TourLog tourLog) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                DELETE FROM tourlog WHERE tourid_fk = ?;
                """ )) {

            statement.setInt(1, tourLog.getTourid_fk() );
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}