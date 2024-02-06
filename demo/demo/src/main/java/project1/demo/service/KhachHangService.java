package project1.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import project1.demo.entity.KhachHang;

import java.util.List;

public interface KhachHangService extends UserDetailsService {
    public KhachHang findKhachHangByName(String name);

    public KhachHang findKhachHangById(long id);

    public void save(KhachHang khachHang);

    public void update(KhachHang khachHang);

    public List<KhachHang> findAll();

}
