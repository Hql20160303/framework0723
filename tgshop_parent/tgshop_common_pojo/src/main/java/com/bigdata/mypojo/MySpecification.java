package com.bigdata.mypojo;

import com.bigdata.pojo.Specification;
import com.bigdata.pojo.SpecificationOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/10/22 22:54
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySpecification implements Serializable {
    private Specification specification;
    private List<SpecificationOption> specificationOptionList;
}
