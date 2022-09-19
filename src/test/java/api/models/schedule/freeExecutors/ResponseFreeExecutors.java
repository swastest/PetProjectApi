package api.models.schedule.freeExecutors;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseFreeExecutors {
	private List<Object> busyExecutors;
	private Integer startHour;
	private List<FreeExecutorsItem> freeExecutors;
}