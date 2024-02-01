package student;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DatasetReader {

  private final List<String> lines;

  public DatasetReader(String pathToFile) throws IOException {
    File file = new File(pathToFile);
    lines = Files.readLines(file, Charsets.UTF_8);
  }

  public static void main(String[] args) throws IOException {
    DatasetReader data = new DatasetReader("./res/spam.csv");
    System.out.println(data.getThirdRow());
    System.out.println(data.countRows());
    System.out.println(data.countSpam());
    System.out.println(data.countHam());
    System.out.println(data.getDictionary("spam spam ham ham"));
    System.out.println(data.wordFrequency("spam"));
    System.out.println(Arrays.deepToString(data.lengthAndFrequency()));
  }

  public String getThirdRow() {
    return lines.get(3);
  }

  public void inspectFirstFiveRows() {
    System.out.println(lines.subList(1,6));
  }

  public int countRows() {
    return lines.size() - 1;
  }
  // 5574 rows

  private int helper(boolean isSpam) {
    int count = 0;
    for (int i = 1; i < countRows(); i++) {
      if (isSpam) {
        if (lines.get(i).startsWith("spam")) {
          count++;
        }
      } else {
        if (lines.get(i).startsWith("ham")) {
          count++;
        }
      }
    }
    return count;
  }

  public int countSpam() {
    return helper(true);
  }
  // 747 spam rows

  public int countHam() {
    return helper(false);
  }
  // 4826 ham rows

  public Set<String> getDictionary(String message) {
    String[] words = message.split("\\s+");
    Set<String> dict = new HashSet<>();
    for (String word : words) {
      dict.add(word);
    }
    return dict;
  }

  public int wordFrequency(String word) {
    Map <String, Integer> freq = new HashMap<>();

    for (int i = 0; i < countRows(); i++) {
      for (int j = 0; j < lines.get(i).length()-word.length(); j++) {
        if (lines.get(i).substring(j, j+word.length()).equals(word)) {
          if (freq.containsKey(word)) {
            freq.put(word, freq.get(word) + 1);
          } else {
            freq.put(word, 1);
          }
        }
      }
    }

    if (freq.containsKey(word)) {
      return freq.get(word);
    }
    return 0;
  }

  public int[][] lengthAndFrequency() {
    int[][] table = new int[50][50];

    String msg = String.join(" ", lines);
    Set<String> set = getDictionary(msg);
    for (String ele : set) {
      int len = ele.length();
      int freq = wordFrequency(ele);
      if (len < 50 && freq < 50) {
        table[len][freq]++;
      }
    }

    return table;
  }
}
