

//Dictionary2.java
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.StringTokenizer;

public class Dictionary2 {
ArrayList<Word> word = new ArrayList<Word>();

public void setWord(ArrayList<Word> input){
  word = input;
}
public ArrayList<Word> getWord(){
  return word;
}  

public void add(String n,String e){
  
  Word w = new Word(n,e);
  word.add(w);
}


public String search1(String name){
  for(int i=0;i<word.size();i++){
    if(name.equals(word.get(i).getName())){
      return word.get(i).getExplain();
    }
  }
  return "查找失敗！";
}

public String search2(String explain){
  for(int i=0;i<word.size();i++){
    if(explain.equals(word.get(i).getExplain())){
      return word.get(i).getName();
    }
  }
  return "查找失敗！";
}

public boolean change(String n,String e){
  int i=0;
  for(i=0;i<word.size();i++){
    if(n.equals(word.get(i).getName())){
      word.get(i).setExplain(e);
      return true;
    }
  }
  return false;
}

public boolean delete(String n){
  int i=0;
  for(i=0;i<word.size();i++){
    if(n.equals(word.get(i).getName())){
      word.remove(word.get(i));
      return true;
    }
  }
  return false;
}

public void load(){
  File file = new File("dictionary2.txt");
  Reader in;
  try {
    if(!file.exists()){
      file.createNewFile();
    }
    in = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(in);
    StringTokenizer str = null;
    
    
    String temp = bufferedReader.readLine();

    
    while(temp != null){
      if(! temp.equals("")){
        
      str = new StringTokenizer(temp);
      String n=str.nextToken();
      String e=str.nextToken();
      Word w = new Word(n,e);
      word.add(w);
      }
    temp = bufferedReader.readLine();
    }
    bufferedReader.close();
    in.close();
  } catch (FileNotFoundException e) {
    e.printStackTrace();
  } catch (IOException e) {
    e.printStackTrace();
  }
}
public void save(){
  File file = new File("dictionary2.txt");
  try {
    Writer out = new FileWriter(file);
    BufferedWriter bufferedWriter = new BufferedWriter(out);
    for(int i=0;i<word.size();i++){
      bufferedWriter.append((word.get(i).getName())+" "+ word.get(i).getExplain());
      bufferedWriter.newLine();
    }
    out.flush();
    bufferedWriter.flush();
    bufferedWriter.close();
    out.close();
  } catch (IOException e) {
    e.printStackTrace();
  }
}
}




