package com.models;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = -7852913657869508413L;
	
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
