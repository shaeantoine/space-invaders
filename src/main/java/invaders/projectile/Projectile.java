package invaders.projectile;

public interface Projectile {
    void update();
    boolean checkCollision();
    boolean isOutOfBounds();
}
