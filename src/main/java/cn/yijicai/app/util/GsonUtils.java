package cn.yijicai.app.util;

import com.google.gson.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by eshowtech on 2015/8/20.
 */

class EshowExclusionStrategy implements ExclusionStrategy{

    private Set<String> items;

    public EshowExclusionStrategy(String item){
        this.items=new HashSet<>();
        for (String s : item.split(",")) {
            this.items.add(s);
        }
    }

    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return this.items.contains(fieldAttributes.getName());
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}

public class GsonUtils{

    private static Map<String,ExclusionStrategy> map=new HashMap<>();

    private static Gson gson=null;

    public static Gson get(){
        if (gson==null){
            gson=instance();
        }
        return gson;
    }

    private static Gson instance(){
        GsonBuilder builder=new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        return builder.create();
    }

    public static Gson create(String includes){

        GsonBuilder gsonBuilder=new GsonBuilder();

        gsonBuilder.setExclusionStrategies();

        gsonBuilder.setDateFormat(DateFormat.LONG);

        gsonBuilder.disableHtmlEscaping();

        if (includes!=null){
            ExclusionStrategy es=map.get(includes);
            if (es==null){
                es=new EshowExclusionStrategy(includes);
                map.put(includes,es);
            }
            gsonBuilder.addSerializationExclusionStrategy(es);
        }

        return gsonBuilder.create();
    }

    public static Gson create(){
        return create(null);
    }
}
