package com.itmstm.ColorRipple;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

public class GLView extends GLSurfaceView {
	private final GLRenderer renderer;
	private ScaleGestureDetector mScaleDetector;
	public GLView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		renderer = new GLRenderer( context );
		setRenderer(renderer);
		
		mScaleDetector = new ScaleGestureDetector( context, new ScaleListener() );
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mScaleDetector.onTouchEvent(event);
		
		return super.onTouchEvent(event);
	}

	public GLRenderer getRenderer() {
		return renderer;
	} 
	
	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			detector.getScaleFactor();
			
			invalidate();
			return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			return super.onScaleBegin(detector);
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
			// TODO Auto-generated method stub
			super.onScaleEnd(detector);
		}
		
	}
}
