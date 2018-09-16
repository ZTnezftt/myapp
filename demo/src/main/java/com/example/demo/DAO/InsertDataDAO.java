package com.example.demo.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InsertDataDAO {

    public void insertDataToids(
            @Param("id") String id,
            @Param("title") String title,
            @Param("imtro") String imtro,
            @Param("albums") String albums
    );

}
