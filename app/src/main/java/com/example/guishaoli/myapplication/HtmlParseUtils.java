package com.example.guishaoli.myapplication;

import android.provider.DocumentsContract;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String pattern = "<input type=\"hidden\" name=\"token\" value=\"(\\w+)\">";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(html);
        if (m.find()) {
            return m.group(1);
        } else {
            return "error";
        }
    }

    public static String getComponent(String str) {
        String pattern = "<select\\s+name..component.\\s+id..component.\\s+size..7.\\s+onchange..set_assign_to....>\\s+<option\\s+value..(\\w+)\"\\s+id";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            return m.group(1);
        } else {
            return "error";
        }
    }

    public static ArrayList<String> getVersionList(String html){
        Document doc = Jsoup.parse(html);
        Element select = doc.getElementById("version");
        Elements options = select.children();
        ArrayList<String> arr = new ArrayList<String>();
        for(Element each:options){
            arr.add(each.text());
        }
        return arr;
    }

    public static String getProduct(String html){
        String title = getTitle(html);
        return title.split(" ")[2];
    }



}
