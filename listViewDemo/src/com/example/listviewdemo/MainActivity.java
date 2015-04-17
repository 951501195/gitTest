package com.example.listviewdemo;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listView;
	private MyAdapter myAdapter;
	private ArrayList<Image> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = getData();
		listView = (ListView) this.findViewById(R.id.list);
		myAdapter = new MyAdapter(MainActivity.this);
		listView.setAdapter(myAdapter);

		click();
	}

	// item点击事件
	public void click() {
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.putExtra("img", list.get(position).getImgUrl());
				intent.setClass(MainActivity.this, ShowImageActivity.class);
				startActivity(intent);

			}
		});
	}

	// 设置数据
	public ArrayList<Image> getData() {
		ArrayList<Image> images = new ArrayList<Image>();
		Image m1 = new Image("汽车1",
				"http://img4.imgtn.bdimg.com/it/u=3007658469,852372451&fm=21&gp=0.jpg");
		Image m2 = new Image("汽车2",
				"http://pic.ffpic.com/files/tupian/0429mlsx33.jpg");
		Image m3 = new Image("汽车3",
				"http://img1.xcar.com.cn/news/1656/1740/20100714162551450255.jpg");
		Image m4 = new Image("汽车4",
				"http://pic6.nipic.com/20100327/3178009_000746611039_2.jpg");
		Image m5 = new Image(
				"汽车5",
				"http://www.18603425522.com/img/aHR0cDovL2ltZzEueGNhci5jb20uY24vZ3VpZGUvMTYxOC8yMzU5LzIwMTAwNzE4MjI0NjI0NDU0ODg1LmpwZw==.jpg");
		Image m6 = new Image("汽车6",
				"http://image6.tuku.cn/pic/wallpaper/qiche/chaoda3/052.jpg");
		images.add(m1);
		images.add(m2);
		images.add(m3);
		images.add(m4);
		images.add(m5);
		images.add(m6);
		return images;
	}

	// 自定义适配器
	public class MyAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;

		private MyImageView img;
		private TextView name;
		public MyAdapter(Context context) {
			this.context = context;
			inflater = LayoutInflater.from(context);

		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_list, null);

			}
			img = (MyImageView) convertView.findViewById(R.id.image);
			img.setSize(getWidth(), (int) (getWidth() * 0.75));
			name = (TextView) convertView.findViewById(R.id.text);
			name.setText(list.get(position).getName());

			// 设置imageloader加载图片时的一些属性
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.showStubImage(R.drawable.loading)
					.showImageForEmptyUri(R.drawable.fail)
					.showImageOnFail(R.drawable.fail).cacheInMemory(true)
					.build();
			ImageLoader imageLoader = ImageLoader.getInstance();
			if (!imageLoader.isInited()) {
				imageLoader.init(ImageLoaderConfiguration
						.createDefault(MainActivity.this));
			}
			imageLoader.displayImage(list.get(position).getImgUrl(), img,
					options);
			final int num = position;
			return convertView;
		}

		// 获取屏幕宽度
		public int getWidth() {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			int width = wm.getDefaultDisplay().getWidth();
			return width;
		}
	}

}
