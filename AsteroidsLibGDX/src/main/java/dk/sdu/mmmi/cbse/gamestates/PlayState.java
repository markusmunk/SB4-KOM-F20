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
		enemys = new Enemy[10];
		sr = new ShapeRenderer();
		
		player = new Player();
		for (int i =0; i < enemys.length; i++){
			Enemy enemy = new Enemy();
			this.enemys[i] = enemy;
		}


		
	}
	public void collision(Player player, Enemy enemy){
		if((int)player.getX() == (int)enemy.getX() && (int)player.getY() == (int)enemy.getY() ){
			System.out.printf("Collision");
		}
	}
	public void update(float dt) {
		
		handleInput();
		for(Enemy enemy: enemys){
			collision(this.player, enemy);
		}
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
		player.setShoot(GameKeys.isPressed(GameKeys.SPACE));
	}
	
	public void dispose() {}
	
}









