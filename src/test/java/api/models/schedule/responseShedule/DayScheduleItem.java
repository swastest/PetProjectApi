package api.models.schedule.responseShedule;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayScheduleItem {
	private Integer endHour;
	private Object endDateLong;
	private String endDate;
	private Integer beginHour;
	private Object startDateLong;
	private String scheduleStatus;
	private Integer id;
	private String startDate;
}