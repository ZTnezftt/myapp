package com.example.administrator.whatiseat.Util;

import android.util.Log;

/*
获取api的工具类
 */
public class ApiUtil {
    /*
        menu 搜索的名称  例：红烧   not null
        key appkey not null
        dtupe 返回类型。默认json
        pn 起始下标
        rn 返回数量 默认30
     */
    private static final String APPKEY="4655e53039c9358c89fb0069e5829a3c";
    //返回目录索引
    public static String queryData_DIR(String menu,String dtype, String pn, String rn){
        String path="http://apis.juhe.cn/cook/query.php?";
        Log.i("api",path+"key="+APPKEY+"&menu="+menu+"&dtype="+dtype+"&pn="+pn+"&rn="+rn);
        return path+"key="+APPKEY+"&menu="+menu+"&dtype="+dtype+"&pn="+pn+"&rn="+rn;
    }
    //返回所有种类
    public static String queryCategory(){
        return "http://apis.juhe.cn/cook/category?key="+APPKEY;
    }
    //根据菜谱id查询详细
    public static String queryForDataId(int id){
        String path="http://apis.juhe.cn/cook/";
        return path+"queryid?key="+APPKEY+"&id="+id;
    }
    //根据菜谱种类id查询索引目录
    public static String queryForCategoryId(int cid,String pn,String rn){
        String path="http://apis.juhe.cn/cook/index?";
        return path+"key="+APPKEY+"&cid="+cid+"&pn="+pn+"&rn="+rn+"&format=1";
    }
    public static String getPagerItem(){
        return "http://134.175.17.50/MyApp/getPager.php";
    }
}
