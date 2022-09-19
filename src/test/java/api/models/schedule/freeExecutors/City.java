package api.models.schedule.freeExecutors;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
	private List<Object> discountList;
	private String fiasId;
	private CityAdmin cityAdmin;
	private Double priceForHour;
	private Double latitude;
	private String name;
	private Integer id;
	private Region region;
	private Double longitude;
}