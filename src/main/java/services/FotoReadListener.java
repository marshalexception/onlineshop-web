package services;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

public class FotoReadListener implements ReadListener {

    private AsyncContext asyncContext;

    public FotoReadListener(AsyncContext asyncContext) {
        this.asyncContext = asyncContext;
    }

    @Override
    public void onDataAvailable() throws IOException {
        final HttpServletRequest request = (HttpServletRequest) asyncContext.getRequest();
        final HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();

        OutputStream os = null;
        InputStream is = null;
        PrintWriter out = null;

        try {
            final Part part = request.getPart("foto");
            os = new FileOutputStream(part.getSubmittedFileName());
            is = part.getInputStream();
            out = response.getWriter();

            byte[] b = new byte[1024];
            int i = 0;
            while ((i = is.read(b)) != -1) os.write(b, 0, i);
            os.flush();
            out.write("[FotoService] Success");
            asyncContext.complete();
        } catch (Exception e) {
            out.write("[FotoService] Error while executing FotoService: " + e.getMessage());
        } finally {
            try {
                os.close();
                is.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAllDataRead() throws IOException {
        asyncContext.complete();
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        asyncContext.complete();
    }
}
