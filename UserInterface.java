package searchengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The User Interface for the search engine
 * Lets users actually use the searchengineanalyzer to search for words and get the top results
 * Keeps a history of queries
 */

public class UserInterface {
	
	private SearchEngineAnalyzer searchEngine;
	
	private ArrayList<String> queryHistory = new ArrayList<>();
	
	public UserInterface(SearchEngineAnalyzer searchEngine) {
		this.searchEngine = searchEngine;
	}
	
	/**
	 * Starts the program
	 * Gets the users name
	 * lets them search and gives them the results
	 */
	public void start() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter your name: ");
		String name = sc.nextLine().trim();
		System.out.println("Hello " + name + "!");
		
		System.out.println("---Song Search Engine---");
		System.out.println("---Type EXIT When Done---");
		
		while(true) {
			System.out.print("Search: ");
			String query = sc.nextLine().trim();
			
			if (query.equalsIgnoreCase("EXIT")) {
				break;
			}
			
			if (query.isEmpty()) {
				System.out.print("Search: ");
				continue;
			}
			
			queryHistory.add(query);
			
			System.out.println("Results: ");
			
			List<String> results = searchEngine.search(query);
			
			if (results.isEmpty()) {
				System.out.println("Nothing");
			} else {
				for (int i = 0; i < results.size(); i++) {
					System.out.println((i + 1) + ". " + results.get(i));
				}
			}
			System.out.println();
		}
		
		System.out.println("---All Searches---");
		for (int i = 0; i < queryHistory.size(); i++) {
			System.out.println(" " + (i + 1) + ". " + queryHistory.get(i));
		}
		System.out.println("Goodbye " + name + "!");
		sc.close();
		
	}
	
	public ArrayList<String> getQueryHistory() {
		return queryHistory;
	}
	
	
	
	
	
	

}
