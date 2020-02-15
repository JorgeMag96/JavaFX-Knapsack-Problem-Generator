package com.newdev;

import java.io.Serializable;
import java.util.List;

import com.models.Item;
import com.models.Knapsack;

public class ProblemInstance implements Serializable{
	
	public ProblemInstance(Knapsack knapsack, List<Item> items) {
		super();
		this.knapsack = knapsack;
		this.items = items;
	}

	public Knapsack getKnapsack() {
		return knapsack;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public void print() {
		System.out.println("Knapsack cap = "+knapsack.WEIGHT_CAP);
		items.forEach(System.out::println);
	}

	private Knapsack knapsack;
	private List<Item> items;
	
	private static final long serialVersionUID = -4880342095498174443L;

}
