import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.util.*;

public class ReadXml {
	static Document doc;
	
	/**TODO be able to read in a value pair & select ouput based on second criteria */
	
	//Empty Constructor
	public ReadXml(){}
	
	//Override Constructor
	public ReadXml(String FilePath){
		//TODO
	}
	
	//HELPER METHODS
	public Document openXml(String filePath){
		//TODO
		return null;
	}
	
	public ArrayList<String> readText(String tag1){
		//TODO:
		return null;
	}
	
	public ArrayList<String> readTextTwoTags(String tag1, String tag2){
		ArrayList<String> tempArray = new ArrayList<String>();
		NodeList childList = doc.getElementsByTagName(tag1);
		for(int i=0; i<childList.getLength(); i++ ){ //iterate through all <field>
			if( childList.item(i).hasChildNodes() ){ // if has child(ren)
				NodeList childChildren = childList.item(i).getChildNodes();
				for (int j=0;j<childChildren.getLength(); j++ ){ //iterate through all <field> children
					if( childChildren.item(j) != null){
						if(childChildren.item(j).getNodeName().equals(tag2)){
							tempArray.add( childChildren.item(j).getTextContent() );
						}
					}
				}	
			}
		}
		return tempArray;
	}// end getChildChildText
	
	protected static ArrayList<StringPair> readTextPairThreeTags (String tag1, String tag2, String tag3){
		ArrayList<StringPair> tempArray = new ArrayList<StringPair>();
		boolean hasTag2=false; //default value of hasTag2
		boolean hasTag3=false; //default value of hasTag3
		String tempValueTag2 = "";
		String tempValueTag3 = "";
		NodeList childList = doc.getElementsByTagName(tag1);

		for(int i=0; i<childList.getLength(); i++ ){ //iterate through all <tag1>
			if( childList.item(i).hasChildNodes() ){ // if has child(ren)
				NodeList childChildren = childList.item(i).getChildNodes();
				for (int j=0;j<childChildren.getLength(); j++ ){ //iterate through all <tag1> children
					if( childChildren.item(j) != null){
						if(childChildren.item(j).getNodeName().equals(tag2)){
							tempValueTag2 = childChildren.item(j).getTextContent();
							hasTag2=true;
						}else if(childChildren.item(j).getNodeName().equals(tag3)){
							tempValueTag3 = childChildren.item(j).getTextContent();
							hasTag3=true;
						}
					}
				}
				if( hasTag2||hasTag3 ){
					tempArray.add( new StringPair( tempValueTag2 ,tempValueTag3 ) );
					//reset values
					hasTag2=false;
					hasTag3=false;
					tempValueTag2 ="";
					tempValueTag3 ="";
				}
			}
		}
		return tempArray;
	}// end getChildChildText
	
	public void closeXml(){
		//TODO
	}
	
}//END CLASS
