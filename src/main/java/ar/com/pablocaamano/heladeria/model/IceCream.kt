package ar.com.pablocaamano.heladeria.model

import java.io.Serializable

data class IceCream(val type: Int, val price: Float, var flavors: MutableList<Flavor>?, val flavorsCount: Int) : Serializable;