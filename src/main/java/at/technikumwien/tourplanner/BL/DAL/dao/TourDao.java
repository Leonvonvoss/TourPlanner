package at.technikumwien.tourplanner.BL.DAL.dao;

import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.DAL.postgres.DBConnection;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class TourDao {

    private static final Logger logger = LogManager.getLogger(TourDao.class);

    private static TourDao instance;

    public static TourDao getInstance() {
        if(instance == null) {
            instance = new TourDao();
        }
        return instance;
    }

    public List<TourModel> getAllTours() {
        ArrayList<TourModel> result = new ArrayList<>();

        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour 
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                result.add( new TourModel(
                        resultSet.getInt(1),
                        resultSet.getString( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 )
                ) );
            }
        } catch (SQLException throwables) {
            logger.error("Error: Selecting tours");
            throwables.printStackTrace();
        }
        return result;
    }

    public Optional<TourModel> getTourByTourId(TourModel tourModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour 
                WHERE tourid = ?
                """)) {

            statement.setInt( 1, tourModel.getTourId());

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return Optional.of( new TourModel(
                        resultSet.getInt(1),
                        resultSet.getString( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 )
                ) );
            }
        } catch (SQLException throwables) {
            logger.error("Error: Selecting tours");
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Collection<TourModel> getToursByName(TourModel tourModel) {
        ArrayList<TourModel> result = new ArrayList<>();

        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour
                WHERE name = ?;
                """)
        ) {
            statement.setString(1, tourModel.getName());

            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                result.add( new TourModel(
                        resultSet.getInt(1),
                        resultSet.getString( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 )
                ) );
            }
        } catch (SQLException throwables) {
            logger.error("Error: Selecting tours");
            throwables.printStackTrace();
        }
        return result;
    }

    public Collection<TourModel> getToursBySearchFilter(TourModel tourModel) {
        ArrayList<TourModel> result = new ArrayList<>();

        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour
                WHERE name LIKE ? OR UPPER(name) LIKE ? or LOWER(name) LIKE ?;
                """)
        ) {
            statement.setString(1, "%" + tourModel.getName() + "%");
            statement.setString(2, "%" + tourModel.getName().toUpperCase() + "%");
            statement.setString(3, "%" + tourModel.getName().toLowerCase() + "%");

            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                result.add( new TourModel(
                        resultSet.getInt(1),
                        resultSet.getString( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 )
                ) );
            }
        } catch (SQLException throwables) {
            logger.error("Error: Selecting tours");
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean saveTour(TourModel tourModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                INSERT INTO tour
                (name, description, totaldistance, totalduration, locationfrom, locationto, transporttype) VALUES (?, ?, ?, ?, ?, ?, ?);
                """ )) {

            statement.setString(1, tourModel.getName() );
            statement.setString( 2, tourModel.getDescription());
            statement.setString( 3, tourModel.getTotaldistance());
            statement.setString( 4, tourModel.getTotalduration());
            statement.setString( 5, tourModel.getLocationfrom());
            statement.setString( 6, tourModel.getLocationto());
            statement.setString( 7, tourModel.getTransporttype());
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            logger.error("Error: Saving tours");
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateTourById(TourModel tourModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                UPDATE tour
                SET name = ?, description = ?, totaldistance = ?, totalduration = ?, locationfrom = ?, locationto = ?, transporttype = ? WHERE tourid = ?;
                """ )) {

            statement.setString(1, tourModel.getName() );
            statement.setString( 2, tourModel.getDescription());
            statement.setString( 3, tourModel.getTotaldistance());
            statement.setString( 4, tourModel.getTotalduration());
            statement.setString( 5, tourModel.getLocationfrom());
            statement.setString( 6, tourModel.getLocationto());
            statement.setString( 7, tourModel.getTransporttype());

            statement.setInt(8, tourModel.getTourId() );
            statement.execute();

            return true;
        } catch (SQLException throwables) {
            logger.error("Error: Modyfing tours");
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean deleteTourById(TourModel tourModel) {

        TourLogDao tourLogDao = new TourLogDao();

        if(tourLogDao.deleteTourLogsOfTourByTourId(tourModel)) { //deletes all tourlogs with tourid of tourmodel
            try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                    DELETE FROM tour WHERE tourid = ?;
                """ )) {

                statement.setInt(1, tourModel.getTourId() );
                statement.execute();

            } catch (SQLException throwables) {
                logger.error("Error: Deleting tours");
                throwables.printStackTrace();
            }
            return true;
        }

        return false;
    }

}