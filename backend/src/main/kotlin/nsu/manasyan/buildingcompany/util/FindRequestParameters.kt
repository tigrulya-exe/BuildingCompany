package nsu.manasyan.buildingcompany.util

class FindRequestParameters {
    enum class Order {
        ASCENDING,
        DESCENDING
    }

    var page: Int? = null
    var pageSize: Int? = null
    var order: Order? = null
    var orderBy: String? = null
}