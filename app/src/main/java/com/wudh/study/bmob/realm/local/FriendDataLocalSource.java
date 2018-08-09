package com.wudh.study.bmob.realm.local;//package com.wudh.study.mvpbmob.data.source.local;
//
//import com.wudh.study.mvpbmob.data.FriendDetailData;
//import com.wudh.study.mvpbmob.data.source.FriendDataSource;
//import com.wudh.study.mvpbmob.realm.RealmHelper;
//
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.realm.Realm;
//
///**
// * Created by wudh on 2018/7/31.
// **/
//public class FriendDataLocalSource implements FriendDataSource {
//
//    public static FriendDataLocalSource INSTANCE;
//
//    private FriendDataLocalSource() {
//    }
//
//    public static FriendDataLocalSource getInstance(){
//        if (INSTANCE==null) {
//            INSTANCE = new FriendDataLocalSource();
//        }
//        return INSTANCE;
//    }
//    @Override
//    public Observable<List<FriendDetailData>> getRemoteFriendData() {
//        return null;
//    }
//
//    @Override
//    public Observable<List<FriendDetailData>> getLocalFriendData() {
//        Realm realm= RealmHelper.newRealmInstance();
//        List<FriendDetailData> data=realm.copyFromRealm(
//                realm.where(FriendDetailData.class)
//                .findAll());
//
//        return Observable.just(data);
//    }
//}
