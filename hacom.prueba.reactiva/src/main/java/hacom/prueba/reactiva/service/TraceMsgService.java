package hacom.prueba.reactiva.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hacom.prueba.reactiva.model.DateRangeRequest;
import hacom.prueba.reactiva.model.TraceMsg;
import hacom.prueba.reactiva.repository.TraceMsgRepositoryInterface;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
@RequiredArgsConstructor
@Log4j2
public class TraceMsgService {

	@Autowired
    TraceMsgRepositoryInterface repository;
	
    private static final Logger log = LogManager.getLogger(TraceMsgService.class);

    public Mono<TraceMsg> insertTraceMsg(TraceMsg traceMsg) {
        log.info("Recibiendo request de inserción: {}", traceMsg);
        return repository.save(traceMsg)
                .doOnSuccess(msg -> log.info("Mensaje guardado: {}", msg));
    }

    public Flux<TraceMsg> getTraceMsgsByDateRange(DateRangeRequest dateRange) {
        log.info("Buscando mensajes entre {} y {}", dateRange.getFrom(), dateRange.getTo());
        return repository.findByTsBetween(dateRange.getFrom(), dateRange.getTo())
                .map(trace -> {
                    trace.setTs(convertToUtc(trace.getTs())); 
                    return trace;
                });
    }
    
    private OffsetDateTime convertToUtc(OffsetDateTime date) {
        return date.withOffsetSameInstant(ZoneOffset.UTC);
    }
}