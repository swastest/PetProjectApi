package api.models.city;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CitiesItem {
	private String fiasId;
	private Integer priceForHour;
	private String name;
	private Integer id;
	private Region region;
}