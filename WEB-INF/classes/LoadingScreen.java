import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoadingScreen extends HttpServlet {
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {

      // Get input request
      if (request.getParameterMap().containsKey("file-name")) {
         request.setAttribute("fileName", request.getParameter("file-name"));
      }
      if (request.getParameterMap().containsKey("email")) {
         request.setAttribute("email", request.getParameter("email"));
      }

      response.setContentType("text/html");
      request.getRequestDispatcher("/views/html/loading-screen.jsp").include(request,response);
   }
}