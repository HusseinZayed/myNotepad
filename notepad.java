
//author hussein zayed

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class notepad extends KeyAdapter  implements ActionListener, KeyListener //******
{
	
	static int active =0;
	static int fsize=17;
	static String filename;
	JFrame frm;
	JMenuBar MenuBar;
	JMenu file, edit, format, view;
	JMenuItem New, open, exit, save, saveas, copy, paste, remove,  fontColor, fontStyle, fontSize, status;
	JTextArea maintext;
	JTextField title;
	Font font1;
	JPanel under;
	JLabel details, pastecopy;
	JList familylist, stylelist, sizelist;
	JScrollPane scroll;
         

	String familyvalue[]={"Agency FB","Antiqua","Architect","Arial","Calibri","Comic Sans","Courier","Cursive","Impact","Serif"};
	String sizevalue[]={"5","10","15","20","25","30","35","40","45","50","55","60","65","70"};
	int [] stylevalue={ Font.PLAIN, Font.BOLD, Font.ITALIC };
	String [] stylevalues={ "PLAIN", "BOLD", "ITALIC" };
	String ffamily, fsizestr, fstylestr;
	int fstyle;
	int cl;
	int linecount;
	String tle ;
	String topicstitle = "";
	JScrollPane sp;
 
notepad(){

	frm = new JFrame("Notepad Fast");

	font1=new Font("Arial",Font.PLAIN,18);

	under = new JPanel();
	details = new JLabel();
	pastecopy = new JLabel();

	familylist = new JList(familyvalue);
	stylelist = new JList(stylevalues);
	sizelist = new JList(sizevalue);


	familylist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	sizelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	stylelist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	under.add(details);

	maintext = new JTextArea();
	sp=new JScrollPane(maintext);
	title = new JTextField(100);
	scroll = new JScrollPane(maintext);
	maintext.setMargin( new Insets(4,4,4,4) );
	maintext.setFont(font1);
	frm.add(maintext);

        
	MenuBar = new JMenuBar();

	file = new JMenu("File");
	edit = new JMenu("Edit");
	format = new JMenu("Format");
	view = new JMenu("View");

	New = new JMenuItem("New");
	open = new JMenuItem("Open");
	save = new JMenuItem("Save");
	saveas = new JMenuItem("Save As");
	exit = new JMenuItem("Exit");

	copy = new JMenuItem("Copy");
	remove = new JMenuItem("Remove");
	paste = new JMenuItem("Paste");

	fontColor = new JMenuItem("Font Family");
	fontStyle = new JMenuItem("Font Style");
	fontSize = new JMenuItem("Font Size");
        
	status = new JMenuItem("Status");
        
        
	MenuBar.add(file);
	MenuBar.add(edit);
	MenuBar.add(format);
	MenuBar.add(view);

	file.add(New);
	file.add(open);
	file.add(save);
	file.add(saveas);
	file.add(exit);

	edit.add(copy);
	edit.add(paste);
	edit.add(remove);

	format.add(fontColor);
	format.add(fontStyle);
	format.add(fontSize);

	view.add(status);


	frm.setJMenuBar(MenuBar);
	frm.add(under, BorderLayout.SOUTH);//********

	New.addActionListener(this);//*********
        open.addActionListener(this);
	copy.addActionListener(this);
	paste.addActionListener(this);
	remove.addActionListener(this);
	status.addActionListener(this);
	save.addActionListener(this);
	saveas.addActionListener(this);

	fontColor.addActionListener(this);
	fontSize.addActionListener(this);
	fontStyle.addActionListener(this);

	exit.addActionListener(this);

	maintext.addKeyListener(this);

	frm.setSize(600,600);     
	frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frm.setLocationRelativeTo(null);
        frm.setTitle("notepad plus plus");
	frm.setVisible(true);
}
public void actionPerformed(ActionEvent ae)//****
{
	if(ae.getSource()== New)
	{
	frm.setTitle("New Document.txt");
	maintext.setText("");
	title.setText("");
	}
	else if (ae.getSource()== copy)
	{
		String texts= maintext.getSelectedText();
		pastecopy.setText(texts);
		JOptionPane.showMessageDialog(null, "Copy Text "+texts);
	}
	else if(ae.getSource()== remove)
	{
		maintext.setText("");
		JOptionPane.showMessageDialog(null, "Removed");
	}
	else if (ae.getSource() == paste)
	{
		
            String s=maintext.getText()+pastecopy.getText();
			maintext.setText(s);
	}
	else if(ae.getSource()== status)
	{
        }
	else if (ae.getSource()== fontColor){
                  JColorChooser cc =new JColorChooser();
                 Color cl=cc.showDialog(null, "choose color of text", Color.black); // cl is color == which get it from showDialog color.black(intital color)
           maintext.setForeground(cl); } //assign cl to textarea
		
	else if (ae.getSource()== fontStyle)
		{
                        
		}
	else if (ae.getSource()== fontSize)
		{

	    JOptionPane.showConfirmDialog(null, sizelist, "Choose Font Size", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		fsizestr=String.valueOf(sizelist.getSelectedValue());
		fsize =Integer.parseInt(fsizestr);
		font1=new Font(ffamily,fstyle,fsize);
		maintext.setFont(font1);
		}
	else if(ae.getSource()==exit)
		{
			System.exit(0);
		}
	else if (ae.getSource()==save)
	{
	}
	else if (ae.getSource()==saveas)
	{
		 FileDialog mySelect=new FileDialog(frm, "open file", FileDialog.SAVE); //show screen to choose file
                   mySelect.setVisible(true);    
                    if(mySelect.getFile()!=null){  // while i choose file
                    filename=mySelect.getDirectory()+mySelect.getFile(); //path file assign to (variable)filename
        }
            try {
              PrintWriter write=new PrintWriter(filename+".txt");
              write.println(maintext.getText());
              write.close();
               } catch (FileNotFoundException ex) {
               Logger.getLogger(frm.getName()).log(Level.SEVERE, null, ex);
                                                                 }
	}
        
	else if (ae.getSource()== open)
	{
             FileDialog selectfile=new FileDialog(frm,"open file",FileDialog.LOAD); //show screen to choose file
                 selectfile.setVisible(true);    
               if(selectfile.getFile()!=null){  // while i choose file
                 filename=selectfile.getDirectory()+selectfile.getFile(); //path file assign to (variable)filename
                 try {
                   BufferedReader read=new BufferedReader(new FileReader(filename));
                    StringBuilder builder=new StringBuilder();
                    String line=null;
                      while((line=read.readLine())!=null){  //line == line from file and check if it not equal null will add to Stringbuilder and newline
                       builder.append(line+"\n");
                       maintext.setText(builder.toString());
                                                            }
                
                  } catch (Exception ex) {
                  Logger.getLogger(frm.getName()).log(Level.SEVERE, null, ex);
                                                                              }
        }
	}
}
	
public void keyTyped(KeyEvent ke){
	cl= maintext.getText().length();
	linecount = maintext.getLineCount();
	details.setText("Length: "+cl+" Line: "+linecount);
}		

public static void main(String ar[]) 
{
new notepad();
}
}
