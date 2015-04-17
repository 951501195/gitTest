package com.example.listviewdemo;

public class Image {
	private String imgUrl;
	private String name;
	
	public Image(String name, String imgUrl) {
		this.name = name;
		this.imgUrl = imgUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
