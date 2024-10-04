//Login.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
  private JPanel contentPane;  
  public static void main(String[] args) {  
    Font font = new Font("宋體",Font.PLAIN,20);
    UIManager.put("Button.font", font);
    UIManager.put("TextField.font", font);
    UIManager.put("PasswordField.font", font);
    UIManager.put("TextArea.font", font);
    UIManager.put("Menu.font", font);
    UIManager.put("MenuItem.font", font);
    Login log = new Login(); 
    new Myframe();
    log.login();
  } 

  JFrame frame;
  JPanel jp0,jp1,jp2,jp3;
  JLabel jlb1,jlb2;
  JButton jb1,jb2;
  JTextField jtf1,jtf2;
  JPasswordField jpf1;
  
  public void login() {

    jp0 = new JPanel();
    jp1 = new JPanel();
    jp2 = new JPanel();
    jp3 = new JPanel();
    
    jlb1 = new JLabel("帳號 (預設 : apple)", SwingConstants.CENTER);
    jlb1.setVerticalAlignment(SwingConstants.TOP); 
    jlb2 = new JLabel("密碼 (預設 : 123)", SwingConstants.CENTER);  
    jlb2.setVerticalAlignment(SwingConstants.TOP); 
    jb1 = new JButton("登錄");
    jb2 = new JButton("取消");
    
    jtf1 = new JTextField(20);
    jpf1 = new JPasswordField(20);
    
    setLayout(new GridLayout(4,1));
    
    jp1.add(jlb1);
    jp1.add(jtf1);
    
    jp2.add(jlb2);
    jp2.add(jpf1);
    
    jp3.add(jb1);
    jp3.add(jb2);
    
    jb1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (jtf1.getText().equals("apple")) {
          if (String.valueOf(jpf1.getPassword()).equals("123")) {
            JOptionPane.showMessageDialog(null, "登錄成功", "恭喜", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            Menu menu = new Menu();
            menu.menu(); 
          } else {
            JOptionPane.showMessageDialog(null, "輸入密碼錯誤!!", "提示", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    
    jb2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jtf1.setText(null);
        jpf1.setText(null);
      }
    });
    
    add(jp0);
    add(jp1);
    add(jp2);
    add(jp3);

    ImageIcon bg = new ImageIcon("voc.jpg");
    JLabel label = new JLabel(bg);
    label.setSize(500,360);
    getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
    
    JPanel pan = (JPanel)getContentPane();
    pan.setOpaque(false);
    pan.setLayout(new FlowLayout());
 
    setTitle("登錄頁面");
    setSize(500,360);
    setLocationRelativeTo(null); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );   
    setVisible(true); 
  }

  }




