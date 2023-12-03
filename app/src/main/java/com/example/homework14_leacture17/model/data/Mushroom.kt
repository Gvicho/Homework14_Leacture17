package com.example.homework14_leacture17.model.data

data class Mushroom(val id:Int,
                    val ifEdible: EDIBLE,
                    val name:String,
                    val habitats:List<HABITAT>) {

}

enum class EDIBLE(val id:Int){ // they will have different types of Items
    SAFE(1),
    DANGEROUS(2)
}

enum class HABITAT(val id:Int){
    CONIFEROUS_FOREST(1),
    MIXED_FORES(2),
    REGULAR_FORES(3),
    OPEN_FIELD(4)
}