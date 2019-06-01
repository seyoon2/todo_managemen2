package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.form.UserUpdatePasswordForm;
import com.example.demo.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * パスワード変更コントローラ
 */

@Controller
@RequestMapping(value = "/user/password/")
public class UserUpdatePasswordController {

    /**
     * パスワード更新サービス
     */
    private UserUpdateService service;
    /**
     * HTTPセッション
     */
    private final HttpSession session;
    /**
     * セッションキー(ログインユーザのアカウント)
     */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public UserUpdatePasswordController(UserUpdateService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }

    /**
     * パスワード変更初期画面表示
     *
     * @param form
     * @return Path
     */
    @RequestMapping(value = "/init")
    public String registerInit(@ModelAttribute UserUpdatePasswordForm form) {
        return "user/userUpdatePasswordForm";
    }

    /**
     * パスワード変更完了
     *
     * @param form
     * @param bindingResult
     * @return Path
     */
    @RequestMapping(value = "/complete", method = RequestMethod.POST)
    public String registerComplete(@ModelAttribute @Validated UserUpdatePasswordForm form, BindingResult bindingResult) {
        // BeanValidation
        if (bindingResult.hasErrors()) {
            return "user/userUpdatePasswordForm";
        }
        User user = (User) session.getAttribute(SESSION_FORM_ID);
        boolean isValid = service.validCurrentPassword(user.getId(), form.getCurrentPassword());
        if (!isValid) {
            bindingResult.reject("validation.current.password", "default message");
            return "user/userUpdatePasswordForm";
        }
        service.updatePassword(user.getId(), form.getNewPassword());
        return "top/topForm";
    }
}
