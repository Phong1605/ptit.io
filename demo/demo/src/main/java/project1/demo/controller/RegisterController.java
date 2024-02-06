package project1.demo.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project1.demo.entity.KhachHang;
import project1.demo.entity.Role;
import project1.demo.reponsitory.RoleReponsitory;
import project1.demo.service.KhachHangService;
import project1.demo.web.KhachHangRegister;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private KhachHangService khachHangService;
    private RoleReponsitory roleReponsitory;

    @Autowired
    public RegisterController(KhachHangService khachHangService, RoleReponsitory roleReponsitory) {
        this.khachHangService = khachHangService;
        this.roleReponsitory = roleReponsitory;
    }

    @GetMapping("/showRegisterForm")
    public String register(Model model) {
        KhachHang khachHang = new KhachHang();
        model.addAttribute("khachHangRegister",khachHang);
        return "register-form";
    }

    //Làm sạch dữ liệu
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @PostMapping("/process")
    public String process(@Valid @ModelAttribute("khachHangRegister")KhachHangRegister khachHangRegister,
                          BindingResult bindingResult,
                          Model model,
                          HttpSession httpSession
                          ) {
        String userName = khachHangRegister.getUserName();
        if(bindingResult.hasErrors()) {
            return "register-form";
        }

        KhachHang khachHang = this.khachHangService.findKhachHangByName(userName);
        if(khachHang != null) {
            model.addAttribute("khachHangRegister",new KhachHangRegister());
            model.addAttribute("my_error","Tài khoản đã tồn tại,vui lòng chọn tài khoản khác");
            return "register-form";
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        KhachHang khachHang1 = new KhachHang();
        khachHang1.setUserName(khachHangRegister.getUserName());
        khachHang1.setPassWord(bCryptPasswordEncoder.encode(khachHangRegister.getPassWord()));
        khachHang1.setHoDem(khachHangRegister.getHoDem());
        khachHang1.setTen(khachHangRegister.getTen());
        khachHang1.setSoDienThoai(khachHangRegister.getSoDienThoai());
        khachHang1.setNgaySinh(khachHangRegister.getNgaySinh());
        khachHang1.setEmail(khachHangRegister.getEmail());

        Role defaultRole = this.roleReponsitory.findByName("ROLE_USER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(defaultRole);
        khachHang1.setRoles(roles);
        this.khachHangService.save(khachHang1);

        httpSession.setAttribute("myuser",khachHang1);
        return "confirmation";
    }
}
