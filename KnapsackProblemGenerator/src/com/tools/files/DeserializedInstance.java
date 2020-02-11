package com.tools.files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.models.Item;
import com.models.Knapsack;

public class DeserializedInstance {
	
	public DeserializedInstance(String path) throws IOException{
		startDeserialization(path);
	}
	
	private void startDeserialization(String path) throws IOException{
		
		File instanceFile = new File(path);
		if(!instanceFile.isFile()) {
			throw new IOException("Invalid file");
		}
		else {
			System.out.println("Deserializing file "+path);
			
			Scanner s = new Scanner(instanceFile);
			
			// Knapsack weight
			String line = s.nextLine();
			if(line.contains("Knapsack")) {
				String weightInfo = line.split("=")[1];
				int knp_weight = Integer.parseInt(weightInfo.trim());
				this.knapsack = new Knapsack(knp_weight);
			}
			else {
				throw new IOException("Instance file got corrupted, please generate a new one.");
			}
			
			// Items
			List<Item> itemList = new ArrayList<>();
			boolean itemsLine = false;
			while(!itemsLine) {
				if(s.nextLine().contains("i	w	v")) {
					itemsLine = true;
				}
			}
			while(s.hasNext()) {
				line = s.nextLine();
				if(!line.isEmpty()) {
					String[] lineData = line.split("\t");
					itemList.add(new Item(Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2])));
				}
			}
			s.close();
			int i = 0;
			items = new Item[itemList.size()];
			for(Item item:itemList) {
				items[i] = item;
				i++;
			}
		}
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
