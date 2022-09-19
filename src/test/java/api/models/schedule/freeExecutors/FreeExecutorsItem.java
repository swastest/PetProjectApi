package api.models.schedule.freeExecutors;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreeExecutorsItem {
	private Object photoUrl;
	private String patronymic;
	private String role;
	private String phone;
	private City city;
	private String surname;
	private String name;
	private Integer id;
	private String status;
}