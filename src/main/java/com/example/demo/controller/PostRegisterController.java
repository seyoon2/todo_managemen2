package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.form.PostRegisterForm;
import com.example.demo.service.PostRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 投稿登録コントローラ。
 */
@Controller
@RequestMapping(value = "/post/register")
public class PostRegisterController {

    /** 投稿登録サービス */
    private final PostRegisterService service;
    /** フォーム名 */
    private static final String FORM_NAME = "postRegisterForm";

    @Autowired
    public PostRegisterController(PostRegisterService service) {
        this.service = service;
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

//    /**
//     * 投稿登録-確認画面表示。
//     *
//     * @param todoRegisterForm 精査済みフォーム
//     * @param bindingResult    精査結果
//     * @param model            モデル
//     * @param redirectAttributes redirectAttributes
//     * @return Path
//     */
//    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
//    public String registerConfirm(@ModelAttribute @Validated TodoRegisterForm todoRegisterForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
//        // BeanValidationのエラー確認
//        if (bindingResult.hasErrors()) {
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//
//        // 日付の前後関係精査
//        if (service.isValidDate(todoRegisterForm.getStartDate(), todoRegisterForm.getEndDate())) {
//            bindingResult.reject("validation.invalidDate", "default message");
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//
//        // 選択されたIDに紐づくAccountを取得する
//        Optional<Account> issuePersonAccount = service.findAccountById(Integer.parseInt(todoRegisterForm.getIssuePersonId()));
//        Optional<Account> inChargeAccount = service.findAccountById(Integer.parseInt(todoRegisterForm.getPersonInChargeId()));
//        // バックグラウンドで削除されていたら、エラーとする
//        if (!issuePersonAccount.isPresent()){
//            bindingResult.reject("validation.invalidAccount", new String[]{"起票者"}, "default message");
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//        if (!inChargeAccount.isPresent()){
//            bindingResult.reject("validation.invalidAccount", new String[]{"担当者"}, "default message");
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//
//        // 確認画面に表示する氏名をセットする
//        model.addAttribute("issuePersonName",issuePersonAccount.get().getName());
//        model.addAttribute("personInChargeName",inChargeAccount.get().getName());
//        // 確認画面に表示するステータス、優先度をセットする
//        model.addAttribute("statusName", codeValue.getStatus().getStatus().get(todoRegisterForm.getStatus()));
//        model.addAttribute("priorityName", codeValue.getPriority().getPriority().get(todoRegisterForm.getPriority()));
//
//        return "todo/todoRegisterConfirmForm";
//    }
//
//    /**
//     * 投稿登録-完了画面表示。
//     *
//     * @param todoRegisterForm 精査済みフォーム
//     * @param bindingResult    精査結果
//     * @param redirectAttributes redirectAttributes
//     * @return Path
//     */
//    @RequestMapping(value = "/do", params = "register", method = RequestMethod.POST)
//    public String registerComplete(@ModelAttribute @Validated TodoRegisterForm todoRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        // BeanValidationのエラー確認
//        if (bindingResult.hasErrors()) {
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//
//        // 日付の有効範囲精査
//        if (service.isValidDate(todoRegisterForm.getStartDate(), todoRegisterForm.getEndDate())) {
//            bindingResult.reject("validation.invalidDate", "default message");
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//
//        // 選択されたIDに紐づくAccountを取得する
//        Optional<Account> issuePersonAccount = service.findAccountById(Integer.parseInt(todoRegisterForm.getIssuePersonId()));
//        Optional<Account> inChargeAccount = service.findAccountById(Integer.parseInt(todoRegisterForm.getPersonInChargeId()));
//        // バックグラウンドで削除されていたら、エラーとする
//        if (!issuePersonAccount.isPresent()){
//            bindingResult.reject("validation.invalidAccount", new String[]{"起票者"}, "default message");
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//        if (!inChargeAccount.isPresent()){
//            bindingResult.reject("validation.invalidAccount", new String[]{"担当者"}, "default message");
//            return redirectToInit(todoRegisterForm, bindingResult, redirectAttributes);
//        }
//
//        // 登録するTODOの作成
//        Todo todo = new Todo();
//        todo.setTitle(todoRegisterForm.getTitle());
//        todo.setDetail(todoRegisterForm.getDetail());
//        todo.setRemarks(todoRegisterForm.getRemarks());
//        todo.setStartDate(todoRegisterForm.getStartDate());
//        todo.setEndDate(todoRegisterForm.getEndDate());
//        todo.setIssuePersonId(Integer.parseInt(todoRegisterForm.getIssuePersonId()));
//        todo.setPersonInChargeId(Integer.parseInt(todoRegisterForm.getPersonInChargeId()));
//        todo.setStatus(todoRegisterForm.getStatus());
//        todo.setPriority(todoRegisterForm.getPriority());
//        // 登録処理
//        service.register(todo);
//
//        return "todo/todoRegisterCompleteForm";
//    }
//
//    /**
//     * 投稿登録-入力画面に戻る。
//     *
//     * @param todoRegisterForm TODO登録フォーム。
//     * @return Path
//     */
//    @RequestMapping(value = "/do", params = "registerBack", method = RequestMethod.POST)
//    public String registerBack(TodoRegisterForm todoRegisterForm, RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("todoRegisterForm", todoRegisterForm);
//        return "redirect:/todo/register/init";
//    }
//
//    /**
//     * エラー時のリダイレクト処理。
//     *
//     * @param todoRegisterForm フォーム
//     * @param bindingResult 精査結果
//     * @param redirectAttributes redirectAttributes
//     * @return リダイレクトURL
//     */
//    private String redirectToInit(@Validated TodoRegisterForm todoRegisterForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
//        // TODOのフォームをリダイレクト先に引き継ぐ
//        redirectAttributes.addFlashAttribute("todoRegisterForm", todoRegisterForm);
//        // エラー情報をリダイレクト先に引き継ぐ
//        redirectAttributes.addFlashAttribute("errors", bindingResult);
//        return "redirect:/todo/register/init";
//    }

}