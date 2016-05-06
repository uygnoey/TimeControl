package com.team01.mail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MailIdentify {

	// 인증코드 생성
	public String MailIdentify(String name) {

		Random r = new Random();
		Date date = new Date();
		SimpleDateFormat registerYYYY = new SimpleDateFormat("yyyy");
		SimpleDateFormat registerMM = new SimpleDateFormat("MM");
		SimpleDateFormat registerDD = new SimpleDateFormat("dd");
		SimpleDateFormat registerHH = new SimpleDateFormat("HH");
		SimpleDateFormat registerMm = new SimpleDateFormat("mm");
		SimpleDateFormat registerSS = new SimpleDateFormat("ss");

		String[] randomCode = { "T", "i", "m", "e", "O", "u", "t", "S", "N" };

		String identifyCode = registerYYYY.format(date)
				+ randomCode[r.nextInt(9)] + registerMM.format(date)
				+ randomCode[r.nextInt(9)] + registerDD.format(date)
				+ randomCode[r.nextInt(9)] + registerHH.format(date)
				+ randomCode[r.nextInt(9)] + registerMm.format(date)
				+ randomCode[r.nextInt(9)] + registerSS.format(date);

		return identifyCode;

	}
}
