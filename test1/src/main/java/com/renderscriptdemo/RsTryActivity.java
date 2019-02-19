package com.renderscriptdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v8.renderscript.RenderScript;

import com.example.fish.entrance.R;
import com.rs.ScriptC_helloworld;

/**
 * 代码来自android应用性能优化，P201
 * 会在日志中输出标签为RenderScript的日志int hello world I am RenderScript 0  0x0
 */
public class RsTryActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.render_script_activity);
        HelloWorldRenderScript();
    }

    private void HelloWorldRenderScript() {
        RenderScript rs= RenderScript.create(this);
        ScriptC_helloworld heloworldScript=new ScriptC_helloworld(rs,getResources(),R.raw.helloworld);
        heloworldScript.invoke_hello_world();
    }


}
