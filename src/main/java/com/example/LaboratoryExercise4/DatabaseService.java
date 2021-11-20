package com.example.LaboratoryExercise4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService implements DatabaseInterface {

   Connection con;

    public DatabaseService(Connection con) {
        this.con = con;
    }

    @Override
    public List<Category> display() throws ClassNotFoundException, SQLException {

        //create an array list that will contain the data recovered
        List<Category> Catlist = new ArrayList<Category>();

        String quer1 = "Select * from category";
        PreparedStatement query = con.prepareStatement(quer1);



        ResultSet rs = query.executeQuery();



        Category obj1;

        //display records if there is data;

        while (rs.next()) {

            obj1 = new Category(rs.getString("catcode"), rs.getString("catdesc"));

            Catlist.add(obj1);
        }



        return Catlist;





    }
}




