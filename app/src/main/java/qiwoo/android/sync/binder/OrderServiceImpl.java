package qiwoo.android.sync.binder;

import android.os.RemoteException;

public class OrderServiceImpl extends IOrderService.Stub {

    @Override
    public int getOrderAmount() throws RemoteException {
        return 100;
    }
}
