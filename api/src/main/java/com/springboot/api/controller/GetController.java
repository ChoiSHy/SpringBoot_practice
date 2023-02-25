package com.springboot.api.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/get-api")
public class GetController {
    // http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello(){
        return "Hello, world!!";
    }

    // http://localhost:8080/api/v1/get-api/name
    @GetMapping("/name")
    public String getName(){
        return "CHOI SH";
    }

    // http://localhost:8080/api/v1/get-api/variable1/변수
    @GetMapping(value="/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    // http://localhost:8080/api/v1/get-api/variable2/변수
    @GetMapping(value="/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    // http://localhost:8080/api/v1/get-api/request1?name=v1&email=v2&organization=v3
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String organization
    ){
        return name+" "+email+" "+organization;
    }
    // http://localhost:8080/api/v1/get-api/request2?key1=val1&key2=val2
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String,String> param){
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map->{
            sb.append(map.getKey()+" : "+map.getValue()+"\n");
        });

        return sb.toString();
    }
    
}
