package jp.live.sato1101.opengl.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jp.live.sato1101.opengl.Face;
import jp.live.sato1101.opengl.IndexBuffer;
import jp.live.sato1101.opengl.Mesh;
import jp.live.sato1101.opengl.OBJLoader;
import jp.live.sato1101.opengl.Vertex;
import jp.live.sato1101.opengl.VertexBuffer;

import org.junit.Test;

public class OBJLoaderTest {
	
	@Test
	public void testSquareOBJ() {
		Mesh mesh = loadMesh("square.obj");
		VertexBuffer vb = mesh.getVertexBuffer();
		assertArrayEquals(new int[]{4}, new int[]{vb.getVertexCount()});
		
		Vertex v = vb.getVertex(0);
		assertArrayEquals(new float[] {
				    v.position.x, v.position.y, v.position.z
				}, 
				new float[] {
				    1.197929f, -0.017535f, -1.740655f
				}, 0.00001f);
		assertNull(v.normal);
		assertNull(v.uv);
		
		v = vb.getVertex(1);
		assertArrayEquals(new float[] {
			    v.position.x, v.position.y, v.position.z
			}, 
			new float[] {
			    1.197929f, -0.017535f, 0.259345f
			}, 0.00001f);
		assertNull(v.normal);
		assertNull(v.uv);
		
		v = vb.getVertex(2);
		assertArrayEquals(new float[] {
			    v.position.x, v.position.y, v.position.z
			}, 
			new float[] {
				-0.802071f, -0.017535f, 0.259344f
			}, 0.00001f);
		assertNull(v.normal);
		assertNull(v.uv);
		
		v = vb.getVertex(3);
		assertArrayEquals(new float[] {
			    v.position.x, v.position.y, v.position.z
			}, 
			new float[] {
				-0.802071f, -0.017535f, -1.740656f
			}, 0.00001f);
		assertNull(v.normal);
		assertNull(v.uv);
		
		List<Face> faceList = mesh.getFaceList();
		assertArrayEquals(new int[]{1}, new int[]{faceList.size()});
		Face face = faceList.get(0);
		assertArrayEquals(new int[]{4}, new int[]{face.numVertex});
		assertArrayEquals(new int[]{
				    0, 3, 2, 1
		        }, new int[]{
				    face.vertexIndex.get(0), face.vertexIndex.get(1),
				    face.vertexIndex.get(2), face.vertexIndex.get(3)
				});
		
		
		IndexBuffer ib = mesh.getIndexBuffer();
		assertArrayEquals(new short[]{
				    0, 3, 2, 2, 1, 0
		        }, new short[]{
				    ib.get(0), ib.get(1), ib.get(2), ib.get(3), ib.get(4), ib.get(5)
		        });
		assertArrayEquals(new int[]{6}, new int[]{ib.getIndexCount()});
	}
	
	private Mesh loadMesh(String fileName) {
		FileInputStream in = null;
		Mesh mesh = null;
		try {
			in = new FileInputStream(fileName);
			mesh = OBJLoader.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mesh;
	}
}
