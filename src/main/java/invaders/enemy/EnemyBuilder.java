package invaders.enemy;

import invaders.physics.Vector2D;
import invaders.projectileStrategy.ProjectileStrategy;

public class EnemyBuilder {
    private Vector2D position;
    private ProjectileStrategy strategy;

    public EnemyBuilder withPosition(Vector2D position) {
        this.position = position;
        return this;
    }

    public EnemyBuilder withProjectileStrategy(ProjectileStrategy strategy){
        this.strategy = strategy;
        return this;
    }

    public Enemy build(){
        return new Enemy(position, strategy);
    }

}
