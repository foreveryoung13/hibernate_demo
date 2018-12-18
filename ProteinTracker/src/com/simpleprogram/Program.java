package com.simpleprogram;

import org.hibernate.Session;

public class Program {

	public static void main(String[] args) {
		try {
			Session session = HibernateUtilities.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(saveUser());
			session.getTransaction().commit();

			session.beginTransaction();
			User usr = getUserById(session);
			if (usr != null) {
				System.out.println(usr.getId() + " " + usr.getName());
			} else {
				System.out.println("null gan!");
			}
			session.getTransaction().commit();
			session.close();
			HibernateUtilities.getSessionFactory().close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static User saveUser() {
		User user = new User();
		user.setName("Ridwan");
		user.getProteinData().setGoal(50);
		user.getProteinData().setTotal(500);
		return user;
	}

	public static User getUserById(Session session) {
		User userLoader = (User) session.get(User.class, 1);
		return userLoader;
	}

}
