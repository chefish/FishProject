package com.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fish on 16/3/18.
 */

public class BaseData implements Parcelable {
    private String name;
    private String payType;
    private int payMoney;


    public BaseData(String name, String payType, int payMoney) {
        this.name=name;
        this.payType = payType;
        this.payMoney = payMoney;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public int getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
		/*将PayClass的成员写入Parcel，
         * 注：Parcel中的数据是按顺序写入和读取的，即先被写入的就会先被读取出来
         */
        dest.writeString(name);
        dest.writeString(payType);
        dest.writeInt(payMoney);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        payType = in.readString();
        payMoney = in.readInt();
    }
    //该静态域是必须要有的，而且名字必须是CREATOR，否则会出错
    public static final Creator<BaseData> CREATOR=new Creator<BaseData>() {

        @Override
        public BaseData[] newArray(int size) {
            return new BaseData[size];
        }

        @Override
        public BaseData createFromParcel(Parcel source) {
            //从Parcel读取通过writeToParcel方法写入的PayClass的相关信息
            String n=source.readString();
            String pt=source.readString();
            int pm=source.readInt();
            return new BaseData(n,pt, pm);
        }
    };

}
