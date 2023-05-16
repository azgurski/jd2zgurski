package com.zgurski.controller.rest.springdata;

import com.zgurski.domain.Capacity;
import com.zgurski.domain.HibernateRestaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
        summary = "Spring Data Restaurant Search According to query params",
        description = "Spring Data Restaurant Search According to query params")
@Parameters({
        @Parameter(name = "query",
                required = true,
                in = ParameterIn.QUERY,
                schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "query", type = "string", description = "text query")),
        @Parameter(name = "country",
                required = true,
                in = ParameterIn.QUERY,
                schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "France", type = "string", description = "restaurant country")),
        @Parameter(name = "capacity",
                required = true,
                in = ParameterIn.QUERY,
                schema = @Schema(requiredMode = Schema.RequiredMode.REQUIRED, example = "NOT_SELECTED", type = "Capacity", implementation = Capacity.class, description = "restaurant capacity"))
})
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Successfully loaded Restaurants",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = HibernateRestaurant.class))),
        @ApiResponse(responseCode = "404", description = "Restaurant(s) not found", content = {@Content(mediaType = "application/json", schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(mediaType = "application/json", schema = @Schema())})})
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RestaurantSearchByCountryAndCapacitySwagger {
}
