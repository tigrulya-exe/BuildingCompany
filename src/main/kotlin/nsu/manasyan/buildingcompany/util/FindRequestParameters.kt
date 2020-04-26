package nsu.manasyan.buildingcompany.util

class FindRequestParameters{
    enum class Order{
        ASCENDING,
        DESCENDING
    }

    var page: Int? = null
    var size: Int? = null
    var order: Order? = null
    var sortColumnName: String? = null
}