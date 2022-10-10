package br.com.alura.bytebank.modelo

enum class HisoryType {
    SCDI;

    companion object {
        fun contains(): String {
            return enumValues<HisoryType>().toString()
        }
    }
}