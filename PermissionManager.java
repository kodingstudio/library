package com.haris.bluetoothspeaker;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.core.app.ActivityCompat;

/*
==========
How to use
==========

      permissionManager = new PermissionManager.Builder(this)
                  .withPermission(Manifest.permission.BLUETOOTH_ADMIN)
                  .withPermission(Manifest.permission.BLUETOOTH)
                  .withRequestCode(REQUEST_CODE_FOR_PERMISSION)
                  .build();

          // request permission
          permissionManager.requestPermissions();

=======================
Request Permission Code
=======================

      @Override
      public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
          super.onRequestPermissionsResult(requestCode, permissions, grantResults);

          if (requestCode == REQUEST_CODE_FOR_PERMISSION) {
              for (int i = 0; i < permissions.length; i++) {
                  if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                      Toast.makeText(getApplicationContext(), " kindly accept the permission" + permissions[i], Toast.LENGTH_LONG).show();
                  }
              }
          }
      }

*/

public class PermissionManager {

    private final Activity activity;
    private final List<String> permissions;
    private final int requestCode;

    private PermissionManager(Builder builder) {
        this.activity = builder.activity;
        this.permissions = builder.permissions;
        this.requestCode = builder.requestCode;
    }

    public void requestPermissions() {

        if (permissions.isEmpty()) {
            try {
                // Triggering an exception
                throw new Exception("Kindly add the requested permission");
            } catch (Exception e) {
                // Catching the exception and printing the stack trace
                e.printStackTrace();
            }
            return;
        }

        List<String> pr = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
                    && (permissions.get(i).equals(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    || permissions.get(i).equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)))
                continue;

            pr.add(permissions.get(i));

        }

        String[] permissionsArray = pr.toArray(new String[0]);
        ActivityCompat.requestPermissions(activity, permissionsArray, requestCode);
    }

    public static class Builder {
        private final Activity activity;
        private final List<String> permissions;
        private int requestCode = 0;

        public Builder(Activity activity) {
            this.activity = activity;
            this.permissions = new ArrayList<>();
        }

        public Builder withPermission(String permission) {
            permissions.add(permission);
            return this;
        }

        public Builder withPermissions(List<String> permissions) {
            this.permissions.addAll(permissions);
            return this;
        }

        public Builder withRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public PermissionManager build() {
            return new PermissionManager(this);
        }
    }

}
