package com.search;

import java.util.HashMap;
import java.util.List;

public class Main {

	public static void main (String [] args) {
		SearchTool searchTool = new SearchToolImpl();
		  searchTool.index(
		    new Document(
		      1, 
		      new HashMap<String, String>() {{
		    	  put("Producto", "Mesa madera barnizada de castaño con mantel de algodon");
		          put("Tienda", "Juan Florez A Coruña");
		      }}
		    )
		  );
		  searchTool.index(
		    new Document(
		      2, 
		      new HashMap<String, String>() {{
		        put("Producto", "Hamaca tela negra de algodon. Uso con soporte de castaño y de color castaño");
		        put("Tienda", "Alfredo Vicenti A Coruña");
		      }}
		    )
		  );
	      long start = System.currentTimeMillis();
		  List<Hit> hits = searchTool.search("Coruña", true);
		  long end = System.currentTimeMillis();
	      System.out.println("Both, any order -> Find finished in  " + (end - start) + "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));
	      start = System.currentTimeMillis();

	      hits = searchTool.search("mesa barnizada", true);
		  end = System.currentTimeMillis();
	      System.out.println("Only doc 1 -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));

	      start = System.currentTimeMillis();

	      hits = searchTool.search("coruña mesa", false);
	      end = System.currentTimeMillis();
	      System.out.println("Both, 1 first -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));

	      hits = searchTool.search("tienda", false); 
		  end = System.currentTimeMillis();
	      System.out.println("Empty -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));

	      start = System.currentTimeMillis();
	      hits = searchTool.search("", false);
		  end = System.currentTimeMillis();
	      System.out.println("Empty -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));
	      
	      start = System.currentTimeMillis();
	      hits = searchTool.search("mesa negra coruña", true);
		  end = System.currentTimeMillis();
	      System.out.println("Empty -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));
	      
	      start = System.currentTimeMillis();
	      hits = searchTool.search("castaño", true);
		  end = System.currentTimeMillis();
	      System.out.println("both  -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));
	      
	      start = System.currentTimeMillis();
	      hits = searchTool.search("hamaca coruña", true);
		  end = System.currentTimeMillis();
	      System.out.println("doc 2  -> Find finished in  " + (end - start)+ "ms");
	      hits.forEach(hit -> System.out.println(hit.toString()));

	}

}
