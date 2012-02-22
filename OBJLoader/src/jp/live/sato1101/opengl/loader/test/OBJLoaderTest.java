package jp.live.sato1101.opengl.loader.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jp.live.sato1101.opengl.Vector3;
import jp.live.sato1101.opengl.loader.Face;
import jp.live.sato1101.opengl.loader.Figure;
import jp.live.sato1101.opengl.loader.OBJLoader;
import jp.live.sato1101.opengl.loader.RawMesh;

import org.junit.Test;

public class OBJLoaderTest {
	
	@Test
	public void testPenginOBJ() {
		Figure figure = loadFigure("pengin.obj");
//		VertexBuffer vb = figure.getVertexBuffer();
//		Vertex v = vb.getVertex(0);
//		assertArrayEquals(new float[] {
//				    v.position.x, v.position.y, v.position.z
//				}, 
//				new float[] {
//				    0.458335f, 2.604805f, 0.759727f
//				}, 0.00001f);
//		
//		assertNull(v.normal);
	}
	
	@Test
	public void testCylinderOBJ() {
		Figure figure = loadFigure("test_model.obj");
		
		List<RawMesh> meshs = figure.getRawMesh();
		assertArrayEquals(new int[]{1}, new int[]{meshs.size()});
		
		RawMesh mesh = meshs.get(0);
		assertEquals(mesh.getObjectName(), "Cylinder");

		Vector3 v = mesh.getPosition(0);
		assertArrayEquals(new float[] {
				    v.x, v.y, v.z
				}, 
				new float[] {
				    0.450369f, -1.875321f, 0.136948f
				}, 0.00001f);
		
		v = mesh.getPosition(443);
		assertArrayEquals(new float[] {
				    v.x, v.y, v.z
				}, 
				new float[] {
				    0.323936f, 2.414714f, 2.074527f
				}, 0.00001f);
		
		Face face = mesh.getFace(0);
		assertArrayEquals(new int[]{
			    1, 116, 353, 113,
	        }, new int[]{
				face.vertexIndex.get(0), face.vertexIndex.get(1),
				face.vertexIndex.get(2), face.vertexIndex.get(3),
	        });
	}
	
	@Test
	public void testSquareOBJ() {
		Figure figure = loadFigure("square.obj");
		List<RawMesh> meshs = figure.getRawMesh();
		assertArrayEquals(new int[]{1}, new int[]{meshs.size()});
		
		RawMesh mesh = meshs.get(0);
		assertEquals(mesh.getObjectName(), "Plane");
		
		Vector3 v = mesh.getPosition(0);
		assertArrayEquals(new float[] {
				    v.x, v.y, v.z
				}, 
				new float[] {
				    1.197929f, -0.017535f, -1.740655f
				}, 0.00001f);
		
		v = mesh.getPosition(1);
		assertArrayEquals(new float[] {
			    v.x, v.y, v.z
			}, 
			new float[] {
			    1.197929f, -0.017535f, 0.259345f
			}, 0.00001f);
		
		v = mesh.getPosition(2);
		assertArrayEquals(new float[] {
			    v.x, v.y, v.z
			}, 
			new float[] {
				-0.802071f, -0.017535f, 0.259344f
			}, 0.00001f);
		
		v = mesh.getPosition(3);
		assertArrayEquals(new float[] {
			    v.x, v.y, v.z
			}, 
			new float[] {
				-0.802071f, -0.017535f, -1.740656f
			}, 0.00001f);
		
		Face face = mesh.getFace(0);
		assertArrayEquals(new int[]{4}, new int[]{face.numVertex});
		assertArrayEquals(new int[]{
				    1, 4, 3, 2
		        }, new int[]{
				    face.vertexIndex.get(0), face.vertexIndex.get(1),
				    face.vertexIndex.get(2), face.vertexIndex.get(3)
				});
	}
	
	private Figure loadFigure(String fileName) {
		FileInputStream in = null;
		Figure mesh = null;
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
