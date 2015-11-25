import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HomeScreen extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
      response.setContentType("text/html");
      request.getRequestDispatcher("/views/html/index.jsp").include(request,response);
   }
}