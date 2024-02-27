interface FoodsDataSource {
    fun getFoodList(): List<FoodsModel>
}

class FoodsDataSourceImp(): FoodsDataSource{
    override fun getFoodList(): List<FoodsModel> {
        return listOf(
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
    }
}