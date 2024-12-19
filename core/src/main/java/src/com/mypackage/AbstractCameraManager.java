package src.com.mypackage;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class AbstractCameraManager implements ICameraManager {
    protected OrthographicCamera camera;
    protected int mapWidth;
    protected int mapHeight;

    public AbstractCameraManager(MapManager mapManager) {
        this.mapWidth = mapManager.getMapWidth();
        this.mapHeight = mapManager.getMapHeight();
        this.camera = new OrthographicCamera();
    }

    @Override
    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public abstract void update(Player player);

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }
}
