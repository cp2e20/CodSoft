import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
    private Game game;
    private JFrame frame;
    private JTextField guessField;
    private JTextArea resultArea;

    public GameGUI() {
        game = new Game();
        frame = new JFrame("Number Guessing Game");

        
        JLabel promptLabel = new JLabel("Guess the number between 1 and 100:");
        guessField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        JButton resetButton = new JButton("Reset");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        
        frame.setLayout(new FlowLayout());
        frame.add(promptLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(resetButton);
        frame.add(new JScrollPane(resultArea));

        
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessNumber();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void guessNumber() {
        try {
            int guess = Integer.parseInt(guessField.getText().trim());
            game.incrementCounter();
            if (guess == game.getRightNb()) {
                resultArea.append("Hooray! You have won!\n");
                guessField.setEnabled(false);
            } else if (guess > game.getRightNb()) {
                resultArea.append("Your guess is too high.\n");
            } else {
                resultArea.append("Your guess is too low.\n");
            }

            if (game.getCounter() == 10) {
                resultArea.append("Oh no! You have lost. The correct number was " + game.getRightNb() + "\n");
                guessField.setEnabled(false);
            }

        } catch (NumberFormatException e) {
            resultArea.append("Invalid input. Please enter a number.\n");
        }

        guessField.setText("");
    }

    private void resetGame() {
        game.reset();
        guessField.setEnabled(true);
        resultArea.setText("");
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}

class Game {
    private int rightNb;
    private int counter;

    public Game() {
        rightNb = generateRandomNumber();
        counter = 0;
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(100) + 1; //random nb generator 
    }

    public int getRightNb() {
        return rightNb;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
    }

    public void reset() {
        rightNb = generateRandomNumber();
        counter = 0;
    }
}

