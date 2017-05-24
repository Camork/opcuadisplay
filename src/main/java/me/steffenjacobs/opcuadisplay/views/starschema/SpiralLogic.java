package me.steffenjacobs.opcuadisplay.views.starschema;

import java.util.LinkedList;
import java.util.List;

public class SpiralLogic {

	public static void main(String[] args) {
		new SpiralLogic(3, 3);
	}

	enum Direction {
		LEFT(new int[] { -1, 0 }), RIGHT(new int[] { 1, 0 }), UP(new int[] { 0, -1 }), DOWN(new int[] { 0, 1 });

		private final int[] delta;

		private Direction(final int[] delta) {
			this.delta = delta;
		}

		public Direction next() {
			switch (this) {
			case LEFT:
				return DOWN;
			case RIGHT:
				return UP;
			case UP:
				return LEFT;
			case DOWN:
				return RIGHT;
			}
			return UP;
		}

		public int[] getDelta() {
			return this.delta;
		}

		public int getDeltaX() {
			return this.delta[0];
		}

		public int getDeltaY() {
			return this.delta[1];
		}
	}

	private int[][] data;
	private int index = 0, maxLength;

	public SpiralLogic(int elemCount) {
		int ceil = (int) Math.ceil(Math.sqrt(elemCount));
		spiralLogic(ceil, ceil);
	}

	public SpiralLogic(int width, int height) {
		spiralLogic(width, height);
	}

	private void spiralLogic(int width, int height) {

		int curX = width / 2;
		int curY = height / 2;

		maxLength = width > height ? width : height;

		Direction dir = Direction.UP;

		List<int[]> list = new LinkedList<int[]>();

		list.add(new int[] { curX, curY });

		for (int i = 1; i < maxLength; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					curX += dir.getDeltaX();
					curY += dir.getDeltaY();

					list.add(new int[] { curX, curY });
				}
				dir = dir.next();
			}
		}

		data = list.toArray(new int[list.size()][]);
	}

	public int[] next() {
		if (index >= data.length) {
			index = 0;
		}
		return data[index++];
	}

	public int getLength() {
		return maxLength;
	}
}