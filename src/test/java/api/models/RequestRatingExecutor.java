package api.models;

import lombok.Data;

@Data
public class RequestRatingExecutor {
	private String comment;
	private Integer id;
	private Integer points;
}