
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class Game extends Applet implements ActionListener {

    JButton yourTurn;
    JButton start;
    JButton terminate;
    JButton updatePlayerList;
    JButton invitePlayer;
    JButton acceptInvite;
    JButton declineInvite;
    JButton resign;
    JButton getBoard;
    JTextField invitor;
    JComboBox playerList;
    JComboBox hosts;
    JComboBox handle;
    JLabel invitor_label;
    Board board;
    MessageHandler mh;

    public void init() {
        mh = null;

        setLayout(new BorderLayout());

        add("Center", board = new Board());

        JPanel localPanel = new JPanel();
        localPanel.setLayout(new GridLayout(24, 1));
        localPanel.add(this.handle = new JComboBox());
        localPanel.add(this.hosts = new JComboBox());
        localPanel.add(this.start = new JButton("Start"));
        localPanel.add(this.terminate = new JButton("Stop"));
        localPanel.add(this.yourTurn = new JButton("     "));
        localPanel.add(new JLabel(""));
        localPanel.add(this.updatePlayerList = new JButton("Update"));
        localPanel.add(this.playerList = new JComboBox());
        localPanel.add(this.invitePlayer = new JButton("Invite"));
        localPanel.add(new JLabel(""));
        localPanel.add(this.invitor_label = new JLabel("Invitor", 0));
        localPanel.add(this.invitor = new JTextField());
        localPanel.add(this.acceptInvite = new JButton("Accept"));
        localPanel.add(this.declineInvite = new JButton("Decline"));
        localPanel.add(new JLabel(""));
        localPanel.add(this.resign = new JButton("Resign"));
        localPanel.add(this.getBoard = new JButton("Get Board"));
        add("East", localPanel);

        setSize(820, 640);
        this.yourTurn.addActionListener(this);
        this.yourTurn.setBackground(Color.red);

        this.start.addActionListener(this);
        this.start.setEnabled(true);

        this.terminate.addActionListener(this);
        this.terminate.setEnabled(false);

        this.acceptInvite.setEnabled(false);
        this.acceptInvite.addActionListener(this);

        this.declineInvite.setEnabled(false);
        this.declineInvite.addActionListener(this);

        this.invitor.setEnabled(false);
        this.invitor_label.setEnabled(false);
        this.invitor.setEditable(false);
        this.invitor.setFont(new Font("TimesRoman", 1, 16));

        this.resign.addActionListener(this);
        this.resign.setEnabled(false);
        this.getBoard.addActionListener(this);
        this.getBoard.setEnabled(false);
        this.updatePlayerList.addActionListener(this);
        this.updatePlayerList.setEnabled(false);
        this.invitePlayer.addActionListener(this);
        this.invitePlayer.setEnabled(false);


        this.hosts.setEditable(true);
        this.hosts.addItem("localhost");
        this.hosts.addItem("gauss.ececs.uc.edu");
        this.hosts.addItem("helios.ececs.uc.edu");
        this.handle.addItem("player_1");
        this.handle.addItem("player_2");
        this.handle.addItem("player_3");
        this.handle.addItem("player_4");
        this.handle.addItem("franco");
        addMouseListener(this.board);
        addMouseMotionListener(this.board);

        board.addChecker(0*80, 1, false, true);
        board.addChecker(0, 3, false, true);
        board.addChecker(0, 5, false, true);
        board.addChecker(0, 7, false, true);
        board.addChecker(1, 0, false, true);
        board.addChecker(1, 2, false, true);
        board.addChecker(1, 4, false, true);
        board.addChecker(1, 6, false, true);
        board.addChecker(2, 1, false, true);
        board.addChecker(2, 3, false, true);
        board.addChecker(2, 5, false, true);
        board.addChecker(2, 7, false, true);

        board.addChecker(5, 0, false, false);
        board.addChecker(5, 2, false, false);
        board.addChecker(5, 4, false, false);
        board.addChecker(5, 6, false, false);
        board.addChecker(6, 1, false, false);
        board.addChecker(6, 3, false, false);
        board.addChecker(6, 5, false, false);
        board.addChecker(6, 7, false, false);
        board.addChecker(7, 0, false, false);
        board.addChecker(7, 2, false, false);
        board.addChecker(7, 4, false, false);
        board.addChecker(7, 6, false, false);


        board.setImage();
        board.paint(board.getGraphics());

    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == start) {
            startCommand();
        } else if (evt.getSource() == terminate) {
            terminate();
            this.terminate.setEnabled(false);
            this.start.setEnabled(true);
            this.handle.setEnabled(true);
            this.hosts.setEnabled(true);
        } else if (evt.getSource() == updatePlayerList) {
            updatePlayerList();
        } else if (evt.getSource() == invitePlayer) {
            invitePlayer();
        } else if (evt.getSource() == acceptInvite) {
            acceptInvite();
        } else if (evt.getSource() == declineInvite) {
            declineInvite();
        } else if (evt.getSource() == resign) {
            resign();
        } else if (evt.getSource() == getBoard) {
            getBoard();
        }
    }

    private void startCommand() {
        mh = new MessageHandler(this);
        mh.start();
    }

    private void terminate() {
        this.updatePlayerList.setEnabled(false);
        this.invitePlayer.setEnabled(false);
        this.declineInvite.setEnabled(false);
        this.acceptInvite.setEnabled(false);
        this.updatePlayerList.setEnabled(false);
        this.invitor.setText("");
        this.resign.setEnabled(false);
        this.getBoard.setEnabled(false);
        this.playerList.removeAllItems();


        mh.terminate();
    }

    public void updatePlayerList() {
        mh.getPlayerList();
    }

    private void invitePlayer() {
        String available = ((String) this.playerList.getSelectedItem()).split(" ")[1];
        String opponent = ((String) this.playerList.getSelectedItem()).split(" ")[0];
        if (available.equals("No")) {
            JOptionPane.showMessageDialog(this, opponent + " is unable to play.");
        } else if (this.handle.getSelectedItem().equals(opponent)) {
            JOptionPane.showMessageDialog(this, "You cannnot challenge yourself, silly!");
        } else if (this.handle.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "No opponent selected");
        } else {
            this.invitePlayer.setEnabled(false);
            mh.invitePlayer(((String) this.playerList.getSelectedItem()).split(" ")[0]);
        }
    }

    private void acceptInvite() {
        this.mh.acceptInvite(this.invitor.getText());
        this.acceptInvite.setEnabled(false);
        this.declineInvite.setEnabled(false);
        this.terminate.setEnabled(false);
        this.resign.setEnabled(true);
        this.invitePlayer.setEnabled(false);
        this.getBoard.setEnabled(true);
    }

    private void declineInvite() {
        this.mh.declineInvite(this.invitor.getText());
        this.acceptInvite.setEnabled(false);
        this.declineInvite.setEnabled(false);
        this.invitor.setText("");
    }

    private void resign() {
        this.mh.resign();
        this.invitePlayer.setEnabled(true);
        this.resign.setEnabled(false);
    }

    public void getBoard() {
        mh.expectingBoard = true;
        this.mh.sendGetBoard();
    }
}
