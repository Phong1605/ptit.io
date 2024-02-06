package project1.demo.web;

import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

public class KhachHangRegister {

    @NotNull(message = "Thông tin bắt buộc")
    @Size(min = 1,message = "Phải chứa tối thiểu 1 kí tự")
    private String userName;

    @NotNull(message = "Thông tin bắt buộc")
    @Size(min = 8, message = "Phải chứa tối thiểu 8 kí tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Mật khẩu phải chứa ít nhất 1 kí tự đặc biệt và 1 số")
    private String passWord;

    @NotBlank(message = "Thông tin bắt buộc")
    private String hoDem;

    @NotBlank(message = "Thông tin bắt buộc")
    private String ten;

    @NotBlank(message = "Thông tin bắt buộc")
    private String soDienThoai;

    @NotBlank(message = "Thông tin bắt buộc")
    private String ngaySinh;

    @NotBlank(message = "Thông tin bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;

    public KhachHangRegister() {
    }

    public KhachHangRegister(String userName, String passWord, String hoDem, String ten, String soDienThoai, String ngaySinh, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.hoDem = hoDem;
        this.ten = ten;
        this.soDienThoai = soDienThoai;
        this.ngaySinh = ngaySinh;
        this.email = email;
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

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}