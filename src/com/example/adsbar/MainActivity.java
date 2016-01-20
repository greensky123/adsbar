package com.example.adsbar;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.example.adsbar.info.TurnViewInfo;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	List<TurnViewInfo> mTurnViewList;
	// private View mDotView;
	private List<View> mDotList;
	private boolean isDotEnable = true;
	private ViewPager mViewPager;
	private LinearLayout dotContaner;
	private TextView mTvDesc1;
	private TextView mTvDesc2;
	private int mCurrentItem = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initVeiw();
	}

	private void initVeiw() {
		// TODO Auto-generated method stub
		mTurnViewList = new ArrayList<TurnViewInfo>();
		mDotList = new ArrayList<View>();
		mViewPager = (ViewPager) findViewById(R.id.banner_vp);
		dotContaner = (LinearLayout) findViewById(R.id.ll_dotcontainer);
		mTvDesc1 = (TextView) findViewById(R.id.tv_desctitle1);
		mTvDesc2 = (TextView) findViewById(R.id.tv_desctitle2);
		prepareData();
		
		MyViewPagerAdapter pagerAdapter = new MyViewPagerAdapter();
		mViewPager.setAdapter(pagerAdapter);
		mViewPager.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%mTurnViewList.size());

		mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				position=position%mDotList.size();
              mCurrentItem=position;
           mTvDesc1.setText(mTurnViewList.get(mCurrentItem).getDescTitle1());
           mTvDesc2.setText(mTurnViewList.get(mCurrentItem).getDescTitle2());
           for (int i = 0; i < mDotList.size(); i++) {
				if (mCurrentItem==i) {
					mDotList.get(i).setEnabled(true);
				}else{
					mDotList.get(i).setEnabled(false);
				}
			}		
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		handler.sendEmptyMessageDelayed(0,2000);
	}
 private Handler handler =new Handler(){
	public void handleMessage(android.os.Message msg) {
		mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
		handler.sendEmptyMessageDelayed(0, 2000);
	}; 
 };
	
	private void addDotContainer() {
		// TODO Auto-generated method stub
		for (int i = 0; i < mDotList.size(); i++) {
			if (i == mCurrentItem) {
				isDotEnable = true;
			} else {
				isDotEnable = false;
			}
			mDotList.get(i).setEnabled(isDotEnable);
			dotContaner.addView(mDotList.get(i));
		}
	}

	/**
	 * 准备要滑动的view和dot
	 */
	private void prepareData() {

		// TODO Auto-generated method stub
		int[] imgIds = new int[] { R.drawable.manzi, R.drawable.kate01, R.drawable.gailun, R.drawable.daomei,
				R.drawable.yasuo, R.drawable.nuoshou2, R.drawable.wushuang };
		String[] descTitle1s = new String[] { "蛮族之王", "卡特", "盖伦", "刀妹", "亚索", "诺手", "无双剑姬" };
		String[] descTitle2s = new String[] { "召唤师你的光辉时刻是什么时候?", "死亡莲花将再次绽放", "呔，看大宝剑", "艾欧尼亚不会灭亡", "死亡如风，常伴吾身",
				"只有我才能带领你们走向胜利", "要来一场利刃华尔兹吗？" };
		for (int i = 0; i < imgIds.length; i++) {
			TurnViewInfo turnViewInfo = new TurnViewInfo();
			turnViewInfo.setIvContent(buildPagerView(imgIds[i]));
			turnViewInfo.setDescTitle1(descTitle1s[i]);
			turnViewInfo.setDescTitle2(descTitle2s[i]);
			mTurnViewList.add(turnViewInfo);
			if (i == mCurrentItem) {
				isDotEnable = true;
			} else {
				isDotEnable = false;
			} 	
			mDotList.add(buidDotView());
		}
		mTvDesc1.setText(mTurnViewList.get(mCurrentItem).getDescTitle1());
		mTvDesc2.setText(mTurnViewList.get(mCurrentItem).getDescTitle2());
		addDotContainer();
	}

	/**
	 * 建立dotview
	 */
	private View buidDotView() {
		View dotview = new View(this);
		dotview.setEnabled(isDotEnable);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(3, 5, 3, 10);
		layoutParams.width = 15;
		layoutParams.height = 15;
		dotview.setLayoutParams(layoutParams);
		dotview.setBackgroundResource(R.drawable.selecor_bg_dotview);
		return dotview;
	}

	/**
	 * 建立滑动的view
	 */
	private ImageView buildPagerView(int resId) {
		ImageView iv = new ImageView(this);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		iv.setLayoutParams(layoutParams);
		iv.setImageResource(resId);

		return iv;
	}

	/**
	 * vpadpter
	 */
	class MyViewPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			position=position%mTurnViewList.size();
			container.removeView(mTurnViewList.get(position).getIvContent());
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			position=position%mTurnViewList.size();
			ImageView pagerView = mTurnViewList.get(position).getIvContent();
			// ((ViewPager) container).addView(iv);
			container.addView(pagerView);
			return pagerView;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

}
