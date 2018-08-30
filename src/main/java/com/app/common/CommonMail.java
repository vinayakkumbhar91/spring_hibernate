package com.app.common;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class CommonMail {

	@Autowired
	private JavaMailSender sender;

	public boolean mailSender(String to, String subject, String massage, final CommonsMultipartFile file) {

		boolean flag;
		MimeMessage mimeMessage = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, file != null ? true : false);

			helper.setTo(to);
			helper.setFrom("javaproject44@gmail.com");
			helper.setSubject(subject);
			helper.setText(massage);
			if (file != null) {
				helper.addAttachment(file.getOriginalFilename(), new InputStreamSource() {

					public InputStream getInputStream() throws IOException {

						return file.getInputStream();
					}
				});
			}
			sender.send(mimeMessage);
			flag = true;
		} catch (MessagingException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
