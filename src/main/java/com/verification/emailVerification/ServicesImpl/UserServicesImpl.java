package com.verification.emailVerification.ServicesImpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verification.emailVerification.Entities.Users;
import com.verification.emailVerification.Repository.UsersRepo;
import com.verification.emailVerification.Requests.RegisterRequests;
import com.verification.emailVerification.Responses.RegisterResponses;
import com.verification.emailVerification.Services.EmailServices;
import com.verification.emailVerification.Services.UserServices;

@Service
public class UserServicesImpl implements UserServices {
	@Autowired
	private UsersRepo usersRepo;
	@Autowired
	private EmailServices emailServices;

	@Override
	public RegisterResponses register(RegisterRequests registerRequests) {

		Users user_found = usersRepo.findByEmail(registerRequests.getEmail());

		if (user_found != null && user_found.isVerified())
			throw new RuntimeException("User already registered!!");
		
		
		String otp =generate_otp();

		// Build users from request
		Users users = Users.builder().userName(registerRequests.getUserName()).email(registerRequests.getEmail())
				.password(registerRequests.getPassword()).otp(otp).build();

//		Since using builder no need
//		users.setOtp(otp);
		
		usersRepo.save(users);
		
		
		sendVerifictaionEmail(users.getEmail(), otp);

		// now build and return a response
		RegisterResponses responses = RegisterResponses.builder().userName(registerRequests.getUserName())
				.email(registerRequests.getEmail()).build();

		return responses;
	}

	public String generate_otp() {

		Random rand = new Random();
		int random = rand.nextInt(10000);
		String otp = Integer.toString(random);

		return otp;
	}
	
	public void sendVerifictaionEmail(String to_email,String otp)
	{
		String subject="Email Verification";
		String body="Your verification otp is: "+otp;
		
		emailServices.sendEmail(to_email, subject, body);
	}

	@Override
	public void verifyUser(String email, String otp) {
		
		Users user=usersRepo.findByEmail(email);
		
		if(user==null)
		{
			throw new RuntimeException("No user exists!!");
		}
		else if (user.isVerified()) 
		{
			throw new RuntimeException("User already verified!!");
		}
		else if(user.getOtp().equals(otp))
		{
			user.setVerified(true);
			usersRepo.save(user);
		}
		else if (!user.getOtp().equals(otp)) {
			throw new RuntimeException("Wrong otp!!");
		}
		else {
			throw new RuntimeException("Try again later!!");
		}
		
	}

}
