package com.verification.emailVerification.Services;

import com.verification.emailVerification.Requests.RegisterRequests;
import com.verification.emailVerification.Responses.RegisterResponses;

public interface UserServices 
{
	public RegisterResponses register(RegisterRequests registerRequests);
	
	void verifyUser(String email, String otp);
	
}
