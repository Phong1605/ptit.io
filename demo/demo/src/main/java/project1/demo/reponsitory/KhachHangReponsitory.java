package project1.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project1.demo.entity.KhachHang;

@Repository
public interface KhachHangReponsitory extends JpaRepository<KhachHang,Long> {
    public KhachHang findKhachHangByUserName(String username);

    public KhachHang findKhachHangById(long id);
}
