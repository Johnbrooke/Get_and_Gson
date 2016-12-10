package com.example.administrator.mydemo_onepic_2;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.mydemo_onepic_2.entity.TestAD;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity implements View.OnClickListener {


    OkHttpClient mokHttpClient;
    private Button bt_send;
    private Button bt_postsend;
    private Button bt_sendfile;
    private Button bt_downfile;
    private TextView textView,textView1;


//    public static final MediaType MEDIA_TYPE_MARKDOWN
//            = MediaType.parse("text/x-markdown; charset=utf-8");
//    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initOkHttpClient();
        bt_send = (Button) this.findViewById(R.id.bt_send);

        textView = (TextView) findViewById(R.id.tv_item);
        textView1 = (TextView) findViewById(R.id.tv_item2);
        bt_send.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_send:
                getAsynHttp();
                break;
        }
    }

    private void initOkHttpClient() {
        File sdcache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize));
        mokHttpClient = builder.build();
    }

    /**
     * get异步请求
     */
    private void getAsynHttp() {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url("http://api.xfc639.com:9010/legend/product_list_all.json?company_type=3")
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String respJson = response.body().string();
                    Gson gson = new Gson();
                    TestAD productListBean = gson.fromJson(respJson,TestAD.class);
                    Log.i("TAG", "--------------" + respJson);
                    updateuis(productListBean);


                }
            }
        });
    }
    public void updateuis(final TestAD resp){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.append(resp.getProductList().get(0).getId()+"");
                textView.append(resp.getProductList().get(0).getName());
                textView1.append(resp.getProductList().get(1).getId()+"");
                textView1.append(resp.getProductList().get(1).getName());

            }
        });

    }


}
