//Word.java
public class Word {
  
  private String name,explain;
  
  public Word(String name,String explain){
    setName(name);
    setExplain(explain);
  }
  
  public String getName(){
    return name;
  }
  
  public void setName(String input){
    name = input;
  }
  
  public String getExplain(){
    return explain;
  }
  
  public void setExplain(String input){
    explain = input;
  }
}


