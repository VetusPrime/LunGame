package GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class GameStateManager implements KeyListener{
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int CONTROLSTATE = 2;
	public static final int HELPSTATE = 3;
	public static final int DEADSTATE = 4;
	public static final int LEVELTWOSTATE = 5;
	public static final int VICTORYSTATE = 6;
	
	public GameStateManager()
	{
		gameStates = new ArrayList<GameState>();
		//we want the game to start at the menu
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));
		//the control menu
		gameStates.add(new OptionState(this));
		gameStates.add(new HelpState(this));
		gameStates.add(new DeadState(this));
		gameStates.add(new Level2State(this));
		gameStates.add(new VictoryState(this));
	}
	public void setState(int state)
	{
		currentState = state;
		gameStates.get(currentState).init();
	}
	public void update()
	{
		gameStates.get(currentState).update();
	}
	public void draw(java.awt.Graphics2D g)
	{
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k){
		// TODO Auto-generated method stub
		gameStates.get(currentState).keyReleased(k);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
