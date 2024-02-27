//Abstraction: Abstract Class
abstract class FoodsData: UserInterface{
    //Encapsulation: Private
    protected var userAnswer: FoodsModel? = null
    protected var returnMoney : Double? = null
    protected var totAmount : Double? = null
    protected var amount : Int = -1
    protected var answer : Int = -1
    protected var payment: Double? = null
    protected var method : Int = -1


    protected val foodsData = listOf(
        FoodsModel(
            foodName = "Ayam Bakar",
            foodPrice = 50000.0
        ),
        FoodsModel(
            foodName = "Ayam Goreng",
            foodPrice = 40000.0
        ),
        FoodsModel(
            foodName = "Ayam Geprek",
            foodPrice = 40000.0
        ),
        FoodsModel(
            foodName = "Kulit Ayam Crispy",
            foodPrice = 15000.0
        ),
        FoodsModel(
            foodName = "Sate Usus Ayam",
            foodPrice = 5000.0
        )
    )

    //SOLID Principle: S = Single Responsibility
    abstract fun printMenu(creatorName: String = "Deny Iqbal Mubarok")

    override fun inputUserAnswer(){
        println("=====================================")
        try {
            print("Pilih Pesanan Anda(1/2/3/4/5): ")
            answer = readln().toInt()
            if (answer !in 1..foodsData.size){
                println("Pilihan tidak Tersedia, silahkan coba lagi!")
                inputUserAnswer()
            }
            showUserAnswer()
        }catch (e: NumberFormatException){
            println("Maaf, Inputan Anda Salah.")
            println("Coba Pilih Kembali!")
            inputUserAnswer()
        }
    }

    abstract fun showUserAnswer()

    override fun inputUserFoodPortion(){
        try {
            if (answer in 1..foodsData.size){
                userAnswer = foodsData[answer - 1]
                println("=====================================")
                print("Anda ingin beli ${userAnswer!!.foodName} berapa porsi? : ")
                amount = readln().toInt()
                showUserFoodPortion()
            }
        }catch (e: NumberFormatException){
            println("Maaf, Inputan Anda Salah.")
            println("Coba Pilih Kembali!")
            inputUserFoodPortion()
        }
    }
    abstract fun showUserFoodPortion()

    abstract fun getBonus()

    override fun inputUserPayment(){
        try {
            if (answer in 1..foodsData.size){
                userAnswer = foodsData[answer - 1]
                println("=====================================")
                print("Masukkan Pembayaran: Rp.")
                payment = readln().toDouble()
                showUserPayment()
            }
        }catch (e: NumberFormatException){
            println("Maaf, Inputan Anda Salah.")
            println("Coba Pilih Kembali!")
            inputUserPayment()
        }
    }

    abstract fun showUserPayment()

    override fun inputDeliveryMethod(){
        println("=====================================")
        println("1. Take Away")
        println("2. Delivery")
        try {
            print("Pilih Metode Pengiriman Makanan (1/2): ")
            method = readln().toInt()
            showDeliveryMethod()
        }catch (e: NumberFormatException){
            println("Maaf, Inputan Anda Salah.")
            println("Coba Pilih Kembali!")
            inputDeliveryMethod()
        }
    }

    abstract fun showDeliveryMethod()
}

//Subclass from Abstract Class
class Food: FoodsData(){
    override fun printMenu(creatorName: String){
        println("=====================================")
        println(" Nama Pembuat: $creatorName")
        println("=====================================")
        println("=====================================")
        println("|        Daftar Menu Makanan        |")
        println("=====================================")
        foodsData.forEachIndexed{ index, data->
            println("${index+1}. ${data.foodName}: Rp.${data.foodPrice}/porsi")
        }
        inputUserAnswer()
    }

    override fun showUserAnswer(){
        if (answer in 1..foodsData.size){
            userAnswer = foodsData[answer - 1]
            println("Kamu memilih Menu $answer")
            println("Nama Menu: ${userAnswer!!.foodName}")
            println("Harga/porsi: Rp.${userAnswer!!.foodPrice}")
            inputUserFoodPortion()
        }
    }

    override fun showUserFoodPortion(){
        if (answer in 1..foodsData.size){
            userAnswer = foodsData[answer - 1]
            totAmount = formula(amount, userAnswer!!.foodPrice)
            println("Anda membeli ${userAnswer!!.foodName} sebanyak $amount porsi")
            println("Total Harga: Rp.$totAmount")
        }
        getBonus()
    }

    override fun getBonus(){
        when(totAmount!!){
            in 0.0..<100000.0 -> println("Anda tidak mendapatkan Bonus")
            in 100000.0..<150000.0 -> println("Selamat, Anda mendapatkan Bonus 2 Buah Tempe Goreng")
            in 150000.0..<200000.0 -> println("Selamat, Anda mendapatkan Bonus 3 Buah Tahu Goreng")
            in 200000.0..<250000.0 -> println("Selamat, Anda mendapatkan Bonus 2 Buah Tahu Goreng + Lalapan ")
//            !in 0..250000 -> ""
            else -> {
                println("Selamat, Anda mendapatkan Bonus 3 Buah Tempe Goreng + 3 Buah Tahu Goreng + Lalapan")
            }
        }
        inputUserPayment()
    }

    override fun showUserPayment(){
        returnMoney = formula(payment!!, totAmount!!)
        if (payment!! > totAmount!!){
            println("Terimakasih, pemesanan Anda berhasil")
            println("Kamu dapat kembalian: Rp.$returnMoney")
            inputDeliveryMethod()
        }else if (payment == totAmount!!){
            println("Terimakasih, pemesanan Anda berhasil.")
            println("Uang anda Pas.")
            inputDeliveryMethod()
        }else{
            println("Maaf pembayaran gagal.")
            println("Uang anda Kurang. Silahkan coba lagi")
            inputUserPayment()
        }
    }

    //Polymorphism? / Overloading?
    private fun formula(x: Double, y: Double): Double {
        return x-y
    }

    private fun formula(a: Int, b: Double): Double {
        return a*b
    }

    override fun showDeliveryMethod(){
        when (method) {
            1 -> {
                for (i in 1..10){
                    if (i == 1){
                        println("Makananmu sedang dimasak (5 detik) .....")
                        Thread.sleep(5000)
                    }
                    if (i == 5){
                        println("Makananmu sudah siap! Silakan ambil di resto ya! (5 detik) .....")
                        Thread.sleep(5000)
                    }
                    if (i == 8){
                        println("Pesanan selesai! (3 detik) ...")
                        Thread.sleep(3000)
                    }
                }
            }
            2 -> {
                for (i in 1..10){
                    if (i == 1){
                        println("Makananmu sedang dimasak (5 detik) .....")
                        Thread.sleep(5000)
                    }
                    if (i == 5){
                        println("Makananmu sudah siap! Driver segera menuju tempatmu! (5 detik) .....")
                        Thread.sleep(5000)
                    }
                    if (i == 8){
                        println("Driver sampai! Pesanan selesai! (3 detik) ...")
                        Thread.sleep(3000)
                    }
                }
            }
            else -> {
                println("Pilihan tidak tersedia.")
                println("Coba pilih kembali.")
                inputDeliveryMethod()
            }
        }
    }

    //Encapsulation: Public
    fun run(){
        printMenu()
    }
}