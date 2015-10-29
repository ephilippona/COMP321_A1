
public class Website {
private int index;
private String host;

Website(int index, String host){
	this.index=index;
	this.host=host;
}

String getHost(){
	return this.host;
}

int getIndex(){
	return this.index;
}

}
