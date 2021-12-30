/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author JSVS
 */
public class About extends JFrame implements ActionListener {

    JButton btn;

    About() {
        setTitle("About Notely");
        setBounds(500, 200, 690, 550);
        setLayout(null);
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows11.png"));
        Image img2 = img1.getImage().getScaledInstance(380, 70, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);//storing the scaled image to img3
        JLabel l1 = new JLabel(img3);
        l1.setBounds(161, 40,380, 70);
        add(l1);

        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notely.png"));
        Image img5 = img4.getImage().getScaledInstance(120, 100, Image.SCALE_DEFAULT);
        ImageIcon img6 = new ImageIcon(img5);//storing the scaled image to img3
        JLabel l2 = new JLabel(img6);
        l2.setBounds(40, 150, 110, 90);
        add(l2);

        JLabel l3 = new JLabel("<html>Notely ft. Brainiac Space<br> Version Stable 1.00.00 <br> Vidhya Varshany, All Rights Reserved<br><br><b>Notely</b> is a Simple Minimal Text Editor Program with smooth user-experience and interface which allows to create files in any format instantly<br><br>🌟Why Notely??<br>1. Easy to use<br>2. Portable to Create Documents<br>3. Compact in size<br><br>Made with 💖Java Swings</html>");
        l3.setBounds(180, 140, 520, 330);
        l3.setFont(new Font("Monospace", Font.PLAIN, 17));
        add(l3);

        btn = new JButton("OK");
        btn.setBounds(520, 460, 80, 20);
        btn.addActionListener(this);
        add(btn);
    }

    public static void main(String[] args) {
        new About().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
    }

}
/* 
<applet code="About.class" width="600" height = "700"> 
</applet> 
*/
