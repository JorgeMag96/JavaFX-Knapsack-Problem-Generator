package com.tools.files;

import com.models.Item;
import com.models.Knapsack;

public class SerializedInstance {

	public SerializedInstance(Knapsack knapsack, Item[] items) {
		this.knapsack 	= knapsack;
		this.items 		= items;
	}
	
	public void saveInstance() {
		
	}
	
	public int getID() {
		return id;
	}
	
	private int id;
	private Knapsack knapsack;
	private Item[] items;
}
