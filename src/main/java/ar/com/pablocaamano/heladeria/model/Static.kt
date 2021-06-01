package ar.com.pablocaamano.heladeria.model

import java.io.Serializable

data class Static(val name: String, val orders: Int, val money: Float) : Serializable;