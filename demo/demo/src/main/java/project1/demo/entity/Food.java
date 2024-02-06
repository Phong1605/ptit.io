package project1.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "ten_do_an")
    private String tenDoAn;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "gia_tien")
    private String giaTien;

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
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "khachhang_id")
    )
    private List<KhachHang> khachHangs;

    public Food() {
    }

    public Food(String ten, String moTa, String giaTien) {
        this.tenDoAn = ten;
        this.moTa = moTa;
        this.giaTien = giaTien;
    }

    public Food(String tenDoAn, String moTa, String giaTien, List<KhachHang> khachHangs) {
        this.tenDoAn = tenDoAn;
        this.moTa = moTa;
        this.giaTien = giaTien;
        this.khachHangs = khachHangs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenDoAn() {
        return tenDoAn;
    }

    public void setTenDoAn(String ten) {
        this.tenDoAn = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(String giaTien) {
        this.giaTien = giaTien;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    @Override
    public String toString() {
        return id + " " + tenDoAn + " " + moTa + " " + giaTien;
    }
}
