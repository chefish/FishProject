package com.fish.http;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.ui.activity.BaseFishActivity;
import com.fish.lib.fishlib.util.system.ToastUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * get 百度官网
 * 得到html代码，然后显示出来
 * Created by fish on 15/9/30.
 */
public class GetActivity extends BaseFishActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_activity);
        setButtonClick(R.id.btn_get,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpClient();
            }
        });
    }

    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
//                    String url="https://www.baidu.com/";
                    String url="http://www.baidu.com/";
                    HttpGet httpGet = new HttpGet(url);
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        // 请求和响应都成功了
                        HttpEntity entity = httpResponse.getEntity();

                        final String response = EntityUtils.toString(entity, "utf-8");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((TextView)findViewById(R.id.content)).setText(response);
                            }
                        });
                        // 将服务器返回的结果存放到Message中 message.obj = response.toString(); handler.sendMessage(message);
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // 我在coolpad9970上运行会出现java.net.UnknownHostException: Unable to resolve host "www.baidu.com": No address associated with hostname
                            //坑爹啊，其他手机上没问题，分析原因，应该是网络太弱造成的，连的是yixin的wifi，连netease就没问题了
                            LogUtil.fish(e.toString());
                            ToastUtils.showToast(GetActivity.this, e.toString());
                        }
                    });

                }
            }
        }).start();


    }
}
