package com.example.demo.DAO;

import com.example.demo.Bean.BannerBean;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GetBannerDAO {
    List<BannerBean> getBanner();
    String getString();
}
