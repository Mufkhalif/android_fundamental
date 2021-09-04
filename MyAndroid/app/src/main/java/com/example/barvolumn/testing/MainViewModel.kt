package com.example.barvolumn.testing

class MainViewModel (private val cuboidModel: CuboidModel){

    fun getCircumreference(): Double = cuboidModel.getCircumference()

    fun getSurfaceArea(): Double= cuboidModel.getSurfaceArea()

    fun getVolume(): Double = cuboidModel.getVolume()

    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(l, w, h)
    }
}