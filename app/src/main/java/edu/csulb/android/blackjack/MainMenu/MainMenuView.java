package edu.csulb.android.blackjack.MainMenu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import edu.csulb.android.blackjack.R;
import edu.csulb.android.blackjack.Utilities.GameObject;
import edu.csulb.android.blackjack.Utilities.Stage;

/**
 * Created by FelipeGibran on 4/18/2015.
 *
 */
public class MainMenuView extends Stage {

	public MainMenuView(Context context) {
		super(context);
	}

	public MainMenuView(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		screenWidth = this.getWidth();
		screenHeight = this.getHeight();

		GameObject background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
		background.setRenderListener(this);

		GameObject blackjackLogo = new BlackjackLogo(BitmapFactory.decodeResource(getResources(), R.drawable.logo));
		blackjackLogo.setRenderListener(this);

		GameObject tapString = new TapString(BitmapFactory.decodeResource(getResources(), R.drawable.tap));
		tapString.setRenderListener(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}

	/**
	 * Custom GameObject Class named Background.
	 * It means this GameObject has a custom behavior.
	 */
	public class Background extends GameObject {

		static final float DEFAULT_VX = -.5f;
		float scale = width/height;

		public Background(Bitmap bitmap) {
			super(bitmap);
			setWidth(screenHeight*scale);
			setHeight(screenHeight);
			vx = DEFAULT_VX;
		}

		@Override
		public void onUpdate()
		{
			super.onUpdate();
			if(x < -width) x=0;
		}

		@Override
		public void onRender(Canvas canvas)
		{
			super.onRender(canvas);
			x += width;
			super.onRender(canvas);
			x -= width;
		}
	}

	/**
	 * Custom GameObject Class named BlackjackLogo.
	 * It means this GameObject has a custom behavior.
	 */
	public class BlackjackLogo extends GameObject
	{
		public BlackjackLogo(Bitmap bitmap) {
			super(bitmap);
			x = screenWidth/2 - width/2;
			y = 10;
		}
	}

	/**
	 * Custom GameObject Class named TapString.
	 * It means this GameObject has a custom behavior.
	 */
	public class TapString extends GameObject {

		int alpha = 255;

		public TapString(Bitmap bitmap) {
			super(bitmap);
			x = screenWidth/2 - width/2;
			y = screenHeight - height;
		}

		@Override
		public void onRender(Canvas canvas) {
			Paint paint = new Paint();
			paint.setAlpha(Math.abs(alpha));
			if(alpha < -255)
				alpha = 255;
			canvas.drawBitmap(bitmap, x, y, paint);
		}

		@Override
		public void onUpdate() {
			alpha -= 4;
		}
	}
}
