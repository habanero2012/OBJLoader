package jp.live.sato1101.opengl;

import java.util.ArrayList;
import java.util.List;

public class VertexBuffer {
	private final List<Vertex> mVertexList = new ArrayList<Vertex>();
	
	public void addVertex(Vertex vertex) {
		mVertexList.add(vertex);
	}
	
	public int getVertexCount() {
		return mVertexList.size();
	}
	
	public Vertex getVertex(int index) {
		return mVertexList.get(index);
	}
	
	public boolean hasUV() {
		Vertex v = mVertexList.get(0);
		return v.uv != null;
	}
	
	public boolean hasNormal() {
		Vertex v = mVertexList.get(0);
		return v.normal != null;
	}
	
	/**
	 * 頂点要素の配列を返す
	 * 
	 * 頂点位置情報しか無い場合は、頂点位置の情報を x,y,z,x,y,z,x,y,z,…の順番で返す
	 * 
	 * UV情報とNormal情報がある場合は、x,y,z,u,v,nx,ny,nz,x,y,…の順番で返す
	 * @return
	 */
	public float[] getVertexArray() {
		int vertexSize = 3;
		
		if(hasUV()) {
			vertexSize += 2;
		}
		
		if(hasNormal()) {
			vertexSize += 3;
		}
		
		float[] result = new float[getVertexCount()*vertexSize];
		for(int i=0, j=0; i<getVertexCount(); i++) {
			Vertex vertex = getVertex(i);
			result[j++] = vertex.position.x;
			result[j++] = vertex.position.y;
			result[j++] = vertex.position.z;
			
			if(hasUV()) {
				result[j++] = vertex.uv.x;
				result[j++] = vertex.uv.y;
			}
			
			if(hasNormal()) {
				result[j++] = vertex.normal.x;
				result[j++] = vertex.normal.y;
				result[j++] = vertex.normal.z;
			}
		}
		return result;
	}
}
