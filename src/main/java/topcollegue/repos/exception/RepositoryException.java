package topcollegue.repos.exception;

import topcollegue.exception.TopCollegueException;

public class RepositoryException extends TopCollegueException {

	private static final long serialVersionUID = 1L;

	public RepositoryException() {
		super();
	}

	public RepositoryException(String message) {
		super(message);
	}

}
