package com.nixiedroid.lockwidget;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.pm.ServiceInfo;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

public class LockService {
    static boolean isPermissionAllowed(Context ctx) {
        String thisPackageName = ctx.getPackageName();
        AccessibilityManager am =
                (AccessibilityManager) ctx.getSystemService(
                        Context.ACCESSIBILITY_SERVICE
                );
        List<AccessibilityServiceInfo> enabledServices =
                am.getEnabledAccessibilityServiceList(
                        AccessibilityServiceInfo.FEEDBACK_ALL_MASK
                );

        for (AccessibilityServiceInfo enabledService : enabledServices) {
            ServiceInfo esInfo = enabledService.getResolveInfo().serviceInfo;
            if (esInfo.packageName.equals(thisPackageName) ) {
                return true;
            }
        }
        return false;
    }

    static void lockScreenUsingAccessibility() {
        ActionsAccessibilityService.instance().performGlobalAction(AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN);
    }
}

