package com.example.demo.DAO;

import com.example.demo.Bean.AdviseBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
@Repository
public interface GetAdviseDAO {
     List<AdviseBean> getAdvise();

     String test();
}
