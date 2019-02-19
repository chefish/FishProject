// IMyAidlInterface.aidl
package com.aidl.server;
import com.example.fish.aidlserver1.ICallback;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
	int getCount();
	double complexCal(String str,int t);
	void setCallback(ICallback call);
}