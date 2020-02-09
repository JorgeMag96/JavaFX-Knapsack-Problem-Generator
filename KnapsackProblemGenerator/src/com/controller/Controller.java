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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class Controller{

	public Controller() {
		System.out.println("Initializing controller...");
	}
	
	@FXML
    public void initialize() {
		//heuristicType.getItems().addAll(HeuristicType.values());
		//heuristicType.setValue(HeuristicType.RATIO);
    }
	
	public void runHeuristicImp() {
		
		Knapsack knapsack = new Knapsack(950);
        
        Item[] items = ItemsGenerator.generate(Integer.parseInt(number_of_items.getText()), 10, 1000, 50, 500);
        
        heuristicChoice(heuristicType.getValue(), knapsack, items).runHeuristic();        
	}
	
	private Heuristic heuristicChoice(HeuristicType type, Knapsack knapsack, Item[] items) {

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

    	return heuristic;
    }
	
	public void generateInstances() {
		System.out.println("Clicked");
	}
	
	public Button 		runHeuristic;
	public Button 		generateBtn;
	public TextField 	number_of_items;
	public TextField 	min_item_value;
	public TextField 	max_item_value;
	public TextField 	min_item_weight;
	public TextField 	max_item_weight;
	public Label 		knp_weight;
	public Slider 		knp_weight_percent;
	public Spinner<Integer> 		number_of_instances;
	public ComboBox<HeuristicType> 	heuristicType;
}
