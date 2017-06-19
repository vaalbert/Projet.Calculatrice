/**
 * Created by Valentin on 02/06/2017.
 */

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CalculatorGUI {

    private CreateButton one, two, three, four, five, six, seven,
        eight, nine, dot, zero, addition, subtract,
        multiply, divide, equals, clear,
			sqrt, close, open, exp, ans, neg;
	
    JTextField displayBox;
    private String button = "";
    //The "input" variable holds user input that is readable to the "Calculator" class
    private String input = "";
    //The variable "newEntry" resets the screen when the user clicks any button after evaluating the expression
    //It removes the dependency on the "clear" button
    private boolean newEntry = false;
    private double result;
    DecimalFormat formater = new DecimalFormat("0.##########");
	
    public static void main(String[] args) {
	CalculatorGUI gui = new CalculatorGUI();
	gui.go();
    }

    public void go() {
        JFrame frame = new JFrame("Calculatrice");
        JPanel centerPane = new JPanel();
        JPanel bottomPane = new JPanel();
		 
        centerPane.setLayout(new GridLayout(5,4));
        bottomPane.setLayout( new GridLayout(1,2,2,2));
        
        displayBox = new JTextField(20);

		sqrt = new CreateButton("sqrt", new OperationButton(), centerPane);
    	exp = new CreateButton(" ^ ", new OperationButton(), centerPane);
    	open = new CreateButton("( ", new OperationButton(), centerPane);
    	close = new CreateButton(" )", new OperationButton(), centerPane);
    	seven = new CreateButton("7", new NumberButton(), centerPane);
    	eight = new CreateButton("8", new NumberButton(), centerPane);
    	nine = new CreateButton("9", new NumberButton(), centerPane);
    	addition = new CreateButton(" + ", new OperationButton(), centerPane);
    	four = new CreateButton("4", new NumberButton(), centerPane);
    	five = new CreateButton("5", new NumberButton(), centerPane);
    	six = new CreateButton("6", new NumberButton(), centerPane);
    	subtract = new CreateButton(" - ", new OperationButton(), centerPane);
    	one = new CreateButton("1", new NumberButton(), centerPane);
    	two = new CreateButton("2", new NumberButton(), centerPane);
    	three = new CreateButton("3", new NumberButton(), centerPane);
    	multiply = new CreateButton(" * ", new OperationButton(), centerPane);
    	dot = new CreateButton(".", new NumberButton(), centerPane);
        zero = new CreateButton("0", new NumberButton(), centerPane);
    	equals = new CreateButton(" = ", new EqualsButton(), centerPane);
    	divide = new CreateButton(" / ", new OperationButton(), centerPane);
    	ans = new CreateButton("ans", new OperationButton(), bottomPane);
    	neg = new CreateButton("(-)", new OperationButton(), bottomPane);
        clear = new CreateButton("clear", new OperationButton(), bottomPane);
		
        frame.getContentPane().add(BorderLayout.NORTH, displayBox);
        frame.getContentPane().add(BorderLayout.CENTER, centerPane);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPane);
		
        frame.setSize(750,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
		
    class NumberButton implements ActionListener {		
        public void actionPerformed(ActionEvent event) {
	   
	    	if (newEntry){
			displayBox.setText("");
			newEntry = false;
			input = "";
	    	}
	    
	    	button = event.getActionCommand();
        	displayBox.setText(displayBox.getText() + button);
        	input = input + button;
        }
    }
	
    class OperationButton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
        	double ans = 0.0;
            button = event.getActionCommand();	
            
	    	if (newEntry){
				displayBox.setText("");
				newEntry = false;
				input = "";
			}
			
			if (button == "ans"){
            	displayBox.setText(displayBox.getText() + formater.format(result));
            	input = input + Double.toString(result);
	    	}else if (button == "(-)"){
            	displayBox.setText(displayBox.getText() + "-");
            	input = input + "-";
           	}else if (button == "clear"){
            	displayBox.setText("");
            	input = "";
        	}else if (button == "sqrt"){
           	 	displayBox.setText(displayBox.getText() + "sqrt( ");
            	input = input + "sqrt ( ";
        	}else{
            	displayBox.setText(displayBox.getText() + button);
            	input = input + button;
        	}
        }
    }
    
    class EqualsButton implements ActionListener {
        Calculator evaluate = new Calculator();
        
    	public void actionPerformed(ActionEvent event){
	    	newEntry = true;

	    	if (evaluate.checkInput(input)){
            	button = event.getActionCommand();
            	result = evaluate.readInput(input);
            	displayBox.setText(displayBox.getText() + button + formater.format(result));
        	} else
            	displayBox.setText(evaluate.getException());
    	}
    }
}
