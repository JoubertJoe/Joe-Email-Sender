package protocol;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Base64;
import java.util.Locale;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
/**
 * 
 * @author Joubert Joe Vieira Lellis
 *
 */
public class ProtocoloEmail {

	public String log;
	
	
	/**
	 * 
	 * @param emailBody - Corpo do email - Body of the Email
	 * @param emailSubject - Assunto do EMail - Subject of the Email
	 * @param emailSender - Remetente do email - Email Sender
	 * @param emailRecipient - Destinat�rio do Email - Email Recipiente
	 * @param emailHost - HOST (Gmail, Hotmail, Yahoo)
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public void enviar( String emailBody, String emailSubject, String emailSender,String emailRecipient,String emailHost, String password) throws IOException, UnknownHostException{
		
		SMTP mail = new SMTP(emailHost);
		// Protocolo - Protocol (Simple Mail Transpor Protocol)
		
		if (mail != null) {
			if (mail.send(emailSubject , emailBody, emailSender, emailRecipient, password)) {
				log = ("Email Enviado \n Mail Sent");
			} else {
				log = ("Falha ao conectar com servidor SMTP \n Connect to SMTP server failed!");
			}
		}
		System.out.println("Pronto. \n Done.");
	}
	
/**
 * 
 * @author Joubert Joe Vieira Lellis
 *
 */
	static class SMTP {
		private final static int SMTP_PORT = 587; // Porta padr�o SMTP - Default Port SMTP
		InetAddress mailHost;
		InetAddress localhost;
		BufferedReader in;
		PrintWriter out;

		/**
		 * 
		 * @param	host Host a ser usado - Host to be used. Ex: (gmail, hotmail, yahoo, etc...)
		 * @throws	UnknownHostException Tratamento de exce��o de Host - Host Exception
		 */
		public SMTP(String host) throws UnknownHostException {
			mailHost = InetAddress.getByName(host);
			localhost = InetAddress.getLocalHost();
			System.out.println("mailhost = " + mailHost);
			System.out.println("localhost= " + localhost);
			System.out.println("Contrutor SMTP pronto. /n SMTP constructor done\n");
		}
		
		/**
		 * 
		 * @param msgEmail		Mensagem contendo Assunto e corpo de email - Mensage includind subject and body of the email.
		 * @param from			Quem vai enviar o email - Who gonna send that Email.
		 * @param to			Quem est� recebendo o email - Who gonna recieve that email.
		 * @return				Booleana para saber se tudo ocorreu bem - Boolean to check if everythings is right.
		 * @throws IOException	Poss�veis erros - Possible errors.
		 */
		public boolean send(String msgAssunto, String msgEmail, String from, String to, String pass) throws IOException {
		//	SSLSocket smtpSocket;
			Socket smtpSocket;
			InputStream inn;
			OutputStream outt;
			
		// Criptografia 
			String user = Base64.getEncoder().encodeToString(from.getBytes("utf-8"));
			pass = Base64.getEncoder().encodeToString(pass.getBytes("utf-8"));
			
		//	smtpSocket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket(mailHost, SMTP_PORT);
			smtpSocket = new Socket(mailHost, SMTP_PORT);
			
			if (smtpSocket == null) {
				return false;
			}
			inn = smtpSocket.getInputStream();
			outt = smtpSocket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(inn));
			out = new PrintWriter(new OutputStreamWriter(outt), true);
			if (inn == null || outt == null) {
				System.out.println("Failed to open streams to socket.");
				return false;
			}
			String initialID = in.readLine();
			System.out.println(initialID);
			System.out.println("HELO ailton.eng.br");
			out.println("HELO ailton.eng.br");
			out.flush();
			String welcome = in.readLine();
			System.out.println(welcome);
			System.out.println("AUTH LOGIN");
			out.println("AUTH LOGIN");
			out.flush();
			out.println(user);
			out.flush();
			System.out.println(user);
			out.println(pass);
			out.flush();
			System.out.println(pass);
			
			System.out.println("MAIL From:<" + from + ">");
			out.println("MAIL FROM:<" + from + ">");
			out.flush();
			
			String senderOK = in.readLine();
			System.out.println(senderOK);
			
			System.out.println("RCPT TO:<" + to + ">");
			out.println("RCPT TO:<" + to + ">");
			out.flush();
			String recipientOK = in.readLine();
			System.out.println(recipientOK);
			
			System.out.println("DATA");
			out.println("DATA");
			out.flush();
			
			out.println("Subject: " +msgAssunto);
			out.flush();
			System.out.println(msgAssunto);
			
			out.println(msgEmail + "\n.\nQUIT\n");
			out.flush();
			System.out.println(msgEmail);
			
			System.out.println(".");
			
			out.flush();
			System.out.println("QUIT");
		//	out.println("QUIT");
			out.flush();
			String acceptedOK = in.readLine();
			String resposta;
			
			
			
			System.out.println(acceptedOK);
			/*
			do{
			   resposta = in.readLine();
			   
			   System.out.println(resposta);
			}while(resposta != null); 
			 */
			
			return true;
		}

	}

}
