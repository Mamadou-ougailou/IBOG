package src.com.mypackage;

public class Bomb extends GameObject {
    private float timeSinceLastMove;

    public Bomb(float x, float y, float width, float height) {
        super("bomb.png", x, y, width, height);
        this.timeSinceLastMove = 0;
    }

    public void update(float delta) {
        timeSinceLastMove += delta;
        if (timeSinceLastMove >= 15) {
            timeSinceLastMove = 0;
            bounds.x = (float) (Math.random() * 800);
            bounds.y = (float) (Math.random() * 600);
        }
    }
}
