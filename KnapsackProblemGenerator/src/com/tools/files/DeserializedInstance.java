package com.tools.files;

import com.models.Item;
import com.models.Knapsack;

public class DeserializedInstance {
	
	public DeserializedInstance(String path) {
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
