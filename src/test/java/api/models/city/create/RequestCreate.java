package api.models.city.create;

import lombok.Data;

import java.util.List;

@Data
public class RequestCreate{
	private List<Object> discountList;
	private String fiasId;
	private Object cityAdmin;
	private Double latitude;
	private Integer priceForHour;
	private String name;
	private Object id;
	private Region region;
	private Double longitude;
}