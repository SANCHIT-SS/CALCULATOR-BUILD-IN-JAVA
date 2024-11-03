import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double firstOperand;

    // Constructor
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Button panel setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // Array of button labels
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};

        // Adding buttons to panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    // Method handling button click events
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("C")) {
            display.setText("");
            operator = "";
        } else if (command.equals("=")) {
            double secondOperand = Double.parseDouble(display.getText());
            double result = calculate(firstOperand, secondOperand, operator);
            display.setText(String.valueOf(result));
            operator = "";
        } else {
            if (operator.isEmpty()) {
                firstOperand = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            } else {
                operator = command;
                firstOperand = Double.parseDouble(display.getText());
                display.setText("");
            }
        }
    }

    // Calculate method performing arithmetic operations
    private double calculate(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                if (second != 0) {
                    return first / second;
                } else {
                    throw new ArithmeticException("Division by zero");
                }
        }
        return 0;
    }

    // Main method
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setVisible(true);
    }
}
