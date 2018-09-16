package com.example.demo.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InsertAdviselrDAO {
    public void insertlr(
            @Param("ids") String ids,
            @Param("type") String type
    );
}
