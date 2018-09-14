package qiwoo.android.sync.binder;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static qiwoo.android.sync.binder.ui.MainActivity.SERVICE_ORDER;
import static qiwoo.android.sync.binder.ui.MainActivity.SERVICE_STORE;


public class BinderProvider extends ContentProvider {

    public static final String TAG = BinderProvider.class.getSimpleName();

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        IBinder binder;

        if (selectionArgs[0].equals(SERVICE_ORDER)) {
            binder = new OrderServiceImpl();

            Log.d(TAG, "Query OrderServiceImpl");

        } else if (selectionArgs[0].equals(SERVICE_STORE)) {
            binder = new StoreServiceImpl();

            Log.d(TAG, "Query StoreServiceImpl");

        } else {
            return null;
        }

        BinderCursor cursor = new BinderCursor(new String[]{"service"}, binder);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        return 0;
    }
}
