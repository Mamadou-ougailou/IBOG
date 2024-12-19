package src.com.mypackage;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class AbstractScreen implements Screen {
    protected MyGame game;
    protected SpriteBatch batch;

    public AbstractScreen(MyGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void hide() { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void dispose() {
        batch.dispose();
    }
    @Override
    public void show() {}
}
