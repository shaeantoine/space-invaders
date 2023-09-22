package invaders.projectileStrategy;

import invaders.physics.Vector2D;

public class SlowDownwardStrategy implements ProjectileStrategy {

    /**
     * We know that both the player
     * and the enemies can fire slow
     * projectiles so this class handles
     * slow projectiles from enemies
     * (downward moving projectiles)
     *
     * @param position of the projectile
     */
    @Override
    public void move(Vector2D position) {
        position.setY(position.getY() + 1);
    }
}
