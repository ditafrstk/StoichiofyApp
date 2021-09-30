package com.uin.stoichiofyapp.Latihan

object Constants {


    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANWERS: String = "correct_answers"


    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        // 1
        val que1 = Question(
            1, "Jika 100 g suatu sampel yang mengandung Fe2O3 direduksi dengan gas karbon monoksida berlebih dihasilkan 28 g logam besi (Fe) dan karbon dioksida, maka kadar Fe2O3 di dalam sampel adalah… (Diketahui Ar Fe = 56, O = 16, H = 1, C = 12) (KSM Provinsi 2017)",
            "20%", "30%",
            "40%", "50%", "60%",3
        )
        questionsList.add(que1)

        // 2
        val que2 = Question(
            2, "Logam Al (Ar = 27) dapat bereaksi dengan larutan H2SO4 menghasilkan Al2(SO4)3 (Mr = 342) dan gas H2. Jika 5,4 g logam Al dimasukkan ke dalam 600 mL larutan H2SO4 0,1 M dan volume larutan dianggap tetap, maka akan diperoleh... (KSM Provinsi 2017)",
            "endapan Al2(SO4)3 34,2 g", "larutan H2SO4 0,02 M",
            "gas H2 1,2 g", "sisa logam Al 4,32 g", "volume gas H2 pada STP adalah 2,24 L", 4
        )
        questionsList.add(que2)

        // 3
        val que3 = Question(
            3, "Berat belerang paling ringan yang terdapat dalam 1 g garam-garam sulfat berikut adalah... (KSM Provinsi 2017)",
            "Al2(SO4)3 (Mr=342)", "Na2SO4 (Mr=142)",
            "MgSO4 (Mr=120)", "Fe2(SO4)3 (Mr= 400)", "CaSO4 (Mr= 116)", 2
        )
        questionsList.add(que3)

        // 4
        val que4 = Question(
            4, "Sukrosa (C12H22O11) dapat dioksidasi dengan asam sulfat pekat (H2SO4) menghasilkan karbon\n" +
                    "(C) dan air (H2O). Jika 171 g sukrosa direaksikan dengan asam sulfat pekat berlebih, maka berat karbon dan air yang dihasilkan adalah...(Diketahui Ar C=12, H=1, O=16) (KSM Provinsi 2017)",
            "36 g karbon dan 99 g air", "36 g karbon dan 135 g air",
            "72 g karbon dan 99 g air", "72 g karbon dan 121 g air", "108 g karbon dan 63 g air", 3
        )
        questionsList.add(que4)

        // 5
        val que5 = Question(
            5, "Jika propanol dibakar sempurna, maka perbandingan volume CO2(g) : H2O(g) yang dihasilkan dari pembakaran tersebut pada suhu dan tekanan tetap adalah… (KSM Provinsi 2017)",
            "1 : 1", "1 : 2",
            "2 : 3", "3 : 4", "4 : 5", 4
        )
        questionsList.add(que5)

        // 6
        val que6 = Question(
            6, "Logam seng (Zn, Ar = 65) dapat bereaksi dengan larutan asam klorida membentuk ZnCl2 dan\n" +
                    "gas H2. Jika 1,3 g seng direaksikan dengan 0,1 M asam klorida, maka volume minimal larutan\n" +
                    "asam klorida yang dibutuhkan agar semua seng habis bereaksi adalah… (KSM Provinsi 2017)\n",
            "0,1 L", "0,2 L",
            "0,3 L", "0,4 L", "0,6 L", 4
        )
        questionsList.add(que6)

        // 7
        val que7 = Question(
            7, "Seorang muslim harus memiliki kebiasaan yang teratur, termasuk pengaturan waktu makan.\n" +
                    "Apabila sering terlambat makan, lambung terasa perih. Untuk mengatasinya digunakan Antacid\n" +
                    "yang mengandung bahan aktif magnesium hidroxide, Mg(OH)2. Zat tersebut bereaksi dengan\n" +
                    "asam lambung (HCl) menghasilkan magnesium klorida (MgCl2) dan air. Massa\n" +
                    "Mg(OH)2  diperlukan untuk bereaksi dengan 0,30 g HCl adalah\t(KSM Kab/Kota 2018)\n",
            "0,188", "0,240",
            "0,375", "0,480", "0,520", 2
        )
        questionsList.add(que7)

        // 8
        val que8 = Question(
            8, "Kebersihan merupakan sebagian dari iman. Baju yang kotor karena noda membandel atau menempelnya warna yang tak diinginkan dapat dibersihkan dengan Clorox. Clorox mengandung hipoklorit yang bersifat racun. Ibrahim melakukan analisis iodometri untuk menentukan persentase massa hipoklorit dalam 1,356 g sampel Clorox. Diketahui reaksi yang terjadi sebagai berikut (belum setara) :\n"+
            "OCl–(aq) + 2H+(aq) + 2I–(aq) → I2(g) + Cl–(aq) + H2O(l)\n" +
                    "I2(g) + 2S2O 2–(aq) → S4O 2–(aq) + 2I–(aq)\n" +
            "Jika pada analisis digunakan 19,50 mL larutan 0,100 M Na2S2O3, persentase massa NaOCl dalam pemutih adalah .....(KSM Kab/Kota 2018)",
            "2,68%", "3,70%",
            "5,35%", "10,70%", "4,30%", 3
        )
        questionsList.add(que8)

        // 9
        val que9 = Question(
            9, "Sekitar 1 dari 20 batuan meteorit yang jatuh ke bumi mengandung sejumlah senyawa organik.\n" +
                    "Para ilmuwan menyelidiki senyawa organik yang menjadi molekul pembangun kehidupan di\n" +
                    "bumi kemungkinan berasal dari angkasa luar. Salah satu metode penentuan komposisi dan\n" +
                    "rumus senyawa adalah dengan metode reaksi pembakaran. Pada suatu eksperimen, telah dibakar\n" +
                    "dalam oksigen berlebih 3,795 mg cairan suatu sampel menghasilkan 9,708 mg CO2 dan 3,969\n" +
                    "mg H2O. Penyelidikan dilanjutkan dengan menguapkan sebanyak 0,205 g sampel pada suhu\n" +
                    "200oC dan tekanan 1 atm, sehingga menghasilkan 89,8 mL uap cairan. Rumus kimia senyawa\n" +
                    "tersebut adalah …",
            "C3H5O2", "C5H10O",
            "C5H8O2", "C10H20O2", "C5H20O", 2
        )
        questionsList.add(que9)

        // 10
        val que10 = Question(
            10, "Vanadium digunakan antara lain sebagai campuran baja, penyerap neutron di reaktor nuklir dan\n" +
                    "pelapis kaca yang dapat memblokir sinar inframerah. Namun debu oksida vanadium bersifat\n" +
                    "racun, sehingga dapat menyebabkan iritasi parah pada mata dan saluran/organ pernafasan. Jika\n" +
                    "diketahui pembakaran 10,00 g vanadium murni (massa molar = 50,492 g/mol) oleh oksigen di\n" +
                    "udara, diperoleh 17,852 g produk, maka rumus kimia oksida vanadium yang bersifat racun\n" +
                    "adalah… (KSM Provinsi 2018)",
            "VO", "VO2",
            "V2O", "V2O5", "V2O2", 4
        )
        questionsList.add(que10)

        // 11
        val que11 = Question(
            11, "Udara yang kita hirup mengandung 78% volume nitrogen dan 21% volume oksigen. Ketersediaannya begitu melimpah di alam. Kimiawan bereksperimen untuk membuat udara tiruan yang komposisinya sama dengan udara alami. Untuk itu nitrogen murni dihasilkan dari dekomposisi senyawa amonium dikromat (belum setara):\n" +
                    "(NH4)2Cr2O7(s) → N2(g) + Cr2O3(s) + H2O(g)\n" +
                    "Sedangkan oksigen dari dekomposisi termal kalium klorat (belum setara): KClO3 (s) → KCl(s) + O2(g)\n" +
                    "Untuk membuat 200 L udara tiruan pada tekanan 0,84 atm dan 273 K, massa (dalam gram) amonium dikromat dan kalium klorat yang dibutuhkan berturut-turut adalah ... (KSM Provinsi 2018)\n" +
                    "tersebut adalah …",
            "1,2 × 102 dan 1,5 × 103", "1,2 × 103 dan 1,5 × 102",
            "1,5 × 103 dan 1,2 × 102", "1,5 × 102 dan 1,2 × 103", "1,5 × 102 dan 1,5 × 103", 3
        )
        questionsList.add(que11)

        // 12
        val que12 = Question(
            12, "Udara yang kita hirup mengandung 78% volume nitrogen dan 21% volume oksigen. Ketersediaannya begitu melimpah di alam. Kimiawan bereksperimen untuk membuat udara tiruan yang komposisinya sama dengan udara alami. Untuk itu nitrogen murni dihasilkan dari dekomposisi senyawa amonium dikromat (belum setara):\n" +
                    "(NH4)2Cr2O7(s) → N2(g) + Cr2O3(s) + H2O(g)\n" +
                    "Sedangkan oksigen dari dekomposisi termal kalium klorat (belum setara): KClO3 (s) → KCl(s) + O2(g)\n" +
                    "Untuk membuat 200 L udara tiruan pada tekanan 0,84 atm dan 273 K, massa (dalam gram) amonium dikromat dan kalium klorat yang dibutuhkan berturut-turut adalah ... (KSM Provinsi 2018)\n" +
                    "tersebut adalah …",
            "1,2 × 102 dan 1,5 × 103", "1,2 × 103 dan 1,5 × 102",
            "1,5 × 103 dan 1,2 × 102", "1,5 × 102 dan 1,2 × 103", "1,5 × 102 dan 1,5 × 103", 4
        )
        questionsList.add(que12)

        // 13
        val que13 = Question(
            13, "Uranium oksida yang digunakan untuk preparasi bahan bakar reaktor nuklir merupakan hasil pemisahan dari mineral logam UOx(NO3)y(H20)z. Apabila dipanggang pada 400 0C maka senyawa ini kehilangan air dan ion nitrat terurai menjadi nitrogen oksida, serta dihasilkan UO3. Pemanggangan pada suhu yang lebih tinggi dari 4000C menghasilkan U3O8. Dalam suatu eksperimen, 1,328 g UOx(NO3)y(H20)z menghasilkan 1,042 g UOx(NO3)y. \n"+
                    "Pemanasan lebih lanjut akan diperoleh 0,742 g Uranium oksida. Berdasarkan data tersebut nilai x, y dan z adalah… (Massa molar , gr.mol-1; U=238, N=14, O=16) (KSM Nasional 2018)",
            "x=2, y=2, z=2", "x=2, y=3, z=4",
            "x=2, y=2, z=6", "x=3, y=2, z=4", "x=3, y=4, z=3", 3
        )
        questionsList.add(que13)

        // 14
        val que14 = Question(
            14, "Ibnu Hajar dalam kitab Tanbih Al Akhbar mengatakan Rasulullah pernah menyuruh para sahabat mengenakan pakaian warna hijau atau putih: “Pada hari raya kami disuruh memakai pakaian berwarna hijau karena warna hijau lebih utama. Adapun warna hijau adalah afdhal daripada warna lainnya, sesudah putih”. Warna hijau pada masa itu diperoleh secara alami dari tumbuh-tumbuhan namun kemudian pada abad-18 pigmen hijau sintesis tembaga (II) hydrogen arsenit CuHAsO3 digunakan pewarna tekstil, cat, dinding, hingga makanan.\n"
                    +"Pigmen hijau sintesis menghasilkan warna hijau terang yang sangat menarik dibanding pewarna hijau alami. Sayangnya pada kondisi lembab, pigmen hijau itu secara perlahan diubah oleh kapang (jamur) menjadi suatu gas yang sangat beracun yaitu trimetilarsine (CH3)3As. Akibatnya pekerja pabrik kain abad ke-18 kerap teracuni dan wanita yang berpakaian hijau dilaporkan pingsan akibat paparan arsenik pada kulit mereka. Keracunan arsenik dapat terjadi menjadi level 0,50 mg/m3.\n" +
                    "Seandainya suatu ruangan berukuran 12,5 m x 7,5 m x 3,0 m dicat menggunakan pigmen hijau.\n"
                    +"Berapa gram kah pigmen hijau yang digunakan untuk mencapai level efek toksisitas arsenik? (Diketahui Ar Cu=63,5; H=1; As=75; O=16) (KSM Provinsi 2019)",
            "0,14", "0,26",
            "0,35", "0,75", "0,45", 1
        )
        questionsList.add(que14)

        // 15
        val que15 = Question(
            15, "Gas xenon terkandung secara alami di atmosfer dengan konsentrasi sekitar 0,086 ppm. Xenon diekstraksi dari udara cair untuk digunakan antara lain sebagai bahan pengisi lampu alat photografi elektronik kecepatan tinggi dan lampu untuk membunuh bakteri.\n"
                    +"Walaupun dikenal sebagai salah satu unsur gas mulia, Xenon dapat bereaksi dengan unsur halogen melalui kondisi tertentu. Senyawaan Xenon digunakan untuk proses pembuatan mikroprosesor silikon dan 5- fluorourasil (obat pnghambat pertumbuhan sel-sel kanker).\n"
                    +"Produksi senyawa xenon dilakukan dengan mereaksikan gas Xenon dan florin dalam tabung nikel. Untuk itu, tabung nikel dengan volume 100 mL diisi dengan Xenon dan fluoride, tekanan parsial masing-masing berturut-turut adalah 1,24 atm dan 10,10 dan pada 250C. \n"
                    +"Kemudian tabung nikel dipanaskan hingga 4000C sehingga terbentuk zat padatan tak menguap. Gas F2 yang tidak bereaksi dipindahkan ke tabung nikel lainnya yang volumenya 100 mL. Tekanan parsial gas F2 dalam tabung itu pada 250C adalah 7,62 atm. Jika diasumsikan semua Xenon bereaksi, rumus senyawa hasil reaksi adalah… (KSM Nasional 2019)",
            "Xe F2", "Xe F4",
            "NiXe F2", "Xe2 NiF", "Ni F4", 2
        )
        questionsList.add(que15)


        return questionsList
    }

}