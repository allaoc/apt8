import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
public class WordLadder {
    public String ladderExists(String[] words, 
                               String from, String to) {
         Map<String,Set<String>> oneaway = new HashMap<String,Set<String>>();
		 Set<String> visited = new HashSet<String>();
		 Queue<String> q = new LinkedList<String>();
		 oneaway.put(from,new HashSet<String>());
		 for (String comp : words) {
			 if (oneoff(from,comp)) oneaway.get(from).add(comp);
		 }
		 for (String word : words) {
			 oneaway.put(word,new HashSet<String>());
			 for (String comp : words) {
				 if (oneoff(word,comp)) oneaway.get(word).add(comp);
			 }
			 if (oneoff(word,to)) oneaway.get(word).add(to);
		 }
		 q.add(from);
		 while (q.size() > 0) {
			 String test = q.remove();
			 if (test.equals(to)) return "ladder";
			 q.addAll(oneaway.get(test));
			 q.removeAll(visited);
			 visited.add(test);
		 }
		 return "none";
    }
	private boolean oneoff(String word,String comp) {
		int off = 0;
		for (int c = 0; c < word.length(); c++) {
			if (word.charAt(c) != comp.charAt(c)) off++;
		}
		if (off == 1) return true;
		return false;
	}
  }