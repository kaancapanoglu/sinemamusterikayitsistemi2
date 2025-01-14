Sinema Rezervasyon Sistemi

Giriş

Bu proje, Java ile yazılmış bir Sinema Rezervasyon Sistemidir. Kullanıcılar, mevcut salonları görüntüleyebilir, belirli film seansları için koltuk seçebilir ve rezervasyon yapabilir. Rezervasyonlar JSON dosyasına kaydedilerek kayıt altına alınır. Sistem, her biri belirli bir filmi gösteren birden fazla salonu ve bu salonların seanslarını yönetmek için tasarlanmıştır.

Özellikler

Salon ve Seansları Listele

Tüm mevcut salonları, ilgili filmleri ve seansları listeler.

Koltuk Rezervasyonu

Kullanıcıların bir salon, seans ve koltuk seçmesini sağlar.

Rezervasyon bilgileri müşteri adı ve telefon numarasını içerir.

Rezervasyon detaylarını bir JSON dosyasına kaydeder.

Satılan Biletleri Görüntüleme

Her salon ve seans için satılan tüm biletleri gösterir.

Hata Yönetimi

Salon, seans, koltuk ve blok seçimlerinde geçerli girişleri kontrol eder.

Kullanım

1. Programı Çalıştırın

Main sınıfını derleyip çalıştırın.

Aşağıdaki seçeneklerle bir menü görüntülenecektir:

1. Salon ve Seansları Listele
2. Müşteri Ekle
3. Satılan Biletleri Göster
4. Çıkış

İşlem seçmek için ilgili numarayı girin.

2. Salon ve Seansları Listele

Seçenek 1'i seçerek tüm mevcut salonları ve detaylarını görüntüleyebilirsiniz.

3. Koltuk Rezervasyonu Yap

Seçenek 2'yi seçin.

Listelenen salonlardan birini seçin.

Seçilen salon için bir seans belirleyin.

Koltuk bloğunu (A, B, C, D) ve numarasını (1-10) girin.

Adınızı ve telefon numaranızı girerek rezervasyonu tamamlayın.

Sistem, rezervasyonunuzu onaylayacak ve bilgileri rezervasyonlar.json dosyasına kaydedecektir.

4. Satılan Biletleri Görüntüle

Seçenek 3'ü seçin.

Sistem, her salon ve seans için satılmış tüm biletleri listeleyecektir.

5. Sistemden Çıkış

Seçenek 4'ü seçerek programdan çıkabilirsiniz.

Sınıflar

1. Film

Bir filmi temsil eder ve şu özelliklere sahiptir:

ad: Film adı

sure: Film süresi

tur: Film türü

seanslar: Önceden tanımlı seanslar (10:30, 15:30, 20:30)

2. Musteri

Bir müşteriyi temsil eder ve şu özelliklere sahiptir:

isim: Müşteri adı

telefonNo: Müşteri telefon numarası

3. Salon

Bir sinema salonunu temsil eder ve şu özelliklere sahiptir:

name: Salon adı

film: Salonda gösterilen film

koltuklar: Koltukların doluluk durumunu temsil eden boolean dizisi

bloklar: Koltuk bloklarını temsil eden dizi (A, B, C, D)

Ana Metotlar:

bosKoltuklariGoster(seansIndex): Belirli bir seans için boş koltukları gösterir.

satilanBiletleriGoster(): Satılan tüm biletleri listeler.

koltukRezerveEt(blok, koltukNo, seansIndex, musteri): Bir müşteri için koltuk rezerve eder.

saveRezervasyonToJson(musteri, blok, koltukNo, seans): Rezervasyon bilgilerini JSON dosyasına kaydeder.

4. Main

Ana menüyü ve kullanıcı etkileşimlerini yönetir.

Örnek salonlar ve filmleri içerir.

JSON Dosya Yapısı

Rezervasyonlar, aşağıdaki yapıya sahip rezervasyonlar.json dosyasına kaydedilir:

{
  "salon": "Salon 1 - Inception -> 148 Dakika",
  "film": "Inception",
  "seans": "10:30",
  "musteri": {
    "isim": "Ahmet Yılmaz",
    "telefonNo": "1234567890"
  },
  "koltuk": "A-1"
}

Hata Yönetimi

Salon, seans, koltuk bloğu ve koltuk numarası için geçerli girişleri kontrol eder.

Geçersiz veya dolu koltuk seçimlerinde uygun mesajlar görüntüler.

Gelecekteki İyileştirmeler

Dinamik film ve seans ekleme desteği eklenmesi.

Grafiksel kullanıcı arayüzü (GUI) uygulanması.

JSON dosya yapısının çok günlük rezervasyonları destekleyecek şekilde geliştirilmesi.

Koltuk iptali ve düzenleme özelliklerinin eklenmesi.

VIP bölümler gibi gelişmiş koltuk düzenleri eklenmesi.

Gereksinimler

Java Geliştirme Kiti (JDK): Sürüm 8 veya üzeri

IDE (isteğe bağlı): IntelliJ IDEA, Eclipse veya benzeri

Teşekkürler

Bu sistem, eğitim amaçlı tasarlanmış basitleştirilmiş bir sinema rezervasyon platformudur. Daha fazla geliştirme için gerçek dünya sinema API'leri ile entegrasyon yapılması önerilir.
