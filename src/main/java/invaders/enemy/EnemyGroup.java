package invaders.enemy;

import invaders.engine.GameEngine;
import invaders.projectileFactory.Projectile;

import java.util.ArrayList;
import java.util.List;

public class EnemyGroup {

    private boolean firstShot = true;

    private List<Enemy> enemies;

    private List<Projectile> enemyProjectiles = new ArrayList<>();

    private int direction;

    private GameEngine model;

    public EnemyGroup(List<Enemy> enemies, GameEngine model) {
        this.enemies = enemies;
        this.direction = 1; // Start by moving right
        this.model = model;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void update() {
        // Check if the group can shoot a projectile
        if (shouldShootProjectile()) {
            selectAlienShooter();
        }

        // Check if the group needs to change direction
        if (shouldChangeDirection()) {
            direction *= -1; // Reverse direction
            moveDown(); // Move the group down
        }

        // Move the group horizontally
        moveHorizontal();
    }

    private boolean shouldChangeDirection() {
        // Calculate the leftmost and rightmost positions of the group
        double leftmostX = Double.MAX_VALUE;
        double rightmostX = Double.MIN_VALUE;

        for (Enemy enemy : enemies) {
            double enemyX = enemy.getPosition().getX();

            if (enemyX < leftmostX) {
                leftmostX = enemyX;
            }

            if (enemyX > rightmostX) {
                rightmostX = enemyX;
            }
        }

        // TODO DO NOT HARD CODE
        // Check if the group is near the screen edges
        double screenWidth = 600; // Adjust with your screen width
        double edgeThreshold = 20; // Adjust as needed

        if (leftmostX <= edgeThreshold && direction == 1) {
            // Group reached the left edge, change direction
            return true;
        } else if (rightmostX >= screenWidth - edgeThreshold && direction == -1) {
            // Group reached the right edge, change direction
            return true;
        }

        return false; // Group should not change direction
    }


    private void moveHorizontal() {
        for (Enemy enemy : enemies) {
            //enemy.moveHorizontal(direction);
            // TODO I don't love this implementation
            if (direction > 0) {
                enemy.right();
            } else {
                enemy.left();
            }
        }
    }

    private void moveDown() {
        for (Enemy enemy : enemies) {
            enemy.down();
        }
    }

    // TODO Update to fit requirements
    private boolean shouldShootProjectile() {
        if (firstShot) {
            firstShot = false;
            return true;
        }

        return false;
    }

    private void selectAlienShooter() {
//        Random random = new Random();
//        int randomIndex = random.nextInt(enemies.size());
        model.initializeAlienProjectile(enemies.get(1));

    }

}
