package api.models;

import lombok.Data;

@Data
public class AuthResponse{
	private String role;
	private String accessToken;
	private String tokenType;
}