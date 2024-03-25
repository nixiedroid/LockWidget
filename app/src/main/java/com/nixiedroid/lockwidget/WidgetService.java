package com.nixiedroid.lockwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetService extends AppWidgetProvider {
    private static volatile long lastUpdateTime;

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int[] nArray, int n2) {
        RemoteViews remoteViews =
                new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra("appWidgetIds", nArray);
        remoteViews.setOnClickPendingIntent(R.id.widget,
                PendingIntent.getBroadcast(context, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE));
        appWidgetManager.updateAppWidget(n2, remoteViews);
    }

    private boolean updateAppWidget(Context context) {
        long time = System.currentTimeMillis();
        if (time - lastUpdateTime <= 300L) {
            if (LockService.isPermissionAllowed(context)) {
                LockService.lockScreenUsingAccessibility();
            } else {
                context.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS")
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
            return true;
        }
        lastUpdateTime = time;
        return false;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] widgetIds) {
        for (int widgetId : widgetIds) {
            if (this.updateAppWidget(context)) continue;
            this.updateAppWidget(context, appWidgetManager, widgetIds, widgetId);
        }
    }
}

