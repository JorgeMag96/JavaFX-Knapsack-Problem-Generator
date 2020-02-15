package com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Knapsack implements Serializable{
	private static final long serialVersionUID = -1064905928087474608L;

	public Knapsack(int max_cap) {
		this.WEIGHT_CAP = max_cap;
		items = new ArrayList<>();
	}
	
	public boolean addItem(Item item) {
		
		if(WEIGHT_CAP - (current_weight + item.getWeight()) < 0) {
			return false;
		}
		
		current_weight += item.getWeight();
		total_value += item.getValue();
		items.add(item);
		return true;
	}
	
	public int getTotalValue() {
		return total_value;
	}
	
	public int getCurrentWeight() {
		return current_weight;
	}
	
	public Item[] getItems(){
		return (Item[]) items.toArray();
	}
	
	private List<Item> items;
	private int total_value;
	private int current_weight;
	
	public final int WEIGHT_CAP;
}
