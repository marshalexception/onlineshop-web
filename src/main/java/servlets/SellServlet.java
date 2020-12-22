package servlets;

import services.FotoReadListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/sell"}, asyncSupported = true)
@MultipartConfig(maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*30)
public class SellServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final AsyncContext asyncContext = request.startAsync();
        ServletInputStream inputStream = request.getInputStream();
        inputStream.setReadListener(new FotoReadListener(asyncContext));

        /**
        final String title = request.getParameter("title");
        final String description = request.getParameter("description");
        final String price = request.getParameter("price");

        final Part part = request.getPart("foto");
        String path = part.getSubmittedFileName();
        File file = new File(path);
        OutputStream os = new FileOutputStream(file);
        try {
            InputStream is = part.getInputStream();
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = is.read(b)) != -1) os.write(b, 0, i);
        } catch (Exception ex) {
            throw new ServletException(ex.getMessage());
        }

        response.setContentType("text/html;charset=UTF-8");

    	final PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");

        out.println("<br>Hochgeladener Artikel: " + title);
        out.println("<br>Beschreibung: " + description);
        out.println("<br>Preis: " + price);
        out.println("<br>Bild: " + part.getSubmittedFileName());

        out.println("</body>");
        out.println("</html>");
         */
    }
}
