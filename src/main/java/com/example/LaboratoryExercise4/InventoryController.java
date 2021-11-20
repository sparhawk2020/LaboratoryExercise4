package com.example.LaboratoryExercise4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SessionAttributes({"id","desc","errMsg","ans"})
@RequestMapping
@Controller
public class InventoryController {

//    DatabaseService service1;

    @Autowired
    Categoryservice service;

//    @Autowired
//    Connection1 con;


    //a mapping when someone enters file
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategorypage(ModelMap model,@RequestParam(defaultValue = "") String id) throws ClassNotFoundException, SQLException {


    //    Class.forName("com.mysql.jdbc.Driver");
    //    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");



        model.addAttribute("todos", service.retrieveTodos());


        List<Category> filteredTodos = new ArrayList<Category>();

        filteredTodos = (List) model.get("todos");

        if(filteredTodos.size()>0) {

            model.put("id", filteredTodos.get(0).getCatcode());


            model.put("desc", filteredTodos.get(0).getCatdesc());

        }

      //  service1 = new DatabaseService(con1);

    //    model.addAttribute("todos", service1.display());



        return "category";


    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCategoryPage2(ModelMap model) throws ClassNotFoundException, SQLException {

        model.addAttribute("todos", service.retrieveTodos());


        List<Category> filteredTodos = new ArrayList<Category>();

        filteredTodos = (List) model.get("todos");

        if(filteredTodos.size()>0) {

            model.put("id", filteredTodos.get(0).getCatcode());


            model.put("desc", filteredTodos.get(0).getCatdesc());


        }

    //    Class.forName("com.mysql.jdbc.Driver");
    //    Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");



     //   service1 = new DatabaseService(con1);

     //   model.addAttribute("todos", service1.display());




        return "category";




    }

    @RequestMapping(value ="/add-todo", method = RequestMethod.GET)
    public String showtodopage(){
        return "catser";
    }


    @RequestMapping(value ="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String catcode, @RequestParam String catdesc){


        service.addTodo(catcode,catdesc);


        model.clear();
        return "redirect:/category";
    }


    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model,  @RequestParam(defaultValue = "") String id){

            model.put("id", id);

           // model.addAttribute("category",service.retrieve(id));

            Category x = service.retrieve(id);

           model.put("id",x.getCatcode());
           model.put("desc", x.getCatdesc());



        return "catedit";
    }



    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String showUpdate(ModelMap model,  @RequestParam String catcode, @RequestParam String catdesc) {

        String iid = (String) model.get("id");

         service.deleteTodo(iid);

         service.addTodo(catcode,catdesc);


          return "redirect:/";

    }



    @RequestMapping(value ="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam String id,  @RequestParam(defaultValue = "") String ans ) throws SQLException, ClassNotFoundException {




        if(ans.equals("true")){

            model.put("ans","That is great");
        }


        service.deleteTodo(id);




        model.clear();
        return "redirect:/";
    }

}