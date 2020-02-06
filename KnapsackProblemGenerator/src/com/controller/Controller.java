package com.controller;

import com.models.Item;
import com.models.Knapsack;
import com.tools.Heuristic;
import com.tools.HeuristicType;
import com.tools.RatioHeuristic;
import com.tools.ValueHeuristic;
import com.tools.WeightHeuristic;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
	
	@FXML
	public Button runHeuristic;
	
	public void handleButtonClick() {
		Knapsack ktest = new Knapsack(950);
        Knapsack ktest2 = new Knapsack(950);
        Knapsack ktest3 = new Knapsack(950);
        
        Item[] itest = new Item[10];
        itest[0] = new Item(500,200);
        itest[1] = new Item(78,90);
        itest[2] = new Item(423,60);
        itest[3] = new Item(325,50);
        itest[4] = new Item(300,100);
        itest[5] = new Item(98,63);
        itest[6] = new Item(150,99);
        itest[7] = new Item(65,50);
        itest[8] = new Item(142,89);
        itest[9] = new Item(800,320);
        
        heuristicChoice(HeuristicType.WEIGHT, ktest, itest);
        heuristicChoice(HeuristicType.VALUE, ktest2, itest);
        heuristicChoice(HeuristicType.RATIO, ktest3, itest);  
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
	
}
