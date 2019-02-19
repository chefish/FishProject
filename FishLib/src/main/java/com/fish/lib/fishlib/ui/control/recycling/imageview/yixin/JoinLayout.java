package com.fish.lib.fishlib.ui.control.recycling.imageview.yixin;

public class JoinLayout {
	/**
	 * [dimension]
	 */
	private static final float [] dimensions = new float [] {
		1.0f,
		0.35f,
		0.25f,
	};
		
	/**
	 * [dimension][divide][index]
	 */
	private static final float [][][] offsets = new float [][][] {
		new float [][] {
			new float [] {0},
		},
		new float [][] {
			new float [] {(1 - dimensions[1]) / 2},	
			new float [] {(1 - dimensions[1] * 2) / 3, (1 - dimensions[1] * 2) / 3 * 2 + dimensions[1]},	
		},
		new float [][] {
			new float [] {(1 - dimensions[2]) / 2},	
			new float [] {1 / 2f - (1 - dimensions[2] * 3) / 4 / 2 - dimensions[2], 1 / 2f + (1 - dimensions[2] * 3) / 4 / 2},	
			new float [] {(1 - dimensions[2] * 3) / 4, (1 - dimensions[2] * 3) / 4 * 2 + dimensions[2], (1 - dimensions[2] * 3) / 4 * 3 + dimensions[2] * 2},	
		},
	};
	
	private static final float[][] sizes = {
		new float[] {dimensions[0], dimensions[0]},
		new float[] {dimensions[1], dimensions[1]},
		new float[] {dimensions[1], dimensions[1]},
		new float[] {dimensions[1], dimensions[1]},
		new float[] {dimensions[2], dimensions[2]},
		new float[] {dimensions[2], dimensions[2]},
		new float[] {dimensions[2], dimensions[2]},
		new float[] {dimensions[2], dimensions[2]},
		new float[] {dimensions[2], dimensions[2]},
	};
	
	private static final float[][][] positions =  {
		new float[][] {
			new float[] {0, 0},
		},
		
		new float[][] {
			new float[] {offsets[1][1][0], offsets[1][0][0]},
			new float[] {offsets[1][1][1], offsets[1][0][0]},
		},
		new float[][] {
			new float[] {offsets[1][0][0], offsets[1][1][0]},

			new float[] {offsets[1][1][0], offsets[1][1][1]},
			new float[] {offsets[1][1][1], offsets[1][1][1]},
		},
		new float[][] {
			new float[] {offsets[1][1][0], offsets[1][1][0]},
			new float[] {offsets[1][1][1], offsets[1][1][0]},
			new float[] {offsets[1][1][0], offsets[1][1][1]},
			new float[] {offsets[1][1][1], offsets[1][1][1]},
		},
		
		new float[][] {
			new float[] {offsets[2][1][0], offsets[2][1][0]},
			new float[] {offsets[2][1][1], offsets[2][1][0]},		
			
			new float[] {offsets[2][2][0], offsets[2][1][1]},
			new float[] {offsets[2][2][1], offsets[2][1][1]},
			new float[] {offsets[2][2][2], offsets[2][1][1]},
		},
		new float[][] {
			new float[] {offsets[2][2][0], offsets[2][1][0]},
			new float[] {offsets[2][2][1], offsets[2][1][0]},
			new float[] {offsets[2][2][2], offsets[2][1][0]},
			
			new float[] {offsets[2][2][0], offsets[2][1][1]},
			new float[] {offsets[2][2][1], offsets[2][1][1]},
			new float[] {offsets[2][2][2], offsets[2][1][1]},
		},
		new float[][] {
			new float[] {offsets[2][0][0], offsets[2][2][0]},
			
			new float[] {offsets[2][2][0], offsets[2][2][1]},
			new float[] {offsets[2][2][1], offsets[2][2][1]},		
			new float[] {offsets[2][2][2], offsets[2][2][1]},
			
			new float[] {offsets[2][2][0], offsets[2][2][2]},
			new float[] {offsets[2][2][1], offsets[2][2][2]},				
			new float[] {offsets[2][2][2], offsets[2][2][2]},
		},
		new float[][] {
			new float[] {offsets[2][1][0], offsets[2][2][0]},
			new float[] {offsets[2][1][1], offsets[2][2][0]},
			
			new float[] {offsets[2][2][0], offsets[2][2][1]},			
			new float[] {offsets[2][2][1], offsets[2][2][1]},
			new float[] {offsets[2][2][2], offsets[2][2][1]},
			
			new float[] {offsets[2][2][0], offsets[2][2][2]},	
			new float[] {offsets[2][2][1], offsets[2][2][2]},
			new float[] {offsets[2][2][2], offsets[2][2][2]},
		},
		new float[][] {
			new float[] {offsets[2][2][0], offsets[2][2][0]},
			new float[] {offsets[2][2][1], offsets[2][2][0]},
			new float[] {offsets[2][2][2], offsets[2][2][0]},	
			
			new float[] {offsets[2][2][0], offsets[2][2][1]},
			new float[] {offsets[2][2][1], offsets[2][2][1]},
			new float[] {offsets[2][2][2], offsets[2][2][1]},		
			
			new float[] {offsets[2][2][0], offsets[2][2][2]},
			new float[] {offsets[2][2][1], offsets[2][2][2]},
			new float[] {offsets[2][2][2], offsets[2][2][2]},
		}
	};

	public static int max() {
		return positions.length;
	}

	public static float[] size(int count) {
		return count > 0 && count <= sizes.length ? sizes[count - 1] : null;
	}
	
	public static float[][] positions(int count) {
		return count > 0 && count <= positions.length ? positions[count - 1] : null;
	}
}
