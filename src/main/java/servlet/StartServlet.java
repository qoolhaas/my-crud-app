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

@WebServlet("/")
public class StartServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("Start servlet");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getRequestURL().toString();
        System.out.println(str);
        //TODO эта часть кода работает по-разному и непредсказуемо
        //Изначально он посылал сюда все запросы УРЛ которые не были привязаны к конкретному сервлету, например файл со стилями
        //сейчас он не входит даже в ветку else
        if(str.equals("http://localhost:8080/")) {
            System.out.println("if");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/list");
            requestDispatcher.forward(req, resp);
        } else {
            System.out.println("else");
            /*RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
            requestDispatcher.forward(req, resp);*/
            resp.setContentType("text/html;charset=utf-8");
        }
        resp.setStatus(200);
    }
}
