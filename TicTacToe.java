import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1Turn;

    TicTacToe() {
        // Set up the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        // Set up the title label
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(25, 255, 0));
        textField.setFont(new Font("Ink Free", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        // Set up panels
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        // Create buttons and add action listeners
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        // Add components to frame
        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        // Start the game
        firstTurn();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1Turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1Turn = false;
                        textField.setText("O turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1Turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        // Randomly determine who starts first
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textField.setText("X turn");
        } else {
            player1Turn = false;
            textField.setText("O turn");
        }

    }

    public void check() {
        // Check win conditions
        String[][] combinations = {
                {"0", "1", "2"}, {"3", "4", "5"}, {"6", "7", "8"}, // Rows
                {"0", "3", "6"}, {"1", "4", "7"}, {"2", "5", "8"}, // Columns
                {"0", "4", "8"}, {"2", "4", "6"} // Diagonals
        };

        for (int i = 0; i < combinations.length; i++) {
            String[] combination = combinations[i];
            if (buttons[Integer.parseInt(combination[0])].getText().equals("X")
                    && buttons[Integer.parseInt(combination[1])].getText().equals("X")
                    && buttons[Integer.parseInt(combination[2])].getText().equals("X")) {
                winEffect(combination);
                disableButtons();
                textField.setText("X wins");
                return;
            } else if (buttons[Integer.parseInt(combination[0])].getText().equals("O")
                    && buttons[Integer.parseInt(combination[1])].getText().equals("O")
                    && buttons[Integer.parseInt(combination[2])].getText().equals("O")) {
                winEffect(combination);
                disableButtons();
                textField.setText("O wins");
                return;
            }
        }
        

        // Check draw
        boolean draw = true;
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().isEmpty()) {
                draw = false;
                break;
            }
        }
        if (draw) {
            textField.setText("Draw!");
            disableButtons();
        }
    }

    public void winEffect(String[] combination) {
        // Apply visual effect for winning combination
        for (int j = 0; j < combination.length; j++) {
            String index = combination[j];
            buttons[Integer.parseInt(index)].setBackground(Color.GREEN);
        }
        
    }

    public void disableButtons() {
        // Disable all buttons
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }
}
