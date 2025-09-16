package fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap4;

import com.fasterxml.jackson.databind.ObjectMapper;
import fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap4.model.Gender;
import fit.iuh.se.ltwww_java_28082025_tuan02_dangnguyentienphat.baitap4.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userJsonServlet", urlPatterns = {"/user", "/user-json"})
public class UserJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        // lay gia tri tu request
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Gender gender = req.getParameter("gender").equalsIgnoreCase(Gender.MALE.toString())
                ? Gender.MALE : Gender.FEMALE;

        User user = new User("1", name, password, email, gender);
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);

        PrintWriter out = resp.getWriter();
        out.println(userJson);
        out.flush();
        out.close();
    }
}
