# Tutorial APAP
## Authors
* **Muhammad Ichsanul Amal** - *1906353454* - *A*



---

## Tutorial 6

1. **Jelaskan secara singkat perbedaan Otentikasi dan Otorisasi! Di bagian mana (dalam kode yang telah anda buat) konsep tersebut diimplementasi?**

- Otentikasi merupakan proses untuk melakukan verifikasi apakah pengguna yang ingin login dengan username tersebut telah terdaftar di database dan berhak untuk masuk dan mengakses aplikasi. Biasanya melibatkan username dan password, tetapi dapat menyertakan metode lain yang dapat menunjukkan identitas seperti sidik jari.
- Berikut merupakan contoh implementasi otentifikasi pada class `WebSecurityConfig`: @Autowired public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{auth.userDetailsService(userDetailsService).passwordEncode(encoder());}
- Otorisasi merupakan proses untuk menentukan hak pengguna apakah pengguna memiliki akses ke halaman tertentu yang telah diotentifikasi dan menentukan apakah orang yang sudah diidentifikasi (diotentikasi), diperbolehkan untuk memanipulasi sumber daya tertentu. Biasanya ditentukan dengan mencari apakah orang itu memiliki akses ke sumber daya tertentu.
- Berikut merupakan contoh implementasi otorisasi pada class `WebSecurityConfig`: authorizeRequests() .antMatchers("/css/**").permitAll() .antMatchers("/js/**").permitAll() .antMatchers("/resep/**").hasAnyAuthority("APOTEKER","ADMIN")

2. **Apa itu BCryptPasswordEncoder? Jelaskan secara singkat cara kerjanya!**

- BCryptPasswordEncoder merupakan salah satu fungsi password hashing. BCryptPasswordEncoder bertugas untuk melakukan enkripsi password user sebelum disimpan ke database. BCryptPasswordEncoder juga bertugas untuk melakukan encrypt terhadap password pada saat user ingin melakukan login. Cara kerjanya adalah saat membuat user baru dengan membuat username dan password baru, password akan dienkripsi menggunakan fungsi BCrypt sehingga password yang terlihat pada database adalah password yang sudah dienkripsi.

3. **Apakah penyimpanan password sebaiknya menggunakan encryption atau hashing? Mengapa demikian?**

- Hashing dan enkripsi keduanya menyediakan cara untuk menjaga keamanan data sensitif. Namun, penyimpanan password perlu dihash. Enkripsi adalah fungsi dua arah, artinya plaintext asli dapat diambil kembali. Enkripsi sesuai untuk menyimpan data seperti alamat pengguna karena data ini ditampilkan dalam teks biasa pada profil pengguna. Sedangkan Hashing adalah fungsi satu arah (yaitu, tidak mungkin untuk "mendekripsi" hash dan mendapatkan nilai aslinya) sehingga Hashing sesuai untuk validasi password.


4. **Jelaskan secara singkat apa itu UUID beserta penggunaannya!**

- UUID (Universally Unique Identifier) merupakan kode identifikasi unik yang diberikan oleh sistem secara acak dengan algoritma tertentu. UUID digunakan untuk memungkinkan sistem men-generate id pengguna secara unik dengan hashing sebanyak 32 karakter secara acak yang bertujuan untuk meningkatkan keamanan data pengguna sehingga id pengguna aman dan tidak mudah untuk diretas. Mirip seperti BCrypt, bedanya kali ini adalah ID, bukan password. Saat kita membuat user baru, sistem akan otomatis melakukan pemberian kode unik yang akan terlihat pada database dengan tipe UUID.

5. **Apa kegunaan class UserDetailsServiceImpl.java? Mengapa harus ada class tersebut ?**

- Class UserDetailsServiceImpl.java mengimplementasi interface UserDetailService yang sudah disediakan oleh spring security. Class tersebut berguna untuk mengambil informasi otentikasi dan otorisasi pengguna. Tujuannya agar Spring Boot Security dapat melakukan otorisasi terhadap pengguna yang melakukan login sesuai dengan rolenya yang sudah terdaftar di database. Class ini harus ada karena class UserServiceImpl dan RoleServiceImpl tidak dapat memberikan informasi kepada Spring boot mengenai otentikasi dan otorisasi dari akun-akun yang ada pada database sistem.



## Tutorial 5

1. **Apa itu Postman? Apa kegunaannya?** Postman adalah aplikasi atau development tool API yang digunakan untuk melakukan build, test, dan modify API. Postman berfungsi sebagai REST Client dimana dapat digunakan untuk uji REST API. Dengan Postman, kita sebagai developer dapat mendokumentasikan, tes, mendesain, debug, menerbitkan, dan memonitor API pada satu tempat. Developer tidak harus menulis HTTP client network code, tetapi membuat test suites yang dinamakan "Collections" ketika menggunakan Postman untuk melakukan testing. Postman akan berinteraksi dengan API secara otomatis.

2. **Jelaskan fungsi dari anotasi @JsonIgnoreProperties dan @JsonProperty.**

- @JsonIgnoreProperties berfungsi untuk memberikan perintah pada class untuk mengabaikan properti logis yang ditentukan dalam serialisasi dan deserialisasi JSON. Sebagai contoh, ketika kita memberikan true untuk element ignoreUnknown, maka field JSON akan diabaikan jika data JSON memiliki field yang tidak mempunyai properti logis.
- @JsonProperty merupakan anotasi yang memiliki fungsi untuk mengubah nama variabel. @JsonProperty memberitahu Jackson ObjectMapper untuk memetakan nama property JSON ke nama field Java yang diberikan anotasi.

3. **Apa kegunaan atribut WebClient?** WebClient yang digunakan pada RestServiceImpl merupakan sebuah interface yang disediakan oleh Spring Framework yang merupakan bagian dari reactive client. WebClient memiliki fungsi sebagai poin akses utama dari web / HTTP requests. Selain itu, WebClient juga berfungsi untuk menginstansiasi sebuah akses poin URL serta mengelola request dan response atas URL tersebut.

4. **Apa itu ResponseEntity dan BindingResult? Apa kegunaannya?**

- ResponseEntity merupakan salah satu class yang terdapat di java dan mewakili respons HTTP, termasuk header, body, dan status. Seperti @ResponseBody yang menempatkan return value ke body dari response, ResponseEntity juga memungkinkan kita untuk dapat menambahkan header dan kode status.
- BindingResult merupakan objek Spring yang menyimpan hasil dari validasi, binding, dan error atas model objek yang divalidasikan. BindingResult berisikan informasi mengenai kesalahan, seperti field yang diperlukan, adanya ketidakcocokan jenis atau kesalahan dalam melakukan pemanggilan method. BindingReslut digunakan dengan cara diletakkan setelah parameter objek validasi.

## Tutorial 4

1. Jelaskan perbedaan th:include dan th:replace! 

   Keduanya merupakan cara untuk memasukkan konten dari sebuah fragmen. Perbedaannya adalah, pada relace tagnya diganti dengan tag pada fragment, sedangkan pada include tidak.

   - contoh fragment

   ```
   <div th:fragment="targetFragmentToIncludeInOurPage" id="tagWithFragmentAttribute">
    <div id="contentGoesHere"></div>
   </div>
   ```

   - penggunaan pada div

   ```
   <div id="tagWithReplaceAttribute" th:replace="fragments/header :: targetFragmentToIncludeInOurPage"></div>
   <div id="tagWithInsertAttribute" th:insert="fragments/header :: targetFragmentToIncludeInOurPage"></div>
   <div id="tagWithIncludeAttribute" th:include="fragments/header :: targetFragmentToIncludeInOurPage"></div>
   ```

   - hasil

   ```
   <div id="tagWithFragmentAttribute">
    <div id="contentGoesHere"></div>
   </div>
   
   <div id="tagWithInsertAttribute">
    <div id="tagWithFragmentAttribute">
     <div id="contentGoesHere"></div>
    </div>
   </div>
   
   <div id="tagWithIncludeAttribute">
    <div id="contentGoesHere"></div>
    </div>
   ```

   

2. Jelaskan apa fungsi dari th:object! 

   th:object digunakan untuk menentukan objek yang akan dibound oleh data pada form yang dikirimkan

3. Jelaskan perbedaan dari * dan $ pada saat penggunaan th:object! Kapan harus dipakai?

   \* digunakan saat ingin mengambil nilai dari atribut pada th:object yang disebutkan, sedangkan cara mengambil nilai dari atribut menggunakan $ adalah dengan `${<nama_objek>.atribut}`. Atau secara singkat ${} berarti variable expressions sedangkan *{} selection variable expressions



---

## Tutorial 3

**1. Tolong jelaskan secara singkat apa kegunaan dari anotasi-anotasi yang ada pada model (@AllArgsConstructor, @NoArgsConstructor, @Setter, @Getter, @Entity, @Table)**

@AllArgsConstructor untuk menambahkan constructor secara otomatis dari class berdasarkan semua argumen, @NoArgsConstructor untuk menambahkan constructor secara otomatis dari class tanpa argumen, @Setter untuk menambahkan setter secara otomatis dari class, @Getter untuk menambahkan getter secara otomatis dari class, @Entity untuk merelasikan class dengan entitas atau table pada database, dan @Table untuk men-set nama table pada entity yang terhubung dengan class.

**2. Pada class CabangDB, terdapat method findByNoCabang, apakah kegunaan dari method tersebut?**

Method tersebut merupakan fitur dari JPA Repository, fungsinya adalah memudahkan dalam pencarian ke database berdasarkan NoCabang yang mana NoCabang merupakan atribut dari Cabang.

**3. Jelaskan perbedaan kegunaan dari anotasi @JoinTable dan @JoinColumn**

@JoinTable menyimpan id dari kedua tabel ke dalam tabel baru terpisah, sedangkan @JoinColumn menyimpan id dari tabel lain di kolom baru, bukan pada tabel baru.

**4. Pada class PegawaiModel, digunakan anotasi @JoinColumn pada atribut cabang, apa kegunaan dari name, referencedColumnName, dan nullable dalam anotasi tersebut? dan apa perbedaan nullable dan penggunaan anotasi @NotNull**

`name` adalah kolom atribut pada PegawaiModel yang berelasi dengan Model lainnya, `referencedColumnName` adalah kolom pada Model lain yang berelasi dengan PegawaiModel, sedangkan `nullable` menyatakan boleh tidaknya null dalam suatu kolom, atau constraint null pada database. nullable lebih ditujukan untuk penggunaan database, sedangkan @NotNull adalah lebih umum untuk mengecek variabel atau hasil dari method tidak boleh null.

**5. Jelaskan kegunaan FetchType.LAZY, CascadeType.ALL, dan FetchType.EAGER**

FetchType.LAZY mengambil data dari database hanya saat dibutuhkan, CascadeType.ALL berarti semua persistensi akan menyebarkan (cascade) ke semua operasi EntityManager seperti PERSIST, REMOVE, REFRESH, MERGE, DETACH tanpa mengecek kondisi entitas yang berhubungan terlebih dahulu, sedangkan FetchType.EAGER mengambil data secara *immediate* atau langsung sekaligus.



## Tutorial 2

**Pertanyaan 1: Cobalah untuk menambahkan sebuah Kebun dengan mengakses link berikut setelah menjalankan program: http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom&noTelepon=081xxx Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi**

Terdapat Whitelabel Error Page karena pada Controller terdapat return  (“add-kebun-safari”), padahal file htmlnya belum dibuat.

**Pertanyaan 2: Menurut kamu anotasi @Autowired pada class Controller tersebut merupakan implementasi dari konsep apa? Dan jelaskan secara singkat cara kerja @Autowired tersebut dalam konteks service dan controller yang telah kamu buat**

Dependency injection. Spring akan mencari `KebunSafariService` dan implementasinya sesuai dengan nama atribut yang didefinisikan (dalam hal ini adalah `KebunSafariInMemoryService`), kemudian menginisiasi dan menginject dependency-nya. Dengan demikian, pada controller kita tidak perlu menginisiasinya secara manual dan bisa langsung menggunakan container tersebut.

**Pertanyaan 3: Cobalah untuk menambahkan sebuah Kebun dengan mengakses link berikut: http://localhost:8080/kebun-safari/add?id=1&nama=Papa%20APAP&alamat=Maung%20Fasilkom Apa yang terjadi? Jelaskan mengapa hal tersebut dapat terjadi.**

"*Required request parameter 'noTelepon' for method parameter type String is not present*" yang artinya client tidak memberikan parameter noTelepon, padahal di controller sudah diwajibkan untuk diisi.

**Pertanyaan 4: Jika Papa APAP ingin melihat Kebun Safari dengan nama Papa APAP, link apa yang harus diakses?**

Tidak bisa, detail kebun safari hanya bisa dicari menggunakan id nya saja.

**Pertanyaan 5: Tambahkan 1 contoh Kebun Safari lainnya sesukamu. Lalu cobalah untuk mengakses http://localhost:8080/ , apa yang akan ditampilkan? Sertakan juga bukti screenshotmu.**

Akan ditampilkan list dari kebun safari yang telah ditambahkan.

![](extras/t21.png)

![](extras/t22.png)

## Tutorial 1 

### What I have learned today 

### Github 

1. Apa itu Issue Tracker? Apa saja masalah yang dapat diselesaikan dengan Issue Tracker? (Tuliskan jawabanmu) 

   

   Issues Tracker merupakan fitur untuk tracking tasks, enhancements, dan bugs pada proyek yang dibuat. Masalah yang dapat diselesaikan adalah seputar dokumentasi, seperti isu yang sudah diselesaikan bisa diclose, dan yang masih dikerjakan dapat dijadikan prioritas. Selain itu, issue tracker juga memudahkan kolaborasi seperti untuk pull request, me-review commit, dan lain-lain.

   

2. Apa perbedaan dari git merge dan git merge --squash? 

   

   Pada git merge, semua commit yang ada pada branch yang melakukan pull request akan ditampilkan pada branch utama. Sedangkan "--squash" menjadikan commit yang ditambahkan pada branch utama menjadi hanya satu saja.

   

3. Apa keunggulan menggunakan Version Control System seperti Git dalam pengembangan suatu aplikasi? 

   

   VCS memudahkan proses pengembangan aplikasi agar setiap pihak yang ikut dalam pengembangan proyek bisa melihat dokumentasi real time dari pengembangannya. Apabila aplikasi terbaru yang dikembangkan kurang sesuai dengan yang diinginkan atau bahkan lebih buruk, pengembang dapat dengan mudah kembali ke versi sebelumnya untuk memperbaiki sesuai yang diinginkan seperti mesin waktu.

   

### Spring 

4. Apa itu library & dependency? 

   

   Library adalah sekumpulan package atau koleksi kelas yang telah disediakan oleh organisasi pengembang bahasa pemrograman untuk dapat digunakan oleh pengguna. Sedangkan dependency adalah ketergantungan antar kelas. Contoh dependency adalah saat ingin membuat program Java dengan konsep MVC, kita memerlukan framework seperti SpringBoot, yang artinya program yang kita buat dependent terhadap Spring Boot, atau Spring Boot merupakan dependency.

   

5. Apa itu Maven? Mengapa kita menggunakan Maven? Apakah ada alternatif dari Maven? 

   

   Apache Maven adalah Java Build Tools yang menggunakan konsep Project Object Model (POM). POM tersebut berisi informasi dan konfigurasi yang digunakan Maven untuk membuat project. Pada dasarnya POM adalah sebuath XML File yang terdapat di dalam project Maven dan di dalam File inilah konfigurasi dari project kita berada. Maven digunakan untuk mengkonfigurasi proyek java seperti untuk mengatur dependency, testing, dan lain-lain. Alternatif selain Maven adalah Gradle pada Java.

   

6. Selain untuk pengembangan web, apa saja yang bisa dikembangkan dengan Spring framework? 

   

   Spring dapat digunakan untuk membangun microservices, reactive, produk cloud, serverless, event-driven, dan batch. Selain itu, Spring juga dapat digunakan untuk:

   - Java Inversion of Control (IoC) Container atau disebut juga Dependency Injection
     Spring membantu pembuatan kontainer dalam menampung object-object yang memiliki ketergantungan pada object lain dalam melakukan transaksi. IoC berupa layer abstrak yang menampung object-object yang akan digunakan secara singleton (singleton adalah object yang dibuat sekali dan ditampung terus dalam memory selama aplikasi berjalan). Contoh object yang disimpan didalam IoC Container adalah SessionManager yang membuat koneksi dengan database. Contoh penggunaan objek SessionManager tersebut dalam IoC Container yaitu melakukan auto wire data injeksi object kedalam object yang akan melakukan transaksi dalam database.

   - Transaction Management
     Spring framework menyediakan sebuah layer abstrak yang generik untuk manajemen transaksi, sehingga memudahkan para developer dalam melakukan manajemen transaksi.
   - JDBC Exception Handling
     layer abstrak JDBC menawarkan exception yang bersifat hierarki sehingga memudahkan penanganan error.
   - Integrasi dengan hibernate, JDO, Email dan ibatis
   - Object-Relational-Mapping (ORM)Menggunakan Hibernate ORM.
     ORM adalah pola pemograman database tanpa harus berinteraksi langsung dengan bahasa SQL.

   

7. Apa perbedaan dari @RequestParam dan @PathVariable? Kapan sebaiknya menggunakan @RequestParam atau @PathVariable? 

   

   Perbedaan utama antara @RequestParam dan @PathVariable adalah bahwa @RequestParam digunakan untuk mengakses nilai parameter kueri sedangkan @PathVariable digunakan untuk mengakses nilai dari template URI. @RequestParam digunakan saat ingin mengambil nilai parameter permintaan dalam bentuk meneruskan parameter permintaan dengan url dan @PathVariable digunakan saat ingin mengambil nilai parameter dalam templat URI permintaan formulir dengan beberapa placeholder. Data yang dikirim melalui @RequestParam juga perlu diencode sehingga untuk mengirimkan informasi yang sensitif sebaiknya menggunakan anotasi ini, sedangkan pada @PathVariable lebih menampilkan informasi yang lebih general dan bisa dibookmark.

   

### What I did not understand 

(tuliskan apa saja yang kurang Anda mengerti, Anda dapat men-_check_ apabila Anda sudah mengerti dikemudian hari, dan tambahkan tulisan yang membuat Anda mengerti) 

- [x] Kenapa saya harus belajar APAP? Karena dengan APAP kita dapat mengerti bagaimana cara membangun aplikasi dengan skala besar yang fleksibel sesuai kebutuhan yang diinginkan.

