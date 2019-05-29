package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.form.UserRegisterForm;
import com.example.demo.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user/register")
public class UserRegisterController {

    /**
     * ユーザー登録サービス
     */
    private final UserRegisterService service;

    @Autowired
    public UserRegisterController(UserRegisterService userRegisterService) {
        this.service = userRegisterService;
    }

    /**
     * ユーザー登録-初期表示。
     *
     * @param userRegisterForm ユーザー登録フォーム
     * @return Path
     */
    @RequestMapping(value = "/init")
    public String registerInit(@ModelAttribute UserRegisterForm userRegisterForm) {
        return "user/userRegisterForm";
    }

    /**
     * ユーザー登録-確認画面表示。
     *
     * @param userRegisterForm 精査済みフォーム
     * @param bindingResult    精査結果
     * @param model            モデル
     * @return Path
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    String registerConfirm(@ModelAttribute @Validated UserRegisterForm userRegisterForm, BindingResult bindingResult, Model model) {
        // BeanValidationのエラー確認
        if (bindingResult.hasErrors()) {
            return "user/userRegisterForm";
        }
        // アカウントIDの重複精査
        if (service.isExistsAccountId(userRegisterForm.getAccountId())) {
            bindingResult.rejectValue("accountId", "validation.duplicate", new String[]{"アカウントID"}, "default message");
            return "user/userRegisterForm";
        }
        return "user/userRegisterConfirmForm";
    }

    /**
     * ユーザー登録-完了画面表示。
     *
     * @param userRegisterForm 精査済みフォーム
     * @param bindingResult    精査結果
     * @return Path
     */
    @RequestMapping(value = "/do", params = "register", method = RequestMethod.POST)
    String registerComplete(@ModelAttribute @Validated UserRegisterForm userRegisterForm, BindingResult bindingResult) {
        // BeanValidationのエラー確認
        if (bindingResult.hasErrors()) {
            return "user/userRegisterForm";
        }
        // 登録するアカウントの作成
        User user = new User();
        user.setAccountId(userRegisterForm.getAccountId());
        // ユーザーの登録
        service.register(user, userRegisterForm.getPassword());
        return "user/userRegisterCompleteForm";
    }

    /**
     * ユーザー登録-入力画面に戻る。
     *
     * @param userRegisterForm ユーザー登録フォーム。
     * @return Path
     */
    @RequestMapping(value = "/do", params = "registerBack", method = RequestMethod.POST)
    String registerBack(@ModelAttribute UserRegisterForm userRegisterForm) {
        return "user/userRegisterForm";
    }
}
