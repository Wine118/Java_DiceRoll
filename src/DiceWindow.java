import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiceWindow extends JFrame {

    JLabel diceLabel1, diceLabel2,titleLabel;
    DiceRollGameLogic logic;
    JButton rollButton;

    public enum GameState {
        NOT_STARTED, PLAYER1_TURN, PLAYER2_TURN, FINISHED
    }

    GameState state = GameState.NOT_STARTED;


    public DiceWindow() {
        this.logic = new DiceRollGameLogic();

        setTitle("Dice Roll Game");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(null); // We'll place components manually for now


//        Title Panel
        JPanel titlepanel = new JPanel();
        titlepanel.setLayout(new GridLayout());
        titlepanel.setBackground(Color.LIGHT_GRAY);
        titlepanel.setBounds(50,30,450,70);
        add(titlepanel);

        titleLabel = new JLabel("Start Game");
        titleLabel.setFont(new Font("Arial",Font.BOLD,24));
        titleLabel.setForeground(Color.MAGENTA);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlepanel.add(titleLabel);
        add(titlepanel);


        // Dice Panel 1
        JPanel dicePanel1 = new JPanel();
        dicePanel1.setBackground(Color.LIGHT_GRAY); // Just for visibility
        dicePanel1.setBounds(50, 150, 200, 200); // x, y, width, height

//        Add dice image to dicePanel1
        ImageIcon diceIcon1 = new ImageIcon("D:\\Java\\JavaGreatProjects\\DiceRoll\\DiceRoll\\src\\dice.png");

//        Resize it to fit 200x200 panel
        Image scaledImage = diceIcon1.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

//        Use resized icaon
        JLabel diceLabel1 = new JLabel(resizedIcon);
        dicePanel1.add(diceLabel1);

        add(dicePanel1);

        // Dice Panel 2
        JPanel dicePanel2 = new JPanel();
        dicePanel2.setBackground(Color.GRAY); // Different shade for second dice
        dicePanel2.setBounds(300, 150, 200, 200);
        JLabel diceLabel2 = new JLabel(resizedIcon);
        dicePanel2.add(diceLabel2);
        add(dicePanel2);


//Buton Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBounds(210,400,100,50);

        rollButton = new JButton("Start Game");
        buttonPanel.add(rollButton);
        add(buttonPanel);

//        Button Action
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switch (state) {
                    case NOT_STARTED:
                        String p1 = JOptionPane.showInputDialog(null, "Enter Player 1 Name: ");
                        if(p1 != null && !p1.trim().isEmpty()) {
                            logic.setPlayer1Name(p1.trim());
                        }

                        String p2 = JOptionPane.showInputDialog(null, "Enter Player 2 Name: ");
                        if(p2 != null && !p2.trim().isEmpty()) {
                            logic.setPlayer2Name(p2.trim());
                        }
                        titleLabel.setText(logic.getPlayer1Name()+"'s Turn");
                        rollButton.setText("Roll");
                        state = GameState.PLAYER1_TURN;
                        break;

                    case PLAYER1_TURN:
                        int[] player1Dice = logic.rollForPlayer1();
                        diceLabel1.setIcon(resizeImage("src/dice" + player1Dice[0] + ".JPG"));
                        diceLabel2.setIcon(resizeImage("src/dice" + player1Dice[1] + ".JPG"));
                        titleLabel.setText(logic.getPlayer2Name()+"'s Turn");
                        state = GameState.PLAYER2_TURN;
                        break;

                    case PLAYER2_TURN:
                        int[] player2Dice = logic.rollForPlayer2();
                        diceLabel1.setIcon(resizeImage("src/dice" + player2Dice[0] + ".JPG"));
                        diceLabel2.setIcon(resizeImage("src/dice" + player2Dice[1] + ".JPG"));

                        String winner = logic.getWinner();
                        titleLabel.setText(winner);
                        rollButton.setText("Restart Game");
                        state = GameState.FINISHED;
                        break;

                    case FINISHED:
                        // Reset game
                        titleLabel.setText(logic.getPlayer1Name() + "'s Turn");
                        rollButton.setText("Restart");
                        diceLabel1.setIcon(resizeImage("src/dice.png")); // Reset dice images
                        diceLabel2.setIcon(resizeImage("src/dice.png"));
                        state = GameState.PLAYER1_TURN;
                        break;
                }
            }
        });

        setVisible(true); // Show the window
    }

    private ImageIcon resizeImage(String path) {
        //                Load and scale new image(e.g., dice1.png)
        ImageIcon newIcon = new ImageIcon(path);
        Image scaled = newIcon.getImage().getScaledInstance(200,200,Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }


}
