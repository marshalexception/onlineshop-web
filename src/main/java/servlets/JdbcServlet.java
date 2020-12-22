package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JdbcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String jdbcProperties = getInitParameter("jdbc_properties");
        ServletContext servletContext = getServletContext();
        InputStream inputStream = servletContext.getResourceAsStream(jdbcProperties);

        Properties properties = new Properties();
        properties.load(inputStream);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        out.println("<br>driver: " + properties.getProperty("driver"));
        out.println("<br>url: " + properties.getProperty("url"));
        out.println("<br>username: " + properties.getProperty("username"));
        out.println("<br>password: " + properties.getProperty("password"));
        out.println("</body>");
        out.println("</html>");
    }
}
