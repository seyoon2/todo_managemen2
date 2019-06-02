package com.example.demo.controller;

import com.example.demo.service.PostDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;
import java.util.Objects;


/**
 * 投稿削除コントローラ
 */
@Controller
@RequestMapping(value = "/post/delete")
public class PostDeleteController {

    /**
     * 投稿削除サービス
     */
    private PostDeleteService service;
    /**
     * メッセージソース
     * message.properties からメッセージを簡単に取得できる
     */
    private MessageSource messageSource;

    @Autowired
    public PostDeleteController(PostDeleteService service, MessageSource messageSource) {
        this.service = service;
        this.messageSource = messageSource;
    }

    /**
     * 削除実行
     *
     * @param postId
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String delete(@RequestParam String postId, Model model) {
        // 遷移元の画面から、削除対象の投稿のIDが渡ってこなければ、エラーとする
        if (Objects.isNull(postId) || StringUtils.isEmpty(postId)) {
            model.addAttribute("errorMsg", messageSource.getMessage("validation.invalid.screen.transition", null, Locale.JAPAN));
            // TODO エラー画面未実装
            return "common/commonError";
        }
        // 投稿が存在しなければ、エラー表示。
        if (!service.isExistsById(Integer.parseInt(postId))) {
            model.addAttribute("errorMsg", messageSource.getMessage("validation.incorrect.specification.todo", null, Locale.JAPAN));
            return "common/commonError";
        }
        // 削除処理
        service.delete(Integer.parseInt(postId));
        // 残っている投稿を見るためにリダイレクトさせる
        return "redirect:/top";
    }
}
