package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.form.PostRegisterForm;
import com.example.demo.service.PostSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/post/search")
public class PostSearchController {

    /**
     * 投稿検索サービス
     */
    private final PostSearchService postSearchService;

    /**
     * メッセージソース
     */
    private final MessageSource messageSource;

    @Autowired
    public PostSearchController(PostSearchService postSearchService, MessageSource messageSource) {
        this.postSearchService = postSearchService;
        this.messageSource = messageSource;
    }

    /**
     * 投稿検索-初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = "/init")
    public String searchInit(@ModelAttribute PostRegisterForm form, Model model) {
        User sessionUser = postSearchService.getSesionUser();
        List<Post> postList = postSearchService.findPostById(sessionUser.getId());
        model.addAttribute("PostList", postList);
        return "post/postSearchForm";
    }

    /**
     * 投稿検索-結果表示
     * TODO どこかで検索処理が止まっているみたい、うまく検索ができていない
     *
     * @param postRegisterForm
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public String search(@ModelAttribute @Validated PostRegisterForm postRegisterForm, BindingResult bindingResult, Model model) {
        // BeanValidationのエラー確認
        if (bindingResult.hasErrors()) {
            // forwardさせるとエラー情報が消えるので、メソッド呼び出しで処理する。
            // TodoRegisterと同様に、RedirectAttributesに情報を詰めてリダイレクトし、先で取り出してModel.addattributeさせるのでもOK。
            return this.searchInit(postRegisterForm, model);
        }

        // 検索処理
        List<Post> postList = postSearchService.findPost(postRegisterForm);
        if (Objects.isNull(postList) || postList.isEmpty()) {
            // 結果０件ならエラー表示
            bindingResult.reject("validation.noSearchResult", "default message");
            return this.searchInit(postRegisterForm, model);
        }

        // 検索結果の格納
        model.addAttribute("PostList", postList);
        return "forward:/post/search/init";
    }
}
