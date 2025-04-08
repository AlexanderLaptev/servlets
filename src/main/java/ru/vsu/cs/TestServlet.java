package ru.vsu.cs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> allPeople = PersonRepository.INSTANCE.findAll();
        req.setAttribute("people", allPeople);
        req.getRequestDispatcher("/people.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = "";
        if (req.getParameter("save") != null) action = "save";

        switch (action) {
            case "save" -> {
                long id = Long.parseLong(req.getParameter("id"));
                String name = req.getParameter("name");
                int age = Integer.parseInt(req.getParameter("age"));
                Person p = new Person(id, name, age);
                PersonRepository.INSTANCE.save(p);
            }
            default -> throw new RuntimeException("unknown action");
        }
        resp.sendRedirect("/test");
    }
}
