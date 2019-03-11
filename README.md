# emailsender
Email atan uygulama
===================

Bu çalışmada sizden aşağıdaki şartları karşılayan, kullanıcılara email yollayan bir uygulama yazmanız beklenmektedir.

1. Atılacak emailler fifo bir kuyruktan okunur.
2. İki farklı sağlayıcıdan gönderilen, üç tip email vardır:
    i) Welcome - mailing-provider-A
    ii) Forgot Password - mailing-provider-A
    iii) Weekly Newsletter - mailing-provider-B
3. Welcome "Firstname", "Lastname" parametreleri alır. Forgot Password "PasswordResetUrl" parametresi alır. Weekly Newsletter "Firstname", "Lastname" "NewsletterDate" ve "Newsletter" parametreleri alır.
4. Kuyruğa giren  email en fazla 60 sn içinde sağlayıcının endpointine aktarılmalıdır
5. A ve B sağlayıcıları için ikişer dummy metod yazılacaktır. Dummy init metodu kullanıcı adı, şifre, ve endpoint url alır. Dummy send metodu "to" adresi, "cc" adresi, email konusu ve email gövdesi parametreleri alır. Dummy send metodu gönderimin başarısında göre true ya da false döner.
6. Email gönderimi 3 defa denenir. 3üncü ve son denemede de gönderim başarısız olursa bir daha gönderim denenmez.
7. Yazılabilecek yerlerde sistem testi ve unit test yazmanız tavsiye edilir.
8. Java yazılım dili kullanılmalıdır. İstediğiniz frameworkü kullanabilirsiniz. 
9. Uygulamanın çalışması için gerekli veritabanı scriptlerini ya da verisi yüklenmiş bir container imajını lütfen paylaşınız.
10. Uygulamayı bedava bir git repository üzerinden paylaşabilirsiniz.


Örnek Welcome
-------------
Subject: Hoşgeldiniz!
Body: Sayın Firstname Lastname, aramıza hoşgeldiniz.

Örnek Forgot Password
---------------------
Subject: Şifre yenileme
Body: Şifrenizi yenilemek için aşağıdaki linke tıklayınız
      PasswordResetUrl
      
Örnek Newsletter
----------------
Subject: Firstname, NewsletterDate tarihli bültenimizi kaçırma
Body: Merhaba Firstname Lastname;
      Newsletter
      
schema.sql = database table created script

Kader Çelik