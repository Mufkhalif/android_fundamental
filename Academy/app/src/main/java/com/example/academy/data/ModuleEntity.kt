package com.example.academy.data

data class ModuleEntity(
    var modulId: String,
    var courseId: String,
    var title: String,
    var position: Int,
    var read: Boolean = false
) {
    var contentEntity: ContentEntity? = null
}