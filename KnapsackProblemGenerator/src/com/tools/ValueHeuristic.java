package com.tools;

import java.util.Arrays;

import com.models.Item;
import com.models.Knapsack;

/**
 * This heuristic will select items to put into the knapsack based on their value ratio.
 * It will choose from the most to the less valuable item.
 * 
 * @param knapsack	- The knapsack to put the items.
 * @param items		- Array of items to select from.
 */
public class ValueHeuristic extends Heuristic{

	public ValueHeuristic(Knapsack knapsack, Item[] items) {
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

		if(o1.getValue() > o2.getValue()) {
			return -1;
		}
		else if(o1.getValue() < o2.getValue()) {
			return 1;
		}

		return 0;
	}

}
