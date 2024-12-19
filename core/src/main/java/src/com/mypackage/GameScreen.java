package src.com.mypackage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;
import java.util.ArrayList;

public class GameScreen extends AbstractScreen {
    private Player player;
    private MapManager mapManager;
    private CameraManager cameraManager;
    private List<Bomb> bombs;
    private List<Coin> coins;
    private int coinsCollected;
    private BitmapFont font;
    private float timer = 30f;
    private final float timeLimit = 0f;
    private boolean gameLost = false;

    public GameScreen(MyGame game) {
        super(game);

        // Initialisation des objets spécifiques à cet écran
        mapManager = new MapManager();
        player = new Player(mapManager);
        bombs = new ArrayList<>();
        createBombs(20);
        coins = new ArrayList<>();
        createCoins(10);
        font = new BitmapFont();
        font.getData().setScale(2.0f);
        cameraManager = new CameraManager(mapManager, 640, 640);

    }

    @Override
    public void show() {
        // Configuration initiale de l'écran
        Gdx.app.log("GameScreen", "Screen shown");
    }

    @Override
    public void render(float delta) {
        if(!gameLost){
            timer -= delta;
            if(timer<timeLimit){
                gameLost = true;
                game.setScreen(new GameOverScreen(game));
            }
        }

        // Effacer l'écran
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Mise à jour des objets
        player.update(delta);
        cameraManager.update(player);
        mapManager.render(cameraManager);
        checkPlayerBombCollision();
        checkPlayerCoinCollision();


        batch.begin();
        player.render(batch);
        for(Bomb bomb : bombs){
            bomb.render(batch);
        }
        for(Coin coin : coins){
            coin.render(batch);
        }
        font.draw(batch, "Coins: "+coinsCollected, 10, 640 - 10);
        font.draw(batch, "Temps restant : "+(int) timer+"s", 640-270, 640-10);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Ajuster la caméra en fonction de la taille de la fenêtre
        cameraManager.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        mapManager.dispose();
        player.dispose();
    }

    private void createBombs(int count) {
        for (int i = 0; i < count; i++) {
            Bomb bomb;
            boolean validPosition;

            do {
                validPosition = true;

                // Générer des coordonnées aléatoires
                float x = (float) (Math.random() * mapManager.getMapWidth());
                float y = (float) (Math.random() * mapManager.getMapHeight());

                // Créer une bombe temporaire pour vérifier la position
                bomb = new Bomb(x, y, 32, 32);

                // Vérifier si la bombe entre en collision avec des objets
                for (Rectangle objectBounds : mapManager.getCollisionBounds()) {
                    if (bomb.getBounds().overlaps(objectBounds) || player.getBounds().overlaps(bomb.getBounds())  ) {
                        validPosition = false;
                        break;
                    }
                }
                // Vérifier si la bombe entre en collision avec d'autres bombes déjà crées
                for(Bomb existingBomb : bombs){
                    if(bomb.getBounds().overlaps(existingBomb.getBounds())){
                        validPosition = false;
                        break;
                    }
                }

            } while (!validPosition); // Répéter jusqu'à trouver une position valide

            bombs.add(bomb);
        }
    }

    // Vérifier la collision entre le joueur et les bombes
    private void checkPlayerBombCollision() {
        for (Bomb bomb : bombs) {
            if (player.getBounds().overlaps(bomb.getBounds())) {
                // Le joueur touche une bombe, on arrête le jeu
                System.out.println("Le joueur a touché une bombe !");

                // Arrêter le jeu et afficher l'écran Game Over
                game.setScreen(new GameOverScreen(game));

                // Vous pouvez aussi utiliser Gdx.app.exit() pour fermer l'application immédiatement.
                // Gdx.app.exit();
            }
        }
    }
    private boolean isCollidingWithObjects(float x, float y) {
        Rectangle coinBounds = new Rectangle(x, y, 16, 16); // Taille de la pièce
        for (Rectangle objectBounds : mapManager.getCollisionBounds()) {
            if (coinBounds.overlaps(objectBounds) || coinBounds.overlaps(player.getBounds()) ) {
                return true;
            }
        }
        return false;
    }
    private void createCoins(int count) {
        for (int i = 0; i < count; i++) {
            float x = (float) (Math.random() * mapManager.getMapWidth());
            float y = (float) (Math.random() * mapManager.getMapHeight());

            // Vérifiez que la pièce ne se trouve pas sur un objet collision
            while (isCollidingWithObjects(x, y)) {
                x = (float) (Math.random() * mapManager.getMapWidth());
                y = (float) (Math.random() * mapManager.getMapHeight());
            }

            coins.add(new Coin(x, y, 16, 16)); // Taille des pièces (16x16 par exemple)
        }
        coinsCollected = 0;
    }
    private void checkPlayerCoinCollision() {
        coins.removeIf(coin -> {
            if (coin.getBounds().overlaps(player.getBounds())) {
                coinsCollected++; // Incrémente le compteur
                // Vérifie si le joueur a gagné

                if (coinsCollected >= 5) {
                    game.setScreen(new VictoryScreen(game));
                }
                return true; // Supprime la pièce collectée
            }
            return false;
        });
    }
}
