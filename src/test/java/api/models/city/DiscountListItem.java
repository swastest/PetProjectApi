package api.models.city;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DiscountListItem {
	private Integer endHour;
	private Integer startHour;
	private Integer id;
	private Integer percent;
}