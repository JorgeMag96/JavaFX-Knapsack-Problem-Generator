package com.newdev;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import com.models.Item;
import com.models.Knapsack;
import com.tools.ItemsGenerator;

public class SerializationTesting {
	public static void main(String[] args) {
		
		long time = System.currentTimeMillis();
		
		List<Item> items = Arrays.asList(ItemsGenerator.generate(1000000, 5, 100, 5, 50));
		
		ProblemInstance pI = new ProblemInstance(new Knapsack(900000), items);
						
		//Serialization
		try(ObjectOutputStream objO = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\testFolder\\serialtest"))){
			System.out.println("Serializing ...");
			objO.writeObject(pI);
			//pI.print();
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
				
		//Deserialization
		try(ObjectInputStream objI = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"\\testFolder\\serialtest"))){
			
			System.out.println("Deserializing...");
			ProblemInstance pTest = (ProblemInstance) objI.readObject();
			//pTest.print();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		System.out.println("time = "+(System.currentTimeMillis()-time)/1000);
	}
}
