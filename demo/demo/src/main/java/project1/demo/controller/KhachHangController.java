package project1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project1.demo.entity.KhachHang;
import project1.demo.service.KhachHangService;

import java.util.List;

@Controller
@RequestMapping("khachhang")
public class KhachHangController {
    private KhachHangService khachHangService;

    @Autowired
    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

//    @GetMapping("/list")
//    public String findAll(Model model) {
//        List<KhachHang> khachHangs = this.khachHangService.findAll();
//        model.addAttribute("khachhangs",khachHangs);
//        return "index";
//    }

    @GetMapping("/get-info")
    private String getId(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();
        KhachHang khachHang = this.khachHangService.findKhachHangByName(username);
        model.addAttribute("khachhang",khachHang);
        return "index";
    }

    @GetMapping("/account")
    public String updateById(@RequestParam("id") long id, Model model) {
        KhachHang khachHang = khachHangService.findKhachHangById(id);
        khachHangService.update(khachHang);
        model.addAttribute("khachhang",khachHang);
        return "khach-hang-form";
    }

    @PostMapping("/process")
    public String process(@ModelAttribute("khachhang") KhachHang khachHang) {
        this.khachHangService.save(khachHang);
        return "index";
    }

}
