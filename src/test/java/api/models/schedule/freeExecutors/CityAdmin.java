package api.models.schedule.freeExecutors;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityAdmin {
	private Object photoUrl;
	private String patronymic;
	private List<Object> regions;
	private String role;
	private List<CitiesItem> cities;
	private String phone;
	private String surname;
	private Object name;
	private Integer id;
}