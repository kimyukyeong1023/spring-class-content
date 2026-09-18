package com.yonsai.Day66_20260903.dto;

// 공공데이터가 전체 데이터를 다 주니깐
// 내가 필요한것만 골라서 담을려고 DTO를 
// 만든다. 그래서 서비스가 컨트롤러한테 전달할 때 
// 필요한것만 묶어서 전달한다. 
public class PharmacieDto {
    // 카카오맵에서 필요한 정보!데이터!
    // 위도,경도 ,이름,전화번호,주소,영업중
    private Double 위도;
    private Double 경도;
    private String 이름;
    private String 전화번호;
    private String 주소;
    private boolean 영업중;

    public Double get위도() {
        return 위도;
    }

    public void set위도(Double 위도) {
        this.위도 = 위도;
    }

    public Double get경도() {
        return 경도;
    }

    public void set경도(Double 경도) {
        this.경도 = 경도;
    }

    public String get이름() {
        return 이름;
    }

    public void set이름(String 이름) {
        this.이름 = 이름;
    }

    public String get전화번호() {
        return 전화번호;
    }

    public void set전화번호(String 전화번호) {
        this.전화번호 = 전화번호;
    }

    public String get주소() {
        return 주소;
    }

    public void set주소(String 주소) {
        this.주소 = 주소;
    }

    public Boolean get영업중() {
        return 영업중;
    }

    public void set영업중(Boolean 영업중) {
        this.영업중 = 영업중;
    }
}