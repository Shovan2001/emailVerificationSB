package com.verification.emailVerification.Responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponses 
{
	private String userName;
	private String email;

}
