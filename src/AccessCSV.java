

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class AccessCSV {
	private ArrayList<Website> websites;	
	
	AccessCSV(){
	websites=new ArrayList<Website>();
	read(getStartIndex(29070117),getStartIndex(26225497));
	}
	
	public void write(String ...text){
		
		try {
			  FileWriter output = new FileWriter("./src/output.csv",true); //the true will append the new data
			  String line="";
			  int counter=0;
			  for(String a:text){
				  counter++;
				  if(text.length!=counter)line+=a+",";
				  else line+=a+"\r\n";
				  
				  
			  }
			  output.write(line,0,line.length());
			  output.flush();
			  output.close();
			}
			catch(IOException e) {
			  e.printStackTrace();
			}
	}

	public ArrayList<Website> getWebsites(){
		return this.websites;}

	
	//Method to generate starting index
	private static int getStartIndex (int studentId)  {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance ("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md.update( new Integer ( studentId ).toString () . getBytes () );
		BigInteger bi = new BigInteger (1 , md.digest () );
		return bi.mod(new BigInteger ("9890")).multiply (new BigInteger("100")).intValue()+1000;
		}
	
  public void read(int index1,int index2) {

	String csvFile = "/src/top-1m.csv";
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {


		br = new BufferedReader(new FileReader(new File("").getAbsolutePath().concat(csvFile)));
		while ((line = br.readLine()) != null) {
			
			// use comma as separator
			String[] siteInfo = line.split(cvsSplitBy);
			int index_value=Integer.parseInt(siteInfo[0]);
			if(((2702<=index_value)&&((index2+9999)>=index_value))){
				websites.add(new Website(index_value,siteInfo[1]));
				System.out.println(siteInfo[0]+","+siteInfo[1]);}
		}

	

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }
  

}

