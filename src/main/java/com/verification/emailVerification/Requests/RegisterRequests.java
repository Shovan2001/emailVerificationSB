package com.verification.emailVerification.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequests 
{
	private String userName;
	private String email;
	private String password;

}
