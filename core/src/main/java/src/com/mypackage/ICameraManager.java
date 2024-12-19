package src.com.mypackage;

import com.badlogic.gdx.graphics.OrthographicCamera;

public interface ICameraManager {
    OrthographicCamera getCamera();

    void update(Player player);

    void resize(int width, int height);
}
