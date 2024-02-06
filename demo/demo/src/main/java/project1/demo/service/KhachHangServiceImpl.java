package project1.demo.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project1.demo.entity.KhachHang;
import project1.demo.entity.Role;
import project1.demo.reponsitory.KhachHangReponsitory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KhachHangServiceImpl implements KhachHangService{
    private KhachHangReponsitory khachHangReponsitory;

    @Autowired
    public KhachHangServiceImpl(KhachHangReponsitory khachHangReponsitory) {
        this.khachHangReponsitory = khachHangReponsitory;
    }

    @Override
    public KhachHang findKhachHangByName(String name) {
        return khachHangReponsitory.findKhachHangByUserName(name);
    }

    @Override
    public KhachHang findKhachHangById(long id) {
        return this.khachHangReponsitory.findKhachHangById(id);
    }

    @Override
    public void save(KhachHang khachHang) {
        this.khachHangReponsitory.saveAndFlush(khachHang);
    }

    @Override
    public void update(KhachHang khachHang) {
        this.khachHangReponsitory.saveAndFlush(khachHang);
    }

    @Override
    public List<KhachHang> findAll() {
        return this.khachHangReponsitory.findAll();
    }

    //Xác thực khách hàng
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        KhachHang khachHang = khachHangReponsitory.findKhachHangByUserName(username);
        if(khachHang == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        } else {
            //tạo ra đối tượng trong quá trình xác thực
            return new org.springframework.security.core.userdetails.User(khachHang.getUserName(), khachHang.getPassWord(),rolesToAuthorities(khachHang.getRoles()));
        }
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
//    @PostConstruct
//    public void insertUser(){
//        KhachHang user1 =new KhachHang();
//        user1.setUserName("Gia Phong");
//        user1.setPassWord("{bcrypt}$2a$12$WloGrRm6ZXn6UE/4RR9KeOw4fQ0RjuQttqoGu8aCnPqHkM3lP5.Ta");
//
//        Role role1 = new Role();
//        role1.setName("ROLE_ADMIN");
//
//        Collection<Role> roles = new ArrayList<>();
//        roles.add(role1);
//        user1.setRoles(roles);
//
//        khachHangReponsitory.save(user1);
//    }
}

