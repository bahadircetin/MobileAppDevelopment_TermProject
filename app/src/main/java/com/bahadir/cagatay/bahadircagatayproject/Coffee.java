package com.bahadir.cagatay.bahadircagatayproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Coffee implements Parcelable{
	private int id;
	private String coffeeName;
	private String price;


	public Coffee(int id, String coffeeName, String price) {
		super();
		this.id = id;
		this.coffeeName = coffeeName;
		this.price = price;

	}
	public Coffee(String coffeeName, String price) {
		super();
		this.coffeeName = coffeeName;
		this.price = price;

	}

	public Coffee(String coffeeName) {
		super();
		this.coffeeName = coffeeName;


	}

	protected Coffee(Parcel in) {
		id = in.readInt();
		coffeeName = in.readString();
		price = in.readString();

	}
	public static final Creator<Coffee> CREATOR = new Creator<Coffee>() {
		@Override
		public Coffee createFromParcel(Parcel in) {
			return new Coffee(in);
		}
		@Override
		public Coffee [] newArray(int size) {
			return new Coffee[size];
		}
	};
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(coffeeName);
		dest.writeString(price);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public static Creator<Coffee> getCREATOR() {
		return CREATOR;
	}
}
