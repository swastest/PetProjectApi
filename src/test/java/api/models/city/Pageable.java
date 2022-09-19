package api.models.city;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pageable {
	private Boolean paged;
	private Integer pageNumber;
	private Integer offset;
	private Integer pageSize;
	private Boolean unpaged;
	private Sort sort;
}