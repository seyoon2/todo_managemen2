package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequestMapping("/top")
public class TopController {

    /** TopService */
    private final TopService service;
    /** HTTPセッション */
    private final HttpSession session;
    /** セッションキー(ログインユーザのアカウント) */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public TopController(TopService topService, HttpSession session) {
        this.service = topService;
        this.session = session;
    }

    /**
     * ログイン成功時処理。
     *
     * @return Path
     */
    @RequestMapping(value = "loginSuccess")
    public String loginSuccess() {
        return "redirect:/top";
    }

    /**
     * トップ画面表示。
     *
     * @param user 認証されたアカウント
     * @param model モデル
     * @return Path
     */
    @RequestMapping(value = "")
    public String init(@AuthenticationPrincipal User user, Model model) {
        // 初回のアクセスなら、アカウントを検索してセッションに格納する
        if (Objects.isNull(session.getAttribute(SESSION_FORM_ID))) {
            User sessionUser = service.getAccountById(user.getId());
            session.setAttribute(SESSION_FORM_ID, sessionUser);
        }
        return "top/topForm";
    }
}
