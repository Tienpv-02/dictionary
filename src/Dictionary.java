import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
  List<Word> words = new ArrayList<>();

  /** doc du lieu tu file EV.txt.*/
  public void insertFromFile(String path) {
    try {
      InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path),
          StandardCharsets.UTF_8);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        int index = line.indexOf('<');
        String target = line.substring(0,index);
        String explain = line.substring(index);
        Word newWord = new Word(target, explain);
        addWord(newWord);
      }
    } catch (FileNotFoundException e) {
      System.err.println("Could not open file at"+path);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*** them tu moi. */
  public void addWord(Word newWord) {
    if (newWord != null) {
      words.add(newWord);
    }
  }
  public void addWord(String word_target, String word_explain) {
    Word newWord = new Word(word_target,word_explain);
    words.add(newWord);
  }

  /** chinh sua tu.*/
  public void editWord(Word word, String newExplain) {
    if (word != null) {
      word.setWord_explain(newExplain);
    }
  }

  /** xoa tu.*/
  public int removeWord(String word_target) {
    int index = binarySearch(word_target);
    if (index == -1) {
      return -1;
    } else {
      words.remove(index);
      return 1;
    }
  }
  public int removeWord(int index) {
    if (index < 0 || index >= words.size()) {
      return -1;
    }
    words.remove(index);
    return 1;
  }
  /** Tim kiem nhi phan.*/
  public int binarySearch(String target) {
    int left = 0;
    int right = words.size() - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      Word tmp = words.get(mid);
      int xpr = target.compareToIgnoreCase(tmp.getWord_target());
      if (xpr == 0) {
        return mid;
      }
      if (xpr < 0) {
        right = mid - 1;
      }
      if (xpr > 0) {
        left = mid + 1;
      }
    }
  return -1;
  }
  /** tìm vị trí gần với từ cần tìm */
  public int preBinarySearch(String target) {
    int left = 0;
    int right = words.size() - 1;
    int mid = -1;
    while (left <= right) {
      mid = (left + right) / 2;
      Word tmp = words.get(mid);
      int xpr = target.compareToIgnoreCase(tmp.getWord_target());
      if (xpr == 0) {
        return mid;
      }
      if (xpr < 0) {
        right = mid - 1;
      }
      if (xpr > 0) {
        left = mid + 1;
      }
    }
    return mid;
  }

  /*private int BinarySearchApproximate(String text){
    int subStr = text.length();
    int left = 0;
    int right = words.size() - 1;
    String s;
    while(left < right){
      int mid = (left + right) /2;
      Word tmp = words.get(mid);
      s = tmp.getWord_target().length() > subStr ? tmp.getWord_target().substring(0, subStr) : tmp.getWord_target();
      if(text.compareToIgnoreCase(s) == 0){
        return mid;
      } else if(text.compareToIgnoreCase(s) < 0){
        left = mid + 1;
      } else if(text.compareToIgnoreCase(s) > 0) {
        right = mid - 1;
      }
    }
    return 0;

  }
  public int getIndex(String target){
    return binarySearch(target) == -1 ? BinarySearchApproximate(target) : binarySearch(target) ;
  }


   public static void main(String[] args) {
    Dictionary dictionary = new Dictionary();
    dictionary.insertFromFile("resources\\E_V.txt");
    System.out.println(dictionary.binarySearch("test"));
    }*/
}
