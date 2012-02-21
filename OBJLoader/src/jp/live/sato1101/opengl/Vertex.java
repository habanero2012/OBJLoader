package jp.live.sato1101.opengl;

/**
 * １つの頂点属性を表すクラス
 * 
 * 
 * １頂点の以下の属性を持つ
 * 
 * 頂点位置
 * UV座標
 * 法線ベクトル
 * 
 * UV座標や法線ベクトルの情報が無い頂点はnullがセットされる
 */
public class Vertex {
	
	/**
	 * 頂点位置情報
	 */
	public final Vector3 position = new Vector3();
	
	/**
	 * UV情報
	 */
	public Vector2 uv = null;
	
	/**
	 * 法線情報
	 */
	public Vector3 normal = null;
	
	@Override
    public boolean equals(Object o) {
        Vertex v = (Vertex) o;
        return v.position.equals(position) && v.uv.equals(uv) && v.normal.equals(normal);
    }
}
