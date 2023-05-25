package core.ics.mstravelai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MsTravelAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTravelAiApplication.class, args);
		log.info("Application UP");
	}

}
