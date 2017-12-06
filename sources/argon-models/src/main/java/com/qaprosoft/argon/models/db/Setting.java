package com.qaprosoft.argon.models.db;

public class Setting extends AbstractEntity
{
	private static final long serialVersionUID = -380292703062844394L;

	private boolean isSoundEnabled;
	private boolean isNewsEnabled;

	public boolean isSoundEnabled() {
		return isSoundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		isSoundEnabled = soundEnabled;
	}

	public boolean isNewsEnabled() {
		return isNewsEnabled;
	}

	public void setNewsEnabled(boolean newsEnabled) {
		isNewsEnabled = newsEnabled;
	}
}
