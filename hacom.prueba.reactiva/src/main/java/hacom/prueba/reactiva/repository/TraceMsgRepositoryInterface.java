package hacom.prueba.reactiva.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import hacom.prueba.reactiva.model.TraceMsg;
import reactor.core.publisher.Flux;
import java.time.OffsetDateTime;

public interface TraceMsgRepositoryInterface extends ReactiveMongoRepository<TraceMsg, String> {
    Flux<TraceMsg> findByTsBetween(OffsetDateTime from, OffsetDateTime to);
}
