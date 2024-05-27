import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class TicTacToe implements ActionListener {

    private Random random = new Random();
    private JFrame frame = new JFrame();
    private JPanel titlePanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel textfield = new JLabel();
    private JButton[] buttons = new JButton[9];
    private boolean player1Turn;

    public TicTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);

        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        titlePanel.add(textfield);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1Turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText("X");
                        player1Turn = false;
                        textfield.setText("O turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText("O");
                        player1Turn = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1Turn = true;
            textfield.setText("X turn");
        } else {
            player1Turn = false;
            textfield.setText("O turn");
        }
    }

    public void check() {
        // Check X win conditions
        if (checkCombination("X")) {
            return;
        }
        // Check O win conditions
        if (checkCombination("O")) {
            return;
        }
        // Check for tie
        boolean tie = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                tie = false;
                break;
            }
        }
        if (tie) {
            textfield.setText("It's a tie!");
        }
    }

    private boolean checkCombination(String player) {
        if ((buttons[0].getText().equals(player) && buttons[1].getText().equals(player) && buttons[2].getText().equals(player)) ||
            (buttons[3].getText().equals(player) && buttons[4].getText().equals(player) && buttons[5].getText().equals(player)) ||
            (buttons[6].getText().equals(player) && buttons[7].getText().equals(player) && buttons[8].getText().equals(player)) ||
            (buttons[0].getText().equals(player) && buttons[3].getText().equals(player) && buttons[6].getText().equals(player)) ||
            (buttons[1].getText().equals(player) && buttons[4].getText().equals(player) && buttons[7].getText().equals(player)) ||
            (buttons[2].getText().equals(player) && buttons[5].getText().equals(player) && buttons[8].getText().equals(player)) ||
            (buttons[0].getText().equals(player) && buttons[4].getText().equals(player) && buttons[8].getText().equals(player)) ||
            (buttons[2].getText().equals(player) && buttons[4].getText().equals(player) && buttons[6].getText().equals(player))) {
            
            int[] winningCombination = getWinningCombination(player);
            for (int i : winningCombination) {
                buttons[i].setBackground(Color.GREEN);
            }
            for (JButton button : buttons) {
                button.setEnabled(false);
            }
            textfield.setText(player + " wins");
            return true;
        }
        return false;
    }

    private int[] getWinningCombination(String player) {
        if (buttons[0].getText().equals(player) && buttons[1].getText().equals(player) && buttons[2].getText().equals(player)) return new int[]{0, 1, 2};
        if (buttons[3].getText().equals(player) && buttons[4].getText().equals(player) && buttons[5].getText().equals(player)) return new int[]{3, 4, 5};
        if (buttons[6].getText().equals(player) && buttons[7].getText().equals(player) && buttons[8].getText().equals(player)) return new int[]{6, 7, 8};
        if (buttons[0].getText().equals(player) && buttons[3].getText().equals(player) && buttons[6].getText().equals(player)) return new int[]{0, 3, 6};
        if (buttons[1].getText().equals(player) && buttons[4].getText().equals(player) && buttons[7].getText().equals(player)) return new int[]{1, 4, 7};
        if (buttons[2].getText().equals(player) && buttons[5].getText().equals(player) && buttons[8].getText().equals(player)) return new int[]{2, 5, 8};
        if (buttons[0].getText().equals(player) && buttons[4].getText().equals(player) && buttons[8].getText().equals(player)) return new int[]{0, 4, 8};
        if (buttons[2].getText().equals(player) && buttons[4].getText().equals(player) && buttons[6].getText().equals(player)) return new int[]{2, 4, 6};
        return new int[]{};
    }
}
