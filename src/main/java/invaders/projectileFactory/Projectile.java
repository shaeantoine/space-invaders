package invaders.projectileFactory;

import invaders.GameObject;
import invaders.physics.Vector2D;
import invaders.projectileStrategy.ProjectileStrategy;
import invaders.rendering.Renderable;

public interface Projectile extends GameObject, Renderable {
//    void update();
//    boolean isOutOfBounds();
//    int getSpeed(); // Get the speed of the projectile

    // Factory Position Implementation
    void prepare(Vector2D position);

    // Strategy Movement Implementation
    void setStrategy(ProjectileStrategy strategy);
}
