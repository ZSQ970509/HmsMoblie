package com.hc.hmsmoblie.utils;

import android.util.Log;

import com.hc.hmsmoblie.bean.domain.VideoBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * Created by Administrator on 2018/6/21.
 */

public class VideoBeanXmlUtil {
    public static VideoBean parseXMLWithPull(String xmlData) throws Exception {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(xmlData));
        int eventType = parser.getEventType();
        VideoBean dataBean = new VideoBean();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String nodeName = parser.getName();
            switch (eventType) {
                // 开始解析某个结点
                case XmlPullParser.START_TAG: {
                    if ("type".equals(nodeName)) {
                        dataBean.setmType(parser.nextText());
                    } else if ("ip".equals(nodeName)) {
                        dataBean.setmIp(parser.nextText());
                    } else if ("port".equals(nodeName)) {
                        dataBean.setmPort(parser.nextText());
                    } else if ("account".equals(nodeName)) {
                        dataBean.setmUserName(parser.nextText());
                    } else if ("password".equals(nodeName)) {
                        dataBean.setmPassword(parser.nextText());
                    } else if ("naming".equals(nodeName)) {
                        dataBean.setmSysCode(parser.nextText());
                    } else if ("camId".equals(nodeName)) {
                        dataBean.setCamId(parser.nextText());
                    } else if ("camName".equals(nodeName)) {
                        dataBean.setCamName(parser.nextText());
                    } else if ("hikxml".equals(nodeName)) {
                        dataBean.setmRtsp(parser.nextText());
                    }
                    break;
                }
                // 完成解析某个结点
                case XmlPullParser.END_TAG: {
                    if ("cam".equals(nodeName)) {
                        Log.e("xml解析", dataBean.toString());
                    }
                    break;
                }
                default:
                    break;
            }
            eventType = parser.next();
        }
        return dataBean;
    }
}
