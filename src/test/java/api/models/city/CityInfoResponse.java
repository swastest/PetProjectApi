package api.models.city;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityInfoResponse {
	private Integer number;
	private Boolean last;
	private Integer numberOfElements;
	private Integer size;
	private Integer totalPages;
	private Pageable pageable;
	private Sort sort;
	private List<ContentItem> content;
	private Boolean first;
	private Integer totalElements;
	private Boolean empty;
}