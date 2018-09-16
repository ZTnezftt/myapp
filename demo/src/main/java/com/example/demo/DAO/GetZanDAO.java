package com.example.demo.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@Mapper
public interface GetZanDAO {

    public int getZan(String id);

    public Boolean putZan(@Param("id") String id, @Param("num") int num);

    public void InsertId(String id);
}
