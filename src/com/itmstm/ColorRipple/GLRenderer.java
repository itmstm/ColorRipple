package com.itmstm.ColorRipple;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.itmstm.ColorRipple.R;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class GLRenderer implements Renderer {
	private static final String TAG = "GLRenderer";
	private final Context context;
	private final GLCube cube = new GLCube();
	private int numFrames;
	private long startTime;
	private long fpsStartTime;
	private boolean seeThru;

	public GLRenderer( Context context ) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.seeThru = false;
	}

	public void onDrawFrame(GL10 gl) {
		// clear screen
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		// model view projection
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glTranslatef(0,  0, -3.f );
		
		// rotation
		long elapsed = System.currentTimeMillis() - startTime;
		gl.glRotatef( elapsed * (30.f / 1000.f ), 0, 1, 0 );
		gl.glRotatef( elapsed * (15.f / 1000.f ), 1, 0, 0 );
		
		if( seeThru ) {
			gl.glDisable( GL10.GL_DEPTH_TEST );
			gl.glEnable(GL10.GL_BLEND);
			gl.glBlendFunc( GL10.GL_SRC_ALPHA, GL10.GL_ONE );
		}
		else {
			gl.glEnable( GL10.GL_DEPTH_TEST );
			gl.glDisable(GL10.GL_BLEND);
			gl.glDepthFunc(GL10.GL_LEQUAL);
		}
		
		cube.draw( gl );
	}

	public boolean isSeeThru() {
		return seeThru;
	}

	public void setSeeThru(boolean seeThru) {
		this.seeThru = seeThru;
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0,  0, width, height );
		gl.glMatrixMode( GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		float ratio = (float) width/height;
		GLU.gluPerspective(gl, 45.f, ratio, 1, 100.f );
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable( GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		// gl.glDisable( GL10.GL_DITHER );
		
		float lightAmbient[] = new float[] { 0.2f, 0.2f, 0.2f, 1.f };
		float lightDiffuse[] = new float[] { 1.f, 1.f, 1.f, 1.f, };
		float lightPos[] = new float[] { 1.f, 1.f, 1.f, 1.f };
		
		// Lighting
		gl.glEnable( GL10.GL_LIGHTING );
		gl.glEnable( GL10.GL_LIGHT0 );
		gl.glLightfv( GL10.GL_LIGHT0, GL10.GL_AMBIENT, lightAmbient, 0 ); 
		gl.glLightfv( GL10.GL_LIGHT0, GL10.GL_DIFFUSE, lightDiffuse, 0 ); 
		gl.glLightfv( GL10.GL_LIGHT0, GL10.GL_POSITION, lightPos, 0 ); 
		
		// Material
		float matAmbient[] = new float[] {1.f, 1.f, 1.f, 1.f };
		float matDiffuse[] = new float[] {1.f, 1.f, 1.f, 1.f };
		gl.glMaterialfv( GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, matAmbient, 0 );
		gl.glMaterialfv( GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, matDiffuse, 0 );
		
		// time
		startTime = System.currentTimeMillis();
		fpsStartTime = startTime;
		numFrames = 0;
		
		// load texture
		gl.glEnableClientState( GL10.GL_TEXTURE_COORD_ARRAY );
		gl.glEnable(GL10.GL_TEXTURE_2D );
		
		GLCube.loadTexture(gl,  context, R.drawable.ore );
	}

}
