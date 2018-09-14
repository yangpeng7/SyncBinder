package qiwoo.android.async.binder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class BossActivity extends AppCompatActivity {

    private Button mOrderButton;
    private Button mStoreButton;

    private IStoreService mStoreService;
    private ServiceConnection mStoreServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mStoreService = IStoreService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    private IOrderService mOrderService;
    private ServiceConnection mOrderServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mOrderService = IOrderService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        initViews();

        Intent orderIntent = new Intent();
        orderIntent.setClass(this, OrderService.class);
        bindService(orderIntent, mOrderServiceConnection, Context.BIND_AUTO_CREATE);


        Intent storeIntent = new Intent();
        storeIntent.setClass(this, StoreService.class);
        bindService(storeIntent, mStoreServiceConnection, Context.BIND_AUTO_CREATE);

    }

    private void initViews() {
        mOrderButton = findViewById(R.id.order);
        mStoreButton = findViewById(R.id.store);

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    int amount = mOrderService.getOrderAmount();
                    Toast.makeText(BossActivity.this, "恭喜老板营业额是：" + amount + " 亿", Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        mStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStoreService != null) {

                    try {
                        List stores = mStoreService.getStores();
                        Toast.makeText(BossActivity.this, "报告老板我们现在有：" + stores.size() + " 家超市", Toast.LENGTH_SHORT).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
