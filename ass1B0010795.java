//Programmer: Pauline Finlay
//Student No: B00107945
//Date: 13.02.2018
//Assignment 1
//Program to enter student grades for grade analysis
//Note: use swing

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ass1B0010795 extends JFrame implements ActionListener
{

public JFrame window = new JFrame("ITB COURSE GRADE ENTRY SYSTEM"); //create main frame

public JPanel panelnorth = new JPanel(); //create 5 panels on main frame
public JPanel panelsouth = new JPanel();
public JPanel paneleast = new JPanel();
public JPanel panelwest = new JPanel();
public JPanel panelcenter = new JPanel();

JLabel label1 = new JLabel("Welcome to the ITB Course Grade Entry System"); //create JLabel as heading

JLabel text = new JLabel("Enter number of students in class"); //create JLabel to prompt user to enter the number of students in class
JTextField classsize = new JTextField(10);  //create JTextField for input
JButton enter = new JButton("Enter");	//create JButton to enter input

JTextArea info = new JTextArea(15,25);	//create JTextArea to display student details
JTextArea display = new JTextArea(15,25);	//create JTextArea to display the results of methods

JButton average = new JButton("Average score");		//create JButtons to call methods
JButton low = new JButton("Lowest score");
JButton high = new JButton("Highest score");
JButton sort = new JButton("Sort Grades");
JButton search = new JButton("Student Search");

String names[]; //declare string array for student names
int grades[]; //declare int array for student grades
int element; //declare variable for use in linear search method of student names
int lowGrade, highGrade, averageGrade =0; //declare & initialise int variables for average, high & low methods

public ass1B0010795()
{
	setLayout(new BorderLayout(5,5));  //create border layout to arrange panels
	panelnorth.setBackground(Color.white);	//set background colour & original status of panels
	panelnorth.setVisible(true);
	panelsouth.setBackground(Color.lightGray);
	panelsouth.setVisible(false);
	paneleast.setBackground(Color.lightGray);
	paneleast.setVisible(false);
	panelwest.setBackground(Color.lightGray);
	panelwest.setVisible(false);
	panelcenter.setBackground(Color.lightGray);
	panelcenter.setVisible(true);

	panelnorth.add(label1);	//add header to panel in North layout
	label1.setFont(new Font ("TimesRoman", Font.BOLD + Font.ITALIC, 16)); //set font type, size & effects

	panelcenter.add(text);	//add label, textfield and enter button to center panel
	panelcenter.add(classsize);
	panelcenter.add(enter);
	enter.addActionListener(this);

	paneleast.add(info); //add JTextArea to show student details to panel in east panel

	panelsouth.add(average);	//add buttons that call methods to south panel & add action listener to each
	average.addActionListener(this);
	panelsouth.add(low);
	low.addActionListener(this);
	panelsouth.add(high);
	high.addActionListener(this);
	panelsouth.add(sort);
	sort.addActionListener(this);
	panelsouth.add(search);
	search.addActionListener(this);

	panelwest.add(display); //add text area to panel in east layout to display results of methods

	window.add(panelnorth, BorderLayout.NORTH); //add panels to main frame in BorderLayout
	window.add(panelsouth, BorderLayout.SOUTH);
	window.add(paneleast, BorderLayout.EAST);
	window.add(panelwest, BorderLayout.WEST);
	window.add(panelcenter, BorderLayout.CENTER);

	window.setSize(750, 350); //set frame size
	window.setVisible(true);

	}

public void actionPerformed(ActionEvent e) //implement event listener
{
	String student = classsize.getText();
	int students = Integer.parseInt(student); //set textfield entry to integer

			if (students<2)
				{
				JOptionPane.showMessageDialog(null, "Invalid entry minimum no. of students is 2"); //if class size is less than 2 error message
				classsize.setText("");
				}
			else
			if (students>25)
				{
				JOptionPane.showMessageDialog(null, "Invalid entry maximum no. of students is 25"); //if class size is greater than 25 error message
				classsize.setText("");
				}
			else
				{
				classsize.setEditable(false); //class size true
				paneleast.setVisible(true);
				}

			if(e.getSource() == enter) //action on pressing enter button
			{
				String len = classsize.getText();
				int length = Integer.parseInt(len);

				names =new String[length]; //initialise names array
				grades = new int[length];  //initialise grades array

				for(int i = 0; i < length; i++)
				{
				//obtain user input from JOptionPane input dialog
				String name =
				JOptionPane.showInputDialog("Enter Student Name");
				name= name.toUpperCase(); //ignores case set all uppercase
				String number =
				JOptionPane.showInputDialog("Enter Student Percentage Grade");

				//convert String inputs to int values for use in calculation
				int number1 = Integer.parseInt(number);

				//checks to see that grade entered is a percentage
				if (number1 <0)
					{
					JOptionPane.showMessageDialog(null, "Invalid Grade, minimum number is 0");
					number=null;
					number = JOptionPane.showInputDialog("Enter Student Percentage Grade");
					number1 = Integer.parseInt(number);
					}
				else
					if(number1> 100)
					{
					JOptionPane.showMessageDialog(null, "Invalid Grade, maximum number is 100");
					number=null;
					number = JOptionPane.showInputDialog("Enter Student Percentage Grade");
					number1 = Integer.parseInt(number);
					}

				//populate arrays
				names[i] = name;
				grades[i] = number1;
				}

				paneleast.add(new JScrollPane(info)); //add JTextArea with JScrollPane to panel
				info.setText("Student names & grades \n");
				//loop to display grades to JTextArea
					for(int i = 0; i < grades.length; i++)
					{
					info.append("\nStudent " +(i+1)  + "\t " +names[i]+"\tGrade: "   + " " +grades[i]);
					}

				panelcenter.setVisible(false); //hide center panel
				panelsouth.setVisible(true); //show button panel in south
			}


			if(e.getSource()==average) //action on pressing average button
				{
				panelwest.setVisible(true); //show east panel
				display.setText("Grades Analysis"); //show user info
				display.append("\nAverage Grade");
				display.append("\n " + average(grades)); //call average method & display average grade
				}


			if(e.getSource()==high) //action on pressing high button
				{
				panelwest.setVisible(true); //show east panel
				display.setText("Grades Analysis"); //show user info
				display.append("\nHighest Grade");
				display.append("\n " + maximum(grades)); //call maximum method & display highest grade
				}

			if(e.getSource()==low) //action on pressing low button
			{
			panelwest.setVisible(true); //show east panel
			display.setText("Grades Analysis"); //show user info
			display.append("\nLowest Grade");
			display.append("\n " +minimum(grades)); //call minimum method & display lowest score
			}

			if(e.getSource()==sort) //action on pressing sort button
			{
			panelwest.setVisible(true); //show east panel
			display.setText("Grades Analysis"); //show user info
			display.append("\nGrades Sorted Ascending");
			panelwest.add(new JScrollPane(display));
			sort(grades); // call sort method
					for(int i = 0; i < grades.length; i++) //print sorted grades array in ascending order
					{
					   	display.append("\n "+grades[i]);
					}
			}

			if(e.getSource()==search) //action on pressing search button
			{
			panelwest.setVisible(true); //show east panel
			display.setText("Grades Analysis"); //show user info
			display.append("\nSearch Student Name\n");
			String searchname=
				JOptionPane.showInputDialog("Enter Student Name"); //open JOption Pane to enter the student name you wish to look for
			searchname= searchname.toUpperCase(); //ignores case set all uppercase

				element = linearsearch(names, searchname); //call search method

						//test value of return from method & prints to screen
						if(element != -1)
						{
						display.append("Student Entered");
						}
						else
						display.append("Student Not Entered");
			}

}

public static void main(String[] args) //main method
	{
		ass1B0010795 app = new ass1B0010795();
		app.addWindowListener(new WindowAdapter()
			   {
				  public void windowClosing(WindowEvent e)
				   {
					   System.exit(0);
				   }
			   }
			   );
	} //end main method2

//=========================================================================
//=========================================================================
	//USER DEFINED METHODS

	//Linear search method
 	public static int linearsearch(String string[], String search)
     {
         int i; //assign temp variable for looping through array
         for(i = 0; i<string.length; i++)
         {
             if (search.equals(string[i])) //if search found in array
             	{
                 return i;
             	}
		 }
			return -1;	//return -1 if search not found in array
	 }

//=========================================================================
//=========================================================================

	//Sort Grades Method
	public static void sort(int array[]) //method code for sort
		{
		int hold; //temporary holding area for swap

		for(int pass = 1; pass < array.length; pass++) //passes
			for(int i = 0; i < array.length-1; i++) //one pass

			if(array[i] > array[i+1]) //one comparison
			{
			hold = array[i];
			array[i] = array[i+1];
			array[i+1] = hold;
			}
		}

//=========================================================================
//=========================================================================

	// method to find minimum grade
	public int minimum(int min[])
	{
	// assume first element of grades array is smallest
	int lowGrade = min[ 0 ];
	// loop through rows of grades array
	for ( int i = 0; i < min.length; i++ )
		{
		// Test if current grade is less than lowGrade & assign current grade to lowGrade
		if ( min[ i ] < lowGrade )
			lowGrade = min[ i ];
			}
			return lowGrade;	// return lowest grade
	}

//=========================================================================
//=========================================================================

	//method to find maximum grade
	public static int maximum(int[] max)
	{
		// assume first element of grades array is largest
		int highGrade = max[0];
		// loop through rows of grades array
		for ( int i = 0; i < max.length; i++ )

			// Test if current grade is greater than highGrade & assign current grade to highGrade
				if ( max[ i ] > highGrade )
				highGrade = max[ i ];
				return highGrade; // return highest grade
	}

//=========================================================================
//=========================================================================

	//method to determine average grade
	public static int average( int setOfGrades[] )
	{
		int total = 0;	// initialize total
		// sum grades
		for ( int i = 0; i < setOfGrades.length; i++ )
		total += setOfGrades[ i ];
		// return average of grades
		return total / setOfGrades.length;
  	}

//=========================================================================
//=========================================================================

}