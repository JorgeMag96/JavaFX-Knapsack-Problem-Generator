package com.controller;

import com.models.Item;
import com.models.Knapsack;
import com.tools.Heuristic;
import com.tools.HeuristicType;
import com.tools.ItemsGenerator;
import com.tools.RatioHeuristic;
import com.tools.ValueHeuristic;
import com.tools.WeightHeuristic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller{	
	
	public Controller() {
		System.out.println("Initializing controller...");
	}
	
	@FXML
    public void initialize() {
		heuristicType.getItems().addAll(HeuristicType.values());
		heuristicType.setValue(HeuristicType.RATIO);				
    }
		
	public void runHeuristicImp() {
		
		Knapsack knapsack = new Knapsack(950);
        
        Item[] items = ItemsGenerator.generate(Integer.parseInt(number_of_items.getText()), 10, 1000, 50, 500);
        
        heuristicChoice(heuristicType.getValue(), knapsack, items);
	}
	
	public void heuristicChoice(HeuristicType type, Knapsack knapsack, Item[] items) {

    	Heuristic heuristic = null;

    	switch(type) {
    		case RATIO:{
    			heuristic = new RatioHeuristic(knapsack, items);
    			break;
    		}
    		case VALUE:{
    			heuristic = new ValueHeuristic(knapsack, items);
    			break;
    		}
    		case WEIGHT:{
    			heuristic = new WeightHeuristic(knapsack, items);
    			break;
    		}
    		default:{
    			System.out.println("Heuristic type selection error");
    		}
    	}

    	if(heuristic != null) {
    		heuristic.runHeuristic();
    	}
    }
	
	public Button runHeuristic;
	public TextField number_of_items;
	public ComboBox<HeuristicType> heuristicType;
}
