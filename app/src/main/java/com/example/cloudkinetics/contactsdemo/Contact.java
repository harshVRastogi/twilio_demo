package com.example.cloudkinetics.contactsdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cloud kinetics on 1/28/2017.
 */
public class Contact implements Parcelable {
    private int contactId;
    private String contactName;
    private String contactNumber;

    protected Contact(Parcel in) {
        contactId = in.readInt();
        contactName = in.readString();
        contactNumber = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public Contact() {

    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(contactId);
        dest.writeString(contactName);
        dest.writeString(contactNumber);
    }
}
