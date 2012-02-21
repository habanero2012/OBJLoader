package jp.live.sato1101.opengl;

import java.util.ArrayList;
import java.util.List;

public class Mesh {
	
	private final VertexBuffer mVertexBuffer = new VertexBuffer();
	private final IndexBuffer mIndexBuffer = new IndexBuffer();
	
	private final List<Vector3> mTempNormal = new ArrayList<Vector3>();
	private final List<Vector2> mTempUV = new ArrayList<Vector2>();
	private final List<Face> mFaceList = new ArrayList<Face>();
	
	private int mTotalVertexCount = 0;
	private int mTotalNormalCount = 0;
	private int mTotalUVCount = 0;
	
	public VertexBuffer getVertexBuffer() {
		return mVertexBuffer;
	}
	
	public IndexBuffer getIndexBuffer() {
		return mIndexBuffer;
	}
	
	public List<Face> getFaceList() {
		return mFaceList;
	}

	/**
	 * 以下のような行データから頂点情報を取得する
	 * v 0.450369 -1.875321 0.136948
	 * @param line
	 */
	public void parseVertex(String line) {
		String[] tokens = line.split("[ ]+");
		Vertex v = new Vertex();
		v.position.x = Float.parseFloat(tokens[1]);
		v.position.y = Float.parseFloat(tokens[2]);
		v.position.z = Float.parseFloat(tokens[3]);
		mVertexBuffer.addVertex(v);
		mTotalVertexCount++;
	}

	/**
	 * 以下のような行データから法線情報を取得する
	 * vn 0.00000 0.00000 1.00000
	 * @param line
	 */	
	public void parseNormal(String line) {
		String[] tokens = line.split("[ ]+");
		Vector3 normal = new Vector3();
		normal.x = Float.parseFloat(tokens[1]);
		normal.y = Float.parseFloat(tokens[2]);
		normal.z = Float.parseFloat(tokens[3]);
		mTempNormal.add(normal);
		mTotalNormalCount++;
	}

	/**
	 * 以下のような行データからUV情報を取得する
	 * vt 0.00000 0.00000
	 * @param line
	 */		
	public void parseUV(String line) {
		String[] tokens = line.split("[ ]+");
		Vector2 normal = new Vector2();
		normal.x = Float.parseFloat(tokens[1]);
		normal.y = Float.parseFloat(tokens[2]);
		mTempUV.add(normal);
		mTotalUVCount++;
	}

	
	/**
	 * face情報を取得する
	 * face情報はfaceの種類によってフォーマットが異なる
	 * 
	 * 頂点属性のみ
	 * f 1 99 297 97
	 * 
	 * 頂点、UV、法線を含む
	 * f v0/t0/n0 v1/t1/n1 v2/t2/n2
	 * 
	 * UVを含まない、頂点数が４つ
	 * f v0//n0 v1//n1 v2//n2　v3//n3
	 * @param line
	 */
	public void parseFace(String line) {
		String[] tokens = line.split("[ ]+");
		Face face = new Face();
		face.numVertex = tokens.length - 1;
		
		for(int i=1; i<tokens.length; i++) {
			String token = tokens[i];
			String[] parts = token.split("/");
			face.vertexIndex.add(getIndex(parts[0], mTotalVertexCount));
			
			if(parts.length > 2 && parts[2] != "") {
				face.normalIndex.add(getIndex(parts[2], mTotalNormalCount));
				face.hasNormal = true;
			} else {
				face.hasNormal = false;
			}
			
			if (parts.length > 1 && parts[1] != "") {
				face.uvIndex.add(getIndex(parts[1], mTotalUVCount));
				face.hasUV = true;
			} else {
				face.hasUV = false;
			}
		}
		mFaceList.add(face);
	}
	
	private int getIndex(String index, int size) {
		int idx = Integer.parseInt(index);
		if (idx < 0) {
			return size + idx;
		} else {
			return idx - 1;
		}
	}
	
	public void setupDataByFace() {
		for(Face face: mFaceList) {
			for(int i=0; i<face.numVertex; i++) {
				mIndexBuffer.add((face.vertexIndex.get(i)).shortValue());
			}
		}
	}
}
