package com.bellinfo.advanced.repository;

import com.bellinfo.advanced.Model.User;

import java.sql.*;
import java.util.Properties;

public class UserRepository {
    private static final String CREATE_SQL = "CREATE TABLE userdetails ( name VARCHAR, email varchar)";
    private static final String INSERT_SQL = "INSERT INTO userdetails VALUES(?,?)";
    private static final String VALIDATE_SQL= "select exists (select 1 from pg_tables where schemaname='public' and tablename='userdetails')";
    private static final String URL = "jdbc:postgresql://localhost:5432/bellinfosep";
    Connection con = null;
    void getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            Properties prop = new Properties();
            prop.setProperty("user","postgres");
            prop.setProperty("password","srilatha9");
            con = DriverManager.getConnection(URL,prop);
            // return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createUserDetails() throws SQLException {
        getConnection();
        PreparedStatement psValidate = con.prepareStatement(VALIDATE_SQL);
        ResultSet rs = psValidate.executeQuery();
        boolean isTableExsist = false;
        while(rs.next()){
            isTableExsist = rs.getBoolean(1);
        }
        if(isTableExsist){
            System.out.println("Hey ..Table already created in your schema, so...skiping it");
        }else{
            PreparedStatement ps = con.prepareStatement(CREATE_SQL);
            boolean isCreated = ps.execute();
            System.out.println("Hey Table has been created");
        }
    }
    public int insertUserDetails(User d){
        getConnection();
        int count = 0;
        try {
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            ps.setString(1,d.getName());
            ps.setString(2,d.getEmail());
            count = ps.executeUpdate();
            System.out.println("Record created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}

