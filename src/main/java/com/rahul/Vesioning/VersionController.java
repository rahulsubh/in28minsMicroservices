package com.rahul.Vesioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Rahul Subh");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Rahul", "Subh"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getPersonV1RequestParam(){
        return new PersonV1("Rahul Subh");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getPersonV2RequestParam(){
        return new PersonV2(new Name("Rahul", "Subh"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getPersonV1RequestHeader(){
        return new PersonV1("Rahul Subh");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getPersonV2RequestHeader(){
        return new PersonV2(new Name("Rahul", "Subh"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getPersonV1RequestAcceptHeader(){
        return new PersonV1("Rahul Subh");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getPersonV2RequestAcceptHeader(){
        return new PersonV2(new Name("Rahul", "Subh"));
    }
}
