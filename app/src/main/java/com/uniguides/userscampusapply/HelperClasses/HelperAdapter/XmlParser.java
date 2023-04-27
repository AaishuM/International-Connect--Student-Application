package com.uniguides.userscampusapply.HelperClasses.HelperAdapter;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class XmlParser {
    public static final String TAG = "XmlParser";
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    public static ArrayList<ProgressStatus> parse(String xml)
    {
        ArrayList<ProgressStatus> progressList = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xml));
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("status")) {
                        ProgressStatus progress = new ProgressStatus();
                        progress.setDate(parseDate(parser.nextText()));
                        progress.setMessage(parser.nextText().trim());
                        progressList.add(progress);
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            Log.e(TAG, "Error parsing XML", e);
        }
        return progressList;
    }

    private static Date parseDate(String dateString) {


        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault());
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            Log.e(TAG, "Error parsing date", e);
        }
        return null;
    }

}
