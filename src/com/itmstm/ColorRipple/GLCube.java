package com.itmstm.ColorRipple;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

public class GLCube {
	private final IntBuffer mVertexBuffer;
	private final IntBuffer mTextureBuffer;
	
	final int ONE = 65536;
	final int HALF = ONE / 2;
	
	public GLCube() {
		int vertices[] = {
				-HALF, -HALF, HALF, HALF, -HALF, HALF,
				-HALF,  HALF, HALF,  HALF, HALF, HALF,
				
				-HALF, -HALF, -HALF, HALF, -HALF, -HALF,
				-HALF,  HALF, -HALF,  HALF, HALF, -HALF,
		
				-HALF, -HALF, HALF, -HALF, HALF, HALF, 
				-HALF, -HALF, -HALF, -HALF, HALF, -HALF,
				
				HALF, -HALF, -HALF, HALF, HALF, -HALF, 
				HALF, -HALF, HALF, HALF, HALF, HALF,
				
				-HALF, HALF, HALF, HALF, HALF, HALF,
				-HALF, HALF, -HALF, HALF, HALF, -HALF, 
				
				-HALF, -HALF, HALF, -HALF, -HALF, -HALF, 
				HALF, -HALF, HALF, HALF, -HALF, -HALF, 
		};
		
		int texCoords[] = {
				0, ONE, ONE, ONE, 0, 0, ONE, 0, // front
				ONE, ONE, ONE, 0, 0, ONE, 0, 0, // back
				ONE, ONE, ONE, 0, 0, ONE, 0, 0, // left
				ONE, ONE, ONE, 0, 0, ONE, 0, 0, // right
				ONE, 0, 0, 0, ONE, ONE, 0, ONE, // up
				0, 0, 0, ONE, ONE, 0, ONE, ONE, // bottom
		};
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4 );
		vbb.order( ByteOrder.nativeOrder() );
		mVertexBuffer = vbb.asIntBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
		
		ByteBuffer tbb = ByteBuffer.allocateDirect(texCoords.length * 4 );
		tbb.order( ByteOrder.nativeOrder() );
		mTextureBuffer = tbb.asIntBuffer();
		mTextureBuffer.put(texCoords);
		mTextureBuffer.position(0);
	}
	
	static void loadTexture( GL10 gl, Context context, int resource ) {
		Bitmap bmp = BitmapFactory.decodeResource( context.getResources(), resource );
		GLUtils.texImage2D( GL10.GL_TEXTURE_2D, 0, bmp, 0 );
		gl.glTexParameterx( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR );
		gl.glTexParameterx( GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR );
		
		bmp.recycle();
	}

	public void draw( GL10 gl ) {
		gl.glEnable(GL10.GL_TEXTURE_2D );
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, mTextureBuffer );
		
		gl.glVertexPointer( 3, GL10.GL_FIXED, 0, mVertexBuffer );
		
		gl.glColor4f( 1.f, 1.f, 1.f, 1.f );
		
		gl.glNormal3f( 0.f, 0.f, 1.f );
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 0, 4 );
		gl.glNormal3f( 0.f, 0.f, -1.f );
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 4, 4 );
		
		gl.glColor4f( 1.f, 1.f, 1.f, 1.f );
		
		gl.glNormal3f( -1.f, 0.f, 0.f );
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 8, 4 );
		gl.glNormal3f( 1.f, 0.f, 0.f );
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 12, 4 );
			
		gl.glColor4f( 1.f, 1.f, 1.f, 1.f );
		
		gl.glNormal3f( 0.f, 1.f, 0.f );
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 16, 4 );
		gl.glNormal3f( 0.f, -1.f, 0.f );
		gl.glDrawArrays( GL10.GL_TRIANGLE_STRIP, 20, 4 );
		
	}
}
