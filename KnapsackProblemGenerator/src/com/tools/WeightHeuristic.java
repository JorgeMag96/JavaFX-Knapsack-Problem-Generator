package com.tools;

import java.util.Arrays;

import com.models.Item;
import com.models.Knapsack;

/**
 * This heuristic will select items to put into the knapsack based on their weight ratio.
 * It will choose from the lightest to the heaviest item.
 * 
 * @param knapsack	- The knapsack to put the items.
 * @param items		- Array of items to select from.
 */
public class WeightHeuristic extends Heuristic{

	public WeightHeuristic(Knapsack knapsack, Item[] items) {
		super(knapsack, items);		
	}

	@Override
	public void runHeuristic() {
		System.out.println("Running "+getClass().getSimpleName());
		Arrays.parallelSort(items, this);
		
		int i = 0;
		boolean added = true;
		do{
			if (i == items.length) break;
			added = knapsack.addItem(items[i]);
			i++;
		}while(added);
		
		System.out.println("Knapsack total value = "+knapsack.getTotalValue());
	}

	@Override
	public int compare(Item o1, Item o2) {
		
		if(o1.getWeight() > o2.getWeight()) {
			return 1;
		}
		else if(o1.getWeight() < o2.getWeight()) {
			return -1;
		}
		else {
			if(o1.getValue() < o2.getValue()) {
				return 1;
			}
			else if(o1.getValue() > o2.getValue()) {
				return -1;
			}
			return 0;
		}
	}
}