package com.verification.emailVerification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verification.emailVerification.Requests.RegisterRequests;
import com.verification.emailVerification.Responses.RegisterResponses;
import com.verification.emailVerification.Services.UserServices;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
	
	@Autowired
	private UserServices userServices;

	@PostMapping("/register")
	public ResponseEntity<RegisterResponses> register(@RequestBody RegisterRequests registerRequests) {

		RegisterResponses registerResponses=userServices.register(registerRequests);
		
//		ResponseEntity<RegisterResponses> responseEntity =new ResponseEntity<>(registerResponses,HttpStatus.CREATED);
		return new ResponseEntity<>(registerResponses,HttpStatus.CREATED);

//		return responseEntity;
	}
	
	@GetMapping("/verify")
	public ResponseEntity<?> verifyUser(@RequestParam String email,@RequestParam String otp)
	{
		try {
		userServices.verifyUser(email, otp);
		return new ResponseEntity<>("User verified",HttpStatus.ACCEPTED);
		}
		catch (Exception e) {
			return new ResponseEntity<>("Problem with user: "+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

}
