package project1.demo.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "ho_dem")
    private String hoDem;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_sinh")
    private String ngaySinh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "khachhangs_roles",//Tên bảng trung gian
            joinColumns = @JoinColumn(name = "khach_hang_id"),//xác định cột trong bảng trung gian liên kết với khóa chính của employee
            inverseJoinColumns = @JoinColumn(name = "role_id")//xác định cột trong bảng trung gian liên kết với khóa chính của role
    )
    private Collection<Role> Roles;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinTable(
            name = "khachhang_food",
            joinColumns = @JoinColumn(name = "khachhang_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private List<Food> foods;

    public KhachHang() {
    }

    public KhachHang(String userName, String passWord, String hoDem, String ten, String ngaySinh, String soDienThoai, String email, List<Food> foods) {
        this.userName = userName;
        this.passWord = passWord;
        this.hoDem = hoDem;
        this.ten = ten;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.foods = foods;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getHoDem() {
        return hoDem;
    }

    public void setHoDem(String hoDem) {
        this.hoDem = hoDem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Collection<Role> getRoles() {
        return Roles;
    }

    public void setRoles(Collection<Role> roles) {
        Roles = roles;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return id + " " + hoDem + " " + ten + " " + ngaySinh + " " + soDienThoai + " " + email;
    }
}
