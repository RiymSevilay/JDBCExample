package com.sevilay;

import com.sevilay.controller.FutbolcuController;
import com.sevilay.repository.FutbolcuRepository;
import com.sevilay.repository.entity.Futbolcu;
import com.sevilay.utility.ConnectionProvider;

import java.sql.SQLOutput;
import java.util.Scanner;


public class Main {
    static Scanner sc = new Scanner(System.in);
    FutbolcuController futbolcuController = new FutbolcuController();

    public static void main(String[] args) {


        ConnectionProvider connectionProvider = new ConnectionProvider();
 //       connectionProvider.openConnection();
//         connectionProvider.closeConnection();
        FutbolcuRepository futbolcuRepository = new FutbolcuRepository();
        Futbolcu futbolcu=new Futbolcu("Alperen", "Orta Saha", 10,1L, 1L);
        futbolcuRepository.save(futbolcu);
//        Main main = new Main();
//        main.Menu();
//        FutbolcuRepository futbolcuRepository = new FutbolcuRepository();
//        Futbolcu futbolcu = new Futbolcu("Alperen","Orta Saha", 10,1L);
//        futbolcuRepository.save(futbolcu);

//        Futbolcu futbolcu1 = new Futbolcu("Muhammet","Forvet",9,15000L,3L);
//        futbolcu1.setId(2L);
//        futbolcuRepository.update(futbolcu1);

//        Optional<Futbolcu> futbolcu = futbolcuRepository.findById(6L);
//        if(futbolcu.isPresent()){
//            System.out.println(futbolcu);
//        } else {
//            System.out.println("Boyle bir futbolcu bulunamadi...");
//        }
//        List<Futbolcu> futbolcular = futbolcuRepository.findAll();
//        futbolcular.forEach(System.out::println);
//        futbolcuRepository.delete(3L);
//        List<Futbolcu> futbolcular1 = futbolcuRepository.findAll();
//        futbolcular1.forEach(System.out::println);
    }

    public void Menu() {
        int input = 0;
        do {
            System.out.println("1- Futbolcu Oluştur");
            System.out.println("2- Futbolcu Düzenle");
            System.out.println("3- Futbolcu Sil");
            System.out.println("4- Futbolcu Bul");
            System.out.println("5- Futbolcuları Getir");
            System.out.println("0- ÇIKIŞ");
            System.out.println("Lütfen bir seçim yapınız: ");
            input = Integer.parseInt(sc.nextLine());

            switch (input) {
                case 1:
                    futbolcuOlustur();
                    break;
                case 2:
                    futbolcuDuzenle();
                    break;
                case 3:
                    futbolcuSil();
                    break;
                case 4:
                    futbolcuBul();
                    break;
                case 5:
                    futbolculariGetir();
                    break;
                case 0:
                    System.out.println("Çıkış yapılıyor..");
                    break;

            }
        } while (input != 0);
    }

    private void futbolculariGetir() {
        System.out.println("******* FUTBOLCU LISTESI *******");
        futbolcuController.findAll().forEach(System.out::println);

    }

    private void futbolcuBul() {
        System.out.println("Lütfen bir futbolcu id'si giriniz: ");
        Long id = Long.parseLong(sc.nextLine());
        System.out.println(futbolcuController.findById(id));
    }

    private void futbolcuSil() {
        System.out.println("Lütfen silmek istediğiniz futbolcunun id'sini giriniz: ");
        Long id = Long.parseLong(sc.nextLine());
        futbolcuController.delete(id);

    }

    private void futbolcuDuzenle() {
        System.out.println("Lütfen bir isim giriniz: ");
        String isim = sc.nextLine();
        System.out.println("Lütfen bir mevki giriniz: ");
        String mevki = sc.nextLine();
        System.out.println("Lütfen bir forma no giriniz: ");
        int formaNo = Integer.parseInt(sc.nextLine());
        System.out.println("Lütfen bir piyasa değeri giriniz: ");
        Long deger = Long.parseLong(sc.nextLine());
        System.out.println("Lütfen bir takım id giriniz: ");
        Long takimId = Long.parseLong(sc.nextLine());
        System.out.println("Lütfen güncellemek istediğiniz futbolcunun id'sini giriniz: ");
        Long id = Long.parseLong(sc.nextLine());
        Futbolcu futbolcu = new Futbolcu(id, isim, mevki, formaNo, deger, takimId);
        futbolcuController.update(futbolcu);


    }

    private void futbolcuOlustur() {
        System.out.println("Lütfen bir isim giriniz: ");
        String isim = sc.nextLine();
        System.out.println("Lütfen bir mevki giriniz: ");
        String mevki = sc.nextLine();
        System.out.println("Lütfen bir forma no giriniz: ");
        int formaNo = Integer.parseInt(sc.nextLine());
        System.out.println("Lütfen bir piyasa değeri giriniz: ");
        Long deger = Long.parseLong(sc.nextLine());
        System.out.println("Lütfen bir takım id giriniz: ");
        Long takimId = Long.parseLong(sc.nextLine());
        Futbolcu futbolcu = new Futbolcu(isim, mevki, formaNo, deger, takimId);
        futbolcuController.save(futbolcu);

    }
}