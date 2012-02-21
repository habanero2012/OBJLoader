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
}
