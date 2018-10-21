package com.example.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.java8.model.Product;

interface Messageable {
	Message getMessage(String msg);
}

class Message {
	Message(String msg) {
		System.out.print(msg);
	}
}

interface Gosho {
	Product getName(String name);
}

@SpringBootApplication
public class Java8Application {

	static void ShowDetails(Map<Integer, String> map, String mapName) {
		System.out.println("----------" + mapName + " records-----------");
		map.forEach((key, val) -> System.out.println(key + " " + val));
	}

	static void addList(List<Integer> list) {
		// Return sum of list values
		int result = list.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Sum of list values: " + result);
	}

	public static void main(String[] args) {
		SpringApplication.run(Java8Application.class, args);
		
		Messageable hello = Message::new;
		hello.getMessage("Hello");
		
		Gosho asd = Product::new;
		asd.getName("gigi");
		
		Map<Integer,String> map=new HashMap<Integer,String>();  
		map.put(1,"vijay");  
		map.put(4,"umesh");  
		map.put(2,"ankit");  
		  
		//Now use Map.Entry for Set and Iterator  
		Set<Map.Entry<Integer,String>> set=map.entrySet();  
		  
		Iterator<Map.Entry<Integer,String>> itr=set.iterator();  
		while(itr.hasNext()){  
			Entry<Integer, String> e=itr.next();//no need to typecast  
			System.out.println(e.getKey()+" "+e.getValue());  
		}  
		
		BiConsumer<Map<Integer, String>, String> biCon = Java8Application::ShowDetails;
		biCon.accept(map, "Map");
		
//		map.forEach((key,val)->System.out.println(" key="+key+" val="+val));
		
		
		List<Product> list = new ArrayList<>();
		list.add(new Product(1,"smoki",20l));
		list.add(new Product(2,"majonez",50l));
		list.add(new Product(3,"cigari",70l));
		
		Stream<Product> products = list.stream().filter(p->p.getPrice()<30l);
		products.forEach((p)->System.out.println("product:"+p));
		List<Float> prices = list.stream()
									.filter(p->p.getPrice()<30l)
									.map(p->p.getPrice())
									.collect(Collectors.toList());
		System.out.println(prices);
		
		 // Creating a list and adding values  
        List<Integer> listOfIntegers = new ArrayList<Integer>();  
        listOfIntegers.add(10);  
        listOfIntegers.add(20);  
        listOfIntegers.add(30);  
        listOfIntegers.add(40);  
		// Referring method to String type Consumer interface   
		Consumer<List<Integer>> consumer = Java8Application::addList;  
		consumer.accept(listOfIntegers);  // Calling Consumer method  
		
		
	    Stream.iterate(1, element->element+1)  
		        .filter(element->element%5==0)  
		        .limit(5)  
		        .forEach(System.out::println);  
	    
	    
	    
        List<Product> productsList = new ArrayList<Product>();  
        //Adding Products  
        productsList.add(new Product(1,"HP Laptop",25000f));  
        productsList.add(new Product(2,"Dell Laptop",30000f));  
        productsList.add(new Product(3,"Lenevo Laptop",28000f));  
        productsList.add(new Product(4,"Sony Laptop",28000f));  
        productsList.add(new Product(5,"Apple Laptop",90000f));  
        // This is more compact approach for filtering data  
        Float totalPrice = productsList.stream()  
                    .map(product->product.getPrice())  
                    .reduce(0.0f,(sum, price)->sum+price);   // accumulating price  
        System.out.println(totalPrice);  
        // More precise code   
        float totalPrice2 = productsList.stream()  
                .map(product->product.getPrice())  
                .reduce(0.0f,Float::sum);   // accumulating price, by referring method of Float class  
        System.out.println(totalPrice2);
        
        // Using Collectors's method to sum the prices.  
        double totalPrice3 = productsList.stream()  
                        .collect(Collectors.summingDouble(product->product.getPrice()));  
        System.out.println(totalPrice3);     
        
        // max() method to get max Product price   
        Product productA = productsList.stream()  
                        .max((product1, product2)->   
                        product1.getPrice() > product2.getPrice() ? 1: -1).get();  
          
        System.out.println(productA.getPrice());  
        // min() method to get min Product price  
        Product productB = productsList.stream()  
                .max((product1, product2)->   
                product1.getPrice() < product2.getPrice() ? 1: -1).get();  
        System.out.println(productB.getPrice()); 	
        
        // count number of products based on the filter  
        long count = productsList.stream()  
                    .filter(product->product.getPrice()<30000)  
                    .count();  
        System.out.println(count); 	
        
        // Converting product List into Set  
        Set<Float> productPriceList =   
            productsList.stream()  
            .filter(product->product.getPrice() < 30000)   // filter product on the base of price  
            .map(product->product.getPrice())  
            .collect(Collectors.toSet());   // collect it as Set(remove duplicate elements)  
        System.out.println(productPriceList);	
        
        Product product1 = new Product(1,"smoki",10l);
        Product product2 = new Product(1,"smoki",10l);
        if(product1.equals(product2)) {
        	System.out.println("isti se");
        }else {
        	System.out.println("ne se isti");
        }
        
        // Converting Product List into a Map  
        Map<Integer,String> productPriceMap =   
            productsList.stream()  
                        .collect(Collectors.toMap(p->p.getId(), p->p.getName()));  
              
        System.out.println(productPriceMap);  	
        
        List<Float> productPriceList1 =   
                productsList.stream()  
                            .filter(p -> p.getPrice() > 30000) // filtering data  
                            .map(Product::getPrice)         // fetching price by referring getPrice method  
                            .collect(Collectors.toList());  // collecting as list  
        System.out.println(productPriceList1); 
        
        System.out.println("------------forEach---------------");  
        productsList.stream().forEach(p->System.out.println(p.getName()));
        System.out.println("------------forEachOrdered---------------");  
        productsList.stream().forEachOrdered(p->System.out.println(p.getName()));
        
        abstract class Shape{  
        	abstract void draw();  
    	}  
    	class Rectangle extends Shape{  
    		void draw(){System.out.println("drawing rectangle");}  
    	}  
    	class Circle extends Shape{  
    		void draw(){System.out.println("drawing circle");}  
    	}  
    	  
    	//creating a method that accepts only child class of Shape  
//    	public static void drawShapes(List<? extends Shape> lists){  
//	    	for(Shape s:lists){  
//	    		s.draw();//calling method of Shape class by child class instance  
//	    	}  
//    	}  
    	
    	
    	 try{  
		    try{  
		    	System.out.println("going to divide");  
		     	int b =39/0;  
		    }catch(ArithmeticException e){System.out.println(e);}  
		   
		    try{  
		    	int a[]=new int[5];  
		    	a[5]=4;  
		    }catch(ArrayIndexOutOfBoundsException e){System.out.println(e);}  
		     
		    System.out.println("other statement");  
		  }catch(Exception e){System.out.println("handeled");}  
		  
		  System.out.println("normal flow..");  
		}

}
