package api.models.schedule.responseShedule;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseSchedule {
	private List<DaysArrayItem> daysArray;
	private String monthNameWithYear;
}