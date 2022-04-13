package com.example.webflux.Controller;

import com.example.webflux.Service.UuidGeneratorService;
import com.example.webflux.Service.WebClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@Api
public class WebfluxController {

    @Autowired
    private UuidGeneratorService generatorService;

    @GetMapping(path = "/flux/uuid")
    //@ApiOperation(value = "Get Uuids using Flux")
    public Flux<Object> getId(){
        return generatorService.getId();
    }

    @GetMapping(path = "/stream/uuid", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //@ApiOperation(value = "Get Uuids using Stream")
    public Flux<Object> getIdStream(){
        return generatorService.getId();
    }

    @GetMapping(path = "/mono")
    //@ApiOperation(value = "Get Uuids using Flux")
    public Mono<String>
    getStatus(){
        return generatorService.changeStatusCode();
        //System.out.print("***response**"+s);
        //return Mono.just(s);
    }

}
