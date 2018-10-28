package com.bigdata.mypojo;

/**
 * @author Administrator
 * @date 2018/10/21 19:01
 * @description
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 * 分页结果封装对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {
    private long total;//总记录数
    private List rows;//当前页结果

}

