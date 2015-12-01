

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Plot extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {

      response.setContentType("text/html;charset=UTF-8");
	  PrintWriter out = response.getWriter();
      BufferedReader is = null;
      BufferedReader es = null;
      //String commands = "whoami";
      String commands = "/home/phanhuy1502/spark-1.5.1-bin-hadoop2.6/bin/spark-submit  --master local[4] /home/phanhuy1502/Spark/Python/detection2.py";
      try
      {
          Process process;
          process = Runtime.getRuntime().exec(commands);
           String line;
          is = new BufferedReader(new InputStreamReader(process.getInputStream()));
          int i = 0;
          while((line = is.readLine()) != null) {
            out.println("<p>" + line + "</p");
            i++;
          }
          es = new BufferedReader(new InputStreamReader(process.getErrorStream()));
          i = 0;
          while((line = es.readLine()) != null) {
            out.println("<p>" + line + "</p");
            i++;
          }

          int exitCode = process.waitFor();
          if (exitCode == 0)
              out.println("<p> It worked </p>");
          else
              out.println("<p> Something bad happend. Exit code: " + exitCode + "</p>");
          
      } 
      catch(Exception e)
      {
      	   out.println("Something when wrong: " + e.getMessage());
          e.printStackTrace();
      } //catch
      finally
      {
          out.close();
		if (is != null)
              try { is.close(); } catch (IOException e) {}
          if (es != null)
              try { es.close(); } catch (IOException e) {}
      } 


   }
}