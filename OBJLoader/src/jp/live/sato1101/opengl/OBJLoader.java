package jp.live.sato1101.opengl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
	public static Mesh load(InputStream in) {
		List<String> lines;
		try {
			lines = readLines(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		Mesh mesh = new Mesh();
		
		for(int i=0; i<lines.size(); i++) {
			String line = lines.get(i);
			
			// 頂点位置情報を取得する
			if (line.startsWith("v ")) {
				mesh.parseVertex(line);
				continue;
			}
			
			// 法線情報を取得する
			if (line.startsWith("vn ")) {
				mesh.parseNormal(line);
				continue;
			}
			
			//　UV情報を取得する
			if (line.startsWith("vt")) {
				mesh.parseUV(line);
				continue;
			}
			
			// face情報を取得する
			if (line.startsWith("f ")) {
				mesh.parseFace(line);
				continue;
			}
		}
		mesh.setupDataByFace();
		return mesh;
	}
	
	public static List<String> readLines(InputStream in) throws IOException {
		List<String> lines = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) != null)
			lines.add(line);
		return lines;
	}
}
