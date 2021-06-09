package com.example.springbootdockerelk.swagger;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams(value = {
        @ApiImplicitParam(name = "page", defaultValue = "0", dataTypeClass = Integer.class, paramType = "query"),
        @ApiImplicitParam(name = "size", defaultValue = "5", dataTypeClass = Integer.class, paramType = "query"),
        @ApiImplicitParam(name = "sort", defaultValue = "id", dataTypeClass = String[].class, paramType = "query"),
        @ApiImplicitParam(name = "direction", defaultValue = "ASC", dataTypeClass = String.class, paramType = "query"),
})
public @interface ApiPageable {
}
