package jp.live.sato1101.opengl.loader;

import java.util.ArrayList;
import java.util.List;


public class Figure {
	private final List<RawMesh> mRawMeshList = new ArrayList<RawMesh>();
	
	public void addRawMesh(RawMesh mesh) {
		mRawMeshList.add(mesh);
	}
	
	public List<RawMesh> getRawMesh() {
		return mRawMeshList;
	}
}
