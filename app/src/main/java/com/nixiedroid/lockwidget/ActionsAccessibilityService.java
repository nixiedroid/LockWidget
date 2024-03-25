package com.nixiedroid.lockwidget;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class ActionsAccessibilityService
extends AccessibilityService {
    private static ActionsAccessibilityService instance;

    public static ActionsAccessibilityService instance() {
        return instance;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    @Override
    public void onInterrupt() {
    }
}

