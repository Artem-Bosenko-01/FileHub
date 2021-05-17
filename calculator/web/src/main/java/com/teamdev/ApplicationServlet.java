package com.teamdev;

import com.teamdev.booby.Booby;
import com.teamdev.booby.impl.BoobyCompilerFactoryImpl;
import com.teamdev.booby.impl.BoobyImpl;
import com.teamdev.booby.runtime.RuntimeEnvironment;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ApplicationServlet", value = "/app")
public class ApplicationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String input = request.getParameter("input");

        try (PrintWriter writer = response.getWriter()){
            StringBuilder builder = new StringBuilder();
            Booby compiler = new BoobyImpl(new BoobyCompilerFactoryImpl(builder));
            RuntimeEnvironment environment = RuntimeEnvironment.getInstance();
            compiler.execute(input,environment);
            writer.println("<p>"+"Result is = "+ builder +"</p>");
        }
    }
}
