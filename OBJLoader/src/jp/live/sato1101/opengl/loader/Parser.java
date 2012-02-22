package jp.live.sato1101.opengl.loader;


public class Parser {
	
	private int mTotalPositionCount = 0;
	private int mTotalNormalCount = 0;
	private int mTotalUVCount = 0;
	
	private Figure mFigure = new Figure();
	private RawMesh mCurrentMesh;
	
	public void createRawMesh() {
		mCurrentMesh = new RawMesh();
		mCurrentMesh.setVertexOffset(
				mTotalPositionCount,
				mTotalUVCount,
				mTotalNormalCount);
		mFigure.addRawMesh(mCurrentMesh);
	}
	
	/**
	 * 以下のような行データからObject名を取得する
	 * o Sphere
	 * @param line
	 */
	public void parseObjectName(String line) {
		String[] tokens = line.split("[ ]+");
		mCurrentMesh.setObjectName(tokens[1]);
	}

	/**
	 * 以下のような行データから頂点情報を取得する
	 * v 0.450369 -1.875321 0.136948
	 * @param line
	 */
	public void parsePosition(String line) {
		String[] tokens = line.split("[ ]+");
		mCurrentMesh.addPosition(
				Float.parseFloat(tokens[1]),
				Float.parseFloat(tokens[2]),
				Float.parseFloat(tokens[3]));
		mTotalPositionCount++;
	}

	/**
	 * 以下のような行データから法線情報を取得する
	 * vn 0.00000 0.00000 1.00000
	 * @param line
	 */	
	public void parseNormal(String line) {
		String[] tokens = line.split("[ ]+");
		mCurrentMesh.addNormal(
				Float.parseFloat(tokens[1]),
				Float.parseFloat(tokens[2]),
				Float.parseFloat(tokens[3]));
		mTotalNormalCount++;
	}

	/**
	 * 以下のような行データからUV情報を取得する
	 * vt 0.00000 0.00000
	 * @param line
	 */		
	public void parseUV(String line) {
		String[] tokens = line.split("[ ]+");
		mCurrentMesh.addUV(
				Float.parseFloat(tokens[1]),
				Float.parseFloat(tokens[2]));
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
		// 頂点数が３未満の図形には何もしない
		if(face.numVertex < 3) {
			return;
		}
		
		for(int i=1; i<tokens.length; i++) {
			String token = tokens[i];
			String[] parts = token.split("/");
			face.vertexIndex.add(getIndex(parts[0], mTotalPositionCount));
			
			if(parts.length > 2 && parts[2] != "") {
				face.normalIndex.add(getIndex(parts[2], mTotalNormalCount));
			}
			
			if (parts.length > 1 && parts[1] != "") {
				face.uvIndex.add(getIndex(parts[1], mTotalUVCount));
			}
		}
		mCurrentMesh.addFace(face);
	}
	
	
	private int getIndex(String index, int size) {
		return Integer.parseInt(index);
		// TODO OBJフォーマットは負のインデックスも形式として許可されているため、
		// 本当は負のインデックスも処理する必要がある。
		// しかし負のインデックスが使われているデータはあまりなさそうなので実装しない。
//		int idx = Integer.parseInt(index);
//		if (idx < 0) {
//			return size + idx;
//		} else {
//			return idx - 1;  // objフォーマットではfaceのインデックスが１から始まるので、-1して0からのインデックスにする
//		}
	}
	
	public Figure getFigure() {
		return mFigure;
	}
	
//	public void setupDataByFace() {
//		int indicesCount = 0;
//		RawMesh mesh = new RawMesh();
//		mesh.objectName = mFaceList.get(0).objectName;
//		mMeshList.add(mesh);
//		
//		for(Face face: mFaceList) {
//			if(!mesh.objectName.equals(face.objectName)) {
//				mesh = new RawMesh();
//				mesh.objectName = face.objectName;
//				mesh.offsetIndices = indicesCount;
//				mMeshList.add(mesh);
//			}
//			switch(face.numVertex) {
//			case 3:
//				mIndexBuffer.add(face.vertexIndex.get(0).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(1).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(2).shortValue());
//				indicesCount += 3;
//				break;
//			// 面が四角形のときは、GL_TRIANGLEで描画するので、三角形２つ分のIndexを作成する
//			case 4:
//				mIndexBuffer.add(face.vertexIndex.get(0).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(1).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(2).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(2).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(3).shortValue());
//				mIndexBuffer.add(face.vertexIndex.get(0).shortValue());
//				indicesCount += 6;
//				break;
//			}
//			mesh.numIndices = indicesCount;
//			
//			if(face.hasUV) {
//				for(int i=0; i<face.numVertex; i++) {
//					int vertexIdx = face.vertexIndex.get(i);
//					int uvIdx = face.uvIndex.get(i);
//					Vertex v = mVertexBuffer.getVertex(vertexIdx);
//					v.uv = mTempUV.get(uvIdx);
//				}
//			}
//		}
//	}
}
