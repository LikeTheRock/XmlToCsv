import java.util.ArrayList;


public class DataContainer {
	String name ="";
	String path =null;
	ArrayList<DataObject> DOs = new ArrayList<DataObject>();

	//Empty constructor
	DataContainer(){}

	//Override constructor
	DataContainer(String Name){
		name=Name;
	}
	//Override constructor
	DataContainer(String Name, String Path){
		name=Name;
		path = Path;
	}

	//HELPER METHODS
	public void setName(String newName){
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
	public void createNewDO(ArrayList<String> al){
		DOs.add(new DataObject(al));
	}
	public void createNewDO(String name, ArrayList<String> al){
		DOs.add(new DataObject(name, al));
	}
	
}
