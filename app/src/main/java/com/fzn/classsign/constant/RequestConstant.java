package com.fzn.classsign.constant;

public interface RequestConstant {
    interface SERVER{
        public static final String SERVER_URL="http://linaxhua.cn:7001/api/";
    }
    interface API{
        public static final String LOGIN="user/login";
        public static final String REFRESH="user/refresh";
        public static final String SIGN_UP="user/signUp";


        public static final String SEND_LOGIN_CODE="sms/sendLoginCode";
        public static final String SEND_REGISTER_CODE="sms/sendRegisterCode";


        public static final String TEACHER_CLASS_CREATE="teacher/class/create";
        public static final String TEACHER_CLASS_UPDATE="teacher/class/update";
        public static final String TEACHER_CLASS_LIST="teacher/class/list";
        public static final String TEACHER_SIGN_CREATE="teacher/sign/create";
        public static final String TEACHER_SIGN_STOP="teacher/sign/stop";
        public static final String TEACHER_SIGN_UPDATE="teacher/sign/update";
        public static final String TEACHER_SIGN_GET_CURRENT_SIGN_TOTAL="teacher/sign/getCurrentSignTotal";


        public static final String STU_CLASS_ADD="stu/class/add";
        public static final String STU_CLASS_EXIT="stu/class/exit";
        public static final String STU_SIGN_LIST="stu/class/list";
        public static final String STU_SIGN_SIGNIN="stu/sign/signin";
        public static final String STU_SIGN_LIST_SIGNIN_RECORD="stu/sign/listSignInRecord";


        public static final String CLASS_GET="common/class/get";
        public static final String CLASS_LISTSTU="common/class/listStu";
        public static final String SIGN_LIST_SIGN_STATISTICS="common/sign/listSignStatistics";
        public static final String SIGN_LIST_SIGNIN="common/sign/listSignIn";
        public static final String UPDATE_PASSWORD="common/user/updatePassword";
        public static final String UPDATE_USER_BASEINFO="common/user/updateUserBaseInfo";
        public static final String GET_USER_BASEINFO="common/user/getUserBaseInfo";
    }


    interface URL {
        public static final String LOGIN=SERVER.SERVER_URL+API.LOGIN;
        public static final String REFRESH=SERVER.SERVER_URL+API.REFRESH;
        public static final String SIGN_UP=SERVER.SERVER_URL+API.SIGN_UP;


        public static final String SEND_LOGIN_CODE=SERVER.SERVER_URL+API.SEND_LOGIN_CODE;
        public static final String SEND_REGISTER_CODE=SERVER.SERVER_URL+API.SEND_REGISTER_CODE;


        public static final String TEACHER_CLASS_CREATE=SERVER.SERVER_URL+API.TEACHER_CLASS_CREATE;
        public static final String TEACHER_CLASS_UPDATE=SERVER.SERVER_URL+API.TEACHER_CLASS_UPDATE;
        public static final String TEACHER_CLASS_LIST=SERVER.SERVER_URL+API.TEACHER_CLASS_LIST;
        public static final String TEACHER_SIGN_CREATE=SERVER.SERVER_URL+API.TEACHER_SIGN_CREATE;
        public static final String TEACHER_SIGN_STOP=SERVER.SERVER_URL+API.TEACHER_SIGN_STOP;
        public static final String TEACHER_SIGN_UPDATE=SERVER.SERVER_URL+API.TEACHER_SIGN_UPDATE;
        public static final String TEACHER_SIGN_GET_CURRENT_SIGN_TOTAL=SERVER.SERVER_URL+API.TEACHER_SIGN_GET_CURRENT_SIGN_TOTAL;


        public static final String STU_CLASS_ADD=SERVER.SERVER_URL+API.STU_CLASS_ADD;
        public static final String STU_CLASS_EXIT=SERVER.SERVER_URL+API.STU_CLASS_EXIT;
        public static final String STU_SIGN_LIST=SERVER.SERVER_URL+API.STU_SIGN_LIST;
        public static final String STU_SIGN_SIGNIN=SERVER.SERVER_URL+API.STU_SIGN_SIGNIN;
        public static final String STU_SIGN_LIST_SIGNIN_RECORD=SERVER.SERVER_URL+API.STU_SIGN_LIST_SIGNIN_RECORD;


        public static final String CLASS_GET=SERVER.SERVER_URL+API.CLASS_GET;
        public static final String CLASS_LISTSTU=SERVER.SERVER_URL+API.CLASS_LISTSTU;
        public static final String SIGN_LIST_SIGN_STATISTICS=SERVER.SERVER_URL+API.SIGN_LIST_SIGN_STATISTICS;
        public static final String SIGN_LIST_SIGNIN=SERVER.SERVER_URL+API.SIGN_LIST_SIGNIN;
        public static final String UPDATE_PASSWORD=SERVER.SERVER_URL+API.UPDATE_PASSWORD;
        public static final String UPDATE_USER_BASEINFO=SERVER.SERVER_URL+API.UPDATE_USER_BASEINFO;
        public static final String GET_USER_BASEINFO=SERVER.SERVER_URL+API.GET_USER_BASEINFO;
    }


}
