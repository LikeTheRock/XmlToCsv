import java.util.ArrayList;


public class DataObject {
	protected String name ="";
	protected String path =null;
	protected ArrayList<String> dataElements = new ArrayList<String>();

	//Empty constructor
	DataObject(){}

	//Override constructor
	DataObject(String Name){
		name=Name;
	}
	//Override constructor
	DataObject(String Name, ArrayList<String> al){
		name=Name;
		dataElements = al; //TODO: verify that this works
	}
	//Override constructor
	DataObject(ArrayList<String> al){
		dataElements = al; //TODO: verify that this works
	}
	//Override constructor
	DataObject(String Name, String Path){
		name=Name;
		path = Path;
	}
	//Override constructor
	DataObject(String Name, String Path, ArrayList<String> dataElements){
		name=Name;
		path = Path;
		dataElements.addAll(dataElements);
	}

	//HELPER METHODS
	protected void setName(String newName){
		name = newName;
	}
	public String getName(){
		return name; 
	}
	public void setPath(String newName){
		path = newName;
	}
	public String getPath(){
		return path; 
	}
	public void setElements( ArrayList<String> newElements ){
		if(dataElements.isEmpty()){
			dataElements = newElements;//TODO: verify this will work properly, it broke in tyler project
		}else{
			dataElements.addAll(newElements);
		}
	}
	public ArrayList<String> getElements(){
		return dataElements;
	}
	public void addDataElement(String s){
		dataElements.add(s);
	}

}
