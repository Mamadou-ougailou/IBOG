package src.com.mypackage;

public class CameraManager extends AbstractCameraManager {

    public CameraManager(MapManager mapManager) {
        super(mapManager);
        camera.setToOrtho(false, mapWidth, mapHeight);
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
