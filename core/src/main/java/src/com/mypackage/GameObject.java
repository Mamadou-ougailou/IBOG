package src.com.mypackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GameObject implements Renderable {
    protected Texture texture;
    protected Rectangle bounds;

    public GameObject(String texturePath, float x, float y, float width, float height) {
        this.texture = new Texture(texturePath);
        this.bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public float getX(){
        return bounds.x;
    }

    public float getY(){
        return bounds.y;
    }

    public float getHeight(){
        return bounds.height;
    }

    public float getWidth(){
        return bounds.width;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void dispose() {
        texture.dispose();
    }
}
