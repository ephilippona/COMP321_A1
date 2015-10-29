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

	public static void main(String[] args) {
		
		ArrayList<Website> websites = new ArrayList<Website>() ;
		
		SSLSocket socket;
		int port = 443;			//standard port for SSL websites
		int timeout = 3000; 	//3 second timeout 
		PrintWriter requestWriter = null;
		BufferedReader socketInputStream = null;
		String socketLine;
		
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
				
				socketInputStream = new BufferedReader (new InputStreamReader(socket.getInputStream()));
				
				while((socketLine = socketInputStream.readLine()) != null){
					if(socketLine.isEmpty())
						return;
					
				}
				
			}catch(Exception e){
				
			}
		}
		
		
		
		
	}

}
