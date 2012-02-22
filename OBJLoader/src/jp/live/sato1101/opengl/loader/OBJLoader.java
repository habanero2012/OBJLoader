package jp.live.sato1101.opengl.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class OBJLoader {
	public static Figure load(InputStream in) {
		List<String> lines;
		try {
			lines = readLines(in);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		Parser parser = new Parser();
		
		for(int i=0; i<lines.size(); i++) {
			String line = lines.get(i);
			
			// Object名を取得する
			if (line.startsWith("o ")) {
				parser.createRawMesh();
				parser.parseObjectName(line);
				continue;
			}
			
			// 頂点位置情報を取得する
			if (line.startsWith("v ")) {
				parser.parsePosition(line);
				continue;
			}
			
			// 法線情報を取得する
			if (line.startsWith("vn ")) {
				parser.parseNormal(line);
				continue;
			}
			
			//　UV情報を取得する
			if (line.startsWith("vt")) {
				parser.parseUV(line);
				continue;
			}
			
			// face情報を取得する
			if (line.startsWith("f ")) {
				parser.parseFace(line);
				continue;
			}
		}

		return parser.getFigure();
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
