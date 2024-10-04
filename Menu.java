//Menu.java
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

class Menu extends JFrame{

JMenuBar menuBar = new JMenuBar();
JMenu fileMenu,editMenu,helpMenu;
JMenuItem fileEnglish,fileChinese,exit,editShow,editAdd,editMod,editDel,helpItem;
JTextField inputText;
JTextArea textArea;
JLabel label1,label2;
JButton btn1;
JPanel p1,p2;

Display display;
Add add;
Modify modify;
Delete delete;

Dictionary2 dic = new Dictionary2();

public void menu(){
  this.setJMenuBar(menuBar);
  fileMenu = new JMenu("文件(F)");
  editMenu = new JMenu("編輯(E)");
  helpMenu = new JMenu("幫助(H)");
  
  fileEnglish = new JMenuItem("英漢字典");
  fileChinese = new JMenuItem("漢英字典");
  exit = new JMenuItem("退出");
  
  editShow = new JMenuItem("顯示單字");
  editAdd = new JMenuItem("添加單字");
  editMod = new JMenuItem("修改單字");
  editDel = new JMenuItem("刪除單字");
  
  helpItem = new JMenuItem("使用幫助");
  
  menuBar.add(fileMenu);
  menuBar.add(editMenu);
  menuBar.add(helpMenu);
  
  fileMenu.add(fileEnglish);
  fileMenu.add(fileChinese);
  fileMenu.addSeparator();
  fileMenu.add(exit);
  
  editMenu.add(editShow);
  editMenu.add(editAdd);
  editMenu.add(editMod);
  editMenu.add(editDel);
  
  helpMenu.add(helpItem);
  
  dic.load();
  
  
  fileEnglish.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      label1.setText("請輸入要查的單字:");
    }
  });
  
  fileChinese.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      label1.setText("請輸入要查的漢譯:");
    }
  });
  
  editShow.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      display = new Display();
      display.display();
      for(int i=0;i<dic.getWord().size();i++){
        if(dic.getWord().get(i).getName().length()<10){
          display.jta.append("\b"+dic.getWord().get(i).getName()+'\t');
        }
        else {
          display.jta.append("\b"+dic.getWord().get(i).getName());
        }
        display.jta.append("\t");
        display.jta.append("\b"+dic.getWord().get(i).getExplain());
        display.jta.append("\n");
      }
    }
  });
  
  editAdd.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      add = new Add();
      add.add();
      add.btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          dic.add(add.jtf.getText(),add.jta.getText());
          add.jtf.setText("");
          add.jta.setText("");
          JOptionPane.showMessageDialog(null, "添加成功", "恭喜", JOptionPane.INFORMATION_MESSAGE);
        }
      });
    }
  }); 
  
  editMod.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      modify = new Modify();
      modify.modify();
      modify.btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          boolean i = dic.change(modify.jtf.getText(),modify.jta.getText());
          if(i){
            modify.jtf.setText("");
            modify.jta.setText("");
            JOptionPane.showMessageDialog(null, "修改成功", "恭喜", JOptionPane.INFORMATION_MESSAGE);
          }else{
            modify.jtf.setText("");
            modify.jta.setText("");
            JOptionPane.showMessageDialog(null, "沒有找到該單字，修改失敗!", "提示", JOptionPane.ERROR_MESSAGE);
          }
        }
      });
    }
  });
  
  editDel.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      delete = new Delete();
      delete.delete();
      delete.btn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          if(dic.delete(delete.jtf.getText())){
            JOptionPane.showMessageDialog(null, "刪除成功", "恭喜", JOptionPane.INFORMATION_MESSAGE);
          }else{
            JOptionPane.showMessageDialog(null, "沒有找到該單字，刪除失敗!", "提示", JOptionPane.ERROR_MESSAGE);
          }
        }
      });
    }
  });
  
  exit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      dic.save();
      System.exit(0);
    }
  });
  
  helpItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){
      JOptionPane.showMessageDialog(null, "查詢：輸入單字，點及查詢，在文本框顯示結果；\n" +
                                    "添加單字：輸入單字，在文本框輸入解釋，點擊增添即可；\n" +
                                    "刪除單字：輸入單字，點擊刪除可將此單詞注釋刪除；\n" +
                                    "修改單字：輸入單字，在文本框輸入修改後的注釋，點擊修改；\n" +
                                    "顯示單字：會顯示所有的排序的單字\n","幫助",JOptionPane.WARNING_MESSAGE);
    }
  });
  
  inputText = new JTextField(20);
  textArea = new JTextArea(10,10);
  label1 = new JLabel("請輸入要查的單字:");
  label2 = new JLabel("查詢結果:");
  btn1 = new JButton("查詢");
  
  btn1.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){
      if(label1.getText().equals("請輸入要查的單字:")){
        if(dic.search1(inputText.getText()).equals("查找失敗")){
          JOptionPane.showMessageDialog(null, "查找失敗","消息對話框",JOptionPane.WARNING_MESSAGE);
        } else {
          textArea.setText(dic.search1(inputText.getText()));
        }
      } else if(label1.getText().equals("請輸入要查的漢譯:")) {
        if(dic.search2(inputText.getText()).equals("查找失敗")){
          JOptionPane.showMessageDialog(null, "查找失敗","消息對話框",JOptionPane.WARNING_MESSAGE);
        } else {
          textArea.setText(dic.search2(inputText.getText()));
        }
      }
    }
  });
  
  this.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e){
      dic.save();
      System.exit(0);
    }
  });
  
  p1 = new JPanel(new BorderLayout());
  p2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
  p2.add(label1);
  p2.add(inputText);
  p2.add(btn1);
  p1.add(label2,"North");
  p1.add(textArea,"Center");
  this.add(p2,"North");
  this.add(p1,"Center");
  
  this.setTitle("詞典筆記");
  this.setSize(800,600);
  this.setLocation(200,200);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
  
  this.setVisible(true); 
}
}

class Display extends JFrame{

JLabel label;
JTextArea jta;
JScrollPane scroll;
JPanel p;

public void display(){
  label = new JLabel("單字展示如下：");
  jta = new JTextArea();
  scroll = new JScrollPane(jta);
  scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  p = new JPanel(new BorderLayout());
  p.add(label,"North");
  p.add(scroll,"Center");
  add(p);
  
  ImageIcon bg = new ImageIcon("1.jpg");
  JLabel label = new JLabel(bg);
  label.setSize(500,360);
  getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
    
    JPanel pan = (JPanel)getContentPane();
    pan.setOpaque(false);
    pan.setLayout(new FlowLayout());
  
  setTitle("顯示單字");
  setSize(500,360);
  setLocation(300,300);
  setVisible(true); 
  
}
}

class Add extends JFrame{

JLabel label1,label2;
JTextField jtf;
JTextArea jta;
JButton btn;
JPanel p1,p2;

public void add(){
  label1 = new JLabel("請輸入要添加的單字:");
  label2 = new JLabel("添加的漢譯為：");
  jtf = new JTextField(20);
  jta = new JTextArea();
  btn = new JButton("確定");
  p1 = new JPanel(new BorderLayout());
  p1.add(label2,"North");
  p1.add(jta,"Center");
  p2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
  p2.add(label1);
  p2.add(jtf);
  p2.add(btn);
  add(p2,"North");
  add(p1,"Center");
  
  setTitle("添加單字");
  setSize(600,400);
  setLocation(300,300);
  setVisible(true);
}
}

class Modify extends JFrame{

JLabel label1,label2;
JTextField jtf;
JTextArea jta;
JButton btn;
JPanel p1,p2;

public void modify(){
  label1 = new JLabel("請輸入要修改的單字:");
  label2 = new JLabel("修改的漢譯為：");
  jtf = new JTextField(20);
  jta = new JTextArea();
  btn = new JButton("確定");
  p1 = new JPanel(new BorderLayout());
  p1.add(label2,"North");
  p1.add(jta,"Center");
  p2 = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
  p2.add(label1);
  p2.add(jtf);
  p2.add(btn);
  add(p2,"North");
  add(p1,"Center");
  
  setTitle("修改單字");
  setSize(600,400);
  setLocation(300,300);
  setVisible(true); 
}
}

class Delete extends JFrame{

JLabel label;
JTextField jtf;
JButton btn;
JPanel p,p1,p2;

public void delete(){
  label = new JLabel("請輸入要刪除的單字：");
  jtf = new JTextField(20);
  btn = new JButton("確定");
  p = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
  p1 = new JPanel(new FlowLayout());
  p2 = new JPanel(new FlowLayout());
  p.add(label);
  p.add(jtf);
  p.add(btn);
  add(p1,"North");
  add(p,"Center");
  add(p2,"South");
  
  setTitle("刪除單字");
  setSize(600,400);
  setLocation(300,300);
  setVisible(true); 
}
}



