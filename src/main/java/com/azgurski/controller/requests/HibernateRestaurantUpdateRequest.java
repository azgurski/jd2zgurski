package com.azgurski.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HibernateRestaurantUpdateRequest extends HibernateRestaurantCreateRequest{

    private Long restaurant_id;
    private String capacity;
}
