package com.example.administrator.floatproject;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class FloatService extends Service{
	//	private static final String TAG = "FloatService";
	private FloatBinder mBinder = new FloatBinder();
	private FloatView mFloatView;

	/**
	 * service创建
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("FloatService", "onCreate");
	}
	/**
	 * activity与service绑定
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	/**
	 * 状态改变的时候销毁掉以前的float然后再重新创建一个新的float
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}
	/**
	 * service销毁
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mFloatView != null) {
			mFloatView.removeAllWindow();
			mFloatView = null;
		}
	}

	class FloatBinder extends Binder{
		/**
		 * 创建float
		 */
        void createFloat(Activity activity){
			mFloatView = new FloatView(activity);
		}
		/**
		 * 销毁float
		 */
        void removeFloat(){
			if (mFloatView != null) {
				mFloatView.removeAllWindow();
				mFloatView = null;
			}
		}
		/**
		 * 重新创建一个float
		 */
		public void reCreateFloat(Activity activity) {
			mFloatView = new FloatView(activity);
		}
	}
}
