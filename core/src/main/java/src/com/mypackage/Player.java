package src.com.mypackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {
    private MapManager mapManager;

    public Player(MapManager mapManager) {
        super("player.png", 50, 50, 25, 45);
        this.mapManager = mapManager;
    }

    public void update(float delta) {
        float speed = 200 * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !isColliding(bounds.x - speed, bounds.y)) {
            bounds.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !isColliding(bounds.x + speed, bounds.y)) {
            bounds.x += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && !isColliding(bounds.x, bounds.y + speed)) {
            bounds.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !isColliding(bounds.x, bounds.y - speed)) {
            bounds.y -= speed;
        }
        // Empêcher de sortir des limites
        bounds.x = Math.max(0, Math.min(bounds.x, mapManager.getMapWidth() - bounds.width));
        bounds.y = Math.max(0, Math.min(bounds.y, mapManager.getMapHeight() - bounds.height));
    }


    private boolean isColliding(float newX, float newY) {
        Rectangle futureBounds = new Rectangle(newX, newY, bounds.width, bounds.height);
        return mapManager.getCollisionBounds().stream().anyMatch(futureBounds::overlaps);
    }
}
