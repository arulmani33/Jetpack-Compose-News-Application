package com.example.firsttep.domain.use_case.get_headline

import com.example.firsttep.data.remote.Resource
import com.example.firsttep.data.remote.dto.Article
import com.example.firsttep.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHeadLineUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(country: String): Flow<Resource<List<Article>>> = flow {
        try {
            emit(Resource.Loading())
            val news = repository.getHeadLine(country)
            emit(Resource.Success(news))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}