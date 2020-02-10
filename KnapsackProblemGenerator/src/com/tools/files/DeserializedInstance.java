package com.tools.files;

import java.io.File;
import java.io.IOException;

import com.models.Item;
import com.models.Knapsack;

public class DeserializedInstance {
	
	public DeserializedInstance(String path) throws IOException{
		startDeserialization(path);		
	}
	
	private void startDeserialization(String path) throws IOException{
		
		File instanceFile = new File(path);
		if(!instanceFile.isFile()) {
			throw new IOException("Invalid path");
		}
		
		System.out.println("Deserializing file "+path);
	}
	
	public Knapsack getKnapsack() {
		return knapsack;
	}	

	public Item[] getItems() {
		return items;
	}
	
	private Knapsack knapsack;
	private Item[] items;
}
