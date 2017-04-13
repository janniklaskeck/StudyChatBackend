package stud.mi.dachatserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import stud.mi.dachatserver.chatserver.Server;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Server s = new Server(1234);
				s.start();
			}

		}).start();
		SpringApplication.run(Application.class, args);
	}

}
