package com.reactnative.common.activity;


import android.content.Intent;

/**
 * Created by ronnieren on 8/30/17.
 */

public interface ActivityHandler  {
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
}

