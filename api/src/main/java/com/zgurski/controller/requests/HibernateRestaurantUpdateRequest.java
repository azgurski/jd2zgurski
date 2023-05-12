package com.zgurski.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HibernateRestaurantUpdateRequest extends HibernateRestaurantCreateRequest{

    private Long restaurant_id;

}
