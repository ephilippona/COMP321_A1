
public class Website {
protected int index;
protected String host;
protected boolean isHSTS;
protected boolean isHSTSLong;
protected String SSLVersion;
protected String keyType;
protected String signatureAlgo;
protected int keySize;
protected boolean isHTTPS;

Website(int index, String host){
	this.index=index;
	this.host=host;
	this.isHSTS = false;
	this.isHSTSLong = false;
	this.SSLVersion = null;
	this.keyType = null;
	this.signatureAlgo = null;
	this.keySize = -1;
	this.isHTTPS = true;
}

String getHost(){
	return this.host;
}

int getIndex(){
	return this.index;
}

void setIsHSTS(boolean hstsBool){
	this.isHSTS = hstsBool;
}

void setIsHSTSLong(boolean hstsLongBool){
	this.isHSTSLong = hstsLongBool;
}

void setSSLVersion(String version){
	this.SSLVersion = version;
}

public void setKeyType(String keyType) {
	this.keyType = keyType;
}

public void setKeySize(int keySize) {
	this.keySize = keySize;
}

public void setSignatureAlgo(String signatureAlgo) {
	this.signatureAlgo = signatureAlgo;
}

public void setHTTPS(boolean isHTTPS) {
	this.isHTTPS = isHTTPS;
}

void printWebsiteInfo(){
	System.out.println(this.index + " => " + this.host);
	System.out.println("Is HTTPS           : " + this.isHTTPS);
	System.out.println("Is HSTS            : " + this.isHSTS);
	System.out.println("Is HSTS Long       : " + this.isHSTSLong);
	System.out.println("SSL Version        : " + this.SSLVersion);
	System.out.println("Public Key Type    : " + this.keyType);
	System.out.println("Public Key Size    : " + this.keySize);
	System.out.println("Server's Signature : " + this.signatureAlgo);
}

}
