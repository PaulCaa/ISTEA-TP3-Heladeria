package ar.com.pablocaamano.heladeria.model

import java.io.Serializable

data class RegisterSale(val idCaja: Int, val order: Int, val price: Float) : Serializable;