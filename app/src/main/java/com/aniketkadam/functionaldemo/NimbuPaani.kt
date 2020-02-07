package com.aniketkadam.functionaldemo

import com.aniketkadam.functionaldemo.Liquid.*

class NimbuPaani {

    fun makeNimbuPaani(
        waterBottles: List<WaterBottle>,
        saltProvider: SaltProvider,
        lemonProvider: LemonProvider
    ): List<LemonadeBottle> =

        waterBottles.map(saltProvider::addSalt)
                    .map(lemonProvider::addLemonJuice)

}

interface SaltProvider {
    fun addSalt(liquid: Liquid): SaltWaterBottle
}

interface LemonProvider {
    fun addLemonJuice(liquid: Liquid): LemonadeBottle
}

sealed class Liquid(val size: Int) {

    class WaterBottle(size: Int) : Liquid(size)

    class LemonadeBottle(size: Int) : Liquid(size)

    class SaltWaterBottle(size: Int) : Liquid(size)
}