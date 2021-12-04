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
    AllPlans("all_plans"),
    PlanWiseLevel("plan_wise_level"),
    UserSubscription("user_level_subscription"),
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
* menu
* Home
* Qank
* Classes
* Test
* Daily Hunt
*
* QBank
* Iyear 2Year Subject  --  previous load
* add new screen before -- based on levels subject --> hit plan level api (default plan A list id 1)
* Display plan a levels --> and remove add lock symbole --> please subscribe level.
* */