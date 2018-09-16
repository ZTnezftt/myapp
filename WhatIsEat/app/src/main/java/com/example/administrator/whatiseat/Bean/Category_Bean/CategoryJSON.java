package com.example.administrator.whatiseat.Bean.Category_Bean;

import java.util.LinkedList;
import java.util.List;

//菜谱分类
public class CategoryJSON {
    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    ///obj
    public String resultcode;
    public String reason;
    public int error_code;

    public List<Result> getResult() {
        return result;
    }

    ////array
    public List<Result> result=new LinkedList<>();
}
