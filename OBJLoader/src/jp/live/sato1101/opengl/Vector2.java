package jp.live.sato1101.opengl;

public class Vector2 {
    public float x = 0.0f;
    public float y = 0.0f;

    public Vector2() {
    }
    
    public Vector2(float x, float y) {
    	this.x = x;
    	this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        Vector2 v = (Vector2) o;
        return v.x == x && v.y == y;
    }
}
