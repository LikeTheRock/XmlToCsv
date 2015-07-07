import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class CustomXmlPull {
	static ArrayList<String> whiteList = new ArrayList<String>();
	static ArrayList<DataContainer> data = new ArrayList<DataContainer>();
	static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	public static void main(String[] args) {
		//VARIABLES
		String userInput="";
		Scanner scan = new Scanner(System.in);

		//GET FOLDER PATH
		System.out.print("Enter the root folder name or directory: ");
		userInput = scan.nextLine();
//		System.out.println(userInput);//debug
		getFolder(userInput);

		System.out.println("Enter the tags you want to read the text of, separated by a space. Type [exit] to quit.");
		while(!( userInput.equals("no")||userInput.equals("No")||userInput.equals("N")) ){
			//GET USER INPUT
			userInput = scan.nextLine();
			if( ( userInput.equals("no")||userInput.equals("No")||userInput.equals("exit")) ){
				break;
			}
			//System.out.println(userInput);//debug
			//DO SOMETHING WITH USER INPUT
			getTextXDeep(userInput);
		}
		System.out.println();

		scan.close();
		Object someArrayOrDataObject = null;//TODO: declare this as something helpful
		printDataToCSV(someArrayOrDataObject);

	}//===============================================================END MAIN=============================================================================================

	private static void printDataToCSV(Object someArrayOrDataObject) {
		// TODO Auto-generated method stub

	}

	/**Will parse the userInput into tags separated by spaces.
	 * If bad input is entered will, ask for correct input, with an example
	 * Will grab the text data after searching n tags deep. n will be based on the input
	 * TODO: could add: whitelist &| non-text grab
	 * 
	 * @param userInput
	 */
	private static void getTextXDeep(String userInput) {
		ArrayList<String> args = new ArrayList<String>();
		ArrayList<String> argsCopy = new ArrayList<String>();
		ArrayList<NodeList> children = new ArrayList<NodeList>();
		int notChildren = 0;
//		ArrayList<Integer> childIndexMarkers = new 	ArrayList<Integer>();
		// Parse the userInput into tags separated by spaces.
		while (userInput.contains(" ") ){
			args.add( userInput.substring(0, userInput.indexOf(" ")) );
			userInput=userInput.substring( userInput.indexOf(" ")+1, userInput.length());
		}
		args.add(userInput);
		argsCopy.addAll( args) ;
		// TODO If bad input is entered will, ask for correct input, with an example
		// TODO Will grab the text data after searching n tags deep. n will be based on the input
//		System.out.println("Data.size(): "+ data.size() );//debug
		for (int i = 0; i < data.size(); i++) {
			//open & read X deep
			argsCopy.addAll( args );
			Document doc = openDoc(data.get(i).getPath());
			NodeList temp = doc.getElementsByTagName(args.get(0)); 
			children.add( temp );
			argsCopy.remove(0);
			while( !argsCopy.isEmpty() ){
				for (int j = notChildren; j < children.size() ; j++) {
					children.addAll( getChilrenMatchingTag(children.get(j), argsCopy.get(0)));
				}
				argsCopy.remove(0);
				
				if(argsCopy.isEmpty()){
					System.out.print("bee");
					for (int j2 = notChildren; j2 < children.size(); j2++) {//TODO: no boops, therefore notCHildrensize==childrensize90
						System.out.println("-boop");
						data.get(i).createNewDO(args.get(args.size()-1), getTextFromNodes(children.get(j2) ) );
					}
				}
				notChildren=children.size();
			}
			System.out.println(data.get(i).toString());
		}
	}
	
	private static ArrayList<String> getTextFromNodes (NodeList nodes){
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i < nodes.getLength(); i++) {
			array.add( nodes.item(i).getTextContent() );
		}
		return array;
	}
	
	private static ArrayList<NodeList> getChilrenMatchingTag (NodeList parentList, String tag){
		ArrayList<NodeList> children = new ArrayList<NodeList>();
//		System.out.println(parentList.getLength());//debug
		for (int j = 0; j < parentList.getLength(); j++) {
			if(parentList.item(j).getNodeName().equals(tag)){
				children.add( parentList.item(j).getChildNodes() );
			}
		}
		return children;
	}

	/**Will populate the Names and Paths of the data array (of some data object)
	 * Will get all child folders and all child files within the root folder
	 * Will print out a list of folders presant in the object if the folder is not found
	 */
	private static void getFolder(String rootFolder) {
		ArrayList<String> tempNames = new ArrayList<String>();
		ArrayList<String> tempPaths = new ArrayList<String>();
		File folder = new File(rootFolder);
		File[] listOfFiles = folder.listFiles();
		try {
			if ( listOfFiles.length>1 ){ //for many folder/files
				tempPaths = getPaths(rootFolder);
				for (int i = 0; i < tempPaths.size() ; i++) {
					tempNames.add( convertPathToName(tempPaths.get(i) )); //set paths
					//CREATE NEW DATA ELEMENT, DATA CONTAINER AND DATA OBJECT
					data.add( new DataContainer(tempNames.get(i), tempPaths.get(i)) );
				}
//				System.out.println(tempPaths);//debug
//				System.out.println(tempNames);//debug

			} else{ //for a single file
				//TODO: for a single file
			}
		}catch ( NullPointerException e ){
			System.out.println("Folder Not Found! List of available folders: ");
			//TODO: print available folders
		}

	}

	/**Sets a white list of paths to search for
	 * Deletes all non whitelisted items after searching for them all. 
	 * TODO: could implement a fuzzy lookup
	 * @param whiteListUI
	 */
	private static void getWhiteList(String whiteListUI ){
		//TODO: could implement
	}

	/**Removes a list of paths
	 * 
	 * @param blackListUI
	 */
	private static void setBlackList(String blackListUI ){
		//TODO: could implement
	}

	/**Clears the white list
	 * 
	 */
	private static void clearWhiteList(){
		//TODO: could implement
	}

	/**Will parse the userInput into tags separated by spaces.
	 * If bad input is entered will, ask for correct input, with an example
	 * Will grab the text data after searching n tags deep. n will be based on the input
	 * Will concat all of the concatList tags under the tags and store in the data object
	 * 
	 * @param tags : list of tags to grab data from the xml(s)
	 * @param concatList : List of tags that user wishes to combine into one string
	 */
	private static void concatText (String tags, String concatList){
		//TODO: could implement
	}

	private static void readAsPair(String tags ){
		//TODO:
	}

	/**Takes a single path and checks it for problem characters
	 * 	This includes the following : %, \, /, and .
	 * 
	 * @param unCheckedPath
	 * @return
	 */
	private static String validatePath(String unCheckedPath){
		//TODO
		return null;
	}

	/**Returns an array of the absolute paths of the files within the root folder
	 * 
	 * @param rootFolder
	 * @return array of the absolute paths
	 */
	protected static ArrayList<String> getPaths(String rootFolder){
		ArrayList<String> tempArray = new ArrayList<String>();
		File folder = new File(rootFolder);
		File[] listOfFiles = folder.listFiles();
		String temp = null;

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				temp = listOfFiles[i].getAbsolutePath();
				temp = temp.substring(temp.lastIndexOf(rootFolder), temp.length() );
				tempArray.add(temp);
			} else if (listOfFiles[i].isDirectory()) {
				tempArray.addAll( getPaths(listOfFiles[i].toString() ));
			}
		}
		return tempArray;
	}//end getPaths

	/**Given a path, removes all parent directories and removes anything after a "."
	 * 
	 * @param string Path
	 * @return string name
	 */
	private static String convertPathToName(String string) {
		while (string.contains("\\")){
			string = string.substring(string.indexOf("\\")+1, string.length());
		}
		if(string.contains(".")){ //TODO: Verify that when opening file doesnt need anything after the "."
			int dotIndex = string.lastIndexOf(".");
			string = string.substring(0, dotIndex);
		}
		return string;
	}
	/**
	 * Creates a new document object from the passed path and returns it.
	 * @param path
	 * @param factory
	 * @return doc
	 */
	protected static Document openDoc (String path ){
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(path);
			return doc;
		} catch (ParserConfigurationException e) {
			System.out.println("Error on path:"+path);
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("Error on path:"+path);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error on path:"+path);
			e.printStackTrace();
		}
		return null;
	}//end openDoc
	
}//END CLASS 
