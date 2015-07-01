
public class StringPair {
	String nameSP = "";
	String valueSP ="";
	
	//EMPTY CONSTRUCTOR
	StringPair(){};
	
	//OVERRIDE CONSTRUCTOR
	StringPair(String newName, String newValue){
		nameSP=newName;
		valueSP=newValue;
	}
	
	//GETTER AND SETTER METHODS
	String getName(){
		return nameSP;
	}
	void setName(String newName){
		nameSP=newName;
	}
	String getValue(){
		return valueSP;
	}
	void setValue(String newValue){
		valueSP=newValue;
	}
	void setStringPiar(String newName, String newValue){
		nameSP=newName;
		valueSP=newValue;
	}
	
}//end class
