package com.example.demo.controller;

import com.example.demo.service.UserUnsubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 退会コントローラ
 */
@Controller
@RequestMapping(value = "user/unsubscribe")
public class UserUnsubscribeController {
    /** 退会サービス */
    private final UserUnsubscribeService userUnsubscribeService;
    /** HTTP SESSION */
    private final HttpSession session;
    /** セッションキー(ログインユーザのアカウント) */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public UserUnsubscribeController(UserUnsubscribeService userUnsubscribeService, HttpSession session) {
        this.userUnsubscribeService = userUnsubscribeService;
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

    // TODO 退会処理未実装、ビューをリターンしているだけ
}
