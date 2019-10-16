package ml.wonwoo.githubissuesdashboard

import org.mockito.Mockito
import org.springframework.core.io.ClassPathResource

inline fun <reified T> any(): T = Mockito.any()

fun ClassPathResource.readString(): String = inputStream.bufferedReader().readText()