package swp.medichor.utils;

import swp.medichor.model.Campaign;
import swp.medichor.model.DonateRecord;
import swp.medichor.model.DonateRegistration;

public class EmailPlatform {
    public static String buildConfirmCodeEmail(String name, int code) {
        return "<!doctype html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <style amp4email-boilerplate>\n" +
                "    body {\n" +
                "      visibility: hidden\n" +
                "    }\n" +
                "  </style>\n" +
                "\n" +
                "  <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "  <style amp-custom>\n" +
                "    .u-row {\n" +
                "      display: flex;\n" +
                "      flex-wrap: nowrap;\n" +
                "      margin-left: 0;\n" +
                "      margin-right: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .u-row .u-col {\n" +
                "      position: relative;\n" +
                "      width: 100%;\n" +
                "      padding-right: 0;\n" +
                "      padding-left: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .u-row .u-col.u-col-100 {\n" +
                "      flex: 0 0 100%;\n" +
                "      max-width: 100%;\n" +
                "    }\n" +
                "    \n" +
                "    @media (max-width: 767px) {\n" +
                "      .u-row:not(.no-stack) {\n" +
                "        flex-wrap: wrap;\n" +
                "      }\n" +
                "      .u-row:not(.no-stack) .u-col {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "    }\n" +
                "    \n" +
                "    body {\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "    }\n" +
                "    \n" +
                "    table,\n" +
                "    tr,\n" +
                "    td {\n" +
                "      vertical-align: top;\n" +
                "      border-collapse: collapse;\n" +
                "    }\n" +
                "    \n" +
                "    p {\n" +
                "      margin: 0;\n" +
                "    }\n" +
                "    \n" +
                "    .ie-container table,\n" +
                "    .mso-container table {\n" +
                "      table-layout: fixed;\n" +
                "    }\n" +
                "    \n" +
                "    * {\n" +
                "      line-height: inherit;\n" +
                "    }\n" +
                "    \n" +
                "    table,\n" +
                "    td {\n" +
                "      color: #000000;\n" +
                "    }\n" +
                "    \n" +
                "    @media (max-width: 480px) {\n" +
                "      #u_content_text_3 .v-container-padding-padding {\n" +
                "        padding: 20px;\n" +
                "      }\n" +
                "      #u_content_text_6 .v-container-padding-padding {\n" +
                "        padding: 20px 55px 10px;\n" +
                "      }\n" +
                "      #u_content_text_9 .v-container-padding-padding {\n" +
                "        padding: 20px 55px 30px;\n" +
                "      }\n" +
                "      #u_column_6.v-col-background-color {\n" +
                "        background-color: #990102;\n" +
                "      }\n" +
                "    }\n" +
                "  </style>\n" +
                "\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;background-color: #f9f9f9;color: #000000\">\n" +
                "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #f9f9f9;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "    <tbody>\n" +
                "      <tr style=\"vertical-align: top\">\n" +
                "        <td style=\"word-break: break-word;border-collapse: collapse;vertical-align: top\">\n" +
                "          <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "          <div style=\"padding: 0px;\">\n" +
                "            <div style=\"max-width: 600px;margin: 0 auto;\">\n" +
                "              <div class=\"u-row\">\n" +
                "\n" +
                "                <div class=\"u-col u-col-100 v-col-background-color\" style=\"display:flex;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                  <div style=\"width: 100%;padding:0px;\">\n" +
                "\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div style=\"background-color: #ffffff; padding: 0px;\">\n" +
                "            <div style=\"max-width: 600px;margin: 0 auto;background-color: #ffffff;\">\n" +
                "              <div class=\"u-row\">\n" +
                "\n" +
                "                <div class=\"u-col u-col-100 v-col-background-color\" style=\"display:flex;background-color:#990102;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                  <div style=\"width: 100%;padding:0px;\">\n" +
                "\n" +
                "                    <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:25px 20px 15px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
                "\n" +
                "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "                              <tr>\n" +
                "                                <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
                "\n" +
                "                                  <img alt=\"Image\" src=\"https://assets.unlayer.com/projects/110217/1667022454066-MEDICHOR%20Trang%20thông%20tin%20và%20đăng%20ký%20hiến%20máu.png\" width=\"1068\" layout=\"intrinsic\" style=\"width: 37%;max-width: 37%;\">\n" +
                "                                  </img>\n" +
                "\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                            </table>\n" +
                "\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div style=\"padding: 0px;\">\n" +
                "            <div style=\"max-width: 600px;margin: 0 auto;background-color: #990102;\">\n" +
                "              <div class=\"u-row\">\n" +
                "\n" +
                "                <div class=\"u-col u-col-100 v-col-background-color\" style=\"display:flex;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                  <div style=\"width: 100%;padding:0px;\">\n" +
                "\n" +
                "                    <table id=\"u_content_text_3\" style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 10px 30px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
                "\n" +
                "                            <div style=\"color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word;\">\n" +
                "                              <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 16px; line-height: 22.4px; font-family: Cabin, sans-serif;\"><strong><span style=\"line-height: 22.4px; font-size: 16px;\">Cảm ơn bạn đăng ký tạo tài khoản trên Medichor</span></strong>\n" +
                "                                </span>\n" +
                "                              </p>\n" +
                "                            </div>\n" +
                "\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div style=\"padding: 0px;\">\n" +
                "            <div style=\"max-width: 600px;margin: 0 auto;background-color: #ffffff;\">\n" +
                "              <div class=\"u-row\">\n" +
                "\n" +
                "                <div class=\"u-col u-col-100 v-col-background-color\" style=\"display:flex;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                  <div style=\"width: 100%;padding:0px;\">\n" +
                "\n" +
                "                    <table id=\"u_content_text_6\" style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:25px 40px 5px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
                "\n" +
                "                            <div style=\"line-height: 160%; text-align: center; word-wrap: break-word;\">\n" +
                "                              <p style=\"font-size: 14px; line-height: 160%;\"><span style=\"font-size: 24px; line-height: 38.4px;\"><strong><span style=\"line-height: 38.4px; font-size: 24px;\"><span style=\"line-height: 38.4px; font-size: 24px;\">Mã xác thực cho </span></span>\n" +
                "                                </strong>\n" +
                "                                </span><span style=\"font-size: 24px; line-height: 38.4px;\"><strong><span style=\"line-height: 38.4px; font-size: 24px;\"><span style=\"line-height: 38.4px; font-size: 24px;\">tài khoản </span></span>\n" +
                "                                </strong>\n" +
                "                                </span>\n" +
                "                              </p>\n" +
                "                              <p style=\"font-size: 14px; line-height: 160%;\"><span " +
                "style=\"font-size: 24px; line-height: 38.4px;\"><strong><span style=\"line-height: 38.4px; " +
                "font-size: 24px;\"><span style=\"line-height: 38.4px; font-size: 24px;\">của " + name + " là:</span" +
                "></span" +
                ">\n" +
                "                                </strong>\n" +
                "                                </span>\n" +
                "                              </p>\n" +
                "                            </div>\n" +
                "\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "\n" +
                "                    <table id=\"u_content_text_9\" style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:5px 40px 20px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
                "\n" +
                "                            <div style=\"line-height: 160%; text-align: center; word-wrap: break-word;\">\n" +
                "                              <p style=\"font-size: 14px; line-height: 160%;\"><span " +
                "style=\"font-size: 36px; line-height: 57.6px;\"><strong><span style=\"line-height: 57.6px; " +
                "font-size: 36px;\">" + code +"</span></strong>\n" +
                "                                </span>\n" +
                "                              </p>\n" +
                "                            </div>\n" +
                "\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div style=\"padding: 0px;\">\n" +
                "            <div style=\"max-width: 600px;margin: 0 auto;\">\n" +
                "              <div class=\"u-row\">\n" +
                "\n" +
                "                <div class=\"u-col u-col-100 v-col-background-color\" style=\"display:flex;background-color:#ffffff;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;\">\n" +
                "                  <div style=\"width: 100%;padding:0px;\">\n" +
                "\n" +
                "                    <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
                "\n" +
                "                            <div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                              <p style=\"font-size: 14px; line-height: 140%; text-align: center;\">Mã có hiệu lực trong 150 giây. Không chia sẻ mã này với người khác.</p>\n" +
                "                              <p style=\"font-size: 14px; line-height: 140%; text-align: center;\">Đây là email tự động. Vui lòng không trả lời email này.</p>\n" +
                "                            </div>\n" +
                "\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <div style=\"padding: 0px;\">\n" +
                "            <div style=\"max-width: 600px;margin: 0 auto;background-color: #003399;\">\n" +
                "              <div class=\"u-row\">\n" +
                "\n" +
                "                <div id=\"u_column_6\" class=\"u-col u-col-100 v-col-background-color\" style=\"display:flex;background-color:#990102;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                  <div style=\"width: 100%;padding:0px;\">\n" +
                "\n" +
                "                    <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                      <tbody>\n" +
                "                        <tr>\n" +
                "                          <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\" align=\"left\">\n" +
                "\n" +
                "                            <div style=\"color: #ffffff; line-height: 180%; text-align: center; word-wrap: break-word;\">\n" +
                "                              <p style=\"font-size: 14px; line-height: 180%;\">Copyright © 2022 Medichor</p>\n" +
                "                            </div>\n" +
                "\n" +
                "                          </td>\n" +
                "                        </tr>\n" +
                "                      </tbody>\n" +
                "                    </table>\n" +
                "\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "\n" +
                "          <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "  </table>\n" +
                "  <!--[if mso]></div><![endif]-->\n" +
                "  <!--[if IE]></div><![endif]-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String buildConfirmCodeEmailV2(String name, int code) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please use this code to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <h3>" + String.valueOf(code) + "</h3> </p></blockquote>\n Code will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


    public static String buildConfirmCodeEmailForChangePass(String name, int code) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Please use this code to verify your account, then you can change your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <h3>" + String.valueOf(code) + "</h3> </p></blockquote>\n Code will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    public static String buildJoinInCampaignEmail(String donorName, String campaignName) {
        String content = "Hi " + donorName + ", a campaign takes place near you. Click here: link/"
                + campaignName + " for more information. Thank you !";
        return content;
    }

    public static String buildChangeCampaignEmail(String donorName, String campaignName) {
        String content = "Hi " + donorName + ", it seems that a campaign has changed the taking-place time. Click " +
                "here: link/"
                + campaignName + " to register for a new schedule due to the time you have picked was outdated. " +
                "Thank you!";
        return content;
    }

    public static String buildCloseCampaignEmail(String donorName, String campaignName) {
        String content = "Hi " + donorName + ", it seems that campaign named " + campaignName + " has been closed. " +
                "We're sorry to inform that your schedule has been cancelled. Hope you please to look for another " +
                "campaign. Thank you!";
        return content;
    }

    public static String buildMedicalDocumentEmail(DonateRecord donateRecord) {
        String content =
                "Hi " + donateRecord.getDonor().getName() + ", after the blood donation campaign named "
                + donateRecord.getCampaign().getName() + " you have attended. Let's have a look at your medical " +
                        "document: \n" +
                        "- Health Check details: " + donateRecord.getDetails() +"\n" +
                        "- Qualified to donate blood: " + donateRecord.getStatus() + "\n" +
                        "- Your blood type: " + donateRecord.getBloodType() + "\n" +
                        "- The amount of blood donated: " + donateRecord.getAmount() + "ml\n" +
                        "Thank you!";
        return content;
    }

    public static String buildCheckinCodeEmail(DonateRegistration donateRegistration) {
        String content =
                "Thank you " + donateRegistration.getDonor().getName() + " for registering campaign \""
                        + donateRegistration.getCampaign().getName() + "\". Your check-in code will be " +
                        donateRegistration.getCode() + ".\n" +
                        "Please attend at the right time you have reserved. Thank you!";
        return content;
    }
}
