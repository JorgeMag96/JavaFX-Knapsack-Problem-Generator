package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.function.UnaryOperator;

import com.models.Item;
import com.models.Knapsack;
import com.tools.Heuristic;
import com.tools.HeuristicType;
import com.tools.ItemsGenerator;
import com.tools.RatioHeuristic;
import com.tools.ValueHeuristic;
import com.tools.WeightHeuristic;
import com.tools.files.DeserializedInstance;
import com.tools.files.SerializedInstance;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller{
	
	@FXML
    public void initialize() {
		System.out.println("Initializing controller...");
		
		number_of_instances.setValueFactory(
	            new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20)
		        );		
		
		UnaryOperator<Change> number = change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null;
		
		number_of_items.setTextFormatter(new TextFormatter<>(number));
		min_item_value.setTextFormatter(new TextFormatter<>(number));
		max_item_value.setTextFormatter(new TextFormatter<>(number));
		min_item_weight.setTextFormatter(new TextFormatter<>(number));
		max_item_weight.setTextFormatter(new TextFormatter<>(number));
		
		knp_weight.setText(String.valueOf(knp_weight_percent.valueProperty().intValue()));
		knp_weight_percent.setSnapToTicks(true);
		knp_weight_percent.setMin(1);
		knp_weight_percent.setMax(100);		
		knp_weight_percent.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
		            	int numberOfItems 	= Integer.parseInt(number_of_items.getText().isEmpty()? "0":number_of_items.getText());
		        		int minWeight		= Integer.parseInt(min_item_weight.getText().isEmpty()? "0":min_item_weight.getText());
		        		int maxWeight		= Integer.parseInt(max_item_weight.getText().isEmpty()? "0":max_item_weight.getText());
		        		
		        		int val = ((numberOfItems*((minWeight+maxWeight)/2))*new_val.intValue())/100;
		        		
                        knp_weight.setText(String.valueOf(val));
                }			
            });
		
		heuristicType.getItems().addAll(HeuristicType.values());
		heuristicType.setValue(HeuristicType.RATIO);
				
		System.out.println("Controller initialization successful.");
    }
	
	public void selectInstanceImp() {

		FileChooser instanceFileChooser = new FileChooser();
		instanceFileChooser.getExtensionFilters().addAll(
			     new FileChooser.ExtensionFilter("Text Files", "*.txt")			    
			);
		instanceFileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"\\instances"));
		
		File selectedInstanceFile = instanceFileChooser.showOpenDialog(stage);		
		
		instance_file_field.setText((selectedInstanceFile != null)? selectedInstanceFile.toString():"");		

	}
	
	public void runHeuristicImp() {

		if(instance_file_field.getText().isEmpty()) {
			System.out.println("No instance file selected...");
			return;
		}
		
		try {
			DeserializedInstance deserializedInstance = new DeserializedInstance(instance_file_field.getText());			
			Knapsack knapsack = deserializedInstance.getKnapsack();	        
			Item[] items = deserializedInstance.getItems();
			Heuristic heuristic = heuristicChoice(heuristicType.getValue(), knapsack, items);
		    heuristic.runHeuristic();
		    saveResults(heuristic.getKnapsack());
		}
		catch(IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText(e.getMessage());
			alert.setContentText("Please select a valid path to an instance file.");
			alert.showAndWait();
		}		
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
	
	private boolean isOkToGenerateInstances() {
		//TODO: Validate all generate tab fields.
		if(!min_item_value.getText().isEmpty() || !max_item_value.getText().isEmpty()) {
			if(Integer.parseInt(min_item_value.getText()) > Integer.parseInt(max_item_value.getText())) {
				System.out.println("Item max value can't be less than min value");			
				max_item_value.setText("");
				return false;
			}
		}
		else {
			System.out.println("Please fill all the fields.");
			return false;
		}
		
		if(Integer.parseInt(min_item_weight.getText()) > Integer.parseInt(max_item_weight.getText())) {
			System.out.println("Item max weight can't be less than min weight");			
			max_item_weight.setText("");
			max_item_weight.requestFocus();
			return false;
		}
		return true;
	}
	
	public void generateInstances() {
		if(isOkToGenerateInstances()) {
			int numberOfItems 	= Integer.parseInt(number_of_items.getText());
			int minValue 		= Integer.parseInt(min_item_value.getText());
			int maxValue 		= Integer.parseInt(max_item_value.getText());
			int minWeight		= Integer.parseInt(min_item_weight.getText());
			int maxWeight		= Integer.parseInt(max_item_weight.getText());		
			
			for(int i = 1; i <= number_of_instances.getValue(); i++) {
				
				Item[] items 		= ItemsGenerator.generate(numberOfItems, minValue, maxValue, minWeight, maxWeight);			
				Knapsack knapsack 	= new Knapsack(Integer.parseInt(knp_weight.getText()));
				
				try {					
					String newInstance = SerializedInstance.saveInstance(knapsack,items);
					Alert sucess = new Alert(AlertType.INFORMATION);
					sucess.setTitle("Information dialog");
					sucess.setHeaderText("Instance generated successfully !");
					sucess.setContentText("New instance generated: "+newInstance);
					sucess.showAndWait();
				} catch (IOException e) {					
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText(e.getMessage());
					alert.setContentText("Error while serializing instance");
					alert.showAndWait();
				}
			}
		}		
		resetFields();		
	}
	
	private void resetFields() {
		//TODO: Reset all field values
	}
	
	private void saveResults(Knapsack knapsack) {
		
		//TODO: Here we are going to serialize the result of the heuristic and save it to the results folder.		
		System.out.println("Knapsack result = "+knapsack.getTotalValue());
		System.out.println("Heuristic results saved successfully.");
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	private Stage		stage;
	public Button		slctHeuristicBtn;
	public Button 		runHeuristicBtn;
	public Button 		generateBtn;
	public TextField	instance_file_field;
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
