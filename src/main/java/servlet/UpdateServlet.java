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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTemp = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long id = Long.parseLong(idTemp);

        UserService.getInstance().editUser(new User(id, name, password));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/successUpdate.jsp");
        requestDispatcher.forward(req, resp);
        resp.setStatus(200);
    }
}
