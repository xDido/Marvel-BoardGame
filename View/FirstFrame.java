package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import model.world.Champion;
import engine.*;

public class FirstFrame extends JFrame implements ActionListener {

	private JPanel FirstPanel;
	private JPanel SecondPanel;
	private JPanel ThirdPanel;
	private JPanel FourthPanel;
	private boardview bv;
	window w;

	public boardview getBv() {
		return bv;
	}

	public void setBv(boardview bv) {
		this.bv = bv;
	}

	public JPanel getFourthPanel() {
		return FourthPanel;
	}

	public void setFourthPanel(JPanel fourthPanel) {
		FourthPanel = fourthPanel;
	}

	public JPanel getFirstLeaderSelectionPanel() {
		return FirstLeaderSelectionPanel;
	}

	public JPanel getSecondLeaderSelectionPanel() {
		return SecondLeaderSelectionPanel;
	}

	public void setSecondLeaderSelectionPanel(JPanel secondLeaderSelectionPanel) {
		SecondLeaderSelectionPanel = secondLeaderSelectionPanel;
	}

	public void setFirstLeaderSelectionPanel(JPanel firstLeaderSelectionPanel) {
		FirstLeaderSelectionPanel = firstLeaderSelectionPanel;
	}

	private JButton FINISH;

	public JButton getFINISH() {
		return FINISH;
	}

	public void setFINISH(JButton fINISH) {
		FINISH = fINISH;
	}

	private JPanel FirstLeaderSelectionPanel;
	private JPanel SecondLeaderSelectionPanel;
	private JPanel ChampSelectPanel;
	private JPanel fifthPanel;

	private JTextField text2;
	private JTextField text1;
	private String FirstName;
	private String SecondName;
	private JTextArea FirstPlayerSelectedChampions;
	private JTextArea SecondPlayerSelectedChampions;
	JLabel FirstPlayerName;
	JLabel SecondPlayerName;
	JLabel MarvelLogo;
	private JTextArea ChampDetailstext;
	private Controller listener;
	private JButton Done;

	public void setListener(Controller Listener) {
		this.listener = Listener;
	}

	public JPanel getChampSelectPanel() {
		return ChampSelectPanel;
	}

	public String getFirstName() {
		return FirstName;
	}

	public String getSecondName() {
		return SecondName;
	}

	public FirstFrame() {
		this.setTitle("Chaos Arena");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(800, 500);
		this.getContentPane().setBackground(Color.decode("#2A75B3"));
		this.setResizable(false);
		JLabel WelcomeMessage = new JLabel("Welcome to marvel game");
		WelcomeMessage.setBackground(Color.decode("#B22234"));
		WelcomeMessage.setVerticalAlignment(JLabel.CENTER);
		WelcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		WelcomeMessage.setOpaque(true);
		WelcomeMessage.setForeground(Color.black);
		WelcomeMessage.setBounds(200, 19, 400, 200);
		WelcomeMessage.setFont(new Font("Serif", Font.BOLD, 30));
		WelcomeMessage.setFocusable(false);

		JButton Start = new JButton();
		Start.setBounds(320, 300, 170, 50);
		Start.setText("Start");
		Start.setFocusable(false);
		Start.setForeground(Color.CYAN);
		Start.setBackground(Color.darkGray);
		Start.addActionListener(this);

		FirstPanel = new JPanel();
		FirstPanel.setBackground(Color.decode("#2A75B3"));
		FirstPanel.setBounds(0, 0, 900, 600);
		FirstPanel.setLayout(null);
		FirstPanel.add(WelcomeMessage);
		FirstPanel.add(Start);

		JLabel Player1 = new JLabel("First Player please enter your name :");
		Player1.setFont(new Font("Serif", Font.BOLD, 22));
		Player1.setBounds(60, 200, 350, 250);
		Player1.setForeground(Color.black);
		Player1.setVisible(true);

		JLabel Player2 = new JLabel("Second Player please enter your name :");
		Player2.setFont(new Font("Serif", Font.BOLD, 22));
		Player2.setBounds(60, 250, 380, 250);
		Player2.setForeground(Color.black);
		Player2.setVisible(true);

		JLabel MarvelLogo = new JLabel();
		MarvelLogo.setBounds(250, 150, 300, 100);
		
		MarvelLogo.setVerticalAlignment(JLabel.TOP);
		MarvelLogo.setHorizontalAlignment(JLabel.CENTER);
		MarvelLogo.setVisible(true);

		text1 = new JTextField();
		text1.setBounds(419, 315, 190, 30);
		text1.setVisible(true);

		text2 = new JTextField();
		text2.setBounds(436, 360, 190, 30);
		text2.setVisible(true);

		JButton Play = new JButton();
		Play.setBounds(350, 500, 170, 50);
		Play.setText("Play");
		Play.setFocusable(false);
		Play.setForeground(Color.CYAN);
		Play.setBackground(Color.darkGray);
		Play.addActionListener(this);

		SecondPanel = new JPanel();
		SecondPanel.setBackground(Color.decode("#2A75B3"));
		SecondPanel.setBounds(0, 0, 900, 600);
		SecondPanel.setLayout(null);
		SecondPanel.add(Player1);
		SecondPanel.add(Player2);
		SecondPanel.add(text1);
		SecondPanel.add(text2);
		SecondPanel.add(Play);
		SecondPanel.add(MarvelLogo);

		FirstPlayerName = new JLabel();
		FirstPlayerName.setForeground(Color.black);
		FirstPlayerName.setBounds(0, 0, 400, 20);
		FirstPlayerName.setFont(new Font("Serif", Font.BOLD, 22));
		FirstPlayerName.setVisible(true);
		FirstPlayerName.setFocusable(false);

		SecondPlayerName = new JLabel();
		SecondPlayerName.setForeground(Color.black);
		SecondPlayerName.setBounds(1730, 0, 400, 20);
		SecondPlayerName.setFont(new Font("Serif", Font.BOLD, 22));
		SecondPlayerName.setVisible(true);
		SecondPlayerName.setFocusable(false);

		FINISH = new JButton();
		FINISH.setBounds(800, 850, 170, 50);
		FINISH.setText("FINISH");
		FINISH.setFocusable(false);
		FINISH.setForeground(Color.CYAN);
		FINISH.setBackground(Color.darkGray);

		ThirdPanel = new JPanel();
		ThirdPanel.setBackground(Color.decode("#2A75B3"));
		ThirdPanel.setBounds(0, 0, 1900, 1000);
		ThirdPanel.setLayout(null);
		ThirdPanel.add(FirstPlayerName);
		ThirdPanel.add(SecondPlayerName);

		ChampDetailstext = new JTextArea();
		ChampDetailstext.setBounds(1050, 40, 300, 460);
		ChampDetailstext.setEditable(false);
		ChampDetailstext.setFont(new Font("Serif", Font.BOLD, 14));
		ChampDetailstext.setFocusable(false);

		ChampSelectPanel = new JPanel();
		ChampSelectPanel.setBackground(Color.decode("#B22234"));
		ChampSelectPanel.setBounds(550, 40, 500, 400);
		ChampSelectPanel.setLayout(new GridLayout(0, 3));

		FourthPanel = new JPanel();
		FourthPanel.setBackground(Color.decode("#2A75B3"));
		FourthPanel.setBounds(0, 0, 1900, 1000);
		FourthPanel.setLayout(null);
		FourthPanel.add(FirstPlayerName);
		FourthPanel.add(SecondPlayerName);

		FirstLeaderSelectionPanel = new JPanel();
		FirstLeaderSelectionPanel.setBackground(Color.decode("#B22234"));
		FirstLeaderSelectionPanel.setBounds(650, 400, 400, 100);
		FirstLeaderSelectionPanel.setLayout(new GridLayout(0, 3));

		SecondLeaderSelectionPanel = new JPanel();
		SecondLeaderSelectionPanel.setBackground(Color.decode("#B22234"));
		SecondLeaderSelectionPanel.setBounds(650, 400, 400, 100);
		SecondLeaderSelectionPanel.setLayout(new GridLayout(0, 3));

		Done = new JButton();
		Done.setBounds(800, 850, 170, 50);
		Done.setText("Done");
		Done.setFocusable(false);
		Done.setForeground(Color.CYAN);
		Done.setBackground(Color.darkGray);
		Done.addActionListener(this);

		FirstPlayerSelectedChampions = new JTextArea();
		FirstPlayerSelectedChampions.setBounds(5, 40, 150, 150);
		FirstPlayerSelectedChampions.setEditable(false);
		FirstPlayerSelectedChampions.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		FirstPlayerSelectedChampions.setFocusable(false);

		SecondPlayerSelectedChampions = new JTextArea();
		SecondPlayerSelectedChampions.setBounds(1650, 40, 150, 150);
		SecondPlayerSelectedChampions.setEditable(false);
		SecondPlayerSelectedChampions.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
		SecondPlayerSelectedChampions.setFocusable(false);

		ThirdPanel.add(FirstPlayerName);
		ThirdPanel.add(SecondPlayerName);
		ThirdPanel.add(FirstPlayerSelectedChampions);
		ThirdPanel.add(SecondPlayerSelectedChampions);
		ThirdPanel.add(ChampSelectPanel, BorderLayout.CENTER);
		ThirdPanel.add(ChampDetailstext, BorderLayout.CENTER);
		this.add(FirstPanel);
		this.setVisible(true);
		FourthPanel.add(Done);
		update("Empty");
	}

	public JTextArea getSecondPlayerSelectedChampions() {
		return SecondPlayerSelectedChampions;
	}

	public JTextArea getFirstPlayerSelectedChampions() {
		return FirstPlayerSelectedChampions;
	}

	public JPanel getThirdPanel() {
		return ThirdPanel;
	}

	public void setThirdPanel(JPanel thirdPanel) {
		ThirdPanel = thirdPanel;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start")) {
			this.remove(FirstPanel);
			this.add(SecondPanel);
			this.revalidate();
			this.repaint();
			this.setSize(800, 600);
		}

		if (e.getActionCommand().equals("Play")) {

			String FirstName = text1.getText();
			String SecondName = text2.getText();
			this.setSize(1820, 950);
			this.remove(SecondPanel);
			FirstPlayerName.setText(FirstName);
			SecondPlayerName.setText(SecondName);
			this.add(ThirdPanel);
			listener.onStartGame(FirstName, SecondName);
			this.revalidate();
			this.repaint();

		}
		if (e.getActionCommand().equals("Done")) {

			if (listener.getP1().getLeader() == null || listener.getP2().getLeader() == null)
				JOptionPane.showMessageDialog(this, "Please Select a Leader");

			else {
				this.setVisible(false);
				this.bv = new boardview(listener.getGame(), listener.getP1(), listener.getP2());

			}

		}

	}

	public void update(String m) {
		this.ChampDetailstext.setText(m);

	}

	public static void main(String[] args) {
		FirstFrame g = new FirstFrame();
	}

}
