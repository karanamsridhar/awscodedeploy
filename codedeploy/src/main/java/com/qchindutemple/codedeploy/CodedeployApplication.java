package com.qchindutemple.codedeploy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodedeployApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CodedeployApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		CodeDeployHandler handler = new CodeDeployHandler();
		handler.handleRequest(null, null);
	}
}
