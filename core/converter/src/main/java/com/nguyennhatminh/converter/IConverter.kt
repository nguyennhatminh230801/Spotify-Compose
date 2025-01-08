package com.nguyennhatminh.converter

interface IConverter<S, D> {
    fun convert(source: S): D
}