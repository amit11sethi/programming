package systemdesign.newsfeed;

import java.util.concurrent.ConcurrentHashMap;

public class UserService {

	private volatile static UserService users = null;
	ConcurrentHashMap<Integer, User> userMap = null;

	private UserService() {
		userMap = new ConcurrentHashMap<>();
		userMap.put(1, new User(1, "A"));
		userMap.put(2, new User(1, "B"));
		userMap.put(3, new User(1, "C"));
		userMap.put(4, new User(1, "D"));

	}

	public static UserService getInstance() {
		if (users == null) { // Single Checked
			synchronized (UserService.class) {
				if (users == null) { // Double checked
					users = new UserService();
				}
			}
		}
		return users;
	}

	public User getUserbyId(int id) {
		return userMap.get(id);
	}

}
