package jp.live.sato1101.opengl;


public class Vector3 {
	public float x = 0.0f;
	public float y = 0.0f;
	public float z = 0.0f;
	
	@Override
    public boolean equals(Object o) {
        Vector3 v = (Vector3) o;
        return v.x == x && v.y == y && v.z == z;
    }
}
