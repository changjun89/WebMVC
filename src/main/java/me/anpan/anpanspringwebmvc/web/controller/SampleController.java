package me.anpan.anpanspringwebmvc.web.controller;

import me.anpan.anpanspringwebmvc.web.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    //preHandle 1
    //preHandle 2
    //요청처리
    //postHandler 2
    //postHandler 1
    //뷰 랜더링
    //afterCompletion 2
    //afterCompletion  1

    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person) {
        return "hello " + person.getName();
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person) {
        return person;
    }

    @GetMapping("/xmlMessage")
    public Person xmlMessage(@RequestBody Person person) {
        return person;
    }


}
