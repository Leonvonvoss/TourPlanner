package at.technikumwien.tourplanner.BL.DAL.dao;
import at.technikumwien.tourplanner.BL.DAL.dto.TourDto;
import at.technikumwien.tourplanner.BL.DAL.model.TourModel;
import at.technikumwien.tourplanner.BL.DAL.postgres.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TourDao {

    public Optional<TourModel> get(TourModel tourModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour 
                WHERE tourid = ?
                """)) {

            statement.setString( 1, tourModel.getTourId() );
            ResultSet resultSet = statement.executeQuery();
            if( resultSet.next() ) {
                return Optional.of( new TourModel(
                        resultSet.getString(1),
                        resultSet.getString( 2 ),
                        resultSet.getFloat( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 ),
                        resultSet.getString( 9 )
                ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }

    public Collection<TourModel> getAll() {
        ArrayList<TourModel> result = new ArrayList<>();
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour 
                """)
        ) {
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                result.add( new TourModel(
                        resultSet.getString(1),
                        resultSet.getString( 2 ),
                        resultSet.getFloat( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 ),
                        resultSet.getString( 9 )
                ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public Collection<TourDto> getAllFromTourname(String tourname) {
        ArrayList<TourDto> result = new ArrayList<>();
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                SELECT *
                FROM tour
                WHERE name = ?;
                """)
        ) {
            statement.setString(1, tourname );
            ResultSet resultSet = statement.executeQuery();
            while( resultSet.next() ) {
                result.add( new TourDto(
                        resultSet.getString(1),
                        resultSet.getFloat( 2 ),
                        resultSet.getString( 3 ),
                        resultSet.getString( 4 ),
                        resultSet.getString( 5 ),
                        resultSet.getString( 6 ),
                        resultSet.getString( 7 ),
                        resultSet.getString( 8 )
                ) );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean save(TourModel TourModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                INSERT INTO tour
                (description, distance, from, picture, name, time, to, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                """ )) {
            statement.setString(1, TourModel.getDescription() );
            statement.setFloat( 2, TourModel.getDistance());
            statement.setString( 3, TourModel.getFrom());
            statement.setString( 4, TourModel.getPicture());
            statement.setString( 5, TourModel.getName());
            statement.setString( 6, TourModel.getTime());
            statement.setString( 7, TourModel.getTo());
            statement.setString( 8, TourModel.getType());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateTourDescription(TourModel userModel) {
        try ( PreparedStatement statement = DBConnection.getInstance().prepareStatement("""
                UPDATE tour
                SET description = ? WHERE name = ?;
                """ )) {
            statement.setString(1, userModel.getDescription() );
            statement.setString(1, userModel.getName() );
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}