package com.bigdata.mypojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/10/21 19:51
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private boolean  flag;
    private boolean  success;
    private String message;

    public Result(boolean success, String message) {
        this.success=success;
        this.flag = success;
        this.message = message;
    }

}
