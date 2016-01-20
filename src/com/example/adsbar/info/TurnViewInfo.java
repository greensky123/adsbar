package com.example.adsbar.info;

import android.widget.ImageView;

public class TurnViewInfo {
	private int id;
	private String descTitle1;
	private String descTitle2;
	private ImageView ivContent;

	public TurnViewInfo(String descTitle1, String descTitle2, ImageView ivContent) {
		super();
		this.descTitle1 = descTitle1;
		this.descTitle2 = descTitle2;
		this.ivContent = ivContent;
	}

	public TurnViewInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescTitle1() {
		return descTitle1;
	}

	public void setDescTitle1(String descTitle1) {
		this.descTitle1 = descTitle1;
	}

	public String getDescTitle2() {
		return descTitle2;
	}

	public void setDescTitle2(String descTitle2) {
		this.descTitle2 = descTitle2;
	}

	public ImageView getIvContent() {
		return ivContent;
	}

	public void setIvContent(ImageView ivContent) {
		this.ivContent = ivContent;
	}

}
