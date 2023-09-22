package invaders.projectileFactory;

import invaders.physics.Vector2D;

public abstract class ProjectileFactory {

    public abstract Projectile createProjectile();

    /**
     *
     * Here we are going to handle not only
     * the initial position of the projectile
     * but also the strategy associated with
     * the projectile
     *
     * @param position
     * @return
     */
    public Projectile initializeProjectile(Vector2D position) {
        Projectile projectile = createProjectile();

        // Initialize the position
        projectile.prepare(position);

//        // Initialize the strategy
//        projectile.setStrategy(strategy);

        return projectile;
    }

}
