package com.edwardwmd.weather.mvp.model.data;

import android.content.Context;
import android.util.Log;


import com.edwardwmd.weather.R;
import com.edwardwmd.weather.bean.DaoMaster;
import com.edwardwmd.weather.bean.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static com.edwardwmd.weather.utils.ConstantUtils.DB_NAME;
import static com.edwardwmd.weather.utils.ConstantUtils.DB_PATH;


public class DaoManager {


	  private static final String TAG = DaoManager.class.getSimpleName();
	  private Context mC;

        private final int BYTE_STREAM=2048;
	  private volatile static DaoManager manager;  //多线程中要被共享的使用volatile关键字修饰
	  private DaoMaster.DevOpenHelper mDevOpenHelper;
	  private DaoMaster mDaoMaster;
	  private DaoSession mDaoSession;


	  /**
	   * 单例模式操作数据库
	   *
	   * @return
	   */
	  public static DaoManager getInstance() {
		    if (manager == null) {
				synchronized (DaoManager.class) {
					  if (manager == null) {
						    manager = new DaoManager();
					  }
				}

		    }
		    return manager;
	  }


	  public void init(Context context) {
		    this.mC = context;
	  }


	  /**
	   * 判断是否有存在数据库，如果没有则创建
	   *
	   * @return
	   */
	  public DaoMaster getDaoMaster() {
		    if (mDaoMaster == null) {
				DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mC, DB_NAME, null);
				mDaoMaster = new DaoMaster(helper.getWritableDatabase());
		    }
		    return mDaoMaster;
	  }


	  /**
	   * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
	   *
	   * @return
	   */
	  public DaoSession getDaoSession() {
		    if (mDaoSession == null) {
				if (mDaoMaster == null) {
					  mDaoMaster = getDaoMaster();
				}
				mDaoSession = mDaoMaster.newSession();
		    }
		    return mDaoSession;
	  }


	  /**
	   * 打开输出日志，默认关闭
	   */
	  public void setDebug() {
		    QueryBuilder.LOG_SQL = true;
		    QueryBuilder.LOG_VALUES = true;
		    Log.i(TAG, "打开输出日志");
	  }


	  /**
	   * 将assets文件夹下文件拷贝到/databases/下
	   *
	   * @param context
	   * @param db_name
	   */
	  public void copyDbFile(Context context, String db_name) {
		    Log.e("数据库文件路径", "" + DB_PATH);
		    InputStream in = null;
		    FileOutputStream out = null;
		    File file = new File(DB_PATH + db_name);

		    //创建文件夹
		    File filePath = new File(DB_PATH);
		    if (!filePath.exists())
				filePath.mkdirs();

		    if (file.exists())
				return;

		    try {
				in = context.getResources().openRawResource(R.raw.city); // 从raw目录下复制
				out = new FileOutputStream(file);
				int length;
				byte[] buf = new byte[BYTE_STREAM];
				while ((length = in.read(buf)) != -1) {
					  out.write(buf, 0, length);
				}
				out.flush();
		    } catch (Exception e) {
				e.printStackTrace();
		    } finally {
				try {
					  if (in != null) in.close();
					  if (out != null) out.close();
				} catch (IOException e1) {
					  e1.printStackTrace();
				}
		    }
	  }


	  /**
	   * 关闭所有的操作，数据库开启后，使用完毕要关闭
	   */
	  public void closeConnection() {
		    closeHelper();
		    closeDaoSession();
		    Log.i(TAG, "-------->数据库关闭操作<---------");
	  }


	  public void closeDaoSession() {
		    if (mDaoSession != null) {
				mDaoSession.clear();
				mDaoSession = null;
				Log.i(TAG, "-------->关闭DaoSession<--------");
		    }
	  }


	  public void closeHelper() {
		    if (mDevOpenHelper != null) {
				mDevOpenHelper.close();
				mDevOpenHelper = null;
				Log.i(TAG, "--------->关闭Helper<---------");
		    }
	  }


}
