package com.tools;

import java.util.Random;

import com.models.Item;

public class ItemsGenerator {

	/**
	 * Generate an array of random items within the specified bounds.
	 * 
	 * @param size		- How many items will be generated.
	 * @param minValue	- Minimum value of each item.
	 * @param maxValue	- Maximum value of each item.
	 * @param minWeight	- Minimum weight of each item.
	 * @param maxWeight	- Maximum weight of each item.
	 * @return	- An array of Items randomly generated.
	 */
	public static Item[] generate(int size, int minValue, int maxValue, int minWeight, int maxWeight) {
		
		Item[] items = new Item[size];
		
		Random ran_val = new Random();
		Random ran_weight = new Random();

		for(int i = 0; i < size; i++) {
			items[i] = new Item(ran_val.nextInt(maxValue)+minValue, ran_weight.nextInt(maxWeight)+minWeight);
		}
		
		return items;
	}
	
}