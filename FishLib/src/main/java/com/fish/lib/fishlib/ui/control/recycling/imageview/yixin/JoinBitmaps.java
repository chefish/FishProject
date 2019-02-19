package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * 把几张图联合在一起
 */
public class JoinBitmaps {
	public static final void join(Canvas canvas, int[] dimension, Bitmap bitmap) {
		// paint
		Paint paint = new Paint();
		paint.setAntiAlias(true);

		Matrix matrix = new Matrix();
		// scale as destination
		matrix.postScale((float)dimension[0] / bitmap.getWidth(), (float)dimension[1] / bitmap.getHeight());
	
		canvas.drawBitmap(bitmap, matrix, paint);
	}
	
	public static final void join(Canvas canvas, int[] dimension, List<Bitmap> bitmaps) {
		// layout
		int count = Math.min(bitmaps.size(), JoinLayout.max());
		float[] size = JoinLayout.size(count);
		float[][] positions = JoinLayout.positions(count);		
		
		// paint
		Paint paint = new Paint();
		paint.setAntiAlias(true);
				
		Matrix matrixJoin = new Matrix();
		// scale as join size
		matrixJoin.postScale(size[0], size[1]);
		
		for (int index = 0; index < count; index++) {
			Bitmap bitmap = bitmaps.get(index);
			float[] position = positions[index];
			
			Matrix matrix = new Matrix();
			// scale as destination
			matrix.postScale((float)dimension[0] / bitmap.getWidth(), (float)dimension[1] / bitmap.getHeight());
			// concatenate join matrix
			matrix.postConcat(matrixJoin);
			
			canvas.save();

			canvas.translate(position[0] * dimension[0], position[1] * dimension[1]);

			canvas.drawBitmap(bitmap, matrix, paint);
			
			canvas.restore();
		}
	}
	
	private static final float o = 11f / 36f;
	private static final float i = 10f / 36f;
	
	private static final float[][][] p = new float[][][] {
		new float[][] {
			new float[] {0.5f - o , 0},				
			new float[] {0.5f - i , o - i},				
		},
		new float[][] {
			new float[] {1.0f - o * 2, 1.0f - o * 2},
			new float[] {1.0f - o * 2 + o - i, 1.0f - o * 2 + o - i},				
		},
		new float[][] {
			new float[] {0, 1.0f - o * 2},
			new float[] {o - i, 1.0f - o * 2 + o - i},				
		},
		
	};
	
	public static final void join2(Canvas canvas, int[] dimension, List<Bitmap> bitmaps) {		
		canvas.save();
		
		// EMPTY CLIP
		canvas.clipPath(new Path());
		
		int size = Math.min(p.length, bitmaps.size());
		for (int index = 0; index < size; index++) {
			Bitmap bitmap = bitmaps.get(index);
			
			// POSITION
			float[] posOut = new float[] {
				p[index][0][0] * dimension[0],
				p[index][0][1] * dimension[1],
			};

			// RADIUS
			float rdsOut = dimension[0] * o;
			float rdsIn = dimension[0] * i;

			// PATH
			Path pathOut = new Path();
			pathOut.addCircle(posOut[0] + rdsOut, posOut[1] + rdsOut, rdsOut, Direction.CW);
			
			// MATRIX
			Matrix matrix = new Matrix();
			// scale as destination
			matrix.postScale((float)dimension[0] / bitmap.getWidth(), (float)dimension[1] / bitmap.getHeight());
			
			canvas.save();
			
			canvas.clipPath(pathOut, Region.Op.REVERSE_DIFFERENCE);
			
			// paint
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			
			matrix.postScale(2 * o, 2 * o);	
			canvas.translate(posOut[0], posOut[1]);
			canvas.drawBitmap(bitmap, matrix, paint);
			canvas.translate(-posOut[0], -posOut[1]);

			paint.setStyle(Style.STROKE);
			paint.setColor(Color.WHITE);
			paint.setStrokeWidth(rdsOut - rdsIn);
			canvas.drawCircle(posOut[0] + rdsOut, posOut[1] + rdsOut, rdsOut, paint);
			
			canvas.restore();	
			
			canvas.clipPath(pathOut, Region.Op.UNION);
		}
		
		canvas.restore();
	}
	
	public static final void join3(Canvas canvas, int[] dimension, List<Bitmap> bitmaps) {	
		int size = Math.min(p.length, bitmaps.size());
		for (int index = size - 1; index >= 0; index--) {
			Bitmap bitmap = bitmaps.get(index);
			
			// POSITION
			float[] posOut = new float[] {
				p[index][0][0] * dimension[0],
				p[index][0][1] * dimension[1],
			};

			// RADIUS
			float rdsOut = dimension[0] * o;
			float rdsIn = dimension[0] * i;
			
			// BOUND
			RectF rect = new RectF(posOut[0], posOut[1], posOut[0] + 2 * rdsOut, posOut[1] + 2 * rdsOut);

			// MATRIX
			Matrix matrix = new Matrix();
			// scale as destination
			matrix.postScale((float)dimension[0] / bitmap.getWidth(), (float)dimension[1] / bitmap.getHeight());
			
			canvas.saveLayer(rect, null, Canvas.ALL_SAVE_FLAG);

			{
				// paint
				Paint paint = new Paint();
				paint.setAntiAlias(true);
				
				matrix.postScale(2 * o, 2 * o);	
				canvas.translate(posOut[0], posOut[1]);
				canvas.drawBitmap(bitmap, matrix, paint);
				canvas.translate(-posOut[0], -posOut[1]);
				
				paint.setStyle(Style.STROKE);
				paint.setColor(Color.WHITE);
				paint.setStrokeWidth(rdsOut - rdsIn);
				canvas.drawCircle(posOut[0] + rdsOut, posOut[1] + rdsOut, rdsOut, paint);
			}
			
			{
				// paint
				Paint paint = new Paint();
				paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
				
				canvas.saveLayer(rect, paint, Canvas.ALL_SAVE_FLAG);				
			}

			{
				// paint
				Paint paint = new Paint();
				paint.setAntiAlias(true);
				
				paint.setStyle(Style.FILL);
				paint.setColor(Color.BLACK);
				
				canvas.drawCircle(posOut[0] + rdsOut, posOut[1] + rdsOut, rdsOut, paint);
		
				canvas.restore();
			}

			canvas.restore();
		}
	}
}
