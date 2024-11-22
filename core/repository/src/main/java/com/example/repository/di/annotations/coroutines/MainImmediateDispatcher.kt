package com.example.repository.di.annotations.coroutines

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainImmediateDispatcher
