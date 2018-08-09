package com.wudh.study.bmob.presenter;

import android.os.Environment;
import android.util.Log;

import com.wudh.study.bmob.model.Lost;
import com.wudh.study.bmob.presenter.contract.AddLostContract;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by wudh on 2018/8/7.
 **/
public class AddLostPresenter implements AddLostContract.Presenter {

    private AddLostContract.View view;

    public AddLostPresenter(AddLostContract.View view) {
        this.view=view;
        this.view.setPresenter(this);
    }

    @Override
    public void upload() {
        if (view.isValid()){

        view.showProgressDialog("上传中...");
        final Lost lost=view.getLost();
            File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/1.jpg");
        if (file.exists()){
            Log.e("zzz", "done:"+ file.getName());
            final BmobFile bmobFile=new BmobFile(file);
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e==null){
                        Log.e("zzz", "done:"+bmobFile.getFileUrl());
                        lost.setImg(bmobFile);
                        lost.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e==null){
                                    view.showMsg("上传成功!");
                                }else {
                                    view.showMsg(e.getMessage());
                                }
                            }
                        });
                    }else {
                        view.showMsg("上传失败!");
                    }
                    view.dismissProgressDialog();
                }
            });
        }else {
            lost.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e==null){
                        view.showMsg("上传成功!");
                    }else {
                        view.showMsg(e.getMessage());
                    }
                    view.dismissProgressDialog();
                }
            });
        }
    }
    }
}
