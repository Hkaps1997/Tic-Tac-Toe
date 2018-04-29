import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TTT extends JFrame implements ActionListener {
    public static final int BOARD_SIZE = 3;
    public static final int X_WINS = 1;
    public static final int Z_WINS = 2;
    public static final int INCOMPLETE = 3;
    public static final int TIE = 4;
    private boolean crossTurn;
    private JButton[][] buttons;

    public TTT() {
        super.setTitle("Tic Tac Toe");
        super.setResizable(false);
        super.setSize(600, 600);
        GridLayout layout = new GridLayout(BOARD_SIZE, BOARD_SIZE);

        super.setLayout(layout);


        this.buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
        for (int rows = 0; rows < BOARD_SIZE; rows++) {
            for (int cols = 0; cols < BOARD_SIZE; cols++) {
                JButton button = new JButton();
                button.setFont(new Font("Monotype Corsiva", 1, 225));
                button.addActionListener(this);
                this.buttons[rows][cols] = button;

                super.add(button);

            }
        }
        this.setVisible(true);
    }

    public void startGame() {
        this.crossTurn = true;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton button = (JButton) arg0.getSource();
        if (button.getText().equals("")) {
            this.makeMove(button);
            int status = this.gameStatus();
            if (this.gameStatus() == INCOMPLETE)
                this.crossTurn = !this.crossTurn;
            else {
                this.declareWinner(status);
                super.dispose();

            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid move");
        }
    }

    private void declareWinner(int status) {
        if (status == X_WINS)
            JOptionPane.showMessageDialog(null, "X WINS");
        else if (status == INCOMPLETE)
            return;
        else if(status==Z_WINS)
            JOptionPane.showMessageDialog(null, "Z WINS");
        else
            JOptionPane.showMessageDialog(null, "TIE");
    }

    private void makeMove(JButton button) {
        if (this.crossTurn){
            button.setForeground(Color.RED);
            button.setText("X");}
        else{
            button.setForeground(Color.BLUE);
            button.setText("O");
        }
    }

    private int gameStatus() {
        int rows = 0, cols = 0;
        String text1 = "", text2 = "";
        for (rows = 0; rows < BOARD_SIZE; rows++) {

            cols = 0;
            while (cols < BOARD_SIZE - 1) {
                text1 = buttons[rows][cols].getText();
                text2 = buttons[rows][cols + 1].getText();
                if (text1.length() == 0 || !text1.equals(text2)) {
                    break;
                }
                cols++;
            }

            if (cols == BOARD_SIZE - 1)
                return text1.equals("X") ? X_WINS : Z_WINS;
        }
        // cols
        rows = 0;
        cols = 0;
        for (cols = 0; cols < BOARD_SIZE; cols++) {

            rows = 0;
            while (rows < BOARD_SIZE - 1) {
                text1 = buttons[rows][cols].getText();
                text2 = buttons[rows + 1][cols].getText();
                if (text1.length() == 0 || !text1.equals(text2)) {
                    break;
                }
                rows++;
            }

            if (rows == BOARD_SIZE - 1)
                return text1.equals("X") ? X_WINS : Z_WINS;
        }
        // diagonal1
        rows = 0;
        cols = 0;
        while(rows<BOARD_SIZE-1) {



            text1 = buttons[rows][cols].getText();
            text2 = buttons[rows + 1][cols + 1].getText();
            if (text1.length() == 0 || !text1.equals(text2)) {
                break;}

            cols++;

            rows++;
        }
        if (rows == BOARD_SIZE - 1)
            return text1.equals("X") ? X_WINS : Z_WINS;

        // diagonal 2
        cols = BOARD_SIZE - 1;
        rows=0;
        while(rows<BOARD_SIZE-1) {


            text1 = buttons[rows][cols].getText();
            text2 = buttons[rows + 1][cols - 1].getText();
            if (text1.length() == 0 || !text1.equals(text2)) {
                break;
            }
            cols--;

            rows++;}

        if (rows == BOARD_SIZE-1)
            return text1.equals("X") ? X_WINS : Z_WINS;

        for(rows=0; rows<BOARD_SIZE; rows++){
            for(cols=0; cols<BOARD_SIZE-1; cols++){
                if(this.buttons[rows][cols].getText().length()==0)
                    return INCOMPLETE;
            }
        }

        return TIE;
    }

}
