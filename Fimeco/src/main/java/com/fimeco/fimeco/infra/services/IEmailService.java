package com.fimeco.fimeco.infra.services;

import java.io.File;

public interface IEmailService {

    void sendEmail(String to, String subject, String message);

    void sendEmailWithFile(String to, String subject, String message, File file);


}
