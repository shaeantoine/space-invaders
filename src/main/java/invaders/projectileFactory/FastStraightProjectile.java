package invaders.projectileFactory;

import invaders.physics.Vector2D;
import invaders.projectileStrategy.ProjectileStrategy;
import javafx.scene.image.Image;

import java.io.File;

public class FastStraightProjectile implements Projectile {

    // Width of the projectile
    final private double width;

    // Height of the projectile
    final private double height;

    // Position of the projectile
    private Vector2D position;

    // Image of the projectile
    final private Image image;

    private ProjectileStrategy strategy;


    // Constructor
    public FastStraightProjectile() {
        this.width = 25;
        this.height = 30;
        //this.position = new Vector2D(200, 200);
        this.image = new Image(new File("src/main/resources/projectile.png").toURI().toString(), width, height, true, true);
    }

    @Override
    public void prepare(Vector2D position) {
        this.position = new Vector2D(position.getX(), position.getY());
    }

    @Override
    public void setStrategy(ProjectileStrategy strategy) {
        this.strategy = strategy;
    }


    @Override
    public void start() {

    }

    @Override
    public void update() {
        // Old position update
//        position.setY(position.getY() - speed);

        // New position update
        strategy.move(position);
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

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }
}
