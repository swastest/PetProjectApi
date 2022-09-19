package api.models.city;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sort {
	private Boolean unsorted;
	private Boolean sorted;
	private Boolean empty;
}