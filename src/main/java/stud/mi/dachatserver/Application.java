package stud.mi.dachatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// new Thread(() -> {
		// Server s = new Server(1234);
		// s.start();
		// }).start();
		SpringApplication.run(Application.class, args);
	}

}
