package invaders.projectileFactory;

public class FastStraightFactory extends ProjectileFactory {

    @Override
    public Projectile createProjectile() {
        return new FastStraightProjectile();
    }
}
