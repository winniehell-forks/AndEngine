package org.anddev.andengine.util.path;

import java.util.ArrayList;

/**
 * (c) 2010 Nicolas Gramlich
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 23:00:24 - 16.08.2010
 */
public class Path {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<Step> mSteps = new ArrayList<Step>();

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public int getLength() {
		return mSteps.size();
	}

	public Step getStep(final int pIndex) {
		return mSteps.get(pIndex);
	}

	public Direction getDirectionToPreviousStep(final int pIndex) {
		if(pIndex == 0) {
			return null;
		} else {
			final int dX = getTileColumn(pIndex - 1) - getTileColumn(pIndex);
			final int dY = getTileRow(pIndex - 1) - getTileRow(pIndex);
			return Direction.fromDelta(dX, dY);
		}
	}

	public Direction getDirectionToNextStep(final int pIndex) {
		if(pIndex == getLength() - 1) {
			return null;
		} else {
			final int dX = getTileColumn(pIndex + 1) - getTileColumn(pIndex);
			final int dY = getTileRow(pIndex + 1) - getTileRow(pIndex);
			return Direction.fromDelta(dX, dY);
		}
	}

	public int getTileColumn(final int pIndex) {
		return getStep(pIndex).getTileColumn();
	}

	public int getTileRow(final int pIndex) {
		return getStep(pIndex).getTileRow();
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	public void append(final int pTileColumn, final int pTileRow) {
		this.append(new Step(pTileColumn, pTileRow));
	}

	public void append(final Step pStep) {
		mSteps.add(pStep);
	}

	public void prepend(final int pTileColumn, final int pTileRow) {
		this.prepend(new Step(pTileColumn, pTileRow));
	}

	public void prepend(final Step pStep) {
		mSteps.add(0, pStep);
	}

	public boolean contains(final int pTileColumn, final int pTileRow) {
		final ArrayList<Step> steps = mSteps;
		for(int i = steps.size() - 1; i >= 0; i--) {
			final Step step = steps.get(i);
			if(step.getTileColumn() == pTileColumn && step.getTileRow() == pTileRow) {
				return true;
			}
		}
		return false;
	}

	public int getFromTileRow() {
		return getTileRow(0);
	}

	public int getFromTileColumn() {
		return getTileColumn(0);
	}

	public int getToTileRow() {
		return getTileRow(mSteps.size() - 1);
	}

	public int getToTileColumn() {
		return getTileColumn(mSteps.size() - 1);
	}

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public class Step {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		private final int mTileColumn;
		private final int mTileRow;

		// ===========================================================
		// Constructors
		// ===========================================================

		public Step(final int pTileColumn, final int pTileRow) {
			mTileColumn = pTileColumn;
			mTileRow = pTileRow;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		public int getTileColumn() {
			return mTileColumn;
		}

		public int getTileRow() {
			return mTileRow;
		}

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public int hashCode() {
			return mTileColumn << 16 + mTileRow;
		}

		@Override
		public boolean equals(final Object pOther) {
			if(this == pOther) {
				return true;
			}
			if(pOther == null) {
				return false;
			}
			if(this.getClass() != pOther.getClass()) {
				return false;
			}
			final Step other = (Step) pOther;
			if(mTileColumn != other.mTileColumn) {
				return false;
			}
			if(mTileRow != other.mTileRow) {
				return false;
			}
			return true;
		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}

}
