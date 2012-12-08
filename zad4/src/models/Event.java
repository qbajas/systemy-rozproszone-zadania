package models;

import java.util.Date;
import java.util.List;

/**
 * Should be handled by default servant
 * @author Qba
 *
 */
public class Event {

	private String name;
	private String description;
	private Date eventDate;
	private User createdBy;
	private List<User> subscribedUsers;
	
	
	
}
