package hacom.prueba.reactiva.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public class InsertMetricService {

	private final Counter insertCounter;

    public InsertMetricService(MeterRegistry registry) {
        this.insertCounter = Counter.builder("hacom.test.developer.insert.rx")
                .description("Número de inserciones recibidas")
                .register(registry);
    }

    public Mono<Void> incrementInsertCounter() {
        return Mono.fromRunnable(() -> insertCounter.increment()); // Envuelve la operación en un flujo reactivo
    }
}