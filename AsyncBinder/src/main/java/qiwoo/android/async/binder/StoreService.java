package qiwoo.android.async.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class StoreService extends Service {

    private List<Store> stores;

    @Override
    public void onCreate() {
        super.onCreate();

        Store store1 = new Store(1, "qiwoo", "123", "beijing");
        Store store2 = new Store(2, "mobile", "123", "beijing");

        stores = new ArrayList<>();
        stores.add(store1);
        stores.add(store2);
    }

    IStoreService.Stub mStoreService = new IStoreService.Stub() {

        @Override
        public List<Store> getStores() throws RemoteException {
            return stores;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStoreService;
    }
}
