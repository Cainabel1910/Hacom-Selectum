package hacom.prueba.reactiva.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateToOffsetDateTimeConverter implements Converter<Date, OffsetDateTime> {
    @Override
    public OffsetDateTime convert(Date source) {
        return source.toInstant().atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }
}
