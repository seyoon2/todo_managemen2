package com.example.demo.controller;

import com.example.demo.service.PostDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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

    // TODO コントローラの実装(存在の有無をチェックなどする)

//    @RequestMapping(value = "", method = RequestMethod.POST)
//    public delete(@RequestParam String postId, Model model) {
//
//    }
}
