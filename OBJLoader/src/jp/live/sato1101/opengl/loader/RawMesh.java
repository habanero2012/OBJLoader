package jp.live.sato1101.opengl.loader;

import java.util.ArrayList;
import java.util.List;

import jp.live.sato1101.opengl.Vector2;
import jp.live.sato1101.opengl.Vector3;

public class RawMesh {
	
	private String mObjectName;
	private int mOffsetPositonIndex;
	private int mOffsetUVIndex;
	private int mOffsetNormalIndex;
	
	private final List<Vector3> mPosition = new ArrayList<Vector3>();
	private final List<Vector3> mNormal = new ArrayList<Vector3>();
	private final List<Vector2> mUV = new ArrayList<Vector2>();
	private final List<Face> mFace = new ArrayList<Face>();
	
	public void setObjectName(String name) {
		mObjectName = name;
	}
	
	public String getObjectName() {
		return mObjectName;
	}
	
	public void setVertexOffset(int position, int uv, int normal) {
		mOffsetPositonIndex = position;
		mOffsetUVIndex = uv;
		mOffsetNormalIndex = normal;
	}
	
	public int getOffsetPositionIndex() {
		return mOffsetPositonIndex;
	}
	
	public int getOffsetUVIndex() {
		return mOffsetUVIndex;
	}
	
	public int getOffsetNormalIndex() {
		return mOffsetNormalIndex;
	}
	
	public void addPosition(float x, float y, float z) {
		mPosition.add(new Vector3(x, y, z));
	}
	
	public void addNormal(float x, float y, float z) {
		mNormal.add(new Vector3(x, y, z));
	}
	
	public void addUV(float x, float y) {
		mUV.add(new Vector2(x, y));
	}
	
	public void addFace(Face face) {
		mFace.add(face);
	}
	
	public Vector3 getPosition(int index) {
		return mPosition.get(index);
	}
	
	public Vector3 getNormal(int index) {
		return mNormal.get(index);
	}
	
	public Vector2 getUV(int index) {
		return mUV.get(index);
	}
	
	public Face getFace(int index) {
		return mFace.get(index);
	}
	
	public Mesh createMesh() {
		return null;
	}
}
