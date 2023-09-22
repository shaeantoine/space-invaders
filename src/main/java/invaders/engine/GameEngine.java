package invaders.engine;

import invaders.GameObject;
import invaders.config.ConfigReader;
import invaders.enemy.Enemy;
import invaders.enemy.EnemyGroup;
import invaders.entities.Player;
import invaders.physics.Vector2D;
import invaders.projectileFactory.*;
import invaders.projectileStrategy.SlowUpwardStrategy;
import invaders.rendering.Renderable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {

	private List<GameObject> gameobjects;
	private List<Renderable> renderables;
	private Player player;
	//private SlowStraightProjectile playerProjectile;
	private ProjectileFactory projectileFactory; // Add this field

	private List<Enemy> enemyList;
	private EnemyGroup enemies;

	private boolean left;
	private boolean right;

	public GameEngine(String config){
		// Initialize starting state
		gameobjects = new ArrayList<GameObject>();
		renderables = new ArrayList<Renderable>();

		//List<GameObject> enemies = ConfigReader.parse(config);
		//enemyList = ConfigReader.parse(config);
		enemies = new EnemyGroup(ConfigReader.parse(config), this);

		// TODO Update projectile initialization
		// Initialize Objects
		//projectileFactory = new ProjectileFactory(); // Create a ProjectileFactory instance
		player = new Player(new Vector2D(200, 380));
		//playerProjectile = new SlowStraightProjectile(new Vector2D(250, 300));
		for (GameObject enemy : enemies.getEnemies()) {
			gameobjects.add(enemy);
		}

		// TODO Update projectile rendering
		// Add Renderables
		renderables.add(player);
		for (Renderable enemy : enemies.getEnemies()) {
			renderables.add(enemy);
		}
		//renderables.add(playerProjectile);
	}

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		// TODO Update projectile movement
		movePlayer();
		enemies.update();
		for(GameObject go: gameobjects){
			go.update();
		}

		// ensure that renderable foreground objects don't go off-screen
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= 640) {
				ro.getPosition().setX(639-ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(1);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= 400) {
				ro.getPosition().setY(399-ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(1);
			}
		}
	}

	public List<Renderable> getRenderables(){
		return renderables;
	}

	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	// Handle a player projectile
	public boolean shootPressed(){
	 	player.shoot();

		// TODO Check factory method for creating player projectiles!
		Vector2D playerPosition = player.getPosition();

		// Factory method + Strategy
		ProjectileFactory slowFactory = new SlowStraightFactory();
		Projectile playerProjectile = slowFactory.initializeProjectile(playerPosition);
		playerProjectile.setStrategy(new SlowUpwardStrategy());

		gameobjects.add(playerProjectile);
		renderables.add(playerProjectile);

		return true;
	}

	// Handle alien projectiles
	public boolean initializeAlienProjectile(Enemy enemy) {
		// TODO Check factory method for creating player projectiles!
		Vector2D enemyPosition = enemy.getPosition();
		ProjectileFactory enemyProjectileFactory = new SlowStraightFactory();
		Projectile enemyProjectile = enemyProjectileFactory.initializeProjectile(enemyPosition);
		enemyProjectile.setStrategy(enemy.getStrategy());

		gameobjects.add(enemyProjectile);
		renderables.add(enemyProjectile);

		return true;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}

}
