package qiwoo.android.sync.binder;

import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

public class BinderCursor extends MatrixCursor {

    static final String KEY_BINDER = "binder";

    Bundle mBinderExtra = new Bundle();

    public static class BinderParcelable implements Parcelable {

        public IBinder mBinder;

        public static final Creator<BinderParcelable> CREATOR = new Creator<BinderParcelable>() {
            @Override
            public BinderParcelable createFromParcel(Parcel source) {
                return new BinderParcelable(source);
            }

            @Override
            public BinderParcelable[] newArray(int size) {
                return new BinderParcelable[size];
            }
        };

        BinderParcelable(IBinder binder) {
            mBinder = binder;
        }

        BinderParcelable(Parcel source) {
            mBinder = source.readStrongBinder();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStrongBinder(mBinder);
        }
    }

    public BinderCursor(String[] columnNames, IBinder binder) {
        super(columnNames);

        if (binder != null) {
            Parcelable value = new BinderParcelable(binder);
            mBinderExtra.putParcelable(KEY_BINDER, value);
        }
    }

    @Override
    public Bundle getExtras() {
        return mBinderExtra;
    }

}
