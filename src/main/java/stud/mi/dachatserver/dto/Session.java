package stud.mi.dachatserver.dto;

public class Session {

	private long sessionNumber = 0L;

	public Session(final long sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public long getSessionNumber() {
		return sessionNumber;
	}

}
