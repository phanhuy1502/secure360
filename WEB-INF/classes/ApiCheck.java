import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import injectionDetection.Config.InjectionType;
import injectionDetection.InjectionDetection;
import injectionDetection.Config.InjectionType;
import injectionDetection.Injection;
import sendEmail.SendMailTLS;
import java.util.ArrayList;
import report.PdfGenerator;

public class ApiCheck extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
 
      // Set the response MIME type of the response message
      response.setContentType("text/html");

      // File list for selection
      ArrayList<String> fileName = new ArrayList<String>();
      try {
         File txtFolder = new File(this.getServletContext().getRealPath("/files"));
         for (File txt : txtFolder.listFiles()) {
            fileName.add(txt.getName());
         }
         request.setAttribute("files", fileName);
      } catch (NullPointerException e) {
         e.printStackTrace();
      }

      request.getRequestDispatcher("/views/html/api-check.jsp").include(request, response);
   }

   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
 
      // Set the response MIME type of the response message
      response.setContentType("text/html");

      // File list for selection
      ArrayList<String> fileName = new ArrayList<String>();
      try {
         File txtFolder = new File(this.getServletContext().getRealPath("/files"));
         for (File txt : txtFolder.listFiles()) {
            fileName.add(txt.getName());
         }
         request.setAttribute("files", fileName);
      } catch (NullPointerException e) {
         e.printStackTrace();
      }

      // Init the InjectionDetection object
      ServletContext cntxt = this.getServletContext();
      InjectionDetection checker = InjectionDetection.getInjectionDetection(cntxt);

      // Get input request
      String fileToCheck = "";
      String emailAddress = "";
      if (request.getParameterMap().containsKey("file-name")) {
         fileToCheck = request.getParameter("file-name");
      }
      if (request.getParameterMap().containsKey("email")) {
         emailAddress = request.getParameter("email");
      }
      
      // Check request
      ArrayList<Injection> requestCheckResult = null;
      if (fileToCheck != "") {
         requestCheckResult = checker.checkFile("/files/"+fileToCheck); 
         if (requestCheckResult == null) {
            request.setAttribute("error", "File not found!");
         }
         request.setAttribute("result", requestCheckResult);

         //Generate Report
         PdfGenerator.generateReport(requestCheckResult);
         //response.getWriter().println(PdfGenerator.generateReport(requestCheckResult));
      } 

      request.setAttribute("fileName", fileToCheck);
      request.setAttribute("email", emailAddress);
      request.getRequestDispatcher("/views/html/api-check.jsp").include(request, response);


      //Send email 
      if (emailAddress != "") {
         SendMailTLS.sendEmail(emailAddress, requestCheckResult);
      }
   }
}
