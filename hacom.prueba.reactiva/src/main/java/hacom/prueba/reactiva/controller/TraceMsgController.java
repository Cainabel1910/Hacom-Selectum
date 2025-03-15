package hacom.prueba.reactiva.controller;


import hacom.prueba.reactiva.model.TraceMsg;
import hacom.prueba.reactiva.model.DateRangeRequest;
import hacom.prueba.reactiva.service.InsertMetricService;
import hacom.prueba.reactiva.service.TraceMsgService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/trace")
@RequiredArgsConstructor
public class TraceMsgController {
	
	@Autowired
	TraceMsgService service;
	
	@Autowired
	InsertMetricService metricService;

    @PostMapping
    public Mono<TraceMsg> insertTrace(@RequestBody TraceMsg traceMsg) {
    	return metricService.incrementInsertCounter()
    	        .then(service.insertTraceMsg(traceMsg)); 
    }

    @GetMapping
    public Flux<TraceMsg> getTraces(@RequestBody DateRangeRequest dateRange) {
        return service.getTraceMsgsByDateRange(dateRange);
    }
	
}
