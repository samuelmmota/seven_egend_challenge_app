package s.m.mota.seven_egend_challenge_app.repository

import s.m.mota.seven_egend_challenge_app.models.ConversationResponse


enum class RepositoryDataSourceEnum {
    DATABASE, REMOTE
}

/**
 * Dictates which sources will be used by the repository's fetch logic.
 */
enum class RepositoryFetchStrategy(private val sourceList: List<RepositoryDataSourceEnum>) {
    /**
     * Fetches from the regular order, using the DB as an offline alternative
     */
    STANDARD(
        listOf(
            RepositoryDataSourceEnum.REMOTE, RepositoryDataSourceEnum.DATABASE
        )
    );

    suspend fun fetch(
        databaseDataSource: LocalDatabaseSource, remoteDataSource: ApiDataSource
    ): ConversationResponse {
        val dataSourceHolder = DataSourceHolder(databaseDataSource, remoteDataSource)
        var dataSourceResult: ConversationResponse? = null

        for (source in this.sourceList) {
            val dataSource = dataSourceHolder.getDataSourceFromEnum(source)
            dataSourceResult = dataSource.fetch()

            updateOtherSourcesIfRequired(source, dataSourceResult, dataSourceHolder)
        }
        return dataSourceResult ?: ConversationResponse(emptyList(), emptyList())
    }

    private suspend fun updateOtherSourcesIfRequired(
        source: RepositoryDataSourceEnum,
        dataSourceListResult: ConversationResponse?,
        dataSourceHolder: DataSourceHolder,
    ) {
        if (dataSourceListResult == null) {
            return
        }
        when (source) {
            RepositoryDataSourceEnum.REMOTE -> {
                dataSourceHolder.database.insertMessages(*dataSourceListResult.messages.toTypedArray())
                dataSourceHolder.database.insertUsers(*dataSourceListResult.users.toTypedArray())
            }

            RepositoryDataSourceEnum.DATABASE -> {}
        }
    }

    private class DataSourceHolder(
        val database: LocalDatabaseSource, val remote: ApiDataSource
    ) {
        fun getDataSourceFromEnum(sourceEnum: RepositoryDataSourceEnum) = when (sourceEnum) {
            RepositoryDataSourceEnum.REMOTE -> remote
            RepositoryDataSourceEnum.DATABASE -> database
        }
    }
}
