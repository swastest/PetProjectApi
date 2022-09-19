package api.models.schedule.responseShedule;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaysArrayItem {
	private List<DayScheduleItem> daySchedule;
	private String dayName;
	private Boolean today;
	private Integer dayNumber;
}