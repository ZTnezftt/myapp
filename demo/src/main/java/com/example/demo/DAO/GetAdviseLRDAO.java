package com.example.demo.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GetAdviseLRDAO {
    public String getL();

    public String getR();
}
