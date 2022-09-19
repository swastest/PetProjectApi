package api.models.city.dadata;

import lombok.Data;

import java.util.List;

@Data
public class DadataResponse{
	private List<DadataResponseItem> dadataResponse;
}