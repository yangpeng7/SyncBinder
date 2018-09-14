package qiwoo.android.async.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class OrderService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    IOrderService.Stub mOrderService = new IOrderService.Stub() {
        @Override
        public int getOrderAmount() throws RemoteException {
            return 100;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mOrderService;
    }
}
