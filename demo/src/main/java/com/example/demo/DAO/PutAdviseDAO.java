package com.example.demo.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PutAdviseDAO {

    public void putadvise(
            @Param("text") String text,
            @Param("ids") String ids,
            @Param("urls") String urls
    );
}
