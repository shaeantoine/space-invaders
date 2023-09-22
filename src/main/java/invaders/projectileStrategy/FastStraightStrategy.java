package invaders.projectileStrategy;

import invaders.physics.Vector2D;

public class FastStraightStrategy implements ProjectileStrategy {
    /**
     *
     * We know that only enemies fire fast
     * projectiles so these projectiles
     * will only move down the screen
     * at double the speed of the slow
     * projectiles
     *
     * @param position of the projectile
     */
    @Override
    public void move(Vector2D position) {
        position.setY(position.getY() + 2);
    }
}
