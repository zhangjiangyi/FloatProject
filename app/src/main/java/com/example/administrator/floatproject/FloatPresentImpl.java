package com.example.administrator.floatproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**悬浮窗控制类*/
class FloatPresentImpl {
//	protected static final String TAG = "FloatPresentImpl";
	private ServiceConnection floatSConnection ;
	private Intent floatServiceIntent;
	private FloatService.FloatBinder mFloatBinder;
	/**传递进来的主Activity*/
	private Activity mActivity;
	private static FloatPresentImpl sLoginPresentImpl;
	private boolean isCreateFloat = false;

	static synchronized FloatPresentImpl getInstance(){
		if (sLoginPresentImpl == null) {
			sLoginPresentImpl = new FloatPresentImpl();
		}
		return sLoginPresentImpl;
	}

	/**
	 * 显示悬浮窗口  把登录成功的信息返回  创建悬浮窗服务 
	 */
    void showFloatBtn(Activity activity){
		mActivity = activity;
		if (floatServiceIntent == null) {
			floatServiceIntent = new Intent(activity,FloatService.class);
			floatSConnection = new ServiceConnection() {	//悬浮窗服务连服务连接
				@Override
				public void onServiceDisconnected(ComponentName name) {
					if (mFloatBinder != null) {
						mFloatBinder.removeFloat();
					}
				}
				@Override
				public void onServiceConnected(ComponentName name, IBinder service) {
					mFloatBinder = (FloatService.FloatBinder) service;
					mFloatBinder.createFloat(mActivity);
				}
			};
		}
		activity.getApplicationContext().bindService(floatServiceIntent, floatSConnection, Activity.BIND_AUTO_CREATE);
		isCreateFloat = true;		//创建了悬浮窗
	}


	/**是否创建了悬浮窗*/
	public boolean isCreateFloat(){
		return isCreateFloat;
	}

	/**
	 * 销毁float
	 */
	public void destoryFloat() {
		if (floatSConnection != null) {
			mActivity.getApplicationContext().unbindService(floatSConnection);
			floatSConnection = null;
			isCreateFloat = false;
		}
	}
}
