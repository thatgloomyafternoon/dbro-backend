package com.fw.dbro.backend.constants;

public interface EmailConstants {

  /**
   * NOTES: dbro.mgmt@gmail.com must have 2-step verification setting turned on
   * for the authentication to succeed.
   */

  String HOST = "smtp.gmail.com";
  int PORT = 587;
  String EMAIL_ADDR = "dbro.mgmt@gmail.com";
  String EMAIL_PSWD = "oqtoeuulsixmmqea";

  String PROPERTY_KEY_MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
  String PROPERTY_KEY_MAIL_SMTP_AUTH = "mail.smtp.auth";
  String PROPERTY_KEY_MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
  String PROPERTY_KEY_MAIL_DEBUG = "mail.debug";

  String PROPERTY_VALUE_SMTP = "smtp";
  String PROPERTY_VALUE_TRUE = "true";

}
