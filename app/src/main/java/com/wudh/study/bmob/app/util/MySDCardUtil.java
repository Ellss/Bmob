package com.wudh.study.bmob.app.util;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MySDCardUtil {
    private static MySDCardUtil instance = null;
    private Context context = null;
    StorageManager sm = null;
    private final static int MOUNT_INTERNAL = 0;
    private final static int MOUNT_EXTERNAL = 1;

    public static MySDCardUtil getInstance() {
        if (instance == null) {
            instance = new MySDCardUtil();
        }
        return instance;
    }

    @SuppressLint("InlinedApi")
    public void init(Context _context) {
        context = _context;
        sm = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
    }

    /**
     * 扩展卡获取
     *
     * @return
     */
    @SuppressLint("NewApi")
    private String getSDCatrdPath() {

        //try{
        Class<?>[] paramClasses = {};
        Method getVolumePathsMethod;
        try {
            getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths", paramClasses);
            getVolumePathsMethod.setAccessible(true);
            Object[] params = {};
            String ownsdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
            Object invoke = getVolumePathsMethod.invoke(sm, params);
            for (int i = 0; i < ((String[]) invoke).length; i++) {
                String path = ((String[]) invoke)[i];
                if (sdcardMounted(path) && !ownsdcard.equals(path)) {
                    return path;
                }
            }
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取扩展卡异常" + e.getMessage());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取扩展卡异常" + e.getMessage());
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取扩展卡异常" + e.getMessage());
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取扩展卡异常" + e.getMessage());
        }


        return "";
    }

    /**
     * 内置卡获取
     *
     * @return
     */
    @SuppressLint("NewApi")
    private String getStoragePath() {
        try {
            Class<?>[] paramClasses = {};
            Method getVolumePathsMethod = StorageManager.class.getMethod("getVolumePaths", paramClasses);
            getVolumePathsMethod.setAccessible(true);
            Object[] params = {};
            String ownsdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
            Object invoke = getVolumePathsMethod.invoke(sm, params);
            for (int i = 0; i < ((String[]) invoke).length; i++) {
                String path = ((String[]) invoke)[i];
                if (sdcardMounted(path) && ownsdcard.equals(path)) {
                    return path;
                }

            }
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取内置sd卡异常" + e.getMessage());
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取内置sd卡异常" + e.getMessage());
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取内置sd卡异常" + e.getMessage());
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            Log.e("MySDCardUtil", "获取内置sd卡异常" + e.getMessage());
        } catch (Exception e) {
            Log.e("MySDCardUtil",  e.getMessage());
        }
        return "";
    }

    @SuppressLint("NewApi")
    public String getPath(int mount_type) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (mount_type == MOUNT_INTERNAL) {
                return Environment.getRootDirectory().getAbsolutePath();
            } else if (mount_type == MOUNT_EXTERNAL) {
                return Environment.getExternalStorageDirectory().getAbsolutePath();
            }
        } else {

            if (mount_type == MOUNT_INTERNAL) {
                return getStoragePath();
            } else if (mount_type == MOUNT_EXTERNAL) {
                return getSDCatrdPath();
            }
        }
        return "";
    }

    @SuppressLint("NewApi")
    public boolean sdcardMounted(String mountPoint) {
        try {

            Class<?>[] paramClassesStatus = {String.class};
            Method getVolumePathsStatus = StorageManager.class.getMethod("getVolumeState", paramClassesStatus);
            getVolumePathsStatus.setAccessible(true);
            Object result = getVolumePathsStatus.invoke(sm, mountPoint);
            if (null != result) {
                if (result.toString().equals(Environment.MEDIA_MOUNTED)) {
                    return true;
                }

            }
        } catch (Exception e) {
            Log.e("MySDCardUtil", "获取内置sd卡异常");
        }
        return false;
    }

    /**
     * 获取内置卡对外接口
     *
     * @return
     */
    public String getInternalInfo() {
        return getPath(MOUNT_INTERNAL);
    }

    /**
     * 获取扩展卡对外接口
     *
     * @return
     */
    public String getExternalInfo() {
        return getPath(MOUNT_EXTERNAL);
    }

    public String[] getStorageSize(String path) {
        StatFs stat = new StatFs(path);
        //文件系统的块的大小（byte）
        long blockSize = stat.getBlockSize();
        //文件系统的总的块数
        long totalBlocks = stat.getBlockCount();
        //文件系统上空闲的可用于程序的存储块数
        long availableBlocks = stat.getAvailableBlocks();

        //总的容量
        double totalSize = ((double) (blockSize * totalBlocks)) / ((double) (1024 * 1024 * 1024));
        double availableSize = ((double) (blockSize * availableBlocks)) / ((double) (1024 * 1024 * 1024));
        DecimalFormat df = new DecimalFormat("#.####");

        return new String[]{df.format(totalSize), df.format(availableSize)};

    }

    public String getDeviceID(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String key = tm.getDeviceId();
        if (key == null) {
            key = android.os.Build.SERIAL;
            if (key == null) {
                key = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            }
        }
        return null != key ? key : System.currentTimeMillis() + "";
    }


    /***
     *通过反射调用获取内置存储和外置sd卡根路径(通用)
     * @param mContext    上下文
     * @param is_removale 是否可移除，false返回内部存储，true返回外置sd卡
     * @return
     * */
    public String getStoragePath(Context mContext, boolean is_removale) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removale == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

