package com.example.guishaoli.myapplication;

import android.provider.DocumentsContract;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by guishaoli on 2017/8/17.
 */
public class HtmlParseUtils {
    public static String getTitle(String html){
        Document doc = Jsoup.parse(html);
        Elements titles = doc.getElementsByTag("title");
        for(Element title:titles){
            Log.d("title",title.text());
            return title.text();
        }
        return "nothing";
    }



}
