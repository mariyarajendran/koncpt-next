package app.krita.koncpt.utils;

public enum EnumApiAction {
    action("action"),
    Registration("registration"),
    SendOtp("send_otp"),
    VerifyOtp("verify_otp"),
    ResendOtp("resend_otp"),
    states("states"),
    HomeScreen("home_screen"),
    SearchHome("search_home"),
    CheckEmail("check_email"),
    AllSubject("qbank_subject"),
    Topics("qbank_subject_topics"),
    QbankModuleQuestion("qbank_module_question"),
    ProfileDetail("profile_detail");

    private String strValue;

    public String getValue() {
        return strValue;
    }

    EnumApiAction() {

    }

    EnumApiAction(String value) {
        this.strValue = value;
    }


}
/*
* static plan 4
* plan A only qbank  -- every single level add pay button, plan heading need to add with verify otp
* level status = 1 = subscribed 0 not purchase, sub start date = level status = 1 (start and end date available), validitity and type
* button need to dim if paid
* plan B only video
* plan C = A and B
* plan D = C + Live video
* */