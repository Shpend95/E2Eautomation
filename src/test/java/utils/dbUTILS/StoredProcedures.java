package utils.dbUTILS;

import java.sql.*;

public class StoredProcedures {
    public static String dbURL = "jdbc:mysql://127.0.0.1:3306/mount_hospital";
    public static String dbUsername = "root";
    public static String dbPassword = "Insert123@@";


    public static void addNewPatient() {
        String sql = "{CALL addNewPatients(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = DriverManager.getConnection(dbURL,dbUsername,dbPassword );
             CallableStatement callableStatement=connection.prepareCall(sql)) {

            callableStatement.setDate(1, Date.valueOf("1997-09-11"));
            callableStatement.setInt(2,99);
            callableStatement.setString(3,"12 Golc Street");
            callableStatement.setString(4, "Fernando");
            callableStatement.setString(5, "Alora");
            callableStatement.setString(6, "C+");
            callableStatement.setString(7, "feri@example.com");
            callableStatement.setString(8, "N/A");
            callableStatement.setString(9, "314-234-6612");

            callableStatement.execute();
            System.out.println("Patient added successfully");


        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    public static void makeAppointment() throws SQLException {
        Connection connection=DriverManager.getConnection(dbURL,dbUsername,dbPassword);
        CallableStatement callableStatement=connection.prepareCall("{CALL makeAppointmentByID(5, '2026-08-18', '11:30:00', 3, 'X-ray follow-up')}");
        callableStatement.execute();
        System.out.println("Appointment added successfully");
    }



    public static void main (String[]args) throws SQLException {
        addNewPatient();
        makeAppointment();
    }

}
