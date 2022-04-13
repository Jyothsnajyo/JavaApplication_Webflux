package com.example.webflux.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.Function;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UuidGeneratorService{

    @Autowired
    private RestTemplate restTemplate;


    String url = "http://httpbin.org/uuid";
    String url_500 = "https://run.mocky.io/v3/bacada99-f016-42e0-ba48-a9b0478f0d8b";

    public List<Object> getIds(long interval) {
        List<Object> uuids = new ArrayList<Object>();
        Object object = restTemplate.getForObject(url,Object.class);
        uuids.add(object);
        return uuids;
    }

    public Flux<Object> getId(){
        return Flux.interval(Duration.ofSeconds(0))
                .onBackpressureDrop()
                .map(this::getIds)
                .flatMapIterable(x->x).take(10);

    }

    public Mono<String> changeStatusCode(){
        WebClient webClient = WebClient.create(url);

        WebClient.ResponseSpec response1 = webClient.get().uri(url).retrieve().onStatus(status -> status.value() == HttpStatus.OK.value(),
                response -> Mono.error(new WebClientService("400 : Bad request. Please check the URL.", response.statusCode().value())));
        WebClientService obj = new WebClientService("400 : Bad request. Please check the URL.", 400);
        obj.getStatusCode();
        return Mono.just(obj.getMessage());

    }





}
