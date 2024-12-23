package src.com.mypackage;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends Game {
    public SpriteBatch batch ;

    @Override
    public void create(){
        batch = new SpriteBatch();
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void dispose(){
        batch.dispose();
    }
}
