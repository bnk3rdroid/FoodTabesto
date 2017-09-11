package yb.foodtabesto.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * POJO for the Food object.
 */
public class Food implements Parcelable{

    private int mId;
    private String mName;
    private String mPrice;
    private String mImageThumbnailUrl;

    public Food(int id, String name, String price, String imageThumbnailUrl) {
        mId = id;
        mName = name;
        mPrice = price;
        mImageThumbnailUrl = imageThumbnailUrl;
    }

    @SuppressWarnings("WeakerAccess")
    protected Food(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mPrice = in.readString();
        mImageThumbnailUrl = in.readString();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mPrice);
        parcel.writeString(mImageThumbnailUrl);
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public String getImageThumbnailUrl() {
        return mImageThumbnailUrl;
    }
}
