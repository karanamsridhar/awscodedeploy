package com.qchindutemple.codedeploy;

import java.util.List;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CodeDeployHandler implements RequestHandler<Request, Response>{

	@Override
	public Response handleRequest(Request input, Context context) {
		AmazonEC2 client = AmazonEC2ClientBuilder
		.standard()
		.withCredentials(new ProfileCredentialsProvider("qcht"))
		.withRegion(Regions.US_EAST_1)
		.build();
		
		DescribeInstancesResult result = client.describeInstances();
		List<Reservation> reservationsList = result.getReservations();
		if(!reservationsList.isEmpty()) {
			for (Reservation reservation : reservationsList) {
				List<Instance> instancesList = reservation.getInstances();
				if(!instancesList.isEmpty()) {
					for (Instance instance : instancesList) {
						System.out.println("Instance ID: "+instance.getInstanceId());
						List<Tag> tagsList = instance.getTags();
						if(!tagsList.isEmpty()) {
							for (Tag tag : tagsList) {
								System.out.println("Key: "+tag.getKey()+" - "+"Values: "+tag.getValue()+"; ");
							}	
						}
						System.out.println("______________________________");
					}
				}
			}
			
		}
		
		
		
		
		
		
		return null;
	}
	
}
