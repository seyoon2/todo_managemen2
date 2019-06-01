package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.form.UserUpdateForm;
import com.example.demo.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * ユーザー情報変更コントローラ
 */

@Controller
@RequestMapping(value = "/user/update/")
public class UserUpdateController {
    /**
     * アカウント情報更新サービス
     */
    private final UserUpdateService service;
    /**
     * HTTPセッション
     */
    private final HttpSession session;
    /**
     * セッションキー(ログインユーザのアカウント)
     */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public UserUpdateController(UserUpdateService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }

    /**
     * アカウント情報更新-初期表示
     *
     * @param model
     * @return Path
     */
    @RequestMapping(value = "/init")
    public String updateInit(Model model) {
        User user = (User) session.getAttribute(SESSION_FORM_ID);
        User targetUser = service.getAccountById(user.getId());
        model.addAttribute("userUpdateForm", targetUser);
        return "user/userUpdateForm";
    }

    /**
     * アカウント情報更新-確認
     *
     * @param userUpdateForm
     * @param bindingResult
     * @param model
     * @return Path
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String updateConfirm(@ModelAttribute @Validated UserUpdateForm userUpdateForm, BindingResult bindingResult, Model model) {
        // BeanValidationのエラー確認
        if (bindingResult.hasErrors()) {
            return "user/userUpdateForm";
        }
        User user = (User) session.getAttribute(SESSION_FORM_ID);
        User targetUser = service.getAccountById(user.getId());

        // 更新有無チェック。何も更新されていなければエラーとする。
        if (service.isNoChange(userUpdateForm, targetUser)) {
            bindingResult.reject("validation.noChange", "default message");
            return "user/userUpdateForm";
        }
        // アカウントIDの重複精査
        String accountId = userUpdateForm.getAccountId();
        if (!accountId.equals(targetUser.getAccountId())) {
            if (service.isExistsAccountId(accountId)) {
                bindingResult.rejectValue("accountId", "validation.duplicate", new String[]{"アカウントID"}, "default message");
                return "user/userUpdateForm";
            }
        }
        return "user/userUpdateConfirmForm";
    }

    /**
     * アカウント情報更新-完了
     *
     * @return
     */
    @RequestMapping(value = "/do", params = "complete", method = RequestMethod.POST)
    public String updateComplete(@ModelAttribute @Validated UserUpdateForm userUpdateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/userUpdateForm";
        }
        User user = (User) session.getAttribute(SESSION_FORM_ID);
        User targetUser = service.getAccountById(user.getId());

        // 更新有無のチェック
        if (service.isNoChange(userUpdateForm, targetUser)) {
            bindingResult.reject("validation.noChange", "default message");
            return "user/userUpdateForm";
        }
        // アカウントID重複精査
        String accountId = userUpdateForm.getAccountId();
        if (!accountId.equals(targetUser.getAccountId())) {
            if (service.isExistsAccountId(accountId)) {
                bindingResult.rejectValue("accountId", "Validation.duplicate", new String[]{"アカウントID"}, "default message");
                return "user/userUpdateForm";
            }
        }
        // 更新用のアカウント作成
        targetUser.setAccountId(userUpdateForm.getAccountId());
        // 更新処理
        service.updateAccountById(targetUser);
        // セッション情報更新
        User sessionUser = service.getAccountById(targetUser.getId());
        session.setAttribute(SESSION_FORM_ID, sessionUser);
        // トップ画面へ
        return "top/topForm";
    }

    @RequestMapping(value = "/do", params = "back", method = RequestMethod.POST)
    public String updateBack(@ModelAttribute UserUpdateForm userUpdateForm) {
        return "user/userUpdateForm";
    }

}
