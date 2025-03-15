package hacom.prueba.reactiva.model;

import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class DateRangeRequest {
	@NotNull
    private OffsetDateTime from;
    
    @NotNull
    private OffsetDateTime to;

	public OffsetDateTime getFrom() {
		return from;
	}

	public void setFrom(OffsetDateTime from) {
		this.from = from;
	}

	public OffsetDateTime getTo() {
		return to;
	}

	public void setTo(OffsetDateTime to) {
		this.to = to;
	}

}