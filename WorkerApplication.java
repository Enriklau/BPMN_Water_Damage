package zeebe.camunda.worker;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
@EnableZeebeClient
@RestController
public class WorkerApplication {
	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static ConsoleHandler handler = new ConsoleHandler();

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
		logger.setLevel(Level.ALL);
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
	}

	@ZeebeWorker(type = "log_entry", autoComplete = true)
	public void logEntry(final JobClient client, final ActivatedJob job, @ZeebeVariable String work_type,  @ZeebeVariable String setAddress, @ZeebeVariable String setName,@ZeebeVariable String comment_work, @ZeebeVariable String setDate) {
		logger.info("Work: " + work_type);
		logger.info("Date: " + setDate);
		logger.info("Name: " + setName);
		logger.info("Address: " + setAddress);
		logger.info("Comment: " + comment_work);

	}
}



