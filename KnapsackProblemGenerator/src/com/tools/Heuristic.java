package com.tools;

import java.util.Comparator;

import com.models.Item;
import com.models.Knapsack;

public abstract class Heuristic implements Comparator<Item>{
	
	public Heuristic(Knapsack knapsack, Item[] items) {
		this.knapsack = knapsack;
		this.items = items;
	}
	
	public void printItems() {
		for(Item i : items) {
			System.out.println(i);
		}
	}
	
	public abstract void runHeuristic();
	
	public Knapsack getKnapsack() {
		return knapsack;
	}
	
	public Item[] getItems() {
		return items;
	}
	
	public Knapsack knapsack;
	public Item[] items;
	
	public enum type {
		RATIO, VALUE, WEIGHT;
	}
}
