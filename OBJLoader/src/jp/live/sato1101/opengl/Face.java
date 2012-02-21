package jp.live.sato1101.opengl;

import java.util.ArrayList;
import java.util.List;

public class Face {
	public List<Integer> vertexIndex = new ArrayList<Integer>();
	public List<Integer> normalIndex = new ArrayList<Integer>();
	public List<Integer> uvIndex = new ArrayList<Integer>();
	public int numVertex;
	
	public boolean hasNormal;
	public boolean hasUV;
}
