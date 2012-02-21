package jp.live.sato1101.opengl;

import java.util.ArrayList;
import java.util.List;

public class IndexBuffer {
	private final List<Short> mIndexList = new ArrayList<Short>();
	
	public void add(short index) {
		mIndexList.add(index);
	}
	
	public int getIndexCount() {
		return mIndexList.size();
	}
	
	public short get(int index) {
		return mIndexList.get(index);
	}
	
	/**
	 * Indices配列を返す
	 * @return
	 */
	public short[] getIndicesArray() {
		short[] indices = new short[getIndexCount()];
		for(int i=0; i<getIndexCount(); i++) {
			indices[i] = mIndexList.get(i);
		}
		return indices;
	}
}
