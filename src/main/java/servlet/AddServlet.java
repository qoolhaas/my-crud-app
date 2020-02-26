package servlet;

import entity.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
        resp.setStatus(200);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        UserService.getInstance().createTable();
        if(!UserService.getInstance().addUser(new User(name, password))) {
            req.setAttribute("message", "User already exist!");
        } else {
            req.setAttribute("message", "User has been added!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/successAdd.jsp");
        requestDispatcher.forward(req, resp);
        resp.setStatus(200);
    }
}
