package servlet;

import entity.User;
import org.apache.commons.lang3.math.NumberUtils;
import service.UserHiberService;

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
        Long id = 0L;

        if(NumberUtils.isCreatable(idTemp)) {
            id = Long.parseLong(idTemp);
        }

        boolean flag = UserHiberService.getInstance().editUser(new User(id, name, password));

        if(flag) {
            resp.sendRedirect("http://localhost:8080/list");
        } else {
            req.setAttribute("message", "Name already exist");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/error.jsp");
            requestDispatcher.forward(req, resp);
        }
        resp.setStatus(200);
    }
}
