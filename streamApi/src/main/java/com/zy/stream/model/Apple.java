package com.zy.stream.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @date 2020/12/1 9:39
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Apple {

    private String color;
    private Integer weight;


}
