package rainy.com.ypermission;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

public class YPermissionActivity extends Activity {
    private static final String PERMISSION_KEY = "permission_key";
    private static final int CODE_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = getIntent().getStringArrayExtra(PERMISSION_KEY);
        boolean hasPermission = true;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false;
                break;
            }
        }
        if (hasPermission) {
            processPermission(true);
        } else {
            ActivityCompat.requestPermissions(this, permissions, CODE_PERMISSIONS);
        }
    }

    private void processPermission(boolean granted) {
        YPermission.with(this).processPermissionResult(granted);
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CODE_PERMISSIONS:
                boolean hasPermission = true;
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        hasPermission &= grantResult == PackageManager.PERMISSION_GRANTED;
                    }
                } else {
                    hasPermission = false;
                }
                processPermission(hasPermission);
                break;
        }
    }
}
