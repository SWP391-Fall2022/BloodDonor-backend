package swp.medichor.utils;

import swp.medichor.model.Campaign;
import swp.medichor.model.DonateRecord;
import swp.medichor.model.DonateRegistration;
import swp.medichor.model.User;

public class EmailPlatform {
    private static final String DOMAIN = "http://localhost:3000/";
    private static final String LINK = DOMAIN + "campaign/campaign-detail/";
    private static final String OTHER_CAMPAIGNS_LINK = DOMAIN + "campaign";
    private static final String MEDICAL_LINK = DOMAIN + "donor/history";

    public static String buildConfirmCodeEmail(String name, int code) {
        return "<!DOCTYPE html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <style amp4email-boilerplate>\n" +
                "      body {\n" +
                "        visibility: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "    <style amp-custom>\n" +
                "      .u-row {\n" +
                "        display: flex;\n" +
                "        flex-wrap: nowrap;\n" +
                "        margin-left: 0;\n" +
                "        margin-right: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        padding-right: 0;\n" +
                "        padding-left: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col.u-col-100 {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 767px) {\n" +
                "        .u-row:not(.no-stack) {\n" +
                "          flex-wrap: wrap;\n" +
                "        }\n" +
                "        .u-row:not(.no-stack) .u-col {\n" +
                "          flex: 0 0 100%;\n" +
                "          max-width: 100%;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #000000;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_3 .v-container-padding-padding {\n" +
                "          padding: 20px;\n" +
                "        }\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 10px;\n" +
                "        }\n" +
                "        #u_content_text_9 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 30px;\n" +
                "        }\n" +
                "        #u_column_6.v-col-background-color {\n" +
                "          background-color: #990102;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0; padding: 0; background-color: #f9f9f9; color: #000000\"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div style=\"max-width: 600px; margin: 0 auto\">\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\"></div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"background-color: #ffffff; padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 20px 15px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <table\n" +
                "                                width=\"100%\"\n" +
                "                                cellpadding=\"0\"\n" +
                "                                cellspacing=\"0\"\n" +
                "                                border=\"0\"\n" +
                "                              >\n" +
                "                                <tr>\n" +
                "                                  <td\n" +
                "                                    style=\"\n" +
                "                                      padding-right: 0px;\n" +
                "                                      padding-left: 0px;\n" +
                "                                    \"\n" +
                "                                    align=\"center\"\n" +
                "                                  >\n" +
                "                                    <img\n" +
                "                                      alt=\"Image\"\n" +
                "                                      src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                      style=\"max-width: 30%\"\n" +
                "                                    >\n" +
                "                                    </img>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #990102;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_3\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 5px 10px 30px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 140%\">\n" +
                "                                  <span\n" +
                "                                    style=\"\n" +
                "                                      font-size: 16px;\n" +
                "                                      line-height: 22.4px;\n" +
                "                                      font-family: Cabin, sans-serif;\n" +
                "                                    \"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 22.4px;\n" +
                "                                          font-size: 16px;\n" +
                "                                        \"\n" +
                "                                        >Cảm ơn quý vị đã đăng ký tạo tài khoản\n" +
                "                                        trên Medichor</span\n" +
                "                                      ></strong\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #ffffff;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_6\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 55px 5px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 160%\">\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 24px; line-height: 38.4px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 38.4px;\n" +
                "                                          font-size: 24px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          >Mã xác thực cho\n" +
                "                                        </span></span\n" +
                "                                      >\n" +
                "                                    </strong> </span\n" +
                "                                  ><span\n" +
                "                                    style=\"font-size: 24px; line-height: 38.4px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 38.4px;\n" +
                "                                          font-size: 24px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          >tài khoản của</span\n" +
                "                                        ></span\n" +
                "                                      >\n" +
                "                                    </strong></span\n" +
                "                                  ><span\n" +
                "                                    style=\"font-size: 24px; line-height: 38.4px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 38.4px;\n" +
                "                                          font-size: 24px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          >" + name + " là:</span\n" +
                "                                        ></span\n" +
                "                                      >\n" +
                "                                    </strong>\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_9\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 5px 40px 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 160%\">\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 36px; line-height: 57.6px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 57.6px;\n" +
                "                                          font-size: 36px;\n" +
                "                                        \"\n" +
                "                                        >" + code + "</span\n" +
                "                                      ></strong\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: left;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  Mã có hiệu lực trong 150 giây.\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  Không được chia sẻ mã này với người khác.\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  Đây là email tự động, vui lòng không trả lời\n" +
                "                                  email này.\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 180%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                  Copyright © 2022 Medichor\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

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
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html\n" +
                "  xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "  xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "  xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                ">\n" +
                "  <head>\n" +
                "    <!--[if gte mso 9]>\n" +
                "      <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "          <o:AllowPNG />\n" +
                "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "      </xml>\n" +
                "    <![endif]-->\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <!--<![endif]-->\n" +
                "    <title></title>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "      @media only screen and (min-width: 620px) {\n" +
                "        .u-row {\n" +
                "          width: 600px !important;\n" +
                "        }\n" +
                "        .u-row .u-col {\n" +
                "          vertical-align: top;\n" +
                "        }\n" +
                "        .u-row .u-col-100 {\n" +
                "          width: 600px !important;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 620px) {\n" +
                "        .u-row-container {\n" +
                "          max-width: 100% !important;\n" +
                "          padding-left: 0px !important;\n" +
                "          padding-right: 0px !important;\n" +
                "        }\n" +
                "        .u-row .u-col {\n" +
                "          min-width: 320px !important;\n" +
                "          max-width: 100% !important;\n" +
                "          display: block !important;\n" +
                "        }\n" +
                "        .u-row {\n" +
                "          width: calc(100% - 40px) !important;\n" +
                "        }\n" +
                "        .u-col {\n" +
                "          width: 100% !important;\n" +
                "        }\n" +
                "        .u-col > div {\n" +
                "          margin: 0 auto;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      a[x-apple-data-detectors=\"true\"] {\n" +
                "        color: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #000000;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_3 .v-container-padding-padding {\n" +
                "          padding: 20px !important;\n" +
                "        }\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 10px !important;\n" +
                "        }\n" +
                "        #u_content_text_9 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 30px !important;\n" +
                "        }\n" +
                "        #u_column_6 .v-col-background-color {\n" +
                "          background-color: #990102 !important;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <link\n" +
                "      href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\"\n" +
                "      rel=\"stylesheet\"\n" +
                "      type=\"text/css\"\n" +
                "    />\n" +
                "    <!--<![endif]-->\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -webkit-text-size-adjust: 100%;\n" +
                "      background-color: #f9f9f9;\n" +
                "      color: #000000;\n" +
                "    \"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        mso-table-lspace: 0pt;\n" +
                "        mso-table-rspace: 0pt;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse !important;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: transparent;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"height: 100%; width: 100% !important\"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: #ffffff\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #ffffff;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"background-color: #990102;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"\n" +
                "                        background-color: #990102;\n" +
                "                        height: 100%;\n" +
                "                        width: 100% !important;\n" +
                "                      \"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 25px 20px 15px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <table\n" +
                "                                  width=\"100%\"\n" +
                "                                  cellpadding=\"0\"\n" +
                "                                  cellspacing=\"0\"\n" +
                "                                  border=\"0\"\n" +
                "                                >\n" +
                "                                  <tr>\n" +
                "                                    <td\n" +
                "                                      style=\"\n" +
                "                                        padding-right: 0px;\n" +
                "                                        padding-left: 0px;\n" +
                "                                      \"\n" +
                "                                      align=\"center\"\n" +
                "                                    >\n" +
                "                                      <img\n" +
                "                                        align=\"center\"\n" +
                "                                        border=\"0\"\n" +
                "                                        src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                        style=\"max-width: 30%\"\n" +
                "                                      />\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #990102;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #990102;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"height: 100%; width: 100% !important\"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          id=\"u_content_text_3\"\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 5px 10px 30px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    color: #ffffff;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p style=\"font-size: 14px; line-height: 140%\">\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 22.4px;\n" +
                "                                        font-family: Cabin, sans-serif;\n" +
                "                                      \"\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 22.4px;\n" +
                "                                            font-size: 16px;\n" +
                "                                          \"\n" +
                "                                          >Cảm ơn quý vị đã tin dùng Medichor</span\n" +
                "                                        ></strong\n" +
                "                                      >\n" +
                "                                    </span>\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"height: 100%; width: 100% !important\"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          id=\"u_content_text_6\"\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 25px 55px 5px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: center;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p style=\"font-size: 14px; line-height: 160%\">\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 24px;\n" +
                "                                        line-height: 38.4px;\n" +
                "                                      \"\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          ><span\n" +
                "                                            style=\"\n" +
                "                                              line-height: 38.4px;\n" +
                "                                              font-size: 24px;\n" +
                "                                            \"\n" +
                "                                            >Mã xác thực cho việc đổi mật khẩu\n" +
                "                                          </span></span\n" +
                "                                        >\n" +
                "                                      </strong> </span\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 24px;\n" +
                "                                        line-height: 38.4px;\n" +
                "                                      \"\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          ><span\n" +
                "                                            style=\"\n" +
                "                                              line-height: 38.4px;\n" +
                "                                              font-size: 24px;\n" +
                "                                            \"\n" +
                "                                            >tài khoản của\n" +
                "                                          </span></span\n" +
                "                                        >\n" +
                "                                      </strong> </span\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 24px;\n" +
                "                                        line-height: 38.4px;\n" +
                "                                      \"\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          ><span\n" +
                "                                            style=\"\n" +
                "                                              line-height: 38.4px;\n" +
                "                                              font-size: 24px;\n" +
                "                                            \"\n" +
                "                                            >" + name + " là:</span\n" +
                "                                          ></span\n" +
                "                                        >\n" +
                "                                      </strong>\n" +
                "                                    </span>\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <table\n" +
                "                          id=\"u_content_text_9\"\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 5px 40px 20px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: center;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p style=\"font-size: 14px; line-height: 160%\">\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 36px;\n" +
                "                                        line-height: 57.6px;\n" +
                "                                      \"\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 57.6px;\n" +
                "                                            font-size: 36px;\n" +
                "                                          \"\n" +
                "                                          >" + code + "</span\n" +
                "                                        ></strong\n" +
                "                                      >\n" +
                "                                    </span>\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <table\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 10px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: left;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 140%;\n" +
                "                                      text-align: center;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    Mã có hiệu lực trong 150 giây.\n" +
                "                                  </p>\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 140%;\n" +
                "                                      text-align: center;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    Không được chia sẻ mã này với người khác.\n" +
                "                                  </p>\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 140%;\n" +
                "                                      text-align: center;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    Đây là email tự động, vui lòng không trả lời\n" +
                "                                    email này.\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #003399;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"background-color: #990102;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"\n" +
                "                        background-color: #990102;\n" +
                "                        height: 100%;\n" +
                "                        width: 100% !important;\n" +
                "                      \"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 10px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    color: #ffffff;\n" +
                "                                    line-height: 180%;\n" +
                "                                    text-align: center;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                    Copyright © 2022 Medichor\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

    public static String buildNormalCampaignNotiEmail(User user, Campaign campaign) {
        return "<!DOCTYPE html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <style amp4email-boilerplate>\n" +
                "      body {\n" +
                "        visibility: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "    <style amp-custom>\n" +
                "      .u-row {\n" +
                "        display: flex;\n" +
                "        flex-wrap: nowrap;\n" +
                "        margin-left: 0;\n" +
                "        margin-right: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        padding-right: 0;\n" +
                "        padding-left: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col.u-col-100 {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 767px) {\n" +
                "        .u-row:not(.no-stack) {\n" +
                "          flex-wrap: wrap;\n" +
                "        }\n" +
                "        .u-row:not(.no-stack) .u-col {\n" +
                "          flex: 0 0 100%;\n" +
                "          max-width: 100%;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #ffffff;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 30px 10px;\n" +
                "        }\n" +
                "        #u_column_6.v-col-background-color {\n" +
                "          background-color: #990102;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0; padding: 0; background-color: #f9f9f9; color: #ffffff\"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div style=\"max-width: 600px; margin: 0 auto\">\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\"></div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"background-color: #ffffff; padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <table\n" +
                "                                width=\"100%\"\n" +
                "                                cellpadding=\"0\"\n" +
                "                                cellspacing=\"0\"\n" +
                "                                border=\"0\"\n" +
                "                              >\n" +
                "                                <tr>\n" +
                "                                  <td\n" +
                "                                    style=\"\n" +
                "                                      padding-right: 0px;\n" +
                "                                      padding-left: 0px;\n" +
                "                                    \"\n" +
                "                                    align=\"center\"\n" +
                "                                  >\n" +
                "                                    <img\n" +
                "                                      alt=\"Image\"\n" +
                "                                      src=\"https://assets.unlayer.com/projects/110217/1667022454066-MEDICHOR%20Trang%20thông%20tin%20và%20đăng%20ký%20hiến%20máu.png\"\n" +
                "                                      style=\"width: 30%;\"\n" +
                "                                    >\n" +
                "                                    </img>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #ffffff;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_6\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 30px 55px 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #000000;\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Kính gửi " + user.getDonor().getName() + ",\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Medichor nhận thấy " + campaign.getName() + " hiện\n" +
                "                                    đang diễn ra ở gần nơi thường trú của\n" +
                "                                    bạn.</span\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Để biết thêm thông tin chi tiết, quý vị hãy vào đường\n" +
                "                                    dẫn: <a href=\"" + LINK + campaign.getId() + "\">Link</a>\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                   \n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #000000;\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: left;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <strong\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 14px;\n" +
                "                                        line-height: 19.6px;\n" +
                "                                      \"\n" +
                "                                      >-Cảm ơn quý vị đã tin dùng\n" +
                "                                      Medichor-</span\n" +
                "                                    ></strong\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 180%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                  Copyright © 2022 Medichor\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

    public static String buildUrgentCampaignNotiEmail(User user, Campaign campaign) {
        return "<!DOCTYPE html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <style amp4email-boilerplate>\n" +
                "      body {\n" +
                "        visibility: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "    <style amp-custom>\n" +
                "      .u-row {\n" +
                "        display: flex;\n" +
                "        flex-wrap: nowrap;\n" +
                "        margin-left: 0;\n" +
                "        margin-right: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        padding-right: 0;\n" +
                "        padding-left: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col.u-col-100 {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 767px) {\n" +
                "        .u-row:not(.no-stack) {\n" +
                "          flex-wrap: wrap;\n" +
                "        }\n" +
                "        .u-row:not(.no-stack) .u-col {\n" +
                "          flex: 0 0 100%;\n" +
                "          max-width: 100%;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #000000;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 30px 10px;\n" +
                "        }\n" +
                "        #u_content_text_9 .v-container-padding-padding {\n" +
                "          padding: 20px 50px 30px;\n" +
                "        }\n" +
                "        #u_content_text_11 .v-container-padding-padding {\n" +
                "          padding: 10px 20px 15px;\n" +
                "        }\n" +
                "        #u_column_6.v-col-background-color {\n" +
                "          background-color: #990102;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0; padding: 0; background-color: #f9f9f9; color: #000000\"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div style=\"max-width: 600px; margin: 0 auto\">\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\"></div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"background-color: #ffffff; padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <table\n" +
                "                                width=\"100%\"\n" +
                "                                cellpadding=\"0\"\n" +
                "                                cellspacing=\"0\"\n" +
                "                                border=\"0\"\n" +
                "                              >\n" +
                "                                <tr>\n" +
                "                                  <td\n" +
                "                                    style=\"\n" +
                "                                      padding-right: 0px;\n" +
                "                                      padding-left: 0px;\n" +
                "                                    \"\n" +
                "                                    align=\"center\"\n" +
                "                                  >\n" +
                "                                    <img\n" +
                "                                      alt=\"Image\"\n" +
                "                                      src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                      style=\"width: 30%\"\n" +
                "                                    >\n" +
                "                                    </img>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #ffffff;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_6\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 55px 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 160%\">\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 24px; line-height: 38.4px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 38.4px;\n" +
                "                                          font-size: 24px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 38.4px;\n" +
                "                                            font-size: 24px;\n" +
                "                                          \"\n" +
                "                                          >⚠️ THÔNG BÁO KHẨN CẤP ⚠️</span\n" +
                "                                        ></span\n" +
                "                                      >\n" +
                "                                    </strong>\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_9\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 5px 50px 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 18px; line-height: 28.8px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 28.8px;\n" +
                "                                          font-size: 18px;\n" +
                "                                        \"\n" +
                "                                        >" + campaign.getOrganization().getName() + " đang cần gấp nhóm " +
                "máu\n" +
                "                                        " + campaign.getBloodTypes() +"</span\n" +
                "                                      ></strong\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 18px; line-height: 28.8px\"\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 28.8px;\n" +
                "                                        font-size: 18px;\n" +
                "                                      \"\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 28.8px;\n" +
                "                                            font-size: 18px;\n" +
                "                                          \"\n" +
                "                                          >Địa điểm:\n" +
                "                                        </span></strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 28.8px;\n" +
                "                                          font-size: 18px;\n" +
                "                                        \"\n" +
                "                                        >" + campaign.getAddressDetails() + ", " + campaign.getDistrict().getPrefix() + " " + campaign.getDistrict().getName() + ", " +
                campaign.getDistrict().getProvince().getName() +
                "</span\n" +
                "                                      ></span\n" +
                "                                    >\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 28.8px;\n" +
                "                                        font-size: 18px;\n" +
                "                                      \"\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 28.8px;\n" +
                "                                          font-size: 18px;\n" +
                "                                        \"\n" +
                "                                      ></span\n" +
                "                                    ></span>\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 18px; line-height: 28.8px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 28.8px;\n" +
                "                                          font-size: 18px;\n" +
                "                                        \"\n" +
                "                                        >Thông tin chi tiết có tại:\n" +
                "                                      </span></strong\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 28.8px;\n" +
                "                                        font-size: 18px;\n" +
                "                                      \"\n" +
                "                                      ><a href = \"" + LINK + campaign.getId() + "\">Link</a></span\n" +
                "                                    ></span\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_11\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px 10px 15px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: left;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 14px; line-height: 19.6px\"\n" +
                "                                    ><em\n" +
                "                                      ><strong\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 19.6px;\n" +
                "                                            font-size: 14px;\n" +
                "                                          \"\n" +
                "                                          >-Chúng tôi rất mong được sự quan tâm\n" +
                "                                          và giúp đỡ từ cộng đồng-</span\n" +
                "                                        ></strong\n" +
                "                                      >\n" +
                "                                    </em>\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 180%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                  Copyright © 2022 Medichor\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

    public static String buildChangeCampaignEmail(User user, Campaign campaign) {
        return "<!DOCTYPE html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <style amp4email-boilerplate>\n" +
                "      body {\n" +
                "        visibility: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "    <style amp-custom>\n" +
                "      .u-row {\n" +
                "        display: flex;\n" +
                "        flex-wrap: nowrap;\n" +
                "        margin-left: 0;\n" +
                "        margin-right: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        padding-right: 0;\n" +
                "        padding-left: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col.u-col-100 {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 767px) {\n" +
                "        .u-row:not(.no-stack) {\n" +
                "          flex-wrap: wrap;\n" +
                "        }\n" +
                "        .u-row:not(.no-stack) .u-col {\n" +
                "          flex: 0 0 100%;\n" +
                "          max-width: 100%;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #ffffff;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 30px 10px;\n" +
                "        }\n" +
                "        #u_column_6.v-col-background-color {\n" +
                "          background-color: #990102;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0; padding: 0; background-color: #f9f9f9; color: #ffffff\"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div style=\"max-width: 600px; margin: 0 auto\">\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\"></div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"background-color: #ffffff; padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <table\n" +
                "                                width=\"100%\"\n" +
                "                                cellpadding=\"0\"\n" +
                "                                cellspacing=\"0\"\n" +
                "                                border=\"0\"\n" +
                "                              >\n" +
                "                                <tr>\n" +
                "                                  <td\n" +
                "                                    style=\"\n" +
                "                                      padding-right: 0px;\n" +
                "                                      padding-left: 0px;\n" +
                "                                    \"\n" +
                "                                    align=\"center\"\n" +
                "                                  >\n" +
                "                                  <img\n" +
                "                                  alt=\"Image\"\n" +
                "                                  src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                  style=\"max-width: 30%\"\n" +
                "                                >\n" +
                "                                </img>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #ffffff;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_6\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 30px 55px 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #000000;\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Kính gửi " + user.getDonor().getName() + ",\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Medichor nhận thấy " + campaign.getName() + " có sự\n" +
                "                                    thay đổi về thời gian diễn ra, việc này dẫn\n" +
                "                                    đến buổi đăng ký hiến máu của quý vị đã bị\n" +
                "                                    hủy bỏ. </span\n" +
                "                                  ><span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Medichor và tổ chức " + campaign.getOrganization().getName() + " vô\n" +
                "                                    cùng xin lỗi về sự cố lần này. Mong quý vị\n" +
                "                                    có thể thông cảm cho sự sai sót này của\n" +
                "                                    chúng tôi.</span\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Để biết thêm thông tin chi tiết cũng như\n" +
                "                                    sắp xếp một buổi đăng ký hiến máu phù hợp\n" +
                "                                    khác, quý vị hãy vào đường dẫn:\n" +
                "                                    <a href=\"" + LINK + campaign.getId() + "\">Link</a></span\n" +
                "                                  ><span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                  ></span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                   \n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #000000;\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: left;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <strong\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 14px;\n" +
                "                                        line-height: 19.6px;\n" +
                "                                      \"\n" +
                "                                      >-Cảm ơn quý vị đã tin dùng\n" +
                "                                      Medichor-</span\n" +
                "                                    ></strong\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 180%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                  Copyright © 2022 Medichor\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

    public static String buildCloseCampaignEmail(User user, Campaign campaign) {
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html\n" +
                "  xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "  xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "  xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
                ">\n" +
                "  <head>\n" +
                "    <!--[if gte mso 9]>\n" +
                "      <xml>\n" +
                "        <o:OfficeDocumentSettings>\n" +
                "          <o:AllowPNG />\n" +
                "          <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "        </o:OfficeDocumentSettings>\n" +
                "      </xml>\n" +
                "    <![endif]-->\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n" +
                "    <!--<![endif]-->\n" +
                "    <title></title>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "      @media only screen and (min-width: 620px) {\n" +
                "        .u-row {\n" +
                "          width: 600px !important;\n" +
                "        }\n" +
                "        .u-row .u-col {\n" +
                "          vertical-align: top;\n" +
                "        }\n" +
                "        .u-row .u-col-100 {\n" +
                "          width: 600px !important;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 620px) {\n" +
                "        .u-row-container {\n" +
                "          max-width: 100% !important;\n" +
                "          padding-left: 0px !important;\n" +
                "          padding-right: 0px !important;\n" +
                "        }\n" +
                "        .u-row .u-col {\n" +
                "          min-width: 320px !important;\n" +
                "          max-width: 100% !important;\n" +
                "          display: block !important;\n" +
                "        }\n" +
                "        .u-row {\n" +
                "          width: calc(100% - 40px) !important;\n" +
                "        }\n" +
                "        .u-col {\n" +
                "          width: 100% !important;\n" +
                "        }\n" +
                "        .u-col > div {\n" +
                "          margin: 0 auto;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      a[x-apple-data-detectors=\"true\"] {\n" +
                "        color: inherit !important;\n" +
                "        text-decoration: none !important;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #ffffff;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 30px 10px !important;\n" +
                "        }\n" +
                "        #u_column_6 .v-col-background-color {\n" +
                "          background-color: #990102 !important;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <link\n" +
                "      href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\"\n" +
                "      rel=\"stylesheet\"\n" +
                "      type=\"text/css\"\n" +
                "    />\n" +
                "    <!--<![endif]-->\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"\n" +
                "      margin: 0;\n" +
                "      padding: 0;\n" +
                "      -webkit-text-size-adjust: 100%;\n" +
                "      background-color: #f9f9f9;\n" +
                "      color: #ffffff;\n" +
                "    \"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        mso-table-lspace: 0pt;\n" +
                "        mso-table-rspace: 0pt;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse !important;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: transparent;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: transparent;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"height: 100%; width: 100% !important\"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: #ffffff\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: #ffffff;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"background-color: #990102;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"\n" +
                "                        background-color: #990102;\n" +
                "                        height: 100%;\n" +
                "                        width: 100% !important;\n" +
                "                      \"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 20px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <table\n" +
                "                                  width=\"100%\"\n" +
                "                                  cellpadding=\"0\"\n" +
                "                                  cellspacing=\"0\"\n" +
                "                                  border=\"0\"\n" +
                "                                >\n" +
                "                                  <tr>\n" +
                "                                    <td\n" +
                "                                      style=\"\n" +
                "                                        padding-right: 0px;\n" +
                "                                        padding-left: 0px;\n" +
                "                                      \"\n" +
                "                                      align=\"center\"\n" +
                "                                    >\n" +
                "                                    <img\n" +
                "                                    alt=\"Image\"\n" +
                "                                    src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                    style=\"max-width: 30%\"\n" +
                "                                  >\n" +
                "                                  </img>\n" +
                "                                    </td>\n" +
                "                                  </tr>\n" +
                "                                </table>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"background-color: #ffffff;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"\n" +
                "                        background-color: #ffffff;\n" +
                "                        height: 100%;\n" +
                "                        width: 100% !important;\n" +
                "                      \"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          id=\"u_content_text_6\"\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 30px 55px 10px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    color: #000000;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: center;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 160%;\n" +
                "                                      text-align: left;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                      >Kính gửi " + user.getDonor().getName() + ",\n" +
                "                                    </span>\n" +
                "                                  </p>\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 160%;\n" +
                "                                      text-align: left;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                      >Medichor nhận thấy " + campaign.getName() + " có sự\n" +
                "                                      thay đổi về thời gian diễn ra, việc này\n" +
                "                                      dẫn đến chiến dịch kết thúc sớm hơn thời\n" +
                "                                      gian đã công bố. </span\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                      >Medichor và tổ chức " + campaign.getOrganization().getName() + "\n" +
                "                                      vô cùng xin lỗi về sự cố lần này.\n" +
                "                                    </span>\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                      >Mong quý vị có thể thông cảm cho sự sai\n" +
                "                                      sót này của chúng tôi.</span\n" +
                "                                    >\n" +
                "                                  </p>\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 160%;\n" +
                "                                      text-align: left;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    <span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                      >Để biết thêm thông tin chi tiết cũng như\n" +
                "                                      sắp xếp một buổi đăng ký hiến máu phù hợp\n" +
                "                                      khác, quý vị hãy vào đường dẫn:\n" +
                "                                      <a href=\"" + OTHER_CAMPAIGNS_LINK + "\">Link</a></span\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                    ></span>\n" +
                "                                  </p>\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 160%;\n" +
                "                                      text-align: left;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                     \n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <table\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 10px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    color: #000000;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: left;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 140%;\n" +
                "                                      text-align: center;\n" +
                "                                    \"\n" +
                "                                  >\n" +
                "                                    <strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          font-size: 14px;\n" +
                "                                          line-height: 19.6px;\n" +
                "                                        \"\n" +
                "                                        >-Cảm ơn quý vị đã tin dùng\n" +
                "                                        Medichor-</span\n" +
                "                                      ></strong\n" +
                "                                    >\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div\n" +
                "              class=\"u-row-container\"\n" +
                "              style=\"padding: 0px; background-color: transparent\"\n" +
                "            >\n" +
                "              <div\n" +
                "                class=\"u-row\"\n" +
                "                style=\"\n" +
                "                  margin: 0 auto;\n" +
                "                  min-width: 320px;\n" +
                "                  max-width: 600px;\n" +
                "                  overflow-wrap: break-word;\n" +
                "                  word-wrap: break-word;\n" +
                "                  word-break: break-word;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div\n" +
                "                  style=\"\n" +
                "                    border-collapse: collapse;\n" +
                "                    display: table;\n" +
                "                    width: 100%;\n" +
                "                    height: 100%;\n" +
                "                    background-color: transparent;\n" +
                "                  \"\n" +
                "                >\n" +
                "                  <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #003399;\"><![endif]-->\n" +
                "\n" +
                "                  <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-background-color\" style=\"background-color: #990102;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100\"\n" +
                "                    style=\"\n" +
                "                      max-width: 320px;\n" +
                "                      min-width: 600px;\n" +
                "                      display: table-cell;\n" +
                "                      vertical-align: top;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div\n" +
                "                      class=\"v-col-background-color\"\n" +
                "                      style=\"\n" +
                "                        background-color: #990102;\n" +
                "                        height: 100%;\n" +
                "                        width: 100% !important;\n" +
                "                      \"\n" +
                "                    >\n" +
                "                      <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      <div\n" +
                "                        style=\"\n" +
                "                          height: 100%;\n" +
                "                          padding: 0px;\n" +
                "                          border-top: 0px solid transparent;\n" +
                "                          border-left: 0px solid transparent;\n" +
                "                          border-right: 0px solid transparent;\n" +
                "                          border-bottom: 0px solid transparent;\n" +
                "                        \"\n" +
                "                      >\n" +
                "                        <!--<![endif]-->\n" +
                "\n" +
                "                        <table\n" +
                "                          style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                          role=\"presentation\"\n" +
                "                          cellpadding=\"0\"\n" +
                "                          cellspacing=\"0\"\n" +
                "                          width=\"100%\"\n" +
                "                          border=\"0\"\n" +
                "                        >\n" +
                "                          <tbody>\n" +
                "                            <tr>\n" +
                "                              <td\n" +
                "                                class=\"v-container-padding-padding\"\n" +
                "                                style=\"\n" +
                "                                  overflow-wrap: break-word;\n" +
                "                                  word-break: break-word;\n" +
                "                                  padding: 10px;\n" +
                "                                  font-family: 'Cabin', sans-serif;\n" +
                "                                \"\n" +
                "                                align=\"left\"\n" +
                "                              >\n" +
                "                                <div\n" +
                "                                  style=\"\n" +
                "                                    color: #ffffff;\n" +
                "                                    line-height: 180%;\n" +
                "                                    text-align: center;\n" +
                "                                    word-wrap: break-word;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                    Copyright © 2022 Medichor\n" +
                "                                  </p>\n" +
                "                                </div>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </tbody>\n" +
                "                        </table>\n" +
                "\n" +
                "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                      </div>\n" +
                "                      <!--<![endif]-->\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                  <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                  <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

    public static String buildMedicalDocumentEmail(DonateRecord donateRecord) {
        return "<!DOCTYPE html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <style amp4email-boilerplate>\n" +
                "      body {\n" +
                "        visibility: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "    <style amp-custom>\n" +
                "      .u-row {\n" +
                "        display: flex;\n" +
                "        flex-wrap: nowrap;\n" +
                "        margin-left: 0;\n" +
                "        margin-right: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        padding-right: 0;\n" +
                "        padding-left: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col.u-col-100 {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 767px) {\n" +
                "        .u-row:not(.no-stack) {\n" +
                "          flex-wrap: wrap;\n" +
                "        }\n" +
                "        .u-row:not(.no-stack) .u-col {\n" +
                "          flex: 0 0 100%;\n" +
                "          max-width: 100%;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #000000;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 10px;\n" +
                "        }\n" +
                "        #u_column_6.v-col-background-color {\n" +
                "          background-color: #990102;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0; padding: 0; background-color: #f9f9f9; color: #000000\"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div style=\"max-width: 600px; margin: 0 auto\">\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\"></div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"background-color: #ffffff; padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 20px 15px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <table\n" +
                "                                width=\"100%\"\n" +
                "                                cellpadding=\"0\"\n" +
                "                                cellspacing=\"0\"\n" +
                "                                border=\"0\"\n" +
                "                              >\n" +
                "                                <tr>\n" +
                "                                  <td\n" +
                "                                    style=\"\n" +
                "                                      padding-right: 0px;\n" +
                "                                      padding-left: 0px;\n" +
                "                                    \"\n" +
                "                                    align=\"center\"\n" +
                "                                  >\n" +
                "                                  <img\n" +
                "                                  alt=\"Image\"\n" +
                "                                  src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                  style=\"max-width: 30%\"\n" +
                "                                >\n" +
                "                                </img>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_6\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 55px 5px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 25.6px;\n" +
                "                                        font-size: 16px;\n" +
                "                                      \"\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 25.6px;\n" +
                "                                          font-size: 16px;\n" +
                "                                        \"\n" +
                "                                        >Medichor xin trân trọng thông\n" +
                "                                        báo,</span\n" +
                "                                      ></span\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 25.6px;\n" +
                "                                        font-size: 16px;\n" +
                "                                      \"\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 25.6px;\n" +
                "                                          font-size: 16px;\n" +
                "                                        \"\n" +
                "                                        >Phiếu thông tin sức khỏe của quý vị tại\n" +
                "                                        " + donateRecord.getCampaign().getName() + " vào ngày " + donateRecord.getId().getRegisteredDate() +
                " " +
                "đã" +
                " " +
                "được\n" +
                "                                        hoàn thành. </span\n" +
                "                                      ></span\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 24px; line-height: 38.4px\"\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        font-size: 16px;\n" +
                "                                        line-height: 25.6px;\n" +
                "                                      \"\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 25.6px;\n" +
                "                                          font-size: 16px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 25.6px;\n" +
                "                                            font-size: 16px;\n" +
                "                                          \"\n" +
                "                                          ><span\n" +
                "                                            style=\"\n" +
                "                                              line-height: 25.6px;\n" +
                "                                              font-size: 16px;\n" +
                "                                            \"\n" +
                "                                            >Xem chi tiết tại:</span\n" +
                "                                          ></span\n" +
                "                                        > </span\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 25.6px;\n" +
                "                                          font-size: 16px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            line-height: 25.6px;\n" +
                "                                            font-size: 16px;\n" +
                "                                          \"\n" +
                "                                          ><span\n" +
                "                                            style=\"\n" +
                "                                              line-height: 25.6px;\n" +
                "                                              font-size: 16px;\n" +
                "                                            \"\n" +
                "                                          >\n" +
                "                                            <a href = \"" + MEDICAL_LINK +"\">Link</a></span\n" +
                "                                          ></span\n" +
                "                                        >\n" +
                "                                      </span> </span\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 38.4px;\n" +
                "                                        font-size: 24px;\n" +
                "                                      \"\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 38.4px;\n" +
                "                                          font-size: 24px;\n" +
                "                                        \"\n" +
                "                                      ></span\n" +
                "                                    ></span> </span\n" +
                "                                  ><span\n" +
                "                                    style=\"font-size: 24px; line-height: 38.4px\"\n" +
                "                                    ><span\n" +
                "                                      style=\"\n" +
                "                                        line-height: 38.4px;\n" +
                "                                        font-size: 24px;\n" +
                "                                      \"\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 38.4px;\n" +
                "                                          font-size: 24px;\n" +
                "                                        \"\n" +
                "                                        ><span\n" +
                "                                          style=\"\n" +
                "                                            font-size: 18px;\n" +
                "                                            line-height: 28.8px;\n" +
                "                                          \"\n" +
                "                                        ></span\n" +
                "                                      ></span>\n" +
                "                                    </span>\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: left;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 140%;\n" +
                "                                    text-align: center;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 22.4px\"\n" +
                "                                    ><em\n" +
                "                                      ><strong\n" +
                "                                        >-Cảm ơn quý vị đã tin dùng\n" +
                "                                        Medichor-</strong\n" +
                "                                      ></em\n" +
                "                                    ></span\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 180%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                  Copyright © 2022 Medichor\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done

    public static String buildCheckinCodeEmail(DonateRegistration donateRegistration) {
        return "<!DOCTYPE html>\n" +
                "<html ⚡4email data-css-strict>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\" />\n" +
                "    <style amp4email-boilerplate>\n" +
                "      body {\n" +
                "        visibility: hidden;\n" +
                "      }\n" +
                "    </style>\n" +
                "\n" +
                "    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
                "\n" +
                "    <style amp-custom>\n" +
                "      .u-row {\n" +
                "        display: flex;\n" +
                "        flex-wrap: nowrap;\n" +
                "        margin-left: 0;\n" +
                "        margin-right: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col {\n" +
                "        position: relative;\n" +
                "        width: 100%;\n" +
                "        padding-right: 0;\n" +
                "        padding-left: 0;\n" +
                "      }\n" +
                "\n" +
                "      .u-row .u-col.u-col-100 {\n" +
                "        flex: 0 0 100%;\n" +
                "        max-width: 100%;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 767px) {\n" +
                "        .u-row:not(.no-stack) {\n" +
                "          flex-wrap: wrap;\n" +
                "        }\n" +
                "        .u-row:not(.no-stack) .u-col {\n" +
                "          flex: 0 0 100%;\n" +
                "          max-width: 100%;\n" +
                "        }\n" +
                "      }\n" +
                "\n" +
                "      body {\n" +
                "        margin: 0;\n" +
                "        padding: 0;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      tr,\n" +
                "      td {\n" +
                "        vertical-align: top;\n" +
                "        border-collapse: collapse;\n" +
                "      }\n" +
                "\n" +
                "      p {\n" +
                "        margin: 0;\n" +
                "      }\n" +
                "\n" +
                "      .ie-container table,\n" +
                "      .mso-container table {\n" +
                "        table-layout: fixed;\n" +
                "      }\n" +
                "\n" +
                "      * {\n" +
                "        line-height: inherit;\n" +
                "      }\n" +
                "\n" +
                "      table,\n" +
                "      td {\n" +
                "        color: #000000;\n" +
                "      }\n" +
                "\n" +
                "      @media (max-width: 480px) {\n" +
                "        #u_content_text_12 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 10px;\n" +
                "        }\n" +
                "        #u_content_text_9 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 30px;\n" +
                "        }\n" +
                "        #u_content_text_6 .v-container-padding-padding {\n" +
                "          padding: 20px 55px 10px;\n" +
                "        }\n" +
                "        #u_content_text_3 .v-container-padding-padding {\n" +
                "          padding: 20px;\n" +
                "        }\n" +
                "        #u_column_6.v-col-background-color {\n" +
                "          background-color: #990102;\n" +
                "        }\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "\n" +
                "  <body\n" +
                "    class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0; padding: 0; background-color: #f9f9f9; color: #000000\"\n" +
                "  >\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table\n" +
                "      style=\"\n" +
                "        border-collapse: collapse;\n" +
                "        table-layout: fixed;\n" +
                "        border-spacing: 0;\n" +
                "        vertical-align: top;\n" +
                "        min-width: 320px;\n" +
                "        margin: 0 auto;\n" +
                "        background-color: #f9f9f9;\n" +
                "        width: 100%;\n" +
                "      \"\n" +
                "      cellpadding=\"0\"\n" +
                "      cellspacing=\"0\"\n" +
                "    >\n" +
                "      <tbody>\n" +
                "        <tr style=\"vertical-align: top\">\n" +
                "          <td\n" +
                "            style=\"\n" +
                "              word-break: break-word;\n" +
                "              border-collapse: collapse;\n" +
                "              vertical-align: top;\n" +
                "            \"\n" +
                "          >\n" +
                "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div style=\"max-width: 600px; margin: 0 auto\">\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\"></div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"background-color: #ffffff; padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 20px 15px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <table\n" +
                "                                width=\"100%\"\n" +
                "                                cellpadding=\"0\"\n" +
                "                                cellspacing=\"0\"\n" +
                "                                border=\"0\"\n" +
                "                              >\n" +
                "                                <tr>\n" +
                "                                  <td\n" +
                "                                    style=\"\n" +
                "                                      padding-right: 0px;\n" +
                "                                      padding-left: 0px;\n" +
                "                                    \"\n" +
                "                                    align=\"center\"\n" +
                "                                  >\n" +
                "                                  <img\n" +
                "                                  alt=\"Image\"\n" +
                "                                  src=\"https://scontent.fsgn2-6.fna.fbcdn.net/v/t1.15752-9/312431927_1875220266142805_7462039892113331592_n.png?_nc_cat=110&ccb=1-7&_nc_sid=ae9488&_nc_ohc=I_10aKFwmKwAX9oRo2o&_nc_ht=scontent.fsgn2-6.fna&oh=03_AdROK8nUSB3UDL7uaW-MKs65DN_c9zqeI2ouxkeYuFqPgw&oe=6386C2D4\"\n" +
                "                                  style=\"max-width: 30%\"\n" +
                "                                >\n" +
                "                                </img>\n" +
                "                                  </td>\n" +
                "                                </tr>\n" +
                "                              </table>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #ffffff;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_12\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 25px 55px 5px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Quý vị đã đăng ký thành công\n" +
                "                                    " + donateRegistration.getCampaign().getName() + ", Medichor xin gửi " +
                "mã" +
                " " +
                "xác\n" +
                "                                    nhận:</span\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_9\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 5px 40px 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 160%\">\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 36px; line-height: 57.6px\"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 57.6px;\n" +
                "                                          font-size: 36px;\n" +
                "                                        \"\n" +
                "                                        >" + donateRegistration.getCode() + "</span\n" +
                "                                      ></strong\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_6\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px 55px 5px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  line-height: 160%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p\n" +
                "                                  style=\"\n" +
                "                                    font-size: 14px;\n" +
                "                                    line-height: 160%;\n" +
                "                                    text-align: left;\n" +
                "                                  \"\n" +
                "                                >\n" +
                "                                  <span\n" +
                "                                    style=\"font-size: 16px; line-height: 25.6px\"\n" +
                "                                    >Mã được dùng cho việc xác nhận thông tin\n" +
                "                                    khi quý vị đến nơi hiến máu. Quý vị vui\n" +
                "                                    lòng đem theo CMND/CCCD và đến địa điểm\n" +
                "                                    đúng theo thời gian đã chọn để việc hiến máu\n" +
                "                                    được diễn ra thuận lợi.</span\n" +
                "                                  >\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "\n" +
                "                      <table\n" +
                "                        id=\"u_content_text_3\"\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px 10px 20px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #000000;\n" +
                "                                  line-height: 140%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 140%\">\n" +
                "                                  <span\n" +
                "                                    style=\"\n" +
                "                                      font-size: 14px;\n" +
                "                                      line-height: 19.6px;\n" +
                "                                      font-family: Cabin, sans-serif;\n" +
                "                                    \"\n" +
                "                                    ><strong\n" +
                "                                      ><span\n" +
                "                                        style=\"\n" +
                "                                          line-height: 19.6px;\n" +
                "                                          font-size: 14px;\n" +
                "                                        \"\n" +
                "                                        >-Cảm ơn quý vị đã tin dùng\n" +
                "                                        Medichor-</span\n" +
                "                                      ></strong\n" +
                "                                    >\n" +
                "                                  </span>\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <div style=\"padding: 0px\">\n" +
                "              <div\n" +
                "                style=\"\n" +
                "                  max-width: 600px;\n" +
                "                  margin: 0 auto;\n" +
                "                  background-color: #003399;\n" +
                "                \"\n" +
                "              >\n" +
                "                <div class=\"u-row\">\n" +
                "                  <div\n" +
                "                    id=\"u_column_6\"\n" +
                "                    class=\"u-col u-col-100 v-col-background-color\"\n" +
                "                    style=\"\n" +
                "                      display: flex;\n" +
                "                      background-color: #990102;\n" +
                "                      border-top: 0px solid transparent;\n" +
                "                      border-left: 0px solid transparent;\n" +
                "                      border-right: 0px solid transparent;\n" +
                "                      border-bottom: 0px solid transparent;\n" +
                "                    \"\n" +
                "                  >\n" +
                "                    <div style=\"width: 100%; padding: 0px\">\n" +
                "                      <table\n" +
                "                        style=\"font-family: 'Cabin', sans-serif\"\n" +
                "                        role=\"presentation\"\n" +
                "                        cellpadding=\"0\"\n" +
                "                        cellspacing=\"0\"\n" +
                "                        width=\"100%\"\n" +
                "                        border=\"0\"\n" +
                "                      >\n" +
                "                        <tbody>\n" +
                "                          <tr>\n" +
                "                            <td\n" +
                "                              class=\"v-container-padding-padding\"\n" +
                "                              style=\"\n" +
                "                                overflow-wrap: break-word;\n" +
                "                                word-break: break-word;\n" +
                "                                padding: 10px;\n" +
                "                                font-family: 'Cabin', sans-serif;\n" +
                "                              \"\n" +
                "                              align=\"left\"\n" +
                "                            >\n" +
                "                              <div\n" +
                "                                style=\"\n" +
                "                                  color: #ffffff;\n" +
                "                                  line-height: 180%;\n" +
                "                                  text-align: center;\n" +
                "                                  word-wrap: break-word;\n" +
                "                                \"\n" +
                "                              >\n" +
                "                                <p style=\"font-size: 14px; line-height: 180%\">\n" +
                "                                  Copyright © 2022 Medichor\n" +
                "                                </p>\n" +
                "                              </div>\n" +
                "                            </td>\n" +
                "                          </tr>\n" +
                "                        </tbody>\n" +
                "                      </table>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "                </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "      </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "  </body>\n" +
                "</html>\n";
    } //done
}
