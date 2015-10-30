import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.security.cert.Certificate;

import java.io.FileWriter;
import java.io.IOException;

public class Survey {

	public static int monthInSeconds = 2592000;
	
	public static void main(String[] args) {
		//AccessCSV Test=new AccessCSV();
		//ArrayList<Website> websites=Test.getWebsites();
		ArrayList<Website> websites = new ArrayList<Website>() ;
		
		websites.add(new Website(0,"www.facebook.com"));
		SSLSocket socket;
		int port = 443;			//standard port for SSL websites
		int timeout = 3000; 	//3 second timeout 
		PrintWriter requestWriter = null;
		BufferedReader socketInputStream = null;
		String socketLine;
		int maxAge;
		
		for(Website w: websites){
			
			String host = w.getHost();
			
			try{
				
				socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
				socket.connect(new InetSocketAddress(host, port), timeout);
				
				requestWriter= (new PrintWriter(new OutputStreamWriter(socket.getOutputStream())));
				requestWriter.println("GET / HTTP/1.1");
				requestWriter.println("Host: " + host);
				requestWriter.println("Accept: */*");
				requestWriter.println("User-Agent: Test");
				requestWriter.println(""); // needed to end a request
				requestWriter.flush();	
				
				//Test.write(w.getIndex()+"",host);
				
				
				socketInputStream = new BufferedReader (new InputStreamReader(socket.getInputStream()));
				
				while((socketLine = socketInputStream.readLine()) != null){
//					System.out.println(socketLine);

					if(socketLine.isEmpty()){
					}
					else if(socketLine.contains("Strict-Transport-Security")){
						//HSTS is supported by w
						w.setIsHSTS(true);

						//Check this line for the "max-age"
						int maxAgePosition = socketLine.indexOf("max-age");
						maxAgePosition = maxAgePosition + 8;
						
						String[] ageTemp = socketLine.split("max-age=");
						ageTemp = ageTemp[1].split(";"); //split the strung at the end of max-age value
						maxAge = Integer.parseInt(ageTemp[0]);

						//Check if maxAge is bigger than a month
						if(maxAge > monthInSeconds){
							w.setIsHSTSLong(true);
						}else{
							w.setIsHSTSLong(false);

						}
					}
					requestWriter.close();
					socketInputStream.close();
					
					SSLSession session = socket.getSession();
					if(session != null){
						w.setSSLVersion(session.getProtocol());
					
						//Get the public key type 
						Certificate certif = session.getPeerCertificates()[0];
						w.setKeyType(certif.getPublicKey().getAlgorithm());
						
						//Get string size from the Public Key algorithm information
						String temp = certif.getPublicKey().toString().split(", ")[1];
						temp = temp.split(" ")[0];
						w.setKeySize(Integer.parseInt(temp));
						
						//Get the server's signature  
						temp = certif.toString().split("Signature Algorithm: ")[1].split("with")[0];
						w.setSignatureAlgo(temp);
					}

				}
				

			}	
			catch(Exception e){
				
			}
			w.printWebsiteInfo();
		}
		
		
		
		
	}

}
