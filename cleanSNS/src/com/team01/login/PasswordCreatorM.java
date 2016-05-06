package com.team01.login;

import java.util.Random;

public class PasswordCreatorM {

	public String passwordCreator() {

		String password = "";

		Random r = new Random();

		for (int i = 0; i <= 10; i++) {

			int j = r.nextInt(4);

			if (j == 0) {
				password += (char) (r.nextInt(26) + 65);
			} else if (j == 1) {
				password += (char) (r.nextInt(26) + 97);
			} else if (j == 2) {
				password += r.nextInt(10);
			} else if (j == 3) {
				int k = r.nextInt(5);
				
				if (k == 0)
					password += (char) (r.nextInt(15) + 33);
				else if (k == 1)
					password += (char) (r.nextInt(7) + 58);
				else if (k == 2)
					password += (char)91;
				else if (k == 3)
					password += (char) (r.nextInt(4) + 93);
				else if (k == 4)
					password += (char) (r.nextInt(5) + 123);
				
			}

		}

		System.out.println(password);

		return password;

	}

}
