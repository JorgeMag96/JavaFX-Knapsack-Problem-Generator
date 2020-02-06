package com.models;

public class Item{

	public Item (int value, int weight) {
		this.value = value;
		this.weight = weight;
	}
	
	public int getValue() {
		return value;
	}

	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return "Item [value=" + value + ", weight=" + weight + "]";
	}

	private int value;
	private int weight;
}
