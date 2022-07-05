import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean hasDecimal = false;

    Calculator(){

        //Setup frame styling
        frame = new JFrame("Calculator Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        //Setup textfield for user input
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false);

        //Setup action buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        for(int i = 0; i < 8; i++){
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for(int i = 0; i < 10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);

        //Setup main button panel
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        //panel.setBackground(Color.GRAY);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);


        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int fieldLength = textField.getText().length();
        String fieldText = textField.getText();

        if(e.getSource() == decButton && hasDecimal == false){
            textField.setText(fieldText + String.valueOf("."));
            hasDecimal = true;

        } else if (e.getSource() == addButton){
            textField.setText(fieldText + String.valueOf(" + "));
            hasDecimal = false;

        } else if (e.getSource() == subButton){
            textField.setText(fieldText + String.valueOf(" - "));
            hasDecimal = false;

        } else if (e.getSource() == mulButton){
            textField.setText(fieldText + String.valueOf(" * "));
            hasDecimal = false;

        } else if (e.getSource() == divButton){
            textField.setText(fieldText + String.valueOf(" / "));
            hasDecimal = false;

        } else if(e.getSource() == delButton && fieldLength > 0){
            if(textField.getText().charAt(fieldLength - 1) == '.'){
                hasDecimal = false;
            }

            try{
                textField.setText(textField.getText(0, fieldLength - 1));
            } catch (BadLocationException exception){
                exception.printStackTrace();
            }


        } else if(e.getSource() == clrButton){
            textField.setText("");

        } else if(e.getSource() == equButton){
            double result = DjikstraShuntingYard.algorithm(textField.getText().substring(0,textField.getText().length()));
            textField.setText(String.valueOf(result));


        } else {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    textField.setText(textField.getText() + String.valueOf(i));
                }
            }

        }



    }
}
