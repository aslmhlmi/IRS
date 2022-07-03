package sims.model;

//////////////////////////////////////////////////////////////////////
////
//This program will display receipt using read-only TextArea    //
////
//////////////////////////////////////////////////////////////////////

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Receipt {
private JFrame     frame;
private JPanel     panel;
private JButton    btnSubmit;
private JLabel     lbName;
private JTextField tfInput;
private JTextArea  taReceipt;

// Constructor
public Receipt() {

// Create label name
lbName = new JLabel( "Name:" );
lbName.setBounds( 10, 10, 50, 20 );

// Create textfield to read customer's name
tfInput = new JTextField(30);
tfInput.setBounds( 60, 10, 240, 20 );

// Create button to submit customer's name
btnSubmit = new JButton( "Submit" );
btnSubmit.addActionListener( new btnSubmitAction( this ) );
btnSubmit.setBounds( 200, 40, 99, 20 );

// Create text area to display receipt
taReceipt = new JTextArea( "", 36, 5 );
taReceipt.setEditable( false );
taReceipt.setBounds( 10, 70, 290, 80 );
taReceipt.setText( "-- RECEIPT --\n\n" +
"CUSTOMER NAME:\n\n" +
"-- THANK YOU! --" );

// Create panel to hold button, textfields, label, textarea
panel = new JPanel( null );
panel.add( lbName );
panel.add( btnSubmit );
panel.add( tfInput );
panel.add( taReceipt );
panel.setPreferredSize( new Dimension(310, 160) );

// Create frame which is the main window
frame = new JFrame( "Receipt" );
frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
frame.getContentPane().add( panel );
frame.pack();
frame.setVisible( true );
}

// Method: Get customer's name from the text field
public String getTextFieldInput() {
return tfInput.getText();
}

// Method: Set receipt's text
public void setTextReceipt( String s ) {
taReceipt.setText( s );
}

public static void main( String[] args ) {
new Receipt();
}
}

class btnSubmitAction implements ActionListener {
private Receipt r;

// Constructor
public btnSubmitAction( Receipt r ) {
this.r = r;
}

@Override
public void actionPerformed( ActionEvent e ) {
String name = r.getTextFieldInput();
r.setTextReceipt( "-- RECEIPT --\n\n" +
"CUSTOMER NAME: " + name + "\n\n" +
"-- THANK YOU! --" );
}
} 