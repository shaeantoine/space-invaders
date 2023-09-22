package invaders.config;

import invaders.GameObject;
import invaders.enemy.Enemy;
import invaders.enemy.EnemyBuilder;
import invaders.physics.Vector2D;
import invaders.projectileStrategy.FastStraightStrategy;
import invaders.projectileStrategy.ProjectileStrategy;
import invaders.projectileStrategy.SlowDownwardStrategy;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigReader {

	/**
	 * You will probably not want to use a static method/class for this.
	 * 
	 * This is just an example of how to access different parts of the json
	 * 
	 * @param path The path of the json file to read
	 */

	public static List<Enemy> parse(String path) {
		//Map<GameObject, Renderable> gameObjects = new HashMap<>();
		List<Enemy> enemyGroup = new ArrayList<>();

		List<GameObject> gameObjects = new ArrayList<>();

		JSONParser parser = new JSONParser();
		try {
			Object object = parser.parse(new FileReader(path));
			JSONObject jsonObject = (JSONObject) object;

			// Reading the Game:
			JSONObject jsonGame = (JSONObject) jsonObject.get("Game");
			Long gameX = (Long) ((JSONObject) jsonGame.get("size")).get("x");
			Long gameY = (Long) ((JSONObject) jsonGame.get("size")).get("y");

			// Reading the player:
			JSONObject jsonPlayer = (JSONObject) jsonObject.get("Player");
			String playerColour = (String) jsonPlayer.get("colour");
			Long playerSpeed = (Long) jsonPlayer.get("speed");
			Long playerLives = (Long) jsonPlayer.get("lives");
			Long playerX = (Long) ((JSONObject) jsonPlayer.get("position")).get("x");
			Long playerY = (Long) ((JSONObject) jsonPlayer.get("position")).get("y");
//			Player player = new Player();
//			player.start((JSONObject) obj);
//			gameObjects.add(player);

			// Reading the bunkers:
			JSONArray jsonBunkers = (JSONArray) jsonObject.get("Bunkers");

			for (Object obj: jsonBunkers) {
				JSONObject jsonBunker = (JSONObject) obj;

				Long bunkerPositionX = (Long) ((JSONObject) jsonBunker.get("position")).get("x");
				Long bunkerPositionY = (Long) ((JSONObject) jsonBunker.get("position")).get("y");
				Long bunkerSizeX = (Long) ((JSONObject) jsonBunker.get("size")).get("x");
				Long bunkerSizeY = (Long) ((JSONObject) jsonBunker.get("size")).get("y");
	//			Bunker bunker = new Bunker();
	//			bunker.start((JSONObject) obj);
	//			gameObjects.add(bunker);
			}

			// reading the "Enemies" array:
			JSONArray jsonEnemies = (JSONArray) jsonObject.get("Enemies");

			// reading from the array:
			for (Object obj : jsonEnemies) {
				JSONObject jsonEnemy = (JSONObject) obj;
				
				// Enemy Attributes
				Long positionX = (Long) ((JSONObject) jsonEnemy.get("position")).get("x");
				Long positionY = (Long) ((JSONObject) jsonEnemy.get("position")).get("y");
				String projectileType = (String) jsonEnemy.get("projectile");

				// Building enemies
				ProjectileStrategy projectileStrategy;
				if ("fast_straight".equals(projectileType)) {
					projectileStrategy = new FastStraightStrategy();
				} else if ("slow_straight".equals(projectileType)) {
					projectileStrategy = new SlowDownwardStrategy();
				} else {
					throw new IllegalArgumentException("Unknown projectile type: " + projectileType);
				}

				Vector2D enemyPosition = new Vector2D(positionX, positionY);

				// Create Enemy as a GameObject using the EnemyBuilder
				Enemy enemy = new EnemyBuilder()
						.withPosition(enemyPosition)
						.withProjectileStrategy(projectileStrategy)
						.build();

				enemyGroup.add(enemy);

				// TODO: delete me, this is just a demonstration:
				// System.out.println("Enemey x: " + positionX + ", Enemey Y: " + positionY + ", projectile: " + projectileStrategy);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//return gameObjects;
		return enemyGroup;

	}

	/**
	 * Your main method will probably be in another file!
	 * 
	 * @param args First argument is the path to the config file
	 */
	public static void main(String[] args) {
		// if a command line argument is provided, that should be used as the path
		// if not, you can hard-code a default. e.g. "src/main/resources/config.json"
		// this makes it easier to test your program with different config files
		String configPath;
		if (args.length > 0) {
			configPath = args[0];
		} else {
			configPath = "src/main/resources/config.json";
		}
		// parse the file:
		ConfigReader.parse(configPath);
	}

}
