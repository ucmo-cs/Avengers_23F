package com.avengers.project.repository;

import com.avengers.project.domain.CMuser;
import com.avengers.project.domain.ChangeRequest;
import com.mysql.cj.jdbc.StatementImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ChangeRequestDao {
    private Statement statement;

    public ChangeRequestDao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        // Connect to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/avengerstest" , "root", "root");
        System.out.println("Database connected");

        // Create a statement
        statement = connection.createStatement();
    }

    public List<ChangeRequest> getViewableRequest(CMuser cMuser){
        List<ChangeRequest> list = new ArrayList<ChangeRequest>();
        String status;
        switch(cMuser.getSecuritylvl()){
            case "user": status = "open";
                break;
            case "application": status = "frozen";
                break;
            case "department": status = "application";
                break;
            case "operations": status = "department";
                break;
            case "admin": status = "operations";
                break;
            default: status = null;
        }
        String query = String.format("select * from changerequest where id='"+cMuser.getId()
                +"' or  status = '"+status+"';");
        System.out.println(query);
        try {
            System.out.println("Trying to execute query: " + query);
            ResultSet resultSet = statement.executeQuery(query);


            // 4. Parse the Result Set
            while (resultSet.next()) {
                // 5. Create Java Objects
                // Collect and store - change#, changeType, reason#, reasontype, appID, status
                ChangeRequest request = new ChangeRequest();
                request.setChangeNumber(resultSet.getInt("change_number"));
                request.setChangeType(resultSet.getString("change_type"));
                request.setReasonNumber(resultSet.getString("reason_number"));
                request.setReasonType(resultSet.getString("reason_type"));
                request.setApplicationID(resultSet.getString("applicationID"));
                request.setStatus(resultSet.getString("status"));

                // 6. Add Java Objects to List
                list.add(request);
                System.out.println("Change Request #:"+request.getChangeNumber()+" has been added to the list");
            }
            System.out.println("Success");
            // Close resources
            resultSet.close();
        }
        catch(SQLException e){
            System.out.println("SQL Error executing query: " + query);
        }
        return list;
    }

    public void updateStatusWithStatus(int changeNumber, String status){
        String query = " Update changerequest " +
                "set status = '"+status+"' " +
                "where change_number = '"+changeNumber+"';";
        try{
            System.out.println("Trying to execute query: " + query);
            statement.executeUpdate(query);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("SQL Error executing query: " + query);
        }
    }
    public void updateImplStart(int cNumber, String startTime){
        String query = " Update changerequest " +
                "set actual_impl_start = '"+startTime+"' " +
                "where change_number = '"+cNumber+"';";
        try{
            System.out.println("Trying to execute query: " + query);
            statement.executeUpdate(query);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("SQL Error executing query: " + query);
        }
    }
    public void updateSingleChangeRequest(int cNumber, String value, String column){
        String query = " Update changerequest " +
                "set "+column+" = '"+value+"' " +
                "where change_number = '"+cNumber+"';";
        try{
            System.out.println("Trying to execute query: " + query);
            statement.executeUpdate(query);
            System.out.println("Success");
        } catch (SQLException e) {
            System.out.println("SQL Error executing query: " + query);
        }
    }
    public void cleanRequest(){
    String query = "Delete from changerequest where status = null;";
        try{
        System.out.println("Trying to execute query: " + query);
        statement.executeUpdate(query);
        System.out.println("Success");
    } catch (SQLException e) {
        System.out.println("SQL Error executing query: " + query);
    }
}
}
