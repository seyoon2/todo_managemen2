package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserUnsubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 退会コントローラ
 */
@Controller
@RequestMapping(value = "/user/unsubscribe")
public class UserUnsubscribeController {
    /**
     * 退会サービス
     */
    private final UserUnsubscribeService service;
    /**
     * HTTP SESSION
     */
    private final HttpSession session;
    /**
     * セッションキー(ログインユーザのアカウント)
     */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public UserUnsubscribeController(UserUnsubscribeService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }

    /**
     * 退会-確認
     *
     * @return Path
     */
    @RequestMapping(value = "/confirm")
    public String unsubscribeInit() {
        return "user/userUnsubscribeConfirmForm";
    }

    /**
     * 退会-完了
     *
     * @return Path
     */
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    String unsubscribeComplete() {
        User user = (User) session.getAttribute(SESSION_FORM_ID);
        service.delete(user);
        session.invalidate();
        return "user/userRegisterForm";
    }
}
