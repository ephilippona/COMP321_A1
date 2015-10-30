

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class ReadCSV {
	private ArrayList<Website> websites;	
ReadCSV(){
	websites=new <Website>ArrayList();
	run(getStartIndex(29070117),getStartIndex(26225497));
}


public ArrayList<Website> getWebsites(){
	return this.websites;}

  public void run(int index1,int index2) {

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
			if((1<=index_value)&&(1000>=index_value)||(index1<=index_value)&&((index1+9999)>=index_value)||((index2<=index_value)&&((index2+9999)>=index_value))){
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
}

