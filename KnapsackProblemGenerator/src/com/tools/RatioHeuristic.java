package com.tools;

import java.util.Arrays;

import com.models.Item;
import com.models.Knapsack;

public class RatioHeuristic extends Heuristic{
	
	public RatioHeuristic(Knapsack knapsack, Item[] items) {
		super(knapsack, items);		
	}

	@Override
	public void runHeuristic() {
		System.out.println("Running "+getClass().getSimpleName());
		Arrays.parallelSort(items, this);

		for(int i = 0; i < items.length; i++) {
			knapsack.addItem(items[i]);
		}

		System.out.println("Knapsack total value = "+knapsack.getTotalValue());
	}

	@Override
	public int compare(Item o1, Item o2) {
		
		float r1 = o1.getValue()/o1.getWeight();
		float r2 = o2.getValue()/o2.getWeight();
		
		if(r1 > r2) {
			return -1;
		}
		else if(r1 < r2){
			return 1;
		}
		
		return 0;
	}

}
