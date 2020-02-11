package com.tools.files;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.models.Item;
import com.models.Knapsack;

public class SerializedInstance {
	//TODO: Here we are going to generate and serialize the problem instance, and save it to the instance folder.
	public static String saveInstance(Knapsack knapsack, Item[] items) throws IOException{
		
		//	Look for the next instance id in the settings .txt	
		File instancesFile = new File(System.getProperty("user.dir")+"\\configurations\\instances.txt");
		FileReader fr = new FileReader(instancesFile);
	    char [] a = new char[50];
	    fr.read(a);	    
	    
	    StringBuilder line = new StringBuilder();
	    for(char c : a) {
	    	if(c > 47 && c < 58)
	    		line.append(c);
	    }
	    
	    int nextVal = Integer.parseInt(line.toString())+1;
	    fr.close();
	    
	    FileWriter writer = new FileWriter(instancesFile);
		writer.write("instances:"+nextVal);
		writer.flush();
		writer.close();
		
		//	Create new instance file
		File file = new File(System.getProperty("user.dir")+"\\instances\\in_"+nextVal+".txt");
		if(file.createNewFile()) {
			writer = new FileWriter(file);
			
			writer.write("Knapsack weight cap = "+knapsack.WEIGHT_CAP+"\n");
			writer.write("\nItems:\n");
			writer.write("i\tw\tv\n");
			for(int i = 0; i < items.length; i++) {
				writer.write(i+"\t"+items[i].getWeight()+"\t"+items[i].getValue()+"\n");
			}			
			writer.flush();
			writer.close();
		}else {
			throw new IOException("Error while creating file "+file);
		}
		
		return "in_"+nextVal+".txt";
	}
	
}
