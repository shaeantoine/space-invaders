package invaders.projectile;

public class SlowStraightProjectile implements Projectile{

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
