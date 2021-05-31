package ar.com.pablocaamano.heladeria.model

import java.io.Serializable

data class Order(val idOrder: Int, var iceCreams: MutableList<IceCream>) : Serializable;