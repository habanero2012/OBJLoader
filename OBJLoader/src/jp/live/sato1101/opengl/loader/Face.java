package jp.live.sato1101.opengl.loader;

import java.util.ArrayList;
import java.util.List;

public class Face {
	public final List<Integer> vertexIndex = new ArrayList<Integer>();
	public final List<Integer> normalIndex = new ArrayList<Integer>();
	public final List<Integer> uvIndex = new ArrayList<Integer>();
	public int numVertex;
}
