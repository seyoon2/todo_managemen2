package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.form.PostRegisterForm;
import com.example.demo.service.PostRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 投稿登録コントローラ。
 */
@Controller
@RequestMapping(value = "/post/register")
public class PostRegisterController {

    /**
     * 投稿登録サービス
     */
    private final PostRegisterService service;
    /**
     * HTTPセッション
     */
    private final HttpSession session;
    /**
     * セッションキー(ログインユーザのアカウント)
     */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public PostRegisterController(PostRegisterService service, HttpSession session) {
        this.service = service;
        this.session = session;
    }

    /**
     * 投稿登録-初期表示。
     *
     * @param model モデル
     * @return Path
     */
    @RequestMapping(value = "/init")
    public String registerInit(@ModelAttribute PostRegisterForm postRegisterForm, Model model) {
        return "post/postRegisterForm";
    }

    /**
     * 投稿確認
     *
     * @param postRegisterForm, bindingResult, model
     * @return
     */
    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    public String registerConfirm(@ModelAttribute @Validated PostRegisterForm postRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // BeanValidationエラー確認
        if (bindingResult.hasErrors()) {
            return redirectToInit(postRegisterForm, bindingResult, redirectAttributes);
        }
        return "post/postRegisterConfirmForm";
    }

    /**
     * 投稿戻る
     *
     * @param postRegisterForm
     * @return Path
     */
    @RequestMapping(value = "/do", params = "registerBack", method = RequestMethod.POST)
    public String registerBack(PostRegisterForm postRegisterForm) {
        return "post/postRegisterForm";
    }

    /**
     * 投稿完了
     *
     * @param postRegisterForm
     * @return Path
     */
    @RequestMapping(value = "/do", params = "register", method = RequestMethod.POST)
    public String registerComplete(PostRegisterForm postRegisterForm) {

        // ログインユーザーを取得
        User user = (User) session.getAttribute(SESSION_FORM_ID);

        Post post = new Post();
        post.setDetail(postRegisterForm.getDetail());
        post.setTitle(postRegisterForm.getTitle());
        // ログインユーザーのIDをフィールドへセット
        post.setPublisherId(user.getId());
        service.register(post);
        return "post/postRegisterComplete";
    }

    /**
     * エラー時のリダイレクト処理。
     *
     * @param postRegisterForm   フォーム
     * @param bindingResult      精査結果
     * @param redirectAttributes redirectAttributes
     * @return リダイレクトURL
     */
    private String redirectToInit(@Validated PostRegisterForm postRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // フォームをリダイレクト先に引き継ぐ
        redirectAttributes.addFlashAttribute("postRegisterForm", postRegisterForm);
        // エラー情報をリダイレクト先に引き継ぐ
        redirectAttributes.addFlashAttribute("errors", bindingResult);
        return "redirect:/post/register/init";
    }
}