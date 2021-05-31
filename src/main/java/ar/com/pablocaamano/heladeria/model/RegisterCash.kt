package ar.com.pablocaamano.heladeria.model

import java.io.Serializable

data class RegisterCash(val id: Int, val name: String, val description: String, val count: Int) : Serializable;