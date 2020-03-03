package servlet;

import entity.User;
import service.UserHiberService;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        boolean flag = UserHiberService.getInstance().addUser(new User(name, password));

        if(flag) {
            resp.sendRedirect("http://localhost:8080/list");
        } else {
            req.setAttribute("message", "User already exist");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/error.jsp");
            requestDispatcher.forward(req, resp);
        }

        resp.setStatus(200);
    }
}
