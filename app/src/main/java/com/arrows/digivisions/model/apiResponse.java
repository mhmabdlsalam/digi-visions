package com.arrows.digivisions.model;

import java.util.ArrayList;

public class apiResponse {


    public String _type;
    public int totalCount;
    public ArrayList<Value> value;


    public class Value{
        public String url;
        public int height;
        public int width;
        public String thumbnail;
        public int thumbnailHeight;
        public int thumbnailWidth;
        public Object base64Encoding;
        public String name;
        public String title;
        public Provider provider;
        public String imageWebSearchUrl;
        public String webpageUrl;


        public String getUrl() {
            return url;
        }

        public String getTitle() {
            return title;
        }
    }


    public class Provider{
        public String name;
        public String favIcon;
        public String favIconBase64Encoding;
    }


    public ArrayList<Value> getValue() {
        return value;
    }
}