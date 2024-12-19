package src.com.mypackage;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class MapManager {
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private int mapWidth;
    private int mapHeight;

    public MapManager() {
        map = new TmxMapLoader().load("mytiled.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public TiledMap getMap() {
        return map;
    }

    public List<Rectangle> getCollisionBounds() {
        List<Rectangle> bounds = new ArrayList<>();
        MapLayer collisionLayer = map.getLayers().get("collision");

        if (collisionLayer != null) {
            for (MapObject object : collisionLayer.getObjects()) {
                if (object instanceof RectangleMapObject) {
                    bounds.add(((RectangleMapObject) object).getRectangle());
                }
            }
        }

        return bounds;
    }


    public void render(CameraManager cameraManager) {
        renderer.setView(cameraManager.getCamera());
        renderer.render();
    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
    }
}
