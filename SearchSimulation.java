package searchengine;

import java.util.List;

/**
 * This tests the search engine on TF-IDF time, search time on queries, and the precision@5 accuracy rating.
 * Is used to start the  User Interface
 */

public class SearchSimulation {
	
	public static void main(String[] args ) {
		
		//measure TF-IDF build time
		long start = System.nanoTime();
		SearchEngineAnalyzer searchEngine = new SearchEngineAnalyzer("D:\\Downloads\\song_lyrics.tsv");
		long end = System.nanoTime();
		
		double calcTime = (end - start) / 1000000.0;
		
		System.out.println("Time needed to calculate TF and IDF: " + calcTime + " milliseconds.");
		System.out.println(" ");
		
		//test queries
		System.out.println("Query Test 1: Brooklyn");
		long s1 = System.nanoTime();
		List<String> r1 = searchEngine.search("Brooklyn");
		long e1 = System.nanoTime();
		System.out.println("Time: " + (e1 - s1) / 1000000.0 + " ms");
		System.out.println("Results: " + r1);
		//precision@5  is how many of the top 5 are actually related or relevant to the search
		//Relevance is based on if the term is found in title or lyrics
		int relevant1 = 5;
		System.out.println("precision@5: " + (relevant1 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 2: love");
		long s2 = System.nanoTime();
		List<String> r2 = searchEngine.search("love");
		long e2 = System.nanoTime();
		System.out.println("Time: " + (e2 - s2) / 1000000.0 + " ms");
		System.out.println("Results: " + r2);
		int relevant2 = 4;
		System.out.println("precision@5: " + (relevant2 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 3: baby");
		long s3 = System.nanoTime();
		List<String> r3 = searchEngine.search("baby");
		long e3 = System.nanoTime();
		System.out.println("Time: " + (e3 - s3) / 1000000.0 + " ms");
		System.out.println("Results: " + r3);
		int relevant3 = 5;
		System.out.println("precision@5: " + (relevant3 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 4: My Big Brother");
		long s4 = System.nanoTime();
		List<String> r4 = searchEngine.search("My Big Brother");
		long e4 = System.nanoTime();
		System.out.println("Time: " + (e4 - s4) / 1000000.0 + " ms");
		System.out.println("Results: " + r4);
		int relevant4 = 2;
		System.out.println("precision@5: " + (relevant4 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 5: I think");
		long s5 = System.nanoTime();
		List<String> r5 = searchEngine.search("I think");
		long e5 = System.nanoTime();
		System.out.println("Time: " + (e5 - s5) / 1000000.0 + " ms");
		System.out.println("Results: " + r5);
		int relevant5 = 4;
		System.out.println("precision@5: " + (relevant5 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 6: Octobers own Please leave me alone");
		long s6 = System.nanoTime();
		List<String> r6 = searchEngine.search("Octobers own Please leave me alone");
		long e6 = System.nanoTime();
		System.out.println("Time: " + (e6 - s6) / 1000000.0 + " ms");
		System.out.println("Results: " + r6);
		int relevant6 = 2;
		System.out.println("precision@5: " + (relevant6 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 7: love you");
		long s7 = System.nanoTime();
		List<String> r7 = searchEngine.search("love you");
		long e7 = System.nanoTime();
		System.out.println("Time: " + (e7 - s7) / 1000000.0 + " ms");
		System.out.println("Results: " + r7);
		int relevant7 = 5;
		System.out.println("precision@5: " + (relevant7 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 8: Night");
		long s8 = System.nanoTime();
		List<String> r8 = searchEngine.search("Night");
		long e8 = System.nanoTime();
		System.out.println("Time: " + (e8 - s8) / 1000000.0 + " ms");
		System.out.println("Results: " + r8);
		int relevant8 = 5;
		System.out.println("precision@5: " + (relevant8 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 9: Day and night");
		long s9 = System.nanoTime();
		List<String> r9 = searchEngine.search("Day and night");
		long e9 = System.nanoTime();
		System.out.println("Time: " + (e9 - s9) / 1000000.0 + " ms");
		System.out.println("Results: " + r9);
		int relevant9 = 4;
		System.out.println("precision@5: " + (relevant9 / 5.0));
		System.out.println();
		
		System.out.println("Query Test 10: I just sip the sizzurp That right there could drive a sane man berserk");
		long s10 = System.nanoTime();
		List<String> r10 = searchEngine.search("I just sip the sizzurp That right there could drive a sane man berserk");
		long e10 = System.nanoTime();
		System.out.println("Time: " + (e10 - s10) / 1000000.0 + " ms");
		System.out.println("Results: " + r10);
		int relevant10 = 0;
		System.out.println("precision@5: " + (relevant10 / 5.0));
		System.out.println();
		
		
		
		//Starts User Interface
		System.out.println("---Song Search---");
		UserInterface uI = new UserInterface(searchEngine);
		uI.start();
		
		
		
	}

}
