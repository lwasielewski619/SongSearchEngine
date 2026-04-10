package searchengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;




/**
 * A search engine using term frequency and inverse document frequency
 * It reads a tsv file with songs names and lyrics and calculates the tf and idf which are used to rank the results
 */
public class SearchEngineAnalyzer {
	
	private Map<String, String> titleLyricsMap;
	
	Map<String, Map<String, Integer>> tf;
	
	Map<String, Double> idf;
	
	Map<String, Double> songScore;
	
	
	/**
	 * reads the TSV file and gets the song title and lyrics
	 * @param filePath
	 * @return
	 */
	
	public static Map<String, String> readFiles(String filePath) {
	    HashMap<String, String> songLyricsMap = new HashMap<>();
	    try {
	        File file = new File(filePath);
	        Scanner scanner = new Scanner(file);

	        // skips header lines
	        if (scanner.hasNextLine()) {
	            scanner.nextLine();
	        }

	        int lineCount = 0;

	        while (scanner.hasNextLine() && lineCount < 100000) {
	            String line = scanner.nextLine();
	            String[] parts = line.split("\t");

	            if (parts.length >= 6) {
	                String title  = parts[0].trim();
	                String lyrics = parts[5].trim();
	                
	                if(!title.isEmpty() && !lyrics.isEmpty()) {
	                	songLyricsMap.put(title, lyrics);
	                }
	                
	            }
	            lineCount++;
	        }
	        scanner.close();

	    } catch (FileNotFoundException e) {
	        System.out.println("File not found: " + filePath);
	    }
	    return songLyricsMap;
	}
	
	/**
	 * loads the data and builds TF and IDF data
	 * @param directoryPath
	 */
	public SearchEngineAnalyzer(String directoryPath) {
		
		this.titleLyricsMap = SearchEngineAnalyzer.readFiles(directoryPath);
		this.tf = calculateTF();
		this.idf = calculateIDF(); 
	}
	
	//Returns all song titles in sorted order
	public Set<String> getSongsOrdered() {
		return new TreeSet<String>(this.titleLyricsMap.keySet());
	}
	
	//Converts the text to frequency map
	private Map<String, Integer> getTermFrequency(String input){
		String cleanStr = input.toLowerCase().replaceAll("[^a-z0-9]", " ");
		String[] words = cleanStr.split("\\s+");
		Map<String, Integer> frequencyMap = new HashMap<>();
		for (String word : words) {
			frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
		}
		return frequencyMap;
		
	}
	
	//Term Frequency for every song
	private Map <String, Map<String,Integer>> calculateTF(){
		Map<String, Map<String, Integer>> tf = new HashMap<String, Map<String, Integer>>();
		for (String songTitle : titleLyricsMap.keySet()) {
			String lyrics = titleLyricsMap.get(songTitle);
			Map<String, Integer> currentTF = getTermFrequency(lyrics);
			tf.put(songTitle, currentTF);
		}
		return tf;
		
	}
	
	//calculates IDF for every word in the map
	private Map<String, Double> calculateIDF() {
		
		Map<String, Double> idfMap = new HashMap<>();
		int totalSongs = titleLyricsMap.size();
		
		Map<String, Integer> wordSongCount = new HashMap<>();
		for (String  songTitle : tf.keySet()) {
			Map<String, Integer> currentTF = tf.get(songTitle);
			for (String word : currentTF.keySet()) {
				wordSongCount.put(word, wordSongCount.getOrDefault(word, 0) + 1);
			}
		}
		
		
		for (String word : wordSongCount.keySet()) {
			double score = Math.log((double) totalSongs / wordSongCount.get(word));
			idfMap.put(word, score);
			
		}
		return idfMap;
		
	}
	
	//Searches songs using the tf and idf score and boosts titles for better ranking in the search
	public List<String> search(String query) {
		String cleanQuery = query.toLowerCase().replaceAll("[^a-z0-9]", " ");
		String[] queryTerms = cleanQuery.split("\\s+");

		songScore = new HashMap<>();

		for (String song : tf.keySet()) {
			double score = 0;
			Map<String, Integer> currentTF = tf.get(song);
			for (String queryTerm : queryTerms) {
				
				//TF-IDF scoring
				if (currentTF.containsKey(queryTerm)) {
					double tfScore  = currentTF.get(queryTerm);
					double idfScore = idf.getOrDefault(queryTerm, 0.0);
					score = score + (tfScore * idfScore);
				}
				//gives more score if the queryterm is in the title
				if (song.toLowerCase().contains(queryTerm)) {
					score += 5;
				}
				
			}
			songScore.put(song, score);
		}

		return topK(songScore, 5);
	}
	
	/**
	 * RETURNS TOP k highest scoring songs
	 * @param songScores
	 * @param k
	 * @return
	 */
	private List<String> topK (Map<String, Double> songScores, int k) {
		
		List<String> top = new ArrayList<String>();
		while (k > 0) {
			double maxScore = -1;
			String maxSong = "";
			for(String song : songScores.keySet()) {
				double currentScore = songScores.get(song);
				if (currentScore > maxScore) {
					maxScore = currentScore;
					maxSong = song;
				}
			}
			
			top.add(maxSong);
			songScores.remove(maxSong);
			k--;
			
		}
		return top;
	}
	
	
	
	
	
	
	

}
