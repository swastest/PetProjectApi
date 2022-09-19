package api.models.city;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentItem {
	private List<DiscountListItem> discountList;
	private String fiasId;
	private CityAdmin cityAdmin;
	private Float priceForHour;
	private Object latitude;
	private String name;
	private Integer id;
	private Region region;
	private Object longitude;
}