package com.zgurski.controller.rest.springdata;

import com.zgurski.domain.HibernateRestaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Operation(
        summary = "Spring Data Restaurants Find All Search",
        description = "Find All Restaurants without limitations",
        tags = {"hateoas", "get"})
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully loaded Restaurants",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = HibernateRestaurant.class))),
        @ApiResponse(responseCode = "404", description = "Entity not found", content = {@Content(mediaType = "application/json", schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(mediaType = "application/json", schema = @Schema())})})

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RestaurantGetAllSwagger {
}
