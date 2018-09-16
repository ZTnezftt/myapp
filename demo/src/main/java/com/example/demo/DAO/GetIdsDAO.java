package com.example.demo.DAO;

import com.example.demo.Bean.Title;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GetIdsDAO {
    public Title getids(String id);
}
