package com.yonsai.aws.dto;

public class Item {

    private String finPrdNm; // 금융상품명
    private String lnLmt;    // 대출한도
    private String irtCtg;   // 금리구분
    private String irt;      // 금리
    private String rdptmthd; // 상환방법
    private String trgt;     // 지원대상

    public Item() {
    }

    public Item(String finPrdNm, String lnLmt, String irtCtg, String irt,
                String rdptmthd, String trgt) {
        this.finPrdNm = finPrdNm;
        this.lnLmt = lnLmt;
        this.irtCtg = irtCtg;
        this.irt = irt;
        this.rdptmthd = rdptmthd;
        this.trgt = trgt;
    }

    public String getFinPrdNm() {
        return finPrdNm;
    }

    public void setFinPrdNm(String finPrdNm) {
        this.finPrdNm = finPrdNm;
    }

    public String getLnLmt() {
        return lnLmt;
    }

    public void setLnLmt(String lnLmt) {
        this.lnLmt = lnLmt;
    }

    public String getIrtCtg() {
        return irtCtg;
    }

    public void setIrtCtg(String irtCtg) {
        this.irtCtg = irtCtg;
    }

    public String getIrt() {
        return irt;
    }

    public void setIrt(String irt) {
        this.irt = irt;
    }

    public String getRdptmthd() {
        return rdptmthd;
    }

    public void setRdptmthd(String rdptmthd) {
        this.rdptmthd = rdptmthd;
    }

    public String getTrgt() {
        return trgt;
    }

    public void setTrgt(String trgt) {
        this.trgt = trgt;
    }
}
