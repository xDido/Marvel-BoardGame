package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.*;

import model.abilities.*;
import model.effects.Effect;
import model.world.*;
import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.*;

public class boardview extends JFrame implements ActionListener, KeyListener {
	JLabel a;
	Player p1;
	Player p2;
	window w;
	JPanel TitlePanel;
	Controller listener;
	private JPanel FirstPanel;
	private JPanel SecondPanel;
	private JLabel firstplayername;
	private JLabel secondplayername;
	private JTextArea ChampDetailstext;
	private JTextArea ChampDetailstext2;
	private JTextArea ChampDetailstext3;
	JPanel ChampText;
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
	JPanel ActionPanel;
	Game game;
	Champion turnchamp;
	JLabel Abilityused;
	JButton useability;
	Champion currentchamp;
	String Largeability = "";
	JPanel p;
	JCheckBox checkbox;
	private JLabel[][] cells = new JLabel[5][5];
	String champdetails = new String();
	String chd;
	JTextArea ChampDetails;
	private boolean onboard = false;
	private boolean onCastAbility = false;
	JPanel CastAbilities;
	JButton Ability1;
	JButton Ability2;
	JButton Ability3;
	JPanel Directions;
	boolean onDirection = false;
	Direction d;
	JButton CastAbility;
	Ability inUse;
	private PriorityQueue q = new PriorityQueue(6);
	JLabel TurnOrder;
	int l = 0;
	ArrayList<Comparable<?>> s = new ArrayList<Comparable<?>>();

	JTextField xs;
	JTextField ys;
	JLabel x;
	JLabel y;
	JPanel singleTarget;
	private Ability singletarget;
	Ability ab4;
	JButton AbilityPunch;
	Ability ab3;
	Ability ab1;
	Ability ab2;

	public void setOnboard(boolean onboard) {
		this.onboard = onboard;
	}

	public void setListener(Controller Listener) {
		this.listener = Listener;
	}

	public boardview(Game g, Player p1, Player p2) {
		this.game = g;
		this.setP1(p1);
		this.setP2(p2);
		this.game.prepareChampionTurns();
		this.currentchamp = this.game.getCurrentChampion();
		g.placeChampions();
		for (Champion c : p1.getTeam())
			q.insert(c);
		for (Champion c : p2.getTeam())
			q.insert(c);
		for (int i = 0; i < 6; i++) {
			if (q.peekMin() != null) {
				s.add(q.remove());
			}
		}
		System.out.println(p1.getLeader().getName());
		this.setSize(1000, 1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(1000, 1000);
		this.getContentPane().setBackground(Color.decode("#2A75B3"));
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		addKeyListener(this);

		checkbox = new JCheckBox();
		checkbox.setBounds(10, 10, 150, 50);
		checkbox.setText("use LeaderAbility");
		checkbox.setFocusable(false);

		JButton useability = new JButton();
		useability.setFocusable(false);
		useability.setBounds(20, 80, 150, 50);
		useability.setText("use ability");
		useability.setFocusable(false);
		useability.setForeground(Color.CYAN);
		useability.setBackground(Color.darkGray);
		useability.addActionListener(this);

		JButton endturn = new JButton();
		endturn.setFocusable(false);
		endturn.setBounds(20, 230, 150, 50);
		endturn.setText("End turn");
		endturn.setFocusable(false);
		endturn.setForeground(Color.CYAN);
		endturn.setBackground(Color.darkGray);
		endturn.addActionListener(this);

		ActionPanel = new JPanel();
		ActionPanel.setPreferredSize(new Dimension(300, 800));
		ActionPanel.setBackground(Color.black);
		ActionPanel.setLayout(null);
		ActionPanel.add(useability);
		ActionPanel.add(checkbox);
		ActionPanel.add(endturn);

		Ability1 = new JButton();
		Ability1.setFocusable(false);
		Ability1.setBounds(0, 0, 300, 30);
		Ability1.setText("Ability1");
		Ability1.setFocusable(false);
		Ability1.setForeground(Color.CYAN);
		Ability1.setBackground(Color.darkGray);
		Ability1.addActionListener(this);

		Ability2 = new JButton();
		Ability2.setFocusable(false);
		Ability2.setBounds(0, 30, 300, 30);
		Ability2.setText("Ability2");
		Ability2.setFocusable(false);
		Ability2.setForeground(Color.CYAN);
		Ability2.setBackground(Color.darkGray);
		Ability2.addActionListener(this);

		Ability3 = new JButton();
		Ability3.setFocusable(false);
		Ability3.setBounds(0, 60, 300, 30);
		Ability3.setText("Ability3");
		Ability3.setFocusable(false);
		Ability3.setForeground(Color.CYAN);
		Ability3.setBackground(Color.darkGray);
		Ability3.addActionListener(this);

		CastAbility = new JButton();
		CastAbility.setFocusable(false);
		CastAbility.setBounds(20, 280, 150, 50);
		CastAbility.setText("Cast Ability");
		CastAbility.setFocusable(false);
		CastAbility.setForeground(Color.CYAN);
		CastAbility.setBackground(Color.darkGray);
		CastAbility.addActionListener(this);
		ActionPanel.add(CastAbility);

		CastAbilities = new JPanel();
		CastAbilities.setBounds(10, 285, 270, 90);
		CastAbilities.setBackground(Color.CYAN);
		CastAbilities.setLayout(null);
		CastAbilities.add(Ability1);
		CastAbilities.add(Ability2);
		CastAbilities.add(Ability3);

		JButton Right = new JButton();
		Right.setBounds(180, 0, 90, 60);
		Right.setText("Right");
		Right.setFocusable(false);
		Right.setForeground(Color.CYAN);
		Right.setBackground(Color.darkGray);
		Right.addActionListener(this);

		JButton Left = new JButton();
		Left.setBounds(0, 0, 90, 60);
		Left.setText("Left");
		Left.setFocusable(false);
		Left.setForeground(Color.CYAN);
		Left.setBackground(Color.darkGray);
		Left.addActionListener(this);

		JButton Up = new JButton();
		Up.setBounds(90, 0, 90, 30);
		Up.setText("Up");
		Up.setFocusable(false);
		Up.setForeground(Color.CYAN);
		Up.setBackground(Color.darkGray);
		Up.addActionListener(this);

		JButton Down = new JButton();
		Down.setFocusable(false);
		Down.setBounds(90, 30, 90, 30);
		Down.setText("Down");
		Down.setFocusable(false);
		Down.setForeground(Color.CYAN);
		Down.setBackground(Color.darkGray);
		Down.addActionListener(this);

		Directions = new JPanel();
		Directions.setBounds(10, 285, 270, 60);
		Directions.setBackground(Color.CYAN);
		Directions.setLayout(null);
		Directions.add(Right);
		Directions.add(Up);
		Directions.add(Left);
		Directions.add(Down);

		x = new JLabel("X: ");
		x.setBounds(5, 20, 30, 20);
		x.setFont(new Font("Serif", Font.BOLD, 18));
		x.setForeground(Color.black);
		x.setVisible(true);

		xs = new JTextField();
		xs.setBounds(40, 20, 40, 30);
		xs.setVisible(true);

		y = new JLabel("Y: ");
		y.setBounds(90, 20, 40, 20);
		y.setFont(new Font("Serif", Font.BOLD, 18));
		y.setForeground(Color.black);
		y.setVisible(true);

		ys = new JTextField();
		ys.setBounds(120, 20, 40, 30);
		ys.setVisible(true);

		JButton Cast = new JButton();
		Cast.setText("Cast");
		Cast.setBounds(175, 20, 90, 30);
		Cast.setForeground(Color.CYAN);
		Cast.setBackground(Color.darkGray);
		Cast.addActionListener(this);

		singleTarget = new JPanel();
		singleTarget.setBounds(10, 285, 285, 80);
		singleTarget.setBackground(Color.LIGHT_GRAY);
		singleTarget.setLayout(null);
		singleTarget.add(xs);
		singleTarget.add(ys);
		singleTarget.add(x);
		singleTarget.add(y);
		singleTarget.add(Cast);

		JLabel WelcomeMessage = new JLabel(p1.getName());
		WelcomeMessage.setFocusable(false);
		WelcomeMessage.setBackground(Color.decode("#B22234"));
		WelcomeMessage.setVerticalAlignment(JLabel.CENTER);
		WelcomeMessage.setHorizontalAlignment(JLabel.CENTER);
		WelcomeMessage.setOpaque(true);
		WelcomeMessage.setForeground(Color.black);
		WelcomeMessage.setBounds(200, 19, 100, 60);
		WelcomeMessage.setFont(new Font("Serif", Font.BOLD, 30));

		JLabel WelcomeMessage2 = new JLabel(p2.getName());
		WelcomeMessage2.setFocusable(false);
		WelcomeMessage2.setBackground(Color.decode("#B22234"));
		WelcomeMessage2.setVerticalAlignment(JLabel.CENTER);
		WelcomeMessage2.setHorizontalAlignment(JLabel.CENTER);
		WelcomeMessage2.setOpaque(true);
		WelcomeMessage2.setForeground(Color.black);
		WelcomeMessage2.setBounds(400, 10, 100, 60);
		WelcomeMessage2.setFont(new Font("Serif", Font.BOLD, 30));

		TurnOrder = new JLabel();
		TurnOrder.setBounds(1300, 40, 600, 60);
		TurnOrder.setFocusable(false);
		TurnOrder.setForeground(Color.black);
		TurnOrder.setFont(new Font("Arial", Font.BOLD, 16));
		TurnOrder.setText("Next turn for " + returnNext());

		FirstPanel = new JPanel();
		FirstPanel.setBackground(Color.gray);
		FirstPanel.setBounds(0, 0, 1500, 1500);
		FirstPanel.setLayout(null);
		FirstPanel.add(WelcomeMessage);
		FirstPanel.add(WelcomeMessage2);

		firstplayername = new JLabel(game.getFirstPlayer().getName());
		firstplayername.setForeground(Color.black);
		firstplayername.setBounds(0, 0, 400, 20);
		firstplayername.setFont(new Font("Serif", Font.BOLD, 22));
		firstplayername.setVisible(true);
		firstplayername.setFocusable(false);

		secondplayername = new JLabel(game.getSecondPlayer().getName());
		secondplayername.setForeground(Color.black);
		secondplayername.setBounds(1730, 0, 400, 20);
		secondplayername.setFont(new Font("Serif", Font.BOLD, 22));
		secondplayername.setVisible(true);
		secondplayername.setFocusable(false);

		TitlePanel = new JPanel();
		TitlePanel.setLayout(null);
		TitlePanel.setPreferredSize(new Dimension(1800, 100));
		/* TitlePanel.add(titletext) */;
		TitlePanel.add(TurnOrder);
		TitlePanel.add(firstplayername);
		TitlePanel.add(secondplayername);

		ChampText = new JPanel();
		ChampText.setLayout(null);
		ChampText.setPreferredSize(new Dimension(300, 800));
		ChampText.setBackground(Color.lightGray);

		ChampDetailstext3 = new JTextArea();
		ChampDetailstext3.setFocusable(false);
		ChampDetailstext3.setBounds(0, 0, 300, 470);
		ChampDetailstext3.setOpaque(false);
		ChampDetailstext3.setEditable(false);
		ChampDetailstext3.setFont(new Font("Serif", Font.BOLD, 14));
		ChampDetailstext3.setVisible(true);
		ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
		ChampText.add(ChampDetailstext3);

		ChampDetails = new JTextArea();
		ChampDetails.setFocusable(false);
		ChampDetails.setBounds(0, 480, 300, 300);
		ChampDetails.setEditable(false);
		ChampDetails.setFont(new Font("Serif", Font.BOLD, 16));
		ChampDetails.setVisible(true);

		SecondPanel = new JPanel();
		GridLayout lay = new GridLayout(1, 1);
		lay.setHgap(10);
		lay.setVgap(20);
		SecondPanel.setLayout(lay);
		SecondPanel.setBackground(Color.WHITE);
		ChampDetailstext = new JTextArea();
		ChampDetailstext.setFocusable(false);
		ChampDetailstext.setBounds(50, 100, 320, 800);
		ChampDetailstext.setEditable(false);
		ChampDetailstext.setFont(new Font("Serif", Font.BOLD, 22));

		ChampDetailstext2 = new JTextArea();
		ChampDetailstext2.setFocusable(false);
		ChampDetailstext2.setBounds(450, 100, 350, 800);
		ChampDetailstext2.setEditable(false);
		ChampDetailstext2.setFont(new Font("Serif", Font.BOLD, 22));

		for (int i = 0; i < p1.getTeam().size(); i++) {
			Champion c = p1.getTeam().get(i);
			name = c.getName();
			maxHP = c.getMaxHP() + "";
			ch = c.getCurrentHP() + "";
			mana = c.getMana() + "";
			acpt = c.getMaxActionPointsPerTurn() + "";
			capt = c.getCurrentActionPoints() + "";
			atr = c.getAttackRange() + "";
			ad = c.getAttackDamage() + "";
			sp = c.getSpeed() + "";
			large = "name :" + name + "\n" + "Maxhp :" + maxHP + "\n" + "Current hp :" + ch + "\n" + "mana :" + mana
					+ "\n" + "Max action points per turn :" + acpt + "\n" + "Current Action points per turn :" + capt
					+ "\n" + "Attack range :" + atr + "\n" + "Attack damage :" + ad + "\n" + "Speed :" + sp;
			if (i == 0)
				this.ChampDetailstext.setText(large);
			else
				this.ChampDetailstext.append("\n" + large);

		}

		for (int i = 0; i < p2.getTeam().size(); i++) {
			Champion c = p2.getTeam().get(i);
			name = c.getName();
			maxHP = c.getMaxHP() + "";
			ch = c.getCurrentHP() + "";
			mana = c.getMana() + "";
			acpt = c.getMaxActionPointsPerTurn() + "";
			capt = c.getCurrentActionPoints() + "";
			atr = c.getAttackRange() + "";
			ad = c.getAttackDamage() + "";
			sp = c.getSpeed() + "";
			large = "name :" + name + "\n" + "Maxhp :" + maxHP + "\n" + "Current hp :" + ch + "\n" + "mana :" + mana
					+ "\n" + "Max action points per turn :" + acpt + "\n" + "Current Action points per turn :" + capt
					+ "\n" + "Attack range :" + atr + "\n" + "Attack damage :" + ad + "\n" + "Speed :" + sp;
			if (i == 0)
				this.ChampDetailstext2.setText(large);
			else
				this.ChampDetailstext2.append("\n" + large);

		}
		JButton showability = new JButton();
		showability.setFocusable(false);

		showability.setBounds(200, 900, 170, 50);
		showability.setText("Start game!");
		showability.setFocusable(false);
		showability.setForeground(Color.CYAN);
		showability.setBackground(Color.darkGray);
		showability.addActionListener(this);

		FirstPanel.add(ChampDetailstext);
		FirstPanel.add(ChampDetailstext2);
		FirstPanel.add(showability);

		this.add(FirstPanel);
		fillboard();

	}

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

	public static void main(String[] args) {

	}

	public String Details(Champion c) {
		name = c.getName();
		maxHP = c.getMaxHP() + "";
		ch = c.getCurrentHP() + "";
		mana = c.getMana() + "";
		acpt = c.getMaxActionPointsPerTurn() + "";
		capt = c.getCurrentActionPoints() + "";
		atr = c.getAttackRange() + "";
		ad = c.getAttackDamage() + "";
		sp = c.getSpeed() + "";
		Ability ab1 = this.game.getCurrentChampion().getAbilities().get(0);
		Ability ab2 = this.game.getCurrentChampion().getAbilities().get(1);
		Ability ab3 = this.game.getCurrentChampion().getAbilities().get(2);
		String type = new String();
		String e = new String();
		for (Effect o : c.getAppliedEffects()) {
			e += o.getName() + " will last for " + o.getDuration() + " seconds" + "\n";

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
		if (c != this.game.getCurrentChampion() || (ab1.getCastArea() == AreaOfEffect.SELFTARGET
				|| ab2.getCastArea() == AreaOfEffect.SELFTARGET || ab3.getCastArea() == AreaOfEffect.SELFTARGET))
			return "Name :" + name + "  " + "Type: " + type + " (" + c.getLocation().x + "," + c.getLocation().y + ")"
					+ "\n" + "Current hp :" + ch + "\n" + "Mana :" + mana + "\n" + "Current Action points per turn :"
					+ capt + "\n" + "Max points per turn: " + acpt + "\n" + "Speed: " + sp + "\n" + "Attack range :"
					+ atr + "\n" + "Attack damage :" + ad + "\n" + e;
		else
			return "Name :" + name + "  " + "Type: " + type + " (" + c.getLocation().x + "," + c.getLocation().y + ")"
					+ "\n" + "Current hp :" + ch + "\n" + "Mana :" + mana + "\n" + "Current Action points per turn :"
					+ capt + "\n" + "Max points per turn: " + acpt + "\n" + "Speed: " + sp + "\n" + "Attack range :"
					+ atr + "\n" + "Attack damage :" + ad + "\n";

	}

	public String Detailsforcurrent(Champion c) {
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
		String ab1time = new String();
		String ab2time = new String();
		String ab3time = new String();
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
			ab1time = "duration :" + ((CrowdControlAbility) ab1).getEffect().getDuration() + "";
		}
		if (ab2 instanceof CrowdControlAbility) {
			ab2type = "Effect: ";
			ab2amount = ((CrowdControlAbility) ab2).getEffect().getName();
			ab2time = "duration: " + ((CrowdControlAbility) ab2).getEffect().getDuration() + "";
		}
		if (ab3 instanceof CrowdControlAbility) {
			ab3type = "Effect: ";
			ab3amount = ((CrowdControlAbility) ab3).getEffect().getName();
			ab3time = "duration :" + ((CrowdControlAbility) ab3).getEffect().getDuration() + "";
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
		return "Current Champion is " + name + " (" + c.getLocation().x + "," + c.getLocation().y + ")" + "\n"
				+ "Maxhp: " + maxHP + " " + "Current hp :" + ch + " " + "Type: " + type + "\n" + "Mana: " + mana + "\n"
				+ "Max points per turn: " + acpt + " " + "Current Action points:" + capt + "\n" + "Attack damage: " + ad
				+ "   " + "Attack range: " + atr + "\n" + "Speed: " + sp + "\n" + "Ability 1: " + ab1.getName() + "  "
				+ ab1.getCastArea() + "\n" + abtype + ab1amount + " " + ab1time + "\n" + "ManaCost: "
				+ ab1.getManaCost() + "\n" + "Base Cooldown: " + ab1.getBaseCooldown() + " " + "Current Cooldown: "
				+ ab1.getCurrentCooldown() + "\n" + "Required Action Points: " + ab1.getRequiredActionPoints() + "\n"
				+ "Cast Range: " + ab1.getCastRange() + "\n" + "Ability 2: " + ab2.getName() + "  " + ab2.getCastArea()
				+ "\n" + ab2type + ab2amount + " " + ab2time + "\n" + "ManaCost: " + ab2.getManaCost() + "\n"
				+ "Base Cooldown: " + ab2.getBaseCooldown() + " " + "Current Cooldown: " + ab2.getCurrentCooldown()
				+ "\n" + "Required Action Points: " + ab2.getRequiredActionPoints() + "\n" + "Cast Range: "
				+ ab2.getCastRange() + "\n" + "Ability 3: " + ab3.getName() + "  " + ab3.getCastArea() + "\n" + ab3type
				+ ab3amount + " " + ab3time + "\n" + "ManaCost: " + ab3.getManaCost() + "\n" + "Base Cooldown: "
				+ ab3.getBaseCooldown() + " " + "Current Cooldown: " + ab3.getCurrentCooldown() + "\n"
				+ "Required Action Points: " + ab3.getRequiredActionPoints() + "\n" + "Cast Range: "
				+ ab3.getCastRange();

	}

	public void fillboard() {
		if (p != null) {
			SecondPanel.remove(p);
		}
		p = new JPanel();
		p.setLayout(new GridLayout(5, 5));
		SecondPanel.add(p);
		for (Object[] o : game.getBoard()) {
			for (Object oo : o) {

				if (oo instanceof Cover) {
					ifCover(p, (Cover) oo);
				}
				if (oo instanceof Champion) {
					ifChampion(p, (Champion) oo);
				}
				if (oo == null) {
					ifnull(p, oo);
				}

			}
		}
		revalidate();
		repaint();
	}

	public void ifCover(JPanel l, Cover o) {

		JButton button = new JButton();
		button.setFocusable(false);
		button.setText((o.getCurrentHP() + ""));
		l.add(button);

	}

	public void ifChampion(JPanel l, Champion o) {
		JButton button = new JButton();
		button.setFocusable(false);
		if (p1.getTeam().contains(o))
			button.setBackground(Color.decode("#6a5acd"));
		if (p2.getTeam().contains(o))
			button.setBackground(Color.decode("#B22234"));

		if (p1.getLeader().getName().equals(o.getName()))
			button.setBackground(Color.decode("#800080"));
		if (p2.getLeader().getName().equals(o.getName()))
			button.setBackground(Color.decode("#8b0000"));

		button.setText((o.getName()));
		button.setForeground(Color.decode("#FBCB0A"));
		button.setFont(new Font("Arial", Font.BOLD, 16));
		l.add(button);
		button.addActionListener(this);

	}

	public void ifnull(JPanel l, Object o) {
		JButton button = new JButton();
		button.setFocusable(false);
		button.setBackground(Color.decode("#293462"));
		l.add(button);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start game!")) {
			this.FirstPanel.remove(ChampDetailstext);
			this.FirstPanel.remove(ChampDetailstext2);
			this.remove(FirstPanel);
			this.add(TitlePanel, BorderLayout.NORTH);
			this.add(ChampText, BorderLayout.EAST);
			this.add(SecondPanel, BorderLayout.CENTER);
			this.add(ActionPanel, BorderLayout.WEST);
			this.revalidate();
			this.repaint();
			this.setSize(1300, 1300);
			this.setOnboard(true);

		}
		if (e.getActionCommand().equals("use ability")) {
			if (checkbox.isSelected()) {
				try {
					game.useLeaderAbility();
					ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
				} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
				}
			}
		}
		if (onboard == true) {

			for (Champion o : p1.getTeam()) {
				if (e.getActionCommand().equals(o.getName())) {

					champdetails = Details(o);
				}

			}
			for (Champion oo : p2.getTeam()) {
				if (e.getActionCommand().equals(oo.getName())) {

					champdetails = Details(oo);
				}

			}
			ChampDetails.setText(champdetails);
			ChampText.add(ChampDetails);
			ChampDetails.revalidate();
			ChampDetails.repaint();
		}

		if (e.getActionCommand().equals("End turn")) {
			ActionPanel.remove(CastAbilities);
			if (AbilityPunch != null) {
				ActionPanel.remove(AbilityPunch);
			}
			CastAbility.setVisible(true);
			this.game.endTurn();
			ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
			l++;
			TurnOrder.setText("Next turn is for " + returnNext());
			if (game.checkGameOver() != null) {
				int res = JOptionPane.showOptionDialog(this, game.checkGameOver().getName() + " is the winner!",
						"Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (res == 0) {
					this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
				}
			}
			this.revalidate();
			this.repaint();
		}
		if (e.getActionCommand().equals("Cast Ability")) {
			((JButton) e.getSource()).setVisible(false);
			ActionPanel.add(CastAbilities);
			onCastAbility = true;
			for (Ability a : game.getCurrentChampion().getAbilities()) {
				if (a.getName().equals("Punch")) {
					ab4 = this.game.getCurrentChampion().getAbilities().get(3);
					AbilityPunch = new JButton();
					AbilityPunch.setFocusable(false);
					AbilityPunch.setBounds(10, 375, 270, 25);
					AbilityPunch.setText("Punch");
					AbilityPunch.setFocusable(false);
					AbilityPunch.setForeground(Color.CYAN);
					AbilityPunch.setBackground(Color.darkGray);
					AbilityPunch.addActionListener(this);
					ActionPanel.add(AbilityPunch);

				}

			}
			ActionPanel.revalidate();
			ActionPanel.repaint();

		}
		if (e.getActionCommand().equals("Cast")) {
			int xcor = Integer.parseInt(xs.getText());
			int ycor = Integer.parseInt(ys.getText());
			try {
				game.castAbility(singletarget, xcor, ycor);
			} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			ActionPanel.remove(singleTarget);
			CastAbility.setVisible(true);
			fillboard();
			ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
			System.out.println("cast used");
			ActionPanel.revalidate();
			ActionPanel.repaint();
		}
		if (onCastAbility) {
			Ability ab1 = this.game.getCurrentChampion().getAbilities().get(0);
			Ability ab2 = this.game.getCurrentChampion().getAbilities().get(1);
			Ability ab3 = this.game.getCurrentChampion().getAbilities().get(2);
			Ability1.setText(ab1.getName());
			Ability2.setText(ab2.getName());
			Ability3.setText(ab3.getName());
			if (e.getActionCommand().equals(ab1.getName())) {
				if (ab1.getCastArea() == AreaOfEffect.SELFTARGET || ab1.getCastArea() == AreaOfEffect.TEAMTARGET
						|| ab1.getCastArea() == AreaOfEffect.SURROUND) {
					try {
						ActionPanel.remove(CastAbilities);
						game.castAbility(ab1);
						ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
						fillboard();
					} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
					}
					CastAbility.setVisible(true);
				}
				if (ab1.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					ActionPanel.remove(CastAbilities);
					ActionPanel.add(Directions);
					onDirection = true;
					inUse = ab1;

				}
				if (ab1.getCastArea() == AreaOfEffect.SINGLETARGET) {
					singletarget = ab1;
					ActionPanel.remove(CastAbilities);
					ActionPanel.add(singleTarget);
				}
			}
			if (e.getActionCommand().equals(ab2.getName())) {
				if (ab2.getCastArea() == AreaOfEffect.SELFTARGET || ab2.getCastArea() == AreaOfEffect.TEAMTARGET
						|| ab2.getCastArea() == AreaOfEffect.SURROUND) {
					try {
						ActionPanel.remove(CastAbilities);
						game.castAbility(ab2);
						ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
						fillboard();
					} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
					}
					CastAbility.setVisible(true);
				}
				if (ab2.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					ActionPanel.remove(CastAbilities);
					ActionPanel.add(Directions);
					onDirection = true;
					inUse = ab2;
					this.revalidate();
					this.repaint();

				}
				if (ab2.getCastArea() == AreaOfEffect.SINGLETARGET) {
					singletarget = ab2;
					ActionPanel.remove(CastAbilities);
					ActionPanel.add(singleTarget);
				}

			}
			if (e.getActionCommand().equals(ab3.getName())) {
				if (ab3.getCastArea() == AreaOfEffect.SELFTARGET || ab3.getCastArea() == AreaOfEffect.TEAMTARGET
						|| ab3.getCastArea() == AreaOfEffect.SURROUND) {
					try {
						ActionPanel.remove(CastAbilities);
						game.castAbility(ab3);
						ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
						fillboard();
					} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage());
					}
					CastAbility.setVisible(true);
				}
				if (ab3.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					ActionPanel.remove(CastAbilities);
					ActionPanel.add(Directions);
					onDirection = true;
					inUse = ab3;
					this.revalidate();
					this.repaint();
					onDirection = true;

				}
				if (ab3.getCastArea() == AreaOfEffect.SINGLETARGET) {
					singletarget = ab3;
					ActionPanel.remove(CastAbilities);
					ActionPanel.add(singleTarget);
				}

			}
			if (ab4 != null) {
				if (e.getActionCommand().equals(ab4.getName())) {
					if (ab2.getCastArea() == AreaOfEffect.SINGLETARGET) {
						singletarget = ab4;
						ActionPanel.remove(CastAbilities);
						ActionPanel.remove(AbilityPunch);
						ActionPanel.add(singleTarget);
					}

				}
			}
			ActionPanel.revalidate();
			ActionPanel.repaint();
		}

		if (onDirection) {
			if (e.getActionCommand().equals("Down")) {
				d = Direction.UP;
				System.out.println("Down");

			}
			if (e.getActionCommand().equals("Right")) {
				d = Direction.LEFT;
				System.out.println("LEFT");
			}
			if (e.getActionCommand().equals("Left")) {
				d = Direction.RIGHT;
				System.out.println("RIGHT");
			}
			if (e.getActionCommand().equals("Up")) {
				d = Direction.DOWN;
				System.out.println("UP");
			}
			if (d != null) {
				try {
					game.castAbility(inUse, d);
					ActionPanel.remove(Directions);
					onDirection = false;
					CastAbility.setVisible(true);
					ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
					fillboard();
				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					ActionPanel.remove(Directions);
					CastAbility.setVisible(true);

				}
			}
			ActionPanel.revalidate();
			ActionPanel.repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Direction d = null;
		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 38) {
			d = Direction.UP;
		}
		if (e.getKeyCode() == 37) {
			d = Direction.LEFT;
		}
		if (e.getKeyCode() == 39) {
			d = Direction.RIGHT;
		}
		if (e.getKeyCode() == 40) {
			d = Direction.DOWN;
		}

		if (d != null) {
			try {
				game.move(d);
				fillboard();
				ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
			} catch (GameActionException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
		}
		if (e.getKeyCode() == 65) {
			try {
				game.attack(Direction.LEFT);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			fillboard();

		}
		if (e.getKeyCode() == 87) {
			try {
				game.attack(Direction.DOWN);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			fillboard();

		}
		if (e.getKeyCode() == 83) {
			try {
				game.attack(Direction.UP);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			fillboard();

		}
		if (e.getKeyCode() == 68) {
			try {
				game.attack(Direction.RIGHT);
			} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
			}
			fillboard();

		}
		ChampDetailstext3.setText(Detailsforcurrent(this.game.getCurrentChampion()));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public String returnNext() {

		String turns = new String();
		for (int i = 0; i <= 5; i++) {
			turns += " " + ((Champion) s.get(i)).getName();
		}
		return turns;
	}
}