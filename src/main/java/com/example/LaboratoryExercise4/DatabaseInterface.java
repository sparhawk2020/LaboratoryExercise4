package com.example.LaboratoryExercise4;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseInterface {


   // public void add(Student stud) throws ClassNotFoundException, SQLException;
   // public Student edit(Student stud, String snumber) throws SQLException, ClassNotFoundException;
   // public void delete(String snumber) throws SQLException;
    public List<Category> display() throws ClassNotFoundException, SQLException;

}
