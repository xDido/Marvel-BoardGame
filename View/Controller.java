package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import engine.*;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.text.StyledDocument;

import View.*;
import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.world.*;

public class Controller implements ActionListener, Gamelistener, FirstFrameListener {
	Game game;
	private FirstFrame view;
	String name;
	String maxHP;
	String ch;
	String mana;
	String acpt;
	String capt;
	String atr;
	String ad;
	String sp;
	Champion champ = null;
	String large;
	int i = 0;
	boardview bv;
	window w = new window();
	String t = new String();
	String s = new String();

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public boolean isSecondselection() {
		return secondselection;
	}

	public void setSecondselection(boolean secondselection) {
		this.secondselection = secondselection;
	}

	private Player p1;
	private Player p2;
	boolean champselect = false;
	boolean leaderselect = false;
	boolean firstselection = true;
	boolean secondselection = true;
	ArrayList<Champion> a = new ArrayList<Champion>();
	ArrayList<Champion> b = new ArrayList<Champion>();

	public static void main(String[] args) throws IOException {
		new Controller();

	}

	@SuppressWarnings("static-access")
	public Controller() throws IOException {
		this.view = new FirstFrame();
		view.setListener(this);
	}

	public void onStartGame(String FirstName, String SecondName) {
		Player p1 = new Player(FirstName);
		Player p2 = new Player(SecondName);
		game = new Game(p1, p2);
		this.game = game;
		this.p1 = p1;
		this.p2 = p2;
		a = game.getFirstPlayer().getTeam();
		b = game.getSecondPlayer().getTeam();
		try {
			onChampSelect(game, view);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public void onChampSelect(Game game, FirstFrame view) throws IOException {
		game.setListener(this);
		game.loadAbilities("Abilities.csv");
		game.loadChampions("Champions.csv");
		view.getFINISH().addActionListener(this);
		view.getThirdPanel().add(view.getFINISH());
		for (int i = 0; i < game.getAvailableChampions().size(); i++) {
			JButton b = new JButton();
			b.setFocusable(false);
			b.setForeground(Color.CYAN);
			b.setBackground(Color.darkGray);
			b.setText(game.getAvailableChampions().get(i).getName());
			view.getChampSelectPanel().add(b);
			b.addActionListener(this);
			b.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseEntered(java.awt.event.MouseEvent evt) {
					String c = ((AbstractButton) evt.getComponent()).getText();
					switch (c) {
					case "Captain America":
						champ = game.getAvailableChampions().get(0);
						break;
					case "Deadpool":
						champ = game.getAvailableChampions().get(1);
						break;
					case "Dr Strange":
						champ = game.getAvailableChampions().get(2);
						break;
					case "Electro":
						champ = game.getAvailableChampions().get(3);
						break;
					case "Ghost Rider":
						champ = game.getAvailableChampions().get(4);
						break;
					case "Hela":
						champ = game.getAvailableChampions().get(5);
						break;
					case "Hulk":
						champ = game.getAvailableChampions().get(6);
						break;
					case "Iceman":
						champ = game.getAvailableChampions().get(7);
						break;
					case "Ironman":
						champ = game.getAvailableChampions().get(8);
						break;
					case "Loki":
						champ = game.getAvailableChampions().get(9);
						break;
					case "Quicksilver":
						champ = game.getAvailableChampions().get(10);
						break;
					case "Spiderman":
						champ = game.getAvailableChampions().get(11);
						break;
					case "Thor":
						champ = game.getAvailableChampions().get(12);
						break;
					case "Venom":
						champ = game.getAvailableChampions().get(13);
						break;
					case "Yellow Jacket":
						champ = game.getAvailableChampions().get(14);
						break;
					}
					update(champ);
				}
			});
		}
		champselect = true;
		view.revalidate();
		view.repaint();

	}

	private void update(Champion c) {
		name = c.getName();
		maxHP = c.getMaxHP() + "";
		ch = c.getCurrentHP() + "";
		mana = c.getMana() + "";
		acpt = c.getMaxActionPointsPerTurn() + "";
		capt = c.getCurrentActionPoints() + "";
		atr = c.getAttackRange() + "";
		ad = c.getAttackDamage() + "";
		sp = c.getSpeed() + "";
		Ability ab1 = c.getAbilities().get(0);
		Ability ab2 = c.getAbilities().get(1);
		Ability ab3 = c.getAbilities().get(2);
		String type = new String();
		String abtype = new String();
		String ab2type = new String();
		String ab3type = new String();
		String ab1amount = new String();
		String ab2amount = new String();
		String ab3amount = new String();
		String ab1time=new String();
		String ab2time=new String();
		String ab3time=new String();
		if (ab1 instanceof DamagingAbility) {
			abtype = "Dmg amount: ";
			ab1amount = "" + ((DamagingAbility) ab1).getDamageAmount();
		}
		if (ab2 instanceof DamagingAbility) {
			ab2type = "Dmg amount: ";
			ab2amount = "" + ((DamagingAbility) ab2).getDamageAmount();
		}
		if (ab3 instanceof DamagingAbility) {
			ab3type = "Dmg amount: ";
			ab3amount = "" + ((DamagingAbility) ab3).getDamageAmount();
		}
		if (ab1 instanceof HealingAbility) {
			abtype = "Heal amount: ";
			ab1amount = "" + ((HealingAbility) ab1).getHealAmount();
		}
		if (ab2 instanceof HealingAbility) {
			ab2type = "Heal amount: ";
			ab2amount = "" + ((HealingAbility) ab2).getHealAmount();
		}
		if (ab3 instanceof HealingAbility) {
			ab3type = "Heal amount: ";
			ab3amount = "" + ((HealingAbility) ab3).getHealAmount();
		}
		if (ab1 instanceof CrowdControlAbility) {
			abtype = "Effect: ";
			ab1amount = ((CrowdControlAbility) ab1).getEffect().getName();
			 ab1time="duration:"+((CrowdControlAbility) ab1).getEffect().getDuration()+"";
		}
		if (ab2 instanceof CrowdControlAbility) {
			ab2type = "Effect: ";
			ab2amount = ((CrowdControlAbility) ab2).getEffect().getName();
		 ab2time="duration:"+((CrowdControlAbility) ab2).getEffect().getDuration()+"";
		}
		if (ab3 instanceof CrowdControlAbility) {
			ab3type = "Effect: ";
			ab3amount = ((CrowdControlAbility) ab3).getEffect().getName();
			ab3time="duration :"+((CrowdControlAbility) ab3).getEffect().getDuration()+"";
		}

		if (c instanceof AntiHero) {
			type = "AntiHero";
		}
		if (c instanceof Hero) {
			type = "Hero";
		}
		if (c instanceof Villain) {
			type = "Villain";
		}
		large = "Current Champion is " + name + "\n" + "Maxhp: " + maxHP + " " + "Current hp :" + ch + " " + "Type: "
				+ type + "\n" + "Mana: " + mana + "\n" + "Max points per turn: " + acpt + " " + "Current Action points:"
				+ capt + "\n" + "Attack damage: " + ad + "   " + "Attack range: " + atr + "\n" + "Speed: " + sp + "\n"
				+ "Ability 1: " + ab1.getName() + "  " + ab1.getCastArea() + "\n" + abtype + ab1amount + " "+ ab1time+"\n"
				+ "ManaCost: " + ab1.getManaCost() + "\n" + "Base Cooldown: " + ab1.getBaseCooldown() + " "
				+ "Current Cooldown: " + ab1.getCurrentCooldown() + "\n" + "Required Action Points: "
				+ ab1.getRequiredActionPoints() + "\n" + "Cast Range: " + ab1.getCastRange() + "\n" + "Ability 2: "
				+ ab2.getName() + "  " + ab2.getCastArea() + "\n" + ab2type + ab2amount +" "+ab2time+ "\n" + "ManaCost: "
				+ ab2.getManaCost() + "\n" + "Base Cooldown: " + ab2.getBaseCooldown() + " " + "Current Cooldown: "
				+ ab2.getCurrentCooldown() + "\n" + "Required Action Points: " + ab2.getRequiredActionPoints() + "\n"
				+ "Cast Range: " + ab2.getCastRange() + "\n" + "Ability 3: " + ab3.getName() + "  " + ab3.getCastArea()
				+ "\n" + ab3type + ab3amount+" "+ ab3time + "\n" + "ManaCost: " + ab3.getManaCost() + "\n" + "Base Cooldown: "
				+ ab3.getBaseCooldown() + " " + "Current Cooldown: " + ab3.getCurrentCooldown() + "\n"
				+ "Required Action Points: " + ab3.getRequiredActionPoints() + "\n" + "Cast Range: "
				+ ab3.getCastRange();
		view.update(large);

	}

	public void onLeaderSelection(ArrayList<Champion> x, Player p) {
		for (int i = 0; i < p.getTeam().size(); i++) {
			JButton b = new JButton();
			b.setFocusable(false);
			b.setForeground(Color.CYAN);
			b.setBackground(Color.darkGray);
			b.setText(p.getTeam().get(i).getName());
			view.getFirstLeaderSelectionPanel().add(b);
			b.addActionListener(this);
		}
		leaderselect = true;
		view.getFourthPanel().add(view.FirstPlayerName);
		view.getFourthPanel().add(view.SecondPlayerName);
		view.getFourthPanel().add(view.getFirstLeaderSelectionPanel());
		view.add(view.getFourthPanel());
		view.revalidate();
		view.repaint();

	}

	public void onSecondLeaderSelection(ArrayList<Champion> x, Player p) {
		view.getFourthPanel().remove(view.getFirstLeaderSelectionPanel());
		for (int i = 0; i < p.getTeam().size(); i++) {
			JButton b = new JButton();
			b.setFocusable(false);
			b.setForeground(Color.CYAN);
			b.setBackground(Color.darkGray);
			b.setText(p.getTeam().get(i).getName());
			view.getSecondLeaderSelectionPanel().add(b);
			b.addActionListener(this);
		}
		leaderselect = true;
		view.getFourthPanel().add(view.getSecondLeaderSelectionPanel());
		view.add(view.getFourthPanel());
		view.revalidate();
		view.repaint();

	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public FirstFrame getView() {
		return view;
	}

	public void setView(FirstFrame view) {
		this.view = view;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (champselect == true) {
			switch (e.getActionCommand()) {
			case "Captain America":
				champ = game.getAvailableChampions().get(0);

				break;
			case "Deadpool":

				champ = game.getAvailableChampions().get(1);

				break;
			case "Dr Strange":

				champ = game.getAvailableChampions().get(2);

				break;
			case "Electro":

				champ = game.getAvailableChampions().get(3);

				break;
			case "Ghost Rider":

				champ = game.getAvailableChampions().get(4);

				break;
			case "Hela":

				champ = game.getAvailableChampions().get(5);

				break;
			case "Hulk":

				champ = game.getAvailableChampions().get(6);

				break;
			case "Iceman":

				champ = game.getAvailableChampions().get(7);

				break;
			case "Ironman":

				champ = game.getAvailableChampions().get(8);

				break;
			case "Loki":

				champ = game.getAvailableChampions().get(9);

				break;
			case "Quicksilver":

				champ = game.getAvailableChampions().get(10);

				break;
			case "Spiderman":

				champ = game.getAvailableChampions().get(11);

				break;
			case "Thor":

				champ = game.getAvailableChampions().get(12);

				break;
			case "Venom":

				champ = game.getAvailableChampions().get(13);

				break;
			case "Yellow Jacket":

				champ = game.getAvailableChampions().get(14);
				break;
			}
			i++;
			if (i < 4) {
				a.add(champ);
				t += champ.getName() + "\n";
			}
			if (i > 3) {
				b.add(champ);
				s += champ.getName() + "\n";
			}

			if (i == 6) {
				champselect = false;
				i = 0;
			}
			((JButton) e.getSource()).setVisible(false);
			view.getFirstPlayerSelectedChampions().setText(t);
			view.getSecondPlayerSelectedChampions().setText(s);
			view.revalidate();
			view.repaint();
		}

		if (e.getActionCommand().equals("FINISH")) {
			try {
				view.remove(view.getThirdPanel());
				onLeaderSelection(a, game.getFirstPlayer());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog((Component) e.getSource(), "Please select 3 champions");
			}

		}
		if (leaderselect == true) {
			for (int i = 0; i < 3; i++) {
				Champion champ = game.getFirstPlayer().getTeam().get(i);
				String champname1 = champ.getName();
				Champion champ2 = game.getSecondPlayer().getTeam().get(i);
				String champname2 = champ2.getName();

				if (e.getActionCommand().equals(champname1) && firstselection == true) {
					game.getFirstPlayer().setLeader(champ);
					
					firstselection = false;
					onSecondLeaderSelection(b, game.getSecondPlayer());
				}
				if (e.getActionCommand().equals(champname2) && secondselection == true) {
					game.getSecondPlayer().setLeader(champ2);
					
					view.remove(view.getSecondLeaderSelectionPanel());
					secondselection = false;
					leaderselect = false;
					view.revalidate();
					view.repaint();

				}

			}

		}
	}

	public void set(Game g) {
    
	}

}
