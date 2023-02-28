package com.zwd.searches;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.zwd.searches.bean.HotSearch;
import com.zwd.searches.utils.HttpUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HotApp {
    private static final String url = "http://zhibo.sina.com.cn/api/zhibo/feed?zhibo_id=152&id=&tag_id=0&page=1&page_size=30&type=0";

    public static void main(String[] args) {
        try {
            String response = HttpUtils.httpGet(url);
            String lists = JSON.parseObject(response).getJSONObject("result").getJSONObject("data").getJSONObject("feed").getString("list");
            List<HotSearch> researches = JSONArray.parseArray(lists, HotSearch.class);

            String collect = researches.stream().map(HotSearch::toString).collect(Collectors.joining());

            String html = new String(Files.readAllBytes(Paths.get("news.html")), "utf-8");
            html = html.replace("${content}", collect);

            Path indexPath = Paths.get("index.html");
            Files.deleteIfExists(indexPath);
            Files.createFile(indexPath);
            Files.write(indexPath, html.getBytes("utf-8"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取接口出错");
        }
    }
}
