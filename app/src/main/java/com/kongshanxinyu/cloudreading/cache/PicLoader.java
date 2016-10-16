package com.kongshanxinyu.cloudreading.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.LruCache;

import com.kongshanxinyu.cloudreading.R;
import com.kongshanxinyu.cloudreading.util.ContextUtil;
import com.kongshanxinyu.cloudreading.util.ImgUtil;
import com.kongshanxinyu.cloudreading.util.ScreenUtil;
import com.kongshanxinyu.cloudreading.util.SysUtil;

/**
 * Created by Steve on 16/10/8.
 * E-mail: singleframe@aliyun.com
 */
public class PicLoader {

    private final String DEFAULT_IMG_URI="default";
    private LruCache<String,Bitmap> mThumbnailPicLru;
    private LruCache<String,Bitmap> mGaussianPicLru;
    private LruCache<String,Bitmap> mCDCirclePicLru;
    private int cacheMemory;

    private PicLoader(){
        cacheMemory= (int) (SysUtil.getMaxMemory()/1024/8);

        mThumbnailPicLru=new LruCache<String, Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };

        mGaussianPicLru=new LruCache<String, Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };

        mCDCirclePicLru=new LruCache<String, Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount()/1024;
            }
        };
    }

    public static PicLoader getInstance(){
        return SingletonHolder.picLoader;
    }

    private static class SingletonHolder{
        public static PicLoader picLoader=new PicLoader();
    }

    public Bitmap loadThumbnailPic(String uri){
        Bitmap bitmap=null;
        if (TextUtils.isEmpty(uri)){
            bitmap=mThumbnailPicLru.get(DEFAULT_IMG_URI);
            if (bitmap==null) {
                bitmap=BitmapFactory.decodeResource(ContextUtil.getAppContext().getResources(),R.mipmap.default_artist);
                mThumbnailPicLru.put(DEFAULT_IMG_URI,bitmap);
            }
        }else {
            bitmap=mThumbnailPicLru.get(uri);
            if (bitmap==null){
                bitmap=loadBitmap(uri,ScreenUtil.getScreenWidth()/10);
                if (bitmap==null){
                    loadThumbnailPic(null);
                }
                mThumbnailPicLru.put(uri,bitmap);
            }
        }
        return bitmap;
    }

    public Bitmap loadGaussianPic(String uri){
        Bitmap bitmap=null;
        if (TextUtils.isEmpty(uri)){
            bitmap=mGaussianPicLru.get(uri);
            if (bitmap==null){
                bitmap=BitmapFactory.decodeResource(ContextUtil.getAppContext().getResources(),R.mipmap.play_page_default_bg);
                mGaussianPicLru.put(DEFAULT_IMG_URI,bitmap);
            }
        }else {
            bitmap=mGaussianPicLru.get(uri);
            if (bitmap==null){
                bitmap=loadBitmap(uri,ScreenUtil.getScreenWidth());
                if (bitmap==null){
                    loadGaussianPic(null);
                }else {
                    ImgUtil.gaussianBlur(bitmap,50);
                }
                mGaussianPicLru.put(uri,bitmap);
            }
        }
        return bitmap;
    }

    public Bitmap loadCDCirclePic(String uri){
        Bitmap bitmap=null;
        if (TextUtils.isEmpty(uri)){
            bitmap=mCDCirclePicLru.get(DEFAULT_IMG_URI);
            if (bitmap==null){
                bitmap=BitmapFactory.decodeResource(ContextUtil.getAppContext().getResources(),R.mipmap.play_page_default_cover);
                ImgUtil.scaleImage(bitmap,530,530);
                ImgUtil.cropCircleImage(bitmap);
            }
        }else {
            bitmap=mCDCirclePicLru.get(uri);
            if (bitmap==null){
                loadBitmap(uri,530);
                if (bitmap==null){
                    loadCDCirclePic(null);
                }else {
                    ImgUtil.scaleImage(bitmap,530,530);
                    ImgUtil.cropCircleImage(bitmap);
                }
                mCDCirclePicLru.put(uri,bitmap);
            }
        }
        return bitmap;
    }

    public Bitmap loadBitmap(String uri,int len){
        Bitmap bitmap=null;
        if (TextUtils.isEmpty(uri)){
            bitmap=BitmapFactory.decodeResource(ContextUtil.getAppContext().getResources(), R.mipmap.default_cover);
        }else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(uri,options);
            int sampleSize=(options.outHeight>options.outWidth?options.outHeight:options.outWidth)/len;
            options.inSampleSize=sampleSize<1?1:sampleSize;
            options.inJustDecodeBounds=false;
            bitmap=BitmapFactory.decodeFile(uri,options);
        }
        return bitmap;
    }


}
