package com.itmstm.ColorRipple;

import com.itmstm.ColorRipple.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ColorRippleActivity extends Activity {
	
	private static final int MENU_SEE_THRU = 0;
	private static final int MENU_NO_SEE_THRU = 1;
	private static final String TAG = "ColorRippleActivity";
	GLView view;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LinearLayout l = (LinearLayout) findViewById( R.id.MyLinearLayout );
        if( l == null ) 
        	Log.d(TAG, "Not found!");
        view = new GLView(this);
        
        l.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 
        		                   ViewGroup.LayoutParams.WRAP_CONTENT) );
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		view.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		view.onResume();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean ret = super.onCreateOptionsMenu(menu);
		return ret;	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch( item.getItemId()) {
		case MENU_SEE_THRU:
			view.getRenderer().setSeeThru(true);
			return true;
		case MENU_NO_SEE_THRU:
			view.getRenderer().setSeeThru(false);
		}
		return false;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		boolean ret = super.onPrepareOptionsMenu(menu);
		menu.clear();
		
		return ret;
	}
}