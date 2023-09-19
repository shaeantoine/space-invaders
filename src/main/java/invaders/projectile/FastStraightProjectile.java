package invaders.projectile;

public class FastStraightProjectile implements Projectile{

    @Override
    public void update() {

    }

    @Override
    public boolean checkCollision() {
        return false;
    }

    @Override
    public boolean isOutOfBounds() {
        return false;
    }
}
