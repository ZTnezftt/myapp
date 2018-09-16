package com.example.demo.Main;

import com.example.demo.Bean.*;
import com.example.demo.DAO.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@SpringBootApplication(scanBasePackages = "com.example.demo")
@ResponseBody
@RestController
public class MainTest {

    @Autowired
    GetBannerDAO getBannerDAO;

    @Autowired
    GetAdviseDAO getAdviseDAO;

    @Autowired
    GetZanDAO getZanDAO;

    @Autowired
    GetIdsDAO getIdsDAO;

    @Autowired
    InsertDataDAO insertDataDAO;

    @Autowired
    Key key;

    @Autowired
    PutAdviseDAO putAdviseDAO;

    @Autowired
    getIdDe getIdDe;

    @Autowired
    InsertAdviselrDAO insertAdviselrDAO;

    @Autowired
    GetAdviseLRDAO getAdviseLRDAO;

    private String appkey="4655e53039c9358c89fb0069e5829a3c";
    private String path="http://134.175.17.50:8080";

    @RequestMapping("/GetBanner")//获得首页banner
    public String getBanner() {
        BannerJson bannerJson = new BannerJson();
        List<BannerBean> list = getBannerDAO.getBanner();//MyBatis查询
        bannerJson.setSize(list.size());
        for (BannerBean bannerBean : list) {
            bannerJson.getBanner().getBanner().add(bannerBean.getImageURL());
            bannerJson.getBanner().getUrls().add(bannerBean.getURLS());
        }
        Gson gson = new Gson();
        return gson.toJson(bannerJson, BannerJson.class);
    }

    @RequestMapping("/")//hello world
    public String Hello() {
        return "Hello World";
    }

    @RequestMapping("/GetAdvise")//获得推荐
    public String getAdvise() {
        AdviseJson adviseJson = new AdviseJson();
        List<AdviseBean> list = getAdviseDAO.getAdvise();
        adviseJson.setSize(list.size());
        for (AdviseBean adviseBean : list) {
            adviseJson.getText().add(adviseBean.getText());
            adviseJson.getUrls().add(adviseBean.getUrls());
            adviseJson.getIds().add(adviseBean.getIds());
        }
        Gson gson = new Gson();
        return gson.toJson(adviseJson, AdviseJson.class);
    }

    @RequestMapping("/GetZan")//获得菜品的点赞数
    public int getZan(String id) {
        int num=0;
        try {
            num=getZanDAO.getZan(id);
        }catch (Exception e){
            getZanDAO.InsertId(id);
        }
        return num;
    }

    @RequestMapping("/PutZan")//点赞or取消点赞
    synchronized public void putZan(String id, Boolean type) {
        System.out.println("start");
        int num = getZan(id);
        if (type) {
            getZanDAO.putZan(id, num + 1);
        } else {
            getZanDAO.putZan(id, num - 1);
        }
        System.out.println("end");
    }

    @RequestMapping("/getIds")
    public String getIds(String str){
        Gson gson=new Gson();
        Ids ids=new Ids(str);
        List<Title> list=new LinkedList<>();
        for (int i=0;i<ids.ids.size();i++){
            try {
                Title title=getIdsDAO.getids(String.valueOf(ids.ids.get(i)));
                list.add(title);
            }catch (Exception e){

            }
        }
        return gson.toJson(list);
    }

    @PostMapping("/databasecrud/insert")
    public void insert(String k,String id, String title, String imtro,  String albums){
        try {

            if (key.getKey(k).equals("1")) {
                insertDataDAO.insertDataToids(id, title, imtro, albums);
            }else{
                System.out.println(k+"权限不足");
            }
        }catch (Exception e){
            System.out.println("出现错误，可能是主键重复，插入失败");
        }
    }


    /*
    添加推荐信息，并且自动添加推荐信息id的内容
     */
    @PostMapping("/databasecrud/insertadvise")
    public void adviseinsert(@RequestParam("text") String text, @RequestParam("file")MultipartFile file, @RequestParam("ids") String ids){
        String[] id=ids.split(";");
        for (int i=0;i<id.length;i++){
            getIdDe.getid(id[i],appkey);
        }
        String fileurl=path+"/adviseurl/"+file.getOriginalFilename();
        String fileend=file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
        if (!file.isEmpty()){
            File file1=new File("C://MyApp//"+"//"+"adviseurl"+"//"+file.getOriginalFilename());
            try {
                if (!file1.getParentFile().exists()) {
                    file1.getParentFile().mkdirs();
                }
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            putAdviseDAO.putadvise(text,ids,fileurl);
        }catch (Exception e){
            System.out.println("可能主键重复");
        }
    }


    @RequestMapping("/databasecrud/insertadviselr")
    public void adviselrinsert(@RequestParam("ids")String ids,@RequestParam("type") String type){
        String[] id=ids.split(";");
        for (int i=0;i<id.length;i++){
            getIdDe.getid(id[i],appkey);
        }
        try {
            insertAdviselrDAO.insertlr(ids, type);
        }catch (Exception e){
            System.out.println("主键重复or其他错误，请检查");
        }
    }


    @RequestMapping("/getLR")
    public String getLR(String type){
        String ids="";
        if (type.equals("l")||type.equals("L")){
            ids=getAdviseLRDAO.getL();
        }else if (type.equals("r")||type.equals("R")){
            ids=getAdviseLRDAO.getR();
        }
        return "\""+ids+"\"";
    }

}