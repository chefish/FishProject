package com.fish.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fish.lib.fishlib.log.LogUtil;
import com.fish.lib.fishlib.ui.activity.BaseFishActivity;
import com.fish.lib.fishlib.util.system.ToastUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *用post方法获取高德静态图
 * Created by fish on 15/9/30.
 */
public class PostActivity extends BaseFishActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_activity);

        setButtonClick(R.id.btn_post, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpClientPost();
            }
        });
    }


    /**
     * 获取高德地图的图片
     */
    private void sendRequestWithHttpClientPost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    //get可以获取到，post不行
//                    HttpGet httpGet = new HttpGet("http://restapi.amap.com/v3/staticmap?location=116.481485,39.990464&zoom=10&size=750*300&key=480c5f26c3de52d9ace89f96baa7f868");
                    HttpPost httpPost = new HttpPost("http://restapi.amap.com/v3/staticmap");

                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("location", "116.481485,39.990464"));
                    params.add(new BasicNameValuePair("zoom", "10"));
                    params.add(new BasicNameValuePair("size", "750*300"));

                    //这是我自己在高德申请的key
                    params.add(new BasicNameValuePair("key", "480c5f26c3de52d9ace89f96baa7f868"));


                    UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(params, "utf-8");
                    httpPost.setEntity(entity1);


                    HttpResponse httpResponse = httpClient.execute(httpPost);


                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
                        // 请求和响应都成功了
                        HttpEntity entity = httpResponse.getEntity();
                        byte[] data = EntityUtils.toByteArray(entity);
                        final Bitmap bitmap = BitmapFactory
                                .decodeByteArray(data, 0, data.length);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((ImageView) findViewById(R.id.iamgeview)).setImageBitmap(bitmap);


                            }
                        });


//                        final String response = EntityUtils.toString(entity, "utf-8");
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                textView.setText(response);
//                            }
//                        });
                    }
                } catch (final Exception e) {
                    LogUtil.fish(e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast(PostActivity.this,e.toString());
                        }
                    });
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
