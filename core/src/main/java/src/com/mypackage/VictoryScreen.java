package src.com.mypackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class VictoryScreen extends AbstractScreen {
    private BitmapFont font;

    public VictoryScreen(MyGame game) {
        super(game);
        this.font = new BitmapFont();
        font.getData().setScale(3);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        GlyphLayout layout = new GlyphLayout();
        String victoryMessage = "Vous avez gagné !";
        layout.setText(font, victoryMessage);
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() + layout.height) / 2;
        font.draw(batch, victoryMessage, x, y);

        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
