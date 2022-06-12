package com.jisu.jpashopSecurity.controller;

import com.jisu.jpashopSecurity.domain.Member;
import com.jisu.jpashopSecurity.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        System.out.println("form = " + form.getPassword());
        System.out.println("form = " + form.getEmail());
        System.out.println("form = " + form.getUsername());

        Member member = new Member(form.getUsername(), form.getPassword(), form.getEmail());
        memberService.join(member);

        return "redirect:/";

    }
}
