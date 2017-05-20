package co.joshuayuan.feedtester;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Entry implements Parcelable {
    public Bitmap picture;
    public String userName;
    public String price;
    public String description;
    public Entry(){
        super();
    }

    public Entry(Bitmap picture, String userName, String price, String description) {
        super();
        this.picture = picture;
        this.userName = userName;
        this.price = price;
        this.description = description;
    }
    public Entry(Parcel in){
        this.userName = in.readString();
        this.picture = in.readParcelable(getClass().getClassLoader());
        this.price = in.readString();
        this.description = in.readString();
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeParcelable(picture, i);
        parcel.writeString(price);
        parcel.writeString(description);
    }
    public static final Parcelable.Creator<Entry> CREATOR
            = new Parcelable.Creator<Entry>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
}