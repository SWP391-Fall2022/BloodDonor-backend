package swp.medichor.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import swp.medichor.model.Campaign;

public class Validator {

    public static boolean testEmail(String email) {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean testPhoneNumber(String phone) {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean testName(String name) {
        String regex = "^[a-zA-Z" +
                "ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ0-9\\s_]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean canCampaignRegistered(Campaign campaign, LocalDate registerDate) {
        return campaign.getStatus() == true // not deleted
                && !registerDate.isBefore(LocalDate.now())
                && !registerDate.isBefore(campaign.getStartDate())
                && (campaign.getEndDate() == null
                || (!registerDate.isAfter(campaign.getEndDate())
                && !campaign.getStartDate().isAfter(campaign.getEndDate()) // not closed
                && !campaign.getEndDate().isBefore(LocalDate.now())));
    }
}
