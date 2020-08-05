package com.example.mvvmtestproject.data.model

class APIError(
    var message: String?,
    var statusCode: Int?
) {
    constructor() : this(null, -0)
}