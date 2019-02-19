// IMyAidlInterface.aidl
package com.aidl.server;
import com.example.fish.aidlserver1.ICallback;
import com.data.BaseData;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
	int getCount();
	double complexCal(String str,int t);
	int putRemote(in BaseData data,int t);
	void setCallback(ICallback call);
}