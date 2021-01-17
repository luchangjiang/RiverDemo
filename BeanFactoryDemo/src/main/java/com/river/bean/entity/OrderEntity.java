package com.river.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity implements Serializable {

    private String orderId;

    private String address;

    private List<GoodEntity> goods;

    private LocalDateTime createTime;

    private String userId;

    private Integer pay;

    private BigDecimal cost;

}