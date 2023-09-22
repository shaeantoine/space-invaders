package invaders.enemy;

import invaders.GameObject;
import invaders.physics.Moveable;
import invaders.physics.Vector2D;
import invaders.projectileStrategy.ProjectileStrategy;
import invaders.rendering.Renderable;
import javafx.scene.image.Image;

import java.io.File;

public class Enemy implements GameObject, Renderable, Moveable {

    // Width of the projectile
    final private double width;

    // Height of the projectile
    final private double height;

    // Position of the projectile
    private Vector2D position;

    // Image of the projectile
    final private Image image;
    private ProjectileStrategy strategy;


    public Enemy(Vector2D position, ProjectileStrategy strategy) {
        this.position = position;
        this.strategy = strategy;
        this.width = 25;
        this.height = 30;
        this.image = new Image(new File("src/main/resources/enemy.png").toURI().toString(), width, height, true, true);
    }


    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    public Vector2D getPosition() {
        return this.position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    public ProjectileStrategy getStrategy() {
        return this.strategy;
    }

    @Override
    public void down() {
        position.setY(position.getY() + 1);
    }

    @Override
    public void left() {
        position.setX(position.getX() + 0.5);
    }

    @Override
    public void right() {
        position.setX(position.getX() - 0.5);
    }

    // NOT USED:
    // ENEMY NEVER MOVES UP
    @Override
    public void up() {

    }
}
