package com.example.guishaoli.myapplication;

import android.provider.DocumentsContract;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

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

    public static ArrayList<String> getBugItems(String html){
        Document doc = Jsoup.parse(html);
        Element bugzillabody = doc.getElementById("bugzilla-body");
        Elements items = bugzillabody.getElementsByTag("a");

        ArrayList<String> bugs = new ArrayList<>();

        for(Element item:items){
            String linkHref = item.attr("href");
            String linkText = item.text();
            bugs.add(linkText);
        }
        return bugs;
    }

    public static String getToken(String html){
        Document doc = Jsoup.parse(html);
        return "nothing";
    }

}
