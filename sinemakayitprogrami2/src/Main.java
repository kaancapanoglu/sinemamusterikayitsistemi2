import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

// Film sınıfı
class Film {
    private String ad;
    private int sure;
    private String tur;
    private String[] seanslar = {"10:30", "15:30", "20:30"};

    public Film(String ad, int sure, String tur) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    public String getTur() {
        return tur;
    }

    public int getSure() {
        return sure;
    }

    public String[] getSeanslar() {
        return seanslar;
    }
}

// Müşteri sınıfı
class Musteri {
    private String isim;
    private String telefonNo;

    public Musteri(String isim, String telefonNo) {
        this.isim = isim;
        this.telefonNo = telefonNo;
    }

    public String getIsim() {
        return isim;
    }

    public String getTelefonNo() {
        return telefonNo;
    }
}

// Salon sınıfı
class Salon {
    private String name;
    private Film film;
    private boolean[][] koltuklar; // Seans başına koltuk durumu
    private char[] bloklar;

    public Salon(String name, Film film, int koltukSayisi) {
        this.name = name;
        this.film = film;
        this.koltuklar = new boolean[film.getSeanslar().length][koltukSayisi];
        this.bloklar = new char[] {'A', 'B', 'C', 'D'};
    }

    public String getName() {
        return name;
    }

    public Film getFilm() {
        return film;
    }

    public void bosKoltuklariGoster(int seansIndex) {
        System.out.print("Boş Koltuklar: ");
        for (int i = 0; i < koltuklar[seansIndex].length; i++) {
            if (!koltuklar[seansIndex][i]) {
                System.out.print(bloklar[i % bloklar.length] + "-" + ((i / bloklar.length) + 1) + " ");
            }
        }
        System.out.println();
    }

    public void satilanBiletleriGoster() {
        System.out.println("Salon: " + name);
        System.out.println("Film: " + film.getAd());
        String[] seanslar = film.getSeanslar();
        for (int seansIndex = 0; seansIndex < seanslar.length; seansIndex++) {
            System.out.println("Seans: " + seanslar[seansIndex]);
            System.out.println("Satılan Biletler:");
            boolean satilanBiletVar = false;
            for (int i = 0; i < koltuklar[seansIndex].length; i++) {
                if (koltuklar[seansIndex][i]) {
                    satilanBiletVar = true;
                    System.out.println("  Koltuk " + bloklar[i % bloklar.length] + "-" + ((i / bloklar.length) + 1));
                }
            }
            if (!satilanBiletVar) {
                System.out.println("  Henüz satılan bilet yok.");
            }
        }
    }

    public boolean koltukRezerveEt(char blok, int koltukNo, int seansIndex, Musteri musteri) {
        int index = (blok - 'A') + ((koltukNo - 1) * bloklar.length);
        if (index < 0 || index >= koltuklar[seansIndex].length) {
            System.out.println("Geçersiz blok veya koltuk numarası.");
            return false;
        }
        if (koltuklar[seansIndex][index]) {
            System.out.println("Bu koltuk zaten dolu.");
            return false;
        }
        koltuklar[seansIndex][index] = true;
        System.out.println("Rezervasyon başarılı! Seans: " + film.getSeanslar()[seansIndex] + ", Koltuk: " + blok + "-" + koltukNo);

        // JSON kaydı
        saveRezervasyonToJson(musteri, blok, koltukNo, film.getSeanslar()[seansIndex]);
        return true;
    }

    private void saveRezervasyonToJson(Musteri musteri, char blok, int koltukNo, String seans) {
        String json = "{\n" +
                "  \"salon\": \"" + name + "\",\n" +
                "  \"film\": \"" + film.getAd() + "\",\n" +
                "  \"seans\": \"" + seans + "\",\n" +
                "  \"musteri\": {\n" +
                "    \"isim\": \"" + musteri.getIsim() + "\",\n" +
                "    \"telefonNo\": \"" + musteri.getTelefonNo() + "\"\n" +
                "  },\n" +
                "  \"koltuk\": \"" + blok + "-" + koltukNo + "\"\n" +
                "}\n";

        try (FileWriter writer = new FileWriter("rezervasyonlar.json", true)) {
            writer.write(json);
            writer.write(",\n"); // Her rezervasyon arasına virgül ekler
        } catch (IOException e) {
            System.out.println("Rezervasyon kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }
}

// Ana sınıf (Main)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Salon> salonlar = new ArrayList<>();

        // Örnek salonlar ve filmler
        salonlar.add(new Salon("Salon 1 - Inception -> 148 Dakika", new Film("Inception", 148, "Bilim Kurgu/Aksiyon"), 40));
        salonlar.add(new Salon("Salon 2 - The Dark Knight -> 152 Dakika", new Film("The Dark Knight", 152, "Aksiyon/Suç"), 40));
        salonlar.add(new Salon("Salon 3 - Titanic -> 195 Dakika", new Film("Titanic", 195, "Romantik/Dram"), 40));

        while (true) {
            System.out.println("1. Salon ve Seansları Listele");
            System.out.println("2. Müşteri Ekle");
            System.out.println("3. Satılan Biletleri Göster");
            System.out.println("4. Çıkış");
            System.out.print("Lütfen bir işlem seçiniz: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    System.out.println("\n[Salon ve Seanslar]");
                    for (int i = 0; i < salonlar.size(); i++) {
                        Salon salon = salonlar.get(i);
                        System.out.println((i + 1) + ". " + salon.getName() + " - Film: " + salon.getFilm().getAd());
                        System.out.println("Seanslar: " + String.join(", ", salon.getFilm().getSeanslar()));
                    }
                    break;

                case 2:
                    System.out.println("\n[Salon Seçimi]");
                    for (int i = 0; i < salonlar.size(); i++) {
                        System.out.println((i + 1) + ". " + salonlar.get(i).getName());
                    }
                    System.out.print("Salon numarasını seçiniz: ");
                    int salonNo = scanner.nextInt() - 1;

                    if (salonNo >= 0 && salonNo < salonlar.size()) {
                        Salon secilenSalon = salonlar.get(salonNo);
                        System.out.println("Seçilen Salon: " + secilenSalon.getName());
                        System.out.println("Film: " + secilenSalon.getFilm().getAd());

                        String[] seanslar = secilenSalon.getFilm().getSeanslar();
                        for (int i = 0; i < seanslar.length; i++) {
                            System.out.println((i + 1) + ". " + seanslar[i]);
                        }
                        System.out.print("Seans numarasını seçiniz: ");
                        int seansNo = scanner.nextInt();
                        scanner.nextLine();

                        if (seansNo < 1 || seansNo > seanslar.length) {
                            System.out.println("Hatalı seçim. Lütfen geçerli bir seans seçiniz.");
                            break;
                        }

                        System.out.print("Blok seçiniz (A-D): ");
                        char blok = scanner.next().toUpperCase().charAt(0);

                        System.out.print("Koltuk numarasını giriniz (1-10) : ");
                        int koltukNo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Adınızı giriniz: ");
                        String isim = scanner.nextLine();
                        System.out.print("Telefon numaranızı giriniz: ");
                        String telefonNo = scanner.nextLine();

                        Musteri musteri = new Musteri(isim, telefonNo);
                        secilenSalon.koltukRezerveEt(blok, koltukNo, seansNo - 1, musteri);
                    } else {
                        System.out.println("Hatalı seçim. Lütfen geçerli bir salon seçiniz.");
                    }
                    break;

                case 3:
                    System.out.println("\n[Satılan Biletler]");
                    for (Salon salon : salonlar) {
                        salon.satilanBiletleriGoster();
                        System.out.println("-------------------------------------------------------------");
                    }
                    break;

                case 4:
                    System.out.println("Çıkış yapılıyor... Teşekkür ederiz!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Hatalı seçim. Lütfen tekrar deneyin.");
            }
        }
    }
}