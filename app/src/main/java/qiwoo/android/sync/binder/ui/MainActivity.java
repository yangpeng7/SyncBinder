package qiwoo.android.sync.binder.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import qiwoo.android.sync.binder.BinderCursor;
import qiwoo.android.sync.binder.IOrderService;
import qiwoo.android.sync.binder.IStoreService;
import qiwoo.android.sync.binder.R;
import qiwoo.android.sync.binder.Store;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getName();

    public static final String AUTHORITY = "qiwoo.android.sync.binder.service";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/binder");

    public static final String SERVICE_ORDER = "order";
    public static final String SERVICE_STORE = "store";


    private Button mOrderButton;
    private Button mStoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mOrderButton = findViewById(R.id.order);
        mStoreButton = findViewById(R.id.store);

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ContentResolver resolver = MainActivity.this.getContentResolver();

                final Cursor cu = resolver.query(CONTENT_URI, null, null, new String[]{SERVICE_ORDER}, null);
                if (cu == null) {
                    return;
                }

                IBinder binder = getBinder(cu);
                try {
                    IOrderService orderService = IOrderService.Stub.asInterface(binder);
                    int amount = orderService.getOrderAmount();

                    Toast.makeText(MainActivity.this, "恭喜老板营业额是：" + amount + " 亿", Toast.LENGTH_SHORT).show();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                cu.close();
            }
        });

        mStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ContentResolver resolver = MainActivity.this.getContentResolver();

                final Cursor cu = resolver.query(CONTENT_URI, null, null, new String[]{SERVICE_STORE}, null);
                if (cu == null) {
                    return;
                }

                IBinder binder = getBinder(cu);
                try {
                    IStoreService storeService = IStoreService.Stub.asInterface(binder);
                    List stores = storeService.getStores();

                    Toast.makeText(MainActivity.this, "报告老板我们现在有：" + stores.size() + " 家超市", Toast.LENGTH_SHORT).show();

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                cu.close();
            }
        });
    }


    public static final IBinder getBinder(Cursor cursor) {
        Bundle extras = cursor.getExtras();
        extras.setClassLoader(BinderCursor.BinderParcelable.class.getClassLoader());
        BinderCursor.BinderParcelable w = extras.getParcelable("binder");
        return w.mBinder;
    }
}
