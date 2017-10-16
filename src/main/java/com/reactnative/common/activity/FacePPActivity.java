package com.reactnative.common.activity;

import android.content.Intent;

import com.facebook.infer.annotation.Functional;
import com.facebook.react.ReactActivity;
import com.facebook.react.modules.core.PermissionListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;


/**
 * Created by ronnieren on 8/30/17.
 */

public class ReactActivityWithHandler extends ReactActivity {
    private ArrayList<ActivityHandler> activityHandlers = new ArrayList<ActivityHandler>();

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //TODO remove duplicated code with closure or function-like
        ArrayList<ActivityHandler> handlers = null;
        synchronized (this){
            handlers = new ArrayList<ActivityHandler>(activityHandlers);
        }

        if(handlers != null){
            for(int i = 0; i < handlers.size(); i++){
                ActivityHandler aHandler = handlers.get(i);
                aHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    }

    public void unRegisterActivityHandler(ActivityHandler handler) {
        synchronized (this){
            activityHandlers.remove(handler);
        }
    }

    public void registerActivityHandler(ActivityHandler handler) {
        synchronized (this){
            if(!activityHandlers.contains(handler)) {
                activityHandlers.add(handler);
            }
        }
    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<ActivityHandler> handlers = null;
        synchronized (this){
            handlers = new ArrayList<ActivityHandler>(activityHandlers);
        }

        if(handlers != null){
            for(int i = 0; i < handlers.size(); i++){
                ActivityHandler aHandler = handlers.get(i);
                aHandler.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


}

