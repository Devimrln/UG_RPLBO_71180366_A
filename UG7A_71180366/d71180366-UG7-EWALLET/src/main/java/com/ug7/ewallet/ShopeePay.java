package com.ug7.ewallet;

import static com.ug7.ewallet.Main.formatRupiah;

public class ShopeePay extends eWallet{
    private int feeTopup = 500;
    private int feeTransfer = 0;
    private int feeWithdraw = 1000;

    public ShopeePay(User user){
        super(user);
    }
    public void topup(int jumlah){
        if(jumlah + feeTransfer > super.getUser().getUang()) {
            jumlah += feeTransfer;
            System.out.println("Maaf, uang cash kamu tidak mencukupi! ("+formatRupiah(this.getSaldo()-jumlah)+")");
            return;
        }
        super.topup(jumlah);
        this.pay(feeTransfer);
    }
    public void transfer(eWallet eWallet, int jumlah){
        if(!super.getUser().isEmailConfirmed()) {
            System.out.println("Halo, " + this.getUser().getNama() + "! Silakan konfirmasi email terlebih dahulu!");
            return;
        }
        System.out.println("Transfer saldo akan terkena potongan fee " + formatRupiah(feeTransfer));
        System.out.print("Transfer sedang diproses");
        Main.tunggu(3);
        if(jumlah + feeTransfer > super.getSaldo()) {
            jumlah += feeTransfer;
            System.out.println("Maaf, saldo kamu tidak mencukupi! ("+formatRupiah(this.getSaldo()-jumlah)+")");
            return;
        }
        super.transfer(eWallet, jumlah);
        this.pay(feeTransfer);
    }

    public void withdraw(int jumlah){
        System.out.println("Withdraw saldo akan terkena potongan fee " + formatRupiah(feeWithdraw));
        if(jumlah + feeWithdraw > super.getSaldo()) {
            jumlah += feeWithdraw;
            System.out.println("Maaf, saldo kamu tidak mencukupi! ("+formatRupiah(this.getSaldo()-jumlah)+")");
            return;
        }
        System.out.println("Halo, " + this.getUser().getNama() + "! Uang cash sejumlah " + formatRupiah(jumlah) + " telah masuk ke dompetmu!");
        super.withdraw(jumlah);
        this.pay(feeWithdraw);
    }

    public void getInfo(){
        System.out.println("======eWallet Shopee Pay======");
        super.getInfo();
    }
//
//    public int getFeeTopup() {
//        return feeTopup;
//    }
//
//    public void setFeeTopup(int feeTopup) {
//        this.feeTopup = feeTopup;
//    }
//
//    public int getFeeTransfer() {
//        return feeTransfer;
//    }
//
//    public void setFeeTransfer(int feeTransfer) {
//        this.feeTransfer = feeTransfer;
//    }
//
//    public int getFeeWithdraw() {
//        return feeWithdraw;
//    }
//
//    public void setFeeWithdraw(int feeWithdraw) {
//        this.feeWithdraw = feeWithdraw;
//    }
}
