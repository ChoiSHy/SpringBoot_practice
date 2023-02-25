package com.springboot.apii.controller;

import com.springboot.apii.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public String getHello(){
        return "Hello, world...";
    }

    // http://localhost:8080/api/v1/get-api/name
    @GetMapping (value="/name")
    public String getName(){
        return "ChoiSHy";
    }

    // http://localhost:8080/api/v1/get-api/variable1/변수
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }

    // http://localhost:8080/api/v1/get-api/variable2/변수
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    // http://localhost:8080/api/v1/get-api/request1?name=이름&email=이메일&org=소속
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String org){
        return name+" "+email+" "+org;
    }
    // http://localhost:8080/api/v1/get-api/request2?key1=val1&key2=val2&key3=val3
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb= new StringBuilder();

        param.entrySet().forEach(map->{
            sb.append(map.getKey() + " : "+ map.getValue()+"\n");
        });
        return sb.toString();
    }
    // http://localhost:8080/api/v1/get-api/request3?name=이름&email=이메일&org=소속
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }
}
