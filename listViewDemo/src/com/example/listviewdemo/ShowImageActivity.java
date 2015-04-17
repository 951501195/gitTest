package com.example.listviewdemo;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowImageActivity extends Activity {
	private ImageView image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showimage);
		image = (ImageView)this.findViewById(R.id.showimg);
		//设置imageloader加载图片时的一些属性
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.loading)
				.showImageForEmptyUri(R.drawable.fail)
				.showImageOnFail(R.drawable.fail).cacheInMemory(true)
				.build();
		ImageLoader imageLoader = ImageLoader.getInstance();
		if (!imageLoader.isInited()) {
			imageLoader.init(ImageLoaderConfiguration.createDefault(ShowImageActivity.this));
		 }	
		imageLoader.displayImage(
				getIntent().getStringExtra("img"), image, options);
	}

}
