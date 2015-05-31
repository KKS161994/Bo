package in.silive.bo;

import android.app.Application;

import com.google.android.gms.analytics.Tracker;

import com.google.android.gms.analytics.GoogleAnalytics;


//import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Logger;
//import com.google.analytics.tracking.android.Tracker;

import java.util.HashMap;

import static com.google.analytics.tracking.android.Logger.*;
import static com.google.analytics.tracking.android.Logger.LogLevel.*;

/**
 * Created by ksrivastava on 5/7/15.
 */
public class BytepadApplication extends Application {
      // The following line should be changed to include the correct property id.
        private static final String PROPERTY_ID = "UA-62659989-1"; // My Property id.

        public static int GENERAL_TRACKER = 0;

        public enum TrackerName {
            APP_TRACKER, // Tracker used only in this app.
            GLOBAL_TRACKER, // Tracker used by all the apps from a company. eg: roll-up tracking.
            ECOMMERCE_TRACKER, // Tracker used by all ecommerce transactions from a company.
        }

        HashMap<TrackerName, Tracker> mTrackers = new HashMap<TrackerName, Tracker>();

    public synchronized Tracker getTracker(TrackerName appTracker) {
                if (!mTrackers.containsKey(appTracker)) {
                   GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
         	            Tracker t = (appTracker == TrackerName.APP_TRACKER) ? analytics.newTracker(PROPERTY_ID) : (appTracker == TrackerName.GLOBAL_TRACKER) ? analytics.newTracker(R.xml.global_tracker) : analytics.newTracker(R.xml.global_tracker);
         	            mTrackers.put(appTracker, t);
         	        }
        	        return mTrackers.get(appTracker);
        	    }
    }