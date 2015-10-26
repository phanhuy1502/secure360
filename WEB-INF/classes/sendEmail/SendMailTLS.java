package sendEmail;

import java.util.Properties;
import injectionDetection.Injection;
import java.util.ArrayList;
import injectionDetection.Config;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;


public class SendMailTLS {

	public static boolean sendEmail (String receiverAddress, ArrayList<Injection> requestCheckResult){

		if (requestCheckResult == null) return false;

		final String username = "secure360.send.email@gmail.com";
		final String password = "phanhuy1502";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("phanhuy1502.email.service@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiverAddress));
			message.setSubject("Your Request Check Result");
			String body  = "Hi,"
				+ "\n\nThe file you checked has " + requestCheckResult.size() + " injections. Attached is the generated report."
				+ "\n\nBest regards,\n\n";


			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(body);

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = Config.getRootDirectory() + "report/report.pdf";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Report.pdf");
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			Transport.send(message);

			return true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}