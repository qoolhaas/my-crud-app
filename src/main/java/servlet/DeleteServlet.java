package servlet;

import service.UserMainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.commons.lang3.math.NumberUtils;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTemp = req.getParameter("id");
        Long id = 0L;
        if(NumberUtils.isCreatable(idTemp)) {
            id = Long.parseLong(idTemp);
        }

        UserMainService.getInstance().deleteUserById(id);

        resp.sendRedirect("http://localhost:8080/list");
        resp.setStatus(200);
    }
}
