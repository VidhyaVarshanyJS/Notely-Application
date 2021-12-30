/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author JSVS^
 */
public class Notepad extends JFrame implements ActionListener {

    JFrame window;
    JTextArea area;
    JScrollPane pane;
    String fileName;
    String fileAddress;
    String text;
    UndoManager um = new UndoManager();

    Notepad() {
        setTitle("Notely");
       // setSize(1100,700);
        setBounds(200, 50, 1100,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        //to add the menu items to the file menu
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); //to set he key shortcut
        newdoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem saveas = new JMenuItem("Save As");
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        saveas.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        //add these to the menu named "File"
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");

        JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
        undo.addActionListener(this);

        JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
        redo.addActionListener(this);

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectall = new JMenuItem("Select All");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);

        edit.add(undo);
        edit.add(redo);
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About Notely");
        about.addActionListener(this);

        help.add(about);
        //adding menu to the menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        setJMenuBar(menubar);

        area = new JTextArea();
        //to set the font family and its size
        area.setFont(new Font("Open Sans", Font.PLAIN, 20));
        //for the line wrapping to avoid the single line infinity writings
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                um.addEdit(e.getEdit());
            }
        });

        pane = new JScrollPane(area);
        //to remove the border around the text field area
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "New":
                area.setText("");
                setTitle("New");
                fileName = null;
                fileAddress = null;
                break;

            case "Open":
                FileDialog fd = new FileDialog(window, "Open", FileDialog.LOAD);
                fd.setVisible(true);
                if (fd.getFile() != null) {
                    fileName = fd.getFile();
                    fileAddress = fd.getDirectory();
                    setTitle(fileName);
                }
                try {
                    BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
                    area.setText("");
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        area.append(line + "\n");
                    }
                    br.close();
                } catch (IOException ae) {
                    System.out.println("FILE NOT OPENED!");
                }

                break;

            case "Save":
                if (fileName == null) {
                    FileDialog fd1 = new FileDialog(window, "Save", FileDialog.SAVE);
                    fd1.setVisible(true);
                    if (fd1.getFile() != null) {
                        fileName = fd1.getFile();
                        fileAddress = fd1.getDirectory();
                        setTitle(fileName);

                    }
                    try {
                        FileWriter fw1 = new FileWriter(fileAddress + fileName);
                        fw1.write(area.getText());
                        setTitle(fileName);
                        fw1.close();
                    } catch (IOException ae) {
                        System.out.println("SOMETHING WRONG!!");
                    }
                } else {
                    try {
                        FileWriter fw1 = new FileWriter(fileAddress + fileName);
                        fw1.write(area.getText());
                        fw1.close();

                    } catch (IOException ae) {
                        System.out.println("SOMETHING WRONG!!");

                    }
                }
                break;

            case "Save As":
                FileDialog fd2 = new FileDialog(window, "Save", FileDialog.SAVE);
                fd2.setVisible(true);
                if (fd2.getFile() != null) {
                    fileName = fd2.getFile();
                    fileAddress = fd2.getDirectory();
                    setTitle(fileName);

                }
                try {
                    FileWriter fw2 = new FileWriter(fileAddress + fileName);
                    fw2.write(area.getText());
                    fw2.close();
                } catch (IOException ae) {
                    System.out.println("SOMETHING WRONG!!");
                }
                break;

            case "Print":
                try {
                area.print();

            } catch (PrinterException ae) {
                System.out.println("SOMETHING WRONG!!");
            }

            break;

            case "Exit":
                System.exit(0);
                break;
//Edit Menu
            case "Undo":
                 try {
                um.undo();
            } catch (CannotUndoException ae) {
                System.out.println("SOMETHING WRONG!!");

            }
            break;

            case "Redo":
                 try {
                um.redo();
            } catch (CannotRedoException ae) {
                System.out.println("SOMETHING WRONG!!");

            }
            break;

            case "Copy":
                text = area.getSelectedText();

                break;
            case "Paste":

                area.insert(text, area.getCaretPosition());

                break;

            case "Cut":
                area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
                break;

            case "Select All":

                area.selectAll();

                break;

            case "About Notely":
                new About().setVisible(true);
                break;

        }

    }

    public static void main(String[] args) {
        new Notepad().setVisible(true);
    }

    // TODO code application logic here
}
