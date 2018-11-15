package com.bellinfo.advanced.controller;
import com.bellinfo.advanced.Model.User;


import com.bellinfo.advanced.repository.UserRepository;



import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.SQLException;

public class HelloWorldServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("user");
        String email = req.getParameter("email");
        User user  = new User();
        user.setName(name);
        user.setEmail(email);
        UserRepository ur = new UserRepository();
        try {
            ur.createUserDetails();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int count = ur.insertUserDetails(user);
        if(count == 0){
            String failed = "Hey "+ name + " we failed to store your info for tech reasons. Please try again. Sorry for the trouble caused";
            req.setAttribute("failed", failed);
        }else{
            String welcome = "Hey ..."+ name +" welcome to advanced java world. Your "+ email + " has been registered";
            req.setAttribute("res", welcome);
        }
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/result.jsp");
        rd.forward(req,resp);
    }
}
