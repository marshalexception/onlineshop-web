package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/serverpush"})
public class ServerPushServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PushBuilder pushBuilder = req.newPushBuilder();
        if (pushBuilder != null) {
            pushBuilder.path("resources/img/tau.jpg")
                    .addHeader("Content-Type", "image/jpg")
                    .push();
        }
        try {
            PrintWriter responseWriter = resp.getWriter();
            responseWriter.write("<html><img src='resources/img/tau.jpg'></html>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
