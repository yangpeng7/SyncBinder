package qiwoo.android.sync.binder;

import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class StoreServiceImpl extends IStoreService.Stub {

    private List<Store> stores;

    public StoreServiceImpl() {

        Store store1 = new Store(1, "qiwoo", "123", "beijing");
        Store store2 = new Store(2, "mobile", "123", "beijing");

        stores = new ArrayList<>();
        stores.add(store1);
        stores.add(store2);
    }

    @Override
    public List<Store> getStores() throws RemoteException {
        return stores;
    }
}
