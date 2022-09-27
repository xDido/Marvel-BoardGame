package View;

import engine.Game;
import engine.Player;

public class window {
	Game g;
	Player p1;
	Player p2;
	
	public void set(Game g ){
		this.g=g;
		this.p1=g.getFirstPlayer();
		this.p2=g.getSecondPlayer();
		System.out.println(p1.getTeam().size());

	}
	public Game getG() {
		return g;
	}
	public void setG(Game g) {
		this.g = g;
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
	

}
