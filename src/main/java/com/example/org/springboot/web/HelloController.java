package com.example.org.springboot.web;

import com.example.org.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String Hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
//gradle 버전을 낮추거나 (4.10.2) 혹은 위와 같이 default 값을 지정해줘야 에러 나지 않음.
//            @RequestParam(value = "name", required = false) String name,
//            @RequestParam(value = "amount", defaultValue = "0") int amount)

            @RequestParam("name") String name,
            @RequestParam("amount") int amount)
    {
        return new HelloResponseDto(name, amount);
    }

}