package api.models.schedule;

import lombok.Data;

@Data
public class ScheduleRequestDto {
	private Integer endHour;
	private Long endDateLong;
	private Integer beginHour;
	private String endDate;
	private Long startDateLong;
	private Integer id;
	private String startDate;
}