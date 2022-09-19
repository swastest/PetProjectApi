package api.models.city.dadata;

import lombok.Data;

@Data
public class DadataResponseItem{
	private String fiasId;
	private Double latitude;
	private String name;
	private String region;
	private Double longitude;
}