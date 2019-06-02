package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.service.PostListService;
import com.example.demo.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * トップ画面、投稿検索画面
 */
@Controller
public class TopController {

    /**
     * TopService
     */
    private final TopService topService;

    /**
     * 投稿一覧表示サービス
     */
    private final PostListService postListService;
    /**
     * HTTPセッション
     */
    private final HttpSession session;
    /**
     * セッションキー(ログインユーザのアカウント)
     */
    private static final String SESSION_FORM_ID = "account";

    @Autowired
    public TopController(TopService topService, PostListService postListService, HttpSession session) {
        this.topService = topService;
        this.postListService = postListService;
        this.session = session;
    }

    /**
     * ログイン成功時処理。
     *
     * @return Path
     */
    @RequestMapping(value = "/top/loginSuccess")
    public String loginSuccess() {
        return "redirect:/top";
    }

    /**
     * トップ画面表示。
     *
     * @param user  認証されたアカウント
     * @param post  セッションユーザーの投稿
     * @param model モデル
     * @return Path
     */
    @RequestMapping(value = "/top")
    public String init(@AuthenticationPrincipal User user, Post post, Model model) {
        // セッションユーザーの取得
        User sessionUser = topService.getAccountById(user.getId());
        // 初回のアクセスなら、アカウントを検索してセッションに格納する
        if (Objects.isNull(session.getAttribute(SESSION_FORM_ID))) {
            session.setAttribute(SESSION_FORM_ID, sessionUser);
        }
        // セッションユーザー名の表示
        model.addAttribute("SessionUserName", sessionUser.getUsername());
        // セッションユーザーの投稿取得
        List<Post> postList = postListService.getPostList(sessionUser);
        // 投稿一覧の表示
        model.addAttribute("PostList", postList);
        return "top/topForm";
    }
}
