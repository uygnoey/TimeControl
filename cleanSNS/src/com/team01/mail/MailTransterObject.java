package com.team01.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailTransterObject {

	private String contentType = "text/html";
	private String host = "mandelina.org";
	private String charset = "utf8";
	private String from = "no-reply@mandelina.org";
	private String name = "TimeOut SNS";

	// 인증 메일 전송
	public void sendIdentify(String email, String memberName, String identify) {

		// SMTP 설정
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);

		// session, mimeMessage 생성
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);

		try {

			// 보낸 시간
			message.setSentDate(new Date());

			// 발신자
			message.setFrom(new InternetAddress(from, name, charset));

			// 수신자
			message.setRecipients(Message.RecipientType.TO, email);

			// 제목
			String subject = memberName + "님 가입을 환영합니다. [인증메일]";
			message.setSubject(subject, charset);

			// 내용
			String contents = memberName
					+ "님 가입을 환영합니다.\n"
					+ memberName
					+ "님의 계정(이메일)은 "
					+ email
					+ "이며 가입을 완료하시려면\n"
					+ "http://mandelina.org/memberidentify.htm?identify="
					+ identify + "&email=" + email + "\n위의 링크를 클릭하여 주시기 바랍니다.";

			message.setText(contents.replaceAll("\n", "<br />"), charset);

			// 헤더
			message.setHeader("Content-Type", contentType);

			// 인증 메일 전송
			Transport.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();
			System.out.println(e.toString());

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			System.out.println(e.toString());

		}

	}

	// 임시 비밀번호 전송
	public void sendPassword(String email, String memberName, String password) {

		// SMTP 설정
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);

		// session, mimeMessage 생성
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);

		try {

			// 보낸 시간
			message.setSentDate(new Date());

			// 발신자
			message.setFrom(new InternetAddress(from, name, charset));

			// 수신자
			message.setRecipients(Message.RecipientType.TO, email);

			// 제목
			String subject = email + "님 변경된 비밀번호입니다. [비밀번호 변경]";
			message.setSubject(subject, charset);

			// 내용
			String contents = email + "님 의 임시 비밀번호는 .\n \"" + password
					+ "\"입니다.\n로그인 후 비밀번호 변경 해주시기 바랍니다.";

			message.setText(contents.replaceAll("\n", "<br />"), charset);

			// 헤더
			message.setHeader("Content-Type", contentType);

			// 인증 메일 전송
			Transport.send(message);

		} catch (MessagingException e) {

			e.printStackTrace();
			System.out.println(e.toString());

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
			System.out.println(e.toString());

		}

	}
}
