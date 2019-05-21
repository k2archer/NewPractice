package pk.wei.com.newpractice.testdemo;


import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class ParcelableEx implements Parcelable {

    private static final String TAG = "ParcelableEx";

    public int userId;
    public String userName;
    public boolean isMale;

    public ParcelableEx(int id, String name, boolean isMale) {
        userId = id;
        userName = name;
        this.isMale = isMale;
    }

    public void show() {
        Log.i(TAG, "show: id " + userId + " name " + userName);
    }
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flag) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale ? 1 : 0);

    }

    public ParcelableEx(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1;

    }

    public static final Parcelable.Creator<ParcelableEx> CREATOR = new Parcelable.Creator<ParcelableEx>() {
        public ParcelableEx createFromParcel(Parcel in) {
            return new ParcelableEx(in);
        }

        public ParcelableEx[] newArray(int size) {
            return new ParcelableEx[size];
        }
    };




}
