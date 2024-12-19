package src.com.mypackage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends AbstractScreen {
    private SpriteBatch batch;
    private BitmapFont font;

    public GameOverScreen(MyGame game) {
        super(game);
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f); // Taille de la police
        font.setColor(Color.WHITE); // Couleur de la police
    }

    @Override
    public void show() {
        // Actions spécifiques lorsque l'écran devient visible
    }

    @Override
    public void render(float delta) {
        // Effacer l'écran
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Commencer à dessiner
        batch.begin();

        // Texte "Game Over" centré
        String message = "Game Over";
        float textWidth = font.getRegion().getRegionWidth();
        float x = (Gdx.graphics.getWidth() - textWidth) / 2;
        float y = Gdx.graphics.getHeight() / 2;
        font.draw(batch, message, x, y);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Ajuster la taille de l'écran si nécessaire
    }

    @Override
    public void pause() {
        // Gérer la pause
    }

    @Override
    public void resume() {
        // Gérer la reprise
    }

    @Override
    public void hide() {
        // Actions spécifiques lorsque l'écran est masqué
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
