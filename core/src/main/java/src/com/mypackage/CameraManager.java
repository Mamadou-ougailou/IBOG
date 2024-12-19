package src.com.mypackage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraManager extends AbstractCameraManager {

    private Viewport viewport;

    public CameraManager(MapManager mapManager, float virtualWidth, float virtualHeight) {
        super(mapManager);
        viewport = new FitViewport(virtualWidth, virtualHeight, camera);
        camera.setToOrtho(false, virtualWidth, virtualHeight);
    }

    @Override
    public  void resize(int width, int height){
        viewport.update(width, height);
    }

    @Override
    public void update(Player player) {
        float cameraHalfWidth = camera.viewportWidth / 2;
        float cameraHalfHeight = camera.viewportHeight / 2;

        // Ajuster la position de la caméra pour suivre le joueur
        float cameraX = Math.max(cameraHalfWidth, Math.min(player.getX() + player.getWidth() / 2, mapWidth - cameraHalfWidth));
        float cameraY = Math.max(cameraHalfHeight, Math.min(player.getY() + player.getHeight() / 2, mapHeight - cameraHalfHeight));

        camera.position.set(cameraX, cameraY, 0);
        camera.update();
    }
}
