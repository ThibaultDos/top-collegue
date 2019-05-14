package topcollegue.vote.exceptions;

import topcollegue.exception.TopCollegueException;

public class VoteException extends TopCollegueException {

	private static final long serialVersionUID = 1L;

	public VoteException() {
		super();
	}

	public VoteException(String message) {
		super("Ce vote n'est pas légal ; si vous voulez tricher faîtes de la politique.");
	}
	
}
