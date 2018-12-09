import java.util.HashMap;
import java.util.HashSet;
public class AllWordLadders {
	HashMap<String,HashSet<String>> oneaway = new HashMap<String,HashSet<String>>();
	HashMap<String,Integer> minint = new HashMap<String,Integer>();
	int min = 0;
	int counts = 0;
    public int[] solve(String[] words, 
                               String from, String to) {
		 int[] ret = new int[2];
		 boolean found = false;
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
		 findpaths(from,to,new HashSet<String>());
		 ret[0] = min;
		 ret[1] = counts;
		 return ret;
    }
	private boolean oneoff(String word, String comp) {
		int off = 0;
		for (int c = 0; c < word.length(); c++) {
			if (word.charAt(c) != comp.charAt(c)) off++;
		}
		if (off == 1) return true;
		return false;
	}
	private void findpaths(String from, String to, HashSet<String> path) {
		if (path.contains(from)) return;
		path.add(from);
		minint.put(from,path.size());
		if (path.size() > min && min != 0) return;
		if (oneaway.get(from).contains(to)) {
			if (path.size()+1 < min || min == 0) {
				min = path.size()+1;
				counts = 1;
			}
			else if (path.size()+1 == min) {
				counts++;
			}
			return;
		}
		if (path.size()+1>min && min != 0) return;
		for (String dest : oneaway.get(from)) {
			if (minint.containsKey(dest) && minint.get(dest) < path.size()+1) continue; 
			if (!path.contains(dest)) findpaths(dest, to, new HashSet<String>(path));
		}
	}
  }