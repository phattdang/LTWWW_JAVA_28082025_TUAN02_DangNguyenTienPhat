package fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap1.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author : user664dntp
 * @mailto : phatdang19052004@gmail.com
 * @created : 10/09/2025, Wednesday
 **/

@WebServlet(
        name = "LoginServlet",
        urlPatterns = "/user/login"
)
public class LoginServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String htmlFilePath = req.getServletContext().getRealPath("/login.html");
        if(htmlFilePath == null){
            out.println("Html page not found!!!");
            return;
        }

        String htmlContent = Files.readString(Paths.get(htmlFilePath));
        out.println(htmlContent);

        out.close();
    }

    @Override
    public void destroy() {

    }
}
