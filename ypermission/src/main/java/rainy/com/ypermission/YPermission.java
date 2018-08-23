package rainy.com.ypermission;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * time:2018-08-22 16:47
 * description:
 *
 * @author yueleilei
 */
public class YPermission {
    private static YPermission sInstance;
    private Application mContext;
    private String[] mPermissions;
    private static final String PERMISSION_KEY = "permission_key";
    private OnPermissionResult onPermissionResult;

    private YPermission(Context context) {
        this.mContext = (Application) context.getApplicationContext();
    }

    public static YPermission with(Context context) {
        if (sInstance == null) {
            synchronized (YPermission.class) {
                if (sInstance == null) {
                    sInstance = new YPermission(context);
                }
            }
        }
        return sInstance;
    }

    public YPermission request(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    public void start(OnPermissionResult onPermissionResult) {
        this.onPermissionResult = onPermissionResult;
        Intent intent = new Intent(mContext, YPermissionActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSION_KEY, mPermissions);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public void processPermissionResult(boolean granted) {
        if (onPermissionResult != null) {
            onPermissionResult.onResult(granted);
        }
    }
}
