import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;

import anormallyDetection.Anormally;
import anormallyDetection.FileType;
import anormallyDetection.MaliciousFile;
import report.ReportParser;

public class CheckFile extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {

      // Set the response message's MIME type
      response.setContentType("text/html;charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      BufferedReader is = null;
      BufferedReader es = null;
      //String commands = "whoami";
      String commands = "/home/phanhuy1502/spark-1.5.1-bin-hadoop2.6/bin/spark-submit --class Secure360 --master local[4] /home/phanhuy1502/Spark/Scala/target/scala-2.10/secure-360_2.10-1.0.jar > error.txt";
      
      try
      {
          out.println("<!DOCTYPE html>");
          out.println("<html><head>");
          out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
          out.println("<title>Check File</title></head>");
          out.println("<body>");
          Process process;
          //Runtime.getRuntime().exec(dir);
          process = Runtime.getRuntime().exec(commands);
          /*String line;
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
          */
          ArrayList<MaliciousFile> t= ReportParser.ParseAnormallyFile();
          for (MaliciousFile f : t) {
            out.println("<p>" + f.getFileName() + "</p>");
            for (Anormally a : f.getAnormallyList()) {
              out.println("<p> Anormally at: " + a.getPosition() + 
                  "th header. Distance from nearest center: " + a.getDistance() + ". Ratio to avarage: "
                  + a.getExceedingRatio() + "</p>");
            }
          }
          ArrayList<FileType> l = ReportParser.ParseReportFile();
          for (FileType f: l) {
            out.println("<p>" + f.getName() + ": " + (f.isMalicious()? "malicious file </p>" : "normal file </p>"));
          }

          out.println("</body>");
          out.println("</html>");
      } //try
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
      } //finally

      //response.setContentType("text/html");
      //request.getRequestDispatcher("/views/html/checkfile.jsp").include(request,response);
   }
}