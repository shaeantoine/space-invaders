package invaders.projectileFactory;

public class SlowStraightFactory extends ProjectileFactory {

    @Override
    public Projectile createProjectile() {
        return new SlowStraightProjectile();
    }
}
