package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private Enemy[] enemys;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		enemys = new Enemy[1000];
		sr = new ShapeRenderer();
		
		player = new Player();
		for (int i =0; i < enemys.length; i++){
			Enemy enemy = new Enemy();
			this.enemys[i] = enemy;
		}


		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		for (int i =0; i < enemys.length; i++){
			enemys[i].update(dt);
		}
		
	}
	
	public void draw() {
		player.draw(sr);
		for (int i =0; i < enemys.length; i++){
			enemys[i].draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {}
	
}









